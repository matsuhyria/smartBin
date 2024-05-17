#ifndef UI_HPP
#define UI_HPP

#include "TFT_eSPI.h"
#include "bitmaps.hpp"

class UserInterface {

public:
    UserInterface(TFT_eSPI& tft);
    void setupWelcomeScreen();
    void updateHumidity(float humidity);
    void updateDistance(int distance);
    void clearScreen();
    void showHeader();
    void distanceHeader();
    void humidityHeader();
    void showConnectionTitle();
    void showConnectionLoop();

private:
    TFT_eSPI& _tft;
    const uint16_t _boxColor;
    const uint16_t _textColor;
    int _boxHumidityX1, _boxHumidityY1, _boxHumidityW1, _boxHumidityH1;
    int _boxFullnessX2, _boxFullnessY2, _boxFullnessW2, _boxFullnessH2;
};

#endif