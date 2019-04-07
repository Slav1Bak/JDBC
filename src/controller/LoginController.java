package controller;

import configuration.DBConector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import service.AlertService;
import service.WindowService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sun.deploy.config.JREInfo.clear;

public class LoginController {

    public static int id_logged;
    @FXML
    private PasswordField pf_password;

    @FXML
    private TextField tf_login;

    @FXML
    void loginAction(ActionEvent event) throws SQLException, IOException {
        //przygotowuje zapytanie
        ps = connection.prepareStatement("select * from users where email = ? and password = ?");
//przypisuje warstosci do
        ps.setString(1, tf_login.getText());
        ps.setString(2, pf_password.getText());
        //wykonuje zapytanie
        //select- > execute()
        //insert,update,delete,drop,alter table itp -> executeUpdate()
        //wykonalem zapytanie i zwarac wynik do tablicy wielowymiarowej
        ResultSet resultSet = ps.executeQuery();
        //przesuwam wskaznik na pierwsza pozycje i sprawdzam czy jest nie null
        if (resultSet.next()) {
            //jezeli nie jest pusta to wyciaam zawartosc
            //do statycznego id przypisuje id zalogowanego uzytkownika
            id_logged = resultSet.getInt(1);
            WindowService.showWindow("/view/courseView.fxml", "Formularz zapisu na kurs");
            WindowService.closeWindow(tf_login);
            //    System.out.println(resultSet.getString(2));
            //    System.out.println(resultSet.getString(3));
            //   System.out.println(resultSet.getString(4));
            //   System.out.println(resultSet.getString(5));
            //  System.out.println(resultSet.getBoolean(6));
            //  System.out.println(resultSet.getString(7));
            //   System.out.println(resultSet.getDate(8));
        } else {
            AlertService.showAlert(Alert.AlertType.INFORMATION, "Błąd logowania", "Zarejestruj się");

        }


    }

    @FXML
    void registerAction(ActionEvent event) throws IOException {
        //utwprzeie okna nowego widoku
        //   Stage stage = new Stage();
        //   Parent root = FXMLLoader.load(getClass().getResource("/view/registerView.fxml"));
        //   stage.setTitle("Panel rejestracji");
        //    stage.setScene(new Scene(root));
        //    stage.show();
        //zamkniecie okna
        //    Stage stageClosed = (Stage) tf_login.getScene().getWindow();
        //     stageClosed.close();
        WindowService.showWindow("/view/registerView.fxml", "Panel rejestracji");
        WindowService.closeWindow(tf_login);

    }

    //globalny
    DBConector dbConector;
    PreparedStatement ps;
    Connection connection;

    public void initialize() throws SQLException {
        dbConector = new DBConector();
        connection = dbConector.initConnection();
    }

}

