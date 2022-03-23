package fr.maxime.mygame01.MyObjects;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.Serializable;
import java.util.Comparator;

import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.R;

@SuppressLint("ViewConstructor")
public class FoodClassSerializable implements Serializable {

    public int bitmapRawRessource;

    public String name = "";

    public int category = 0; // cf. enumCategory LibraryArrayBitmapDrawingRessources

    public String[] categoryArrayName = {LibraryArrayBitmapDrawingRessources.categoryEnum.neutre.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.legumineux.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.fruit.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.feculent.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.proteine.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.sucrerie.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.fruitSec.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.plante.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.friture.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.NotUSEDyaourt.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.produit_laitier.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.oeuf.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.viande.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.poisson.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.fruitDeMer.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.boisson.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.champignon.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.fromage.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.cereales.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.alcool.getName()};

    public String categoryName = "";

    public Double acidityPH = 7.0;
    public Double calories = 1.0;
    public Double proteine = 1.0;
    public Double glucide = 1.0;
    public Double sucre = 1.0;
    public Double fibre = 1.0;
    public Double lipide = 1.0;
    public Double acideGrasSature = 1.0;
    public Double eau = 1.0;

    public Double vitamineA = 1.0;
    public Double vitamineB1 = 1.0;
    public Double vitamineB2 = 1.0;
    public Double vitamineB3 = 1.0;
    public Double vitamineB5 = 1.0;
    public Double vitamineB6 = 1.0;
    public Double vitamineB9 = 1.0;
    public Double vitamineB12 = 1.0;
    public Double vitamineC = 1.0;
    public Double vitamineD = 1.0;
    public Double vitamineE = 1.0;

    public Double calcium = 1.0;
    public Double phosphore = 1.0;
    public Double magnesium = 1.0;
    public Double potassium = 1.0;
    public Double sodium = 1.0;
    public Double sel = 1.0;
    public Double fer = 1.0;
    public Double cuivre = 1.0;
    public Double zinc = 1.0;
    public Double manganese = 1.0;
    public Double selenium = 1.0;
    public Double iode = 1.0;

    public Double[] arrayValuesFood;

    public boolean isVegetarien = true;
    public boolean isVegetalien = true;
    private boolean isOnDropZone = false;

