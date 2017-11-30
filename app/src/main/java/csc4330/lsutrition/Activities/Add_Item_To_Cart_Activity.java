package csc4330.lsutrition.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import csc4330.lsutrition.R;
import csc4330.lsutrition.RestaurantMenuItem;
import csc4330.lsutrition.User_Order_Cart;

public class Add_Item_To_Cart_Activity extends AppCompatActivity {
        TextView calorieView;
        private String itemName;
        private int itemCalories;
    /**
     Android System Action called whenever the corresponding layout is inflated (activity launched, phone rotated, ect.)
     Actions taken are all setup required for the app interface to work
     @param savedInstanceState: contains any data relevant to the current session in the event that the view is destroyed and recreated (e.g. the phone is rotated with user data entered)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item__to__cart_);
        ActionBar actionBar = this.getSupportActionBar(); //reference to the action bar in order to enable some default menu options
        Intent start = getIntent(); //this reference lets us obtain the extras from the activity that started this one
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // enables the back button
        }
        calorieView = findViewById(R.id.item_view_calories);
        TextView itemNameView = findViewById(R.id.item_view_name);
        itemName = start.getStringExtra("Item Name");//obtains the menu item's name
        itemCalories = start.getIntExtra("CaloriesF",300);//obtains the float representation of the calories, 300 is an arbitrary default value(required by signature)

        itemNameView.setText(itemName);
        calorieView.setText(start.getStringExtra("CaloriesS"));

    }


    /**
     * This function is called when the corresponding "Add to Order" button is clicked
     * This function adds an item to the global User Order Cart
     * @param view - reference to the view of the button(required by signature)
     */
    public void addToCart(View view){
        User_Order_Cart cart = User_Order_Cart.createUser_Order_Cart();//Either create a new Order Cart or obtain a reference to the current one
        cart.addItem(new RestaurantMenuItem(itemName,"",itemCalories));
        String toastMessage = itemName + " Added to your Order";
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();//Displays a toast to the user confirming the item was added
        finish();//ends the activity and returns to the previous one

    }
    public void addAndFinish(View view){
        User_Order_Cart cart = User_Order_Cart.createUser_Order_Cart();//Either create a new Order Cart or obtain a reference to the current one
        cart.addItem(new RestaurantMenuItem(itemName,"",itemCalories));
        Intent intent = new Intent(this,Finish_Order_Activity.class);
        startActivity(intent);
        finish();//ends the activity and returns to the previous one(After the activity is returned to)
    }

}
