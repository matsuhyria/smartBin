#include "ultrasonic.hpp"

UltrasonicRanger::UltrasonicRanger(int pin):_uls(pin) {};

int UltrasonicRanger::measureDistance(){
  return _uls.MeasureInCentimeters();
}

int UltrasonicRanger::calculateFullness(){
  float calculatedDistance = 0.0;
  int distance = measureDistance();

  if (distance > 18){
    calculatedDistance = 0.0;
  }else{
    calculatedDistance = (100.0 - ((distance/18.0) * 100.0));
  }

  return (int)calculatedDistance;
}