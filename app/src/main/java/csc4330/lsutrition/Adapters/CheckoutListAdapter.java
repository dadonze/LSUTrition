package csc4330.lsutrition.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import csc4330.lsutrition.R;
import csc4330.lsutrition.RestaurantMenuItem;
import csc4330.lsutrition.User_Order_Cart;

/**
 * Created by Danny on 11/26/2017.
 */

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.CheckoutViewHolder> {
    User_Order_Cart cart;
    public CheckoutListAdapter(){
        cart = User_Order_Cart.createUser_Order_Cart();
    }
    @Override
    public CheckoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.checkout_list_item,parent,false);
        CheckoutViewHolder checkoutViewHolder = new CheckoutViewHolder(view);
        return checkoutViewHolder;

    }

    @Override
    public void onBindViewHolder(CheckoutViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return cart.getSize();
    }

    class CheckoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView restaurantItemTextView;
        TextView restaurantCalorieTextView;
        /*
            Overriden Constructor for the Viewholder, obtains references to the xml elements in its elements
            @param view: reference to the xml layout for the individual item

         */
        public CheckoutViewHolder(View view){
            super(view);
            restaurantItemTextView =(TextView) view.findViewById(R.id.checkout_item__name_RV);
            restaurantCalorieTextView = (TextView) view.findViewById(R.id.checkout_item_calorie_RV);
            //itemView.setOnClickListener(this);
        }
        /*
            Called when the viewHolder is bound to the Recyclerview
            @param index: the index of the item relative to the entire list
         */
        public void bind(int index){
            RestaurantMenuItem item = cart.viewItem(index);
            restaurantItemTextView.setText(item.getName());
            String calorieText = "Calories: " + String.valueOf(item.getCalories());
            restaurantCalorieTextView.setText(calorieText);
        }
        /*
            Click handler for when a viewHolder is clicked
            @param view : reference to the xml layout of the viewholder
         */
        @Override
        public void onClick(View view) {


        }
    }
}
