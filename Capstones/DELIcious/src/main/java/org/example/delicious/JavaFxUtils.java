package org.example.delicious;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFxUtils {
    public static Scene prepareScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(JavaFxUtils.class.getResource(fxmlFile));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
        return scene;
    }
    public static VBox centeredVbox() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}
