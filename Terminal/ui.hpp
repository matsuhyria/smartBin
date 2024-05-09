#include "TFT_eSPI.h"

class UserInterface {
public:
    UserInterface(TFT_eSPI& tft);
    void setup();
    void updateHumidity(float humidity);
    void updateDistance(int distance);
    void showWelcomeScreen();
    void clearScreen();

private:
    TFT_eSPI& _tft;
    uint16_t _boxColor;
    uint16_t _textColor;
    int _x1, _y1, _w1, _h1;
    int _x2, _y2, _w2, _h2;
};