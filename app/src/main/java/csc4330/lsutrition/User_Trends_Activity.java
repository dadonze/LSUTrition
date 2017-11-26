package csc4330.lsutrition;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class User_Trends_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__trends_);
        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    /*
        Click handler for the bottom nav component User History
        @param view - reference to the element clicked
     */
    public void onUserHistoryClick(View view){
        Intent intent = new Intent(User_Trends_Activity.this, user_history_activity.class);
        startActivity(intent);
    }
    /*
        Click handler for the bottom nav Home button
        @param view - reference to the element clicked
     */
    public void onBottomNavHomeClick(View view){
        Intent intent = new Intent(User_Trends_Activity.this, MainActivity.class);
        startActivity(intent);
    }
    /*
        Click handler for the bottom nav component Deals
        @param view - reference to the element clicked
     */
    public void onDealsClick(View view){
        Intent intent = new Intent(User_Trends_Activity.this, Deals_Display.class);
        startActivity(intent);
    }
}
