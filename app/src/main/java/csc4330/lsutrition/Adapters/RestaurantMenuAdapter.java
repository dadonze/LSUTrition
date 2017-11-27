package csc4330.lsutrition.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import csc4330.lsutrition.R;
import csc4330.lsutrition.RestaurantMenuItem;

/**
 * Created by Danny on 11/24/2017.
 */

public class RestaurantMenuAdapter extends RecyclerView.Adapter<RestaurantMenuAdapter.ItemViewHolder> {

    private RestaurantMenuItem[] menu_items;
    private RestaurantMenuItemClickListener restaurantMenuItemClickListener;
    private int numberOfItems;
    private int numberOfViewHolders;

    public RestaurantMenuAdapter(RestaurantMenuItem[] items, RestaurantMenuItemClickListener listener){
        menu_items=items;
        numberOfItems= items.length;
        numberOfViewHolders = 0;
        restaurantMenuItemClickListener = listener;
    }

    /*
        Called whenever a viewholder is created to handle its integration into the RecyclerView Adapter
        @param parent: reference to the parent view(RecylerView in this case)
        @param ViewType: integer ID of the viewtype(used when there are different Viewholder children in the same Adapter)
        @return: The new Viewholder adapted for the RecyclerView
     */
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menu_list_item,parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    /*
        Called when the viewholder is bound to the Recycler view
        @param holder: reference to the viewHolder that was just bound
        @param position: index of the viewholder in the entire list
     */
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(position);
    }
    /*
        Required function to be overriden, returns the size of the list
        @return : total size of the list
     */
    @Override
    public int getItemCount() {
        return numberOfItems;
    }

    public interface RestaurantMenuItemClickListener {
        void onRestaurantItemClick(int clickedItemIndex, View view);
    }
    /*
        Customized Viewholder class for holding the menu item information
     */
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView restaurantItemTextView;
        TextView restaurantCalorieTextView;
        /*
            Overriden Constructor for the Viewholder, obtains references to the xml elements in its elements
            @param view: reference to the xml layout for the individual item

         */
        public ItemViewHolder(View view){
            super(view);
            restaurantItemTextView =(TextView) view.findViewById(R.id.tv_selected_restaurant_menu_item_name_RV);
            restaurantCalorieTextView = (TextView) view.findViewById(R.id.tv_selected_restaurant_menu_item_calories_RV);
            itemView.setOnClickListener(this);
        }
        /*
            Called when the viewHolder is bound to the Recyclerview
            @param index: the index of the item relative to the entire list
         */
        public void bind(int index){
            restaurantItemTextView.setText(menu_items[index].getName());
            String calorieText = String.valueOf(menu_items[index].getCalories());
            restaurantCalorieTextView.setText(calorieText);
        }
        /*
            Click handler for when a viewHolder is clicked
            @param view : reference to the xml layout of the viewholder
         */
        @Override
        public void onClick(View view) {
            restaurantMenuItemClickListener.onRestaurantItemClick(getAdapterPosition(),view);

        }
    }
}
