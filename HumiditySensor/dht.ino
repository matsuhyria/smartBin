#include <DHT.h>
#include "TFT_eSPI.h"

const int DHTPIN = A0;
const int DHTTYPE = DHT11;

TFT_eSPI tft;

TFT_eSprite spr = TFT_eSprite(&tft);

DHT dht(DHTPIN, DHTTYPE);

void setup(){
  Serial.begin(9600);
  dht.begin();
  tft.begin(); 
  tft.setRotation(1); 
  spr.createSprite(TFT_HEIGHT,TFT_WIDTH); //buffer for faster display
}

void loop(){
  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();
  delay(500);

  spr.fillSprite(TFT_BLACK); 
  spr.setTextSize(2); 
  spr.setTextColor(TFT_YELLOW); 
    
  spr.drawString("Temp & Humidity", 55, 10); 
  spr.drawFastHLine(40, 35, 240, TFT_DARKGREY); 
    
  spr.setTextColor(TFT_WHITE); 
  spr.drawString("- temp(*C): ", 20, 50);
  spr.drawString("- humidity(%): ", 20, 80);
    
  spr.drawNumber(temperature, 200,50); 
  delay(50);
  spr.drawNumber(humidity, 200, 80); 
    
  spr.pushSprite(0,0); //draw everything from the buffer
  delay(1000);
}
