package com.example.workkeeper;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Job implements Serializable {
    private String companyName;
    private double salary;
    private double hours;
    private double moneyTotal;
    private String date;

    public Job(String id,String companyName, double salary, double hours) {
        this.companyName = companyName;
        this.salary = salary;
        this.hours = hours;
        this.moneyTotal = salary*hours;
        DateFormat df = new SimpleDateFormat("dd MM yyyy");
        String now = df.format(Calendar.getInstance().getTime());
        this.date=now;
    }

    public Job() {

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(double moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public String getDate() {
        return date;
    }
}
