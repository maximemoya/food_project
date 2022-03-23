package fr.maxime.mygame01.ResultView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.GameViewActivity;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

import static android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;

public class ResultViewActivity extends AppCompatActivity {

    private Context context;
    private int dateTimeMoment;
    private String dateTextMoment;
    private ArrayList<FoodClass> arrayFoodSelected = new ArrayList<>();

    private TextView hourDetailText;

    private ArrayList<FoodClass> arrayListFoodGood = new ArrayList<>();
    private ArrayList<FoodClass> arrayListFoodMiddle = new ArrayList<>();
    private ArrayList<FoodClass> arrayListFoodBad = new ArrayList<>();

    private LinearLayout listGoodLayout, listMiddleLayout, listBadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_result_view);
        context = this;

        listGoodLayout = findViewById(R.id.resultLinearLayoutListeGood);
        listMiddleLayout = findViewById(R.id.resultLinearLayoutListeMiddle);
        listBadLayout = findViewById(R.id.resultLinearLayoutListeBad);

        hourDetailText = findViewById(R.id.resultView_detailText);

        daycheck();
        String text = TransitionClass.getDayMomentText() + " :";
        hourDetailText.setText(text);

        for (FoodClass food : arrayListFoodGood) {
            ImageView image = new ImageView(context);
            image.setImageResource(food.bitmapRawRessource);
            listGoodLayout.addView(image, MaximeToolsLayout.setLayoutParams(context, -2, -2, 10, 5, 5, 5, Gravity.START, 0));
        }

        for (FoodClass food : arrayListFoodMiddle) {
            ImageView image = new ImageView(context);
            image.setImageResource(food.bitmapRawRessource);
            listMiddleLayout.addView(image, MaximeToolsLayout.setLayoutParams(context, -2, -2, 10, 5, 5, 5, Gravity.START, 0));
        }

        for (FoodClass food : arrayListFoodBad) {
            ImageView image = new ImageView(context);
            image.setImageResource(food.bitmapRawRessource);
            listBadLayout.addView(image, MaximeToolsLayout.setLayoutParams(context, -2, -2, 10, 5, 5, 5, Gravity.START, 0));
        }

    }

    //----------------------------------------------------------------------------------------------
    // My methods :

    // Valeur dietetique nourriture selon periode de la journee :
    private void daycheck() {
        TransitionClass.calculateTimeDayMoment();
        dateTimeMoment = TransitionClass.getTimeDayMoment();
        dateTextMoment = TransitionClass.getDayMomentText();
        if (TransitionClass.foodOnDropZoneList != null) {
            arrayFoodSelected = TransitionClass.foodOnDropZoneList;
            Collections.sort(arrayFoodSelected, FoodClass.comparatorCategory);
            for (FoodClass food : arrayFoodSelected) {

                // legume :
                if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.legumineux.ordinal()) {
                    arrayListFoodGood.add(food);
                }

                // n'est pas un legume et hors de la nuit :
                else if (dateTimeMoment > TransitionClass.enumTimeDayMoment.NUIT.ordinal()) {

                    // fruit :
                    if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.fruit.ordinal()) {

                        // soir :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.SOIR.ordinal()) {
                            arrayListFoodMiddle.add(food);
                        }
                        // tout sauf soir :
                        else {
                            arrayListFoodGood.add(food);
                        }

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.feculent.ordinal()) {

                        // matin :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.MATIN.ordinal()) {
                            arrayListFoodGood.add(food);
                        }
                        // tout sauf matin et midi :
                        else {
                            arrayListFoodMiddle.add(food);
                        }

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.proteine.ordinal()) {

                        // tout le temps :
                            arrayListFoodMiddle.add(food);

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.sucrerie.ordinal()) {

                        // matin et midi :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.MATIN.ordinal() || dateTimeMoment == TransitionClass.enumTimeDayMoment.MIDI.ordinal()) {
                            arrayListFoodMiddle.add(food);
                        }
                        // tout sauf matin et midi :
                        else {
                            arrayListFoodBad.add(food);
                        }

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.fruitSec.ordinal()) {

                        // soir :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.SOIR.ordinal()) {
                            arrayListFoodBad.add(food);
                        }
                        // tout sauf soir :
                        else {
                            arrayListFoodGood.add(food);
                        }

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.plante.ordinal()) {

                        // matin et midi :
                        if (dateTimeMoment <= TransitionClass.enumTimeDayMoment.MIDI.ordinal()) {
                            arrayListFoodGood.add(food);
                        }
                        // tout sauf matin et midi :
                        else {
                            arrayListFoodBad.add(food);
                        }

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.friture.ordinal()) {

                        // matin et midi :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.MATIN.ordinal() || dateTimeMoment == TransitionClass.enumTimeDayMoment.MIDI.ordinal()) {
                            arrayListFoodMiddle.add(food);
                        }
                        // tout sauf matin et midi :
                        else {
                            arrayListFoodBad.add(food);
                        }

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.NotUSEDyaourt.ordinal()) {

                        // soir :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.SOIR.ordinal()) {
                            arrayListFoodBad.add(food);
                        }
                        // tout sauf soir :
                        else {
                            arrayListFoodGood.add(food);
                        }

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.oeuf.ordinal()) {

                        arrayListFoodGood.add(food);

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.produit_laitier.ordinal()) {

                        // soir :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.SOIR.ordinal()) {
                            arrayListFoodBad.add(food);
                        }
                        // tout sauf soir :
                        else {
                            arrayListFoodGood.add(food);
                        }
                    }

                    // -----------------------------------------------------------------------------
                    // NEW < viande / poisson / fruit de mer / boisson / champignon / fromage / cereales / Alcool / Condiments >:

                    else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.viande.ordinal()) {

                        // toute la journee :
                        arrayListFoodMiddle.add(food);

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.poisson.ordinal()) {

                        // toute la journee :
                        arrayListFoodGood.add(food);

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.fruitDeMer.ordinal()) {

                        // toute la journee :
                        arrayListFoodGood.add(food);

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.boisson.ordinal() && !food.name.equals(context.getResources().getString(R.string.eau_minerale))) {

                        // soir :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.SOIR.ordinal()) {
                            arrayListFoodBad.add(food);
                        }
                        // tout sauf soir :
                        else {
                            arrayListFoodMiddle.add(food);
                        }
                    }
                    else if (food.name.equals(context.getResources().getString(R.string.eau_minerale))) {

                        // toute la journee :
                        arrayListFoodGood.add(food);
                    }
                    else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.champignon.ordinal()) {

                        // toute la journee :
                        arrayListFoodGood.add(food);

                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.fromage.ordinal()) {

                        // matin et midi :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.MATIN.ordinal() || dateTimeMoment == TransitionClass.enumTimeDayMoment.MIDI.ordinal()) {
                            arrayListFoodMiddle.add(food);
                        }
                        // tout sauf matin ou midi :
                        else {
                            arrayListFoodBad.add(food);
                        }
                    } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.cereales.ordinal()) {

                        // matin et midi :
                        if (dateTimeMoment == TransitionClass.enumTimeDayMoment.MATIN.ordinal() || dateTimeMoment == TransitionClass.enumTimeDayMoment.MIDI.ordinal()) {
                            arrayListFoodGood.add(food);
                        }
                        // apres-midi :
                        else if(dateTimeMoment == TransitionClass.enumTimeDayMoment.APRESMIDI.ordinal()){
                            arrayListFoodMiddle.add(food);
                        }
                        // tout sauf matin, midi, apres-midi :
                        else {
                            arrayListFoodBad.add(food);
                        }
                    }
                    else if( food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.alcool.ordinal()){

                        // Toujours :
                        arrayListFoodBad.add(food);
                    }
                    else if( food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.condiment.ordinal()){

                        // Toujours :
                        arrayListFoodMiddle.add(food);
                    }

                }

                // pendant la nuit :
                else {
                    arrayListFoodBad.add(food);
                }

            }
        }

    }

    private void resetOnDroppingZoneFood() {
        TransitionClass.foodOnDropZoneList = null;
        TransitionClass.foodOnDropZonePositionArray = null;
    }

    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());

        if(TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.GAME.ordinal()){
            Intent intent = new Intent(getApplicationContext(), GameViewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if(TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.FOODSELECTORV2.ordinal()){
            Intent intent = new Intent(getApplicationContext(), FoodSelectorActivityV2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    @Override
    protected void onPause() {
        TransitionClass.music.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        TransitionClass.music.start();
        super.onResume();
    }
}