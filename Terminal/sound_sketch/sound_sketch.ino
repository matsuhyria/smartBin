/*macro definition of Speaker pin*/
#define SPEAKER 1

int BassTab=1136; //one bass note for fullness


void setup()
{
    pinInit();
}
void loop()
{
        fullBin();
        delay(2000);
}
void pinInit()
{
    pinMode(SPEAKER,OUTPUT);
    digitalWrite(SPEAKER,LOW);
}
void fullBin()
{
    for(int i=0;i<100;i++)
    {
        digitalWrite(SPEAKER,HIGH);
        delayMicroseconds(BassTab);
        digitalWrite(SPEAKER,LOW);
        delayMicroseconds(BassTab);
    }
}