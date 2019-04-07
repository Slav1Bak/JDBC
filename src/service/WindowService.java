package service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowService {
    public static void showWindow(String path, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(WindowService.class.getResource(path));
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void closeWindow(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}