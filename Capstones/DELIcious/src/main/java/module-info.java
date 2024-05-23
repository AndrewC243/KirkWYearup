module org.example.delicious {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.delicious to javafx.fxml;
    exports org.example.delicious;
    exports org.example.delicious.enums;
}