# SmartBin+

## Description

**SmartBin+ is an intelligent waste monitor and control system designed to optimize waste management in fast food restaurants. Reliable container fullness and humidity monitor, configurable location, prompt notification feature and fire detection capabilities: all-in-one!**

Introducing SmartBin+: Revolutionizing Waste Management in Fast Food Restaurants

In the fast-paced world of restaurant operations, efficient waste management is essential. That's where SmartBin+ comes in. It's an intelligent waste monitor and control system designed specifically for fast food establishments.

SmartBin+ combines precise sensors with a sophisticated desktop application to optimize waste management processes. With real-time data on container fullness and humidity levels, restaurant managers can make informed decisions about waste disposal. Our system offers configurable location settings and prompt notifications, ensuring proactive management and reducing the risk of oversights.

But that's not all. SmartBin+ goes beyond basic monitoring. It enhances restaurant service with display status indicators and LED lights, benefiting both staff and customers. And with built-in fire detection capabilities, safety is always a priority.

In summary, SmartBin+ transforms waste management in fast food restaurants, offering a data-driven approach that streamlines operations and ensures compliance with safety standards. Are you ready to revolutionize your restaurant's waste management?

## Link to demo video 
Here you can have a look at the full functionality of the SmartBin+ project:
[![image](Resources/youtube_butt.png)](https://youtu.be/nEBGqGX3CHQ)

## Components you need in order to use SmartBin+

1.  [Wio Seeed Terminal](https://www.seeedstudio.com/Wio-Terminal-p-4509.html)
2.  [Arduino IDE](https://www.arduino.cc/en/software)
3.  Arduino libraries:

    - [Adafruit NeoPixel](https://github.com/adafruit/Adafruit_NeoPixel), by Phil "Paint Your Dragon" Burgess for Adafruit Industries
    - [Adafruit Unified Sensor](https://github.com/adafruit/Adafruit_Sensor), by Adafruit Industries
    - [DHT sensor library](https://github.com/adafruit/DHT-sensor-library), by Adafruit Industries
    - [PubSubClient](https://pubsubclient.knolleary.net/), by Nick O'Leary
    - [Seeed_Arduino_RTC](https://github.com/Seeed-Studio/Seeed_Arduino_RTC), by Seeed Studio
    - [Seeed_Arduino_SFUD](https://github.com/Seeed-Studio/Seeed_Arduino_SFUD), by Seeed Studio
    - [Seeed_Arduino_rpcUnified](https://github.com/Seeed-Studio/Seeed_Arduino_rpcUnified), by Seeed Studio
    - [Seeed_Arduino_rpcWiFi](https://github.com/Seeed-Studio/Seeed_Arduino_rpcWiFi), by Seeed Studio
    - [Seeed_Arduino_mbedtls](https://github.com/Seeed-Studio/Seeed_Arduino_mbedtls), by Seeed Studio
    - [Ultrasonic](https://github.com/ErickSimoes/Ultrasonic), by Erick Simoes
    - [FreeRTOS](https://github.com/feilipu/Arduino_FreeRTOS_Library), by feilipu

4.  Wio Terminal Grove Sensors:

    - Grove - Ultrasonic Ranger
    - Grove - Temperature & Humidity Sensor
    - Grove - Flame Sensor
    - Grove - Speaker Actuator
    - Grove - RGB stick / Red LED light

5.  [Git](https://git-scm.com/downloads) installed in order to clone the repository

6.  [Java JDK](https://www.oracle.com/se/java/technologies/downloads/) and [JavaFX](https://openjfx.io/openjfx-docs/#install-java)

## Installation Process

### Hardware Setup

#### Arduino Wio Terminal

Ensure you have the Wio Terminal, an Arduino compatible microcontroller, ready for use.

#### Sensor Integration

Connect the sensors to the Wio Terminal following the pin-out instructions:

![image](Resources/WT-GROVE.jpeg)

![image](Resources/WioT-Pinout.jpg)

- For the Ultrasonic Ranger sensor:

  - SIG connected to 18 (which is D3) on the Wio
  - GND can be connected to any GND port, VCC connected to either 2/4 (5V)

- For the Humidity & Temperature sensor:

  - SIG connected to 16 (which is D2) on the Wio
  - GND can be connected to any GND port, VCC connected to either 2/4 (both 5V)

- For the RGB LED bar:

  - SIG connected to 5 (which is l2c1_SCL) on the Wio grove port 1
  - GND can be connected GND port in Grove port 1, VCC connected to 3v3 on Grove port 1

- For the Speaker Actuator:

  - SIG connected to 13 (which is D0) on the Wio Grove port 2
  - GND can be connected to any GND port in Grove port 2, VCC connected to 3v3 on Grove port 2

- For the Flame Sensor:

  - SIG connected to - (which is -) on the Wio
  - GND can be connected to any GND port, VCC connected to -

### Software Setup

#### Arduino IDE

1. Open the Arduino IDE and navigate to Tools > Manage Libraries...
2. Install the required libraries by searching for the library name in the search bar in 'library' tab and clicking on the Install button.
   (Note: It is recommended to download the latest version of each library, for the full library list please see [here](https://git.chalmers.se/courses/dit113/2024/group-11/smartbin#components-you-need-in-order-to-use-smartbin))

#### Code Integration & Software

1. Download our project repository using Git command `git clone` and locate the `Terminal.ino` file containing the main code for the Arduino terminal;
2. Within the repository, you'll find directories `Terminal`, `Application` and `Resources` among others, which are the directories for properly initializing our system on Arduino Wio Terminal, the SmartBin+ desktop software and the resource files needed respectively;
3. Ensure to include the Secrets.h file with your WiFi credentials;
4. Upload the Terminal.ino file to your Wio Terminal using the Arduino IDE;
5. Open you IDE installed such as (IntelliJ Idea or VS code) and clean install Maven dependencies (through Maven clean+install commands: .\mvnw clean install)

## Used Technologies

- GIT (GITLAB & GIT BASH)
- C++
- Java / JavaFX
- Google Cloud CLI
- INTELLIJ IDE
- FIGMA
- GOOGLE DOCS
- TINKERCAD
- MIRO
- DRAW.IO
- WIO SEEED TERMINAL
- ULTRASONIC SENSOR
- HUMIDITY AND TEMPERATURE SENSOR
- FLAME SENSOR
- SPEAKER ACTUATOR
- RGB LED STICK

## Use Case Diagram

![image](<Resources/UseCaseExample%20(1).png>)

## UML Class Diagram

![image](<Resources/UMLclassDiagram%20(1).jpg>)

## System Architecture

![image](Resources/updatedSysArchFinal__1_.jpg)

## Authors, acknowledgment and contributions

Software Engineering and Management / DIT113 V24 Mini Project: System Development

Developer Group 11

- Andre Ibrahim (gusibraan@student.gu.se)

Hardware: Integrated the ultrasonic and speaker sensors with the terminal and designed the physical appearance of the bin. 3D printing of the Arduino terminal case.

Software: Developed the statistics screen bar charts and helped create the UI according to the Figma designs. Enhanced the Git repository’s README and contributed diagrams. 

Project Management: Contributed to the Wiki with weekly challenges, achievements, and meeting reflections throughout the project. Created the project video and business cases.

- Maksym Matsuhyria (gusmaksyma@student.gu.se)

Hardware: ultrasonic, humidity, MQTT

Software: requirements, issues, class diagram, figma prototype, notifications, CI/CD pipeline, refactoring, bug fixing, tests

- Gulbadanbegim Muzaffarova (gusmuzgu@student.gu.se)

- Yaroslav Ursul (gusursya@student.gu.se)

More focus on hardware part: Coded the Wio Terminal component, set up humidity and flame sensors and speaker actuator. Implemented visual appearance on Wio Terminal’s screen and transitions.

Software: Created the initial JavaFX main page and enhanced it over time. Coded the map feature of the application, wrote tests for various classes, and refactored the C++ code for the Wio Terminal.

Project Management: introduced Milestones, created issues and edited Wiki and Readme

- Victoria Yurevich (gusyurevi@student.gu.se)

Hardware: Light indicationg: integration of RGB LED stick.

Software: MQTT connection, overview page, map page, statistics page, database connection.

Database: setup, creation of tables, inserting data, creating procedure for automatic update. 

Project Management: edited Wiki, prototype design, use case diagram, Gantt chart.

## License

Copyright 2024 Developer Group 11
Licensed under the Apache License, Version 2.0 (the "License");

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

See [Licence](https://git.chalmers.se/courses/dit113/2024/group-11/smartbin/-/blob/main/License.md?ref_type=heads) for full specification.
