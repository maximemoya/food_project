package fr.maxime.mygame01.MyObjects;

import android.content.Context;

import java.util.ArrayList;

import fr.maxime.mygame01.R;

public class SuiviAlimentaireClass {

    private int timeDayMoment;
    private String dayMomentText;
    private int[] intArrayDate;                                                                     // Format : [jour, mois, annee]

    private ArrayList<FoodClass> arrayListFoodEaten;

    private Double[] arrayTotalElementByarrayListFoodEaten;                                         // enum enumElementList dans LibraryArrayBitmapDrawingRessource;

    private Double[] arrayElementsInFoodAccordingToQuantity;
    private ArrayList<Double[]> bigList = new ArrayList<>();


    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public SuiviAlimentaireClass(int timeDayMoment, int[] intArrayDate, ArrayList<FoodClass> arrayListFoodEaten) {
        this.timeDayMoment = timeDayMoment;
        this.dayMomentText = TransitionClass.getArrayDayMoment()[timeDayMoment];
        this.intArrayDate = intArrayDate;
        this.arrayListFoodEaten = arrayListFoodEaten;
        calculateTotalElementbyFood();
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private void calculateTotalElementbyFood(){

        double d =0.0;

        for (FoodClass food : arrayListFoodEaten) {
            arrayElementsInFoodAccordingToQuantity = food.arrayValuesFoodPer100g.clone();
            double quantityMultipliarFood = ((double) food.getQuantity() / 100);

            for (int i = 0; i < arrayElementsInFoodAccordingToQuantity.length; i++) {
                arrayElementsInFoodAccordingToQuantity[i] *= quantityMultipliarFood;
            }
            bigList.add(arrayElementsInFoodAccordingToQuantity);
        }

        // calcul de totalArray Somme elements :
        arrayTotalElementByarrayListFoodEaten = new Double[arrayElementsInFoodAccordingToQuantity.length];

        for (int j = 0; j < arrayElementsInFoodAccordingToQuantity.length; j++) {
            for (int k = 0; k < arrayListFoodEaten.size(); k++) {
                double x = bigList.get(k)[j];
                d += x;
            }
            arrayTotalElementByarrayListFoodEaten[j] = (double)Math.round(d*10)/10;
            d = 0.0;
        }
    }

    // ---------------------------------------------------------------------------------------------
    // GETTER and SETTER :

    public String getDateNumberFormat(){

        String dateText;

        if(TransitionClass.langageActual.equals(TransitionClass.languageArraySelector[TransitionClass.enumLanguageSelector.Francais.ordinal()])){
            dateText = intArrayDate[0] + " / " + intArrayDate[1] + " / " + intArrayDate[2];
        }
        else {
            dateText = intArrayDate[1] + " / " + intArrayDate[0] + " / " + intArrayDate[2];
        }

        return dateText;

    }

    public int getTimeDayMoment() {
        return timeDayMoment;
    }

    public void setTimeDayMoment(int timeDayMoment) {
        this.timeDayMoment = timeDayMoment;
    }

    public String getDayMomentText() {
        return dayMomentText;
    }

    public void setDayMomentText(String dayMomentText) {
        this.dayMomentText = dayMomentText;
    }

    public int[] getIntArrayDate() {
        return intArrayDate;
    }

    public void setIntArrayDate(int[] intArrayDate) {
        this.intArrayDate = intArrayDate;
    }

    public ArrayList<FoodClass> getArrayListFoodEaten() {
        return arrayListFoodEaten;
    }

    public void setArrayListFoodEaten(ArrayList<FoodClass> arrayListFoodEaten) {
        this.arrayListFoodEaten = arrayListFoodEaten;
    }

    public Double[] getArrayTotalElementByarrayListFoodEaten() {
        return arrayTotalElementByarrayListFoodEaten;
    }

    public void setArrayTotalElementByarrayListFoodEaten(Double[] arrayTotalElementByarrayListFoodEaten) {
        this.arrayTotalElementByarrayListFoodEaten = arrayTotalElementByarrayListFoodEaten;
    }
}
