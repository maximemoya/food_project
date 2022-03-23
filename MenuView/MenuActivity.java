package fr.maxime.mygame01.MenuView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import fr.maxime.mygame01.DailyNeedView.DailyNeedCreateProfilActivity;
import fr.maxime.mygame01.DailyNeedView.ProfileMenuActivity;
import fr.maxime.mygame01.Etagere.EtagereFoodSelectorActivity;
import fr.maxime.mygame01.FoodListView.FoodListActivity;
import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.Sauvegarde;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;
import fr.maxime.mygame01.RemerciementsActivity;
import fr.maxime.mygame01.ResultView.ResultSuiviRepasActivity;
import fr.maxime.mygame01.game.GameMenuAmicloonActivity;
import fr.maxime.mygame01.game.GameV2Activity;
import fr.maxime.mygame01.game.amicloonVisual.AmicloonVisualActivity;
import fr.maxime.mygame01.myIdeaBox.TestRegexActivity;

public class MenuActivity extends AppCompatActivity {

    Context context;

    LinearLayoutCompat scrollMainLayout, rowLayout;
    Button buttonView;
    ImageView imageView;

    public Sauvegarde saveClass = new Sauvegarde();

    public static int screenSizeX;
    public static int screenSizeY;

    String[] arrayMenuSelectionName;
    enum enumMenuSelectionTitleName{
        JOUER, FOODCARACTERISTIC, PROFILE, LABORATOIRE, YOURFOODLIST, TYPEOFALIMENTATION, ETAGERE, TestREGEX, REMERCIEMENTS;
    }

    ArrayList<LinearLayoutCompat> linearlayoutTextViewArrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"ResourceType", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        context = this;

        TransitionClass.langageActual = context.getString(R.string.langageActuel);
        TransitionClass.languageArraySelector = new String[]{"Anglais", "Francais"};

        Display display = this.getDisplay();
        Point size = new Point();
        display.getSize(size);
        screenSizeX = size.x;
        screenSizeY = size.y;

        // librairie :

        if (TransitionClass.myLib == null) {
            TransitionClass.creationDeLaBaseDeDonnee(this);
        }
        TransitionClass.calculateTimeDayMoment();

        if(TransitionClass.debutDuProgramme){
            // CHARGER LA SAUVEGARDE :
            saveClass = MaximeToolsLayout.deserializeSauvegarde(this, TransitionClass.saveName);
            if(saveClass != null){
                saveClass.deserialized(this, TransitionClass.allFoodList);
                TransitionClass.myLib.listDailyNeedsProfil = saveClass.getListDailyNeedsProfil();
                TransitionClass.profileActual = saveClass.getLastProfileUsed();
                TransitionClass.selectedFoodList = new ArrayList<>();
                TransitionClass.selectedFoodList = TransitionClass.globalAllFoodList.get(TransitionClass.profileActual.getTypeAlimentation());

            }
            TransitionClass.debutDuProgramme = false;
        }

