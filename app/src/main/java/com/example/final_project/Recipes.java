package com.example.final_project;
import java.io.Serializable;

public class Recipes implements Serializable
{

    // private variables
    private String RecName;
    private String RecIngreQuant;
    private String RecSteps;
    private String RecTime;


    public String getRecName() {
        return RecName;
    }

    public void setRecName(String recName) {
        RecName = recName;
    }

    public String getRecIngreQuant() {
        return RecIngreQuant;
    }

    public void setRecIngreQuant(String recIngreQuant) {
        RecIngreQuant = recIngreQuant;
    }

    public String getRecSteps() {
        return RecSteps;
    }

    public void setRecSteps(String recSteps) {
        RecSteps = recSteps;
    }

    public String getRecTime() {
        return RecTime;
    }

    public void setRecTime(String recTime) {
        RecTime = recTime;
    }
}
