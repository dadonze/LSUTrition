package csc4330.lsutrition.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import csc4330.lsutrition.R;

/**
 * Created by Danny on 11/22/2017.
 */
//TODO Replace temporary constructor and data list with Cursor
public class RestaurantNameAdapter extends RecyclerView.Adapter<RestaurantNameAdapter.NameViewHolder> {
    private String[] restaurant_names;
    private RestaurantNameClickListener restaurantNameClickListener;
    private int numberOfItems;
    private int numberOfViewHolders;

    public interface RestaurantNameClickListener {
        void onRestaurantNameClick(int clickedItemIndex, View view);
    }

    /**
     * Constructor for the adapter, initializes necesarry fields
     * @param names the list of restaurant names to populate the recyclerView
     * @param listener - a reference to the onClick listener
     */
    public RestaurantNameAdapter(String[] names,RestaurantNameClickListener listener){
        restaurant_names = names;
        restaurantNameClickListener = listener;
        numberOfItems = names.length;
        numberOfViewHolders = 0;
    }

    /**
     Called whenever a viewholder is created to handle its integration into the RecyclerView Adapter
     @param parent: reference to the parent view(RecylerView in this case)
     @param viewType: integer ID of the viewtype(used when there are different Viewholder children in the same Adapter)
     @return: The new Viewholder adapted for the RecyclerView
     */
    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_list_item,parent,false);
        NameViewHolder nameViewHolder = new NameViewHolder(view);
        //nameViewHolder.restaurantNameTextView.setText(restaurant_names[numberOfViewHolders]);
       // numberOfViewHolders++;
        return nameViewHolder;
    }

    /**
     Called when the viewholder is bound to the Recycler view
     @param holder: reference to the viewHolder that was just bound
     @param position: index of the viewholder in the entire list
     */
    @Override
    public void onBindViewHolder(NameViewHolder holder, int position) {
        holder.restaurantNameTextView.setText(restaurant_names[position]);
    }
    /**
     Required function to be overriden, returns the size of the list
     @return : total size of the list
     */
    @Override
    public int getItemCount() {
        return numberOfItems;
    }

    /**
     * Custimized ViewHolder for displaying the names of restaurants
     */
    class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView restaurantNameTextView;

        /**
         * Constructor for the Viewholder
         * @param view
         */
        public NameViewHolder(View view){
            super(view);
            restaurantNameTextView =(TextView) view.findViewById(R.id.tv_restaurant_name_RV_item_display);
            itemView.setOnClickListener(this);
        }
        //void bind(){}

        /**
         * Handles on click events for the viewHolder
         * @param view a reference to the view of the RecyclerView
         */
        @Override
        public void onClick(View view) {
        restaurantNameClickListener.onRestaurantNameClick(getAdapterPosition(),view);

        }
    }
}
