package com.example.admin.housekeeper.bean;

/**
 * Created by admin on 2016/7/20.
 */
public class PhoneInfo {
    private int map;
    private String headLine;
    private String subHand;

    public PhoneInfo() {
    }

    @Override
    public String toString() {
        return "PhoneInfo{" +
                "map=" + map +
                ", headLine='" + headLine + '\'' +
                ", subHand='" + subHand + '\'' +
                '}';
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getSubHand() {
        return subHand;
    }

    public void setSubHand(String subHand) {
        this.subHand = subHand;
    }

    public PhoneInfo(int map, String headLine, String subHand) {

        this.map = map;
        this.headLine = headLine;
        this.subHand = subHand;
    }
}
