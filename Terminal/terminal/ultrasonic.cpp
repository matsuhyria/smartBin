#include "ultrasonic.hpp"

UltrasonicRanger::UltrasonicRanger(int pin):_uls(pin) {};

int UltrasonicRanger::measureDistance(){
    return _uls.MeasureInCentimeters();
}