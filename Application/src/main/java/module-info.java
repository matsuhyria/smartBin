module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.paho.client.mqttv3;
    requires java.persistence;
    requires java.sql;
    requires org.controlsfx.controls;
    requires javafx.graphics;

    opens com.example.demo1;
    opens com.example.demo1.Config;
    opens com.example.demo1.Core;
    opens com.example.demo1.UI;
    exports com.example.demo1 to javafx.graphics;
}