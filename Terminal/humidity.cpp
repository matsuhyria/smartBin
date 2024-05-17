#include "includes/humidity.hpp"

Humidity::Humidity(int pin, int type):_dht(pin, type){};

void Humidity::setup(){
  _dht.begin();
};

float Humidity::read() {
  return _dht.readHumidity(); 
};

