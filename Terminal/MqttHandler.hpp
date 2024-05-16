#ifndef mqtt_h
#define mqtt_h
#include "rpcWiFi.h"
#include <PubSubClient.h>
#include <string>

class MqttHandler{
  
  public:
  MqttHandler(const char* ssid, const char* password, const char* ID, const char* pubTopic, const char* subTopic, const char* broker, const int port);
  void setup();
  void publish(const char* topic, const char* payload);
  void reconnect();
  void loop();

  private:
  WiFiClient _wifiClient;
  PubSubClient _mqtt;
  const char* _ssid; 
  const char* _password; 
  const char* _id; 
  const char* _pubTopic;
  const char* _subTopic;
  const char* _broker;
  const int _port;
};


#endif