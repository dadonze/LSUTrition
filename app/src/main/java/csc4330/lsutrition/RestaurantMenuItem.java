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

    /**
     * Constructor for a menu item
     * @param nam - the name of the item
     * @param cal - the number of calories in the item
     */

    public RestaurantMenuItem(String nam, float cal){
        name = nam;
        calories = cal;
    }

    /**
     *
     * @return - the number of calories in the item
     */
    public float getCalories() {
        return calories;
    }

    /**
     *
     * @return - the name of item
     */
    public String getName() {
        return name;
    }
}
