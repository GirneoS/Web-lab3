package com.ozhegov.laba3.model;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "points")
@Named("currentPoint")
@ViewScoped
public class Point implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private double x;
    @Column(nullable = false)
    private double y;
    @Column(nullable = false)
    private double r;
    @Column(nullable = false)
    private String result;
    @Column(nullable = false, name = "execution_time")
    private long executionTime;

    public Point() {}

    public boolean setX(String x) {
        this.x = Double.parseDouble(x);
        return true;
    }
    public boolean setY(String y){
        this.y = Double.parseDouble(y);
        return true;
    }
    public boolean setR(String r){
        this.r = Double.parseDouble(r);
        return true;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setR(double r){
        this.r = r;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public long getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public String getResult() {
        return result;
    }

    public long getExecutionTime() {
        return executionTime;
    }
    public void resetFields(){
        this.x = 0;
        this.y = 0;
        this.r = 0;
        this.result = "";
        this.executionTime = 0;
    }
}

