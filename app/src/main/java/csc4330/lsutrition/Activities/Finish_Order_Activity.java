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
import android.widget.TextView;

import csc4330.lsutrition.Adapters.CheckoutListAdapter;
import csc4330.lsutrition.OrderContentData.OrderContract;
import csc4330.lsutrition.R;
import csc4330.lsutrition.User_Order_Cart;

public class Finish_Order_Activity extends AppCompatActivity {
    User_Order_Cart cart;
    RecyclerView recyclerView;
    CheckoutListAdapter checkoutListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish__order_);
        ActionBar actionBar = this.getSupportActionBar();
        cart = User_Order_Cart.createUser_Order_Cart();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = (RecyclerView) findViewById(R.id.order_item_display_RV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

         checkoutListAdapter = new CheckoutListAdapter();
        recyclerView.setAdapter(checkoutListAdapter);

        TextView textView = (TextView) findViewById(R.id.order_total_calories);
        textView.setText(String.valueOf(cart.getTotalCalories()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // When the home button is pressed, take the user back to the previous activity
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void finishOrder(View view){
        cart.checkOut();
        ContentValues contentValues = new ContentValues();

        contentValues.put(OrderContract.OrderEntry.COLUMN_ORDER_CALORIES, cart.getTotalCalories());
        try {
            Uri uri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, contentValues);
        } catch (Exception e){
            e.printStackTrace();
        }
        //Log.d("URI", "finishOrder: " +uri.toString());
        finish();
    }
    public void deleteItem(View view){

    }
}
