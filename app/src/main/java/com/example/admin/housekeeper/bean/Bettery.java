package com.example.admin.housekeeper.bean;

/**
 * Created by admin on 2016/7/21.
 */
public class Bettery {
    private int currentBattery;
    private double currentBtTemperature;

    @Override
    public String toString() {
        return "Bettery{" +
                "currentBattery=" + currentBattery +
                ", currentBtTemperature=" + currentBtTemperature +
                '}';
    }

    public int getCurrentBattery() {
        return currentBattery;
    }

    public void setCurrentBattery(int currentBattery) {
        this.currentBattery = currentBattery;
    }

    public double getCurrentBtTemperature() {
        return currentBtTemperature;
    }

    public void setCurrentBtTemperature(double currentBtTemperature) {
        this.currentBtTemperature = currentBtTemperature;
    }

    public Bettery() {

    }

    public Bettery(int currentBattery, double currentBtTemperature) {

        this.currentBattery = currentBattery;
        this.currentBtTemperature = currentBtTemperature;
    }
}
