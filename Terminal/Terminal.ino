#include "humidity.hpp"
#include "ultrasonic.hpp"
#include "led_indicator.hpp"
#include "MqttHandler.hpp"
#include "buzzer.hpp"
#include "secrets_template.hpp"
#include "ui.hpp"
#include <FreeRTOS.h>
#include "flame_detector.hpp"

#define FLAME_PIN D6 
#define BUZZER_PIN D0
//Humidity
#define DHT_PIN D2
#define DHT_TYPE DHT11
//Ultrasonic
#define ULS_PIN D3
//LED strip
#define NEOPIXEL_PIN PIN_WIRE_SCL //using I2C as a digital port
#define NEOPIXEL_TYPE NEO_GRB + NEO_KHZ800
const int PIXELS = 10;
const int TURN_ON_DISTANCE_CM = 18;
//wifi
const char* ssid = WIFI_SSID;
const char* password = WIFI_PASSWORD;
//mqtt
const char* ID = "Wio-Terminal-group11"; 
const char* pubTopic1 = "Sensors/Humidity";
const char* pubTopic2 = "Sensors/Ultrasonic";
const char* pubTopic3 = "Sensors/Flame";
const char* subTopic = "WioTerminal";
const char* broker = "test.mosquitto.org";
const int port = 1883;

TFT_eSPI tft;

UserInterface ui(tft);
Buzzer buzzer (BUZZER_PIN);
Humidity humidSensor(DHT_PIN, DHT_TYPE);
UltrasonicRanger ulsSensor(ULS_PIN);
LedIndicator led(PIXELS, NEOPIXEL_PIN, NEOPIXEL_TYPE, TURN_ON_DISTANCE_CM);
MqttHandler mqttHandler(ssid, password, ID, pubTopic1, subTopic, broker, port);
FlameDetector flameDetector(FLAME_PIN);

// Task handles
TaskHandle_t showConnectionLoopTaskHandle = NULL;
bool shouldRunConnectionLoop = false;

void showConnectionLoopTask(void* pvParameters) {
    while (shouldRunConnectionLoop) {
        ui.showConnectionLoop();
    }
    vTaskDelete(NULL);
}

void setup() {
    pinMode(WIO_5S_PRESS, INPUT_PULLUP);
    ui.setupWelcomeScreen();

    while (true) {
        if (digitalRead(WIO_5S_PRESS) == LOW) {
          flameDetector.setup();
          humidSensor.setup();
          led.setup();
          buzzer.setup();
          ui.showConnectionTitle();

          shouldRunConnectionLoop = true;
          xTaskCreate(showConnectionLoopTask, "ShowConnectionLoop", 128, NULL, 1, &showConnectionLoopTaskHandle);
          mqttHandler.setup();
          shouldRunConnectionLoop = false;

          break;
        }
    }

    delay(3000);
    ui.showHeader();
    ui.distanceHeader();
    ui.humidityHeader();
}

void loop(){
  mqttHandler.loop();
  float humidity = humidSensor.read();
  Serial.println(humidity);
  int distance = ulsSensor.measureDistance();
  Serial.println(distance);
  led.turnOn(distance);
  
  std::string humidityStr = std::to_string(humidity);
  const char* humidityPayload = humidityStr.c_str();
  mqttHandler.publish(pubTopic1, humidityPayload);

  int fullness = ulsSensor.calculateFullness();
  std::string ultrasonicStr = std::to_string(fullness);
  const char* ultrasonicPayload = ultrasonicStr.c_str();
  mqttHandler.publish(pubTopic2, ultrasonicPayload);
  
  ui.updateHumidity(humidity);
  ui.updateDistance(fullness);
  buzzer.notify(fullness);

  if(flameDetector.detect()){
    const char* flamePayload = "112";
    mqttHandler.publish(pubTopic3, flamePayload);
    buzzer.alarm();
  }

  delay(1500);
}