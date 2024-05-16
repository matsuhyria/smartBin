#ifndef ultras_h
#define ultras_h
#include <Ultrasonic.h>

class UltrasonicRanger{
  public:
    UltrasonicRanger(int pin, int turnOnDistanceCM);
    int measureDistance();
    int calculateFullness();

  private:
    Ultrasonic _uls;
    const int TURN_ON_DISTANCE_CM;
};

#endif