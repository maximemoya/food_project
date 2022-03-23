package fr.maxime.mygame01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fr.maxime.mygame01.Etagere.RepasMakerClass;
import fr.maxime.mygame01.MyObjects.ProfileDailyNeedsClass;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.TransitionClass;

public class LibraryArrayBitmapDrawingRessources {

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    public ArrayList<ProfileDailyNeedsClass> listDailyNeedsProfil = new ArrayList<>();              // liste des profils enregistrés pour DailyNeedActivity

    public ArrayList<RepasMakerClass> repasMakerList = new ArrayList<>();                           // liste de tous les RepasCrees dans la librairie
    public ArrayList<Bitmap> bitmapEmotionList = new ArrayList<>();                                 // liste image Emotion
    public ArrayList<Integer> rawBitmapImageEmotionList = new ArrayList<>();                        // liste int raw image Emotion
    public ArrayList<Integer> soundEmotionList = new ArrayList<>();                                 // liste sons Emotion
    public ArrayList<String> nameEmotionList = new ArrayList<>();                                   // liste nom Emotion

    public ArrayList<Bitmap> bitmapMorphologieList = new ArrayList<>();                             // liste image morphologie

    public ArrayList<Integer> intQuantityFoodImageList = new ArrayList<>();                         // liste integer resource quantity food IMG
    public int[] intIMGDayMomentArray;

    public int[] intIMGPouceFood;

    public String[] comparatorArrayName;

    public ArrayList<FoodClass> bigAllFoodList = new ArrayList<>();                                 // Listes de tous les objets Food créés

    public Double[] arrayMoyenneDoubleValuesAllFoodByElements;                                                 // Tableau de la valeur moyenne par elements de tous les aliments

    public ArrayList<ArrayList<ArrayList<FoodClass>>> globalFoodCategoryList = new ArrayList<>();   // group the all 3 foodCategoryList just above;
    public ArrayList<ArrayList<FoodClass>> foodCategoryAllFoodList = new ArrayList<>();               // group allfood by Categorylist <foodLegumeListLibrary, foodFruitListLibrary , ... ,foodViandeListLibrary ...>
    public ArrayList<ArrayList<FoodClass>> foodCategoryVegetarienFoodList = new ArrayList<>();        // group vegetarienfood by Categorylist <foodLegumeListLibrary, foodFruitListLibrary , ...>
    public ArrayList<ArrayList<FoodClass>> foodCategoryVegetalienFoodList = new ArrayList<>();        // group vegetalienfood by Categorylist <foodLegumeListLibrary, foodFruitListLibrary , ...>
    public ArrayList<ArrayList<FoodClass>> foodCategoryNoPorkAndAlcoholFoodList = new ArrayList<>();  // group sansPorcEtAlcoolfood by Categorylist <foodLegumeListLibrary, foodFruitListLibrary , ...>

    public ArrayList<ArrayList<FoodClass>> globalVisualFoodCategoryList = new ArrayList<>();         // group the all 3 visualfoodCategoryList just above;
    public ArrayList<FoodClass> visualFoodCategoryAllFoodList = new ArrayList<>();                    // group FoodClass represented categories of foodCategoryAllFoodList;
    public ArrayList<FoodClass> visualFoodCategoryVegetarienFoodList = new ArrayList<>();             // group FoodClass represented categories of foodCategoryVegetarienFoodList;
    public ArrayList<FoodClass> visualFoodCategoryVegetalienFoodList = new ArrayList<>();             // group FoodClass represented categories of foodCategoryVegetalienFoodList;
    public ArrayList<FoodClass> visualFoodCategoryNoPorkAndAlcoholFoodList = new ArrayList<>();             // group FoodClass represented categories of foodCategoryVegetalienFoodList;

    public ArrayList<Bitmap> bitmapFoodList = new ArrayList<>();                                    // liste image Food
    public ArrayList<Integer> listeRessourceRawBitmap = new ArrayList<>();                          // liste source int Bitmap
    public ArrayList<String> nameList = new ArrayList<>();                                          // liste nom
    public ArrayList<Integer> categoryList = new ArrayList<>();                                     // cf categoryEnum plus bas;
    public ArrayList<Double> calorieList = new ArrayList<>();                                       // kiloCalories / 100g
    public ArrayList<Double> acidityPHList = new ArrayList<>();                                     // 0  = acide , 7 = neutre, 14 = basique

    public ArrayList<Double> eauPourcentageList = new ArrayList<>();             // en g/100g
    public ArrayList<Double> proteinePourcentageList = new ArrayList<>();        // en g/100g
    public ArrayList<Double> lipidePourcentageList = new ArrayList<>();          // en g/100g
    public ArrayList<Double> acideGrasSaturePourcentageList = new ArrayList<>(); // en g/100g
    public ArrayList<Double> glucidePourcentageList = new ArrayList<>();         // en g/100g
    public ArrayList<Double> sucrePourcentageList = new ArrayList<>();           // en g/100g
    public ArrayList<Double> fibrePourcentageList = new ArrayList<>();           // en g/100g

    public ArrayList<Double> vitamineAPourcentageList = new ArrayList<>();          // en mg/100g
    public ArrayList<Double> vitamineB1PourcentageList = new ArrayList<>();         // en mg/100g
    public ArrayList<Double> vitamineB2PourcentageList = new ArrayList<>();         // en mg/100g
    public ArrayList<Double> vitamineB3PourcentageList = new ArrayList<>();         // en mg/100g
    public ArrayList<Double> vitamineB5PourcentageList = new ArrayList<>();         // en mg/100g
    public ArrayList<Double> vitamineB6PourcentageList = new ArrayList<>();         // en mg/100g
    public ArrayList<Double> vitamineB9PourcentageList = new ArrayList<>();         // en mg/100g
    public ArrayList<Double> vitamineB12PourcentageList = new ArrayList<>();        // en mg/100g
    public ArrayList<Double> vitamineCPourcentageList = new ArrayList<>();          // en mg/100g
    public ArrayList<Double> vitamineDPourcentageList = new ArrayList<>();          // en mg/100g
    public ArrayList<Double> vitamineEPourcentageList = new ArrayList<>();          // en mg/100g

    public ArrayList<Double> calciumPourcentageList = new ArrayList<>();        // conseille = 1g par jour (diviser par deux pour enfant < 6ans)           en mg/100g
    public ArrayList<Double> phosphorePourcentageList = new ArrayList<>();      // conseille = 1g par jour (diviser par deux pour enfant < 6ans)           en mg/100g
    public ArrayList<Double> magnesiumPourcentageList = new ArrayList<>();      // conseille = 1g par jour (diviser par deux pour enfant < 6ans)           en mg/100g
    public ArrayList<Double> potassiumPourcentageList = new ArrayList<>();      // conseille = 1g par jour (diviser par deux pour enfant < 6ans)           en mg/100g
    public ArrayList<Double> sodiumPourcentageList = new ArrayList<>();         // 2g < conseille < 7g par jour (maximum)                                  en mg/100g
    public ArrayList<Double> selPourcentageList = new ArrayList<>();            // sodium X 4                                                              en mg/100g
    public ArrayList<Double> ferPourcentageList = new ArrayList<>();            // conseille = 0,01g par jour (peut doubler voir tripler pour la femme)    en mg/100g
    public ArrayList<Double> cuivrePourcentageList = new ArrayList<>();         // conseille = 0,01g par jour (peut doubler voir tripler pour la femme)    en mg/100g
    public ArrayList<Double> zincPourcentageList = new ArrayList<>();           // conseille = 0,01g par jour (peut doubler voir tripler pour la femme)    en mg/100g
    public ArrayList<Double> manganesePourcentageList = new ArrayList<>();      // conseille = 0,01g par jour (peut doubler voir tripler pour la femme)    en mg/100g
    public ArrayList<Double> seleniumPourcentageList = new ArrayList<>();       // conseille = 0,01g par jour (peut doubler voir tripler pour la femme)    en mg/100g
    public ArrayList<Double> iodePourcentageList = new ArrayList<>();           // conseille = 0,15g par jour pour adulte et 0,1g pour l'enfant < 10ans   /!\ en micro-g/100g /!\

    public enum categoryEnum {
        neutre(0), legumineux(1), fruit(2), feculent(3), proteine(4), sucrerie(5),
        fruitSec(6), plante(7), friture(8), NotUSEDyaourt(9), produit_laitier(10), oeuf(11),
        viande(12), poisson(13), fruitDeMer(14), boisson(15), champignon(16),
        fromage(17), cereales(18), alcool(19), condiment(20);

        categoryEnum(int i) {
        }

        public int index() {
            return this.ordinal();
        }

        public String getName() {
            switch (this.ordinal()) {
                case 0:
                    return context.getString(R.string.neutre);
                case 1:
                    return context.getString(R.string.legumineux);
                case 2:
                    return context.getString(R.string.fruit);
                case 3:
                    return context.getString(R.string.feculent);
                case 4:
                    return context.getString(R.string.proteine);
                case 5:
                    return context.getString(R.string.sucrerie);
                case 6:
                    return context.getString(R.string.fruitSec);
                case 7:
                    return context.getString(R.string.plante);
                case 8:
                    return context.getString(R.string.friture);
                case 9:
                    return context.getString(R.string.yaourt);
                case 10:
                    return context.getString(R.string.produitLaitier);
                case 11:
                    return context.getString(R.string.oeuf);
                case 12:
                    return context.getString(R.string.viande);
                case 13:
                    return context.getString(R.string.poisson);
                case 14:
                    return context.getString(R.string.fruitDeMer);
                case 15:
                    return context.getString(R.string.boisson);
                case 16:
                    return context.getString(R.string.champignon);
                case 17:
                    return context.getString(R.string.fromage);
                case 18:
                    return context.getString(R.string.cereales);
                case 19:
                    return context.getString(R.string.alcool);
                case 20:
                    return context.getString(R.string.assaisonnement);
                default:
                    return context.getString(R.string.neutre);
            }
        }
    }

    public enum enumElementsListe {
        CALORIES, LIPIDE, ACIDEGRASSATURES, GLUCIDE, SUCRE, FIBRE, PROTEINE, SEL,
        VITAMINE_A, VITAMINE_B1, VITAMINE_B2, VITAMINE_B3, VITAMINE_B5, VITAMINE_B6, VITAMINE_B9, VITAMINE_B12, VITAMINE_C, VITAMINE_D, VITAMINE_E,
        EAU, CALCIUM, CUIVRE, FER, IODE, MAGNESIUM, MANGANESE, PHOSPHORE, POTASSIUM, SELENIUM, SODIUM, ZINC;
    }

    public enum enumGlobalComparatorAttributFoodClass {
        NAME, CATEGORY, CALORIES, LIPIDE, ACIDEGRASSATURES, GLUCIDE, SUCRE, FIBRE, PROTEINE, SEL,
        VITAMINE_A, VITAMINE_B1, VITAMINE_B2, VITAMINE_B3, VITAMINE_B5, VITAMINE_B6, VITAMINE_B9, VITAMINE_B12, VITAMINE_C, VITAMINE_D, VITAMINE_E,
        EAU, CALCIUM, CUIVRE, FER, IODE, MAGNESIUM, MANGANESE, PHOSPHORE, POTASSIUM, SELENIUM, SODIUM, ZINC;

        @RequiresApi(api = Build.VERSION_CODES.N)
        static public Comparator<FoodClass> comparator(int i) {
            if (i == NAME.ordinal()) {
                return FoodClass.comparatorName.thenComparing(FoodClass.comparatorCalories);
            } else if (i == CATEGORY.ordinal()) {
                return FoodClass.comparatorCategoryName.thenComparing(FoodClass.comparatorName);
            } else if (i == CALORIES.ordinal()) {
                return FoodClass.comparatorCalories.thenComparing(FoodClass.comparatorName);
            } else if (i == LIPIDE.ordinal()) {
                return FoodClass.comparatorLipide.thenComparing(FoodClass.comparatorName);
            } else if (i == ACIDEGRASSATURES.ordinal()) {
                return FoodClass.comparatorAcideGrasSature.thenComparing(FoodClass.comparatorName);
            } else if (i == GLUCIDE.ordinal()) {
                return FoodClass.comparatorGlucide.thenComparing(FoodClass.comparatorName);
            } else if (i == SUCRE.ordinal()) {
                return FoodClass.comparatorSucre.thenComparing(FoodClass.comparatorName);
            } else if (i == FIBRE.ordinal()) {
                return FoodClass.comparatorFibre.thenComparing(FoodClass.comparatorName);
            } else if (i == PROTEINE.ordinal()) {
                return FoodClass.comparatorProteine.thenComparing(FoodClass.comparatorName);
            } else if (i == SEL.ordinal()) {
                return FoodClass.comparatorSel.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_A.ordinal()) {
                return FoodClass.comparatorVitamineA.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_B1.ordinal()) {
                return FoodClass.comparatorVitamineB1.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_B2.ordinal()) {
                return FoodClass.comparatorVitamineB2.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_B3.ordinal()) {
                return FoodClass.comparatorVitamineB3.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_B5.ordinal()) {
                return FoodClass.comparatorVitamineB5.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_B6.ordinal()) {
                return FoodClass.comparatorVitamineB6.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_B9.ordinal()) {
                return FoodClass.comparatorVitamineB9.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_B12.ordinal()) {
                return FoodClass.comparatorVitamineB12.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_C.ordinal()) {
                return FoodClass.comparatorVitamineC.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_D.ordinal()) {
                return FoodClass.comparatorVitamineD.thenComparing(FoodClass.comparatorName);
            } else if (i == VITAMINE_E.ordinal()) {
                return FoodClass.comparatorVitamineE.thenComparing(FoodClass.comparatorName);
            } else if (i == EAU.ordinal()) {
                return FoodClass.comparatorEau.thenComparing(FoodClass.comparatorName);
            } else if (i == CALCIUM.ordinal()) {
                return FoodClass.comparatorCalcium.thenComparing(FoodClass.comparatorName);
            } else if (i == CUIVRE.ordinal()) {
                return FoodClass.comparatorCuivre.thenComparing(FoodClass.comparatorName);
            } else if (i == FER.ordinal()) {
                return FoodClass.comparatorFer.thenComparing(FoodClass.comparatorName);
            } else if (i == IODE.ordinal()) {
                return FoodClass.comparatorIode.thenComparing(FoodClass.comparatorName);
            } else if (i == MAGNESIUM.ordinal()) {
                return FoodClass.comparatorMagnesium.thenComparing(FoodClass.comparatorName);
            } else if (i == MANGANESE.ordinal()) {
                return FoodClass.comparatorManganese.thenComparing(FoodClass.comparatorName);
            } else if (i == PHOSPHORE.ordinal()) {
                return FoodClass.comparatorPhosphore.thenComparing(FoodClass.comparatorName);
            } else if (i == POTASSIUM.ordinal()) {
                return FoodClass.comparatorPotassium.thenComparing(FoodClass.comparatorName);
            } else if (i == SELENIUM.ordinal()) {
                return FoodClass.comparatorSelenium.thenComparing(FoodClass.comparatorName);
            } else if (i == SODIUM.ordinal()) {
                return FoodClass.comparatorSodium.thenComparing(FoodClass.comparatorName);
            } else if (i == ZINC.ordinal()) {
                return FoodClass.comparatorZinc.thenComparing(FoodClass.comparatorName);
            } else {
                return null;
            }
        }
    }

    public enum morphologieEnum {
        FEMMEFINE, FEMMEMOYENNE, FEMMESOLIDE, FEMMEFINESELECTED, FEMMEMOYENNESELECTED, FEMMESOLIDESELECTED,
        HOMMEFIN, HOMMEMOYEN, HOMMESOLIDE, HOMMEFINSELECTED, HOMMEMOYENSELECTED, HOMMESOLIDESELECTED;
    }

    public enum emotionEnum {
        AMICAL, AMOUR, COLERE, CONTENT1, CONTENT2, DECEPTION, DEGOUTER, FARCEUR, INTERET, MACHIAVELIQUE,
        MORT, NEUTRE, PLEURER, RIRE, SOURIRE, STRESS, TAQUIN, TENDRESSE, TRISTESSE, VEXER;
    }

