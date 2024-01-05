package com.example.test1application;

public class FoodModel {
    public int id;
    String name;
    int calories;
    boolean goalAchieved;

    public FoodModel(String name, int calories, boolean goalAchieved) {
        this.name = name;
        this.calories = calories;
        this.goalAchieved = goalAchieved;
    }

    public FoodModel() {}

    public FoodModel(int id, String name, int calories, boolean goalAchieved) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.goalAchieved = goalAchieved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isGoalAchieved() {
        return goalAchieved;
    }

    public void setGoalAchieved(boolean goalAchieved) {
        this.goalAchieved = goalAchieved;
    }
}
