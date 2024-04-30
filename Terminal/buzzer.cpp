#include "buzzer.hpp"

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

void Buzzer::notify(int distance, int threshold) {
    if (distance < threshold) {
        playTone(2000, 500);
        delay(1000);
        stopTone();
    }
}