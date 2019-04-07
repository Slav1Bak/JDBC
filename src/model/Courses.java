package model;

import java.time.LocalDate;

public class Courses {
    private int id_c;
    private String name;
    private String category;
    private LocalDate start_date;
    private String trainer;
    private double price;
    private int time_interval;

    public Courses(int id_c, String name, String category, LocalDate start_date, String trainer, double price, int time_interval) {
        this.id_c = id_c;
        this.name = name;
        this.category = category;
        this.start_date = start_date;
        this.trainer = trainer;
        this.price = price;
        this.time_interval = time_interval;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTime_interval() {
        return time_interval;
    }

    public void setTime_interval(int time_interval) {
        this.time_interval = time_interval;
    }

    @Override
    public String toString() {
        return "Nazwa: " +name +", kategoria: "+ category+ ", data: "+ start_date;
    }
}
