package csc4330.lsutrition.Activities;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;

import csc4330.lsutrition.Adapters.CheckoutListAdapter;
import csc4330.lsutrition.OrderContentData.OrderContract;
import csc4330.lsutrition.R;
import csc4330.lsutrition.User_Order_Cart;

public class Finish_Order_Activity extends AppCompatActivity implements CheckoutListAdapter.deleteItemClickListener {
    User_Order_Cart cart;
    RecyclerView recyclerView;
    CheckoutListAdapter checkoutListAdapter;
    TextView calView;
    /**
     Android System Action called whenever the corresponding layout is inflated (activity launched, phone rotated, ect.)
     Actions taken are all setup required for the app interface to work
     @param savedInstanceState: contains any data relevant to the current session in the event that the view is destroyed and recreated (e.g. the phone is rotated with user data entered)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish__order_);
        ActionBar actionBar = this.getSupportActionBar();
        cart = User_Order_Cart.createUser_Order_Cart(); //Either create a new Order Cart or obtain a reference to the current one

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//enables the back button
        }
        /* Setup for the RecyclerView */
        recyclerView = (RecyclerView) findViewById(R.id.order_item_display_RV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

         checkoutListAdapter = new CheckoutListAdapter(this);
        recyclerView.setAdapter(checkoutListAdapter);

        calView = (TextView) findViewById(R.id.order_total_calories);
        calView.setText(String.valueOf(cart.getTotalCalories())); //displays the total calorie count of the users order
    }
    /**
     Handles click events on the menu options
     @param item - reference to the element selected by the user
     @return: true when menu processing is complete
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /* When the home button is pressed, take the user back to the previous activity */
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Finishes a users order, storing it and tracking data
     * @param view - reference to the view of the button that called this function (required by signature)
     */
    public void finishOrder(View view){
        if(cart.getTotalCalories() > 0)
        {
            ContentValues contentValues = new ContentValues();
            GregorianCalendar calendar = new GregorianCalendar();

            contentValues.put(OrderContract.OrderEntry.COLUMN_ORDER_CALORIES, cart.getTotalCalories());//formats data into an object that can be sent to a local database
            contentValues.put(OrderContract.OrderEntry.COLUMN_ORDER_TIME, calendar.getTime().toString());
            try {
                Uri uri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, contentValues);//inserts data into the database
            } catch (Exception e) {
                e.printStackTrace();//Exception catching here prevents app crashing and allows for debugging
            }
            cart.checkOut();//cart handles self deletion
        }
        finish();//finishes activity and returns to the previous one
    }

    /**
     * Whenever the user clicks the X box next to an item, it is deleted from their order
     * @param clickedItemIndex - the index of the viewholder clicked, corresponding to the index of the item in the cart
     * @param view - a reference to the view clicked.
     */
    @Override
    public void onDeleteItemClick(int clickedItemIndex, View view) {
        cart.removeitem(clickedItemIndex);
        checkoutListAdapter = new CheckoutListAdapter(this);
        recyclerView.setAdapter(checkoutListAdapter);
        calView.setText(String.valueOf(cart.getTotalCalories()));
    }
}
