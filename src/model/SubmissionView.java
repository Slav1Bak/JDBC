package model;

import java.time.LocalDate;


public class SubmissionView {
   private int id_s;
   private String username;
   private String lastname;
   private String email;
   private String course_name;
   private String trainer;
   private LocalDate start_date;

    public int getId_s() {
        return id_s;
    }

    public void setId_s(int id_s) {
        this.id_s = id_s;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public SubmissionView(int id_s, String username, String lastname, String email, String course_name, String trainer, LocalDate start_date) {
        this.id_s = id_s;
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.course_name = course_name;
        this.trainer = trainer;
        this.start_date = start_date;
    }
}
