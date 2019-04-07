package service;

import configuration.DBConector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Courses;
import model.SubmissionView;

import java.sql.*;
import java.time.LocalDate;

public class CourseService {
    private Connection connection;

    public CourseService() throws SQLException {
        DBConector db = new DBConector();
        connection = db.initConnection();
    }

    public int getCountAllcourses() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select count(*) from courses");
        if (resultSet.next()) {

            return resultSet.getInt(1);
        }
        resultSet.close();
        stmt.close();
        return 0;
    }

    public int getMyCourses(int id) throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select count(*) from submission where id_u= " + id);
        if (resultSet.next()) {

            return resultSet.getInt(1);
        }
        resultSet.close();
        stmt.close();
        return 0;
    }

    public ObservableList<Courses> getAllCourses() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from courses");
        //wprowadzanie rekordow z bazy danych do listy obietow klasy modelu- courses
        ObservableList<Courses> courses_list = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Courses c = new Courses(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getInt(7));
            courses_list.add(c);
        }
        return courses_list;

    }

    public ObservableList<SubmissionView> getAllSubmissions(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from submission_view where email = (select email from users where id_u = " + id + ")");
        //wprowadzanie rekordow z bazy danych do listy obietow klasy modelu- courses
        ObservableList<SubmissionView> submissions_list = FXCollections.observableArrayList();
        while (resultSet.next()) {
            SubmissionView sv = new SubmissionView(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDate(7).toLocalDate()
            );
            submissions_list.add(sv);
        }
        return submissions_list;

    }
    public void saveUserOnCourse(int id_u, int id_c) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("Insert into submission values (default,?,?)");
        ps.setInt(1,id_u);
        ps.setInt(2,id_c);
        ps.executeUpdate();
    }
}
