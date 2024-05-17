#include "includes/led_indicator.hpp"

LedIndicator::LedIndicator(uint16_t pixels, int16_t pin, neoPixelType type, int turnOnDistanceCM): _led(pixels, pin, type), PIXELS(pixels), TURN_ON_DISTANCE_CM(turnOnDistanceCM) {};

void LedIndicator::setup(){
  _led.begin();
}

int LedIndicator::calculateLedsToLight(int curr_distance_cm){
  if(curr_distance_cm > TURN_ON_DISTANCE_CM){
    return 0;
  }
  //we scale the current distance by a factor of 10 to adjust the sensitivity of LED activation. 
  //This ratio is subtracted from the total number of LEDs, determining the number of LEDs to light up.
  //The scaled distance is then divided by the threshold distance for LED activation to get a ratio representing the object's proximity relative to the threshold.
  return PIXELS - ((curr_distance_cm * 10) / TURN_ON_DISTANCE_CM);
}

int LedIndicator::getColor(int leds_on){
  if (leds_on < GREEN_THRESHOLD) {
    return _led.Color(0, 255, 0);
  } else if (leds_on < YELLOW_THRESHOLD) {
    return _led.Color(255, 255, 0);
  } else {
    return _led.Color(255, 0, 0);
  }
}

void LedIndicator::turnOn(int distance){
  int leds_to_light = calculateLedsToLight(distance);
  for(int i = 0; i < PIXELS; i++){
    if(i < leds_to_light){
      _led.setPixelColor(i, getColor(i));
    } else{
      _led.setPixelColor(i, _led.Color(0, 0, 0));
    }
  }
  _led.show();
  delay(100);
}