module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.paho.client.mqttv3;
    requires javafx.graphics;
    requires org.controlsfx.controls;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}