    public enum enumActivityBackUpByCancel {
        MENU, GAME, FOODSELECTORV2, RESULTFOODSELECTORV2NUTRISCORE,
        RESULTFOODSELECTORV2NUTRISCOREGLOBALELEMENTS, FOODLIST, RESULTSUIVIREPAS, PROFILEMENU,
        DAILYNEEDACTIVITY, TESTREGEX;
    }

    public enum enumQuantityFoodIntResList {
        SMALL, MEDIUM, BIG, SMALLSELECTED, MEDIUMSELECTED, BIGSELECTED;
    }

    public enum enumTypeOfFood {

        SANSRESTRICTION, VEGETARIEN, VEGAN, SANSPORCETALCOOL;

    }

    public enum enumValuePouceFood {pouceVert, pouceRouge}

    // dans FoodDescriptionV2 :

    public static String[] getdescriptionFoodArrayElements(Context context) {
        String[] descriptionFoodArrayElements = new String[]{
                context.getResources().getString(R.string.text_calories),
                context.getResources().getString(R.string.text_lipide),
                context.getResources().getString(R.string.text_acideGrasSature),
                context.getResources().getString(R.string.text_glucide),
                context.getResources().getString(R.string.text_sucre),
                context.getResources().getString(R.string.text_fibre),
                context.getResources().getString(R.string.text_proteine),
                context.getResources().getString(R.string.text_sel),
                context.getResources().getString(R.string.text_vitamineA),
                context.getResources().getString(R.string.text_vitamineB1),
                context.getResources().getString(R.string.text_vitamineB2),
                context.getResources().getString(R.string.text_vitamineB3),
                context.getResources().getString(R.string.text_vitamineB5),
                context.getResources().getString(R.string.text_vitamineB6),
                context.getResources().getString(R.string.text_vitamineB9),
                context.getResources().getString(R.string.text_vitamineB12),
                context.getResources().getString(R.string.text_vitamineC),
                context.getResources().getString(R.string.text_vitamineD),
                context.getResources().getString(R.string.text_vitamineE),
                context.getResources().getString(R.string.text_eau),
                context.getResources().getString(R.string.text_calcium),
                context.getResources().getString(R.string.text_cuivre),
                context.getResources().getString(R.string.text_fer),
                context.getResources().getString(R.string.text_iode),
                context.getResources().getString(R.string.text_magnesium),
                context.getResources().getString(R.string.text_manganese),
                context.getResources().getString(R.string.text_phosphore),
                context.getResources().getString(R.string.text_potassium),
                context.getResources().getString(R.string.text_selenium),
                context.getResources().getString(R.string.text_sodium),
                context.getResources().getString(R.string.text_zinc),
        };
        return descriptionFoodArrayElements;
    }

    public enum enumValueIndexQuantityFoodByElement {
        UNEPORTION50gr, UNEPORTION60gr, UNEPORTION125gr, UN100gr, PETITEASSIETTE200gr, GRANDEASSIETTE400gr;
    }

    public static final double[] arrayMultipliarQuantityAmount = {0.5, 0.6, 1.25, 1.0, 2.0, 4.0};

    public enum enumArrayMultipliarAmount {
        UNEPORTION50gr, UNEPORTION60gr, UNEPORTION125gr, UN100gr, PETITEASSIETTE200gr, GRANDEASSIETTE400gr;
    }

