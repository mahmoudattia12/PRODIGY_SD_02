module com.example.guessinggame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.guessinggame to javafx.fxml;
    exports com.example.guessinggame;
}