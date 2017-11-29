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
    public RestaurantNameAdapter(String[] names,RestaurantNameClickListener listener){
        restaurant_names = names;
        restaurantNameClickListener = listener;
        numberOfItems = names.length;
        numberOfViewHolders = 0;
    }
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


    @Override
    public void onBindViewHolder(NameViewHolder holder, int position) {
        holder.restaurantNameTextView.setText(restaurant_names[position]);
    }

    @Override
    public int getItemCount() {
        return numberOfItems;
    }

    class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView restaurantNameTextView;

        public NameViewHolder(View view){
            super(view);
            restaurantNameTextView =(TextView) view.findViewById(R.id.tv_restaurant_name_RV_item_display);
            itemView.setOnClickListener(this);
        }
        //void bind(){}
        @Override
        public void onClick(View view) {
        restaurantNameClickListener.onRestaurantNameClick(getAdapterPosition(),view);

        }
    }
}
