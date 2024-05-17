#include "includes/ultrasonic.hpp"

UltrasonicRanger::UltrasonicRanger(int pin, int turnOnDistanceCM):_uls(pin), TURN_ON_DISTANCE_CM(turnOnDistanceCM) {};

int UltrasonicRanger::measureDistance(){
  return _uls.MeasureInCentimeters();
}

//calculates the fullness of the container converting it to percentages.
//it calculates the fullness percentage based on the distance to the threshold:
int UltrasonicRanger::calculateFullness(){
  float calculatedDistance = 0.0;
  int distance = measureDistance();

  if (distance > TURN_ON_DISTANCE_CM){
    calculatedDistance = 0.0;
  }else{
    //(distance / TURN_ON_DISTANCE_CM) makes the proportion of the current distance to the maximum distance.
    // when subtracting this proportion from 1.0 and multiplying by 100 we convert it to a percentage representing how full the container actually is.
    calculatedDistance = (100.0 - ((distance/(float)TURN_ON_DISTANCE_CM) * 100.0));
  }

  return (int)calculatedDistance;
}