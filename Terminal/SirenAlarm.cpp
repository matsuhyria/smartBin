#ifndef SIREN_ALARM_H
#define SIREN_ALARM_H

#include "buzzer.hpp"
#include <Arduino.h>

#define FLAME_PIN D6
#define BUZZER_PIN D0

class SirenAlarm {
private:
    Buzzer buzzer;

public:
    SirenAlarm(int pin) : buzzer(BUZZER_PIN) {}

    void trigger() {
        playSirenAlarm();
    }

    void playSirenAlarm() {
        int frequencies[] = {1000, 1500};//change if it is needed
        int durations[] = {200, 200};//change if it is needed 

        for (int i = 0; i < sizeof(frequencies) / sizeof(frequencies[0]); i++) {
            buzzer.playTone(frequencies[i], durations[i]);
            delay(durations[i]);
        }
    }

    bool isFlameDetected() {
        if (digitalRead(FLAME_PIN))
            return false;
        else
            return true;
    }
};

#endif


