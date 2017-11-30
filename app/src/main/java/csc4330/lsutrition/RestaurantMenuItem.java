package csc4330.lsutrition;

/**
 * Created by Danny on 11/24/2017.
 */
/*
    data type class used to model a restaurant item's nutrition
 */
public class RestaurantMenuItem {

    private String name;
    private String restaurant;
    private int calories;
    //TODO fat_total,unsat_fat,sat_fat,protein,sugars;

    /**
     * Constructor for a menu item
     * @param nam - the name of the item
     * @param cal - the number of calories in the item
     */

    public RestaurantMenuItem(String nam, String res, int cal){
        name = nam;
        restaurant = res;
        calories = cal;
    }

    /**
     *
     * @return - the number of calories in the item
     */
    public int getCalories() {
        return calories;
    }

    /**
     *
     * @return - the name of item
     */
    public String getName() {
        return name;
    }

    public String getRestaurant(){return restaurant;}
}
