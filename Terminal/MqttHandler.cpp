#include "MqttHandler.hpp"

MqttHandler::MqttHandler(const char* ssid, const char* password, const char* ID, const char* pubTopic, const char* subTopic, const char* broker, const int port): _wifiClient(), _mqtt(_wifiClient), _ssid(ssid), _password(password), _id(ID), _pubTopic(pubTopic), _subTopic(subTopic), _broker(broker), _port(port) {};

static void callback(char* topic, byte* payload, unsigned int length){
  Serial.print("Message arrived: ");
  for (int i=0;i<length;i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();
}

void MqttHandler::reconnect() {
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print("Connecting to WiFi: ");
    Serial.println(_ssid);
    WiFi.begin(_ssid, _password);
    delay(5000);
  }
  Serial.println("Connected to WiFi");

  while(!_mqtt.connected()){
     Serial.print("Reconnecting MQTT");
    
    if (_mqtt.connect(_id)) {
    Serial.println("MQTT connection has been established");
      
    _mqtt.publish(_pubTopic, "WioTerminal-group-11");
    Serial.println("Message has been published");
      
    _mqtt.subscribe(_subTopic);
    Serial.println("Client has been subscribed");
  }
  delay(5000);
  }
}

void MqttHandler::setup(){
  _mqtt.setServer(_broker, _port);
  _mqtt.setCallback(callback);
  reconnect();
}

void MqttHandler::loop() {
  if (!_mqtt.connected()) {
    reconnect();
  }
  _mqtt.loop();
}

void MqttHandler::publish(const char* topic, const char* payload){
  _mqtt.publish(topic, payload);
}