#include "includes/humidity.hpp"
#include "includes/ultrasonic.hpp"
#include "includes/led_indicator.hpp"
#include "includes/MqttHandler.hpp"
#include "includes/buzzer.hpp"
#include "includes/secrets_template.hpp"
#include "includes/ui.hpp"
#include <FreeRTOS.h>
#include "includes/flame_detector.hpp"

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

//WiFi credentials
const char* ssid = WIFI_SSID;
const char* password = WIFI_PASSWORD;

//MQTT configuration
const char* ID = "Wio-Terminal-group11"; 
const char* PUB_TOPIC_HUMIDITY = "Sensors/Humidity";
const char* PUB_TOPIC_ULTRASONIC = "Sensors/Ultrasonic";
const char* PUB_TOPIC_FLAME = "Sensors/Flame";
const char* subTopic = "WioTerminal";
const char* broker = "test.mosquitto.org";
const int port = 1883;

// Instantiate display and sensor/actuator objects
TFT_eSPI tft;
UserInterface ui(tft);
Buzzer buzzer (BUZZER_PIN);
Humidity humidSensor(DHT_PIN, DHT_TYPE);
UltrasonicRanger ulsSensor(ULS_PIN, TURN_ON_DISTANCE_CM);
LedIndicator led(PIXELS, NEOPIXEL_PIN, NEOPIXEL_TYPE, TURN_ON_DISTANCE_CM);
MqttHandler mqttHandler(ssid, password, ID, PUB_TOPIC_HUMIDITY, subTopic, broker, port);
FlameDetector flameDetector(FLAME_PIN);

// FreeRTOS task handle and flag for managing connection animalion
TaskHandle_t showConnectionLoopTaskHandle = NULL;
bool shouldRunConnectionLoop = false;

// Task to display connection animation on the UI
void showConnectionLoopTask(void* pvParameters) {
    while (shouldRunConnectionLoop) {
        ui.showConnectionLoop();
    }
    vTaskDelete(NULL);
}

void setupSensors() {
  flameDetector.setup();
  humidSensor.setup();
  led.setup();
  buzzer.setup();
}

void setup() {
    pinMode(WIO_5S_PRESS, INPUT_PULLUP); // Set up button
    ui.setupWelcomeScreen();

    // Wait for button press to initialize sensors and connect to MQTT
    while (true) {
        if (digitalRead(WIO_5S_PRESS) == LOW) {
          setupSensors();
          ui.showConnectionTitle();

          shouldRunConnectionLoop = true; // Start connection animation task
          xTaskCreate(showConnectionLoopTask, "ShowConnectionLoop", 128, NULL, 1, &showConnectionLoopTaskHandle);
          mqttHandler.setup();
          shouldRunConnectionLoop = false; // Start connection animation task

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
  mqttHandler.publish(PUB_TOPIC_HUMIDITY, humidityPayload);

  int fullness = ulsSensor.calculateFullness();
  std::string ultrasonicStr = std::to_string(fullness);
  const char* ultrasonicPayload = ultrasonicStr.c_str();
  mqttHandler.publish(PUB_TOPIC_ULTRASONIC, ultrasonicPayload);
  
  ui.updateHumidity(humidity);
  ui.updateDistance(fullness);
  buzzer.notify(fullness);

  if(flameDetector.detect()){
    const char* flamePayload = "112";
    mqttHandler.publish(PUB_TOPIC_FLAME, flamePayload);
    buzzer.alarm();
  }

  delay(1500);
}