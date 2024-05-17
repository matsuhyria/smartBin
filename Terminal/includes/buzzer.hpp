#pragma once
#include <Arduino.h>

class Buzzer {
public:
    Buzzer(int pin);
    void setup();
    void playTone(int frequency, int duration);
    void stopTone();
    void notify(int distance);
    void alarm();

private:
    int _pin;
};