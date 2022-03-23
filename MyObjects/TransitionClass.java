package fr.maxime.mygame01.MyObjects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.R;

public abstract class TransitionClass {

    public static String saveName = "saveAlpha3";
    public static String langageActual;
    public static String[] languageArraySelector;                                                   // init dans MenuActivity.java
    public static final int MAXPROFILENUMBER = 4;

    public enum enumLanguageSelector {                                                              // enum pour languageArraySelector[]
        Anglais, Francais;
    }

    public static boolean debutDuProgramme = true;

    public static ProfileDailyNeedsClass profileActual = null;

    public static FoodClass foodstatic = null;

    //GameV2 et food selectorV2 Activity :
    public static ArrayList<FoodClass> foodOnDropZoneList = null;
    public static int[] foodOnDropZonePositionArray = null;

    private static int dateDay;
    private static int dateMonth;
    private static int dateYear;
    private static int timeNow;
    private static int timeDayMoment;

    public enum enumTimeDayMoment {
        NUIT, MATIN, MIDI, APRESMIDI, SOIR;
    }

    private static String[] arrayDayMoment;
    private static String dayMomentText;

    public static int alimentationType = 0;
    public static String[] arrayAlimentationType;
    public static boolean isAlimentationSelected = false;

    // AllFOOD :
    public static ArrayList<ArrayList<FoodClass>> globalAllFoodList = new ArrayList<>();
    public static ArrayList<FoodClass> allFoodList = new ArrayList<>();
    public static ArrayList<FoodClass> vegetarianAllFoodList = new ArrayList<>();
    public static ArrayList<FoodClass> veganAllFoodList = new ArrayList<>();
    public static ArrayList<FoodClass> noPorkAndAlcoholFoodList = new ArrayList<>();
    public static ArrayList<FoodClass> selectedFoodList = new ArrayList<>();

    public static Double[] arrayMoyenneValuesAllFoodElements;

    // FoodList :
    public static int indexSpinnerFoodList = 0;
    public static int indexScrollViewFocusFoodList = 0;
    public static boolean isStartingFoodList = true;
    public static boolean isReversedOrganiserFoodList = false;

    // about FoodSelectorActivityV2 :
    public static int positionFoodInCategoryGridView = 0;
    public static int indexCategoryGridView = 0;
    public static int levelCategoryGridView = 0;
    public static int quantityAmountChosen = -2;
    public static String foodCategoryNameStatic;
    public static Double[] arrayTotalFoodValues;
    public static String nutriScoreCharacter = "";
    public static int nutriScoreColor = 0;

    // about ResultSuiviRepasActivity :
    private static ArrayList<ArrayList<FoodClass>> bigArrayListFoodClass = new ArrayList<>();
    private static SuiviAlimentaireClass suiviAlimentaireClassArrayListStatic;

    // about ProfilMenuActivity :
    public static boolean isOnModifyProfile = false;


    public static int activityToBackUpByCancel = 0;
    public static int activityToBackUpByCancelLevel2 = 0;

    @SuppressLint("StaticFieldLeak")
    public static LibraryArrayBitmapDrawingRessources myLib = null;

    // about Etagere OnBoardingAdapter :
    public static ArrayList<LinearLayout> onBoardingAdapterlinearLayoutArrayList = new ArrayList<>();

    // about TESTREGEX :
    public static String regexSavedInput = "";

    // About Music :

    public static boolean resetMusicForFoodSelectorV2 = true;
    public static MediaPlayer music = null;
    public static MediaPlayer soundWrongClick = null;
    public static MediaPlayer soundOnClick = null;
    public static MediaPlayer soundOnClickBubble = null;
    public static MediaPlayer soundOnValidation = null;
    public static MediaPlayer soundEmotion = null;

    public static MediaPlayer[] arrayMediaPlayerSound; // Cf : setInitMediaPlayer voir dessous;
    public static int[] arraySoundPoolSound;

    public enum enumSoundSelectorValue {
        RightClick, WrongClick, BubbleClick, ValidationSound, SharedSound;
    }

    // About Sound :

    private static AudioAttributes audioAttributes = new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build();

    private static SoundPool soundPool = new SoundPool.Builder()
            .setMaxStreams(3)
            .setAudioAttributes(audioAttributes)
            .build();

    private static int soundPoolWrongClick, soundPoolOnClick, soundPoolOnClickBubble, soundPoolOnValidation, soundPoolShared;

    public static int valeuralea = 30;

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    // MUSIC :
    public static void setInitMediaPlayers(Context context) {
        TransitionClass.soundOnClick = MediaPlayer.create(context, R.raw.ed_click_right_original);
        TransitionClass.soundWrongClick = MediaPlayer.create(context, R.raw.ed_click_wrong_original);
        TransitionClass.soundOnClickBubble = MediaPlayer.create(context, R.raw.ed_goutte_positif_original);
        TransitionClass.soundOnValidation = MediaPlayer.create(context, R.raw.ed_click_validation);
        arrayMediaPlayerSound = new MediaPlayer[]{
                TransitionClass.soundOnClick,
                TransitionClass.soundWrongClick,
                TransitionClass.soundOnClickBubble,
                TransitionClass.soundOnValidation};

        TransitionClass.soundPoolOnClick = soundPool.load(context, R.raw.ed_click_right_original, 1);
        TransitionClass.soundPoolWrongClick = soundPool.load(context, R.raw.ed_click_wrong_original, 1);
        TransitionClass.soundPoolOnClickBubble = soundPool.load(context, R.raw.ed_goutte_positif_original, 1);
        TransitionClass.soundPoolOnValidation = soundPool.load(context, R.raw.ed_click_validation, 1);
        TransitionClass.soundPoolShared = soundPool.load(context, R.raw.emotion_machiavelique, 1);
        arraySoundPoolSound = new int[]{
                TransitionClass.soundPoolOnClick,
                TransitionClass.soundPoolWrongClick,
                TransitionClass.soundPoolOnClickBubble,
                TransitionClass.soundPoolOnValidation,
                TransitionClass.soundPoolShared};

    }

    public static void playEnumSound(int enumSoundSelectorValue) {

//        TransitionClass.playTransitionClassMediaPlayerSound(arrayMediaPlayerSound[enumSoundSelectorValue]);
        TransitionClass.playTransitionClassSoundPoolSound(arraySoundPoolSound[enumSoundSelectorValue]);

    }

