package csc4330.lsutrition;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import csc4330.lsutrition.OrderContentData.OrderContract;

import static android.content.ContentValues.TAG;



/**
 * Created by Danny on 11/26/2017.
 */

public class User_Order_Cart {

    private static User_Order_Cart activeCart;
    private ArrayList<RestaurantMenuItem> item_Cart;
    private float totalCalories;

    /**
     * Constructor for the Cart, Private because we only want one instance of the cart at any given time
     */
    private User_Order_Cart(){
        item_Cart = new ArrayList<>();
        totalCalories = 0;
    }

    /**
     * creates a new cart if one does not exist, otherwise return the current one
     * @return - the user order cart
     */
    public static User_Order_Cart createUser_Order_Cart(){
        if (activeCart!= null){
            return activeCart;
        }else {
            activeCart = new User_Order_Cart();
            return activeCart;
        }

    }

    /**
     * adds an item to the User cart and updates the calorie count
     * @param item - the menuItem to be added
     */
    public void addItem(RestaurantMenuItem item){
        item_Cart.add(item);
        totalCalories+= item.getCalories();
    }

    /**
     * removes an item at the given index and updates the total calories
     * @param index - the index of the item to remove
     * @return - the item removed
     */
    public RestaurantMenuItem removeitem(int index){

        RestaurantMenuItem item = item_Cart.remove(index);
        totalCalories-= item.getCalories();
        return item;
    }

    /**
     *
     * @param index - the item index to peek at
     * @return - the item at the given index
     */

    public RestaurantMenuItem viewItem(int index){
        return item_Cart.get(index);
    }

    /**
     *
     * @return - the number of items currently in the cart
     */
    public int getSize(){
        return item_Cart.size();
    }

    /**
     *
     * @return the total calorie count in the order cart
     */
    public float getTotalCalories(){
        return totalCalories;
    }

    /**
     * handles any needed activities related to the end of the carts life
     */
    public void checkOut(){
        activeCart = null;
    }

}
