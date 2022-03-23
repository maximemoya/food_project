package fr.maxime.mygame01.ResultView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.maxime.mygame01.DailyNeedView.DailyNeedActivity;
import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.FoodClassSerializable;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.Sauvegarde;
import fr.maxime.mygame01.MyObjects.SuiviAlimentaireClass;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

import static fr.maxime.mygame01.MyObjects.TransitionClass.myLib;

public class ResultSuiviRepasActivity extends AppCompatActivity {

    private Context context;
    private TextView profilTextView;
    private Button buttonCancel;
    private LinearLayout linearLayoutScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_suivi_repas);

        context = this;
        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.RESULTSUIVIREPAS.ordinal();
        TransitionClass.setMusic(context, R.raw.ed_guitard_total);

        profilTextView = findViewById(R.id.resultSuiviRepas_textProfilName);
        buttonCancel = findViewById(R.id.resultSuiviRepas_btnCancel);
        linearLayoutScrollView = findViewById(R.id.resultSuiviRepas_LinearLayoutinScrollView);
        linearLayoutScrollView.removeAllViews();

        // On Click buttonCANCEL :
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClick();
            }
        });

        if (TransitionClass.profileActual != null) {

            profilTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());

                    Intent intent = new Intent(getApplicationContext(), DailyNeedActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });

            profilTextView.setText(TransitionClass.profileActual.getPseudo());


            // AFFICHAGE dans la scrollView des listes aliments manges du profilActual :
            TransitionClass.profileActual.getCheckListSuiviAlimentaire();

            for(SuiviAlimentaireClass suiviAliments : TransitionClass.profileActual.getArrayListSuiviAlimentaireClass()){

                final SuiviAlimentaireClass suiviAlimentaireFinal = suiviAliments;

                LinearLayout mainlayout = new LinearLayout(context);
                mainlayout.setOrientation(LinearLayout.VERTICAL);
                mainlayout.setBackgroundResource(R.drawable.button_border_orangelight);
                mainlayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0,0,10,0,0,0));

                // LAYOUT DATE :
                LinearLayout dateLayout = new LinearLayout(context);
                dateLayout.setOrientation(LinearLayout.HORIZONTAL);
                dateLayout.setGravity(Gravity.CENTER_VERTICAL);
                dateLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                5,5,5,5,0,0));

                // Font perso :
                Typeface myfont = ResourcesCompat.getFont(this, R.font.courgette);

                // TEXT DATE :
                TextView dateText = new TextView(context);
                dateText.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0,0,0,0,0,0));
                dateText.setTextColor(Color.WHITE);
                dateText.setTypeface(Typeface.create("casual", Typeface.BOLD));
                dateText.setText(TransitionClass.getDateText(context, suiviAliments.getIntArrayDate()));

                // IMAGE DAYMOMENT :
                ImageView imageDayMomentView = new ImageView(context);
                imageDayMomentView.setImageResource(TransitionClass.myLib.intIMGDayMomentArray[suiviAliments.getTimeDayMoment()]);
                imageDayMomentView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (context, 80, 80, 10,10,0,0,0,0));

                // TEXT DATE :
                TextView dayMomentText = new TextView(context);
                dayMomentText.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0,0,0,0,0,0));
                dayMomentText.setTextColor(Color.WHITE);
                dayMomentText.setTypeface(Typeface.create("casual", Typeface.BOLD));
                dayMomentText.setText(suiviAliments.getDayMomentText());

                if(suiviAliments.getDayMomentText().length() > 5){
                    dayMomentText.setTextSize(20);
                    dateText.setTextSize(20);
                }
                else {
                    dayMomentText.setTextSize(24);
                    dateText.setTextSize(24);
                }

                // NUTRISCORE Letter :
                TextView nutriScoreTextView = new TextView(context);
                nutriScoreTextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                10,0,0,0,0,0));

                TransitionClass.getNutriScoreV2ABC_foodclassalone(context, suiviAliments.getArrayListFoodEaten(), nutriScoreTextView, suiviAliments.getTimeDayMoment());
                nutriScoreTextView.setTypeface(Typeface.create("casual", Typeface.BOLD));
                nutriScoreTextView.setTextSize(24);

                dateLayout.addView(dateText);
                dateLayout.addView(imageDayMomentView);
                dateLayout.addView(dayMomentText);
                dateLayout.addView(nutriScoreTextView);

                // ScrollView Horizontal :
                HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);

                // LAYOUT IMG foodList :
                LinearLayout layoutIMGFoodlist = new LinearLayout(context);
                layoutIMGFoodlist.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (context, LinearLayout.LayoutParams.WRAP_CONTENT, 240,
                        0,0,0,0,0,0));
                layoutIMGFoodlist.setOrientation(LinearLayout.HORIZONTAL);

                // on layoutFoodList CLICK :
                layoutIMGFoodlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLayoutFoodListClick(suiviAlimentaireFinal);
                    }
                });

                for(FoodClass food : suiviAliments.getArrayListFoodEaten()){

                    // IMG food :
                    LinearLayout layoutPerImg = new LinearLayout(context);
                    layoutPerImg.setLayoutParams(MaximeToolsLayout.setLayoutParams
                            (context, 150, 240,
                            10,10,0,0,0,0));
                    layoutPerImg.setOrientation(LinearLayout.VERTICAL);

                    ImageView foodImageView = new ImageView(context);
                    foodImageView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                            (context, 150, 180,
                            0,0,0,0,0,0));
                    foodImageView.setImageResource(food.bitmapRawRessource);

                    // Quantity Text food :
                    TextView quantityTextView = new TextView(context);
                    quantityTextView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                            (context, 200, 50,
                            0,0,0,0,0,0));
                    quantityTextView.setTextSize(14);
                    quantityTextView.setTypeface(Typeface.create("monospace", Typeface.BOLD));

                    // set quantity text color :
                    if(food.quantityID == FoodSelectorActivityV2.enumQuantityChosenAmount.SMALL.geti()){
                        quantityTextView.setTextColor(context.getResources().getColor(R.color.quantitySmall));
                    }
                    else if(food.quantityID == FoodSelectorActivityV2.enumQuantityChosenAmount.MEDIUM.geti()){
                        quantityTextView.setTextColor(context.getResources().getColor(R.color.quantityMedium));
                    }
                    else if(food.quantityID == FoodSelectorActivityV2.enumQuantityChosenAmount.BIG.geti()){
                        quantityTextView.setTextColor(context.getResources().getColor(R.color.quantityBig));
                    }
                    else {
                        quantityTextView.setTextColor(context.getResources().getColor(R.color.quantitySpecific));
                    }

                    String text = food.getQuantityString(context);
                    quantityTextView.setText(text);

                    layoutPerImg.addView(foodImageView);
                    layoutPerImg.addView(quantityTextView);
                    layoutIMGFoodlist.addView(layoutPerImg);
                }

                horizontalScrollView.addView(layoutIMGFoodlist);

                mainlayout.addView(dateLayout);
                mainlayout.addView(horizontalScrollView);

                linearLayoutScrollView.addView(mainlayout);
            }

        }

        // serialize :
        Sauvegarde save = new Sauvegarde(myLib.listDailyNeedsProfil, TransitionClass.profileActual);
        MaximeToolsLayout.serializeSauvegarde(context,TransitionClass.saveName, save);

    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private void onCancelClick(){
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void onLayoutFoodListClick(SuiviAlimentaireClass suiviAlimentaireFinal){
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
        TransitionClass.setSuiviAlimentaireClassArrayListStatic(suiviAlimentaireFinal);

        Intent intent = new Intent(getApplicationContext(), ResultModifyFoodInSuiviRepas.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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