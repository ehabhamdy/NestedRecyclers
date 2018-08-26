package com.ehab.listdemo.model;

public class Subcategory {
    String name;
    boolean isSelected;

    public Subcategory() {
    }

    public Subcategory(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