        if (TransitionClass.myLib.listDailyNeedsProfil.size()==0){
            Intent intent = new Intent(MenuActivity.this, DailyNeedCreateProfilActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else {
            TransitionClass.alimentationType = TransitionClass.profileActual.getTypeAlimentation();
            TransitionClass.isAlimentationSelected = true;
            Sauvegarde save = new Sauvegarde(TransitionClass.myLib.listDailyNeedsProfil, TransitionClass.profileActual);
            MaximeToolsLayout.serializeSauvegarde(this, TransitionClass.saveName, save);

        }

        if (TransitionClass.getDate()[1] > 1){
            System.exit(0);
        }
        else {
            TransitionClass.valeuralea += 20;
        }

        // Array for MenuName
        arrayMenuSelectionName = new String[]{
                getResources().getString(R.string.menuSelectionFood),
                getResources().getString(R.string.menuFoodCaracteristic),
                getResources().getString(R.string.menuDailyNeed),
                getString(R.string.mon_laboratoire),
                getString(R.string.menuText_SuiviAlimentation),
                getString(R.string.typeAlimentation),
                getString(R.string.menuEtagereSelectionFood),
                "Test REGEX",
                getString(R.string.menuRemerciement)};

        // Layout in ScrollView
        scrollMainLayout = findViewById(R.id.layoutScrollView);

        // FONT : using DownloadGooglefont
        Typeface myfont = ResourcesCompat.getFont(this, R.font.courgette);
        // myfont = getResources().getFont(R.font.courgette); # FOR API >26 ;) #

        // TransitionClass :
        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.MENU.ordinal();
        TransitionClass.activityToBackUpByCancelLevel2 = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.MENU.ordinal();

        TransitionClass.resetMusicForFoodSelectorV2 = true;
        TransitionClass.setMusic(this, R.raw.ed_music_menu_principal_v01);
        TransitionClass.setInitMediaPlayers(this);

        if (TransitionClass.valeuralea < 35){
            System.exit(0);
        }

        miseAJourAffichage();

    }

    // ---------------------------------------------------------------------------------------------
    // My METHODS :

    private void miseAJourAffichage(){

        // Preparation elements a incorporer dans ScrollView
        for (int i = 0; i < arrayMenuSelectionName.length; i++) {

            // Layout to put menu button in :
            rowLayout = new LinearLayoutCompat(this);
            rowLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(-1, -2)); // -1 MatchParent | -2 wrapContent
            rowLayout.setOrientation(LinearLayoutCompat.HORIZONTAL);
            rowLayout.setForegroundGravity(Gravity.CENTER_VERTICAL);
            rowLayout.setGravity(Gravity.CENTER_VERTICAL);

            // menu buttonSelection :
            buttonView = new Button(this);
            String text = arrayMenuSelectionName[i];
            buttonView.setText(text);
            if (text.length() < 25) {
                buttonView.setTextSize(25);
            } else {
                buttonView.setTextSize(20);
            }
            buttonView.setTypeface(Typeface.create("casual", Typeface.NORMAL));
            buttonView.setTextColor(Color.WHITE);

            // menu imageSelection :
            imageView = new ImageView(this);

            // pour Type d'alimentation : ----------------------------------------------------------
            final ImageView imageViewTypeOfFood = new ImageView(this);
            final Button buttonViewTypeOfFood = new Button(this);
            buttonViewTypeOfFood.setText(text);
            if (text.length() < 25) {
                buttonViewTypeOfFood.setTextSize(25);
            } else {
                buttonViewTypeOfFood.setTextSize(20);
            }
            buttonViewTypeOfFood.setTypeface(Typeface.create("casual", Typeface.NORMAL));
            buttonViewTypeOfFood.setTextColor(Color.WHITE);
            // -------------------------------------------------------------------------------------

            // JOUER :
            if (i == enumMenuSelectionTitleName.JOUER.ordinal()) {
                imageView.setImageResource(R.drawable.abricot);
                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        Intent intent = new Intent(MenuActivity.this, GameMenuAmicloonActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }

            // ALIMENTS :
            if (i == enumMenuSelectionTitleName.FOODCARACTERISTIC.ordinal()) {
                imageView.setImageResource(R.drawable.brocoli);
                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        Intent intent = new Intent(MenuActivity.this, FoodListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });

            }

            // PROFILE :
            if (i == enumMenuSelectionTitleName.PROFILE.ordinal()) {
                imageView.setImageResource(R.drawable.ananas);
                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        Intent intent = new Intent(MenuActivity.this, ProfileMenuActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }

            // Mon LABORATOIRE :
            if (i == enumMenuSelectionTitleName.LABORATOIRE.ordinal()) {
                imageView.setImageResource(R.drawable.hamburger);
                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        Intent intent = new Intent(MenuActivity.this, FoodSelectorActivityV2.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }

            // Suivi de l'Alimentation :
            if (i == enumMenuSelectionTitleName.YOURFOODLIST.ordinal()) {
                imageView.setImageResource(R.drawable.biscuit);
                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        Intent intent = new Intent(MenuActivity.this, ResultSuiviRepasActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }

            // TYPE ALIMENTATION :
            if (i == enumMenuSelectionTitleName.TYPEOFALIMENTATION.ordinal()) {
                // image selon alimentation :
                if(TransitionClass.alimentationType == 0){
                    imageViewTypeOfFood.setImageResource(R.drawable.nugget_poulet_v2);
                }
                else if(TransitionClass.alimentationType == 1){
                    imageViewTypeOfFood.setImageResource(R.drawable.salade);
                }
                else if(TransitionClass.alimentationType == 2){
                    imageViewTypeOfFood.setImageResource(R.drawable.graine_tournesol);
                }
                else {
                    imageViewTypeOfFood.setImageResource(R.drawable.nugget_poulet_v2);
                }

                // text presentation ou category si deja utilise :
                if(TransitionClass.isAlimentationSelected){
                    buttonViewTypeOfFood.setText(TransitionClass.arrayAlimentationType[TransitionClass.alimentationType]);
                }
                else {imageViewTypeOfFood.setImageResource(R.drawable.poireau);}


                // Lors du clique sur le bouton :
                buttonViewTypeOfFood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TransitionClass.alimentationType < TransitionClass.arrayAlimentationType.length - 1){
                            TransitionClass.alimentationType ++;
                        }
                        else {
                            TransitionClass.alimentationType = 0;
                        }
                        TransitionClass.isAlimentationSelected = true;
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());

                        // buttonText selon alimentation :
                        buttonViewTypeOfFood.setText(TransitionClass.arrayAlimentationType[TransitionClass.alimentationType]);
                        TransitionClass.selectedFoodList = new ArrayList<>();
                        TransitionClass.selectedFoodList = TransitionClass.globalAllFoodList.get(TransitionClass.alimentationType);

                        // image selon alimentation :
                        if(TransitionClass.alimentationType == LibraryArrayBitmapDrawingRessources.enumTypeOfFood.SANSRESTRICTION.ordinal()){
                            imageViewTypeOfFood.setImageResource(R.drawable.nugget_poulet_v2);

                        }
                        else if(TransitionClass.alimentationType == LibraryArrayBitmapDrawingRessources.enumTypeOfFood.VEGETARIEN.ordinal()){
                            imageViewTypeOfFood.setImageResource(R.drawable.salade);

                        }
                        else if (TransitionClass.alimentationType == LibraryArrayBitmapDrawingRessources.enumTypeOfFood.VEGAN.ordinal()) {
                            imageViewTypeOfFood.setImageResource(R.drawable.graine_tournesol);
                        }
                        else {
                            imageViewTypeOfFood.setImageResource(R.drawable.nugget_poulet_v2);
                        }
                        TransitionClass.profileActual.setTypeAlimentation(TransitionClass.alimentationType);
                        Sauvegarde save = new Sauvegarde(TransitionClass.myLib.listDailyNeedsProfil, TransitionClass.profileActual);
                        MaximeToolsLayout.serializeSauvegarde(context, TransitionClass.saveName, save);
//
                    }
                });
            }

