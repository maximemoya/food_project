package fr.maxime.mygame01.DailyNeedView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fr.maxime.mygame01.FoodListView.FoodListActivity;
import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.GameViewActivity;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;
import fr.maxime.mygame01.ResultView.ResultSuiviRepasActivity;

public class DailyNeedActivity extends AppCompatActivity {

    Context context;

    LinearLayoutCompat rowLayout;
    LinearLayout scrollMainLayout;
    TextView textView, textNeedView, titreTextView, pseudoTextView;
    Button cancelButton;

    ArrayList<LinearLayoutCompat> linearlayoutTextViewArrayList = new ArrayList<>();
    ArrayList<String> yourDailyNeedTEXTList = new ArrayList<>();
    ArrayList<Double> yourDailyNeedDOUBLEValuesList = new ArrayList<>();

    String[] arrayCaracteristicName;

    int poidsIdeal = 0;

    boolean color;
    boolean isOnDailyNeed = false;

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_need);

        context = this;
        if(TransitionClass.activityToBackUpByCancel != LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.PROFILEMENU.ordinal()){
            TransitionClass.setMusic(context, R.raw.ed_music_menu_profil_v01);
        }

        TransitionClass.activityToBackUpByCancelLevel2 = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.DAILYNEEDACTIVITY.ordinal();
        TransitionClass.resetMusicForFoodSelectorV2 = true;

        titreTextView = findViewById(R.id.dailyNeed_titreText);
        pseudoTextView = findViewById(R.id.dailyNeed_pseudoName);
        scrollMainLayout = findViewById(R.id.linearlayoutScrollViewDailyNeed);
        cancelButton = findViewById(R.id.dailyNeed_cancelButton);

        // Set PseudoNameTextView :
        if (TransitionClass.profileActual != null) {
            poidsIdeal = TransitionClass.profileActual.getPoidsIdeal();
            yourDailyNeedDOUBLEValuesList = TransitionClass.profileActual.getDailyNeedDOUBLEValuesList();
            yourDailyNeedTEXTList = TransitionClass.profileActual.getDailyNeedSTRINGlist(context);
            pseudoTextView.setText(TransitionClass.profileActual.getPseudo());
        }
        // Clique sur PseudoNameTextView :
        pseudoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionClass.soundEmotion = MediaPlayer.create(context, R.raw.emotion_interet);
                TransitionClass.playTransitionClassMediaPlayerSound(TransitionClass.soundEmotion);
            }
        });

        // Clique sur TITRE (besoin quotidien / besoin actuel)
        titreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                isOnDailyNeed = !isOnDailyNeed;
                affichageDansVerticalScrollView();
            }
        });

        // Clique sur Cancel Button :
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCloseActivity();
            }
        });

        arrayCaracteristicName = new String[]{
                this.getResources().getString(R.string.text_calories),
                this.getResources().getString(R.string.text_lipide),
                this.getResources().getString(R.string.text_acideGrasSature),
                this.getResources().getString(R.string.text_glucide),
                this.getResources().getString(R.string.text_sucre),
                this.getResources().getString(R.string.text_fibre),
                this.getResources().getString(R.string.text_proteine),
                this.getResources().getString(R.string.text_sel),
                this.getResources().getString(R.string.text_vitamineA),
                this.getResources().getString(R.string.text_vitamineB1),
                this.getResources().getString(R.string.text_vitamineB2),
                this.getResources().getString(R.string.text_vitamineB3),
                this.getResources().getString(R.string.text_vitamineB5),
                this.getResources().getString(R.string.text_vitamineB6),
                this.getResources().getString(R.string.text_vitamineB9),
                this.getResources().getString(R.string.text_vitamineB12),
                this.getResources().getString(R.string.text_vitamineC),
                this.getResources().getString(R.string.text_vitamineD),
                this.getResources().getString(R.string.text_vitamineE),
                this.getResources().getString(R.string.text_eau),
                this.getResources().getString(R.string.text_calcium),
                this.getResources().getString(R.string.text_cuivre),
                this.getResources().getString(R.string.text_fer),
                this.getResources().getString(R.string.text_iode),
                this.getResources().getString(R.string.text_magnesium),
                this.getResources().getString(R.string.text_manganese),
                this.getResources().getString(R.string.text_phosphore),
                this.getResources().getString(R.string.text_potassium),
                this.getResources().getString(R.string.text_selenium),
                this.getResources().getString(R.string.text_sodium),
                this.getResources().getString(R.string.text_zinc)};

        // Affichage dans ScrollView :
        affichageDansVerticalScrollView();

    }

    // ---------------------------------------------------------------------------------------------
    // My methods :

    private void affichageDansVerticalScrollView() {

        scrollMainLayout.removeAllViews();
        linearlayoutTextViewArrayList.clear();

        // ajout du poids ideal :
        rowLayout = new LinearLayoutCompat(this);
        rowLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, -2, -2, 5, 5, 20, 20, 0, 0)); // -1 MatchParent | -2 wrapContent
        rowLayout.setOrientation(LinearLayoutCompat.HORIZONTAL);

        textView = new TextView(this);
        String text = getResources().getString(R.string.poids_ideal) + " ";
        textView.setText(text);
        if (text.length() < 35) {
            textView.setTextSize(22);
        } else {
            textView.setTextSize(20);
        }
        textView.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, MenuActivity.screenSizeX / 2 - 10, -2, 0, 0, 0, 0, 0, 0)); // -1 MatchParent | -2 wrapContent
        textView.setTypeface(Typeface.create("casual", Typeface.NORMAL));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(Color.WHITE);

        textNeedView = new TextView(this);
        textNeedView.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, MenuActivity.screenSizeX / 2 - 10, -1, 0, 0, 0, 0, 0, 0)); // -1 MatchParent | -2 wrapContent
        textNeedView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textNeedView.setGravity(Gravity.CENTER_VERTICAL);
        textNeedView.setTextSize(20);
        text = poidsIdeal + " kg";
        textNeedView.setText(text);

        textView.setTextColor(this.getColor(R.color.vert2));
        textNeedView.setTextColor(this.getColor(R.color.vert2));

        rowLayout.addView(textView);
        rowLayout.addView(textNeedView);
        scrollMainLayout.addView(rowLayout);

        // ajout des elements arrayCaracteristicName :
        for (int i = 0; i < arrayCaracteristicName.length; i++) {

            // Layout to put Caracteristic TextView in
            rowLayout = new LinearLayoutCompat(this);
            rowLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, -2, -2, 5, 5, 20, 20, 0, 0)); // -1 MatchParent | -2 wrapContent
            rowLayout.setOrientation(LinearLayoutCompat.HORIZONTAL);

            // Caracteristic TextView
            final TextView textView1 = new TextView(this);
            text = arrayCaracteristicName[i];
            textView1.setText(text);
            if (text.length() < 35) {
                textView1.setTextSize(22);
            } else {
                textView1.setTextSize(20);
            }