    //----------------------------------------------------------------------------------------------
    // CONSTRUCTOR :
    public LibraryArrayBitmapDrawingRessources(Context context) {

        // Tableau textes caracteristiques pour comparator :

        comparatorArrayName = new String[]
                {
                        context.getResources().getString(R.string.text_name),
                        context.getResources().getString(R.string.text_category),
                        context.getResources().getString(R.string.text_calories),
                        context.getResources().getString(R.string.text_lipide),
                        context.getResources().getString(R.string.text_acideGrasSature),
                        context.getResources().getString(R.string.text_glucide),
                        context.getResources().getString(R.string.text_sucre),
                        context.getResources().getString(R.string.text_fibre),
                        context.getResources().getString(R.string.text_proteine),
                        context.getResources().getString(R.string.text_sel),
                        context.getResources().getString(R.string.text_vitamineA), context.getResources().getString(R.string.text_vitamineB1),
                        context.getResources().getString(R.string.text_vitamineB2), context.getResources().getString(R.string.text_vitamineB3),
                        context.getResources().getString(R.string.text_vitamineB5), context.getResources().getString(R.string.text_vitamineB6),
                        context.getResources().getString(R.string.text_vitamineB9), context.getResources().getString(R.string.text_vitamineB12),
                        context.getResources().getString(R.string.text_vitamineC), context.getResources().getString(R.string.text_vitamineD),
                        context.getResources().getString(R.string.text_vitamineE),
                        context.getResources().getString(R.string.text_eau),
                        context.getResources().getString(R.string.text_calcium),
                        context.getResources().getString(R.string.text_cuivre), context.getResources().getString(R.string.text_fer),
                        context.getResources().getString(R.string.text_iode), context.getResources().getString(R.string.text_magnesium),
                        context.getResources().getString(R.string.text_manganese), context.getResources().getString(R.string.text_phosphore),
                        context.getResources().getString(R.string.text_potassium), context.getResources().getString(R.string.text_selenium),
                        context.getResources().getString(R.string.text_sodium),
                        context.getResources().getString(R.string.text_zinc)};

        // Creation Food :

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.abricot, context.getString(R.string.abricot), categoryEnum.fruit.index(),
                3.5, 49.0, 0.9, 9.0, 8.0, 2.0, 0.2, 0.0, 86.0,
                0.0, 5.5, 0.0, 0.6, 0.0, 0.0, 0.5, 0.2, 0.1, 0.0062, 0.0,
                15.6, 16.6, 8.7, 237.0, 2.2, 0.3, 0.1, 0.1, 0.2, 0.0, 0.3);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.ail, context.getString(R.string.ail), categoryEnum.legumineux.index(),
                7.0, 131.0, 7.9, 21.5, 1.6, 5.0, 0.5, 0.1, 64.0,
                0.0, 17.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.6, 1.2, 0.005, 0.0,
                17.7, 161.0, 20.7, 555.0, 17.6, 1.3, 0.1, 0.8, 0.4, 0.0051, 3.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.amande, context.getString(R.string.amande), categoryEnum.fruitSec.index(),
                7.0, 634.0, 25.4, 1.5, 1.1, 15.0, 53.4, 4.0, 4.0,
                0.0, 0.5, 0.0, 14.6, 0.2, 0.9, 3.4, 0.3, 0.1, 0.0931, 0.0,
                248.0, 416.0, 232.0, 668.0, 1.6, 3.0, 1.1, 3.3, 2.3, 0.0022, 5.7);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.ananas, context.getString(R.string.ananas), categoryEnum.fruit.index(),
                4.0, 53.2, 0.4, 11.0, 9.2, 2.0, 0.2, 0.0, 86.0,
                0.0, 12.0, 0.0, 0.1, 0.1, 0.0, 0.3, 0.2, 0.1, 0.0135, 0.0,
                20.3, 11.0, 19.8, 170.0, 2.9, 0.2, 0.1, 0.7, 2.0, 0.0003, 1.2);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.artichaud, context.getString(R.string.artichaud), categoryEnum.legumineux.index(),
                5.6, 44.6, 2.8, 4.9, 1.4, 5.0, 0.2, 0.0, 85.0,
                0.0, 10.3, 0.0, 0.2, 0.1, 0.1, 1.0, 0.3, 0.1, 0.068, 0.0,
                39.0, 49.2, 29.5, 380.0, 56.6, 0.7, 0.1, 0.5, 0.2, 0.0, 1.6);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.asperge, context.getString(R.string.asperge), categoryEnum.legumineux.index(),
                5.0, 30.0, 2.7, 3.2, 0.8, 2.0, 0.3, 0.1, 92.0,
                0.0, 16.0, 0.0, 0.0, 0.1, 0.1, 1.1, 0.2, 0.0, 0.0765, 0.0,
                19.9, 51.5, 6.3, 198.0, 8.5, 0.7, 0.1, 0.4, 0.1, 0.0022, 0.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.aubergine, context.getString(R.string.aubergine), categoryEnum.legumineux.index(),
                5.0, 35.0, 0.8, 6.3, 3.2, 3.0, 0.2, 0.0, 90.0,
                0.0, 1.3, 0.0, 0.0, 0.1, 0.0, 0.6, 0.1, 0.1, 0.014, 0.0,
                20.1, 15.0, 15.0, 123.0, 158.0, 0.3, 0.1, 0.1, 0.1, 0.0022, 1.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.avocat, context.getString(R.string.avocat), categoryEnum.fruit.index(),
                6.5, 169.0, 1.8, 3.1, 1.0, 5.0, 16.0, 2.3, 74.0,
                0.0, 7.5, 0.0, 2.4, 0.1, 0.2, 1.3, 0.9, 0.2, 0.0785, 0.0,
                10.8, 41.9, 27.1, 412.0, 38.8, 0.5, 0.53, 0.5, 0.36, 0.005, 1.5);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.banane_v2, context.getString(R.string.banane), categoryEnum.fruit.index(),
                6.5, 94.0, 1.2, 20.5, 15.9, 3.1, 0.2, 0.1, 75.0,
                0.0, 6.5, 0.0, 0.3, 0.0, 0.1, 0.7, 0.4, 0.3, 0.0229, 0.0,
                4.5, 17.5, 32.8, 411.0, 1.0, 0.3, 0.1, 0.2, 0.6, 0.0004, 3.9);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.betterave, context.getString(R.string.betterave), categoryEnum.legumineux.index(),
                6.5, 43.0, 2.3, 7.2, 6.7, 2.0, 0.1, 0.0, 87.0,
                0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.1, 0.0, 0.074, 0.0,
                18.4, 31.1, 16.3, 266.0, 54.1, 0.7, 0.1, 0.3, 0.5, 0.0003, 0.3);

        // Beurre 70%MatGrasse en pain :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.beurre, context.getString(R.string.beurre), categoryEnum.produit_laitier.index(),
                6.5, 634.0, 0.1, 0.0, 0.0, 0.0, 70.4, 33.4, 28.0,
                0.0, 2.4, 0.0, 10.0, 0.05, 0.05, 0.05, 0.05, 0.05, 0.005, 0.0002,
                6.9, 6.6, 2.0, 13.5, 276.0, 0.8, 0.8, 0.5, 0.5, 0.003, 10.0,
                true, false);

        // biscuit SEC :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.biscuit, context.getString(R.string.biscuit), categoryEnum.sucrerie.index(),
                7.0, 475.0, 6.5, 72.2, 42.6, 3.0, 12.8, 4.3, 4.0,
                0.0, 0.0, 0.004, 1.4, 0.1, 0.1, 0.7, 0.3, 0.0, 0.014, 0.0001,
                10.0, 190.0, 14.0, 157.0, 255.0, 0.4, 0.1, 0.6, 0.9, 0.024, 4.2);

        // BOISSONS Fraiches:
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.boisson_energetique, context.getString(R.string.boisson_energetique), categoryEnum.boisson.index(),
                4.5, 45.0, 0.3, 10.2, 10.1, 0.0, 0.0, 0.0, 88.5,
                0.0, 0.0, 0.0, 0.0, 0.1, 0.6, 8.5, 1.9, 2.0, 0.0, 0.002,
                10.0, 0.0, 3.0, 3.0, 40.0, 0.05, 0.05, 0.0, 0.05, 0.01, 0.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.eau, context.getString(R.string.eau_minerale), categoryEnum.boisson.index(),
                7.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 99.9,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                23.4, 0.0, 6.1, 0.2, 0.8, 0.0, 0.0, 0.0, 0.0, 0.0005, 0.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.soda, context.getString(R.string.soda), categoryEnum.boisson.index(),
                4.5, 41.0, 0.0, 10.0, 10.0, 0.0, 0.0, 0.0, 90.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                3.0, 13.0, 2.4, 1.5, 5.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.4);

        // brioche Boulangerie :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.brioche, context.getString(R.string.brioche), categoryEnum.sucrerie.index(),
                7.0, 375.0, 9.6, 47.4, 10.0, 3.0, 15.8, 8.5, 23.0,
                0.061, 0.5, 0.0002, 0.7, 0.1, 0.2, 0.7, 0.7, 0.09, 0.0931, 0.0005,
                164.0, 124.0, 22.2, 185.0, 528.0, 1.17, 0.1, 0.7, 0.3, 0.0192, 10.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.brocoli, context.getString(R.string.brocoli), categoryEnum.legumineux.index(),
                5.4, 29.0, 2.1, 2.8, 1.4, 2.0, 0.5, 0.1, 92.0,
                0.0, 37.3, 0.0, 1.0, 0.05, 0.1, 0.1, 0.5, 0.23, 0.102, 0.0,
                55.8, 56.0, 11.5, 148.0, 53.0, 1.0, 0.198, 0.4, 0.284, 0.0022, 2.0);

        // cafe noir non sucre :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.cafe, context.getString(R.string.cafe), categoryEnum.boisson.index(),
                7.0, 0.5, 0.1, 0.1, 0.1, 0.0, 0.0, 0.0, 99.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.2, 0.1, 0.0, 0.0008, 0.0,
                99.2, 2.7, 12.0, 119.0, 1.9, 0.0, 0.3, 0.08, 0.1, 0.010, 0.6);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.cafe_latte, context.getString(R.string.cafeLatte), categoryEnum.produit_laitier.index(),
                7.0, 25.0, 1.7, 2.7, 2.3, 0.0, 0.8, 0.5, 94.0,
                0.0095, 1.3, 0.0, 0.0, 1.3, 0.1, 0.1, 0.3, 0.0, 0.0028, 0.0,
                62.2, 42.6, 8.5, 108.0, 23.4, 0.1, 0.0, 0.2, 0.0, 0.006, 5.6,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.calamard, context.getString(R.string.calamar), categoryEnum.fruitDeMer.index(),
                7.0, 80.0, 16.0, 0.6, 0.0, 0.0, 1.5, 0.3, 82.0,
                0.015, 0.0, 0.00056, 1.4, 0.1, 0.12, 1.8, 0.3, 0.2, 0.013, 0.0022,
                15.7, 170.0, 42.0, 230.0, 195.0, 0.3, 0.98, 1.2, 0.0, 0.0258, 14.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.carotte, context.getString(R.string.carotte), categoryEnum.legumineux.index(),
                5.0, 36.4, 0.8, 6.6, 4.8, 2.6, 0.3, 0.1, 89.0,
                0.0, 4.0, 0.0, 0.6, 0.1, 0.0, 1.0, 0.3, 0.1, 0.052, 0.0,
                26.2, 20.4, 11.9, 243.0, 49.0, 0.3, 0.0, 0.2, 0.1, 0.00133, 1.3);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.cereales, context.getString(R.string.cereales), categoryEnum.cereales.index(),
                7.0, 391.0, 6.2, 81.8, 27.0, 3.0, 3.7, 1.6, 3.0,
                0.0, 43.4, 0.0002, 0.8, 1.3, 1.4, 16.1, 4.8, 1.8, 0.185, 0.0009,
                201.0, 94.1, 30.5, 262.0, 441.0, 7.8, 0.2, 0.7, 0.3, 0.0015, 8.9);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.cereales_muesli_nature, context.getString(R.string.cereales_muesli_nature), categoryEnum.cereales.index(),
                7.0, 352.0, 11.6, 58.3, 1.3, 10.7, 5.7, 0.8, 2.0,
                0.0, 0.5, 0.0002, 1.8, 0.8, 0.6, 2.5, 0.8, 1.3, 0.100, 0.0008,
                143.0, 200.0, 60.5, 362.0, 100.0, 5.0, 0.5, 1.5, 1.3, 0.0085, 4.9);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.cereales_muesli, context.getString(R.string.cereales_muesli), categoryEnum.cereales.index(),
                7.0, 390.4, 11.3, 61.8, 16.8, 8.0, 7.6, 1.1, 9.0,
                0.0, 0.5, 0.0004, 4.6, 0.7, 0.3, 2.1, 0.6, 1.1, 0.0967, 0.0001,
                143.0, 315.0, 74.5, 460.0, 68.8, 3.2, 0.4, 1.9, 1.6, 0.017, 2.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.cerise, context.getString(R.string.cerise), categoryEnum.fruit.index(),
                3.5, 71.0, 1.3, 14.2, 12.4, 2.0, 0.3, 0.0, 81.0,
                0.0, 9.3, 0.0, 0.2, 0.0, 0.0, 0.3, 0.2, 0.0, 0.034, 0.0,
                15.4, 20.2, 13.0, 217.0, 5.2, 0.2, 0.1, 0.1, 0.1, 0.0, 1.4);

        // champignon de Paris
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.champignon, context.getString(R.string.champignon), categoryEnum.champignon.index(),
                6.2, 25.0, 1.8, 2.7, 0.2, 1.0, 0.4, 0.1, 93.0,
                0.0, 1.0, 0.0, 0.1, 0.1, 0.3, 3.2, 2.0, 0.2, 0.0335, 0.0,
                28.5, 56.4, 8.6, 326.0, 16.9, 1.7, 0.5, 0.4, 0.1, 0.0056, 2.0);

        // boisson lactee cacao
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.chocolat_chaud, context.getString(R.string.chocolatChaud), categoryEnum.boisson.index(),
                7.0, 77.0, 3.5, 10.0, 9.7, 1.0, 2.3, 1.4, 83.0,
                0.051, 0.2, 0.0011, 0.0, 0.0, 0.2, 0.1, 0.3, 0.0, 0.005, 0.0005,
                114.0, 105.0, 23.0, 197.0, 44.0, 0.4, 0.1, 0.6, 0.0, 0.0004, 22.0,
                true, false);

        // chou blanc
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.choux, context.getString(R.string.choux), categoryEnum.legumineux.index(),
                5.5, 29.0, 1.3, 4.8, 1.5, 2.0, 0.2, 0.0, 92.0,
                0.0, 35.5, 0.0, 0.0, 0.0, 0.0, 0.4, 0.2, 0.2, 0.027, 0.0,
                50.0, 29.6, 11.9, 237.0, 13.0, 0.4, 0.0, 0.1, 0.2, 0.0, 0.7);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.choux_bruxelles, context.getString(R.string.chouxBruxelle), categoryEnum.legumineux.index(),
                6.0, 41.0, 2.6, 6.9, 1.7, 3.2, 0.1, 0.0, 88.0,
                0.0, 58.2, 0.0, 0.9, 0.05, 0.1, 0.7, 0.3, 0.1, 0.11, 0.0,
                36.2, 65.0, 16.9, 324.0, 9.7, 0.1, 0.0, 0.3, 0.2, 0.0, 1.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.citron, context.getString(R.string.citron), categoryEnum.fruit.index(),
                2.2, 34.0, 0.85, 2.5, 2.2, 2.0, 0.3, 0.1, 89.0,
                0.0, 53.0, 0.0, 0.8, 0.1, 0.0, 0.2, 0.2, 0.1, 0.011, 0.0,
                18.0, 15.5, 8.9, 149.0, 3.0, 0.4, 0.0, 0.1, 0.0, 0.0049, 0.9);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.citron_vert, context.getString(R.string.citronVert), categoryEnum.fruit.index(),
                1.8, 36.0, 0.7, 2.8, 1.7, 3.0, 0.2, 0.0, 88.0,
                0.0, 31.7, 0.0, 0.4, 0.0, 0.0, 0.2, 0.2, 0.0, 0.026, 0.0,
                33.0, 18.0, 6.7, 102.0, 2.0, 0.6, 0.1, 0.1, 0.0, 0.0, 0.8);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.coing, context.getString(R.string.coing), categoryEnum.fruit.index(),
                3.2, 58.0, 0.4, 11.2, 6.3, 6.4, 0.4, 0.0, 84.0,
                0.0, 15.0, 0.0, 0.6, 0.0, 0.0, 0.4, 0.1, 0.0, 0.0009, 0.0,
                8.7, 16.0, 8.0, 198.0, 3.7, 0.5, 0.1, 0.3, 0.0, 0.0, 0.4);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.concombre_v2, context.getString(R.string.concombre), categoryEnum.legumineux.index(),
                5.3, 13.6, 0.6, 2.0, 1.3, 1.0, 0.2, 0.035, 96.0,
                0.0075, 3.75, 0.0, 0.1, 0.05, 0.0, 0.1, 0.15, 0.0, 0.012, 0.0,
                14.0, 21.0, 12.0, 154.0, 4.3, 0.2, 0.0, 0.1, 0.1, 0.0, 4.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.courgette, context.getString(R.string.courgette), categoryEnum.legumineux.index(),
                6.0, 20.0, 1.3, 2.5, 1.9, 1.0, 0.35, 0.05, 94.0,
                0.028, 21.0, 0.0, 1.1, 0.1, 0.1, 0.4, 0.2, 0.2, 0.051, 0.0,
                19.0, 35.5, 26.0, 244.0, 2.8, 0.8, 0.1, 0.3, 0.1, 0.0007, 1.3);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.crabe_v2, context.getString(R.string.crabe), categoryEnum.fruitDeMer.index(),
                7.0, 128.0, 19.3, 0.5, 0.0, 0.0, 5.4, 0.7, 71.0,
                0.0, 0.0, 0.0, 0.0, 0.1, 0.9, 1.5, 1.0, 0.2, 0.02, 0.0,
                15.0, 343.0, 54.7, 257.0, 402.0, 1.6, 1.5, 4.3, 0.1, 0.017, 100.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.crevette, context.getString(R.string.crevette), categoryEnum.fruitDeMer.index(),
                7.0, 94.0, 21.4, 0.0, 0.0, 0.0, 0.9, 0.1, 75.0,
                0.0, 0.0, 0.0, 1.0, 0.0, 0.1, 1.0, 0.3, 0.0, 0.0067, 0.005,
                225.0, 159.0, 59.7, 246.0, 691.0, 2.1, 0.8, 1.8, 0.1, 0.05, 30.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.crevette_pane, context.getString(R.string.crevettePane), categoryEnum.friture.index(),
                7.0, 342.0, 8.6, 33.1, 4.0, 2.0, 19.1, 0.1, 35.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                77.0, 0.0, 18.1, 101.0, 400.0, 1.0, 0.0, 0.5, 0.0, 0.0417, 22.0,
                true, false);

        // croissant au beurre
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.croissant, context.getString(R.string.croissant), categoryEnum.sucrerie.index(),
                7.0, 437.0, 7.8, 42.7, 5.2, 3.0, 25.1, 16.8, 20.0,
                0.197, 0.2, 0.0007, 0.9, 0.1, 0.1, 1.2, 0.4, 0.2, 0.045, 0.0001,
                49.0, 66.0, 18.8, 146.0, 471.0, 0.8, 0.121, 0.7, 0.4, 0.0022, 0.040,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.dattes, context.getString(R.string.dattes), categoryEnum.fruitSec.index(),
                6.4, 282.0, 2.7, 62.5, 58.1, 8.0, 0.4, 0.1, 24.0,
                0.0, 1.5, 0.0, 0.5, 0.1, 0.1, 1.7, 0.8, 0.2, 0.034, 0.0,
                44.9, 65.0, 47.2, 750.0, 18.2, 2.7, 0.2, 0.2, 0.3, 0.005, 1.4);

        // endive cuite
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.endive, context.getString(R.string.endive), categoryEnum.legumineux.index(),
                7.0, 18.0, 0.6, 2.7, 0.5, 1.0, 0.3, 0.05, 95.0,
                0.0, 1.0, 0.0, 0.0, 0.1, 0.05, 0.05, 0.2, 0.05, 0.0225, 0.0,
                42.2, 25.0, 12.0, 110.0, 120.0, 0.4, 0.1, 0.2, 0.2, 0.0022, 0.1);

        // epinard cuit
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.epinard, context.getString(R.string.epinard), categoryEnum.legumineux.index(),
                6.0, 27.0, 3.0, 1.9, 0.4, 3.0, 0.1, 0.0, 91.0,
                0.0, 5.1, 0.0, 2.1, 0.05, 0.1, 0.2, 0.05, 0.1, 0.122, 0.0,
                141.0, 40.0, 53.0, 390.0, 90.7, 2.1, 0.13, 0.873, 0.717, 0.001, 2.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.figues, context.getString(R.string.figue), categoryEnum.fruit.index(),
                4.6, 67.0, 1.3, 13.4, 12.3, 2.0, 0.3, 0.0, 82.0,
                0.0, 2.0, 0.0, 0.6, 0.0, 0.0, 0.4, 0.2, 0.1, 0.0073, 0.0,
                38.0, 15.0, 15.0, 200.0, 3.0, 0.3, 0.1, 0.3, 0.1, 0.0, 1.5);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.fraise, context.getString(R.string.fraise), categoryEnum.fruit.index(),
                3.3, 29.0, 0.8, 4.1, 4.1, 2.0, 0.3, 0.0, 92.0,
                0.0, 67.0, 0.0, 0.2, 0.0, 0.0, 0.6, 0.3, 0.1, 0.043, 0.0,
                14.9, 23.6, 13.0, 165.0, 2.6, 0.3, 0.0, 0.1, 0.3, 0.0002, 5.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.framboise, context.getString(R.string.framboise), categoryEnum.fruit.index(),
                3.5, 45.0, 1.4, 4.3, 4.3, 7.0, 0.3, 0.0, 85.0,
                0.0, 25.1, 0.0, 0.5, 0.0, 0.2, 0.5, 0.3, 0.1, 0.0365, 0.0,
                20.6, 24.4, 22.4, 201.0, 3.5, 0.7, 0.1, 0.3, 0.5, 0.0007, 0.5);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.frites, context.getString(R.string.frites), categoryEnum.friture.index(),
                6.5, 254.0, 4.6, 30.0, 3.5, 4.0, 12.0, 4.1, 47.0,
                0.0, 19.0, 0.0005, 5.0, 0.1, 0.1, 2.6, 0.4, 0.4, 0.0362, 0.0,
                23.8, 106.0, 27.7, 754.0, 210.0, 0.9, 0.1, 0.4, 0.2, 0.0022, 5.0);

        // gruyere :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.fromage, context.getString(R.string.fromage_gruyere), categoryEnum.fromage.index(),
                6.0, 414.0, 28.2, 0.4, 0.4, 0.0, 33.4, 20.1, 33.0,
                0.325, 0.0, 0.0003, 0.3, 0.05, 0.3, 0.1, 0.6, 0.05, 0.012, 0.0008,
                1020.0, 607.0, 37.4, 80.5, 1600.0 / 2.5, 0.2, 0.1, 4.0, 0.0, 0.0084, 32.3,
                true, false);

        // from yazio.com fromage bleu :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.fromage_bleu, context.getString(R.string.fromage_bleu), categoryEnum.fromage.index(),
                6.0, 365.0, 16.7, 1.0, 1.0, 0.0, 33.0, 23.0, 45.0,
                0.25, 0.0, 0.0003, 0.3, 0.05, 0.5, 1.0, 0.66, 0.2, 0.054, 0.0008,
                563.0, 301.0, 18.1, 112.0, 1300.0 / 2.5, 0.3, 0.07, 2.7, 0.0, 0.0037, 27.2,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.fromage_camembert, context.getString(R.string.fromage_camembert), categoryEnum.fromage.index(),
                6.0, 275.0, 20.0, 1.0, 1.0, 0.0, 21.0, 15.0, 54.0,
                0.18, 0.0, 0.0007, 0.4, 0.05, 0.6, 1.3, 0.3, 0.2, 0.097, 0.001,
                463.0, 349.0, 17.9, 140.0, 1300.0 / 2.5, 0.3, 0.0, 2.6, 0.0, 0.0065, 20.3,
                true, false);

        // chevre frais cf: lanutrition.fr :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.fromage_chevre, context.getString(R.string.fromage_chevre), categoryEnum.fromage.index(),
                6.0, 158.0, 9.5, 3.0, 3.0, 0.0, 12.0, 8.0, 60.0,
                0.325, 0.0, 0.0003, 0.6, 0.05, 0.4, 0.05, 0.4, 0.1, 0.012, 0.0016,
                140.0, 256.0, 16.0, 26.0, 1300.0 / 2.5, 1.9, 0.7, 0.9, 0.0, 0.0028, 20.3,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.fromage_edam, context.getString(R.string.fromage_edam), categoryEnum.fromage.index(),
                6.0, 320.0, 25.0, 0.5, 0.5, 0.0, 24.0, 16.0, 43.0,
                0.145, 0.0, 0.00008, 0.2, 0.05, 0.4, 0.05, 0.3, 0.1, 0.012, 0.0006,
                731.0, 536.0, 30.4, 188.5, 2000.0 / 2.5, 0.4, 0.05, 3.8, 0.0, 0.0005, 20.3,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.fromage_maasdam, context.getString(R.string.fromage_maasdam), categoryEnum.fromage.index(),
                6.0, 350.0, 25.0, 0.5, 0.5, 0.0, 27.0, 18.0, 42.0,
                0.240, 0.0, 0.0002, 0.5, 0.05, 0.3, 0.1, 0.3, 0.1, 0.020, 0.0017,
                862.0, 519.0, 29.3, 81.0, 1200.0 / 2.5, 0.1, 0.05, 3.1, 0.0, 0.008, 20.3,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.fromage_gouda, context.getString(R.string.fromage_gouda), categoryEnum.fromage.index(),
                6.0, 370.0, 25.0, 0.5, 0.5, 0.0, 30.0, 18.0, 40.0,
                0.183, 0.0, 0.0002, 0.5, 0.05, 0.3, 0.1, 0.3, 0.1, 0.043, 0.0017,
                736.0, 500.0, 28.9, 94.5, 2000.0 / 2.5, 0.3, 0.1, 3.9, 0.0, 0.010, 31.4,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.gateau_chocolat, context.getString(R.string.gateauChocolat), categoryEnum.sucrerie.index(),
                7.0, 403.0, 6.0, 44.6, 25.5, 2.0, 21.8, 7.8, 24.0,
                0.082, 0.2, 0.0005, 4.1, 0.1, 0.2, 0.5, 0.6, 0.1, 0.037, 0.0002,
                58.1, 159.0, 31.8, 196.0, 257.0, 2.0, 0.2, 0.7, 0.3, 0.011, 10.0,
                true, false);

        // gateau mousse de fruit sur genoise :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.gateau_fraise, context.getString(R.string.gateauFruit), categoryEnum.sucrerie.index(),
                7.0, 270.0, 3.9, 34.3, 25.9, 1.0, 13.0, 7.9, 48.0,
                0.103, 3.1, 0.0005, 0.7, 0.04, 0.2, 0.2, 0.4, 0.0, 0.026, 0.0002,
                42.3, 57.0, 10.5, 97.0, 44.5, 0.8, 0.1, 0.4, 0.2, 0.0, 3.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.graine_tournesol, context.getString(R.string.graineTournesol), categoryEnum.fruitSec.index(),
                7.0, 642.0, 20.2, 15.0, 2.0, 20.0, 52.7, 5.9, 1.0,
                0.0, 0.5, 0.0, 31.9, 2.0, 0.2, 4.8, 0.8, 1.2, 0.182, 0.0,
                94.3, 477.0, 364.0, 622.0, 4.7, 4.9, 1.5, 3.8, 2.0, 0.0413, 5.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.grenade, context.getString(R.string.grenade), categoryEnum.fruit.index(),
                3.0, 71.0, 1.1, 14.2, 13.7, 2.3, 0.74, 0.12, 80.0,
                0.0, 8.15, 0.0, 0.6, 0.1, 0.1, 0.3, 0.5, 0.2, 0.022, 0.0,
                4.5, 18.0, 7.5, 262.0, 4.3, 0.3, 0.1, 0.4, 0.1, 0.0, 0.9);


        // bigMac :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.hamburger, context.getString(R.string.hamburger), categoryEnum.friture.index(),
                7.0, 275.0, 12.5, 23.0, 4.0, 2.0, 14.5, 4.8, 54.0,
                0.0, 0.3, 0.0, 0.0, 0.3, 0.2, 4.1, 0.4, 0.1, 0.007, 0.0012,
                64.2, 117.0, 29.1, 248.0, 970.0, 1.3, 0.1, 2.3, 0.2, 0.015, 17.6,
                false, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.haricot_vert, context.getString(R.string.haricotVert), categoryEnum.legumineux.index(),
                4.6, 33.0, 1.4, 5.1, 2.3, 3.0, 0.2, 0.0, 90.0,
                0.0, 8.0, 0.0, 0.2, 0.05, 0.1, 0.3, 0.05, 0.05, 0.045, 0.0,
                56.3, 36.0, 22.4, 175.0, 144.0, 0.6, 0.1, 0.3, 0.2, 0.01, 0.005);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.homard, context.getString(R.string.homard), categoryEnum.fruitDeMer.index(),
                7.0, 104.0, 22.1, 0.3, 0.0, 0.0, 1.6, 0.2, 74.0,
                0.0, 0.0, 0.0, 1.5, 0.1, 0.1, 1.5, 1.0, 0.1, 0.009, 0.003,
                66.5, 260.0, 34.1, 260.0, 350.0, 0.8, 1.3, 2.8, 0.1, 0.130, 100.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.hot_dog, context.getString(R.string.hotDog), categoryEnum.friture.index(),
                7.0, 318.0, 11.3, 26.0, 2.4, 2.0, 18.4, 5.2, 40.0,
                0.0, 0.0, 0.0, 0.0, 0.2, 0.3, 3.7, 0.5, 0.1, 0.03, 0.0005,
                33.6, 99.0, 20.5, 172.0, 961.0, 2.4, 0.1, 1.4, 0.1, 0.0, 7.0,
                false, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.huitre, context.getString(R.string.huitre), categoryEnum.fruitDeMer.index(),
                7.0, 42.0, 6.4, 0.8, 0.0, 0.0, 1.5, 0.3, 87.0,
                0.0142, 5.0, 0.0005, 1.2, 0.1, 0.2, 7.3, 0.6, 0.1, 0.003, 0.0241,
                74.6, 94.6, 81.9, 215.0, 580.0, 2.3, 1.5, 21.3, 0.5, 0.044, 87.9,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.jus_orange, context.getString(R.string.jusOrange), categoryEnum.boisson.index(),
                3.5, 44.0, 0.7, 9.4, 8.7, 0.45, 0.09, 0.0, 88.0,
                0.0, 38.3, 0.0, 0.2, 0.1, 0.0, 0.4, 0.2, 0.1, 0.033, 0.0,
                4.5, 15.8, 9.8, 171.0, 0.6, 0.1, 0.0, 0.065, 0.0, 0.0, 0.7);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.kebab, context.getString(R.string.kebab), categoryEnum.friture.index(),
                7.0, 233.0, 17.2, 15.5, 1.3, 2.0, 10.8, 3.6, 52.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                28.4, 60.0, 24.0, 358.0, 365.0, 1.0, 0.2, 2.2, 0.2, 0.0, 3.0,
                false, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.kiwi, context.getString(R.string.kiwi), categoryEnum.fruit.index(),
                6.0, 58.0, 1.1, 9.4, 9.1, 2.0, 0.7, 0.0, 84.0,
                0.0, 92.7, 0.0, 1.2, 0.0, 0.0, 0.3, 0.2, 0.1, 0.0365, 0.0,
                26.6, 47.1, 12.2, 270.0, 3.1, 0.3, 0.2, 0.1, 0.1, 0.010, 1.1);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.lait, context.getString(R.string.lait), categoryEnum.produit_laitier.index(),
                6.5, 46.0, 3.2, 4.8, 4.8, 0.0, 1.5, 0.9, 90.0,
                0.020, 1.4, 0.0, 0.0, 0.0, 0.2, 0.1, 0.3, 0.0, 0.0026, 0.0002,
                116.9, 91.9, 12.1, 167.0, 43.1, 0.0, 0.0, 0.4, 0.0, 0.0009, 11.5,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.lait_entier, context.getString(R.string.lait_entier), categoryEnum.produit_laitier.index(),
                6.5, 65.0, 3.2, 4.8, 4.8, 0.0, 3.6, 2.0, 88.0,
                0.047, 1.0, 0.0, 0.1, 0.0, 0.2, 0.1, 0.3, 0.0, 0.0025, 0.0002,
                112.0, 87.0, 11.0, 140.0, 42.2, 0.1, 0.0, 0.4, 0.0, 0.001, 11.2,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.lait_ecreme, context.getString(R.string.lait_ecreme), categoryEnum.produit_laitier.index(),
                6.5, 32.0, 3.2, 4.8, 4.8, 0.0, 0.1, 0.1, 91.0,
                0.0005, 1.3, 0.0, 0.0, 0.1, 0.2, 0.1, 0.4, 0.0, 0.0028, 0.0003,
                113.0, 88.8, 10.6, 173.0, 41.8, 0.1, 0.0, 0.4, 0.0, 0.0008, 12.7,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.langouste, context.getString(R.string.langouste), categoryEnum.fruitDeMer.index(),
                7.0, 136.0, 26.4, 3.1, 0.0, 0.0, 1.9, 0.3, 67.0,
                0.0105, 2.1, 0.0005, 1.5, 0.0, 0.1, 4.9, 0.4, 0.2, 0.001, 0.004,
                63.0, 229.0, 51.0, 208.0, 227.0, 1.4, 0.4, 7.3, 0.0, 0.0, 65.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.litchi, context.getString(R.string.litchi), categoryEnum.fruit.index(),
                6.5, 69.0, 0.8, 14.0, 14.0, 1.0, 0.4, 0.1, 82.0,
                0.0, 71.5, 0.0, 0.0, 0.0, 0.1, 0.6, 0.0, 0.1, 0.070, 0.0,
                5.0, 31.0, 10.0, 171.0, 1.0, 0.3, 0.1, 0.1, 0.1, 0.0, 0.8);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.mais, context.getString(R.string.mais), categoryEnum.feculent.index(),
                7.0, 100.0, 3.4, 16.4, 4.8, 4.0, 1.5, 0.2, 75.0,
                0.0, 7.0, 0.0, 0.9, 0.1, 0.1, 1.7, 0.8, 0.1, 0.0578, 0.0,
                3.0, 77.0, 26.0, 218.0, 1.0, 0.5, 0.0, 0.4, 0.2, 0.0, 1.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.melon, context.getString(R.string.melon), categoryEnum.fruit.index(),
                6.0, 32.0, 0.7, 6.5, 5.9, 1.0, 0.1, 0.0, 91.0,
                0.0, 11.1, 0.0, 0.1, 0.0, 0.0, 0.6, 0.1, 0.1, 0.098, 0.0,
                11.4, 12.3, 17.6, 329.0, 11.4, 0.2, 0.0, 0.2, 0.0415, 0.0, 0.5);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.moule, context.getString(R.string.moule), categoryEnum.fruitDeMer.index(),
                7.0, 114.0, 17.1, 7.4, 0.0, 0., 1.8, 0.5, 73.0,
                0.082, 0.0, 0.0, 2.4, 0.0, 0.3, 1.2, 0.3, 0.0, 0.037, 0.0183,
                69.1, 192.0, 78.9, 187.0, 376.0, 5.5, 0.24, 2.9, 0.2, 0.0584, 195.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.muffin, context.getString(R.string.muffin), categoryEnum.sucrerie.index(),
                7.0, 310.0, 2.6, 48.7, 16.5, 4.0, 15.5, 4.0, 22.0,
                0.069, 0.0, 0.00042, 2.6, 0.2, 0.2, 1.8, 0.5, 0.12, 0.0681, 0.0005,
                71.4, 154.0, 30.4, 202.0, 250.0, 1.9, 0.5, 0.5, 0.5, 0.0032, 5.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.noisette, context.getString(R.string.noisette), categoryEnum.fruitSec.index(),
                7.0, 683.0, 16.4, 5.6, 3.3, 8.0, 63.0, 5.2, 5.0,
                0.0, 0.3, 0.0, 5.7, 0.4, 0.1, 1.1, 0.9, 0.3, 0.198, 0.0,
                135.0, 458.0, 88.4, 606.0, 10.0, 3.5, 1.6, 2.3, 4.9, 0.005, 5.6);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.noix, context.getString(R.string.noix), categoryEnum.fruitSec.index(),
                7.0, 698.0, 14.7, 10.8, 3.0, 6.0, 63.8, 5.7, 3.0,
                0.0, 3.0, 0.0, 3.5, 0.4, 0.1, 1.2, 0.9, 0.6, 0.135, 0.00008,
                67.8, 384.0, 126.0, 525.0, 3.0, 2.6, 1.1, 2.5, 2.1, 0.019, 4.5);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.noix_coco, context.getString(R.string.noixCoco), categoryEnum.fruit.index(),
                6.5, 374.0, 3.6, 3.8, 3.8, 12.0, 35.3, 30.9, 44.0,
                0.0, 2.8, 0.0, 0.4, 0.0, 0.0, 0.5, 0.3, 0.1, 0.0253, 0.0,
                12.6, 108.0, 37.5, 352.0, 19.3, 2.1, 0.4, 0.7, 0.8, 0.002, 0.8);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.noix_saint_jacques, context.getString(R.string.noixSaintJacques), categoryEnum.fruitDeMer.index(),
                7.0, 120.0, 23.2, 3.2, 0.0, 0.0, 1.6, 0.4, 73.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.9, 0.2, 0.0, 0.018, 0.0087,
                17.4, 298.0, 58.7, 340.0, 188.0, 1.3, 0.1, 2.8, 1.1, 0.110, 20.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.nugget_poulet_v2, context.getString(R.string.nuggetPoulet), categoryEnum.friture.index(),
                7.0, 250.0, 7.5, 20.0, 7.0, 2.0, 13.5, 2.5, 57.0,
                0.00075, 0.0, 0.0005, 2.6, 0.1, 0.1, 5.7, 0.7, 0.3, 0.0193, 0.0002,
                16.4, 225.0, 24.0, 215.0, 400.0, 0.6, 0.1, 0.7, 0.2, 0.0095, 13.6,
                false, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.oeuf, context.getString(R.string.oeufDur), categoryEnum.oeuf.index(),
                7.0, 134.0, 13.5, 0.5, 0.5, 0.0, 8.6, 2.9, 77.0,
                0.0615, 0.0, 0.0013, 1.9, 0.1, 0.4, 0.1, 1.4, 0.1, 0.106, 0.001,
                41.0, 176.0, 14.0, 112.0, 124.0, 1.8, 0.1, 1.0, 0.0, 0.020, 49.7,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.oeuf_plat, context.getString(R.string.oeufAuPlat), categoryEnum.oeuf.index(),
                7.0, 205.0, 12.7, 2.4, 0.4, 0.0, 16.0, 3.9, 68.0,
                0.098, 0.0, 0.0006, 3.1, 0.1, 0.4, 0.1, 1.3, 0.1, 0.0626, 0.0011,
                57.0, 134.0, 10.4, 141.0, 144.0, 2.2, 0.1, 0.9, 0.0, 0.018, 58.4,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.oignon, context.getString(R.string.oignon), categoryEnum.legumineux.index(),
                6.0, 43.0, 1.3, 7.4, 5.4, 1.0, 0.6, 0.0, 89.0,
                0.0, 6.0, 0.0, 0.3, 0.05, 0.05, 0.05, 0.2, 0.1, 0.0205, 0.0,
                31.0, 29.7, 9.2, 179.0, 39.0, 0.1, 0.1, 0.2, 0.1, 0.006, 2.6);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.orange_v2, context.getString(R.string.orange), categoryEnum.fruit.index(),
                3.5, 47.0, 1.0, 8.3, 8.3, 2.0, 0.3, 0.1, 87.0,
                0.0, 39.7, 0.0, 0.4, 0.1, 0.0, 0.3, 0.2, 0.1, 0.0387, 0.0,
                39.0, 15.1, 12.4, 151.0, 4.9, 0.1, 0.0, 0.1, 0.1, 0.0013, 1.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.pain, context.getString(R.string.painBaguette), categoryEnum.feculent.index(),
                7.0, 286.0, 9.3, 56.6, 2.1, 3.0, 1.5, 0.2, 27.0,
                0.0, 0.0, 0.0, 0.1, 0.1, 0.1, 1.2, 0.4, 0.1, 0.0205, 0.0001,
                52.4, 110.0, 19.7, 158.0, 681.0, 1.5, 0.1, 0.7, 0.5, 0.0047, 5.9);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.pain_complet, context.getString(R.string.painComplet), categoryEnum.feculent.index(),
                7.0, 269.0, 9.0, 50.6, 1.7, 7.0, 1.8, 0.4, 31.0,
                0.0, 0.0, 0.0, 0.5, 0.1, 0.1, 6.3, 0.5, 0.4, 0.0307, 0.0001,
                33.0, 254.0, 108.0, 291.0, 603.0, 6.8, 0.2, 3.9, 5.7, 0.010, 31.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.pain_de_mie, context.getString(R.string.painDeMie), categoryEnum.feculent.index(),
                7.0, 281.0, 8.2, 49.2, 5.4, 3.0, 4.7, 1.1, 33.0,
                0.0, 0.0, 0.0, 0.0, 0.1, 0.1, 6.0, 0.5, 0.1, 0.020, 0.0,
                231.0, 87.0, 49.3, 153.0, 498.0, 10.0, 0.1, 5.0, 5.0, 0.028, 31.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.pasteque_v2, context.getString(R.string.pasteque), categoryEnum.fruit.index(),
                7.0, 34.0, 0.6, 7.3, 6.3, 0.0, 0.1, 0.0, 91.0,
                0.0, 8.6, 0.0, 0.1, 0.0, 0.0, 0.2, 0.3, 0.1, 0.0023, 0.0,
                0.0, 10.9, 12.6, 179.0, 0.0, 0.3, 0.06, 0.0, 0.0, 0.0022, 0.1);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.patate, context.getString(R.string.patate), categoryEnum.feculent.index(),
                7.0, 75.0, 2.0, 15.8, 0.5, 2.0, 0.2, 0.0, 79.0,
                0.0, 8.0, 0.0, 0.1, 0.1, 0.04, 1.7, 0.6, 0.3, 0.013, 0.0,
                10.5, 37.2, 17.3, 279.0, 39.0, 0.3, 0.1, 0.1, 0.1, 0.010, 0.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.pates, context.getString(R.string.pates), categoryEnum.feculent.index(),
                7.0, 151.0, 4.9, 29.7, 0.5, 2.0, 0.8, 0.1, 62.0,
                0.0, 0.0, 0.0, 0.1, 0.1, 0.0, 0.05, 0.1, 0.05, 0.0069, 0.0,
                16.5, 70.0, 24.5, 51.7, 115.0, 0.65, 0.418, 0.75, 0.335, 0.010, 1.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.peche_v2, context.getString(R.string.peche), categoryEnum.fruit.index(),
                3.5, 53.0, 0.9, 10.2, 8.7, 3.0, 0.3, 0.0, 85.0,
                0.0, 5.8, 0.0, 1.4, 0.0, 0.0, 0.6, 0.2, 0.0, 0.0155, 0.0,
                7.3, 20.6, 11.2, 215.0, 1.0, 0.2, 0.1, 0.1, 0.1, 0.0003, 0.6);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.petits_pois, context.getString(R.string.petitsPois), categoryEnum.legumineux.index(),
                7.0, 71.0, 5.2, 8.3, 3.9, 6.0, 0.6, 0.0, 79.0,
                0.0, 8.9, 0.0, 0.21, 0.1, 0.1, 0.4, 0.05, 0.05, 0.044, 0.0,
                33.5, 96.0, 31.6, 133.0, 167.0, 1.2, 0.181, 0.8, 0.4, 0.001, 2.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.piment, context.getString(R.string.piment), categoryEnum.legumineux.index(),
                4.5, 76.0, 2.7, 7.1, 2.0, 5.0, 2.9, 0.0, 78.0,
                0.0, 31.9, 0.0, 4.5, 0.05, 0.2, 0.05, 0.5, 0.3, 0.028, 0.0,
                78.1, 63.0, 47.2, 710.0, 1120.0, 3.1, 1.0, 1.0, 1.0, 0.005, 43.5);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.pizza, context.getString(R.string.pizza), categoryEnum.feculent.index(),
                7.0, 216.0, 8.9, 27.8, 5.0, 3.0, 7.0, 2.6, 50.0,
                0.043, 1.5, 0.0005, 1.1, 0.2, 0.1, 1.9, 0.4, 0.2, 0.017, 0.0005,
                189.0, 170.0, 24.4, 234.0, 665.0, 0.7, 0.1, 1.4, 0.2, 0.008, 6.0,
                false, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poire, context.getString(R.string.poire), categoryEnum.fruit.index(),
                4.5, 53.0, 0.4, 10.8, 10.4, 2.3, 0.2, 0.0, 85.0,
                0.0, 5.3, 0.0, 0.5, 0.0, 0.0, 0.2, 0.3, 0.1, 0.0062, 0.0,
                7.4, 11.4, 7.8, 130.0, 1.9, 0.1, 0.1, 0.1, 0.0, 0.00059, 1.0);

        // --- POISSON :
        // general :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poisson, context.getString(R.string.poisson_autre), categoryEnum.poisson.index(),
                7.0, 154.0, 18.7, 4.0, 0.0, 0.0, 7.0, 1.3, 69.0,
                0.0097, 0.0, 0.003, 1.5, 0.1, 0.1, 4.8, 0.4, 0.3, 0.0137, 0.0026,
                27.7, 189.0, 27.6, 309.0, 306.0, 0.5, 0.1, 0.6, 0.0, 0.044, 34.6,
                true, false);

        // Saumon en barquette :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poisson_saumon, context.getString(R.string.poisson_saumon), categoryEnum.poisson.index(),
                7.0, 182.0, 25.5, 0.1, 0.1, 0.0, 10.0, 1.8, 70.0,
                0.013, 0.0, 0.008, 1.5, 0.275, 0.487, 4.5, 1.92, 0.944, 0.0137, 0.003,
                15.0, 256.0, 37.0, 628.0, 1000.0, 1.03, 0.321, 0.82, 0.021, 0.0468, 34.6,
                true, false);

        // Thon :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poisson_thon, context.getString(R.string.poisson_thon), categoryEnum.poisson.index(),
                7.0, 136.0, 23.7, 0.1, 0.1, 0.0, 4.6, 1.2, 70.0,
                0.026, 0.0, 0.0072, 0.0, 0.1, 0.1, 12.8, 0.7, 0.4, 0.015, 0.004,
                24.0, 220.0, 33.0, 400.0, 100.0, 1.5, 0.2, 0.7, 0.01, 0.099, 36.7,
                true, false);

        // Thon boite :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poisson_thon_boite, context.getString(R.string.poisson_thonBoite), categoryEnum.poisson.index(),
                7.0, 112.0, 25.4, 0.1, 0.1, 0.0, 1.2, 0.3, 70.0,
                0.026, 0.0, 0.0072, 0.0, 0.1, 0.1, 12.8, 0.7, 0.4, 0.015, 0.004,
                24.0, 220.0, 33.0, 400.0, 100.0, 1.5, 0.2, 0.7, 0.01, 0.099, 36.7,
                true, false);

        // Espadon :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poisson_espadon, context.getString(R.string.poisson_espadon), categoryEnum.poisson.index(),
                7.0, 191.0, 22.9, 0.1, 0.1, 0.0, 11.0, 0.4, 64.0,
                0.0, 0.0, 0.0, 3.6, 0.1, 0.2, 9.7, 0.5, 0.6, 0.0, 0.005,
                5.0, 270.0, 34.0, 450.0, 120.0, 0.6, 0.0, 0.0, 0.0, 0.057, 17.2,
                true, false);

        // Sardine :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poisson_sardine, context.getString(R.string.poisson_sardine), categoryEnum.poisson.index(),
                7.0, 214.0, 30.0, 0.1, 0.1, 0.0, 10.4, 3.1, 56.0,
                0.0, 0.0, 0.0123, 0.3, 0.0, 0.3, 6.9, 0.9, 0.4, 0.004, 0.012,
                130.0, 320.0, 34.0, 400.0, 140.0, 1.7, 0.1, 1.4, 0.1, 0.038, 32.0,
                true, false);

        // Sardine boite :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poisson_sardine_boite, context.getString(R.string.poisson_sardineBoite), categoryEnum.poisson.index(),
                7.0, 246.0, 24.9, 0.1, 0.1, 0.0, 16.3, 4.6, 61.0,
                0.0025, 0.0, 0.0074, 2.1, 0.0, 0.2, 7.3, 0.8, 0.4, 0.0, 0.0136,
                108.0, 262.0, 37.0, 357.0, 280.0, 2.3, 0.1, 2.0, 0.1, 0.046, 40.0,
                true, false);

        // --- FIN POISSON;

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poisson_pane, context.getString(R.string.poissonPane), categoryEnum.friture.index(),
                7.0, 190.0, 14.0, 16.0, 2.8, 0.5, 8.7, 1.8, 62.0,
                0.008, 0.0, 0.0015, 1.9, 0.1, 0.1, 1.6, 0.3, 0.2, 0.0145, 0.0013,
                24.9, 104.0, 30.6, 277.0, 1200.0, 0.5, 0.1, 0.4, 0.2, 0.018, 5.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poivron, context.getString(R.string.poivron), categoryEnum.legumineux.index(),
                7.0, 29.0, 0.9, 4.8, 4.7, 1.0, 0.4, 0.0, 92.0,
                0.0, 74.4, 0.0, 0.8, 0.1, 0.0, 0.5, 0.1, 0.2, 0.016, 0.0,
                9.0, 18.0, 10.0, 166.0, 2.0, 0.5, 0.1, 0.1, 0.1, 0.0001, 1.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.pomme_v2, context.getString(R.string.pomme), categoryEnum.fruit.index(),
                3.5, 53.0, 0.3, 11.3, 11.3, 2.0, 0.2, 0.0, 85.0,
                0.0, 6.5, 0.0, 0.6, 0.0, 0.0, 0.1, 0.1, 0.1, 0.0039, 0.0,
                5.1, 10.0, 6.1, 120.0, 2.0, 0.1, 0.0, 0.15, 0.0, 0.0014, 0.6);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poulpe, context.getString(R.string.poulpe), categoryEnum.fruitDeMer.index(),
                7.0, 86.0, 17.9, 1.5, 0.0, 0.0, 0.9, 0.3, 82.0,
                0.005, 0.0, 0.0, 0.0, 0.1, 0.1, 5.0, 0.0, 0.4, 0.0, 0.0,
                33.0, 170.0, 0.0, 230.0, 136.0, 1.2, 0.4, 1.7, 0.0, 0.075, 20.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.prune, context.getString(R.string.prune), categoryEnum.fruit.index(),
                6.0, 49.0, 0.8, 9.6, 9.6, 2.0, 0.3, 0.0, 82.0,
                0.0, 5.4, 0.0, 0.6, 0.1, 0.0, 0.6, 0.2, 0.1, 0.010, 0.0,
                5.3, 25.7, 7.3, 243.0, 0.8, 0.3, 0.1, 0.1, 0.1, 0.0001, 2.7);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.pruneau, context.getString(R.string.pruneau), categoryEnum.fruitSec.index(),
                6.0, 244.0, 2.4, 52.3, 37.9, 16.0, 0.7, 0.1, 33.0,
                0.0, 2.5, 0.0, 2.0, 0.1, 0.1, 1.6, 0.4, 0.2, 0.007, 0.0,
                47.9, 77.4, 33.7, 657.0, 4.3, 2.0, 0.4, 0.5, 0.3, 0.001, 1.1);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.radis, context.getString(R.string.radis), categoryEnum.legumineux.index(),
                6.0, 13.0, 0.8, 1.8, 1.8, 1.0, 0.1, 0.0, 95.0,
                0.0, 17.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.2, 0.1, 0.051, 0.0,
                30.6, 17.3, 11.2, 287.0, 22.1, 0.6, 0.0, 0.1, 0.1, 0.0015, 1.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.raisin, context.getString(R.string.raisin), categoryEnum.fruit.index(),
                6.0, 70.0, 0.6, 16.1, 16.1, 1.0, 0.2, 0.1, 82.0,
                0.0, 4.0, 0.0, 0.7, 0.0, 0.0, 0.3, 0.1, 0.1, 0.015, 0.0,
                10.1, 21.0, 10.3, 212.0, 1.1, 0.4, 0.1, 0.1, 0.1, 0.004, 0.5);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.riz, context.getString(R.string.riz), categoryEnum.feculent.index(),
                7.0, 135.0, 2.5, 28.7, 0.1, 1.0, 0.9, 0.3, 67.0,
                0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.1, 0.04, 0.0053, 0.0,
                8.2, 38.0, 11.4, 29.9, 117.0, 0.3, 0.14, 0.4, 0.2, 0.0029, 59.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.salade, context.getString(R.string.salade), categoryEnum.legumineux.index(),
                7.0, 16.0, 0.7, 2.3, 1.7, 1.0, 0.1, 0.0, 95.0,
                0.0, 2.0, 0.0005, 0.2, 0.05, 0.03, 0.1, 0.12, 0.1, 0.0422, 0.0,
                48.7, 23.5, 20.0, 320.0, 13.6, 0.9, 0.15, 0.2, 0.2, 0.008, 1.2);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.sandwich_pain, context.getString(R.string.sandwichPain), categoryEnum.feculent.index(),
                7.0, 290.0, 11.5, 33.3, 2.2, 2.0, 11.9, 5.1, 39.0,
                0.161, 2.7, 0.0003, 1.4, 0.2, 0.1, 2.3, 0.4, 0.2, 0.0195, 0.003,
                63.4, 143.0, 25.2, 167.0, 645.0, 1.1, 0.1, 1.1, 0.4, 0.0063, 9.2,
                false, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.sandwich_pain_de_mie, context.getString(R.string.sandwichPainDeMie), categoryEnum.feculent.index(),
                7.0, 260.0, 13.8, 26.0, 1.7, 2.0, 10.7, 4.4, 45.0,
                0.0302, 1.5, 0.0002, 0.8, 0.1, 0.0, 1.1, 0.2, 0.1, 0.0093, 0.001,
                49.5, 76.7, 24.0, 239.0, 469.0, 0.9, 0.1, 1.5, 0.3, 0.0035, 6.4,
                false, false);

        // --- VIANDE :

        // vache :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.viande_steak, context.getString(R.string.viande_steak), categoryEnum.viande.index(),
                7.5, 239.0, 23.6, 0.5, 0.1, 0.0, 16.1, 7.2, 59.0,
                0.0, 0.0, 0.0002, 0.0, 0.1, 0.1, 5.2, 0.7, 0.3, 0.009, 0.0023,
                15.0, 196.0, 29.4, 384.0, 185.0, 2.6, 0.1, 5.1, 0.1, 0.006, 6.1,
                false, false);

        // brochette mixte de viande :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.brochette, context.getString(R.string.viande_brochette), categoryEnum.viande.index(),
                7.5, 209.0, 19.3, 1.7, 1.0, 0.1, 13.8, 5.0, 63.0,
                0.002, 9.5, 0.0, 0.7, 0.1, 0.2, 4.1, 0.5, 0.3, 0.0115, 0.001,
                10.9, 162.0, 21.4, 273.0, 247.0, 1.4, 0.1, 1.0, 0.0, 0.0084, 2.2,
                false, false);

        // poulet :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.viande_poulet_cuisse, context.getString(R.string.viande_pouletCuisse), categoryEnum.viande.index(),
                7.5, 205.0, 28.8, 0.1, 0.0, 0.0, 9.9, 2.7, 60.0,
                0.0475, 1.3, 0.0005, 0.5, 0.1, 0.2, 8.6, 1.0, 0.3, 0.013, 0.0005,
                19.4, 170.0, 39.6, 600.0, 121.0, 0.7, 0.1, 1.3, 0.0230, 0.015, 5.0,
                false, false);

        // Saucisse Volaille knaki herta poulet : (40g unitee)
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.viande_saucisse_strasbourg_volaille, context.getString(R.string.viande_saucisseVolaille), categoryEnum.viande.index(),
                7.5, 199.0, 14.0, 1.5, 1.5, 1.0, 15.0, 3.5, 54.0,
                0.0, 9.4, 0.0003, 0.4, 0.2, 0.2, 2.5, 0.6, 0.01, 0.0036, 0.0006,
                25.0, 173.0, 13.1, 192.0, 1900.0 / 2.5, 1.1, 0.1, 1.1, 0.02, 0.0056, 7.0,
                false, false);

        // saucisson a l'ail carrefour :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.viande_cervela, context.getString(R.string.viande_cervela), categoryEnum.viande.index(),
                7.5, 280.0, 16.0, 0.6, 0.6, 0.0, 26.0, 10.7, 56.0,
                0.0, 0.0, 0.0011, 0.0, 0.21, 0.18, 5.4, 0.53, 0.179, 0.008, 0.00066,
                11.0, 157.0, 11.4, 483.0, 827.0, 0.6, 0.176, 1.31, 0.011, 0.0183, 5.1,
                false, false, true);

        // grison carrefour : (20g unitee)
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.viande_grison, context.getString(R.string.viande_grison), categoryEnum.viande.index(),
                7.5, 199.0, 39.0, 1.2, 0.7, 0.0, 4.1, 1.5, 54.0,
                0.003, 0.25, 0.00025, 0.005, 0.1, 0.3, 11.8, 1.3, 0.7, 0.0339, 0.0024,
                30.8, 278.0, 46.6, 660.0, 4600.0 / 2.5, 1.8, 0.2, 5.9, 0.05, 0.009, 53.8,
                false, false);

        // jambon superieur avec couane carrefour : (40g unitee)
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.viande_jambon, context.getString(R.string.viande_jambon), categoryEnum.viande.index(),
                7.5, 155.0, 29.0, 0.8, 0.8, 0.0, 8.0, 3.5, 54.0,
                0.0015, 10.6, 0.0006, 0.1, 0.7, 0.2, 4.9, 0.7, 0.3, 0.0031, 0.0008,
                7.1, 136.0, 28.1, 359.0, 2000.0 / 2.5, 0.8, 0.1, 1.9, 0.0, 0.0082, 13.9,
                false, false, true);

        // saucisse starsbourg porc biovillage marque-repere : (40g unitee)
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.viande_saucisse_strasbourg_porc, context.getString(R.string.viande_saucisseStrasbourgPorc), categoryEnum.viande.index(),
                7.5, 277.0, 14.0, 1.0, 1.0, 0.0, 24.0, 9.2, 54.0,
                0.0, 9.4, 0.0003, 0.4, 0.2, 0.2, 2.5, 0.6, 0.01, 0.0036, 0.0006,
                25.0, 173.0, 13.1, 192.0, 1900.0 / 2.5, 1.1, 0.1, 1.1, 0.02, 0.0056, 7.0,
                false, false, true);

        // saucisse toulouse : (100g unitee)
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.viande_saucisse_toulouse, context.getString(R.string.viande_saucisseToulouse), categoryEnum.viande.index(),
                7.5, 311.0, 18.8, 1.1, 0.6, 0.0, 25.7, 10.2, 55.0,
                0.0, 9.4, 0.0003, 0.4, 0.2, 0.2, 2.5, 0.6, 0.01, 0.0036, 0.0006,
                25.0, 173.0, 13.1, 192.0, 1900.0 / 2.5, 1.1, 0.1, 1.1, 0.02, 0.0056, 7.0,
                false, false, true);

        // --- fin des viandes;

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.the, context.getString(R.string.the), categoryEnum.boisson.index(),
                7.0, 0.2, 0.1, 0.1, 0.0, 0.0, 0.0, 0.0, 99.5,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.0, 0.0, 0.0, 0.0,
                100.0, 1.8, 2.7, 10.1, 2.5, 0.0, 0.0, 0.1, 0.1, 0.008, 0.6);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.tomate, context.getString(R.string.tomate), categoryEnum.fruit.index(),
                5.0, 16.0, 0.8, 1.7, 1.7, 1.0, 0.3, 0.0, 95.0,
                0.0, 14.3, 0.0, 1.1, 0.1, 0.0, 0.6, 0.3, 0.1, 0.0205, 0.0,
                8.2, 19.3, 10.1, 251.0, 3.3, 0.2, 0.0, 0.2, 0.1, 0.0002, 2.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.wrap, context.getString(R.string.wrap), categoryEnum.friture.index(),
                7.0, 164.0, 7.4, 17.7, 2.7, 1.0, 6.9, 2.7, 65.0,
                0.0174, 1.6, 0.0007, 1.2, 0.2, 0.2, 0.0, 0.0, 0.1, 0.0304, 0.0003,
                97.1, 118.0, 14.9, 148.0, 438.0, 0.6, 0.1, 0.6, 0.2, 0.0051, 12.6,
                false, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.yaourt, context.getString(R.string.yaourt_nature), categoryEnum.produit_laitier.index(),
                7.5, 50.0, 4.0, 5.0, 5.0, 0.0, 1.0, 0.5, 89.0,
                0.016, 0.3, 0.0, 0.0, 0.1, 0.2, 0.2, 0.3, 0.1, 0.0222, 0.0002,
                146.0, 99.8, 11.6, 182.0, 60.0, 0.1, 0.0, 0.5, 0.0, 0.0019, 18.7,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.yaourt_fruit, context.getString(R.string.yaourt_fruit), categoryEnum.produit_laitier.index(),
                7.5, 97.0, 3.2, 13.0, 12.5, 0.0, 3.0, 1.5, 78.0,
                0.023, 0.2, 0.0004, 0.1, 0.0, 0.2, 0.2, 0.3, 0.1, 0.0135, 0.0002,
                119.0, 79.6, 10.9, 159.0, 50.0, 0.0, 0.0, 0.4, 0.0, 0.0015, 10.0,
                true, false);

        // ADD AFTER

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.bonbons, context.getString(R.string.bonbon), categoryEnum.sucrerie.index(),
                6.0, 403.0, 1.9, 85.0, 80.0, 0.2, 5.0, 4.0, 7.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                26.5, 7.3, 19.6, 68.3, 61.4, 0.7, 0.1, 0.1, 0.1, 0.010, 6.2,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.calamar_beignet, context.getString(R.string.calamarBeignet), categoryEnum.friture.index(),
                7.0, 220.0, 8.6, 24.5, 4.0, 2.0, 11.5, 2.0, 56.0,
                0.046, 0.0, 0.001, 2.6, 0.1, 0.5, 1.3, 0.4, 0.3, 0.0138, 0.0017,
                64.7, 185.0, 22.1, 183.0, 480.0, 0.7, 0.5, 0.9, 0.1, 0.035, 122.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.chips, context.getString(R.string.chips), categoryEnum.friture.index(),
                7.0, 487.0, 6.5, 52.9, 1.1, 5.0, 27.0, 4.0, 2.0,
                0.0, 31.8, 0.0, 5.3, 0.1, 0.1, 4.9, 0.6, 0.5, 0.051, 0.0,
                27.1, 97.0, 73.3, 1260.0, 622.0, 1.1, 0.2, 0.8, 0.3, 0.005, 87.5);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.chocolat, context.getString(R.string.chocolat), categoryEnum.sucrerie.index(),
                7.0, 572.0, 9.3, 33.3, 21.3, 13.0, 41.9, 30.6, 1.0,
                0.0, 0.0, 0.0, 0.7, 0.2, 0.3, 0.9, 0.3, 0.4, 0.035, 0.0003,
                60.0, 248.0, 206.0, 727.0, 4.1, 10.7, 1.4, 2.9, 1.1, 0.005, 10.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.citrouille, context.getString(R.string.citrouille), categoryEnum.legumineux.index(),
                6.0, 26.0, 0.6, 6.5, 0.6, 2.0, 0.1, 0.0, 92.0,
                0.0, 0.7, 0.0, 0.3, 0.0, 0.1, 0.4, 0.4, 0.05, 0.0292, 0.0,
                25.9, 15.8, 5.9, 223.0, 9.3, 1.0, 0.1, 0.2, 0.0, 0.0, 0.7);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.crepe, context.getString(R.string.crepe), categoryEnum.sucrerie.index(),
                7.0, 351.0, 6.3, 67.7, 28.4, 1.0, 5.9, 3.9, 16.0,
                0.062, 1.0, 0.0005, 0.6, 0.1, 0.1, 0.7, 0.7, 0.1, 0.033, 0.0001,
                82.5, 87.0, 17.5, 162.0, 569.0, 0.8, 0.1, 0.6, 0.3, 0.006, 11.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.courge_butternut, context.getString(R.string.courgeButternut), categoryEnum.legumineux.index(),
                6.0, 50.0, 0.66, 14.5, 2.0, 2.0, 0.34, 0.02, 85.0,
                0.013, 5.7, 0.0, 0.0, 0.046, 0.042, 0.0, 0.5, 0.099, 0.0, 0.0,
                9.0, 31.0, 20.0, 453.0, 21.0, 0.36, 0.085, 0.17, 0.223, 0.0002, 0.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.glace, context.getString(R.string.glace), categoryEnum.sucrerie.index(),
                7.0, 278.0, 4.2, 33.1, 21.8, 1.0, 14.2, 11.4, 47.0,
                0.0, 1.0, 0.0, 0.8, 0.1, 0.2, 0.5, 0.3, 0.1, 0.0083, 0.0003,
                98.0, 86.7, 20.9, 261.0, 73.3, 1.7, 0.0, 0.6, 0.0, 0.00011, 29.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.haricot, context.getString(R.string.haricot), categoryEnum.feculent.index(),
                7.0, 104.0, 8.4, 13.6, 0.8, 8.0, 0.3, 0.1, 70.0,
                0.0, 0.0, 0.0, 0.1, 0.1, 0.0, 0.1, 0.2, 0.1, 0.081, 0.0,
                55.2, 100.0, 60.6, 310.0, 77.5, 1.7, 0.2, 1.0, 0.6, 0.0036, 0.003);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.miel_v2, context.getString(R.string.miel), categoryEnum.sucrerie.index(),
                7.0, 327.0, 0.4, 81.1, 79.1, 0.0, 0.1, 0.0, 18.0,
                0.0, 1.7, 0.0, 0.0, 0.0, 0.1, 0.3, 0.1, 0.3, 0.0053, 0.0,
                7.9, 17.0, 4.3, 70.2, 4.1, 0.2, 0.0, 0.4, 0.2, 0.0014, 0.7,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.sirop_erable, context.getString(R.string.siropErable), categoryEnum.sucrerie.index(),
                7.0, 261.0, 0.0, 66.89, 45.0, 0.0, 0.244, 0.036, 32.15,
                0.0, 0.0, 0.0, 0.0, 0.066, 1.27, 0.08, 0.03, 0.002, 0.0, 0.0,
                108.8, 2.0, 20.94, 224.82, 9.0, 1.2, 0.074, 0.7, 2.3, 0.0006, 0.0,
                true, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.courge, context.getString(R.string.courge), categoryEnum.legumineux.index(),
                6.0, 50.0, 0.66, 14.5, 2.0, 2.0, 0.34, 0.02, 85.0,
                0.013, 5.7, 0.0, 0.0, 0.046, 0.042, 0.0, 0.5, 0.01, 0.0, 0.0,
                9.0, 31.0, 20.0, 453.0, 21.0, 0.36, 0.085, 0.17, 0.223, 0.0002, 0.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.poireau, context.getString(R.string.poireau), categoryEnum.legumineux.index(),
                6.0, 25.0, 0.8, 3.3, 2.0, 3.0, 0.2, 0.0, 92.0,
                0.0, 4.2, 0.0, 0.5, 0.05, 0.05, 0.05, 0.05, 0.01, 0.0545, 0.0,
                25.3, 20.0, 8.4, 148.0, 3.0, 0.76, 0.211, 0.2, 0.2, 0.001, 0.2);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.nouilles_riz_nanchang, context.getString(R.string.nouilles_riz_nanchang), categoryEnum.feculent.index(),
                7.0, 347.0, 4.4, 60.3, 5.0, 1.0, 9.5, 3.0, 70.0,
                0.0, 0.0, 0.0, 0.2, 0.578, 0.421, 0.05, 0.533, 0.11, 0.0, 0.0,
                20.0, 161.0, 52.0, 120.0, 772.0, 4.73, 0.167, 1.4, 1.357, 0.043, 0.0,
                false, false);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.sucrerie_marron_glace, context.getString(R.string.sucrerie_marron_glace), categoryEnum.sucrerie.index(),
                7.0, 318.0, 1.0, 75.0, 46.0, 4.0, 0.9, 0.2, 20.0,
                0.0, 0.0, 0.0, 0.0, 0.02, 0.1, 1.5, 0.4, 0.2, 0.058, 0.0,
                21.0, 51.0, 24.0, 359.0, 52.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0);

        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.sucrerie_gateau_quatre_quart, context.getString(R.string.sucrerie_gateau_quatre_quart), categoryEnum.sucrerie.index(),
                7.0, 344.0, 6.0, 55.0, 28.0, 2.6, 10.0, 1.0, 23.0,
                0.035, 0.1, 0.0004, 0.0, 0.143, 0.26, 0.38, 0.48, 0.038, 0.039, 0.00024,
                84.9, 221.0, 8.14, 83.5, 1340.0 / 2.5, 0.7, 0.05, 0.5, 0.17, 0.007, 9.0);


        // CONDIMENT :

        // sel :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_sel, context.getString(R.string.sel), categoryEnum.condiment.index(),
                7.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 100000.0 / 2.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        // sucre :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_sucre, context.getString(R.string.sucre), categoryEnum.condiment.index(),
                7.0, 400.0, 0.0, 100.0, 100.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        // huile d'olive :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_huile_olive, context.getString(R.string.huile_olive), categoryEnum.condiment.index(),
                7.0, 900.0, 0.0, 0.0, 0.0, 0.0, 100.0, 13.8, 0.0,
                0.0, 0.0, 0.0, 25.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 2.0, 0.5, 0.0, 0.0, 0.0, 0.021, 0.0);

        // huile de tournesol :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_huile_tournesol, context.getString(R.string.huile_tournesol), categoryEnum.condiment.index(),
                7.0, 900.0, 0.0, 0.0, 0.0, 0.0, 100.0, 10.4, 0.0,
                0.0, 0.0, 0.0, 75.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 1.1, 0.0, 0.0, 0.0, 0.0, 0.010, 0.0);

        // huile de noix :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_huile_noix, context.getString(R.string.huile_noix), categoryEnum.condiment.index(),
                7.0, 900.0, 0.0, 0.0, 0.0, 0.0, 100.0, 9.8, 0.0,
                0.0, 0.0, 0.0, 6.9, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        // huile de coco :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_huile_coco, context.getString(R.string.huile_coco), categoryEnum.condiment.index(),
                7.0, 900.0, 0.0, 0.0, 0.0, 0.0, 100.0, 86.5, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        // sauce tomate :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_sauce_tomate, context.getString(R.string.sauce_tomate), categoryEnum.condiment.index(),
                7.0, 75.0, 3.0, 9.0, 6.0, 1.5, 3.5, 1.0, 80.0,
                0.0, 5.5, 0.0, 0.0, 0.0, 0.1, 0.9, 0.3, 0.1, 0.011, 0.0,
                22.8, 39.0, 19.2, 1500.0 / 2.5, 668.0, 0.8, 0.1, 0.2, 0.1, 0.001, 5.0);

        // sauce Pesto :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_sauce_pesto, context.getString(R.string.sauce_pesto), categoryEnum.condiment.index(),
                7.0, 290.0, 4.5, 3.5, 0.9, 2.0, 30.0, 5.0, 80.0,
                0.013, 0.5, 0.0005, 18.3, 0.1, 0.1, 0.6, 0.2, 0.1, 0.0297, 0.0,
                128.0, 80.3, 47.8, 196.0, 2500.0 / 2.5, 1.5, 0.4, 1.1, 0.5, 0.001, 5.0);

        // sauce Pesto ROSSO :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_sauce_pesto_roso, context.getString(R.string.sauce_pesto_rosso), categoryEnum.condiment.index(),
                7.0, 330.0, 4.5, 10.5, 7.0, 2.0, 30.0, 5.0, 80.0,
                0.013, 0.5, 0.0005, 18.3, 0.1, 0.1, 0.6, 0.2, 0.1, 0.0297, 0.0,
                128.0, 80.3, 47.8, 196.0, 2500.0 / 2.5, 1.5, 0.4, 1.1, 0.5, 0.001, 5.0);

        // sauce ketchup :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_sauce_ketchup, context.getString(R.string.sauce_ketchup), categoryEnum.condiment.index(),
                7.0, 120.0, 1.3, 27.0, 20.0, 1.0, 0.1, 0.05, 66.0,
                0.0, 15.0, 0.0, 1.5, 0.0, 0.1, 1.2, 0.2, 0.1, 0.0067, 0.0,
                21.4, 36.8, 20.3, 490.0, 2600.0 / 2.5, 0.8, 0.1, 0.1, 0.1, 0.005, 1.5);

        // sauce mayonnaise :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_sauce_mayonnaise, context.getString(R.string.sauce_mayonnaise), categoryEnum.condiment.index(),
                7.0, 725.0, 1.3, 1.6, 1.6, 1.0, 79.0, 8.0, 16.0,
                0.0085, 0.5, 0.0005, 27.6, 0.05, 0.05, 0.1, 0.2, 0.0, 0.016, 0.0004,
                10.3, 23.0, 3.1, 34.9, 1500.0 / 2.5, 0.6, 0.0, 0.2, 0.0, 0.005, 30.0);

        // sauce moutarde :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.assaisonnement_sauce_moutarde, context.getString(R.string.sauce_moutarde), categoryEnum.condiment.index(),
                7.0, 200.0, 4.3, 6.5, 5.5, 2.2, 17.0, 1.6, 68.0,
                0.0, 38.2, 0.0, 1.1, 0.2, 0.1, 0.7, 0.2, 0.1, 0.018, 0.0004,
                91.2, 196.0, 77.9, 216.0, 4300.0 / 2.5, 3.8, 0.2, 1.3, 0.7, 0.016, 22.1);

        // ALCOOL :

        // alcool biere :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.alcool_biere, context.getString(R.string.alcool_biere), categoryEnum.alcool.index(),
                7.0, 60.0, 0.5, 5.0, 0.2, 0.0, 0.0, 0.0, 94.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 30.0 / 2.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        // alcool vin rouge :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.alcool_vin_rouge, context.getString(R.string.alcool_vin_rouge), categoryEnum.alcool.index(),
                7.0, 70.0, 0.1, 0.2, 0.15, 0.0, 0.0, 0.0, 87.0,
                0.0, 0.0, 0.0, 0.0, 0.002, 0.026, 0.1, 0.12, 0.03, 0.001, 0.0,
                6.36, 13.0, 8.85, 110.0, 3.0, 0.16, 0.0, 0.16, 0.089, 0.0001, 1.0);

        // alcool vin rose :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.alcool_vin_rose, context.getString(R.string.alcool_vin_rose), categoryEnum.alcool.index(),
                7.0, 72.0, 0.2, 1.8, 0.2, 0.0, 0.0, 0.0, 89.0,
                0.0, 0.0, 0.0, 0.0, 0.002, 0.026, 0.1, 0.05, 0.1, 0.0003, 0.0,
                12.0, 6.0, 7.0, 75.0, 4.0, 1.0, 0.01, 0.01, 0.089, 0.0002, 1.0);

        // alcool vin blanc :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.alcool_vin_blanc, context.getString(R.string.alcool_vin_blanc), categoryEnum.alcool.index(),
                7.0, 77.0, 0.1, 3.9, 0.5, 0.0, 0.0, 0.0, 89.0,
                0.0, 0.0, 0.0, 0.0, 0.002, 0.026, 0.1, 0.05, 0.1, 0.0003, 0.0,
                5.6, 35.7, 5.7, 74.0, 1.7, 0.6, 0.01, 0.1, 0.089, 0.001, 1.0);

        // alcool champagne :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.alcool_champagne, context.getString(R.string.alcool_champagne), categoryEnum.alcool.index(),
                7.0, 82.0, 0.3, 2.8, 1.4, 0.0, 0.0, 0.0, 87.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.01, 0.1, 0.05, 0.05, 0.0, 0.0,
                7.1, 7.0, 8.0, 22.8, 1.9, 0.1, 0.0, 0.0, 0.05, 0.005, 1.0);

        // alcool fort (whisky) :
        addSingleBitmapToBiglistLibraryNEWV2(context, R.drawable.alcool_fort, context.getString(R.string.alcool_fort), categoryEnum.alcool.index(),
                7.0, 238.0, 0.2, 10.0, 1.0, 0.0, 0.0, 0.0, 63.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.7, 4.9, 0.1, 2.2, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0);


        // Implementation des listes foodList dans allFoodList :

        TransitionClass.setAllFoodList(bigAllFoodList);

        addfoodCategoryList();
        globalFoodCategoryList.add(foodCategoryAllFoodList);
        globalFoodCategoryList.add(foodCategoryVegetarienFoodList);
        globalFoodCategoryList.add(foodCategoryVegetalienFoodList);
        globalFoodCategoryList.add(foodCategoryNoPorkAndAlcoholFoodList);

        globalVisualFoodCategoryList.add(visualFoodCategoryAllFoodList);
        globalVisualFoodCategoryList.add(visualFoodCategoryVegetarienFoodList);
        globalVisualFoodCategoryList.add(visualFoodCategoryVegetalienFoodList);
        globalVisualFoodCategoryList.add(visualFoodCategoryNoPorkAndAlcoholFoodList);

        // Creation MEAL-FOOD LIST :
        createMealLibrary(context);

        // Creation Emotion :
        createEmotionLibrary();

        // Creation MorphologieLib :
        createMorphologieLibrary();

        // Creation QuantityIntResIMGLib :
        createIntResImgQuantityFoodLibrary();

        // Creation DayMomentIntResIMGLib :
        createIntResImgPeriodeDayMomentLibrary();

        // Creation PouceFoodIntResIMGLib :
        createIntIMGPouceFood();

    }

    //----------------------------------------------------------------------------------------------
    // My Methods :

    //NORMAL FOOD:
    private void addSingleBitmapToBiglistLibraryNEWV2(Context context, int ressource, String name, Integer category, Double acidityPH,
                                                      Double calorie, Double proteine, Double glucide, Double sucre, Double fibre,
                                                      Double lipide, Double acideGrasSature, Double eau,
                                                      Double vitamineA, Double vitamineC, Double vitamineD, Double vitamineE,
                                                      Double vitamineB1, Double vitamineB2, Double vitamineB3, Double vitamineB5,
                                                      Double vitamineB6, Double vitamineB9, Double vitamineB12,
                                                      Double calcium, Double phosphore, Double magnesium, Double potassium, Double sodium, Double fer,
                                                      Double cuivre, Double zinc, Double manganese, Double selenium, Double iode) {

        // creation base de donnees :

        LibraryArrayBitmapDrawingRessources.context = context;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), ressource);
        listeRessourceRawBitmap.add(ressource);
        bitmapFoodList.add(bitmap);
        nameList.add(name);
        categoryList.add(category);
        calorieList.add(calorie);
        acidityPHList.add(acidityPH);
        eauPourcentageList.add(eau);
        proteinePourcentageList.add(proteine);
        lipidePourcentageList.add(lipide);
        acideGrasSaturePourcentageList.add(acideGrasSature);
        glucidePourcentageList.add(glucide);
        sucrePourcentageList.add(sucre);
        fibrePourcentageList.add(fibre);
        vitamineAPourcentageList.add(vitamineA);
        vitamineB1PourcentageList.add(vitamineB1);
        vitamineB2PourcentageList.add(vitamineB2);
        vitamineB3PourcentageList.add(vitamineB3);
        vitamineB5PourcentageList.add(vitamineB5);
        vitamineB6PourcentageList.add(vitamineB6);
        vitamineB9PourcentageList.add(vitamineB9);
        vitamineB12PourcentageList.add(vitamineB12);
        vitamineCPourcentageList.add(vitamineC);
        vitamineDPourcentageList.add(vitamineD);
        vitamineEPourcentageList.add(vitamineE);
        calciumPourcentageList.add(calcium);
        phosphorePourcentageList.add(phosphore);
        magnesiumPourcentageList.add(magnesium);
        potassiumPourcentageList.add(potassium);
        ferPourcentageList.add(fer);
        cuivrePourcentageList.add(cuivre);
        zincPourcentageList.add(zinc);
        manganesePourcentageList.add(manganese);
        seleniumPourcentageList.add(selenium);
        iodePourcentageList.add(iode);
        sodiumPourcentageList.add(sodium);
        selPourcentageList.add(sodium * 2.5);

        // creation aliment fruits legumes et fruits secs :

        bigAllFoodList.add(new FoodClass(context, ressource,
                bitmap, name, category, acidityPH, calorie, proteine, glucide,
                sucre, fibre, lipide, acideGrasSature, eau,
                vitamineA, vitamineB1, vitamineB2, vitamineB3, vitamineB5,
                vitamineB6, vitamineB9, vitamineB12, vitamineC, vitamineD, vitamineE,
                calcium, phosphore, magnesium, potassium, sodium, fer,
                cuivre, zinc, manganese, selenium, iode, true, true, false));

    }

    // isVEGETARIEN isVEGAN ET isPORK FOOD :
    private void addSingleBitmapToBiglistLibraryNEWV2(Context context, int ressource, String name, Integer category, Double acidityPH,
                                                      Double calorie, Double proteine, Double glucide, Double sucre, Double fibre,
                                                      Double lipide, Double acideGrasSature, Double eau,
                                                      Double vitamineA, Double vitamineC, Double vitamineD, Double vitamineE,
                                                      Double vitamineB1, Double vitamineB2, Double vitamineB3, Double vitamineB5,
                                                      Double vitamineB6, Double vitamineB9, Double vitamineB12,
                                                      Double calcium, Double phosphore, Double magnesium, Double potassium, Double sodium, Double fer,
                                                      Double cuivre, Double zinc, Double manganese, Double selenium, Double iode,
                                                      boolean isVegetarien, boolean isVegetalien) {

        // creation base de donnees :

        LibraryArrayBitmapDrawingRessources.context = context;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), ressource);
        listeRessourceRawBitmap.add(ressource);
        bitmapFoodList.add(bitmap);
        nameList.add(name);
        categoryList.add(category);
        calorieList.add(calorie);
        acidityPHList.add(acidityPH);
        eauPourcentageList.add(eau);
        proteinePourcentageList.add(proteine);
        lipidePourcentageList.add(lipide);
        acideGrasSaturePourcentageList.add(acideGrasSature);
        glucidePourcentageList.add(glucide);
        sucrePourcentageList.add(sucre);
        fibrePourcentageList.add(fibre);
        vitamineAPourcentageList.add(vitamineA);
        vitamineB1PourcentageList.add(vitamineB1);
        vitamineB2PourcentageList.add(vitamineB2);
        vitamineB3PourcentageList.add(vitamineB3);
        vitamineB5PourcentageList.add(vitamineB5);
        vitamineB6PourcentageList.add(vitamineB6);
        vitamineB9PourcentageList.add(vitamineB9);
        vitamineB12PourcentageList.add(vitamineB12);
        vitamineCPourcentageList.add(vitamineC);
        vitamineDPourcentageList.add(vitamineD);
        vitamineEPourcentageList.add(vitamineE);
        calciumPourcentageList.add(calcium);
        phosphorePourcentageList.add(phosphore);
        magnesiumPourcentageList.add(magnesium);
        potassiumPourcentageList.add(potassium);
        ferPourcentageList.add(fer);
        cuivrePourcentageList.add(cuivre);
        zincPourcentageList.add(zinc);
        manganesePourcentageList.add(manganese);
        seleniumPourcentageList.add(selenium);
        iodePourcentageList.add(iode);
        sodiumPourcentageList.add(sodium);
        selPourcentageList.add(sodium * 2.5);

        // creation aliment :

        bigAllFoodList.add(new FoodClass(context, ressource,
                bitmap, name, category, acidityPH, calorie, proteine, glucide,
                sucre, fibre, lipide, acideGrasSature, eau,
                vitamineA, vitamineB1, vitamineB2, vitamineB3, vitamineB5,
                vitamineB6, vitamineB9, vitamineB12, vitamineC, vitamineD, vitamineE,
                calcium, phosphore, magnesium, potassium, sodium, fer,
                cuivre, zinc, manganese, selenium, iode, isVegetarien, isVegetalien, false));

    }

    private void addSingleBitmapToBiglistLibraryNEWV2(Context context, int ressource, String name, Integer category, Double acidityPH,
                                                      Double calorie, Double proteine, Double glucide, Double sucre, Double fibre,
                                                      Double lipide, Double acideGrasSature, Double eau,
                                                      Double vitamineA, Double vitamineC, Double vitamineD, Double vitamineE,
                                                      Double vitamineB1, Double vitamineB2, Double vitamineB3, Double vitamineB5,
                                                      Double vitamineB6, Double vitamineB9, Double vitamineB12,
                                                      Double calcium, Double phosphore, Double magnesium, Double potassium, Double sodium, Double fer,
                                                      Double cuivre, Double zinc, Double manganese, Double selenium, Double iode,
                                                      boolean isVegetarien, boolean isVegetalien, boolean isPork) {

        // creation base de donnees :

        LibraryArrayBitmapDrawingRessources.context = context;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), ressource);
        listeRessourceRawBitmap.add(ressource);
        bitmapFoodList.add(bitmap);
        nameList.add(name);
        categoryList.add(category);
        calorieList.add(calorie);
        acidityPHList.add(acidityPH);
        eauPourcentageList.add(eau);
        proteinePourcentageList.add(proteine);
        lipidePourcentageList.add(lipide);
        acideGrasSaturePourcentageList.add(acideGrasSature);
        glucidePourcentageList.add(glucide);
        sucrePourcentageList.add(sucre);
        fibrePourcentageList.add(fibre);
        vitamineAPourcentageList.add(vitamineA);
        vitamineB1PourcentageList.add(vitamineB1);
        vitamineB2PourcentageList.add(vitamineB2);
        vitamineB3PourcentageList.add(vitamineB3);
        vitamineB5PourcentageList.add(vitamineB5);
        vitamineB6PourcentageList.add(vitamineB6);
        vitamineB9PourcentageList.add(vitamineB9);
        vitamineB12PourcentageList.add(vitamineB12);
        vitamineCPourcentageList.add(vitamineC);
        vitamineDPourcentageList.add(vitamineD);
        vitamineEPourcentageList.add(vitamineE);
        calciumPourcentageList.add(calcium);
        phosphorePourcentageList.add(phosphore);
        magnesiumPourcentageList.add(magnesium);
        potassiumPourcentageList.add(potassium);
        ferPourcentageList.add(fer);
        cuivrePourcentageList.add(cuivre);
        zincPourcentageList.add(zinc);
        manganesePourcentageList.add(manganese);
        seleniumPourcentageList.add(selenium);
        iodePourcentageList.add(iode);
        sodiumPourcentageList.add(sodium);
        selPourcentageList.add(sodium * 2.5);

        // creation aliment fruits legumes et fruits secs :

        bigAllFoodList.add(new FoodClass(context, ressource,
                bitmap, name, category, acidityPH, calorie, proteine, glucide,
                sucre, fibre, lipide, acideGrasSature, eau,
                vitamineA, vitamineB1, vitamineB2, vitamineB3, vitamineB5,
                vitamineB6, vitamineB9, vitamineB12, vitamineC, vitamineD, vitamineE,
                calcium, phosphore, magnesium, potassium, sodium, fer,
                cuivre, zinc, manganese, selenium, iode, false, false, isPork));

    }

    private void addfoodCategoryList() {
        addListCategoryIfNotEmpty(TransitionClass.allFoodList, foodCategoryAllFoodList, visualFoodCategoryAllFoodList);
        addListCategoryIfNotEmpty(TransitionClass.vegetarianAllFoodList, foodCategoryVegetarienFoodList, visualFoodCategoryVegetarienFoodList);
        addListCategoryIfNotEmpty(TransitionClass.veganAllFoodList, foodCategoryVegetalienFoodList, visualFoodCategoryVegetalienFoodList);
        addListCategoryIfNotEmpty(TransitionClass.noPorkAndAlcoholFoodList, foodCategoryNoPorkAndAlcoholFoodList, visualFoodCategoryNoPorkAndAlcoholFoodList);

    }

    private void addListCategoryIfNotEmpty(ArrayList<FoodClass> foodListLibrary, ArrayList<ArrayList<FoodClass>> foodListByCategory, ArrayList<FoodClass> visualFoodCategoryList) {

        ArrayList<FoodClass> foodLegumeListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodFruitListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodFeculentListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodProteineListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodSucrerieListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodFruitSecListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodPlanteListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodFritureListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodYaourtListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodLaitListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodOeufListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodViandeListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodPoissonListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodFruitDeMerListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodBoissonListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodChampignonListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodFromageListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodCerealesListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodAlcoolListLibrary = new ArrayList<>();
        ArrayList<FoodClass> foodCondimentListLibrary = new ArrayList<>();

        for (FoodClass food : foodListLibrary) {
            if (food.category == categoryEnum.legumineux.ordinal()) {
                foodLegumeListLibrary.add(food);
            } else if (food.category == categoryEnum.fruit.ordinal()) {
                foodFruitListLibrary.add(food);
            } else if (food.category == categoryEnum.feculent.ordinal()) {
                foodFeculentListLibrary.add(food);
            } else if (food.category == categoryEnum.proteine.ordinal()) {
                foodProteineListLibrary.add(food);
            } else if (food.category == categoryEnum.sucrerie.ordinal()) {
                foodSucrerieListLibrary.add(food);
            } else if (food.category == categoryEnum.fruitSec.ordinal()) {
                foodFruitSecListLibrary.add(food);
            } else if (food.category == categoryEnum.plante.ordinal()) {
                foodPlanteListLibrary.add(food);
            } else if (food.category == categoryEnum.friture.ordinal()) {
                foodFritureListLibrary.add(food);
            } else if (food.category == categoryEnum.NotUSEDyaourt.ordinal()) {
                foodYaourtListLibrary.add(food);
            } else if (food.category == categoryEnum.produit_laitier.ordinal()) {
                foodLaitListLibrary.add(food);
            } else if (food.category == categoryEnum.oeuf.ordinal()) {
                foodOeufListLibrary.add(food);
            } else if (food.category == categoryEnum.viande.ordinal()) {
                foodViandeListLibrary.add(food);
            } else if (food.category == categoryEnum.poisson.ordinal()) {
                foodPoissonListLibrary.add(food);
            } else if (food.category == categoryEnum.fruitDeMer.ordinal()) {
                foodFruitDeMerListLibrary.add(food);
            } else if (food.category == categoryEnum.boisson.ordinal()) {
                foodBoissonListLibrary.add(food);
            } else if (food.category == categoryEnum.champignon.ordinal()) {
                foodChampignonListLibrary.add(food);
            } else if (food.category == categoryEnum.fromage.ordinal()) {
                foodFromageListLibrary.add(food);
            } else if (food.category == categoryEnum.cereales.ordinal()) {
                foodCerealesListLibrary.add(food);
            } else if (food.category == categoryEnum.alcool.ordinal()) {
                foodAlcoolListLibrary.add(food);
            } else if (food.category == categoryEnum.condiment.ordinal()) {
                foodCondimentListLibrary.add(food);
            }
        }

        if (foodLegumeListLibrary.size() > 0) {

            if (foodLegumeListLibrary.size() > 3) {
                visualFoodCategoryList.add(foodLegumeListLibrary.get(3));
            } else {
                visualFoodCategoryList.add(foodLegumeListLibrary.get(0));
            }

            Collections.sort(foodLegumeListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodLegumeListLibrary);

        }
        if (foodFruitListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodFruitListLibrary.get(0));

            Collections.sort(foodFruitListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodFruitListLibrary);
        }
        if (foodFeculentListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodFeculentListLibrary.get(0));

            Collections.sort(foodFeculentListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodFeculentListLibrary);
        }
        if (foodProteineListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodProteineListLibrary.get(0));

            Collections.sort(foodProteineListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodProteineListLibrary);
        }
        if (foodSucrerieListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodSucrerieListLibrary.get(0));

            Collections.sort(foodSucrerieListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodSucrerieListLibrary);
        }
        if (foodFruitSecListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodFruitSecListLibrary.get(0));

            Collections.sort(foodFruitSecListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodFruitSecListLibrary);
        }
        if (foodCerealesListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodCerealesListLibrary.get(0));

            Collections.sort(foodCerealesListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodCerealesListLibrary);
        }
        if (foodPlanteListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodPlanteListLibrary.get(0));

            Collections.sort(foodPlanteListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodPlanteListLibrary);
        }
        if (foodFritureListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodFritureListLibrary.get(0));

            Collections.sort(foodFritureListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodFritureListLibrary);
        }
        if (foodLaitListLibrary.size() > 0) {
            if (foodLaitListLibrary.size() > 2) {
                visualFoodCategoryList.add(foodLaitListLibrary.get(2));
            } else {
                visualFoodCategoryList.add(foodLaitListLibrary.get(0));
            }

            Collections.sort(foodLaitListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodLaitListLibrary);
        }
        if (foodFromageListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodFromageListLibrary.get(0));

            Collections.sort(foodFromageListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodFromageListLibrary);
        }
        if (foodYaourtListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodYaourtListLibrary.get(0));

            Collections.sort(foodYaourtListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodYaourtListLibrary);
        }
        if (foodOeufListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodOeufListLibrary.get(0));

            Collections.sort(foodOeufListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodOeufListLibrary);
        }
        if (foodViandeListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodViandeListLibrary.get(0));

            Collections.sort(foodViandeListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodViandeListLibrary);
        }
        if (foodPoissonListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodPoissonListLibrary.get(0));

            Collections.sort(foodPoissonListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodPoissonListLibrary);
        }
        if (foodFruitDeMerListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodFruitDeMerListLibrary.get(0));

            Collections.sort(foodFruitDeMerListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodFruitDeMerListLibrary);
        }
        if (foodBoissonListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodBoissonListLibrary.get(0));

            Collections.sort(foodBoissonListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodBoissonListLibrary);
        }
        if (foodChampignonListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodChampignonListLibrary.get(0));

            Collections.sort(foodChampignonListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodChampignonListLibrary);
        }
        if (foodAlcoolListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodAlcoolListLibrary.get(0));

            Collections.sort(foodAlcoolListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodAlcoolListLibrary);
        }
        if (foodCondimentListLibrary.size() > 0) {
            visualFoodCategoryList.add(foodCondimentListLibrary.get(0));

            Collections.sort(foodCondimentListLibrary, FoodClass.comparatorCaloriesCroissant.thenComparing(FoodClass.comparatorName));
            foodListByCategory.add(foodCondimentListLibrary);
        }

    }

    private void createMealLibrary(Context context) {

        // PETIT DEJEUNER :
        // ----------------

        // MUESLI VITAMINE :
        String titre = context.getString(R.string.repas_petitDejeuner_muesliVitamine);
        int rDrawableImage = R.drawable.repas_petit_dejeuner_01;
        int typeOfMeal = RepasMakerClass.enumTypeOfMeal.petitDejeuner.ordinal();
        String[] arrayFoodNameInMeal = new String[]{
                context.getString(R.string.cereales_muesli_nature),
                context.getString(R.string.lait_ecreme),
                context.getString(R.string.jusOrange),
                context.getString(R.string.kiwi),
                context.getString(R.string.chocolat)};

        int[] arrayFoodQuantityInMeal = new int[]{
                200,
                250,
                250,
                200,
                25};

        FoodClass[] arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        String description = mealDescriptionCreator(arrayFoodInMeal);
        RepasMakerClass repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // FRUITS ET GATEAU BEURRE :
        titre = context.getString(R.string.repas_petitDejeuner_fruits_gateau_beurre_01);
        rDrawableImage = R.drawable.repas_petit_dejeuner_02_fruits_gateau;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.petitDejeuner.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.jusOrange),
                context.getString(R.string.kiwi),
                context.getString(R.string.citron),
                context.getString(R.string.gateauChocolat)};

        arrayFoodQuantityInMeal = new int[]{
                250,
                200,
                100,
                100};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // FRUIT ET GATEAU OEUF :
        titre = context.getString(R.string.repas_petitDejeuner_fruits_gateau_oeuf_01);
        rDrawableImage = R.drawable.repas_petit_dejeuner_03_fruits_gateau_quatre_quart;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.petitDejeuner.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.jusOrange),
                context.getString(R.string.citron),
                context.getString(R.string.banane),
                context.getString(R.string.sucrerie_gateau_quatre_quart)};

        arrayFoodQuantityInMeal = new int[]{
                250,
                100,
                100,
                100};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // DEJEUNER :
        // ----------------

        // CASSOULET :
        titre = context.getString(R.string.repas_dejeuner_cassoulet);
        rDrawableImage = R.drawable.repas_dejeuner_01_cassoulet;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.dejeuner.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.eau_minerale),
                context.getString(R.string.haricot),
                context.getString(R.string.viande_saucisseToulouse),
                context.getString(R.string.oignon),
                context.getString(R.string.ail),
                context.getString(R.string.huile_olive)};
        arrayFoodQuantityInMeal = new int[]{
                500,
                200,
                100,
                50,
                25,
                25};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // PIZZA tomate, fromage, champignon :
        titre = context.getString(R.string.repas_dejeuner_pizza_tomate_fromage_champignon);
        rDrawableImage = R.drawable.repas_dejeuner_02_pizza_tomate_fromage_champignon;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.dejeuner.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.eau_minerale),
                context.getString(R.string.painBaguette),
                context.getString(R.string.champignon),
                context.getString(R.string.sauce_tomate),
                context.getString(R.string.fromage_gruyere),
                context.getString(R.string.huile_olive)};
        arrayFoodQuantityInMeal = new int[]{
                500,
                200,
                100,
                100,
                50,
                25};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // APRES MIDI :
        // ----------------

        // Fruits Orange et Kiwi :
        titre = context.getString(R.string.repas_apres_midi_fruit_orange_kiwi);
        rDrawableImage = R.drawable.repas_apres_midi_01_orange_kiwi;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.apresMidi.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.orange),
                context.getString(R.string.kiwi)};
        arrayFoodQuantityInMeal = new int[]{
                200,
                100};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // Fruits (Orange/Kiwi) et Yaourt Nature :
        titre = context.getString(R.string.repas_apres_midi_fruit_orange_kiwi_yaourt_nature);
        rDrawableImage = R.drawable.repas_apres_midi_02_orange_kiwi_yaourt_nature;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.apresMidi.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.orange),
                context.getString(R.string.kiwi),
                context.getString(R.string.yaourt_nature)};
        arrayFoodQuantityInMeal = new int[]{
                200,
                100,
                125};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // Fruits (Orange/Kiwi) et PainChoco :
        titre = context.getString(R.string.repas_apres_midi_fruit_orange_kiwi_pain_chocolat);
        rDrawableImage = R.drawable.repas_apres_midi_03_orange_kiwi_pain_chocolat;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.apresMidi.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.orange),
                context.getString(R.string.kiwi),
                context.getString(R.string.croissant)};
        arrayFoodQuantityInMeal = new int[]{
                200,
                100,
                75};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // Fruits (Orange/Kiwi) Yaourt Nature et PainChoco :
        titre = context.getString(R.string.repas_apres_midi_fruit_orange_kiwi_yaourt_nature_pain_chocolat);
        rDrawableImage = R.drawable.repas_apres_midi_04_orange_kiwi_pain_chocolat_yaourt_nature;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.apresMidi.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.orange),
                context.getString(R.string.kiwi),
                context.getString(R.string.yaourt_nature),
                context.getString(R.string.croissant)};
        arrayFoodQuantityInMeal = new int[]{
                200,
                100,
                125,
                75};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // DINNER :
        // ----------------

        // OEUFS et Pain :
        titre = context.getString(R.string.repas_dinner_oeuf_au_plat);
        rDrawableImage = R.drawable.repas_dinner_01_oeufs;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.dinner.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.eau_minerale),
                context.getString(R.string.oeufAuPlat),
                context.getString(R.string.painBaguette)};
        arrayFoodQuantityInMeal = new int[]{
                500,
                200,
                100};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

        // Poilee de Legumes et yaourt :
        titre = context.getString(R.string.repas_dinner_poilee_legumes_et_yaourt);
        rDrawableImage = R.drawable.repas_dinner_02_poilee_legumes_yaourt_nature;
        typeOfMeal = RepasMakerClass.enumTypeOfMeal.dinner.ordinal();
        arrayFoodNameInMeal = new String[]{
                context.getString(R.string.eau_minerale),
                context.getString(R.string.yaourt_nature),
                context.getString(R.string.haricotVert),
                context.getString(R.string.carotte),
                context.getString(R.string.champignon),
                context.getString(R.string.patate),
                context.getString(R.string.huile_olive)};
        arrayFoodQuantityInMeal = new int[]{
                500,
                125,
                50,
                50,
                50,
                50,
                5};

        arrayFoodInMeal = arrayFoodListInMealCreatorManager(arrayFoodNameInMeal, arrayFoodQuantityInMeal);
        description = mealDescriptionCreator(arrayFoodInMeal);
        repas = new RepasMakerClass(rDrawableImage, titre, description, typeOfMeal, arrayFoodInMeal);
        repasMakerList.add(repas);
        //------------------------------------------------------------------------------------------

    }

    private FoodClass[] arrayFoodListInMealCreatorManager(String[] arrayFoodNameInMeal, int[] arrayFoodQuantityInMeal) {

        FoodClass[] arrayFoodToReturn = new FoodClass[arrayFoodNameInMeal.length];

        for (int j = 0; j < arrayFoodNameInMeal.length; j++) {

            for (int i = 0; i < bigAllFoodList.size(); i++) {

                if (arrayFoodNameInMeal[j].equals(bigAllFoodList.get(i).name)) {

                    FoodClass cloneFood = new FoodClass(bigAllFoodList.get(i));
                    arrayFoodToReturn[j] = cloneFood;
                    break;
                }
            }
        }

        for (int i = 0; i < arrayFoodToReturn.length; i++) {
            arrayFoodToReturn[i].setQuantity(arrayFoodQuantityInMeal[i]);
        }

        return arrayFoodToReturn;
    }

    private String mealDescriptionCreator(FoodClass[] arrayFoodInMeal) {

        StringBuilder textToReturn = new StringBuilder();

        for (int i = 0; i < arrayFoodInMeal.length; i++) {

            String text = "-> " + arrayFoodInMeal[i].name + "\n";
            textToReturn.append(text);

        }

        return textToReturn.toString();
    }

    private void createEmotionLibrary() {

        // IMPORTANT penser a completer l'enumEMOTION dans TransitionClass a chaque modification

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_amical);
        rawBitmapImageEmotionList.add(R.drawable.emotion_amical);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_amical);
        nameEmotionList.add(context.getString(R.string.amical));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_amour);
        rawBitmapImageEmotionList.add(R.drawable.emotion_amour);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_amour);
        nameEmotionList.add(context.getString(R.string.amour));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_colere);
        rawBitmapImageEmotionList.add(R.drawable.emotion_colere);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_colere);
        nameEmotionList.add(context.getString(R.string.colere));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_content);
        rawBitmapImageEmotionList.add(R.drawable.emotion_content);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_content);
        nameEmotionList.add(context.getString(R.string.joie));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_content2);
        rawBitmapImageEmotionList.add(R.drawable.emotion_content2);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_content_2);
        nameEmotionList.add(context.getString(R.string.joie));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_deception);
        rawBitmapImageEmotionList.add(R.drawable.emotion_deception);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_deception);
        nameEmotionList.add(context.getString(R.string.deception));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_degouter);
        rawBitmapImageEmotionList.add(R.drawable.emotion_degouter);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_degout);
        nameEmotionList.add(context.getString(R.string.degouter));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_farceur);
        rawBitmapImageEmotionList.add(R.drawable.emotion_farceur);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_farceur);
        nameEmotionList.add(context.getString(R.string.farceur));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_interet);
        rawBitmapImageEmotionList.add(R.drawable.emotion_interet);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_interet);
        nameEmotionList.add(context.getString(R.string.interesse));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_machiavelique);
        rawBitmapImageEmotionList.add(R.drawable.emotion_machiavelique);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_machiavelique);
        nameEmotionList.add(context.getString(R.string.machiavelique));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_mort);
        rawBitmapImageEmotionList.add(R.drawable.emotion_mort);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_mort);
        nameEmotionList.add(context.getString(R.string.mort));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_neutre);
        rawBitmapImageEmotionList.add(R.drawable.emotion_neutre);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(-1);
        nameEmotionList.add(context.getString(R.string.neutre));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_pleurer);
        rawBitmapImageEmotionList.add(R.drawable.emotion_pleurer);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_pleurer);
        nameEmotionList.add(context.getString(R.string.pleurer));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_rire);
        rawBitmapImageEmotionList.add(R.drawable.emotion_rire);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_amical);
        nameEmotionList.add(context.getString(R.string.rire));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_sourire);
        rawBitmapImageEmotionList.add(R.drawable.emotion_sourire);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_machiavelique);
        nameEmotionList.add(context.getString(R.string.souriant));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_stress);
        rawBitmapImageEmotionList.add(R.drawable.emotion_stress);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_stress);
        nameEmotionList.add(context.getString(R.string.stresser));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_taquin);
        rawBitmapImageEmotionList.add(R.drawable.emotion_taquin);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_taquin);
        nameEmotionList.add(context.getString(R.string.taquin));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_tendresse);
        rawBitmapImageEmotionList.add(R.drawable.emotion_tendresse);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_tendresse);
        nameEmotionList.add(context.getString(R.string.tendre));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_tristesse);
        rawBitmapImageEmotionList.add(R.drawable.emotion_tristesse);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_triste);
        nameEmotionList.add(context.getString(R.string.triste));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.emotion_vexe);
        rawBitmapImageEmotionList.add(R.drawable.emotion_vexe);
        bitmapEmotionList.add(bitmap);
        soundEmotionList.add(R.raw.emotion_vexer);
        nameEmotionList.add(context.getString(R.string.vexer));

    }

    private void createMorphologieLibrary() {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_femme_fine);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_femme_moyenne);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_femme_solide);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_femme_fine_selected);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_femme_moyenne_selected);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_femme_solide_selected);
        bitmapMorphologieList.add(bitmap);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_homme_fin);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_homme_moyen);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_homme_solide);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_homme_fin_selected);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_homme_moyen_selected);
        bitmapMorphologieList.add(bitmap);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.morphologie_homme_solide_selected);
        bitmapMorphologieList.add(bitmap);
    }

    private void createIntResImgQuantityFoodLibrary() {
        int i = R.drawable.balance_quantite_petite;
        intQuantityFoodImageList.add(i);
        i = R.drawable.balance_quantite_moyenne;
        intQuantityFoodImageList.add(i);
        i = R.drawable.balance_quantite_grande;
        intQuantityFoodImageList.add(i);

        i = R.drawable.balance_selected_quantite_petite;
        intQuantityFoodImageList.add(i);
        i = R.drawable.balance_selected_quantite_moyenne;
        intQuantityFoodImageList.add(i);
        i = R.drawable.balance_selected_quantite_grande;
        intQuantityFoodImageList.add(i);
    }

    private void createIntResImgPeriodeDayMomentLibrary() {
        intIMGDayMomentArray =
                new int[]{R.drawable.periode_nuit,
                        R.drawable.periode_matin,
                        R.drawable.periode_midi,
                        R.drawable.periode_apres_midi,
                        R.drawable.periode_soir};

    }

    private void createIntIMGPouceFood() {
        intIMGPouceFood = new int[]{R.drawable.pouce_vert, R.drawable.pouce_rouge};
    }

}
