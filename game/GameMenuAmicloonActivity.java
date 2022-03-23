package fr.maxime.mygame01.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.Amicloon;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.Sauvegarde;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;
import fr.maxime.mygame01.game.amicloonVisual.AmicloonView;
import fr.maxime.mygame01.game.amicloonVisual.AmicloonVisualActivity;

public class GameMenuAmicloonActivity extends AppCompatActivity {

    private Context context;

    private LinearLayout linearLayoutVertical;
    private View viewStubAmicloon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu_amicloon_acticity);

        context = this;
        if (TransitionClass.activityToBackUpByCancel != LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.GAME.ordinal()){

            TransitionClass.setMusic(context, R.raw.ed_music_menu_tamagochi_v01);
        }

        if (TransitionClass.foodOnDropZoneList==null){
            TransitionClass.foodOnDropZoneList = new ArrayList<>();
        }


        linearLayoutVertical = findViewById(R.id.gameMenuAmicloon_ScrollViewVerticalLinearLayout);
        viewStubAmicloon = new AmicloonView(context);

        displayUpdate();

    }

    public void gameMenuAmicloonActivity_OnClickCancelImageView(View view) {
        onCancelClick();
    }

    public void gameMenuAmicloonActivity_OnClickNourrir(View view) {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
        Intent intent = new Intent(GameMenuAmicloonActivity.this, GameV2Activity.class);
        startActivity(intent);
    }


    // A CONTINUER :
    public void gameMenuAmicloonActivity_OnClickClean(View view) {
    }

    public void gameMenuAmicloonActivity_OnClickWash(View view) {
    }

    public void gameMenuAmicloonActivity_OnClickTakeAWalk(View view) {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
        Intent intent = new Intent(GameMenuAmicloonActivity.this, AmicloonVisualActivity.class);
        startActivity(intent);
    }

    public void gameMenuAmicloonActivity_OnClickLearn(View view) {
    }

    public void gameMenuAmicloonActivity_OnClickWorkOut(View view) {
    }

    // ---------------------------------------------------------------------------------------------
    // MyMethods :

    private void onCancelClick() {

        TransitionClass.foodOnDropZoneList.clear();

        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        Intent intent = new Intent(GameMenuAmicloonActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    // AFFICHAGE :
    private void displayUpdate(){

        // NEW AMICLOON :
        if (TransitionClass.profileActual.getAmicoon() == null){
            Amicloon amicloon = new Amicloon(context);
            TransitionClass.profileActual.setAmicoon(amicloon);
            // SAUVEGARDE :
            Sauvegarde save = new Sauvegarde(TransitionClass.myLib.listDailyNeedsProfil, TransitionClass.profileActual);
            MaximeToolsLayout.serializeSauvegarde(context, TransitionClass.saveName, save);
        }

        onEAT();

        linearLayoutVertical.removeAllViews();

        String text = "";
        LinearLayout.LayoutParams params = MaximeToolsLayout.setLayoutParams
                (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,10,10,0,0);

        // Date aujourd'hui le : ---
        TextView dateTodaytextView = new TextView(context);
        dateTodaytextView.setLayoutParams(params);
        dateTodaytextView.setText(TransitionClass.dateDAujourdhuiToString(context));
        linearLayoutVertical.addView(dateTodaytextView);

        // Nom de l'Amicloon : ---
        TextView amicloonNametextView = new TextView(context);
        amicloonNametextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,10,10, Gravity.CENTER,0));
        amicloonNametextView.setText(TransitionClass.profileActual.getAmicoon().getName());
        linearLayoutVertical.addView(amicloonNametextView);

        // Date anniverssaire Amicloon : ---
        TextView amicloonBirthdaytextView = new TextView(context);
        amicloonBirthdaytextView.setLayoutParams(params);
        amicloonBirthdaytextView.setText(TransitionClass.profileActual.getAmicoon().getDateBirthdayToString(context));
        linearLayoutVertical.addView(amicloonBirthdaytextView);

        // Vie : --- %
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,15,15,0,0));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        // ---
        TextView vieTitleTextView = new TextView(context);
        vieTitleTextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,2));
        text = context.getString(R.string.vie) + " : ";
        vieTitleTextView.setText(text);
        linearLayout.addView(vieTitleTextView);
        // ---
        TextView amicloonVietextView = new TextView(context);
        amicloonVietextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,3));
        amicloonVietextView.setText(TransitionClass.profileActual.getAmicoon().getVieToString());
        linearLayout.addView(amicloonVietextView);
        // ---
        linearLayoutVertical.addView(linearLayout);

        // Humeur : --- %
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,15,15,0,0));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        // ---
        TextView humeurTitleTextView = new TextView(context);
        humeurTitleTextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,2));
        text = context.getString(R.string.humeur) + " : ";
        humeurTitleTextView.setText(text);
        linearLayout.addView(humeurTitleTextView);
        // ---
        TextView amicloonHumeurtextView = new TextView(context);
        amicloonHumeurtextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,3));
        amicloonHumeurtextView.setText(TransitionClass.profileActual.getAmicoon().getHumeurToString());
        linearLayout.addView(amicloonHumeurtextView);
        // ---
        linearLayoutVertical.addView(linearLayout);

        // Intelligence : --- %
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,15,15,0,0));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        // ---
        TextView intelligencetextView = new TextView(context);
        intelligencetextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,2));
        text = context.getString(R.string.Cerveau) + " : ";
        intelligencetextView.setText(text);
        linearLayout.addView(intelligencetextView);
        // ---
        TextView amicloonIntelligencetextView = new TextView(context);
        amicloonIntelligencetextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,3));
        amicloonIntelligencetextView.setText(TransitionClass.profileActual.getAmicoon().getIntelligenceToString());
        linearLayout.addView(amicloonIntelligencetextView);
        // ---
        linearLayoutVertical.addView(linearLayout);

        // Force : --- %
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,15,15,0,0));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        // ---
        TextView forcetextView = new TextView(context);
        forcetextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,2));
        text = context.getString(R.string.corps) + " : ";
        forcetextView.setText(text);
        linearLayout.addView(forcetextView);
        // ---
        TextView amicloonForcetextView = new TextView(context);
        amicloonForcetextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,3));
        amicloonForcetextView.setText(TransitionClass.profileActual.getAmicoon().getForceToString());
        linearLayout.addView(amicloonForcetextView);
        // ---
        linearLayoutVertical.addView(linearLayout);

        // Proprete : --- %
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,15,15,0,0));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        // ---
        TextView propretetextView = new TextView(context);
        propretetextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,2));
        text = context.getString(R.string.proprete) + " : ";
        propretetextView.setText(text);
        linearLayout.addView(propretetextView);
        // ---
        TextView amicloonPropretetextView = new TextView(context);
        amicloonPropretetextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0,0,0,0,0,3));
        amicloonPropretetextView.setText(TransitionClass.profileActual.getAmicoon().getPropreteToString());
        linearLayout.addView(amicloonPropretetextView);
        // ---
        linearLayoutVertical.addView(linearLayout);


        // Elements food in belly :
        String[] arrayTitleElements = LibraryArrayBitmapDrawingRessources.getdescriptionFoodArrayElements(context);
        for (int i = 0; i < TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesByElements().length; i++){

            linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0,0,15,15,0,0));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            // --- elementTitletextView :

            TextView elementTitletextView = new TextView(context);
            elementTitletextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0,0,0,0,0,2));
            text = arrayTitleElements[i] + " ";
            elementTitletextView.setText(text);
                // color start
            elementTitletextView.setTextColor(context.getColor
                    (TransitionClass.getArrayintColorByElementsPercentComparator
                            (TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesByElements(),
                                    TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesMAXbyElements())[i]));
                // color end
            linearLayout.addView(elementTitletextView);

            // --- elementValueInBellytextView :

            TextView elementValueInBellytextView = new TextView(context);
            elementValueInBellytextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0,0,0,0,0,2));
            elementValueInBellytextView.setGravity(Gravity.END);
            text = TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesByElements()[i] + " / ";
            elementValueInBellytextView.setText(text);
            // color start
            elementValueInBellytextView.setTextColor(context.getColor
                    (TransitionClass.getArrayintColorByElementsPercentComparator
                            (TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesByElements(),
                                    TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesMAXbyElements())[i]));
            // color end
            linearLayout.addView(elementValueInBellytextView);

            // --- elementValueMaxtextView :

            TextView elementValueMaxtextView = new TextView(context);
            elementValueMaxtextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0,0,0,0,0,3));
            text = TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesMAXbyElements()[i] + " " + FoodClass.arrayStringUnitValuesFoodStatic[i];
            elementValueMaxtextView.setText(text);
            // color start
            elementValueMaxtextView.setTextColor(context.getColor
                    (TransitionClass.getArrayintColorByElementsPercentComparator
                            (TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesByElements(),
                                    TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesMAXbyElements())[i]));
            // color end
            linearLayout.addView(elementValueMaxtextView);

            // --- percentValuetextView :

            TextView percentValuetextView = new TextView(context);
            percentValuetextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0,0,0,0,0,1));
            percentValuetextView.setGravity(Gravity.END);
            text = TransitionClass.getArrayStringByElementsPercentComparator
                    (TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesByElements(),TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesMAXbyElements())[i];
            percentValuetextView.setText(text);
            // color start
            percentValuetextView.setTextColor(context.getColor
                    (TransitionClass.getArrayintColorByElementsPercentComparator
                            (TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesByElements(),
                                    TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesMAXbyElements())[i]));
            // color end
            linearLayout.addView(percentValuetextView);
            // ---
            linearLayoutVertical.addView(linearLayout);

        }

        // SAUVEGARDE :
        Sauvegarde save = new Sauvegarde(TransitionClass.myLib.listDailyNeedsProfil, TransitionClass.profileActual);
        MaximeToolsLayout.serializeSauvegarde(context, TransitionClass.saveName, save);

    }

    private void onEAT(){
        if (TransitionClass.foodOnDropZoneList.size()>0){

            for (FoodClass food : TransitionClass.foodOnDropZoneList){
                food.setQuantity(FoodSelectorActivityV2.enumQuantityChosenAmount.MEDIUM.geti());
            }

            Double[] arrayAfterEat = new Double[TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesByElements().length];
            Arrays.fill(arrayAfterEat, 0.0);
            for (int i = 0; i < arrayAfterEat.length; i ++){

                arrayAfterEat[i] += (double)Math.round(FoodClass.getCalculateTotalDoubleValueByElementOfAFoodList(TransitionClass.foodOnDropZoneList)[i]*10)/10;
                arrayAfterEat[i] += (double)Math.round(TransitionClass.profileActual.getAmicoon().getArrayDoubleValuesByElements()[i]);
            }
            TransitionClass.profileActual.getAmicoon().setArrayDoubleValuesByElements(arrayAfterEat);
        }
        TransitionClass.foodOnDropZoneList.clear();

    }




    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {
        onCancelClick();
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