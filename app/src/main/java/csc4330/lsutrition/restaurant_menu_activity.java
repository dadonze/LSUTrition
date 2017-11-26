package csc4330.lsutrition;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class restaurant_menu_activity extends AppCompatActivity implements RestaurantMenuAdapter.RestaurantMenuItemClickListener {

    RestaurantMenuAdapter restaurantMenuAdapter;
    RecyclerView recyclerView;
    String restaurantName;
    /*
        Android System Action called whenever the corresponding layout is inflated (activity launched, phone rotated, ect.)
        Actions taken are all setup required for the app interface to work
        @param savedInstanceState: contains any data relevant to the current session in the event that the view is destroyed and recreated (e.g. the phone is rotated with user data entered)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu_activity);
        Intent start = getIntent();
        recyclerView = (RecyclerView) findViewById(R.id.menu_item_list_RV);
        restaurantName = start.getStringExtra("Restaurant Name");
        setTitle(restaurantName);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        restaurantMenuAdapter = new RestaurantMenuAdapter(FakeDataUtils.generateFakeMenu(),this);
        recyclerView.setAdapter(restaurantMenuAdapter);

    }
    /*
        Handles click events on the menu options
        @param item - reference to the element clicked by the user
        @return: true when menu processing is complete
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // When the home button is pressed, take the user back to the VisualizerActivity
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
    /*
        Handles click events for the list items
        @param clickedItemIndex : index of the item within the list
        @param view : reference to the layout of the list item
     */
    @Override
    public void onRestaurantItemClick(int clickedItemIndex, View view) {
        TextView textView= (TextView) view.findViewById(R.id.tv_selected_restaurant_menu_item_name_RV);
        String toastMessage = "Clicked " + textView.getText().toString();
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();

    }
}
