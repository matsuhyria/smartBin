#ifndef FLAME_DETECTOR_HPP
#define FLAME_DETECTOR_HPP

#include <Arduino.h>

class FlameDetector {
public:
    FlameDetector(int pin);
    void setup();
    bool detect();
    
private:
    int _pin;
};

#endif