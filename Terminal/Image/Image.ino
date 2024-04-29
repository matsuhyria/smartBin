#include "TFT_eSPI.h"
#include "Seeed_FS.h"
#include "RawImage.h"
TFT_eSPI tft;

void setup() {
  if (!SD.begin(SDCARD_SS_PIN, SDCARD_SPI)){
    while(1);
  }

  tft.begin();
  tft.setRotation(3);
  drawImage<uint16_t>("background_bin.bmp", 0,0);

  tft.setTextSize(3);
  uint16_t boxColor = 0x03EF;
  uint16_t textColor = 0xC618;

  int x1 = 25, y1 = 50, w1 = 285, h1 = 40; 
  tft.fillRoundRect(x1, y1, w1, h1, 5, boxColor); // Draw a rounded rectangle box
  tft.setTextColor(textColor, boxColor); 
  tft.setCursor(x1 + 5, y1 + 5);
  tft.print("Humidity: 60.5%"); 

  int x2 = 25, y2 = y1 + h1 + 10, w2 = 285, h2 = 40;
  tft.fillRoundRect(x2, y2, w2, h2, 5, boxColor); 
  tft.setTextColor(textColor, boxColor);
  tft.setCursor(x2 + 5, y2 + 5);
  tft.print("Distance: 10 cm"); 
}

void loop() {
  
}
