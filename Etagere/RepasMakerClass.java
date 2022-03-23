package fr.maxime.mygame01.Etagere;

import fr.maxime.mygame01.MyObjects.FoodClass;

public class RepasMakerClass {

    // DYNAMIQUE :

    private int image;
    private String titre;
    private String description;
    private int typeOfMeal;

    public enum enumTypeOfMeal {petitDejeuner, dejeuner, apresMidi, dinner;}

    private boolean isVegetarian = true, isVegan = true, isPorkOrAlcohol = false;
    private FoodClass[] arrayFoodListInMeal;

    public RepasMakerClass(int image, String titre, String description, int typeOfMeal, FoodClass[] arrayFoodListInMeal) {
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.typeOfMeal = typeOfMeal;
        this.arrayFoodListInMeal = arrayFoodListInMeal;
        isVegetarianVeganPorkOrAlcohol();

    }

    // ---------------------------------------------------------------------------------------------
    // MyMethods :

    private void isVegetarianVeganPorkOrAlcohol() {

        for (FoodClass food : arrayFoodListInMeal) {
            if (!food.isVegetarien && isVegetarian) {
                isVegetarian = false;
            }
            if (!food.isVegetalien && isVegan) {
                isVegan = false;
            }
            if (food.isPork)
            isPorkOrAlcohol = true;

        }

    }

    // ---------------------------------------------------------------------------------------------
    // GETTER :

    public int getImage() {
        return image;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getTypeOfMeal() {
        return typeOfMeal;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isPorkOrAlcohol() {
        return isPorkOrAlcohol;
    }
}
