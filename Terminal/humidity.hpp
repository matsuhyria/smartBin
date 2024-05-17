#ifndef humid_h
#define humid_h
#include <DHT.h>

class Humidity{
  public:
    Humidity(int pin, int type);
    void setup();
    float read();
  private:
    DHT _dht;  
};

#endif