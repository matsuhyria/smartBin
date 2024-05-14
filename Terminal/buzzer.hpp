#pragma once
#include <Arduino.h>

class Buzzer {
public:
    Buzzer(int pin);
    void setup();
    void playTone(int frequency, int duration);
    void stopTone();
    void notify(int distance, int threshold);
    void alarm();

private:
    int _pin;
};