    public static void playTransitionClassSoundPoolSound(int soundpoolInt) {

        soundPool.play(soundpoolInt,1,1,0,0,1);
    }

    public static void playYourSoundPoolSound(Context context, int rRawPath) {
        TransitionClass.soundPoolShared = soundPool.load(context, rRawPath, 1);
        playTransitionClassSoundPoolSound(soundPoolShared);

    }

    public static void playTransitionClassMediaPlayerSound(MediaPlayer sound) {

        if (sound != null) {
            sound.seekTo(1);
            sound.start();
        }
    }

    public static void playYourMediaPlayerSound(Context context, MediaPlayer mediaPlayerSound, int rRawPath) {
        if (mediaPlayerSound != null) {
            mediaPlayerSound.release();
            mediaPlayerSound = MediaPlayer.create(context, rRawPath);
            mediaPlayerSound.seekTo(1);
            mediaPlayerSound.start();
        } else {
            mediaPlayerSound = MediaPlayer.create(context, rRawPath);
            mediaPlayerSound.seekTo(1);
            mediaPlayerSound.start();
        }

    }

    public static void setMusic(Context context, int rawPathMusicSound) {

        if (music != null) {
            music.stop();
            music.reset();
            music.release();
            music = null;
        }
        music = MediaPlayer.create(context, rawPathMusicSound);
        music.start();
        music.setLooping(true);
    }

    // LIBRAIRIE :
    public static void stockageFood(FoodClass food) {
        foodstatic = food;
    }

    public static void creationDeLaBaseDeDonnee(Context context) {
        myLib = new LibraryArrayBitmapDrawingRessources(context);

        setDoubleMoyeneAllFoodListByElements();

        arrayDayMoment = new String[]{
                context.getString(R.string.nuit),
                context.getString(R.string.matin),
                context.getString(R.string.midi),
                context.getString(R.string.apres_midi),
                context.getString(R.string.soir)};

        arrayAlimentationType = new String[]{
                context.getResources().getString(R.string.normal),
                context.getResources().getString(R.string.vegetarien),
                context.getResources().getString(R.string.vegetalien),
                context.getResources().getString(R.string.sansPorcEtAlcool)};
    }

    public static void calculateTimeDayMoment() {
        Date date0 = new Date();
        Calendar calendrier = Calendar.getInstance(TimeZone.getDefault());

        TransitionClass.dateDay = Calendar.getInstance().get(Calendar.DATE);
        TransitionClass.dateMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        TransitionClass.dateYear = Calendar.getInstance().get(Calendar.YEAR);
        TransitionClass.timeNow = date0.getHours();

        if (TransitionClass.timeNow <= 4 || TransitionClass.timeNow >= 22) {
            TransitionClass.timeDayMoment = enumTimeDayMoment.NUIT.ordinal();
        } else if (TransitionClass.timeNow <= 10) {
            TransitionClass.timeDayMoment = enumTimeDayMoment.MATIN.ordinal();
        } else if (TransitionClass.timeNow <= 14) {
            TransitionClass.timeDayMoment = enumTimeDayMoment.MIDI.ordinal();
        } else if (TransitionClass.timeNow <= 17) {
            TransitionClass.timeDayMoment = enumTimeDayMoment.APRESMIDI.ordinal();
        } else {
            TransitionClass.timeDayMoment = enumTimeDayMoment.SOIR.ordinal();
        }

        TransitionClass.dayMomentText = arrayDayMoment[TransitionClass.timeDayMoment];

    }

    public static void setAllFoodList(ArrayList<FoodClass> allFoodArrayList) {

        for (FoodClass food : allFoodArrayList) {

            allFoodList.add(food);

            if (food.isVegetarien) {
                vegetarianAllFoodList.add(food);
            }
            if (food.isVegetalien) {
                veganAllFoodList.add(food);
            }
            if (food.category != LibraryArrayBitmapDrawingRessources.categoryEnum.alcool.index()
                    && !food.isPork) {
                noPorkAndAlcoholFoodList.add(food);
            }
        }
        globalAllFoodList.add(allFoodList);
        globalAllFoodList.add(vegetarianAllFoodList);
        globalAllFoodList.add(veganAllFoodList);
        globalAllFoodList.add(noPorkAndAlcoholFoodList);
        selectedFoodList = allFoodList;

    }

    public static void setDoubleMoyeneAllFoodListByElements() {

        Double[] arrayDoubleValueToReturn = new Double[allFoodList.get(0).arrayValuesFoodPer100g.length];
        Arrays.fill(arrayDoubleValueToReturn, 0.0);

        // for food in bigAllFoodList
        for (int i = 0; i < allFoodList.size(); i++) {

            // for valuesFoodPer100g in arrayValuesFoodPer100g
            for (int j = 0; j < allFoodList.get(i).arrayValuesFoodPer100g.length; j++) {

                arrayDoubleValueToReturn[j] += allFoodList.get(i).arrayValuesFoodPer100g[j];

            }

        }

        // for valuesFoodPer100g in arrayValuesFoodPer100g
        for (int j = 0; j < arrayDoubleValueToReturn.length; j++) {

            arrayDoubleValueToReturn[j] /= allFoodList.size();
            arrayDoubleValueToReturn[j] = (double) Math.round(arrayDoubleValueToReturn[j] * 1000) / 1000;

        }

        TransitionClass.myLib.arrayMoyenneDoubleValuesAllFoodByElements = arrayDoubleValueToReturn;
        TransitionClass.arrayMoyenneValuesAllFoodElements = arrayDoubleValueToReturn;
    }

