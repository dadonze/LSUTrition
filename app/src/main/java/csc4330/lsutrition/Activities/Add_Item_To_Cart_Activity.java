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
        private Float itemCalories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item__to__cart_);
        ActionBar actionBar = this.getSupportActionBar();
        Intent start = getIntent();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        calorieView = findViewById(R.id.item_view_calories);
        TextView itemNameView = findViewById(R.id.item_view_name);
        itemName = start.getStringExtra("Item Name");
        itemCalories = start.getFloatExtra("CaloriesF",300);

        itemNameView.setText(itemName);
        calorieView.setText(start.getStringExtra("CaloriesS"));

    }

    public void addToCart(View view){
        User_Order_Cart cart = User_Order_Cart.createUser_Order_Cart();
        cart.addItem(new RestaurantMenuItem(itemName,itemCalories));
        String toastMessage = itemName + " Added to your Order";
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
        finish();

    }
    public void addAndFinish(View view){
        User_Order_Cart cart = User_Order_Cart.createUser_Order_Cart();
        cart.addItem(new RestaurantMenuItem(itemName,itemCalories));
        Intent intent = new Intent(this,Finish_Order_Activity.class);
        startActivity(intent);
        finish();
    }

}
