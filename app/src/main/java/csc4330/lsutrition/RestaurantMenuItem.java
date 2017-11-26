package csc4330.lsutrition;

/**
 * Created by Danny on 11/24/2017.
 */
/*
    data type class used to model a restaurant item's nutrition
 */
public class RestaurantMenuItem {

    private String name;
    private float calories;
    //TODO fat_total,unsat_fat,sat_fat,protein,sugars;
    public RestaurantMenuItem(String nam, float cal){
        name = nam;
        calories = cal;
    }

    public float getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }
}
