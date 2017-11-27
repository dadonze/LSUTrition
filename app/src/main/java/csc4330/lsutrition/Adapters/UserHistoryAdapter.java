package csc4330.lsutrition.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import csc4330.lsutrition.OrderContentData.OrderContract;
import csc4330.lsutrition.R;

/**
 * Created by Danny on 11/27/2017.
 */

public class UserHistoryAdapter extends RecyclerView.Adapter<UserHistoryAdapter.HistoryViewHolder>{

    private Cursor historyCursor;
    private Context useContext;

    public UserHistoryAdapter(Context context){
        useContext = context;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(useContext)
                .inflate(R.layout.history_list_item, parent, false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        int idIndex = historyCursor.getColumnIndex(OrderContract.OrderEntry._ID);
        int calorieIndex = historyCursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_ORDER_CALORIES);
        historyCursor.moveToPosition(position); // get to the right location in the cursor

        // Determine the values of the wanted data
        final int id = historyCursor.getInt(idIndex);
        float calories = historyCursor.getFloat(calorieIndex);


        //Set values
        String idtext="Order #"+String.valueOf(id);
        String caltext = String.valueOf(calories) + " Calories";
        holder.idView.setText(idtext);
        holder.calorieView.setText(caltext);

    }



    @Override
    public int getItemCount() {
        if(historyCursor == null){
            return 0;
        }
        return historyCursor.getCount();
    }
    public Cursor swapCursor(Cursor cursor) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (historyCursor == cursor) {
            return null; // bc nothing has changed
        }
        Cursor temp = historyCursor;
        historyCursor = cursor; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }
    class HistoryViewHolder extends RecyclerView.ViewHolder{

        private TextView idView;
        private TextView calorieView;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            idView = itemView.findViewById(R.id.order_history_ordernumber_RV);
            calorieView = itemView.findViewById(R.id.order_history_totalcal_RV);

        }
    }
}
