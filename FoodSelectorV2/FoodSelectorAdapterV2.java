package fr.maxime.mygame01.FoodSelectorV2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

public class FoodSelectorAdapterV2 extends BaseAdapter {

    private Context context;
    private ArrayList<FoodClass> itemGridViewList;
    private LayoutInflater inflater;

    private String itemName;


    public FoodSelectorAdapterV2(Context context, ArrayList<FoodClass> itemGridViewList) {
        this.context = context;
        this.itemGridViewList = itemGridViewList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemGridViewList.size();
    }

    @Override
    public Object getItem(int indexItem) {
        return itemGridViewList.get(indexItem);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.adapter_food_selector_v2_gridview, null);

        FoodClass currentItem = (FoodClass) getItem(i);
        int itemImageResource = currentItem.bitmapRawRessource;

        if(TransitionClass.levelCategoryGridView == 0){
            itemName = currentItem.categoryName;
        }
        else {
            itemName = currentItem.name;
        }

        ImageView itemImageView = view.findViewById(R.id.adapterFoodSelectorV2ImageView);
        itemImageView.setImageResource(itemImageResource);

        TextView itemNameView = view.findViewById(R.id.adapterFoodSelectorV2TextView);
        itemNameView.setText(itemName);

        return view;
    }
}
