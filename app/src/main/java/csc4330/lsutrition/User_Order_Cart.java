package csc4330.lsutrition;

import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by Danny on 11/26/2017.
 */

public class User_Order_Cart {

    private static User_Order_Cart activeCart;
    private ArrayList<RestaurantMenuItem> item_Cart;
    private float totalCalories;
    private User_Order_Cart(){
        item_Cart = new ArrayList<>();
        totalCalories = 0;
    }
    public static User_Order_Cart createUser_Order_Cart(){
        if (activeCart!= null){
            return activeCart;
        }else {
            activeCart = new User_Order_Cart();
            return activeCart;
        }

    }
    public void addItem(RestaurantMenuItem item){
        item_Cart.add(item);
        totalCalories+= item.getCalories();
    }
    public RestaurantMenuItem removeitem(int index){
        return item_Cart.remove(index);
    }
    public float getTotalCalories(){
        return totalCalories;
    }
    public void checkOut(){
        activeCart = null;
    }
}
