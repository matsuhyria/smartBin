#include <string>
#include "includes/ui.hpp"

UserInterface::UserInterface(TFT_eSPI& tft)
    : _tft(tft), _boxColor(0x03EF), _textColor(0xC618),
      _boxHumidityX1(5), _boxHumidityY1(50), _boxHumidityW1(150), _boxHumidityH1(40),
      _boxFullnessX2(165), _boxFullnessY2(_boxHumidityY1), _boxFullnessW2(_boxHumidityW1), _boxFullnessH2(_boxHumidityH1) {
}

void UserInterface::humidityHeader() {
  _tft.fillRoundRect(_boxHumidityX1, _boxHumidityY1, _boxHumidityW1, _boxHumidityH1, 5, _boxColor);
  _tft.setTextColor(_textColor, _boxColor);
  _tft.setCursor(_boxHumidityX1 + 3, _boxHumidityY1 + 10);
  _tft.printf("Humidity");
}

void UserInterface::distanceHeader() {
  _tft.fillRoundRect(_boxFullnessX2, _boxFullnessY2, _boxFullnessW2, _boxFullnessH2, 5, _boxColor);
  _tft.setTextColor(_textColor, _boxColor);
  _tft.setCursor(_boxFullnessX2 + 3, _boxFullnessY2 + 10);
  _tft.printf("Fullness");
}

void UserInterface::updateHumidity(float humidity) {
  _tft.fillRoundRect(_boxHumidityX1 + 20, _boxHumidityY1 + 120, _boxHumidityW1 - 20, _boxHumidityH1 + 20, 5, TFT_BLACK);
  _tft.setTextColor(_textColor);
  _tft.setCursor(_boxHumidityX1 + 20, _boxHumidityY1 + 130);
  _tft.setTextSize(5.5);
  _tft.printf("%d", (int)humidity);
  _tft.print("%");
  _tft.drawXBitmap(10, 100, Bitmaps::humidity120_70, 120, 70, _textColor);
}

void UserInterface::updateDistance(int fullness) {
  _tft.fillRoundRect(_boxFullnessX2 + 20, _boxFullnessY2 + 120, _boxFullnessW2 - 20, _boxFullnessH2 + 20, 5, TFT_BLACK);
  _tft.setTextColor(_textColor);
  _tft.setCursor(_boxFullnessX2 + 20, _boxFullnessY2 + 130);
  _tft.setTextSize(5.5);
  _tft.printf("%d", fullness);
  _tft.print("%");
  _tft.drawXBitmap(200, 95, Bitmaps::fullness_bin100_100, 70, 75, _textColor);
}

void UserInterface::setupWelcomeScreen() {
  _tft.begin();
  _tft.setRotation(3);
  _tft.setTextSize(3); 
  _tft.fillScreen(TFT_BLACK);
  _tft.setTextColor(_textColor);

  _tft.fillRoundRect(90, 75, 135, 125, 15, _boxColor);
  _tft.drawXBitmap(100, 75, Bitmaps::welcome150_153, 120, 120, _textColor);
  delay(1500);
  
  _tft.setCursor(70, 10);
  _tft.println("Welcome to");
  _tft.setCursor(85, 45);
  _tft.println("SMARTBIN+");
  delay(2000);

  _tft.setTextSize(2);
  _tft.setCursor(45, 205);
  _tft.println("Press the button to");
  _tft.setCursor(65, 225);
  _tft.println("connect to WiFi");
}

void UserInterface::showHeader() {
  clearScreen();
  _tft.setTextSize(3);
  _tft.setCursor(85, 10);
  _tft.setTextColor(_boxColor);
  _tft.println("SmartBin+");
  _tft.drawFastHLine(0, 45, _tft.width(), _boxColor);
  _tft.drawFastVLine(160, 45, _tft.height() , _boxColor);
}

void UserInterface::showConnectionTitle() {
  clearScreen();
  _tft.setCursor(40, 60);
  _tft.setTextSize(4);
  _tft.println("Connection");
}

void UserInterface::showConnectionLoop() {
  _tft.drawXBitmap(120, 100, Bitmaps::levelOne70_75, 70, 75, _textColor);
  delay(1500);
  _tft.drawXBitmap(120, 100, Bitmaps::levelTwo70_75, 70, 75, _textColor);
  delay(1500);
  _tft.drawXBitmap(120, 100, Bitmaps::levelThree70_75, 70, 75, _textColor);
  delay(1500);
  _tft.fillRoundRect(115, 95, 135, 125, 5, TFT_BLACK);
}

void UserInterface::clearScreen() {
    _tft.fillScreen(TFT_BLACK);
}