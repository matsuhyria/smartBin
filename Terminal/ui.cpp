#include <string>
#include "ui.hpp"

UserInterface::UserInterface(TFT_eSPI& tft)
    : _tft(tft), _boxColor(0x03EF), _textColor(0xC618),
      _x1(5), _y1(60), _w1(150), _h1(45),
      _x2(165), _y2(60), _w2(150), _h2(45) {
}

void UserInterface::setup() {
    _tft.begin();
    _tft.setRotation(3);
    _tft.setTextSize(3);
    _tft.fillScreen(TFT_WHITE);
}

void UserInterface::humidityHeader() {
  _tft.fillRoundRect(_x1, _y1, _w1, _h1, 5, _boxColor);
  _tft.setTextColor(_textColor, _boxColor);
  _tft.setCursor(_x1 + 3, _y1 + 10);
  _tft.printf("Humidity");
}

void UserInterface::distanceHeader() {
  _tft.fillRoundRect(_x2, _y2, _w2, _h2, 5, _boxColor);
  _tft.setTextColor(_textColor, _boxColor);
  _tft.setCursor(_x2 + 3, _y2 + 10);
  _tft.printf("Fullness");
}

void UserInterface::updateHumidity(float humidity) {
  _tft.fillRoundRect(_x1 + 20, _y1 + 50, _w1 - 20, _h1 + 50, 5, TFT_BLACK);
  _tft.setTextColor(_textColor);
  _tft.setCursor(_x1 + 20, _y1 + 70);
  _tft.setTextSize(5.5);
  _tft.printf("%d", (int)humidity);
  _tft.print("%");

}

void UserInterface::updateDistance(int distance) {
  float calculatedDistance = 0.0;
  if (distance > 50){
    calculatedDistance = 0.0;
  }else{
    calculatedDistance = 100.0 - ((distance/50.0) * 100.0);
  }

  _tft.fillRoundRect(_x2 + 20, _y2 + 50, _w2 - 20, _h2 + 50, 5, TFT_BLACK);
  _tft.setTextColor(_textColor);
  _tft.setCursor(_x2 + 20, _y2 + 70);
  _tft.setTextSize(5.5);
  _tft.printf("%d", (int)calculatedDistance);
  _tft.print("%");
}

void UserInterface::showWelcomeScreen() {
  _tft.begin();
  _tft.setRotation(3);
  _tft.setTextSize(4); 
  _tft.fillScreen(TFT_BLACK);
  _tft.setTextColor(_textColor);
  _tft.setCursor(40, 50);
  _tft.println("Welcome to ");
  _tft.setCursor(55, 90);
  _tft.setTextColor(_boxColor);
  _tft.println("SmartBin+");
  _tft.setTextColor(_textColor);
  _tft.setTextSize(2);
  _tft.setCursor(40, 180);
  _tft.println("Press the button to");
  _tft.setCursor(60, 200);
  _tft.println("connect to WiFi");
}

void UserInterface::showHeader() {
  clearScreen();
  _tft.setRotation(1);
  _tft.setTextSize(3);
  _tft.setCursor(85, 10);
  _tft.setTextColor(_boxColor);
  _tft.println("SmartBin+");
  _tft.drawFastHLine(0, 45, _tft.width(), _boxColor);
  _tft.drawFastVLine(160, 45, _tft.height() , _boxColor);
}

void UserInterface::clearScreen() {
    _tft.fillScreen(TFT_BLACK);
}