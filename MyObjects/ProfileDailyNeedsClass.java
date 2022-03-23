package fr.maxime.mygame01.MyObjects;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;

public class ProfileDailyNeedsClass {

    private Amicloon amicoon = null;
    private int idNumber;

    private String pseudo;
    private Integer sex;                                                                            // 0 male, 1 femme
    private Integer age;
    private Integer taille;                                                                         // en cm
    private Integer morphologie;                                                                    // 1 fin | 2 moyen | 3 solide
    private Integer poidsIdeal;                                                                     // en kg
    private Integer typeAlimentation = 0;                                                           // 0 noraml | 1 vegetarien | 2 vegetalien
    private final Double[] arrayDailyActivityCoefficient = {1.375, 1.55, 1.64, 1.82};                             // sedentaire / legere / moderee / intense

    private ArrayList<String> dailyNeedSTRINGlist = new ArrayList<>();                              // String about elements (ex: XXX + "kCal"...) enumElementsListe Dans LibraryArrayBitmapDrawingRessources      >
    private ArrayList<Double> dailyNeedDOUBLEValuesList = new ArrayList<>();                        // Double values about elements (ex: Calories...) enumElementsListe Dans LibraryArrayBitmapDrawingRessources   >

    public ArrayList<SuiviAlimentaireClass> arrayListSuiviAlimentaireClass = new ArrayList<>();    // liste du suivi des aliments manges selon date et la periode de la journee;

    public enum enumValuesDailyNeed {
        CALORIES, LIPIDES, ACIDESGRASSATURES, GLUCIDES, SUCRE, FIBRE, PROTEINE, SEL,
        VITAMINE_A, VITAMINE_B1, VITAMINE_B2, VITAMINE_B3, VITAMINE_B5, VITAMINE_B6, VITAMINE_B9, VITAMINE_B12, VITAMINE_C, VITAMINE_D, VITAMINE_E,
        EAU, CALCIUM, CUIVRE, FER, IODE, MAGNESIUM, MANGANESE, PHOSPHORE, POTASSIUM, SELENIUM, SODIUM, ZINC;
    }

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public ProfileDailyNeedsClass(Context context, String pseudo, Integer sex, Integer age, Integer taille, Integer morphologie, int idNumber) {

        this.idNumber = idNumber;
        this.pseudo = pseudo;
        this.sex = sex;
        this.age = age;
        this.taille = taille;
        this.morphologie = morphologie;

        setCalculPoidsIdeal(context);
    }

    // ConstructorDeserialized :

