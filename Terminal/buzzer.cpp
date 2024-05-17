#include "includes/buzzer.hpp"

Buzzer::Buzzer(int pin) : _pin(pin) {}

void Buzzer::setup() {
    pinMode(_pin, OUTPUT);
    digitalWrite(_pin, LOW);
}

void Buzzer::playTone(int frequency, int duration) {
    tone(_pin, frequency, duration);
}

void Buzzer::stopTone() {
    noTone(_pin);
}

void Buzzer::notify(int fullness) {
    if (fullness > 80) {
      int frequencies[] = {2000, 3000, 4000, 5000};
      int duration = 100;

      for (int i = 0; i < 4; i++) { 
          playTone(frequencies[i], duration);
          delay(100); 
      }
      delay(2000);
      stopTone();
    }
}

void Buzzer::alarm() {
    playTone(1800, 1100);
    delay(1200); 

    playTone(1700, 1000);
    delay(1000); 

    //stopTone();
}