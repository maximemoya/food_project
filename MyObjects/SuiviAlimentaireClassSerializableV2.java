package fr.maxime.mygame01.MyObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class SuiviAlimentaireClassSerializableV2 implements Serializable {

    private final int timeDayMoment;
    private final int[] intArrayDate;

    private final FoodClassSerializableV2[] arrayFoodEatenSerializedV2;

    private ArrayList<FoodClass> foodClassArrayListDeserialized;

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public SuiviAlimentaireClassSerializableV2(SuiviAlimentaireClass suiviAlimentaireClass) {
        this.timeDayMoment = suiviAlimentaireClass.getTimeDayMoment();
        this.intArrayDate = suiviAlimentaireClass.getIntArrayDate();

        arrayFoodEatenSerializedV2 = selectionDesElementsDansFoodClassASauvegarder(suiviAlimentaireClass.getArrayListFoodEaten());
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private FoodClassSerializableV2[] selectionDesElementsDansFoodClassASauvegarder(ArrayList<FoodClass> arrayListFoodClass){

        FoodClassSerializableV2[] arrayFoodClassSerializableV2 = new FoodClassSerializableV2[arrayListFoodClass.size()];

        for (FoodClass foodClass : arrayListFoodClass){

            int index = arrayListFoodClass.indexOf(foodClass);

            FoodClassSerializableV2 foodClassSerializableV2 = new FoodClassSerializableV2(foodClass);
            arrayFoodClassSerializableV2[index] = foodClassSerializableV2;

        }

        return arrayFoodClassSerializableV2;

    }

    public void deserializationFoodClass(ArrayList<FoodClass> listAllFood){

        foodClassArrayListDeserialized = new ArrayList<>();

        for(FoodClassSerializableV2 foodSerialized : arrayFoodEatenSerializedV2){

            for (FoodClass food : listAllFood){

                if (foodSerialized.getFoodID() == (food.getfoodID())){

                    FoodClass newFood = new FoodClass(food);
                    newFood.setQuantity(foodSerialized.getQuantity());
                    newFood.quantityID = foodSerialized.getQuantityID();
                    foodClassArrayListDeserialized.add(newFood);
                    break;

                }

            }

        }

    }

    // ---------------------------------------------------------------------------------------------
    // GETTER and SETTER :

    public int getTimeDayMoment() {
        return timeDayMoment;
    }

    public int[] getIntArrayDate() {
        return intArrayDate;
    }

    public ArrayList<FoodClass> getFoodClassArrayListDeserialized() {
        return foodClassArrayListDeserialized;
    }
}
