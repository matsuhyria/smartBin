#ifndef ultras_h
#define ultras_h
#include <Ultrasonic.h>

class UltrasonicRanger{
  public:
    UltrasonicRanger(int pin);
    int measureDistance();
  private:
    Ultrasonic _uls;
};

#endif