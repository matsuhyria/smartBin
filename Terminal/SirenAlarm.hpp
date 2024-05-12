#pragma once
#ifndef SIREN_ALARM_H
#define SIREN_ALARM_H
class SirenAlarm {

public:
    SirenAlarm(int pin);

    void setup();
    void trigger();

private:
    void playSirenAlarm();
};

#endif