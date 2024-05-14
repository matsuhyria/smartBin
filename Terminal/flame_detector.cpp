#include "flame_detector.hpp"

FlameDetector::FlameDetector(int pin) : _pin(pin) {}

void FlameDetector::setup() {
    pinMode(_pin, INPUT);
}

bool FlameDetector::detect() {
    if(digitalRead(_pin))
    return false;
    else return true;
}
