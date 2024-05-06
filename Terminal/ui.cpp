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
    _tft.setTextSize(3);
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