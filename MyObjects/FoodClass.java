package fr.maxime.mygame01.MyObjects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MySpriteClass;
import fr.maxime.mygame01.R;

@SuppressLint("ViewConstructor")
public class FoodClass extends MySpriteClass {

    private static int identifiantUnique = 0;
    private int foodID = 0;
    private int valuePouce;

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
            LibraryArrayBitmapDrawingRessources.categoryEnum.cereales.getName(), LibraryArrayBitmapDrawingRessources.categoryEnum.alcool.getName(),
            LibraryArrayBitmapDrawingRessources.categoryEnum.condiment.getName()};

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

    public Double[] arrayValuesFoodPer100g;
    public String[] arrayStringUnitValuesFood;
    public static String[] arrayStringUnitValuesFoodStatic;

    public boolean isVegetarien;
    public boolean isVegetalien;
    public boolean isPork;

    private boolean isOnDropZone = false;
    private int indexOnTemporaryList;

    private int quantity = 100;                          // en gr => "FoodSelectorActivityV2.java"
    private String quantityString = quantity + "g";
    public int quantityID = 1;                           // enum  => "FoodSelectorActivityV2.java"

    public enum enumQuantityChosenAmount {
        SPECIFIC(0), SMALL(-1), MEDIUM(-2), BIG(-3);

        enumQuantityChosenAmount(int i) {
        }

        public int geti() {
            switch (this.ordinal()) {
                case 1:
                    return -1;
                case 2:
                    return -2;
                case 3:
                    return -3;
                default:
                    return 0;
            }
        }
    }

    Random random = new Random();


    // ---
    // STATIC Methods :
    // ---------------------------------------------------------------------------------------------

    public static String[] getArrayStringTotalFoodAllElements(Double[] arrayTotalFoodValues) {

        String[] arrayStringToReturn = new String[arrayTotalFoodValues.length];

        for (int i = 0; i < arrayTotalFoodValues.length; i++) {

            arrayStringToReturn[i] = arrayTotalFoodValues[i] + " " + arrayStringUnitValuesFoodStatic[i];
        }

        return arrayStringToReturn;

    }

    public static String[] getArrayStringActualNeedDoubleValuesWithSign(Double[] arrayTotalValueFood) {

        Double[] actualNeedDoubleArray = TransitionClass.getArrayDoubleValueByElementsForActualNeedWITHOUTNEGATIVE();
        String[] arrayStringToReturn = new String[actualNeedDoubleArray.length];

        for (int i = 0; i < actualNeedDoubleArray.length; i++) {

            if (arrayTotalValueFood[i] < actualNeedDoubleArray[i]) {
                arrayStringToReturn[i] = "< " + actualNeedDoubleArray[i] + " " + arrayStringUnitValuesFoodStatic[i];
            } else if (arrayTotalValueFood[i] > actualNeedDoubleArray[i]) {
                arrayStringToReturn[i] = "> " + actualNeedDoubleArray[i] + " " + arrayStringUnitValuesFoodStatic[i];
            } else {
                arrayStringToReturn[i] = "= " + actualNeedDoubleArray[i] + " " + arrayStringUnitValuesFoodStatic[i];
            }

        }

        return arrayStringToReturn;

    }

    public static int getLengthOfArrayDoubleValueFoodPer100g() {
        return arrayStringUnitValuesFoodStatic.length;
    }

    public static Double[] getCalculateTotalDoubleValueByElementOfAFoodList(ArrayList<FoodClass> foodClassArrayList) {

        Double[] totalValueArrayToReturn = new Double[FoodClass.getLengthOfArrayDoubleValueFoodPer100g()];
        Arrays.fill(totalValueArrayToReturn, 0.0);

        for (FoodClass food : foodClassArrayList) {

            for (int i = 0; i < food.arrayValuesFoodPer100g.length; i++) {

                double d = (double) Math.round((food.arrayValuesFoodPer100g[i] * food.quantity / 100) * 1000) / 1000;
                totalValueArrayToReturn[i] += d;

            }

        }

        return totalValueArrayToReturn;

    }

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public FoodClass(Context context, int bitmapRawRessource, Bitmap bitmap, String name,
                     int category, Double acidityPH, Double calories, Double proteine, Double glucide, Double sucre, Double fibre, Double lipide, Double acideGrasSature, Double eau,
                     Double vitamineA, Double vitamineB1, Double vitamineB2, Double vitamineB3, Double vitamineB5, Double vitamineB6, Double vitamineB9, Double vitamineB12, Double vitamineC, Double vitamineD, Double vitamineE,
                     Double calcium, Double phosphore, Double magnesium, Double potassium, Double sodium, Double fer, Double cuivre, Double zinc, Double manganese, Double selenium, Double iode,
                     boolean isVegetarien, boolean isVegetalien, boolean isPork) {
        super(context, bitmap);
        this.bitmapRawRessource = bitmapRawRessource;
        this.name = name;
        this.category = category;
        this.categoryName = categoryArrayName[category];
        this.acidityPH = acidityPH;
        this.calories = calories;
        this.proteine = proteine;
        this.glucide = glucide;
        this.sucre = sucre;
        this.fibre = fibre;
        this.lipide = lipide;
        this.acideGrasSature = acideGrasSature; // en g et ceux avant aussi
        this.eau = eau;                         // en mL
        this.vitamineA = vitamineA;
        this.vitamineB1 = vitamineB1;
        this.vitamineB2 = vitamineB2;
        this.vitamineB3 = vitamineB3;
        this.vitamineB5 = vitamineB5;
        this.vitamineB6 = vitamineB6;
        this.vitamineB9 = vitamineB9;
        this.vitamineB12 = vitamineB12 * 1000;  // conversion en microGramme
        this.vitamineC = vitamineC;
        this.vitamineD = vitamineD * 1000;      // conversion en microGramme
        this.vitamineE = vitamineE;
        this.calcium = calcium;
        this.phosphore = phosphore;
        this.magnesium = magnesium;
        this.potassium = potassium / 1000;      // conversion en gramme
        this.sodium = sodium / 1000;            // conversion en gramme
        this.sel = this.sodium * 2.5;           // conversion en gramme
        this.fer = fer;
        this.cuivre = cuivre;
        this.zinc = zinc;
        this.manganese = manganese;
        this.selenium = selenium * 1000;        // conversion en microGramme
        this.iode = iode;                       // sans conversion mais en microGramme de base
        this.isVegetarien = isVegetarien;
        this.isVegetalien = isVegetalien;
        this.isPork = isPork;

        arrayValuesFoodPer100g = new Double[]{calories, lipide, acideGrasSature, glucide, sucre, fibre, proteine, sel,
                vitamineA, vitamineB1, vitamineB2, vitamineB3, vitamineB5, vitamineB6, vitamineB9, this.vitamineB12, vitamineC, this.vitamineD, vitamineE,
                eau, calcium, cuivre, fer, iode, magnesium, manganese, phosphore, this.potassium, this.selenium, this.sodium, zinc};

        setArrayStringUnitFood();

        setPosition(-2 * width, 0);

        foodID = identifiantUnique;
        identifiantUnique++;
    }

    // ---------------------------------------------------------------------------------------------
    // CLONE Constructor :

    public FoodClass(FoodClass food) {
        super(food.context, food.bitmap);
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
        this.isPork = food.isPork;

        this.quantity = food.quantity;
        this.quantityID = food.quantityID;

        arrayValuesFoodPer100g = new Double[]{food.calories, food.lipide, food.acideGrasSature, food.glucide, food.sucre, food.fibre, food.proteine, food.sel,
                food.vitamineA, food.vitamineB1, food.vitamineB2, food.vitamineB3, food.vitamineB5, food.vitamineB6, food.vitamineB9, food.vitamineB12, food.vitamineC, food.vitamineD, food.vitamineE,
                food.eau, food.calcium, food.cuivre, food.fer, food.iode, food.magnesium, food.manganese, food.phosphore, food.potassium, food.selenium, food.sodium, food.zinc};

        setArrayStringUnitFood();

        setPosition(-2 * width, 0);

        this.foodID = food.getfoodID();
    }

    // ---------------------------------------------------------------------------------------------
    // Animation Methods :

    public Bitmap getDrawAnimateImage() {

        isOnScreen = positionX > 0 && positionX < screenWidth && positionY > 0 && positionY < screenHeight;

        // OnClick
        if (isOnClick) {
            isOnClick = false;
        }

        // OnTouch
        if (isOnTouch) {
            setRectImagePosition();
            return bitmap;
        }

        // Idle Position
        else if (isOnIdlePosition) {
            setRectImagePosition();
            return bitmap;
        } else if (isOnDropZone) {
            setRectImagePosition();
            return bitmap;
        }

        // COLLIDE WITH OTHER BY SIDE
//        else if (sideCollide == sideCollideEnum.LEFT.action()) {
//            positionX += 50;
//            if (positionX > screenWidth + width * 50) {
//                resetPosition();
//            }
//        } else if (sideCollide == sideCollideEnum.RIGHT.action()) {
//            positionX -= 50;
//            if (positionX < -width * 50) {
//                resetPosition();
//            }
//        } else if (sideCollide == sideCollideEnum.UP.action()) {
//            positionY += 50;
//            if (positionY < -height) {
//                resetPosition();
//            }
//        } else if (sideCollide == sideCollideEnum.DOWN.action()) {
//            positionY -= 50;
//            if (positionY > screenHeight + height) {
//                resetPosition();
//            }
//        }
        // MOVING ALONE to right side
        else if (isGoingToRight) {
            positionX += imgSpeed;
//            if (positionX > screenWidth + width) {
//                resetPosition();
//            }
        }
        // MOVING ALONE to left side
        else {
            positionX -= imgSpeed;
//            if (positionX < -width) {
//                resetPosition();
//            }
        }

        sideCollide = 0;
        setRectImagePosition();
        return bitmap;
    }

    public void resetPosition() {

        positionX = -500;
        positionY = -500;
        isOnScreen = false;
        isOnTouch = false;
        isOnIdlePosition = true;
        isOnDropZone = false;
        imgFrame = 0;
        imgSpeed = 12;
        setRectImagePosition();
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    // CHANGEMENT TEXT : 100g en 100mL selon LIQUIDE OU SOLIDE dans FoodDescriptionV2 et autres :

    private boolean isALiquid(Context context) {

        boolean isAliquid = false;

        if (category == LibraryArrayBitmapDrawingRessources.categoryEnum.boisson.index()
                || category == LibraryArrayBitmapDrawingRessources.categoryEnum.produit_laitier.index()
                || category == LibraryArrayBitmapDrawingRessources.categoryEnum.condiment.index()
                || category == LibraryArrayBitmapDrawingRessources.categoryEnum.alcool.index()
                || name.equals(context.getResources().getString(R.string.siropErable))
                || name.equals(context.getResources().getString(R.string.miel))) {
            isAliquid = true;
        }
        if (name.equals(context.getResources().getString(R.string.beurre))
                || name.equals(context.getResources().getString(R.string.yaourt_nature))
                || name.equals(context.getResources().getString(R.string.yaourt_fruit))
                || name.equals(context.getResources().getString(R.string.sel))
                || name.equals(context.getResources().getString(R.string.sucre))) {
            isAliquid = false;
        }

        return isAliquid;

    }

    public String[] getArrayTextTitleFoodDescriptionV2Quantity(Context context) {

        String[] arrayStringTitleQuantityFood;

        if (isALiquid(context)) {
            arrayStringTitleQuantityFood = new String[]{
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUnePortion50gr),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUnePortion60grOeuf),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUnePortion125grYaourt),
                    context.getResources().getString(R.string.amount_for_100mL),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUnePetiteAssiette200gr),
                    context.getResources().getString(R.string.textTitleFoodDescriptionV2QuantityMilliLitreUneGrandeAssiette400gr),

            };
        } else {
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

    public String getQuantityString(Context context) {

        String textQuantity;

        if (isALiquid(context)) {
            textQuantity = getQuantity() + "mL";

            if (quantity >= 500) {
                double quantityLitre = (double) quantity / 1000;
                textQuantity = quantityLitre + "L";
            }

        } else {
            textQuantity = getQuantity() + "g";

            if (quantity >= 1000) {
                double quantitykilogramme = (double) quantity / 1000;
                textQuantity = quantitykilogramme + "kg";
            }
        }

        return textQuantity;
    }

    public void setQuantity(int quantity) {

        quantityID = quantity;

        if (quantity == FoodSelectorActivityV2.enumQuantityChosenAmount.SMALL.geti()) {

            // ------------------------------------------------------------------------------------
            // PORTION LEGERE :

            if (name.equals(context.getResources().getString(R.string.miel))
                    || name.equals(context.getResources().getString(R.string.siropErable))
                    || name.equals(context.getResources().getString(R.string.sel))
                    || name.equals(context.getResources().getString(R.string.sucre))) {
                this.quantity = 10;

                if (name.equals(context.getResources().getString(R.string.sel))) {
                    this.quantity /= 10;
                }

            }

            // Legumes, Feculents, Friture, Viande :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.legumineux.ordinal()
                            || category == LibraryArrayBitmapDrawingRessources.categoryEnum.feculent.ordinal()
                            || category == LibraryArrayBitmapDrawingRessources.categoryEnum.friture.ordinal()
                            || category == LibraryArrayBitmapDrawingRessources.categoryEnum.viande.ordinal()) {
                this.quantity = 100;
            }

            // Boissons :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.boisson.ordinal()) {
                this.quantity = 100;
            }

            // Produits Laitier :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.produit_laitier.ordinal()) {
                this.quantity = 100;

                // beurre :
                if (name.equals(context.getResources().getString(R.string.beurre))) {
                    this.quantity = 10;
                }

                // yaourts :
                if (name.equals(context.getResources().getString(R.string.yaourt_nature))
                        || name.equals(context.getResources().getString(R.string.yaourt_fruit))) {
                    this.quantity = 75;
                }
            }

            // Alcools :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.alcool.ordinal()) {
                this.quantity = 150;

                if (name.equals(context.getResources().getString(R.string.alcool_biere))) {
                    this.quantity = 250;
                }
                if (name.equals(context.getResources().getString(R.string.alcool_fort))) {
                    this.quantity = 100;
                }
            }

            // CAS GENERAL :
            else {
                this.quantity = 50;
            }

        }

        // ------------------------------------------------------------------------------------
        // PORTION MOYENNE :

        else if (quantity == FoodSelectorActivityV2.enumQuantityChosenAmount.MEDIUM.geti()) {

            if (name.equals(context.getResources().getString(R.string.miel))
                    || name.equals(context.getResources().getString(R.string.siropErable))
                    || name.equals(context.getResources().getString(R.string.sel))
                    || name.equals(context.getResources().getString(R.string.sucre))) {
                this.quantity = 25;

                if (name.equals(context.getResources().getString(R.string.sel))) {
                    this.quantity /= 10;
                }

            }

            // Legumes, Feculents, Friture, Viande :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.legumineux.ordinal()
                            || category == LibraryArrayBitmapDrawingRessources.categoryEnum.feculent.ordinal()
                            || category == LibraryArrayBitmapDrawingRessources.categoryEnum.friture.ordinal()
                            || category == LibraryArrayBitmapDrawingRessources.categoryEnum.viande.ordinal()) {
                this.quantity = 200;
            }

            // Boissons :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.boisson.ordinal()) {
                this.quantity = 250;
            }

            // Produits Laitier :
            else if (category == LibraryArrayBitmapDrawingRessources.categoryEnum.produit_laitier.ordinal()) {
                this.quantity = 250;

                // beurre :
                if (name.equals(context.getResources().getString(R.string.beurre))) {
                    this.quantity = 25;
                }

                // yaourts :
                if (name.equals(context.getResources().getString(R.string.yaourt_nature))
                        || name.equals(context.getResources().getString(R.string.yaourt_fruit))) {
                    this.quantity = 125;
                }

            }

            // Alcools :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.alcool.ordinal()) {
                this.quantity = 250;

                if (name.equals(context.getResources().getString(R.string.alcool_biere))) {
                    this.quantity = 500;
                }
                if (name.equals(context.getResources().getString(R.string.alcool_fort))) {
                    this.quantity = 200;
                }
            }

            // CAS GENERAL :
            else {
                this.quantity = 100;
            }
        }

        // ------------------------------------------------------------------------------------
        // PORTION ELEVEE :

        else if (quantity == FoodSelectorActivityV2.enumQuantityChosenAmount.BIG.geti()) {

            if (name.equals(context.getResources().getString(R.string.beurre))
                    || name.equals(context.getResources().getString(R.string.miel))
                    || name.equals(context.getResources().getString(R.string.siropErable))
                    || name.equals(context.getResources().getString(R.string.sel))
                    || name.equals(context.getResources().getString(R.string.sucre))) {
                this.quantity = 50;

                if (name.equals(context.getResources().getString(R.string.sel))) {
                    this.quantity /= 10;
                }

            }

            // Legumes, Feculents, Friture, Viande :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.legumineux.ordinal()
                            || category == LibraryArrayBitmapDrawingRessources.categoryEnum.feculent.ordinal()
                            || category == LibraryArrayBitmapDrawingRessources.categoryEnum.friture.ordinal()
                            || category == LibraryArrayBitmapDrawingRessources.categoryEnum.viande.ordinal()) {
                this.quantity = 400;
            }

            // Boissons :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.boisson.ordinal()) {
                this.quantity = 500;
            }

            // Produits Laitier :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.produit_laitier.ordinal()) {
                this.quantity = 500;

                // beurre :
                if (name.equals(context.getResources().getString(R.string.beurre))) {
                    this.quantity = 50;
                }

                // yaourts :
                if (name.equals(context.getResources().getString(R.string.yaourt_nature))
                        || name.equals(context.getResources().getString(R.string.yaourt_fruit))) {
                    this.quantity = 250;
                }
            }

            // Alcool :
            else if
            (category == LibraryArrayBitmapDrawingRessources.categoryEnum.alcool.ordinal()) {
                this.quantity = 500;

                if (name.equals(context.getResources().getString(R.string.alcool_biere))) {
                    this.quantity = 1000;
                }
                if (name.equals(context.getResources().getString(R.string.alcool_fort))) {
                    this.quantity = 400;
                }
            }

            // CAS GENERAL :
            else {
                this.quantity = 200;
            }
        }

        // ------------------------------------------------------------------------------------
        // PORTION SPECIFIQUE :
        else if (quantity > 0) {
            quantityID = FoodSelectorActivityV2.enumQuantityChosenAmount.SPECIFIC.geti();
            this.quantity = quantity;
        }

    }

    public int getColorQuantityText() {

        int colorQuantity;

        if (quantityID == enumQuantityChosenAmount.SMALL.geti()) {
            colorQuantity = context.getColor(R.color.quantitySmall);
        } else if (quantityID == enumQuantityChosenAmount.MEDIUM.geti()) {
            colorQuantity = context.getColor(R.color.quantityMedium);
        } else if (quantityID == enumQuantityChosenAmount.BIG.geti()) {
            colorQuantity = context.getColor(R.color.quantityBig);
        } else {
            colorQuantity = context.getColor(R.color.quantitySpecific);
        }

        return colorQuantity;
    }

    // Valeur index pour TITRE TEXTE QUANTITE DE L'ALIMENT (portion +/-50g petite 200g grande 400g...) :
    public int getValueIndexQuantityFoodByElementOnClick(Context context, int valueIndexQuantityFoodByElement) {

        // CAS GENERAL :
        if (valueIndexQuantityFoodByElement < getArrayTextTitleFoodDescriptionV2Quantity(context).length - 1) {
            if (valueIndexQuantityFoodByElement == LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UNEPORTION50gr.ordinal()) {
                valueIndexQuantityFoodByElement = LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UN100gr.ordinal();
            } else {
                valueIndexQuantityFoodByElement++;
            }
        } else {
            valueIndexQuantityFoodByElement = LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UNEPORTION50gr.ordinal();
        }

        // CAS PARTICULIERS :
        // oeuf
        if (category == LibraryArrayBitmapDrawingRessources.categoryEnum.oeuf.ordinal()) {
            if (valueIndexQuantityFoodByElement == LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UNEPORTION60gr.ordinal()) {
                valueIndexQuantityFoodByElement = LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UN100gr.ordinal();
            } else if (valueIndexQuantityFoodByElement < LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UN100gr.ordinal()) {
                valueIndexQuantityFoodByElement = LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UNEPORTION60gr.ordinal();
            }
        }
        // yaourt
        else if (category == LibraryArrayBitmapDrawingRessources.categoryEnum.NotUSEDyaourt.ordinal()) {
            if (valueIndexQuantityFoodByElement == LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UNEPORTION125gr.ordinal()) {
                valueIndexQuantityFoodByElement = LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UN100gr.ordinal();
            } else if (valueIndexQuantityFoodByElement < LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UN100gr.ordinal()) {
                valueIndexQuantityFoodByElement = LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UNEPORTION125gr.ordinal();
            }
        }

        return valueIndexQuantityFoodByElement;
    }

    // Text about Quantity of food According Portion And Element :
    public String getTextQuantityAccordingPortionAndElement(int valueIndexEnumQuantityFoodByElement, int indexFoodinArrayValuesFood) {

        double valueQuantityFoodPerElement = arrayValuesFoodPer100g[indexFoodinArrayValuesFood] * LibraryArrayBitmapDrawingRessources.arrayMultipliarQuantityAmount[valueIndexEnumQuantityFoodByElement];

        String text;
        text = String.valueOf((float) Math.round(valueQuantityFoodPerElement * 1000) / 1000);

        // Calorie
        if (indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal()) {
            text += " kCal";
        } else if (indexFoodinArrayValuesFood <= LibraryArrayBitmapDrawingRessources.enumElementsListe.SEL.ordinal()) {
            text += " g";
        }
        // EAU
        else if (indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.EAU.ordinal()) {
            text += " mL";
        }
        // POTASSIUM
        else if (indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.POTASSIUM.ordinal() ||
                indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.SODIUM.ordinal()) {
            text += " g";
        }
        // VITAMINE B12 / D / SELENIUM :
        else if (indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.VITAMINE_B12.ordinal() ||
                indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.VITAMINE_D.ordinal() ||
                indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.SELENIUM.ordinal() ||
                indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.IODE.ordinal()
        ) {
            text += " μg";
        } else {
            text += " mg";
        }

        return text;
    }

    public String getTextDailyNeed(boolean isDailyNeedInPercent, int valueIndexQuantityFoodByElement, int indexFoodinArrayValuesFood) {
        String text = " - - - ";
        if (TransitionClass.profileActual != null) {
            if (isDailyNeedInPercent) {
                double besoinUtilisateur = TransitionClass.profileActual.getDailyNeedDOUBLEValuesList().get(indexFoodinArrayValuesFood);

                double resultPercent = (double) Math.round(((getValueDoubleElementByIndex(valueIndexQuantityFoodByElement, indexFoodinArrayValuesFood) * 100 / besoinUtilisateur) * 10)) / 10;
                text = resultPercent + " %";
            } else {
                text = TransitionClass.profileActual.getDailyNeedSTRINGlist(context).get(indexFoodinArrayValuesFood);
            }
        }
        return text;
    }

    public double getValueDoubleElementByIndex(int valueIndexEnumQuantityFoodByElement, int indexFoodInArrayValuesFood) {
        return arrayValuesFoodPer100g[indexFoodInArrayValuesFood] * LibraryArrayBitmapDrawingRessources.arrayMultipliarQuantityAmount[valueIndexEnumQuantityFoodByElement];
    }

    public String getTextPlusOuMoinsQueLaValeurMoyenneDeAllFood(int valueIndexEnumQuantityFoodByElement, int indexFoodinArrayValuesFood) {

        String stringToReturn = "---";

        // Pour Calories, Lipides, AcidesGrasSatures, Glucide, Sucre :
        if (               indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal()
                        || indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.LIPIDE.ordinal()
                        || indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.ACIDEGRASSATURES.ordinal()
                        || indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.GLUCIDE.ordinal()
                        || indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.SUCRE.ordinal()
                        || indexFoodinArrayValuesFood == LibraryArrayBitmapDrawingRessources.enumElementsListe.SEL.ordinal())
        {

            if (getValueDoubleElementByIndex(valueIndexEnumQuantityFoodByElement, indexFoodinArrayValuesFood) >= TransitionClass.arrayMoyenneValuesAllFoodElements[indexFoodinArrayValuesFood]){

                stringToReturn = context.getString(R.string.trop);
                valuePouce = LibraryArrayBitmapDrawingRessources.enumValuePouceFood.pouceRouge.ordinal();

            }
            else {
                stringToReturn = context.getString(R.string.bien);
                valuePouce = LibraryArrayBitmapDrawingRessources.enumValuePouceFood.pouceVert.ordinal();
            }


        }

        // Pour les Autres :
        else {

            if (getValueDoubleElementByIndex(valueIndexEnumQuantityFoodByElement, indexFoodinArrayValuesFood) >= TransitionClass.arrayMoyenneValuesAllFoodElements[indexFoodinArrayValuesFood]){

                stringToReturn = context.getString(R.string.bien);
                valuePouce = LibraryArrayBitmapDrawingRessources.enumValuePouceFood.pouceVert.ordinal();

            }
            else {
                stringToReturn = context.getString(R.string.pas_assez);
                valuePouce = LibraryArrayBitmapDrawingRessources.enumValuePouceFood.pouceRouge.ordinal();
            }

        }

        return stringToReturn;

    }

    // tableau String par element de la SOMME des aliments dans liste :
    private void setArrayStringUnitFood() {

        arrayStringUnitValuesFood = new String[arrayValuesFoodPer100g.length];

        for (int i = 0; i < arrayValuesFoodPer100g.length; i++) {
            String text;

            // Calorie :
            if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal()) {
                text = "kCal";
            }
            // entre Calories et VitamineA :
            else if (i < LibraryArrayBitmapDrawingRessources.enumElementsListe.VITAMINE_A.ordinal()) {
                text = "g";
            } else if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.VITAMINE_B12.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.VITAMINE_D.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SELENIUM.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.IODE.ordinal()) {
                text = "μg";
            } else if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.POTASSIUM.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SODIUM.ordinal()) {
                text = "g";
            } else if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.EAU.ordinal()) {
                text = "mL";
            } else {
                text = "mg";
            }

            arrayStringUnitValuesFood[i] = text;
        }

        arrayStringUnitValuesFoodStatic = new String[arrayStringUnitValuesFood.length];
        arrayStringUnitValuesFoodStatic = arrayStringUnitValuesFood;
    }

    // ---------------------------------------------------------------------------------------------
    // Getter Setter :

    public int getValuePouce(){return valuePouce;}

    public int getfoodID() {
        return foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setADDQuantity(int aquantity) {

        quantityID = FoodSelectorActivityV2.enumQuantityChosenAmount.SPECIFIC.geti();
        this.quantity += aquantity;
    }

    public boolean isOnDropZone() {
        return isOnDropZone;
    }

    public void setOnDropZone(boolean onDropZone) {
        isOnDropZone = onDropZone;
    }

    public int getIndexOnTemporaryList() {
        return indexOnTemporaryList;
    }

    public void setIndexOnTemporaryList(int indexOnTemporaryList) {
        this.indexOnTemporaryList = indexOnTemporaryList;
    }

    // --------------------------------------------------------------------------------------------
    // Order Methods :

    // Comparaison ordre croissant

    public static Comparator<FoodClass> comparatorName = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return f1.name.compareTo(f2.name);
        }
    };

    public static Comparator<FoodClass> comparatorCategoryName = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return f1.categoryName.compareTo(f2.categoryName);
        }

    };

    public static Comparator<FoodClass> comparatorCategory = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f1.category - f2.category);
        }
    };

    public static Comparator<FoodClass> comparatorCaloriesCroissant = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f1.calories * 1000 - f2.calories * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorCaloriesMultiplyByQuantityCroissant = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f1.calories * f1.quantity * 1000 - f2.calories * f2.quantity * 1000);
        }
    };

    // Comparaison ordre decroissant

    public static Comparator<FoodClass> comparatorIndexOnTemporaryList = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.indexOnTemporaryList - f1.indexOnTemporaryList);
        }
    };

    public static Comparator<FoodClass> comparatorCalories = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.calories * 1000 - f1.calories * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorCaloriesMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.calories * f2.quantity * 1000 - f1.calories * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorPH = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.acidityPH * 1000 - f1.acidityPH * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorProteine = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.proteine * 1000 - f1.proteine * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorProteineMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.proteine * f2.quantity * 1000 - f1.proteine * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorGlucide = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.glucide * 1000 - f1.glucide * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorGlucideMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.glucide * f2.quantity * 1000 - f1.glucide * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorSucre = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.sucre * 1000 - f1.sucre * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorSucreMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.sucre * f2.quantity * 1000 - f1.sucre * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorFibre = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.fibre * 1000 - f1.fibre * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorFibreMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.fibre * f2.quantity * 1000 - f1.fibre * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorLipide = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.lipide * 1000 - f1.lipide * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorLipideMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.lipide * f2.quantity * 1000 - f1.lipide * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorAcideGrasSature = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.acideGrasSature * 1000 - f1.acideGrasSature * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorAcideGrasSatureMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.acideGrasSature * f2.quantity * 1000 - f1.acideGrasSature * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorSel = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.sel * 1000 - f1.sel * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorSelMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.sel * f2.quantity * 1000 - f1.sel * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineA = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineA * 1000 - f1.vitamineA * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineAMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineA * f2.quantity * 1000 - f1.vitamineA * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB1 = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB1 * 1000 - f1.vitamineB1 * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB1MultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB1 * f2.quantity * 1000 - f1.vitamineB1 * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB2 = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB2 * 1000 - f1.vitamineB2 * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB2MultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB2 * f2.quantity * 1000 - f1.vitamineB2 * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB3 = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB3 * 1000 - f1.vitamineB3 * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB3MultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB3 * f2.quantity * 1000 - f1.vitamineB3 * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB5 = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB5 * 1000 - f1.vitamineB5 * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB5MultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB5 * f2.quantity * 1000 - f1.vitamineB5 * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB6 = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB6 * 1000 - f1.vitamineB6 * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB6MultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB6 * f2.quantity * 1000 - f1.vitamineB6 * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB9 = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB9 * 100000 - f1.vitamineB9 * 100000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB9MultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB9 * f2.quantity * 1000 - f1.vitamineB9 * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB12 = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB12 * 100000 - f1.vitamineB12 * 100000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineB12MultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineB12 * f2.quantity * 1000 - f1.vitamineB12 * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineC = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineC * 1000 - f1.vitamineC * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineCMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineC * f2.quantity * 1000 - f1.vitamineC * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineD = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineD * 1000 - f1.vitamineD * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineDMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineD * f2.quantity * 1000 - f1.vitamineD * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineE = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineE * 1000 - f1.vitamineE * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorVitamineEMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.vitamineE * f2.quantity * 1000 - f1.vitamineE * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorEau = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.eau * 1000 - f1.eau * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorEauMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.eau * f2.quantity * 1000 - f1.eau * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorCalcium = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.calcium * 1000 - f1.calcium * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorCalciumMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.calcium * f2.quantity * 1000 - f1.calcium * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorCuivre = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.cuivre * 1000 - f1.cuivre * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorCuivreMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.cuivre * f2.quantity * 1000 - f1.cuivre * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorFer = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.fer * 1000 - f1.fer * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorFerMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.fer * f2.quantity * 1000 - f1.fer * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorIode = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.iode * 1000 - f1.iode * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorIodeMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.iode * f2.quantity * 1000 - f1.iode * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorMagnesium = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.magnesium * 1000 - f1.magnesium * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorMagnesiumMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.magnesium * f2.quantity * 1000 - f1.magnesium * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorManganese = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.manganese * 1000 - f1.manganese * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorManganeseMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.manganese * f2.quantity * 1000 - f1.manganese * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorPhosphore = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.phosphore * 1000 - f1.phosphore * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorPhosphoreMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.phosphore * f2.quantity * 1000 - f1.phosphore * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorPotassium = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.potassium * 1000 - f1.potassium * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorPotassiumMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.potassium * f2.quantity * 1000 - f1.potassium * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorSelenium = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.selenium * 100000 - f1.selenium * 100000);
        }
    };

    public static Comparator<FoodClass> comparatorSeleniumMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.selenium * f2.quantity * 1000 - f1.selenium * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorSodium = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.sodium * 1000 - f1.sodium * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorSodiumMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.sodium * f2.quantity * 1000 - f1.sodium * f1.quantity * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorZinc = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.zinc * 1000 - f1.zinc * 1000);
        }
    };

    public static Comparator<FoodClass> comparatorZincMultiplyByQuantity = new Comparator<FoodClass>() {
        @Override
        public int compare(FoodClass f1, FoodClass f2) {
            return (int) (f2.zinc * f2.quantity * 1000 - f1.zinc * f1.quantity * 1000);
        }
    };

}
