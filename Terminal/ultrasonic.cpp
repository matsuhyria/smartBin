#include "ultrasonic.hpp"

UltrasonicRanger::UltrasonicRanger(int pin, int turnOnDistanceCM):_uls(pin), TURN_ON_DISTANCE_CM(turnOnDistanceCM) {};

int UltrasonicRanger::measureDistance(){
  return _uls.MeasureInCentimeters();
}

int UltrasonicRanger::calculateFullness(){
  float calculatedDistance = 0.0;
  int distance = measureDistance();

  if (distance > TURN_ON_DISTANCE_CM){
    calculatedDistance = 0.0;
  }else{
    calculatedDistance = (100.0 - ((distance/(float)TURN_ON_DISTANCE_CM) * 100.0));
  }

  return (int)calculatedDistance;
}