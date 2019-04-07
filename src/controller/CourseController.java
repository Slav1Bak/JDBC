package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Courses;
import model.SubmissionView;
import service.CourseService;
import service.WindowService;

public class CourseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem m_logout;

    @FXML
    private MenuItem m_exit;

    @FXML
    private TableView<SubmissionView> table;

    @FXML
    private TableColumn<SubmissionView, String> tab_name;

    @FXML
    private TableColumn<SubmissionView, String> tab_lastname;

    @FXML
    private TableColumn<SubmissionView, String> tab_email;

    @FXML
    private TableColumn<SubmissionView, String> tab_course;

    @FXML
    private TableColumn<SubmissionView, String> tab_trainer;

    @FXML
    private TableColumn<SubmissionView, LocalDate> tab_date;

    @FXML
    private ComboBox<Courses> cb_save;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_delete;

    @FXML
    private ComboBox<Courses> cb_update;

    @FXML
    private Button btn_update;

    @FXML
    private Label lbl_course_count;

    @FXML
    private Label lbl_subbmision_count;


    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void logoutAction(ActionEvent event) throws IOException {
        WindowService.showWindow("/view/loginView.fxml", "Panel logowania");
        WindowService.closeWindow(lbl_course_count);
    }

    @FXML
    void saveAction(ActionEvent event) throws SQLException {
        //pobranei wartosci zaznaczonej z listy
        Courses courses = cb_save.getValue();
//wydobywanie id c kursu
        int id_c = courses.getId_c();
        //serwis do zapisu uzytkownika na kurs
        new CourseService().saveUserOnCourse(LoginController.id_logged, id_c);
        initialize();
    }

    @FXML
    void deleteAction(ActionEvent event) {
        int id_selected = table.getSelectionModel().getSelectedItem().getId_s();
        //serwis do usuwania zapisu kursu na pdst id
    }

        @FXML
        void selectRowAction (MouseEvent event){
            btn_update.setDisable(false);
            btn_delete.setDisable(false);
        }

        @FXML
        void updateAction (ActionEvent event){
            int id_selected = table.getSelectionModel().getSelectedItem().getId_s();
//serwis do zmiany zapisu uzytkownika id na wybrany kurs

        }

        @FXML
        void initialize () throws SQLException {
            //najpierw zaznacz potem usun
            btn_delete.setDisable(true);
            btn_update.setDisable(true);
            assert m_logout != null : "fx:id=\"m_logout\" was not injected: check your FXML file 'courseView.fxml'.";
            assert m_exit != null : "fx:id=\"m_exit\" was not injected: check your FXML file 'courseView.fxml'.";
            assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'courseView.fxml'.";
            assert tab_name != null : "fx:id=\"tab_name\" was not injected: check your FXML file 'courseView.fxml'.";
            assert tab_lastname != null : "fx:id=\"tab_lastname\" was not injected: check your FXML file 'courseView.fxml'.";
            assert tab_email != null : "fx:id=\"tab_email\" was not injected: check your FXML file 'courseView.fxml'.";
            assert tab_course != null : "fx:id=\"tab_course\" was not injected: check your FXML file 'courseView.fxml'.";
            assert tab_trainer != null : "fx:id=\"tab_trainer\" was not injected: check your FXML file 'courseView.fxml'.";
            assert tab_date != null : "fx:id=\"tab_date\" was not injected: check your FXML file 'courseView.fxml'.";
            assert cb_save != null : "fx:id=\"cb_save\" was not injected: check your FXML file 'courseView.fxml'.";
            assert btn_save != null : "fx:id=\"btn_save\" was not injected: check your FXML file 'courseView.fxml'.";
            assert btn_delete != null : "fx:id=\"btn_delete\" was not injected: check your FXML file 'courseView.fxml'.";
            assert cb_update != null : "fx:id=\"cb_update\" was not injected: check your FXML file 'courseView.fxml'.";
            assert btn_update != null : "fx:id=\"btn_update\" was not injected: check your FXML file 'courseView.fxml'.";
            assert lbl_course_count != null : "fx:id=\"lbl_course_count\" was not injected: check your FXML file 'courseView.fxml'.";
            assert lbl_subbmision_count != null : "fx:id=\"lbl_subbmision_count\" was not injected: check your FXML file 'courseView.fxml'.";

            CourseService courseService = new CourseService();
            System.out.println(LoginController.id_logged);
            //wykonanie zapytania ile jest dostepnych kursow
            //zwrac liczbe kursow
            int allCoursesCount = courseService.getCountAllcourses();
            //zlacza napis z liczba kursow
            String allCoursesCountLable = "  liczba dostępnych kursów: " + allCoursesCount;
            //umieszcza napis na kontrolce
            lbl_course_count.setText(allCoursesCountLable);

            //wykonanie zapytania na ile kursow jest zapisany
            int myCoursesCount = courseService.getMyCourses(LoginController.id_logged);
            String myCoursesCountLabel = "  Twoje kursy: " + myCoursesCount;
            lbl_subbmision_count.setText(myCoursesCountLabel);
            //wypisanie kursow z db do kontroli cboxa
            cb_save.setItems(courseService.getAllCourses());
            cb_update.setItems(courseService.getAllCourses());
            //wypisanie rekordow z widoku modelu
            ObservableList<SubmissionView> submissions_list = courseService.getAllSubmissions(LoginController.id_logged);
            //konfiguracja waartosci przekazywanych do tabeli
            tab_name.setCellValueFactory(new PropertyValueFactory<>("username"));
            tab_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            tab_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            tab_course.setCellValueFactory(new PropertyValueFactory<>("course_name"));
            tab_trainer.setCellValueFactory(new PropertyValueFactory<>("trainer"));
            tab_date.setCellValueFactory(new PropertyValueFactory<>("start_date"));
            //wprowadzanie wartosci z listy
            table.setItems(submissions_list);
        }
    }