    private int quantity = 100;                          // en gr => "FoodSelectorActivityV2.java"
    private String quantityString = quantity + "g";
    public int quantityID = 1;                           // enum  => "FoodSelectorActivityV2.java"

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public FoodClassSerializable(FoodClass food) {
        this.bitmapRawRessource = food.bitmapRawRessource;
        this.name = food.name;
        this.category = food.category;
        this.categoryName = food.categoryName;
        this.acidityPH = food.acidityPH;
        this.calories = food.calories;
        this.proteine = food.proteine;
        this.glucide = food.glucide;
        this.sucre = food.sucre;
        this.fibre = food.fibre;
        this.lipide = food.lipide;
        this.acideGrasSature = food.acideGrasSature;
        this.eau = food.eau;
        this.vitamineA = food.vitamineA;
        this.vitamineB1 = food.vitamineB1;
        this.vitamineB2 = food.vitamineB2;
        this.vitamineB3 = food.vitamineB3;
        this.vitamineB5 = food.vitamineB5;
        this.vitamineB6 = food.vitamineB6;
        this.vitamineB9 = food.vitamineB9;
        this.vitamineB12 = food.vitamineB12;
        this.vitamineC = food.vitamineC;
        this.vitamineD = food.vitamineD;
        this.vitamineE = food.vitamineE;
        this.calcium = food.calcium;
        this.phosphore = food.phosphore;
        this.magnesium = food.magnesium;
        this.potassium = food.potassium;
        this.sodium = food.sodium;
        this.fer = food.fer;
        this.cuivre = food.cuivre;
        this.zinc = food.zinc;
        this.manganese = food.manganese;
        this.selenium = food.selenium;
        this.iode = food.iode;
        this.isVegetarien = food.isVegetarien;
        this.isVegetalien = food.isVegetalien;

        this.quantity = food.getQuantity();
        this.quantityID = food.quantityID;

        arrayValuesFood = new Double[]{food.calories, food.lipide, food.acideGrasSature, food.glucide, food.sucre, food.fibre, food.proteine, food.sel,
                food.vitamineA, food.vitamineB1, food.vitamineB2, food.vitamineB3, food.vitamineB5, food.vitamineB6, food.vitamineB9, food.vitamineB12, food.vitamineC, food.vitamineD, food.vitamineE,
                food.eau, food.calcium, food.cuivre, food.fer, food.iode, food.magnesium, food.manganese, food.phosphore, food.potassium, food.selenium, food.sodium, food.zinc};
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    // CHANGEMENT TEXT : 100g en 100mL selon LIQUIDE OU SOLIDE dans FoodDescriptionV2 :
    public String[] getArrayTextTitleFoodDescriptionV2Quantity(Context context){

        String[] arrayStringTitleQuantityFood;

        if(category == LibraryArrayBitmapDrawingRessources.categoryEnum.boisson.ordinal() ||
                category == LibraryArrayBitmapDrawingRessources.categoryEnum.produit_laitier.ordinal() &&
                        !name.equals(context.getResources().getString(R.string.beurre))){
            arrayStringTitleQuantityFood = new String[]{
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUnePortion50gr),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUnePortion60grOeuf),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUnePortion125grYaourt),
                    context.getResources().getString(R.string.amount_for_100mL),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUnePetiteAssiette200gr),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUneGrandeAssiette400gr),

            };
        }
        else {
            arrayStringTitleQuantityFood = new String[]{
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityUnePortion50gr),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityUnePortion60grOeuf),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityUnePortion125grYaourt),
                    context.getResources().getString(R.string.amount_for_100g),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityUnePetiteAssiette200gr),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityUneGrandeAssiette400gr),


            };
        }
        return arrayStringTitleQuantityFood;
    }

    // ---------------------------------------------------------------------------------------------
    // Getter Setter :

    public int getQuantity() {
        return quantity;
    }

    public void setADDQuantity(int aquantity) {

        quantityID = FoodSelectorActivityV2.enumQuantityChosenAmount.SPECIFIC.geti();
        this.quantity += aquantity;

    }

    public String getQuantityString(Context context){

        quantityString = getQuantity() + "g";
        if (category == LibraryArrayBitmapDrawingRessources.categoryEnum.boisson.index() ||
                category == LibraryArrayBitmapDrawingRessources.categoryEnum.produit_laitier.index() ||
                name.equals(context.getResources().getString(R.string.siropErable)) ||
                name.equals(context.getResources().getString(R.string.miel))) {
            quantityString = getQuantity() + "mL";
        }
        if (name.equals(context.getResources().getString(R.string.beurre))) {
            quantityString = getQuantity() + "g";
        }

        return quantityString;
    }

    public boolean isOnDropZone() {
        return isOnDropZone;
    }

    public void setOnDropZone(boolean onDropZone) {
        isOnDropZone = onDropZone;
    }


    // --------------------------------------------------------------------------------------------
    // Order Methods :

    // Comparaison ordre croissant

    public static Comparator<FoodClassSerializable> comparatorName = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return f1.name.compareTo(f2.name);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorCategoryName = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return f1.categoryName.compareTo(f2.categoryName);
        }

    };

    public static Comparator<FoodClassSerializable> comparatorCategory = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f1.category - f2.category);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorCaloriesCroissant = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f1.calories * 1000 - f2.calories * 1000);
        }
    };

    // Comparaison ordre decroissant

    public static Comparator<FoodClassSerializable> comparatorCalories = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.calories * 1000 - f1.calories * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorPH = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.acidityPH * 1000 - f1.acidityPH * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorProteine = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.proteine * 1000 - f1.proteine * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorGlucide = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.glucide * 1000 - f1.glucide * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorSucre = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.sucre * 1000 - f1.sucre * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorFibre = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.fibre * 1000 - f1.fibre * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorLipide = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.lipide * 1000 - f1.lipide * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorAcideGrasSature = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.acideGrasSature * 1000 - f1.acideGrasSature * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorSel = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.sel * 1000 - f1.sel * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorEau = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.eau * 1000 - f1.eau * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineA = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineA * 1000 - f1.vitamineA * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineB1 = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineB1 * 1000 - f1.vitamineB1 * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineB2 = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineB2 * 1000 - f1.vitamineB2 * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineB3 = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineB3 * 1000 - f1.vitamineB3 * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineB5 = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineB5 * 1000 - f1.vitamineB5 * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineB6 = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineB6 * 1000 - f1.vitamineB6 * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineB9 = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineB9 * 100000 - f1.vitamineB9 * 100000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineB12 = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineB12 * 100000 - f1.vitamineB12 * 100000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineC = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineC * 1000 - f1.vitamineC * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineD = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineD * 1000 - f1.vitamineD * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorVitamineE = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.vitamineE * 1000 - f1.vitamineE * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorCalcium = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.calcium * 1000 - f1.calcium * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorCuivre = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.cuivre * 1000 - f1.cuivre * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorFer = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.fer * 1000 - f1.fer * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorIode = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.iode * 1000 - f1.iode * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorMagnesium = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.magnesium * 1000 - f1.magnesium * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorManganese = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.manganese * 1000 - f1.manganese * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorPhosphore = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.phosphore * 1000 - f1.phosphore * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorPotassium = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.potassium * 1000 - f1.potassium * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorSelenium = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.selenium * 100000 - f1.selenium * 100000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorSodium = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.sodium * 1000 - f1.sodium * 1000);
        }
    };

    public static Comparator<FoodClassSerializable> comparatorZinc = new Comparator<FoodClassSerializable>() {
        @Override
        public int compare(FoodClassSerializable f1, FoodClassSerializable f2) {
            return (int) (f2.zinc * 1000 - f1.zinc * 1000);
        }
    };

}
