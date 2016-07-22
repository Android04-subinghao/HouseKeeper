package com.example.admin.housekeeper.bean;

/**
 * Created by admin on 2016/7/18.
 */
public class Function {
    private String name;
    private int icon;

    public Function(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public Function() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                '}';
    }
}
