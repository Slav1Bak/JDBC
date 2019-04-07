import configuration.DBConector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        primaryStage.setTitle("Panel logowania");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
       DBConector db = new DBConector();
       Connection conection = db.initConnection();
       db.showConnectionWarnings(conection);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
