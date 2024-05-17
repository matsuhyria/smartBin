#ifndef indicat_h
#define indicat_h

#include <Adafruit_NeoPixel.h>

class LedIndicator{
  public:
    LedIndicator(uint16_t pixels, int16_t pin, neoPixelType type, int turnOnDistanceCM);
    void setup();
    void turnOn(int on_distance_cm);
    
  private:
    int calculateLedsToLight(int curr_distance_cm);
    int getColor(int leds_on);
    Adafruit_NeoPixel _led;
    const int PIXELS;
    const int TURN_ON_DISTANCE_CM;
    const int GREEN_THRESHOLD = PIXELS / 2;
    const int YELLOW_THRESHOLD = (4 * PIXELS) / 5;
};

#endif