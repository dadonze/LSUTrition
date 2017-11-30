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
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import csc4330.lsutrition.Adapters.RestaurantNameAdapter;
import csc4330.lsutrition.FakeDataUtils;
import csc4330.lsutrition.R;
import csc4330.lsutrition.Activities.adapter.MyArrayAdapter;
import csc4330.lsutrition.Activities.model.MyDataModel;
import csc4330.lsutrition.Activities.parser.JSONparser;
import csc4330.lsutrition.Activities.utils.InternetConnection;
import csc4330.lsutrition.Activities.utils.Keys;
//import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RestaurantNameAdapter.RestaurantNameClickListener {
    private RestaurantNameAdapter restaurantNameAdapter;
    private RecyclerView restaurantRecyclerView;
    private  GoogleSignInClient mGoogleSignInClient;
    private FloatingActionButton googleSignInActionButton;
    private ListView listView;
    public ArrayList<MyDataModel> list;
    public MyArrayAdapter arrayAdapter;
    public static GoogleSignInAccount account;


    public ArrayList<MyDataModel> getList(){
        return this.list;
    }
    /**
        Android System Action called whenever the corresponding layout is inflated (app launched, phone rotated, ect.)
        Actions taken are all setup required for the app interface to work
        @param savedInstanceState: contains any data relevant to the current session in the event that the view is destroyed and recreated (e.g. the phone is rotated with user data entered)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fresco.initialize(this);
        //restaurantRecyclerView = (RecyclerView) findViewById(R.id.restaurant_List_RV);
        googleSignInActionButton = (FloatingActionButton) findViewById(R.id.google_sign_in_AB);
        account = GoogleSignIn.getLastSignedInAccount(this); // attempts to automatically login the user if they have previously logged in
        //hide the google sign in FAB if they are already signed in
        if(account!= null )
        {
            googleSignInActionButton.setVisibility(View.GONE);
        }
        //set up formatting for the recylcer view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //restaurantRecyclerView.setLayoutManager(layoutManager);
        //restaurantRecyclerView.setHasFixedSize(true);


        //constructs the premade view that google uses to sign someone in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        /**
         * Array List for Binding Data from JSON to this List
         */
        list = new ArrayList<>();
        /**
         * Binding that List to Adapter
         */
        arrayAdapter = new MyArrayAdapter(this, list);

        /**
         * Getting List and Setting List Adapter
         */
        listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Snackbar.make(findViewById(R.id.), list.get(position).getRestaurantName() + " => " + list.get(position).getMenuItem(), Snackbar.LENGTH_LONG).show();
            }
        });




                if (InternetConnection.checkConnection(getApplicationContext())) {
                    GetDataTask gdt = (GetDataTask) new GetDataTask().execute();
                    restaurantNameAdapter = new RestaurantNameAdapter(arrayAdapter.getRestaurantList(),this);
                    //restaurantRecyclerView.setAdapter(restaurantNameAdapter);
                } else {
                    //Snackbar.make(view, "Internet Connection Not Available", Snackbar.LENGTH_LONG).show();
                }



    }

    /**
     * Creating Get Data Task for Getting Data From Web
     */
    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;
        MyDataModel model;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */

            x=list.size();

            if(x==0)
                jIndex=0;
            else
                jIndex=x;

            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Please Wait..."+x);
            dialog.setMessage("Loading Database");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            /**
             * Getting JSON Object from Web Using okHttp
             */
            JSONObject jsonObject = JSONparser.getDataFromWeb();

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if(jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray(Keys.KEY_CONTACTS);

                        /**
                         * Check Length of Array...
                         */


                        int lenArray = array.length();
                        if(lenArray > 0) {
                            for( ; jIndex < lenArray; jIndex++) {

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */
                                model = new MyDataModel();

                                /**
                                 * Getting Inner Object from contacts array...
                                 * and
                                 * From that We will get Name of that Contact
                                 *
                                 */
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String restaurantName = innerObject.getString(Keys.KEY_RESTAURANTNAME);
                                String menuItem = innerObject.getString(Keys.KEY_MENUITEM);
                                String type = innerObject.getString(Keys.KEY_TYPE);
                                String calorie = innerObject.getString(Keys.KEY_CALORIES);
                                Integer calories = Integer.parseInt(calorie);

                                /**
                                 * Getting Object from Object "phone"
                                 */
                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);

                                model.setRestaurantName(restaurantName);
                                model.setMenuItem(menuItem);
                                model.setType(type);
                                model.setCalories(calories);

                                /**
                                 * Adding name and phone concatenation in List...
                                 */
                                list.add(model);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSONparser.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */
            if(list.size() > 0) {
                arrayAdapter.notifyDataSetChanged();
            } else {
                //Snackbar.make(findViewById(R.id.match_parent), "No Data Found", Snackbar.LENGTH_LONG).show();
            }
        }


    }

    /**
        Handles click events for the list items
        @param clickedItemIndex : index of the item within the list
        @param view : reference to the layout of the list item
     */
    @Override
    public void onRestaurantNameClick(int clickedItemIndex, View view) {
        TextView textView= (TextView) view.findViewById(R.id.listView);
        String toastMessage = "Clicked " + textView.getText().toString() + " " + clickedItemIndex;
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this,restaurant_menu_activity.class); // create a new intent to the menu activity
        intent.putExtra("Restaurant Name",textView.getText().toString());
        intent.putExtra("Menu Items", arrayAdapter.getMenuItemList());
        intent.putExtra("Calories", arrayAdapter.getCalorieList());// puts the name of the restaurant in the intent, allowing us to specify which menu to query in the menu
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
