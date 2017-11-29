package csc4330.lsutrition.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import csc4330.lsutrition.R;
import csc4330.lsutrition.RestaurantMenuItem;
import csc4330.lsutrition.User_Order_Cart;

/**
 * Created by Danny on 11/26/2017.
 */

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.CheckoutViewHolder> {
    User_Order_Cart cart;
    deleteItemClickListener listener;
    //private int

    /**
     * Constructor for the CheckoutListAdapter, initializes the cart
     */
    public CheckoutListAdapter(deleteItemClickListener deleteItemClickListener){

        cart = User_Order_Cart.createUser_Order_Cart();
        listener = deleteItemClickListener;
    }


    public interface deleteItemClickListener {
        void onDeleteItemClick(int clickedItemIndex, View view);
    }/**
     * Called to create a new viewholder within the recyclerView, handling its setup
     * @param parent - parent view of all viewholders(RecyclerView in this case)
     * @param viewType - id of the ViewHolder type, used if more than one Viewholder can populate a RecyclerView
     * @return the newly created checkoutViewHolder
     */
    @Override
    public CheckoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.checkout_list_item,parent,false);
        CheckoutViewHolder checkoutViewHolder = new CheckoutViewHolder(view);
        return checkoutViewHolder;

    }

    /**
     * Called to bind a created viewHolder to a particular index in the RecyclerView.
     * @param holder a reference to the viewHolder, allowing us to call any needed functions relative to its position
     * @param position the index of the viewHolder in the RecyclerView
     */
    @Override
    public void onBindViewHolder(CheckoutViewHolder holder, int position) {
        holder.bind(position);
    }

    /**
     *
     * @return - the size of the RecyclerView(in this case, the size of the cart)
     */
    @Override
    public int getItemCount() {
        return cart.getSize();
    }

    class CheckoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView restaurantItemTextView;
        TextView restaurantCalorieTextView;
        ImageButton deleteItemButton;

        /**
            Overriden Constructor for the Viewholder, obtains references to the xml elements in its elements
            @param view: reference to the xml layout for the individual item

         */
        public CheckoutViewHolder(View view){
            super(view);
            restaurantItemTextView =(TextView) view.findViewById(R.id.checkout_item__name_RV);
            restaurantCalorieTextView = (TextView) view.findViewById(R.id.checkout_item_calorie_RV);
            deleteItemButton = view.findViewById(R.id.checkout_item_delete_button_RV);
            deleteItemButton.setOnClickListener(this);
            //itemView.setOnClickListener(this);
        }
        /**
            Called when the viewHolder is bound to the Recyclerview,
            @param index: the index of the item relative to the entire list
         */
        public void bind(int index){
            RestaurantMenuItem item = cart.viewItem(index);
            restaurantItemTextView.setText(item.getName());
            String calorieText = "Calories: " + String.valueOf(item.getCalories());
            restaurantCalorieTextView.setText(calorieText);


        }
        /**
            Click handler for when a viewHolder is clicked (Required by parent class)
            @param view : reference to the xml layout of the viewholder
         */
        @Override
        public void onClick(View view) {
            listener.onDeleteItemClick(getAdapterPosition(),view);
        }

    }
}
