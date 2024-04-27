# SmartBin+

# Description

**SmartBin+ is an intelligent waste monitor and control system designed to optimize waste management in fast food restaurants. Reliable container fullness and humidity monitor, configurable location, prompt notification feature and fire detection capabilities: all-in-one!**

Are you prepared to transform the way your restaurant handles waste? With nothing less than a set of precise sensors and a high quality desktop application, you will gain insights into your disposal stations' organization, keeping you informed about all container statuses. Within the application, you will not only have access to the current level of fullness of the bins, but also the humidity degrees which allow you to judge when the garbage needs to be removed. Of course, the bin system could not be classified as a SmartBin+ unless we carefully implement a notification feature that will help to rationalize waste management. Furthermore, our system enhances the quality of restaurant service by utilizing display status indicators and LED lights, benefiting both garbage collectors and restaurant clients. Last but not least, the system will help to ensure fire safety by alarming you in case of a presence of flame in the container!

# Components you need in order to use SmartBin+:


1.  [Wio Seeed Terminal](https://www.seeedstudio.com/Wio-Terminal-p-4509.html)
2. [Arduino IDE](https://www.arduino.cc/en/software)
3. Arduino libraries: 
   - [Adafruit NeoPixel](https://github.com/adafruit/Adafruit_NeoPixel), by Phil "Paint Your Dragon" Burgess for Adafruit Industries
   - [Adafruit Unified Sensor](https://github.com/adafruit/Adafruit_Sensor), by Adafruit Industries
   - [DHT sensor library](https://github.com/adafruit/DHT-sensor-library), by Adafruit Industries
   - [PubSubClient](https://pubsubclient.knolleary.net/), by Nick O'Leary
   - [Seeed_Arduino_FS](https://github.com/Seeed-Studio/Seeed_Arduino_FS), by Seeed Studio 
   -[Seeed_Arduino_RTC](https://github.com/Seeed-Studio/Seeed_Arduino_RTC), by Seeed Studio
   - [Seeed_Arduino_SFUD](https://github.com/Seeed-Studio/Seeed_Arduino_SFUD), by Seeed Studio
   - [Seeed_Arduino_rpcUnified](https://github.com/Seeed-Studio/Seeed_Arduino_rpcUnified), by Seeed Studio
   - [Seeed_Arduino_rpcWiFi](https://github.com/Seeed-Studio/Seeed_Arduino_rpcWiFi), by Seeed Studio
   - [Seeed_Arduino_mbedtls](https://github.com/Seeed-Studio/Seeed_Arduino_mbedtls), by Seeed Studio
   - [Ultrasonic](https://github.com/ErickSimoes/Ultrasonic), by Erick Simoes


4. Wio Terminal Grove Sensors:
   - Grove - Ultrasonic Ranger
   - Grove - Temperature & Humidity Sensor
   - Grove - Flame Sensor
   - Grove - Speaker Actuator
   - Grove - RGB stick / Red LED light

5. [Git](https://git-scm.com/downloads) installed in order to clone the repository 

6. [Java JDK](https://www.oracle.com/se/java/technologies/downloads/) and [JavaFX](https://openjfx.io/openjfx-docs/#install-java)

# Installation Process

## Wio Terminal

Open the Arduino IDE and navigate to Tools > Manage Libraries...
Install the required libraries by searching for the library name in the search bar and clicking on the Install button:
(Library names list)
It is recommended to download the latest version of the library.

# Use cases

Sample use cases will be documented here

# Authors and acknowledgment

Software Engineering and Management / DIT113 V24 Mini Project: System Development

Developer Group 11

Andre Ibrahim (gusibraan@student.gu.se)
-
Maksym Matsuhyria (gusmaksyma@student.gu.se)
-
Gulbadanbegim Muzaffarova (gusmuzgu@student.gu.se)
-
Yaroslav Ursul (gusursya@student.gu.se)
-
Victoria Yurevich (gusyurevi@student.gu.se)
-

# License
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.