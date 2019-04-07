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
import java.sql.SQLException;
import java.util.InputMismatchException;

public class RegisterController {

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_lastname;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField pf_password;

    @FXML
    private PasswordField pf_password2;

    private void clear() {
        tf_name.clear();
        tf_lastname.clear();
        tf_email.clear();
        pf_password.clear();
        pf_password2.clear();
    }

    @FXML
    void clearAction(ActionEvent event) {
        clear();
    }

    @FXML
    void keyRegisterAction(KeyEvent event) {
        //dla esc clear a dla enter insert
        if (event.getCode() == KeyCode.ENTER) {
            insertData();
        } else if (event.getCode() == KeyCode.ESCAPE) {
            clear();
        }

    }

    //zapytanie w osobnej metodzie
    private void insertData() {
        //rejestracja uzytkownika na pdst danych pol
        try {
            if (tf_email.getText().equals("") || tf_name.getText().equals("") || tf_lastname.getText().equals("") || pf_password.getText().equals("")) {
                throw new NullPointerException();
            }
            //sprawdzam czy hasla sa jednakowe
            if (!pf_password.getText().equals(pf_password2.getText())) {
                throw new InputMismatchException();
            }
            ps = connection.prepareStatement("insert into users values (default, ?,?,?,?, default, default, default)");
            ps.setString(1, tf_name.getText());
            ps.setString(2, tf_lastname.getText());
            ps.setString(3, tf_email.getText());
            ps.setString(4, pf_password.getText());
            ps.executeUpdate();
            AlertService.showAlert(Alert.AlertType.INFORMATION,"zarejestrowano uzytkownika","Zarejestrowano " + tf_email);
         //   Alert a = new Alert(Alert.AlertType.INFORMATION);
         //   a.setTitle("Rejestracja");
          //  a.setHeaderText("Zarejestrowano uzytkownika ");
          //  a.setContentText("Zarejestrowano uzytkownika: " + tf_email.getText());
         //   a.show();
            //czyszczenie pol
            clear();
            //zamkniecie okna i przejscie do logowania

          //  Stage stage = new Stage();
          //  Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
          //  stage.setTitle("Panel logowania");
          //  stage.setScene(new Scene(root));
          //  stage.show();
            //zamkniecie okna
          //  Stage stageClosed = (Stage) tf_email.getScene().getWindow();
           // stageClosed.close();
            WindowService.showWindow("/view/loginView.fxml","Panel logowania");
            WindowService.closeWindow(tf_email);

        } catch (SQLException e) {
            AlertService.showAlert(Alert.AlertType.INFORMATION, "Błędny login", "Login juz istnieje");

            //   Alert a = new Alert(Alert.AlertType.ERROR);a.setTitle("Błąd");
            //  a.setHeaderText("Musisz uzupelnic wszystkie pola ");
            //  a.setContentText("Wypełnij pola");
            //  a.show();
        }
        catch (NullPointerException e){
            AlertService.showAlert(Alert.AlertType.INFORMATION,"Musisz uzupełnic wszystkie dane","uzupełnij puste pola");

        } catch (InputMismatchException e) {
        //    Alert a = new Alert(Alert.Aler
            // tType.ERROR);
        //    a.setTitle("Błąd");
        //    a.setHeaderText("Podane hasła nie sa jednakowe ");
      //      a.setContentText("Musisz podac takie samo hasło");
      //      a.show();
            AlertService.showAlert(Alert.AlertType.INFORMATION,"Podane hasla nie sa jednakowe","wprawdz takie samo haslo");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void regiserAction(ActionEvent event) throws IOException {

        insertData();

    }  //globalny

    DBConector dbConector;
    PreparedStatement ps;
    Connection connection;

    public void initialize() throws SQLException {
        dbConector = new DBConector();
        connection = dbConector.initConnection();
    }
}

