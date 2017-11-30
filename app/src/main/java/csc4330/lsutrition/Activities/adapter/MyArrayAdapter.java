package csc4330.lsutrition.Activities.adapter;

/**
 * Created by Jay on 11/29/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import csc4330.lsutrition.R;
import csc4330.lsutrition.Activities.model.MyDataModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class MyArrayAdapter extends ArrayAdapter<MyDataModel> {

    List<MyDataModel> modelList;
    Context context;
    private LayoutInflater mInflater;
    private ArrayList<String> restaurantList, menuItemList;
    private ArrayList<Integer> calorieList;

    // Constructors
    public MyArrayAdapter(Context context, List<MyDataModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
        restaurantList = createRestaurantList(modelList);
        menuItemList = createMenuItemList(modelList);
        calorieList = createCalorieList(modelList);
    }

    @Override
    public MyDataModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MyDataModel item = getItem(position);

        vh.textViewRestaurantName.setText(item.getRestaurantName());
        vh.textViewMenuItem.setText(item.getMenuItem());
        vh.textViewType.setText(item.getType());
        vh.textViewCalories.setText(String.valueOf(item.getCalories()));

        return vh.rootView;
    }

    public ArrayList<String> createRestaurantList(List<MyDataModel> items){
        ArrayList<String> restaurantList = new ArrayList<String>();
        MyDataModel item;
        for(int i = 0; i < items.size(); i++){
            item = getItem(i);
            restaurantList.add(item.getRestaurantName());
        }

        return restaurantList;
    }

    public ArrayList<String> createMenuItemList(List<MyDataModel> items){
        ArrayList<String> menuItemList = new ArrayList<String>();
        MyDataModel item;
        for(int i = 0; i < items.size(); i++){
            item = getItem(i);
            menuItemList.add(item.getMenuItem());
        }

        return menuItemList;
    }

    public ArrayList<Integer> createCalorieList(List<MyDataModel> items){
        ArrayList<Integer> calorieItemList = new ArrayList<Integer>();
        MyDataModel item;
        for(int i = 0; i < items.size(); i++){
            item = getItem(i);
            calorieItemList.add(item.getCalories());
        }

        return calorieItemList;
    }

    public ArrayList<String> getRestaurantList(){
        return this.restaurantList;
    }
    public ArrayList<String> getMenuItemList(){
        return this.menuItemList;
    }
    public ArrayList<Integer> getCalorieList(){
        return this.calorieList;
    }

    /**
     * ViewHolder class for layout.
     */
    private static class ViewHolder {
        public final RelativeLayout rootView;

        public final TextView textViewRestaurantName;
        public final TextView textViewMenuItem;
        public final TextView textViewType;
        public final TextView textViewCalories;

        private ViewHolder(RelativeLayout rootView, TextView textViewRestaurantName, TextView textViewMenuItem, TextView textViewType, TextView textViewCalories) {
            this.rootView = rootView;
            this.textViewRestaurantName = textViewRestaurantName;
            this.textViewMenuItem = textViewMenuItem;
            this.textViewType = textViewType;
            this.textViewCalories = textViewCalories;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewRestaurantName = (TextView) rootView.findViewById(R.id.textViewRestaurantName);
            TextView textViewMenuItem = (TextView) rootView.findViewById(R.id.textViewMenuItem);
            TextView textViewType = (TextView) rootView.findViewById(R.id.textViewType);
            TextView textViewCalories = (TextView) rootView.findViewById(R.id.textViewCalories);
            return new ViewHolder(rootView, textViewRestaurantName, textViewMenuItem, textViewType, textViewCalories);
        }
    }
}