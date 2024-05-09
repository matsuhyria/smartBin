#include "ui.hpp"


UserInterface::UserInterface(TFT_eSPI& tft)
    : _tft(tft), _boxColor(0x03EF), _textColor(0xC618),
      _x1(15), _y1(50), _w1(300), _h1(40),
      _x2(15), _y2(_y1 + _h1 + 10), _w2(300), _h2(40) {
}

void UserInterface::setup() {
    _tft.begin();
    _tft.setRotation(3);
    _tft.setTextSize(3);
    _tft.fillScreen(TFT_WHITE);
}

void UserInterface::updateHumidity(float humidity) {
    _tft.setTextSize(3);// repeatition
    _tft.fillRoundRect(_x1, _y1, _w1, _h1, 5, _boxColor);
    _tft.setTextColor(_textColor, _boxColor);
    _tft.setCursor(_x1 + 5, _y1 + 5);
    _tft.printf("Humidity: %.1f%%", humidity);
}

void UserInterface::updateDistance(int distance) {
    _tft.fillRoundRect(_x2, _y2, _w2, _h2, 5, _boxColor);
    _tft.setTextColor(_textColor, _boxColor);
    _tft.setCursor(_x2 + 5, _y2 + 5);
    _tft.printf("Distance: %d cm", distance);
}

void UserInterface::showWelcomeScreen() {
  _tft.begin();
  _tft.setRotation(3);
  _tft.setTextSize(4); 
  _tft.fillScreen(TFT_BLACK);
  _tft.setTextColor(_textColor);
  _tft.setCursor(40, 50);
  _tft.println("Welcome to ");
  _tft.setCursor(45, 90);
  _tft.setTextColor(_boxColor);
  _tft.println("SmartBin+");
  _tft.setTextColor(_textColor);
  _tft.setTextSize(2);
  _tft.setCursor(40, 180);
  _tft.println("Press the button to");
  _tft.setCursor(60, 200);
  _tft.println("connect to WiFi");
}

void UserInterface::clearScreen() {
    _tft.fillScreen(TFT_BLACK);
}