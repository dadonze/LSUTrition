package csc4330.lsutrition.Activities.model;

/**
 * Created by Jay on 11/29/2017.
 */

public class MyDataModel {

    private String restaurantName;
    private String menuItem;
    private String type;
    private Integer calories;


    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getMenuItem() {
        return menuItem;
    }
    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public Integer getCalories(){
        return calories;
    }
    public void setCalories(Integer calories){
        this.calories = calories;
    }



}