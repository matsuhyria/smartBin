#include <Ultrasonic.h> //include ultrasonic distance sensor library
#include <TFT_eSPI.h> //include TFT LCD library
#include <Adafruit_NeoPixel.h>


#define NUMPIXELS       10
#define NEOPIXEL_PIN    BCM3
#define BIN_DISTANCE_CM 50


TFT_eSPI tft; //initialize TFT LCD


TFT_eSprite spr = TFT_eSprite(&tft); //initialize buffer
 
Ultrasonic ultrasonic(D0); //set ultrasonic distance sensor pin




Adafruit_NeoPixel pixels = Adafruit_NeoPixel(NUMPIXELS, NEOPIXEL_PIN, NEO_GRB + NEO_KHZ800);




void setup()
{
  tft.begin(); //start TFT LCD
  tft.setRotation(3); //set screen rotation
  spr.createSprite(TFT_HEIGHT,TFT_WIDTH); //create buffer
 


  pixels.setBrightness(255);
  pixels.begin();
}
void loop()
{
    spr.fillSprite(TFT_BLACK); //fill background
    spr.setTextSize(2); //set text size
    spr.setTextColor(TFT_YELLOW); //set text color
   
    spr.drawString("Distance to Object", 55, 10); //draw text string
    spr.drawFastHLine(40, 35, 240, TFT_DARKGREY); //draw horizontal line
   
    spr.setTextColor(TFT_WHITE);
    spr.drawString("- Centimeters: ", 20, 50);
    spr.drawString("- Inches: ", 20, 80);
   
    float distance_cm = ultrasonic.MeasureInCentimeters();


    spr.drawNumber(distance_cm, 200,50); //display distance in centimeters
    delay(50);
    spr.drawNumber(ultrasonic.MeasureInInches(), 130,80); //display distance in inches
   
    spr.pushSprite(0,0); //push to LCD
    delay(500);
   
    int num_leds_to_light;
    if (distance_cm > BIN_DISTANCE_CM) {
      num_leds_to_light = 0;
    } else {
      float distance_percentage = (100-((distance_cm / BIN_DISTANCE_CM) * 100));
      num_leds_to_light = map(distance_percentage, 0, 100, 0, NUMPIXELS);
    }
   
    //Serial.print(num_leds_to_light);


    for(int i=0;i<NUMPIXELS;i++){
    if (i < num_leds_to_light) {
        if (i < 5) {
            pixels.setPixelColor(i, pixels.Color(0, 255, 0)); // Green color
        }
        else if (i < 8) {
            pixels.setPixelColor(i, pixels.Color(255, 255, 0)); // Yellow color
        }
        else {
            pixels.setPixelColor(i, pixels.Color(255, 0, 0)); // Red color
        }
    }
    else {
        pixels.setPixelColor(i, pixels.Color(0, 0, 0)); //Turn off
    }
    }
    pixels.show();
}
