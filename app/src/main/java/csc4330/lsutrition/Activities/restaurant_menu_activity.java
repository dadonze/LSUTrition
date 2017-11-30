package csc4330.lsutrition.Activities;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import csc4330.lsutrition.Adapters.RestaurantMenuAdapter;
import csc4330.lsutrition.FakeDataUtils;
import csc4330.lsutrition.R;

public class restaurant_menu_activity extends AppCompatActivity implements RestaurantMenuAdapter.RestaurantMenuItemClickListener {

    RestaurantMenuAdapter restaurantMenuAdapter;
    RecyclerView recyclerView;
    static String restaurantName;
    /**
        Android System Action called whenever the corresponding layout is inflated (activity launched, phone rotated, ect.)
        Actions taken are all setup required for the app interface to work
        @param savedInstanceState: contains any data relevant to the current session in the event that the view is destroyed and recreated (e.g. the phone is rotated with user data entered)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu_activity);

        Intent start = getIntent();//obtains a reference to the intent that started this activity
        if(start.hasExtra("Restaurant Name")) {
            restaurantName = start.getStringExtra("Restaurant Name");
        }
        setTitle(restaurantName);
        /* Set up for the Recycler View */
        recyclerView = (RecyclerView) findViewById(R.id.menu_item_list_RV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//enables the back button
        }
        restaurantMenuAdapter = new RestaurantMenuAdapter(FakeDataUtils.generateFakeMenu(restaurantName),this);
        recyclerView.setAdapter(restaurantMenuAdapter);

    }

    /**
        Handles the creation of a toolbar menu and displays it to the user
        @param menu : the predefined menu to be created
        @return : true if there is a menu made, false unless overriden
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.restaurant_menu_activity_menu, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }
    /**
        Handles click events on the menu options
        @param item - reference to the element clicked by the user
        @return: true when menu processing is complete
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /* When the home button is pressed, take the user back to the VisualizerActivity*/
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this); //returns to the previous activity
        }else
            if (id == R.id.action_finish_order){
            Intent intent = new Intent(this,Finish_Order_Activity.class); //sends the user to the finish order activity
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    /**
        Handles click events for the list items
        @param clickedItemIndex : index of the item within the list
        @param view : reference to the layout of the list item
     */
    @Override
    public void onRestaurantItemClick(int clickedItemIndex, View view) {
        /* Initialize Variables */
        TextView itemNameView= (TextView) view.findViewById(R.id.tv_selected_restaurant_menu_item_name_RV);
        TextView calorieView = (TextView) view.findViewById(R.id.tv_selected_restaurant_menu_item_calories_RV);
        String itemSelectedName = itemNameView.getText().toString();

        String toastMessage = "Clicked " + itemSelectedName;
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,Add_Item_To_Cart_Activity.class); //makes a new intent to start the add to cart activity
        float calories = Float.valueOf(calorieView.getText().toString());

        intent.putExtra("Item Name",itemSelectedName); //Allows us to obtain which Item was clicked in the next activity
        intent.putExtra("CaloriesS", calorieView.getText().toString());//Allows us to obtain the calories (as a string) of the item that was selected in the next activity
        intent.putExtra("CaloriesF",calories);//Allows us to obtain the calories (as a float) of the item that was selected in the next activity
        startActivity(intent);

    }
}