//            textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(MenuActivity.screenSizeX / 2 - 10, -2)); // -1 MatchParent | -2 wrapContent
            textView1.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, MenuActivity.screenSizeX / 2 - 10, -2, 0, 0, 0, 0, 0, 0)); // -1 MatchParent | -2 wrapContent
            textView1.setTypeface(Typeface.create("casual", Typeface.NORMAL));
            textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView1.setTextColor(Color.WHITE);
            textView1.setBackgroundResource(R.drawable.button_border_dark_elements);

            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                    TransitionClass.setFoodListActivitySpinnerByClickOnCategoryText(textView1);
                    Intent intent = new Intent(context, FoodListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);

                }
            });

            textNeedView = new TextView(this);
//            textNeedView.setLayoutParams(new LinearLayoutCompat.LayoutParams(MenuActivity.screenSizeX / 2 - 10, -2)); // -1 MatchParent | -2 wrapContent
            textNeedView.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, MenuActivity.screenSizeX / 2 - 10, -1, 0, 0, 0, 0, 0, 0)); // -1 MatchParent | -2 wrapContent
            //textNeedView.setTypeface(Typeface.create("casual", Typeface.BOLD_ITALIC));
            textNeedView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textNeedView.setGravity(Gravity.CENTER_VERTICAL);
            textNeedView.setTextSize(20);

            if (isOnDailyNeed) {

                titreTextView.setText(R.string.dailyNeedActivity_besoinQuotidient);
                titreTextView.setBackgroundResource(R.drawable.besoin_quotidien2_background);

                textNeedView.setText(yourDailyNeedTEXTList.get(i));

                color = !color;
                if (color) {
                    textView1.setTextColor(this.getColor(R.color.vert1));
                    textNeedView.setTextColor(this.getColor(R.color.vert1));
                } else {
                    textView1.setTextColor(this.getColor(R.color.vert2));
                    textNeedView.setTextColor(this.getColor(R.color.vert2));

                }
            } else {

                titreTextView.setText(R.string.dailyNeedActivity_besoinTempsReel);
                titreTextView.setBackgroundResource(R.drawable.besoin_quotidien_background);

                textView1.setTextColor(TransitionClass.getColorElementsCompareDailyNeedToActualNeed(context)[i]);

                textNeedView.setText(TransitionClass.getArrayStringByElementsForActualNeed(TransitionClass.getArrayDoubleValueByElementsForActualNeedWITHOUTNEGATIVE())[i]);
                textNeedView.setTextColor(TransitionClass.getColorElementsCompareDailyNeedToActualNeed(context)[i]);

            }

            //Add to Layout
            rowLayout.addView(textView1);
            rowLayout.addView(textNeedView);
            linearlayoutTextViewArrayList.add(rowLayout);

        }

        LinearLayoutCompat layoutButtonContinue = new LinearLayoutCompat(context);
        layoutButtonContinue.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0, 20, 25, 10, Gravity.END, 0));
        layoutButtonContinue.setOrientation(LinearLayoutCompat.VERTICAL);

        Button buttonContinue = new Button(context);
        buttonContinue.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                        0, 0, 0, 0, Gravity.CENTER, 0));
        buttonContinue.setBackground(ContextCompat.getDrawable(context, R.drawable.button_border2));
        buttonContinue.setPadding(100, 10, 100, 10);
        buttonContinue.setText(context.getString(R.string.continuer));
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onContinueClick();
            }
        });

        layoutButtonContinue.addView(buttonContinue);
        linearlayoutTextViewArrayList.add(layoutButtonContinue);

        // affichage dans ScrollView
        for (LinearLayoutCompat linearLayout : linearlayoutTextViewArrayList) {
            scrollMainLayout.addView(linearLayout);
        }
    }

    private void privateLogicMethodOnClose() {
        if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.MENU.ordinal()) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.GAME.ordinal()) {
            Intent intent = new Intent(getApplicationContext(), GameViewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.FOODSELECTORV2.ordinal()) {
            Intent intent = new Intent(getApplicationContext(), FoodSelectorActivityV2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.RESULTSUIVIREPAS.ordinal()) {
            Intent intent = new Intent(getApplicationContext(), ResultSuiviRepasActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.PROFILEMENU.ordinal()) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.FOODLIST.ordinal()) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void onContinueClick() {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.ValidationSound.ordinal());
        privateLogicMethodOnClose();
    }

    private void onCloseActivity() {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        privateLogicMethodOnClose();

    }

    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {

        onCloseActivity();
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