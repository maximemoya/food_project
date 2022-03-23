package fr.maxime.mygame01.MyObjects;

import android.annotation.SuppressLint;

import java.io.Serializable;

@SuppressLint("ViewConstructor")
public class FoodClassSerializableV2 implements Serializable {

    private int foodID;
    private int quantity;                                                                           // en gr => "FoodSelectorActivityV2.java"
    private int quantityID;                                                                         // enum  => "FoodSelectorActivityV2.java"

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public FoodClassSerializableV2(FoodClass food) {

        this.foodID = food.getfoodID();
        this.quantity = food.getQuantity();
        this.quantityID = food.quantityID;
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :


    // ---------------------------------------------------------------------------------------------
    // Getter Setter :

    public int getFoodID() {
        return foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getQuantityID() {
        return quantityID;
    }
}