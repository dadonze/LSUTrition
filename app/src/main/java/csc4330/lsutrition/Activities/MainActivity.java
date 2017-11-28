package csc4330.lsutrition.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import csc4330.lsutrition.Adapters.RestaurantNameAdapter;
import csc4330.lsutrition.FakeDataUtils;
import csc4330.lsutrition.R;

public class MainActivity extends AppCompatActivity implements RestaurantNameAdapter.RestaurantNameClickListener {
    private RestaurantNameAdapter restaurantNameAdapter;
    private RecyclerView restaurantRecyclerView;
    private  GoogleSignInClient mGoogleSignInClient;
    private FloatingActionButton googleSignInActionButton;
    public static GoogleSignInAccount account;

    /**
        Android System Action called whenever the corresponding layout is inflated (app launched, phone rotated, ect.)
        Actions taken are all setup required for the app interface to work
        @param savedInstanceState: contains any data relevant to the current session in the event that the view is destroyed and recreated (e.g. the phone is rotated with user data entered)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restaurantRecyclerView = (RecyclerView) findViewById(R.id.restaurant_List_RV);
        googleSignInActionButton = (FloatingActionButton) findViewById(R.id.google_sign_in_AB);
        account = GoogleSignIn.getLastSignedInAccount(this); // attempts to automatically login the user if they have previously logged in
        //hide the google sign in FAB if they are already signed in
        if(account!= null )
        {
            googleSignInActionButton.setVisibility(View.GONE);
        }
        //set up formatting for the recylcer view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        restaurantRecyclerView.setLayoutManager(layoutManager);
        restaurantRecyclerView.setHasFixedSize(true);

        restaurantNameAdapter = new RestaurantNameAdapter(FakeDataUtils.generateData(),this);
        restaurantRecyclerView.setAdapter(restaurantNameAdapter);
        //constructs the premade view that google uses to sign someone in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }

    /**
        Handles click events for the list items
        @param clickedItemIndex : index of the item within the list
        @param view : reference to the layout of the list item
     */
    @Override
    public void onRestaurantNameClick(int clickedItemIndex, View view) {
        TextView textView= (TextView) view.findViewById(R.id.tv_restaurant_name_RV_item_display);
        String toastMessage = "Clicked " + textView.getText().toString() + " " + clickedItemIndex;
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this,restaurant_menu_activity.class); // create a new intent to the menu activity
        intent.putExtra("Restaurant Name",textView.getText().toString()); // puts the name of the restaurant in the intent, allowing us to specify which menu to query in the menu
        startActivity(intent);
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

        inflater.inflate(R.menu.lsutrition_main_menu, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }
    /**
        Handles click events on the menu options
        @param item - reference to the element selected by the user
        @return: true when menu processing is complete
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class); // create a new intent for the settings activity
            startActivity(startSettingsActivity);
            return true;
        }else if ((id == R.id.action_sign_out)){
            mGoogleSignInClient.signOut();
            account = null;
            googleSignInActionButton.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Handles the click event for the google sign in floating action button by giving the user
     * the option to sign in to a google account
     * @param view- reference to the view of the floating action button
     */
    public void onClickGoogleSignIn(View view){

        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivity(intent);
        account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!= null )
        {
            //createFileInAppFolder(view);
            googleSignInActionButton.setVisibility(View.GONE);

        }

    }
    /**
        Click handler for the bottom nav component User history
        @param view - reference to the element clicked
     */
    public void onUserHistoryClick(View view){
        Intent intent = new Intent(MainActivity.this, user_history_activity.class);
        startActivity(intent);
    }
    /**
        Click handler for the bottom nav component User Trends
        @param view - reference to the element clicked
     */
    public void onUserTrendClick(View view){
        Intent intent = new Intent(MainActivity.this, User_Trends_Activity.class);
        startActivity(intent);
    }
    /**
        Click handler for the bottom nav component Deals
        @param view - reference to the element clicked
     */
    public void onDealsClick(View view){
        Intent intent = new Intent(MainActivity.this, Deals_Display.class);
        startActivity(intent);
    }

}