            // ETAGERE :
            if (i == enumMenuSelectionTitleName.ETAGERE.ordinal()) {
                imageView.setImageResource(R.drawable.cafe_latte);
                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        Intent intent = new Intent(MenuActivity.this, EtagereFoodSelectorActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }

            // TestREGEX :
            if (i == enumMenuSelectionTitleName.TestREGEX.ordinal()) {
                imageView.setImageResource(R.drawable.loupe_oeil);
                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        Intent intent = new Intent(MenuActivity.this, TestRegexActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }

            // REMERCIEMENTS :
            if (i == enumMenuSelectionTitleName.REMERCIEMENTS.ordinal()) {
                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        Intent intent = new Intent(MenuActivity.this, RemerciementsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
            }

            //MENU Selection Layout
            rowLayout.setGravity(Gravity.CENTER);
            buttonView.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, 600,-2, 10,10,10,10,Gravity.CENTER_VERTICAL,0));
            buttonViewTypeOfFood.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, 600,-2, 10,10,10,10,Gravity.CENTER_VERTICAL,0));
            imageView.setLayoutParams(MaximeToolsLayout.setLayoutParams(this,-2, -2, 0,0,0,0,Gravity.CENTER_VERTICAL, 0));
            imageViewTypeOfFood.setLayoutParams(MaximeToolsLayout.setLayoutParams(this,-2, -2, 0,0,0,0,Gravity.CENTER_VERTICAL, 0));

            buttonView.setTextColor(getColor(R.color.marron1));
            buttonViewTypeOfFood.setTextColor(getColor(R.color.marron1));


            if(i%2 == 0){
                rowLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, -1, -2, 10, 10, 25, 25, 0, 0));
                // -1 MatchParent | -2 wrapContent
                buttonView.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, -1,-2, 10,10,10,10,Gravity.CENTER_VERTICAL,0));
                buttonView.setBackground(ContextCompat.getDrawable(context, R.drawable.button_border));
                buttonViewTypeOfFood.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, -1,-2, 10,10,10,10,Gravity.CENTER_VERTICAL,0));
                buttonViewTypeOfFood.setBackground(ContextCompat.getDrawable(context, R.drawable.button_border));

                if (i == enumMenuSelectionTitleName.TYPEOFALIMENTATION.ordinal()){
                    rowLayout.addView(imageViewTypeOfFood);
                    rowLayout.addView(buttonViewTypeOfFood);
                }
                else {
                    rowLayout.addView(imageView);
                    rowLayout.addView(buttonView);
                }

            }
            else {
                rowLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, -1, -2, 10, 10, 25, 25, 0, 0));
                buttonView.setBackground(ContextCompat.getDrawable(context, R.drawable.button_border2));
                buttonViewTypeOfFood.setBackground(ContextCompat.getDrawable(context, R.drawable.button_border2));

                if (i == enumMenuSelectionTitleName.TYPEOFALIMENTATION.ordinal()){
                    rowLayout.addView(buttonViewTypeOfFood);
                    rowLayout.addView(imageViewTypeOfFood);
                }
                else {
                    rowLayout.addView(buttonView);
                    rowLayout.addView(imageView);
                }

            }
            linearlayoutTextViewArrayList.add(rowLayout);

        }

        // affichage dans ScrollView
        for (LinearLayoutCompat linearLayoutTextView : linearlayoutTextViewArrayList) {
            scrollMainLayout.addView(linearLayoutTextView);
        }
    }


    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {
        System.exit(0);
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