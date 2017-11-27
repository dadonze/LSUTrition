package csc4330.lsutrition;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
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
    public RestaurantMenuItem viewItem(int index){
        return item_Cart.get(index);
    }
    public int getSize(){
        return item_Cart.size();
    }
    public float getTotalCalories(){
        return totalCalories;
    }
    public void checkOut(){



        activeCart = null;
    }

}