    // FOODLIST :
    public static void setFoodListActivitySpinnerByClickOnCategoryText(TextView textView1) {
        for (int i = 0; i < TransitionClass.myLib.comparatorArrayName.length; i++) {
            if (TransitionClass.myLib.comparatorArrayName[i].contentEquals(textView1.getText())) {
                TransitionClass.indexSpinnerFoodList = i;
                if (i < LibraryArrayBitmapDrawingRessources.enumElementsListe.VITAMINE_A.ordinal() + 2) {
                    TransitionClass.isReversedOrganiserFoodList = true;
                }
                if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.FIBRE.ordinal() + 2
                        || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.PROTEINE.ordinal() + 2) {
                    TransitionClass.isReversedOrganiserFoodList = false;
                }
            }
        }
    }

    // ---
    // BESOINS CALCULES TEMPS REEL :
    // -----------------------

    public static Double[] getArrayDoubleValueByElementsForActualNeedInDailyNeed() {

        calculateTimeDayMoment();
        int[] date = getDate();

        Double[] arrayTOTALDoubleValuesByElementOfFoodlists = new Double[FoodClass.getLengthOfArrayDoubleValueFoodPer100g()];
        Arrays.fill(arrayTOTALDoubleValuesByElementOfFoodlists, 0.0);

        Double[] arrayTOTALDoubleValuesByElementOfResultSubtractionWithDailyNeed = new Double[FoodClass.getLengthOfArrayDoubleValueFoodPer100g()];
        Arrays.fill(arrayTOTALDoubleValuesByElementOfResultSubtractionWithDailyNeed, 0.0);

        for (SuiviAlimentaireClass suiviAlimentaire : profileActual.getArrayListSuiviAlimentaireClass()) {

            if (Arrays.equals(suiviAlimentaire.getIntArrayDate(), date)) {

                if (suiviAlimentaire.getArrayListFoodEaten().size() > 0) {
                    Double[] arrayDoubleValueByElementOfFoodList = FoodClass.getCalculateTotalDoubleValueByElementOfAFoodList(suiviAlimentaire.getArrayListFoodEaten());

                    for (int i = 0; i < arrayDoubleValueByElementOfFoodList.length; i++) {

                        arrayTOTALDoubleValuesByElementOfFoodlists[i] += arrayDoubleValueByElementOfFoodList[i];
                    }
                }
            }
        }
        for (int i = 0; i < arrayTOTALDoubleValuesByElementOfFoodlists.length; i++) {

            double d = profileActual.getDailyNeedDOUBLEValuesList().get(i) - arrayTOTALDoubleValuesByElementOfFoodlists[i];
            arrayTOTALDoubleValuesByElementOfResultSubtractionWithDailyNeed[i] = (double) Math.round(d * 100) / 100;

        }

        return arrayTOTALDoubleValuesByElementOfResultSubtractionWithDailyNeed;

    }

    public static Double[] getArrayDoubleValueByElementsForActualNeedWITHOUTNEGATIVE() {

        Double[] arrayDoubleActualNeedElement = getArrayDoubleValueByElementsForActualNeedInDailyNeed();
        Double[] arrayDoubleValueToReturn = new Double[arrayDoubleActualNeedElement.length];

        for (int i = 0; i < arrayDoubleValueToReturn.length; i++) {
            if (arrayDoubleActualNeedElement[i] < 0.0) {
                arrayDoubleValueToReturn[i] = 0.0;
            } else {
                arrayDoubleValueToReturn[i] = arrayDoubleActualNeedElement[i];
            }
        }

        return arrayDoubleValueToReturn;
    }

    public static String[] getArrayStringByElementsForActualNeed(Double[] arrayDoubleValues) {

        return FoodClass.getArrayStringTotalFoodAllElements(arrayDoubleValues);

    }

    public static String[] getArrayStringByElementsForActualNeedWithInferiorSign(Double[] arrayTotalValueFood) {

        return FoodClass.getArrayStringActualNeedDoubleValuesWithSign(arrayTotalValueFood);

    }

    public static String[] getArrayStringByElementsForActualNeedPercent(Double[] comparaisonArrayDoubleElementsValue) {

        Double[] ArrayDoubleValuesWithoutNegative = getArrayDoubleValueByElementsForActualNeedWITHOUTNEGATIVE();
        String[] arrayStringToReturn = new String[ArrayDoubleValuesWithoutNegative.length];

        for (int i = 0; i < ArrayDoubleValuesWithoutNegative.length; i++) {

            if (ArrayDoubleValuesWithoutNegative[i] > 0) {

                double d0 = comparaisonArrayDoubleElementsValue[i] * 100 / ArrayDoubleValuesWithoutNegative[i];
                double d = (double) Math.round(d0 * 10) / 10;
                if (d > 100) {
                    arrayStringToReturn[i] = "> 100 %";
                } else {
                    arrayStringToReturn[i] = d + " %";
                }
            } else {
                arrayStringToReturn[i] = "> 100 %";
            }

        }

        return arrayStringToReturn;

    }

    public static String[] getArrayStringByElementsPercentComparator(Double[] arrayValuesByElements, Double[] arrayMaxValuesByElements) {

        String[] arrayStringToReturn = new String[arrayMaxValuesByElements.length];

        for (int i = 0; i < arrayMaxValuesByElements.length; i++) {

            if (arrayValuesByElements[i] < arrayMaxValuesByElements[i]) {

                double d = (double) Math.round((arrayValuesByElements[i] * 100 / arrayMaxValuesByElements[i]) * 10) / 10;
                arrayStringToReturn[i] = d + " %";
            } else {
                arrayStringToReturn[i] = "> 100 %";
            }

        }

        return arrayStringToReturn;

    }

    public static int[] getArrayintColorByElementsPercentComparator(Double[] arrayValuesByElements, Double[] arrayMaxValuesByElements) {

        int[] arrayStringToReturn = new int[arrayMaxValuesByElements.length];

        for (int i = 0; i < arrayMaxValuesByElements.length; i++) {

            if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.LIPIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.ACIDEGRASSATURES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.GLUCIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SUCRE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SEL.ordinal()) {
                if (arrayValuesByElements[i] < arrayMaxValuesByElements[i] / 2) {
                    arrayStringToReturn[i] = R.color.nutriscore_vert1;
                } else if (arrayValuesByElements[i] < arrayMaxValuesByElements[i]) {
                    arrayStringToReturn[i] = R.color.nutriscore_orange;
                } else {
                    arrayStringToReturn[i] = R.color.nutriscore_rouge;
                }
            } else {
                if (arrayValuesByElements[i] < arrayMaxValuesByElements[i] * 3 / 4) {
                    arrayStringToReturn[i] = R.color.nutriscore_bleu;
                } else if (arrayValuesByElements[i] < arrayMaxValuesByElements[i]) {
                    arrayStringToReturn[i] = R.color.nutriscore_vert1;
                } else if (arrayValuesByElements[i] < arrayMaxValuesByElements[i] * 5 / 4) {
                    arrayStringToReturn[i] = R.color.nutriscore_orange;
                } else {
                    arrayStringToReturn[i] = R.color.nutriscore_rouge;
                }
            }


        }

        return arrayStringToReturn;

    }

    public static int[] getColorElementsBasicEvaluation(Context context, Double[] arrayValuesByElements, int colorToReturnIfNormal) {

        int[] arrayColorToReturn = new int[arrayValuesByElements.length];

        for (int i = 0; i < arrayValuesByElements.length; i++) {

            if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.LIPIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.ACIDEGRASSATURES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.GLUCIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SUCRE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.PROTEINE.ordinal()) {
                if (arrayValuesByElements[i] < 0) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_rouge);
                } else {
                    arrayColorToReturn[i] = colorToReturnIfNormal;
                }
            }
            // Les autres Elements :
            else {
                if (arrayValuesByElements[i] <= 0) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_vert2);
                } else {
                    arrayColorToReturn[i] = colorToReturnIfNormal;
                }
            }
        }

        return arrayColorToReturn;

    }

    // DailyNeedActivity :
    public static int[] getColorElementsCompareDailyNeedToActualNeed(Context context) {

        Double[] actualNeedArrayElement = getArrayDoubleValueByElementsForActualNeedWITHOUTNEGATIVE();
        int[] arrayColorToReturn = new int[actualNeedArrayElement.length];

        ArrayList<Double> dailyNeedArrayListELements = TransitionClass.profileActual.getDailyNeedDOUBLEValuesList();

        for (int i = 0; i < actualNeedArrayElement.length; i++) {

            // Pour Calorie, Lipide, AcideGrasSature, Glucide, Sucre, Proteine, Sel :
            if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.LIPIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.ACIDEGRASSATURES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.GLUCIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SUCRE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.PROTEINE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SEL.ordinal()) {

                if (actualNeedArrayElement[i] <= 0) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_rouge);
                } else if (actualNeedArrayElement[i] < dailyNeedArrayListELements.get(i) * 0.5) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_orange);
                } else {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_vert1);
                }
            }
            // Les autres Elements :
            else {
                if (actualNeedArrayElement[i] <= 0) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_vert1);
                } else if (actualNeedArrayElement[i] < dailyNeedArrayListELements.get(i) * 0.5) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_vert2);
                } else {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_bleu);
                    ;
                }
            }
        }

        return arrayColorToReturn;

    }

    // ResultActivity :
    public static int[] getColorElementsCompareActualNeedToArrayDoubleValueElements(Context context, Double[] compareArrayDoubleElements) {

        Double[] arrayDoubleValueActualNeed = getArrayDoubleValueByElementsForActualNeedWITHOUTNEGATIVE();
        int[] arrayColorToReturn = new int[arrayDoubleValueActualNeed.length];

        for (int i = 0; i < arrayDoubleValueActualNeed.length; i++) {

            // Pour Calorie, Lipide, AcideGrasSature, Glucide, Sucre, Proteine, Sel :
            if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.LIPIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.ACIDEGRASSATURES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.GLUCIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SUCRE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.PROTEINE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SEL.ordinal()) {

                if (compareArrayDoubleElements[i] >= arrayDoubleValueActualNeed[i]) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_rouge);
                } else if (compareArrayDoubleElements[i] >= arrayDoubleValueActualNeed[i] * 0.5) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_orange);
                } else {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_vert1);
                }
            }

            // Pour les autres Elements :
            else {
                if (compareArrayDoubleElements[i] >= arrayDoubleValueActualNeed[i]) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_vert1);
                } else if (compareArrayDoubleElements[i] >= arrayDoubleValueActualNeed[i] * 0.5) {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_vert2);
                } else {
                    arrayColorToReturn[i] = context.getColor(R.color.nutriscore_orange);
                }
            }
        }

        return arrayColorToReturn;

    }


    // ---
    // NUTRISCORE GENERAL :
    // -----------------------

    public static void getInstantNutriScoreV2ABC(Context context, ArrayList<FoodClass> listFoodSelected, TextView textViewScore) {

        if (TransitionClass.profileActual != null && listFoodSelected.size() > 0) {

            ArrayList<Double> listBesoinUser = TransitionClass.profileActual.getDailyNeedDOUBLEValuesList();
            Double[] arrayElementsInFoodAccordingToQuantity = new Double[listFoodSelected.get(0).arrayValuesFoodPer100g.length];
            ArrayList<Double[]> bigList = new ArrayList<>();                                                                             // selon le nombre de food dans la liste selectedfood;
            Double[] totalArray = new Double[listFoodSelected.get(0).arrayValuesFoodPer100g.length];                                            // selon le nombre de caracteristiques (ex: calories);
            double coefApportJselonPeriodeJournee = TransitionClass.getApportInstantaneSelonPeriodeDeLaJournee();
            double d = 0.0;
            int score = 0;

            // calcul de totalArray la somme des valeurs caracteristiques de tous les aliments presents dans la liste selon leur quantite :
            // calcul des caracteristiques selon la quantite pour chaque aliment present dans la liste :

            for (FoodClass food : listFoodSelected) {
                arrayElementsInFoodAccordingToQuantity = food.arrayValuesFoodPer100g.clone();
                double quantityMultipliarFood = ((double) food.getQuantity() / 100);

                for (int i = 0; i < arrayElementsInFoodAccordingToQuantity.length; i++) {
                    arrayElementsInFoodAccordingToQuantity[i] *= quantityMultipliarFood;
                }
                bigList.add(arrayElementsInFoodAccordingToQuantity);
            }

            // calcul de totalArray Somme elements :
            for (int j = 0; j < arrayElementsInFoodAccordingToQuantity.length; j++) {
                for (int k = 0; k < listFoodSelected.size(); k++) {
                    double x = bigList.get(k)[j];
                    d += x;
                }
                totalArray[j] = (double) Math.round(d * 10) / 10;
                d = 0.0;
            }

            TransitionClass.arrayTotalFoodValues = totalArray;

            // utilisation de totalArray :
            for (int i = 0; i < 8; i++) // calories, lipide, acideGrasSature, glucide, sucre, fibre, proteine, sel;
            {
                if (i == 5) // squeezage de la caracteristique Fibre;
                {
                    i++;
                }

                if (i == 1 || i == 3 || i == 6) //Conditions pour Lipides, Glucides, Proteines :
                {
                    // calcul de la difference entre besoin et apport selon coefApport :
                    d = listBesoinUser.get(i) * 0.5 - totalArray[i];
                    if (d < 0) {
                        // si d <= 10% en plus des apports conseilles : A
                        if (Math.abs(d) <= 0.1 * listBesoinUser.get(i)
                                && score <= 1) {
                            score = 1;
                        }
                        // si 20% < d < 50% en plus des apports conseilles : B
                        else if (Math.abs(d) < 0.5 * listBesoinUser.get(i)) {
                            score = 2;
                        }
                        // sinon si d >= 50% apports conseilles : C
                        else {
                            score = 3;
                            break;
                        }
                    }
                } else // Conditions autres elements :
                {
                    // calcul de la difference entre besoin et apport selon coefApport :
                    d = listBesoinUser.get(i) * coefApportJselonPeriodeJournee - totalArray[i];
                    if (d < 0) {
                        // si d <= 10% en plus des apports conseilles : A
                        if (Math.abs(d) <= 0.1 * listBesoinUser.get(i) * coefApportJselonPeriodeJournee
                                && score <= 1) {
                            score = 1;
                        }
                        // si 10% < d <= 20% en plus des apports conseilles : B
                        else if (Math.abs(d) <= 0.2 * listBesoinUser.get(i) * coefApportJselonPeriodeJournee) {
                            score = 2;
                        }
                        // sinon si d > 20% apports conseilles : C
                        else {
                            score = 3;
                            break;
                        }
                    }
                }
            }

            // NutriScore :
            if (score == 0) {
                // A+
                setNutriScore("A+", R.color.nutriscore_vert1);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            } else if (score == 1) {
                //A
                setNutriScore("A", R.color.nutriscore_vert2);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            } else if (score == 2) {
                //B
                setNutriScore("B", R.color.nutriscore_orange);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            } else {
                //C
                setNutriScore("C", R.color.nutriscore_rouge);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            }

        } else {
            nutriScoreCharacter = "";
            textViewScore.setText(nutriScoreCharacter);
        }
    }

    public static void getNutriScoreV2ABC(Context context, ArrayList<FoodClass> listFoodSelected, TextView textViewScore, int enumTimeDayMoment) {

        if (TransitionClass.profileActual != null && listFoodSelected.size() > 0) {

            ArrayList<Double> listBesoinUser = TransitionClass.profileActual.getDailyNeedDOUBLEValuesList();
            Double[] arrayElementsInFoodAccordingToQuantity = new Double[listFoodSelected.get(0).arrayValuesFoodPer100g.length];
            ArrayList<Double[]> bigList = new ArrayList<>();                                                                             // selon le nombre de food dans la liste selectedfood;
            Double[] totalArray = new Double[listFoodSelected.get(0).arrayValuesFoodPer100g.length];                                            // selon le nombre de caracteristiques (ex: calories);
            double coefApportJselonPeriodeJournee = TransitionClass.getApportSelonPeriodeDeLaJourneeADefinir(enumTimeDayMoment);
            double d = 0.0;
            int score = 0;

            // calcul de totalArray la somme des valeurs caracteristiques de tous les aliments presents dans la liste selon leur quantite :
            // calcul des caracteristiques selon la quantite pour chaque aliment present dans la liste :

            for (FoodClass food : listFoodSelected) {
                arrayElementsInFoodAccordingToQuantity = food.arrayValuesFoodPer100g.clone();
                double quantityMultipliarFood = ((double) food.getQuantity() / 100);

                for (int i = 0; i < arrayElementsInFoodAccordingToQuantity.length; i++) {
                    arrayElementsInFoodAccordingToQuantity[i] *= quantityMultipliarFood;
                }
                bigList.add(arrayElementsInFoodAccordingToQuantity);
            }

            // calcul de totalArray Somme elements :
            for (int j = 0; j < arrayElementsInFoodAccordingToQuantity.length; j++) {
                for (int k = 0; k < listFoodSelected.size(); k++) {
                    double x = bigList.get(k)[j];
                    d += x;
                }
                totalArray[j] = (double) Math.round(d * 10) / 10;
                d = 0.0;
            }

            TransitionClass.arrayTotalFoodValues = totalArray;

            // utilisation de totalArray :
            for (int i = 0; i < 8; i++) // calories, lipide, acideGrasSature, glucide, sucre, fibre, proteine, sel;
            {
                if (i == 5) // squeezage de la caracteristique Fibre;
                {
                    i++;
                }

                if (i == 1 || i == 3 || i == 6) //Conditions pour Lipides, Glucides, Proteines :
                {
                    // calcul de la difference entre besoin et apport selon coefApport :
                    d = listBesoinUser.get(i) * 0.5 - totalArray[i];
                    if (d < 0) {
                        // si d <= 10% en plus des apports conseilles : A
                        if (Math.abs(d) <= 0.1 * listBesoinUser.get(i)
                                && score <= 1) {
                            score = 1;
                        }
                        // si 20% < d < 50% en plus des apports conseilles : B
                        else if (Math.abs(d) < 0.5 * listBesoinUser.get(i)) {
                            score = 2;
                        }
                        // sinon si d >= 50% apports conseilles : C
                        else {
                            score = 3;
                            break;
                        }
                    }
                } else // Conditions autres elements :
                {
                    // calcul de la difference entre besoin et apport selon coefApport :
                    d = listBesoinUser.get(i) * coefApportJselonPeriodeJournee - totalArray[i];
                    if (d < 0) {
                        // si d <= 10% en plus des apports conseilles : A
                        if (Math.abs(d) <= 0.1 * listBesoinUser.get(i) * coefApportJselonPeriodeJournee
                                && score <= 1) {
                            score = 1;
                        }
                        // si 10% < d <= 20% en plus des apports conseilles : B
                        else if (Math.abs(d) <= 0.2 * listBesoinUser.get(i) * coefApportJselonPeriodeJournee) {
                            score = 2;
                        }
                        // sinon si d > 20% apports conseilles : C
                        else {
                            score = 3;
                            break;
                        }
                    }
                }
            }

            // NutriScore :
            if (score == 0) {
                // A+
                setNutriScore("A+", R.color.nutriscore_vert1);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            } else if (score == 1) {
                //A
                setNutriScore("A", R.color.nutriscore_vert2);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            } else if (score == 2) {
                //B
                setNutriScore("B", R.color.nutriscore_orange);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            } else {
                //C
                setNutriScore("C", R.color.nutriscore_rouge);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            }

        } else {
            nutriScoreCharacter = "";
            textViewScore.setText(nutriScoreCharacter);
        }
    }

    public static void getNutriScoreV2ABC_foodclassalone(Context context, ArrayList<FoodClass> listFoodSelected, TextView textViewScore, int enumTimeDayMoment) {

        if (TransitionClass.profileActual != null && listFoodSelected.size() > 0) {

            ArrayList<Double> listBesoinUser = TransitionClass.profileActual.getDailyNeedDOUBLEValuesList();
            Double[] arrayElementsInFoodAccordingToQuantity = new Double[listFoodSelected.get(0).arrayValuesFoodPer100g.length];
            ArrayList<Double[]> bigList = new ArrayList<>();                                                                             // selon le nombre de food dans la liste selectedfood;
            Double[] totalArray = new Double[listFoodSelected.get(0).arrayValuesFoodPer100g.length];                                            // selon le nombre de caracteristiques (ex: calories);
            double coefApportJselonPeriodeJournee = TransitionClass.getApportSelonPeriodeDeLaJourneeADefinir(enumTimeDayMoment);
            double d = 0.0;
            int score = 0;

            // calcul de totalArray la somme des valeurs caracteristiques de tous les aliments presents dans la liste selon leur quantite :
            // calcul des caracteristiques selon la quantite pour chaque aliment present dans la liste :

            for (FoodClass food : listFoodSelected) {
                arrayElementsInFoodAccordingToQuantity = food.arrayValuesFoodPer100g.clone();
                double quantityMultipliarFood = ((double) food.getQuantity() / 100);

                for (int i = 0; i < arrayElementsInFoodAccordingToQuantity.length; i++) {
                    arrayElementsInFoodAccordingToQuantity[i] *= quantityMultipliarFood;
                }
                bigList.add(arrayElementsInFoodAccordingToQuantity);
            }

            // calcul de totalArray Somme elements :
            for (int j = 0; j < arrayElementsInFoodAccordingToQuantity.length; j++) {
                for (int k = 0; k < listFoodSelected.size(); k++) {
                    double x = bigList.get(k)[j];
                    d += x;
                }
                totalArray[j] = (double) Math.round(d * 10) / 10;
                d = 0.0;
            }

            TransitionClass.arrayTotalFoodValues = totalArray;

            // utilisation de totalArray :
            for (int i = 0; i < 8; i++) // calories, lipide, acideGrasSature, glucide, sucre, fibre, proteine, sel;
            {
                if (i == 5) // squeezage de la caracteristique Fibre;
                {
                    i++;
                }

                if (i == 1 || i == 3 || i == 6) //Conditions pour Lipides, Glucides, Proteines :
                {
                    // calcul de la difference entre besoin et apport selon coefApport :
                    d = listBesoinUser.get(i) * 0.5 - totalArray[i];
                    if (d < 0) {
                        // si d <= 10% en plus des apports conseilles : A
                        if (Math.abs(d) <= 0.1 * listBesoinUser.get(i)
                                && score <= 1) {
                            score = 1;
                        }
                        // si 20% < d < 50% en plus des apports conseilles : B
                        else if (Math.abs(d) < 0.5 * listBesoinUser.get(i)) {
                            score = 2;
                        }
                        // sinon si d >= 50% apports conseilles : C
                        else {
                            score = 3;
                            break;
                        }
                    }
                } else // Conditions autres elements :
                {
                    // calcul de la difference entre besoin et apport selon coefApport :
                    d = listBesoinUser.get(i) * coefApportJselonPeriodeJournee - totalArray[i];
                    if (d < 0) {
                        // si d <= 10% en plus des apports conseilles : A
                        if (Math.abs(d) <= 0.1 * listBesoinUser.get(i) * coefApportJselonPeriodeJournee
                                && score <= 1) {
                            score = 1;
                        }
                        // si 10% < d <= 20% en plus des apports conseilles : B
                        else if (Math.abs(d) <= 0.2 * listBesoinUser.get(i) * coefApportJselonPeriodeJournee) {
                            score = 2;
                        }
                        // sinon si d > 20% apports conseilles : C
                        else {
                            score = 3;
                            break;
                        }
                    }
                }
            }

            // NutriScore :
            if (score == 0) {
                // A+
                setNutriScore("A+", R.color.nutriscore_vert1);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            } else if (score == 1) {
                //A
                setNutriScore("A", R.color.nutriscore_vert2);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            } else if (score == 2) {
                //B
                setNutriScore("B", R.color.nutriscore_orange);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            } else {
                //C
                setNutriScore("C", R.color.nutriscore_rouge);
                textViewScore.setText(nutriScoreCharacter);
                textViewScore.setTextColor(context.getColor(nutriScoreColor));
            }

        } else {
            nutriScoreCharacter = "";
            textViewScore.setText(nutriScoreCharacter);
        }
    }

    private static void setNutriScore(String lettre, int color) {
        nutriScoreCharacter = lettre;
        nutriScoreColor = color;
    }

    public static ArrayList<Integer> getColorInstantaneeFoodTextViewComparing(Double[] arrayTotalValues, ProfileDailyNeedsClass profil) {

        ArrayList<Integer> arrayListColorResult = new ArrayList<>();

        double coefApportJselonPeriodeJournee = TransitionClass.getApportInstantaneSelonPeriodeDeLaJournee();
        ArrayList<Double> arrayListbesoinUser = profil.getDailyNeedDOUBLEValuesList();
        double d;

        for (int i = 0; i < 8; i++) // calories, lipide, acideGrasSature, glucide, sucre, fibre, proteine, sel;
        {

            if (i == 1 || i == 3 || i == 6) //Conditions pour Lipides, Glucides, Proteines :
            {
                // calcul de la difference entre besoin et apport selon coefApport :
                d = arrayListbesoinUser.get(i) * 0.5 - arrayTotalValues[i];
                if (d < 0) {
                    // si d <= 10% en plus des apports conseilles : A
                    if (Math.abs(d) <= 0.1 * arrayListbesoinUser.get(i)) {
                        arrayListColorResult.add(R.color.nutriscore_vert2);
                    }
                    // si 20% < d < 50% en plus des apports conseilles : B
                    else if (Math.abs(d) < 0.5 * arrayListbesoinUser.get(i)) {
                        arrayListColorResult.add(R.color.nutriscore_orange);
                    }
                    // sinon si d >= 50% apports conseilles : C
                    else {
                        arrayListColorResult.add(R.color.nutriscore_rouge);
                    }
                } else {
                    // A+
                    arrayListColorResult.add(R.color.nutriscore_vert1);
                }
            } else // Conditions autres elements :
            {
                // calcul de la difference entre besoin et apport selon coefApport :
                d = arrayListbesoinUser.get(i) * coefApportJselonPeriodeJournee - arrayTotalValues[i];
                if (d < 0) {
                    // si d <= 10% en plus des apports conseilles : A
                    if (Math.abs(d) <= 0.1 * arrayListbesoinUser.get(i) * coefApportJselonPeriodeJournee) {
                        arrayListColorResult.add(R.color.nutriscore_vert2);
                    }
                    // si 10% < d <= 20% en plus des apports conseilles : B
                    else if (Math.abs(d) <= 0.2 * arrayListbesoinUser.get(i) * coefApportJselonPeriodeJournee) {
                        arrayListColorResult.add(R.color.nutriscore_orange);
                    }
                    // sinon si d > 20% apports conseilles : C
                    else {
                        arrayListColorResult.add(R.color.nutriscore_rouge);
                    }
                } else {
                    // A+
                    arrayListColorResult.add(R.color.nutriscore_vert1);
                }
            }
        }
        return arrayListColorResult;

    }

    public static ArrayList<Integer> getColorInstantaneeFoodTextViewComparingAllElements(Double[] arrayTotalValues, ProfileDailyNeedsClass profil) {

        ArrayList<Integer> arrayListColorResult = new ArrayList<>();

        double coefApportJselonPeriodeJournee = TransitionClass.getApportInstantaneSelonPeriodeDeLaJournee();
        ArrayList<Double> arrayListbesoinUser = profil.getDailyNeedDOUBLEValuesList();
        double d;

        for (int i = 0; i < arrayTotalValues.length; i++) // Tous les elements;
        {

            //Conditions pour Calories, AcidesGrasSature, Sucres, Sel {plus strict que les autres} :
            if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.CALORIES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.ACIDEGRASSATURES.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SUCRE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.SEL.ordinal()) {
                // calcul de la difference entre besoin et apport selon coefApport :
                d = arrayListbesoinUser.get(i) * coefApportJselonPeriodeJournee - arrayTotalValues[i];
                if (d < 0) {
                    // si d <= 10% en plus des apports conseilles : A
                    if (Math.abs(d) <= 0.1 * arrayListbesoinUser.get(i) * coefApportJselonPeriodeJournee) {
                        arrayListColorResult.add(R.color.nutriscore_vert2);
                    }
                    // si 10% < d <= 20% en plus des apports conseilles : B
                    else if (Math.abs(d) <= 0.2 * arrayListbesoinUser.get(i) * coefApportJselonPeriodeJournee) {
                        arrayListColorResult.add(R.color.nutriscore_orange);
                    }
                    // sinon si d > 20% apports conseilles : C
                    else {
                        arrayListColorResult.add(R.color.nutriscore_rouge);
                    }
                } else {
                    // A+
                    arrayListColorResult.add(R.color.nutriscore_vert1);
                }
            }

            // Conditions Lipides, Glucides, Proteines :
            else if (i == LibraryArrayBitmapDrawingRessources.enumElementsListe.LIPIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.GLUCIDE.ordinal()
                    || i == LibraryArrayBitmapDrawingRessources.enumElementsListe.PROTEINE.ordinal()) {
                // calcul de la difference entre besoin et apport selon coefApport :
                d = arrayListbesoinUser.get(i) * 0.5 - arrayTotalValues[i];
                if (d < 0) {
                    // si d <= 10% en plus des apports conseilles : A
                    if (Math.abs(d) <= 0.1 * arrayListbesoinUser.get(i)) {
                        arrayListColorResult.add(R.color.nutriscore_vert2);
                    }
                    // si 10% < d < 50% en plus des apports conseilles : B
                    else if (Math.abs(d) < 0.5 * arrayListbesoinUser.get(i)) {
                        arrayListColorResult.add(R.color.nutriscore_orange);
                    }
                    // sinon si d >= 50% apports conseilles : C
                    else {
                        arrayListColorResult.add(R.color.nutriscore_rouge);
                    }
                } else {
                    // A+
                    arrayListColorResult.add(R.color.nutriscore_vert1);
                }
            }

            // Conditions autres elements :
            else {
                // calcul de la difference entre besoin et apport selon coefApport :
                d = arrayListbesoinUser.get(i) * 0.25 - arrayTotalValues[i];
                if (d < 0) {
                    // si d <= 10% en plus des apports conseilles : A
                    if (Math.abs(d) <= 0.1 * arrayListbesoinUser.get(i)) {
                        arrayListColorResult.add(R.color.nutriscore_vert2);
                    }
                    // si 10% < d < 50% en plus des apports conseilles : A+
                    else if (Math.abs(d) < 0.5 * arrayListbesoinUser.get(i)) {
                        arrayListColorResult.add(R.color.nutriscore_vert1);
                    }
                    // sinon d > 50% en plus des apports conseilles : C
                    else {
                        arrayListColorResult.add(R.color.nutriscore_rouge);
                    }
                } else {
                    // pas assez d'element mange : d < 25% apports conseilles C
                    arrayListColorResult.add(R.color.nutriscore_bleu);
                }
            }
        }
        return arrayListColorResult;

    }

    public static ArrayList<Integer> getColorFoodTextViewComparing(Double[] arrayTotalValues, ProfileDailyNeedsClass profil, int enumTimeDayMoment) {

        ArrayList<Integer> arrayListColorResult = new ArrayList<>();

        double coefApportJselonPeriodeJournee = TransitionClass.getApportSelonPeriodeDeLaJourneeADefinir(enumTimeDayMoment);
        ArrayList<Double> arrayListbesoinUser = profil.getDailyNeedDOUBLEValuesList();
        double d;

        for (int i = 0; i < 8; i++) // calories, lipide, acideGrasSature, glucide, sucre, fibre, proteine, sel;
        {

            if (i == 1 || i == 3 || i == 6) //Conditions pour Lipides, Glucides, Proteines :
            {
                // calcul de la difference entre besoin et apport selon coefApport :
                d = arrayListbesoinUser.get(i) * 0.5 - arrayTotalValues[i];
                if (d < 0) {
                    // si d <= 10% en plus des apports conseilles : A
                    if (Math.abs(d) <= 0.1 * arrayListbesoinUser.get(i)) {
                        arrayListColorResult.add(R.color.nutriscore_vert2);
                    }
                    // si 20% < d < 50% en plus des apports conseilles : B
                    else if (Math.abs(d) < 0.5 * arrayListbesoinUser.get(i)) {
                        arrayListColorResult.add(R.color.nutriscore_orange);
                    }
                    // sinon si d >= 50% apports conseilles : C
                    else {
                        arrayListColorResult.add(R.color.nutriscore_rouge);
                    }
                } else {
                    // A+
                    arrayListColorResult.add(R.color.nutriscore_vert1);
                }
            } else // Conditions autres elements :
            {
                // calcul de la difference entre besoin et apport selon coefApport :
                d = arrayListbesoinUser.get(i) * coefApportJselonPeriodeJournee - arrayTotalValues[i];
                if (d < 0) {
                    // si d <= 10% en plus des apports conseilles : A
                    if (Math.abs(d) <= 0.1 * arrayListbesoinUser.get(i) * coefApportJselonPeriodeJournee) {
                        arrayListColorResult.add(R.color.nutriscore_vert2);
                    }
                    // si 10% < d <= 20% en plus des apports conseilles : B
                    else if (Math.abs(d) <= 0.2 * arrayListbesoinUser.get(i) * coefApportJselonPeriodeJournee) {
                        arrayListColorResult.add(R.color.nutriscore_orange);
                    }
                    // sinon si d > 20% apports conseilles : C
                    else {
                        arrayListColorResult.add(R.color.nutriscore_rouge);
                    }
                } else {
                    // A+
                    arrayListColorResult.add(R.color.nutriscore_vert1);
                }
            }
        }
        return arrayListColorResult;

    }

    public static double getApportInstantaneSelonPeriodeDeLaJournee() {

        double result = 0.0;

        TransitionClass.calculateTimeDayMoment();
        if (TransitionClass.getTimeDayMoment() == TransitionClass.enumTimeDayMoment.NUIT.ordinal()) {
            result = 0.05; // 5%
        } else if (TransitionClass.getTimeDayMoment() == TransitionClass.enumTimeDayMoment.MATIN.ordinal()) {
            result = 0.50; // 50%
        } else if (TransitionClass.getTimeDayMoment() == TransitionClass.enumTimeDayMoment.MIDI.ordinal()) {
            result = 0.50; // 50%
        } else if (TransitionClass.getTimeDayMoment() == TransitionClass.enumTimeDayMoment.APRESMIDI.ordinal()) {
            result = 0.20; // 20%
        } else {
            result = 0.20; // 20% le soir
        }
        return result;
    }

    public static double getApportSelonPeriodeDeLaJourneeADefinir(int enumTimeDayMoment) {

        double result = 0.0;

        if (enumTimeDayMoment == TransitionClass.enumTimeDayMoment.NUIT.ordinal()) {
            result = 0.05; // 5%
        } else if (enumTimeDayMoment == TransitionClass.enumTimeDayMoment.MATIN.ordinal()) {
            result = 0.50; // 50%
        } else if (enumTimeDayMoment == TransitionClass.enumTimeDayMoment.MIDI.ordinal()) {
            result = 0.50; // 50%
        } else if (enumTimeDayMoment == TransitionClass.enumTimeDayMoment.APRESMIDI.ordinal()) {
            result = 0.20; // 20%
        } else {
            result = 0.20; // 20% le soir
        }
        return result;
    }

    // ---------------------------------------------------------------------------------------------
    // GETTER / SETTER :

    public static String[] getArrayDayMoment() {
        return arrayDayMoment;
    }

    public static int[] getDate() {
        // jour / mois / annee
        return new int[]{dateDay, dateMonth, dateYear};
    }

    public static String dateDAujourdhuiToString(Context context) {

        if (TransitionClass.langageActual.equals(TransitionClass.languageArraySelector[enumLanguageSelector.Anglais.ordinal()])) {
            return context.getString(R.string.date_d_aujourdhui) + " : " + TransitionClass.getDate()[1] + " / " + TransitionClass.getDate()[0] + " / " + TransitionClass.getDate()[2];
        } else {
            return context.getString(R.string.date_d_aujourdhui) + " : le " + TransitionClass.getDate()[0] + " / " + TransitionClass.getDate()[1] + " / " + TransitionClass.getDate()[2];
        }

    }

    public static String getDateText(Context context, int[] intArrayDate) {
        String text0 = context.getResources().getString(R.string.langageActuel);
        String text1 = "no data !";

        if (text0.equals("Anglais")) {

            String day = String.valueOf(intArrayDate[0]);
            String month = String.valueOf(intArrayDate[1]);

            if (intArrayDate[0] < 10) {
                day = "0" + intArrayDate[0];
            }
            if (intArrayDate[1] < 10) {
                month = "0" + intArrayDate[1];
            }

            text1 = month + " / " + day + " / " + intArrayDate[2];
        } else if (text0.equals("Francais")) {

            String day = String.valueOf(intArrayDate[0]);
            String month = String.valueOf(intArrayDate[1]);

            if (intArrayDate[0] < 10) {
                day = "0" + intArrayDate[0];
            }
            if (intArrayDate[1] < 10) {
                month = "0" + intArrayDate[1];
            }

            text1 = day + " / " + month + " / " + intArrayDate[2];
        }
        return text1;
    }

    public static int getTimeDayMoment() {
        return TransitionClass.timeDayMoment;
    }

    public static String getDayMomentText() {
        return TransitionClass.dayMomentText;
    }

    public static ArrayList<ArrayList<FoodClass>> getBigArrayListFoodClass() {
        return bigArrayListFoodClass;
    }

    public static void setAddFoodListinBigArrayListFoodClass(ArrayList<FoodClass> foodlist) {

    }

    public static void setBigArrayListFoodClass(ArrayList<ArrayList<FoodClass>> bigArrayListFoodClass) {
        TransitionClass.bigArrayListFoodClass = bigArrayListFoodClass;
    }

    public static SuiviAlimentaireClass getSuiviAlimentaireClassArrayListStatic() {
        return suiviAlimentaireClassArrayListStatic;
    }

    public static void setSuiviAlimentaireClassArrayListStatic(SuiviAlimentaireClass suiviAlimentaireClassArrayListStatic) {
        TransitionClass.suiviAlimentaireClassArrayListStatic = suiviAlimentaireClassArrayListStatic;
    }
}
