module org.example.pointvisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens org.example.pointvisualizer to javafx.fxml;
    exports org.example.pointvisualizer;
}