    public ProfileDailyNeedsClass(Context context, String pseudo, Integer sex, Integer age, Integer taille, Integer morphologie, int idNumber, AmicloonSerialized amicloonSerialized) {

        this.idNumber = idNumber;
        this.pseudo = pseudo;
        this.sex = sex;
        this.age = age;
        this.taille = taille;
        this.morphologie = morphologie;

        setCalculPoidsIdeal(context);

        try {
            this.amicoon = new Amicloon(pseudo, amicloonSerialized, dailyNeedDOUBLEValuesList);
        }
        catch (Exception e){
            this.amicoon = null;
            Log.i("MAX#ERROR#"," problem : " + e.getMessage()
            + "\n Localisation : " + e.getLocalizedMessage());
        }

    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    public static int getGenerateIdNumberNewProfile() {

        boolean isbreak = false;
        int intIdToReturn = 0;

        // de id 0 a nombre de profiles max :
        for (int i = 0; i < TransitionClass.MAXPROFILENUMBER + 1; i++) {

            // pour tous les profiles deja existants :
            for (int j = 0; j < TransitionClass.myLib.listDailyNeedsProfil.size(); j++) {
                isbreak = false;
                if (TransitionClass.myLib.listDailyNeedsProfil.get(j).getIdNumber() == i) {
                    isbreak = true;
                    break;
                }

            }

            if (!isbreak){
                intIdToReturn = i;
                break;
            }

        }
        // nombre Max de profiles : plus id disponible
        if (isbreak){
            return -1;
        }
        // renvoie id non utilise :
        else {
            return intIdToReturn;
        }

    }

    public void getCheckListSuiviAlimentaire() {

        ArrayList<SuiviAlimentaireClass> listSuiviToErase = new ArrayList<>();

        for (SuiviAlimentaireClass suiviAlimentaire : arrayListSuiviAlimentaireClass) {

            if (suiviAlimentaire.getArrayListFoodEaten().size() == 0) {
                listSuiviToErase.add(suiviAlimentaire);
            }

        }

        for (SuiviAlimentaireClass suiviAlimentaireToErase : listSuiviToErase) {

            arrayListSuiviAlimentaireClass.remove(suiviAlimentaireToErase);

        }
    }

    public void setCalculPoidsIdeal(Context context) {
        calculPoidsIdealFormuleDeBroca(taille, age, morphologie);
        calculDailyNeedDOUBLEValues(context);
    }

    private void calculPoidsIdealFormuleDeLorentz(int sexe, int tall) {
        double poids;
        // homme :
        if (sexe == 0) {
            poids = tall - 100 - ((tall - 150) / 4.0);
        }
        // femme
        else {
            poids = tall - 100 - ((tall - 150) / 2.5);
        }
        poidsIdeal = (int) Math.round(poids);
    }

    private void calculPoidsIdealFormuleDeCreff(int tall, int age) {
        double poids;

        poids = tall - 100 + (((float) age / 10) * 0.9);

        poidsIdeal = (int) Math.round(poids);
    }

    private void calculPoidsIdealFormuleDeBroca(int tall, int age, int morphologie) {
        double poids = 1.0;

        // morphologie svelte :
        if (morphologie == 1) {
            poids = (tall - 100 + ((float) age / 10)) * 0.9 * 0.9;
        }
        // morphologie normale :
        else if (morphologie == 2) {
            poids = (tall - 100 + ((float) age / 10)) * 0.9;
        }
        // morphologie epaisse :
        else if (morphologie == 3) {
            poids = (tall - 100 + ((float) age / 10)) * 0.9 * 1.1;
        }

        poidsIdeal = (int) Math.round(poids);
    }

    public void calculDailyNeedDOUBLEValues(Context context) {

        dailyNeedSTRINGlist.clear();
        dailyNeedDOUBLEValuesList.clear();

        // Calories :
        int calorieNeed = calculDailyCalorieRozaShizgal1994(sex, poidsIdeal, taille, age, arrayDailyActivityCoefficient[1]);
        dailyNeedSTRINGlist.add(calorieNeed + " kCal");
        dailyNeedDOUBLEValuesList.add((double) calorieNeed);
        // Lipides :
        double needdouble = calculDailyLipide();
        dailyNeedSTRINGlist.add(needdouble + " g");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Acides Gras Satures :
        needdouble = calculDailyAcidesGrasSatures();
        dailyNeedSTRINGlist.add(needdouble + " g");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Glucides :
        needdouble = calculDailyGlucide();
        dailyNeedSTRINGlist.add(needdouble + " g");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Sucres :
        needdouble = calculDailySucre();
        dailyNeedSTRINGlist.add(needdouble + " g");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Fibre :
        needdouble = calculDailyFibre();
        dailyNeedSTRINGlist.add(needdouble + " g");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Proteines :
        needdouble = calculDailyProteine(sex, age, poidsIdeal);
        dailyNeedSTRINGlist.add(needdouble + " g");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Sel :
        needdouble = calculDailySel();
        dailyNeedSTRINGlist.add(needdouble + " g");
        dailyNeedDOUBLEValuesList.add(needdouble);

        // Vitamine A :
        needdouble = calculDailyVitamineA(sex) / 1000;    // conversion en mg
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine B1 :
        needdouble = calculDailyVitamineB1(sex);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine B2 :
        needdouble = calculDailyVitamineB2(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine B3 :
        needdouble = calculDailyVitamineB3(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine B5 :
        needdouble = calculDailyVitamineB5(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine B6 :
        needdouble = calculDailyVitamineB6(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine B9 :
        needdouble = calculDailyVitamineB9(sex) / 1000;   // conversion en mg
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine B12 :
        needdouble = calculDailyVitamineB12();
        dailyNeedSTRINGlist.add(needdouble + " μg");    // en microgramme
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine C :
        needdouble = calculDailyVitamineC(age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine D :
        needdouble = calculDailyVitamineD();
        dailyNeedSTRINGlist.add(needdouble + " μg");    // en microgramme
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Vitamine E :
        needdouble = calculDailyVitamineE(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);

        // Eau :
        needdouble = calculDailyEau();
        dailyNeedSTRINGlist.add(needdouble / 1000 + " L ");    // en Litre string
        dailyNeedDOUBLEValuesList.add(needdouble); // en mL Double
        // Calcium :
        needdouble = calculDailyCalcium(age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Cuivre :
        needdouble = calculDailyCuivre(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Fer :
        needdouble = calculDailyFer(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Iode :
        needdouble = calculDailyIode();
        dailyNeedSTRINGlist.add(needdouble + " μg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Magnesium :
        needdouble = calculDailyMagnesium(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Manganese :
        needdouble = calculDailyManganese(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Phosphore :
        needdouble = calculDailyPhosphore(age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Potassium :
        needdouble = calculDailyPotassium(age) / 1000; // conversion en g
        dailyNeedSTRINGlist.add(needdouble + " g");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Selenium :
        needdouble = calculDailySelenium(poidsIdeal);
        dailyNeedSTRINGlist.add(needdouble + " μg"); // en microgramme
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Sodium :
        needdouble = calculDailySodium();
        dailyNeedSTRINGlist.add(needdouble + " g");
        dailyNeedDOUBLEValuesList.add(needdouble);
        // Zinc :
        needdouble = calculDailyZinc(sex, age);
        dailyNeedSTRINGlist.add(needdouble + " mg");
        dailyNeedDOUBLEValuesList.add(needdouble);
    }

    // ---
    // FORMULES :

    private int calculDailyCalorieHarrisBenedict1919(int sexe, int poids, int tall, int age, double dailyActivity) {

        double dailyCalorieNeed;

        // homme
        if (sexe == 0) {
            dailyCalorieNeed = 13.7516 * poids + 5.0033 * tall - 6.755 * age + 66.473;
        }
        // femme
        else {
            dailyCalorieNeed = 9.5634 * poids + 1.8496 * tall - 4.6756 * age + 655.0955;
        }

        dailyCalorieNeed *= dailyActivity;
        return (int) Math.round(dailyCalorieNeed);
    }

    private int calculDailyCalorieRozaShizgal1994(int sexe, int poids, int tall, int age, double dailyActivity) {

        // pour AMELIORATION : femme enceinte + 285 kCal/jour

        double dailyCalorieNeed;

        // homme
        if (sexe == 0) {
            dailyCalorieNeed = 13.707 * poids + 4.923 * tall - 6.673 * age + 77.607;
        }
        // femme
        else {
            dailyCalorieNeed = 9.740 * poids + 1.729 * tall - 4.737 * age + 667.051;
        }

        dailyCalorieNeed *= dailyActivity;
        return (int) Math.round(dailyCalorieNeed);
    }

    private double calculDailyProteine(int sex, int age, int poids) {

        // doit apporter 20% du besoin en calorie

        // cf. eureka nutrition besoins en proteines
        // pour AMELIORATION : enceinte + 10g / jour

        double calculProteine;

        // homme :
        if (sex == 0) {
            if (age < 20) {
                calculProteine = poids * 1.2;
            } else if (age < 60) {
                calculProteine = poids * 0.8;
            } else {
                calculProteine = poids;
            }
        }
        // femme :
        else {
            if (age < 20) {
                calculProteine = poids * 1.2;
            } else if (age < 60) {
                calculProteine = poids * 0.8;
            } else {
                calculProteine = poids;
            }
        }

        return (double) Math.round(calculProteine * 10) / 10; // arrondi au dixieme

    }

    private double calculDailyGlucide() {

        // 50% calorieNeed sachant que 1g glucide = 4 kCal
        // femme enceinte > 250g / jour et Surtout au petit dejeuner

        double calorieNeed = dailyNeedDOUBLEValuesList.get(LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal());
        return (double) Math.round((float) calorieNeed / 8);
    }

    private double calculDailySucre() {
        // 5% apport calorique journalier
        // cf. https://www.futura-sciences.com/sante/questions-reponses/nutrition-sucre-quantite-peut-on-manger-chaque-jour-9597/
        // return (double) Math.round((float)25.0 * calorieNeed / 2000);

        // 10% apports caloriques journaliers (1g sucre = 4kCal) cf: OMS;
        double calorieNeed = dailyNeedDOUBLEValuesList.get(LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal());
        return (double) Math.round((float) (0.1 * calorieNeed) / 4);
    }

    private double calculDailyFibre() {
        return 30.0;
    }

    private double calculDailyLipide() {
        // 30% apport calorique journalier
        double calorieNeed = dailyNeedDOUBLEValuesList.get(LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal());
        return (double) Math.round((float) (85.0 * calorieNeed / 2000)); // arrondi a l'unite
    }

    private double calculDailyAcidesGrasSatures() {
        // 10% apport calorique journalier
        double calorieNeed = dailyNeedDOUBLEValuesList.get(LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal());
        return (double) Math.round((float) 20.0 * calorieNeed / 1800);
    }

    private double calculDailyEau() {
        return 2500;
    }

    private double calculDailyVitamineA(int sex) {

        // en microgramme /!\
        // homme :
        if (sex == 0) {
            return 750.0;
        }
        // femme :
        else {
            return 650.0;
        }
    }

    private double calculDailyVitamineB1(int sex) {

        // en mg
        // homme :
        if (sex == 0) {
            return 1.3;
        }
        // femme :
        else {
            return 1.2;
        }
    }

    private double calculDailyVitamineB2(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 1,6mg / allaitante 1,8mg
        //                      Grand sportif 25 -> 50 mg

        // en mg
        if (age < 15) {
            return 1.4;
        } else if (age > 60) {
            return 1.6;
        }
        // homme :
        else if (sex == 0) {
            return 1.6;
        }
        // femme :
        else {
            return 1.5;
        }
    }

    private double calculDailyVitamineB3(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 18mg / allaitante 17mg

        // en mg

        if (age < 15) {
            return 12.0;
        }

        // homme :
        else if (sex == 0) {
            return 16.0;
        }
        // femme :
        else {
            return 14.0;
        }
    }

    private double calculDailyVitamineB5(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 5mg / allaitante 7mg

        // en mg

        if (age < 16) {
            return 4.5;
        }
        // homme :
        else if (sex == 0) {
            return 5.8;
        }
        // femme :
        else {
            return 4.7;
        }
    }

    private double calculDailyVitamineB6(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 1.9mg / allaitante 2mg

        // en mg

        if (age < 14) {
            return 1.0;
        } else if (age < 19) {
            // homme
            if (sex == 0) {
                return 1.3;
            }
            // femme :
            else {
                return 1.2;
            }
        } else if (age < 51) {
            return 1.3;
        }

        // superieur a 50ans :
        else {
            // homme :
            if (sex == 0) {
                return 1.7;
            }
            // femme :
            else {
                return 1.5;
            }
        }

    }

    private double calculDailyVitamineB9(int age) {

        // pour AMELIORATION :  femme enceinte 400.0μg / allaitante 400.0μg

        // en microgramme /!\

        if (age < 14) {
            return 250.0;
        } else if (age < 19) {
            return 300.0;
        } else {
            return 330.0;
        }
    }

    private double calculDailyVitamineB12() {

        // pour AMELIORATION :  femme enceinte 4.5μg / allaitante 5.0μg

        // en microgramme /!\

        return 4.0;
    }

    private double calculDailyVitamineC(int age) {

        // pour AMELIORATION :  femme enceinte 120.0mg / allaitante 130.0mg
        //                      Grand sportif + 50 -> 100 mg

        // en mg

        if (age < 13) {
            return 100.0;
        } else if (age < 60) {
            return 110.0;
        } else {
            return 120.0;
        }
    }

    private double calculDailyVitamineD() {

        // en microgramme /!\

        return 5.0;
    }

    private double calculDailyVitamineE(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 12.0mg / allaitante 12.0mg
        //                      Grand sportif -> 24 mg

        // en mg

        if (age < 13) {
            return 11.0;
        } else if (age < 70) {
            // homme :
            if (sex == 0) {
                return 15.5;
            }
            // femme :
            else {
                return 10.0;
            }
        } else {
            return 30.0;
        }
    }

    private double calculDailyCalcium(int age) {

        // en mg

        if (age < 24) {
            return 1000.0;
        } else {
            return 950.0;
        }
    }

    private double calculDailyCuivre(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 2.0mg / allaitante 2.0mg
        //                      GrandSportif + 0.5 -> 1.0 mg

        // en mg

        if (age < 20) {
            return 1.5;
        } else {
            if (sex == 0) {
                return 1.3;
            } else {
                return 1.0;
            }
        }
    }

    private double calculDailyFer(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 25.0mg / allaitante 9.0mg

        // en mg

        // homme :
        if (sex == 0) {
            return 9.0;
        }
        // femme :
        else if (age < 45) {
            return 16.0;
        }
        // femme > 45ans(menopausee) :
        else {
            return 9.0;
        }
    }

    private double calculDailyIode() {

        // pour AMELIORATION :  femme enceinte 200.0 μg / allaitante 200.0 μg

        // en microgramme /!\

        return 150.0;
    }

    private double calculDailyMagnesium(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 400.0mg / allaitante 390.0mg

        // en mg

        // homme :
        if (sex == 0) {
            if (age < 19) {
                return 410.0;
            } else if (age < 75) {
                return 420.0;
            } else {
                return 400.0;
            }
        }
        // femme :
        else {
            if (age < 19) {
                return 370.0;
            } else if (age < 75) {
                return 360.0;
            } else {
                return 400.0;
            }
        }
    }

    private double calculDailyManganese(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 2.0mg / allaitante 2.6mg

        // en mg

        // homme :
        if (sex == 0) {
            if (age < 13) {
                return 1.9;
            } else if (age < 18) {
                return 2.2;
            } else {
                return 2.3;
            }
        }
        // femme :
        else {
            if (age < 13) {
                return 1.6;
            } else if (age < 18) {
                return 1.6;
            } else {
                return 1.8;
            }
        }

    }

    private double calculDailyPhosphore(int age) {

        // pour AMELIORATION :  femme enceinte 800.0mg / allaitante 850.0mg

        // en mg

        if (age < 14) {
            return 830.0;
        } else if (age < 18) {
            return 800.0;
        } else if (age < 75) {
            return 700.0;
        } else {
            return 800.0;
        }

    }

    private double calculDailyPotassium(int age) {

        // pour AMELIORATION :  femme enceinte 4700.0 mg / allaitante 5100.0 mg

        // en mg

        if (age < 14) {
            return 4500.0;
        } else {
            return 4700.0;
        }

    }

    private double calculDailySelenium(int poids) {

        // pour AMELIORATION :  femme enceinte 0.0 μg / allaitante 0.0 μg

        // en microgramme /!\

        return poids * 1.0;

    }

    private double calculDailySodium() {

        // en g /!\
        return 6 / 2.5;

    }

    private double calculDailySel() {

        // en g /!\
        return 6.0;

    }

    private double calculDailyZinc(int sex, int age) {

        // pour AMELIORATION :  femme enceinte 11.0mg / allaitante 12.0mg

        // en mg

        // homme :
        if (sex == 0) {
            if (age < 14) {
                return 8.0;
            } else if (age < 65) {
                return 11.0;
            } else {
                return 12.0;
            }
        }
        // femme :
        else {
            if (age < 14) {
                return 8.0;
            } else if (age < 18) {
                return 9.0;
            } else if (age < 65) {
                return 8.0;
            } else {
                return 9.0;
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // GETTER AND SETTER :


    public Amicloon getAmicoon() {
        return amicoon;
    }

    public void setAmicoon(Amicloon amicoon) {
        this.amicoon = amicoon;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public ArrayList<SuiviAlimentaireClass> getArrayListSuiviAlimentaireClass() {
        return arrayListSuiviAlimentaireClass;
    }

    public void setADDArrayListSuiviAlimentaireClass(SuiviAlimentaireClass suiviAlimentaireClassObject) {

        boolean samemoment = false;
        boolean samefood = false;

        if (arrayListSuiviAlimentaireClass.size() > 0) {

            for (SuiviAlimentaireClass suiviAlimentaire : arrayListSuiviAlimentaireClass) {

                if (Arrays.equals(suiviAlimentaireClassObject.getIntArrayDate(), suiviAlimentaire.getIntArrayDate())) {

                    samemoment = false;

                    if (suiviAlimentaireClassObject.getTimeDayMoment() == suiviAlimentaire.getTimeDayMoment()) {

                        samemoment = true;

                        // SI On veut ajouter une liste d'aliments mangés dans la meme periode qu'une precedente :
                        for (FoodClass foodToadd : suiviAlimentaireClassObject.getArrayListFoodEaten()) {

                            samefood = false;

                            for (FoodClass foodA : suiviAlimentaire.getArrayListFoodEaten()) {

                                // SI on retrouve un meme aliment, set quantity by the new one :
                                if (foodToadd.name.equals(foodA.name)) {
                                    foodA.setADDQuantity(foodToadd.getQuantity());
                                    samefood = true;
                                    break;

                                }
                            }

                            // SI aliment foodToAdd ne correspond a aucun aliment parmi foodA :
                            if (!samefood) {
                                suiviAlimentaire.getArrayListFoodEaten().add(foodToadd);
                            }
                        }
                        break;
                    }

                } else {
                    break;
                }

            }
            if (!samemoment) {
                this.arrayListSuiviAlimentaireClass.add(0, suiviAlimentaireClassObject);
            }
        } else {
            this.arrayListSuiviAlimentaireClass.add(0, suiviAlimentaireClassObject);
        }

    }

    public void setArrayListSuiviAlimentaireClass(ArrayList<SuiviAlimentaireClass> arrayListSuiviAlimentaireClass) {
        this.arrayListSuiviAlimentaireClass = arrayListSuiviAlimentaireClass;
    }

    public ArrayList<String> getDailyNeedPercentSTRINGlist(Double[] arrayDoubleValuesComparaison) {

        dailyNeedSTRINGlist.clear();
        String text;
        for (int i = 0; i <= enumValuesDailyNeed.ZINC.ordinal(); i++) {

            text = Math.round((arrayDoubleValuesComparaison[i] * 1000) / dailyNeedDOUBLEValuesList.get(i)) / 10 + " %";

            dailyNeedSTRINGlist.add(text);
        }
        return dailyNeedSTRINGlist;
    }

    public ArrayList<String> getDailyNeedSTRINGlist(Context context) {

        dailyNeedSTRINGlist.clear();
        String text;
        for (int i = 0; i <= enumValuesDailyNeed.ZINC.ordinal(); i++) {
            if (i == enumValuesDailyNeed.CALORIES.ordinal()) {
                text = dailyNeedDOUBLEValuesList.get(i) + " kCal";
            } else if (i <= enumValuesDailyNeed.SEL.ordinal() || i == enumValuesDailyNeed.POTASSIUM.ordinal() || i == enumValuesDailyNeed.SODIUM.ordinal()) {
                text = dailyNeedDOUBLEValuesList.get(i) + " g";
            } else if (i == enumValuesDailyNeed.VITAMINE_B12.ordinal() || i == enumValuesDailyNeed.VITAMINE_D.ordinal()
                    || i == enumValuesDailyNeed.IODE.ordinal() || i == enumValuesDailyNeed.SELENIUM.ordinal()) {
                text = dailyNeedDOUBLEValuesList.get(i) + " μg";
            } else if (i == enumValuesDailyNeed.EAU.ordinal()) {
                text = dailyNeedDOUBLEValuesList.get(i) / 1000 + " L ";
            } else {
                text = dailyNeedDOUBLEValuesList.get(i) + " mg";
            }
            dailyNeedSTRINGlist.add(text);
        }
        return dailyNeedSTRINGlist;
    }

    public void setDailyNeedSTRINGlist(ArrayList<String> dailyNeedSTRINGlist) {
        this.dailyNeedSTRINGlist = dailyNeedSTRINGlist;
    }

    public ArrayList<Double> getDailyNeedDOUBLEValuesList() {
        return dailyNeedDOUBLEValuesList;
    }

    public void setDailyNeedDOUBLEValuesList(ArrayList<Double> dailyNeedDOUBLEValuesList) {
        this.dailyNeedDOUBLEValuesList = dailyNeedDOUBLEValuesList;
    }

    public Integer getTypeAlimentation() {
        return typeAlimentation;
    }

    public void setTypeAlimentation(Integer typeAlimentation) {
        this.typeAlimentation = typeAlimentation;
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMorphologie() {
        return morphologie;
    }

    public void setMorphologie(Integer morphologie) {
        this.morphologie = morphologie;
    }

    public Integer getPoidsIdeal() {
        return poidsIdeal;
    }

    public void setPoidsIdeal(Integer poidsIdeal) {
        this.poidsIdeal = poidsIdeal;
    }
}
