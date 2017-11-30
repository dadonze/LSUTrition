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



public class UserHistoryAdapter extends RecyclerView.Adapter<UserHistoryAdapter.HistoryViewHolder>{

    private Cursor historyCursor;
    private Context useContext;

    /**
     * constructor for the UserHistoryAdapter
     * @param context the context the Adapter is used in
     */
    public UserHistoryAdapter(Context context){
        useContext = context;
    }

    /**
     *Called to create a new viewholder within the recyclerView, handling its setup
     * @param parent - the parent view of the Viewgroups, in this case, the RecyclerView
     * @param viewType - integer ID of the viewtype(used when there are different Viewholder children in the same Adapter)
     * @return - the newly created Viewholder
     */
    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(useContext)
                .inflate(R.layout.history_list_item, parent, false);

        return new HistoryViewHolder(view);
    }

    /**
     * Called to bind a created viewHolder to a particular index in the RecyclerView.
     * @param holder - a reference to the viewHolder, allowing us to call any needed functions relative to position
     * @param position - the index of the viewHolder
     */
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


    /**
     *
     * @return - the number of items in the RecyclerView, in this case, the size of the cursor
     */
    @Override
    public int getItemCount() {
        if(historyCursor == null){
            return 0;
        }
        return historyCursor.getCount();
    }

    /**
     * Replaces the current cursor with a new one whenever one is loaded
     * @param cursor - the new Cursor
     * @return - the new Cursor if it is valid, otherwise the old cursor
     */
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

        /**
         * Constructor for the HistoryViewHolder
         * @param itemView - a reference to the view of the xml we want to use
         */
        public HistoryViewHolder(View itemView) {
            super(itemView);
            idView = itemView.findViewById(R.id.order_history_ordernumber_RV);
            calorieView = itemView.findViewById(R.id.order_history_totalcal_RV);

        }
    }
}
