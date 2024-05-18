module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.paho.client.mqttv3;
    requires java.persistence;
    requires java.sql;
    requires org.controlsfx.controls;
    requires javafx.graphics;

    opens com.example.demo1.Core.FXMLControllers to javafx.fxml;
    exports com.example.demo1 to javafx.graphics;
}