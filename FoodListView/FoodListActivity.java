package fr.maxime.mygame01.FoodListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import fr.maxime.mygame01.DailyNeedView.DailyNeedActivity;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources.enumGlobalComparatorAttributFoodClass;
import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScore;
import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScoreGlobalElements;

public class FoodListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private Context context;

    private Button bouttonOrganiseur, cancelButton;
    private Spinner spinner;
    private ScrollView scrollView;
    private LinearLayout linearLayoutScrollView, linearlayout, separatorLayout;
    private ImageView imageView;
    private TextView textView;

    private int index;

    private ArrayList<FoodClass> foodList;

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        context = this;
        foodList = TransitionClass.selectedFoodList;
        index = TransitionClass.indexSpinnerFoodList;

        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.FOODLIST.ordinal();
//        if(TransitionClass.resetMusicForFoodSelectorV2){
//            TransitionClass.setMusic(this, R.raw.ed_guitard_haut);
//            TransitionClass.resetMusicForFoodSelectorV2 = false;
//        }

        scrollView = findViewById(R.id.foodList_ScrollView);

        cancelButton = findViewById(R.id.foodList_cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCloseActivityAndResetValues();
            }
        });

        bouttonOrganiseur = findViewById(R.id.buttonOrganiseurFoodList);
        bouttonOrganiseur.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                TransitionClass.isReversedOrganiserFoodList = !TransitionClass.isReversedOrganiserFoodList;

                Collections.reverse(foodList);
                refreshlistViewInScrollView();
            }
        });

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TransitionClass.myLib.comparatorArrayName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // set la selection du spinner dans String[] de adapter (TransitionClass.myLib.comparatorArrayName)
        spinner.setSelection(index);

        spinner.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                return false;
            }
        });

        linearLayoutScrollView = findViewById(R.id.linearlayoutScrollViewCaracFood);
        refreshlistViewInScrollView();

        // creating food's displayView in ScrollViewLayout :

//        for (FoodClass food : foodList) {
//
//            linearlayout = new LinearLayout(this);
//            linearlayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            linearlayout.setOrientation(LinearLayout.HORIZONTAL);
//            imageView = new ImageView(this);
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            imageView.setImageResource(food.bitmapRawRessource);
//
//            final FoodClass food2 = food;
//            // On click ImageView :
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    TransitionClass.soundOnClick.seekTo(1);
//                    TransitionClass.soundOnClick.start();
//
//                    TransitionClass.stockageFood(food2);
//                    TransitionClass.indexSpinner = index;
//
//                    Intent intent = new Intent(context, FoodDescriptionV2.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    context.startActivity(intent);
//                }
//            });
//
//            textView = new TextView(this);
//            String text = food.name + "\n\t\t< " + food.categoryName + " >\n\t\t\t\t\t" + food.calories + " kCal / 100g";
//            textView.setText(text);
//
//            separatorLayout = new LinearLayout(this);
//            separatorLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
//
//            linearlayout.addView(imageView);
//            linearlayout.addView(textView);
//            linearLayoutScrollView.addView(linearlayout);
//            linearLayoutScrollView.addView(separatorLayout);
//        }
    }

    // ---------------------------------------------------------------------------------------------
    // Spinner Adapter View Methods :

    // /!\ SE DECLENCHE IMMEDIATEMENT AU LANCEMENT DE L'ACTIVITEE :

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        if(TransitionClass.isStartingFoodList){
            Collections.sort(foodList, LibraryArrayBitmapDrawingRessources.enumGlobalComparatorAttributFoodClass.comparator(index));

            if(TransitionClass.isReversedOrganiserFoodList){
                Collections.reverse(foodList);
            }

            if(TransitionClass.indexScrollViewFocusFoodList != 0){
                scrollView.scrollBy(0,TransitionClass.indexScrollViewFocusFoodList);
            }

            TransitionClass.isStartingFoodList = false;
        }
        else {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
            index = position;
            Collections.sort(foodList, LibraryArrayBitmapDrawingRessources.enumGlobalComparatorAttributFoodClass.comparator(index));
            TransitionClass.isReversedOrganiserFoodList = false;
        }
        refreshlistViewInScrollView();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshlistViewInScrollView() {
        if (index <= LibraryArrayBitmapDrawingRessources.enumGlobalComparatorAttributFoodClass.SEL.ordinal()) {
            // nom
            if (index == LibraryArrayBitmapDrawingRessources.enumGlobalComparatorAttributFoodClass.NAME.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.calories, "kCal / 100g");
                }
            }
            // categorie
            else if (index == enumGlobalComparatorAttributFoodClass.CATEGORY.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.calories, "kCal / 100g");
                }
            }
            // calories
            else if (index == enumGlobalComparatorAttributFoodClass.CALORIES.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.calories, "kCal / 100g");
                }
            }
            // proteine
            else if (index == enumGlobalComparatorAttributFoodClass.PROTEINE.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {

                    setTextElement(food, food.proteine, "g / 100g");
                }
            }
            // glucide
            else if (index == enumGlobalComparatorAttributFoodClass.GLUCIDE.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {

                    setTextElement(food, food.glucide, "g / 100g");
                }
            }
            // sucre
            else if (index == enumGlobalComparatorAttributFoodClass.SUCRE.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {

                    setTextElement(food, food.sucre, "g / 100g");
                }
            }
            // fibre
            else if (index == enumGlobalComparatorAttributFoodClass.FIBRE.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {

                    setTextElement(food, food.fibre, "g / 100g");
                }
            }
            // lipide
            else if (index == enumGlobalComparatorAttributFoodClass.LIPIDE.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {

                    setTextElement(food, food.lipide, "g / 100g");
                }
            }
            // acideGrasSature
            else if (index == enumGlobalComparatorAttributFoodClass.ACIDEGRASSATURES.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {

                    setTextElement(food, food.acideGrasSature, "g / 100g");
                }
            }
            // sel
            else if (index == enumGlobalComparatorAttributFoodClass.SEL.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {

                    setTextElement(food, food.sel, "g / 100g");
                }
            }


        } else if (index <= LibraryArrayBitmapDrawingRessources.enumGlobalComparatorAttributFoodClass.VITAMINE_E.ordinal()) {
            // VitamineA
            if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_A.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineA, "mg / 100g");

                }
            }
            // VitamineB1
            else if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_B1.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineB1, "mg / 100g");
                }
            }
            // VitamineB2
            else if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_B2.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineB2, "mg / 100g");
                }
            }
            // VitamineB3
            else if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_B3.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineB3, "mg / 100g");
                }
            }
            // VitamineB5
            else if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_B5.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineB5, "mg / 100g");
                }
            }
            // VitamineB6
            else if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_B6.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineB6, "mg / 100g");
                }
            }
            // VitamineB9
            else if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_B9.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineB9, "mg / 100g");
                }
            }
            // VitamineB12
            else if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_B12.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineB12, "μg / 100g");
                }
            }
            // VitamineC
            else if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_C.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineC, "mg / 100g");
                }
            }
            // VitamineD
            else if (index == enumGlobalComparatorAttributFoodClass.VITAMINE_D.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineD, "μg / 100g");

                }
            }
            // VitamineE
            else {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.vitamineE, "mg / 100g");
                }
            }
        } else {

            // eau
            if (index == enumGlobalComparatorAttributFoodClass.EAU.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    String text = "% " + this.getResources().getString(R.string.text_d_eau);
                    setTextElement(food, food.eau, text);
                }
            }
            // Calcium
            else if (index == enumGlobalComparatorAttributFoodClass.CALCIUM.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.calcium, "mg / 100g");
                }
            }
            // Cuivre
            else if (index == enumGlobalComparatorAttributFoodClass.CUIVRE.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.cuivre, "mg / 100g");
                }
            }
            // Fer
            else if (index == enumGlobalComparatorAttributFoodClass.FER.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.fer, "mg / 100g");
                }
            }
            // Iode
            else if (index == enumGlobalComparatorAttributFoodClass.IODE.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.iode, "μg / 100g");
                }
            }
            // Magnesium
            else if (index == enumGlobalComparatorAttributFoodClass.MAGNESIUM.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.magnesium, "mg / 100g");
                }
            }
            // Manganese
            else if (index == enumGlobalComparatorAttributFoodClass.MANGANESE.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.manganese, "mg / 100g");
                }
            }
            // Phosphore
            else if (index == enumGlobalComparatorAttributFoodClass.PHOSPHORE.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.phosphore, "mg / 100g");
                }
            }
            // Potassium
            else if (index == enumGlobalComparatorAttributFoodClass.POTASSIUM.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.potassium, "g / 100g");
                }
            }
            // Selenium
            else if (index == enumGlobalComparatorAttributFoodClass.SELENIUM.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.selenium, "μg / 100g");
                }
            }
            // Sodium
            else if (index == enumGlobalComparatorAttributFoodClass.SODIUM.ordinal()) {
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.sodium, "g / 100g");
                }
            }
            // Zinc
            else if (index == enumGlobalComparatorAttributFoodClass.ZINC.ordinal()){
                linearLayoutScrollView.removeAllViews();
                for (FoodClass food : foodList) {
                    setTextElement(food, food.zinc, "mg / 100g");
                }
            }
        }

    }

    private void setTextElement(FoodClass food, double element, String unitee) {

        linearlayout = new LinearLayout(this);
        linearlayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                (context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                        20,20,0,0, 0,0));
        linearlayout.setOrientation(LinearLayout.HORIZONTAL);
        linearlayout.setPadding(0,20,160,20);
        linearlayout.setBackgroundResource(R.drawable.button_border_dark_elements);

        imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(food.bitmapRawRessource);

        final FoodClass food2 = food;
        // On click ImageView :
        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());

                TransitionClass.stockageFood(food2);
                TransitionClass.indexSpinnerFoodList = index;
                TransitionClass.indexScrollViewFocusFoodList = scrollView.getScrollY();

                Intent intent = new Intent(context, FoodDescriptionV2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });

        textView = new TextView(this);

        String text = food.name + "\n\t\t< " + food.categoryName + " >\n\t\t\t\t\t" + arrondir3decimales(element) + " " + unitee;
        textView.setText(text);

        separatorLayout = new LinearLayout(this);
        separatorLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50));

        linearlayout.addView(imageView);
        linearlayout.addView(textView);
        linearLayoutScrollView.addView(linearlayout);
        linearLayoutScrollView.addView(separatorLayout);

    }

    public Double arrondir3decimales(Double nombre) {
        return (double) Math.round(nombre * 1000) / 1000;
    }

    public void onCloseActivityAndResetValues(){

        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        TransitionClass.isStartingFoodList = true;
        TransitionClass.isReversedOrganiserFoodList = false;
        TransitionClass.indexSpinnerFoodList = 0;
        TransitionClass.indexScrollViewFocusFoodList = 0;

        if (TransitionClass.activityToBackUpByCancelLevel2 == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.RESULTFOODSELECTORV2NUTRISCOREGLOBALELEMENTS.ordinal()){
            TransitionClass.isStartingFoodList = true;
            Intent intent = new Intent(getApplicationContext(), ResultFoodSelectorV2ActivityNutriScoreGlobalElements.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if (TransitionClass.activityToBackUpByCancelLevel2 == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.RESULTFOODSELECTORV2NUTRISCORE.ordinal()){
            TransitionClass.isStartingFoodList = true;
            Intent intent = new Intent(getApplicationContext(), ResultFoodSelectorV2ActivityNutriScore.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if (TransitionClass.activityToBackUpByCancelLevel2 == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.DAILYNEEDACTIVITY.ordinal()){
            TransitionClass.isStartingFoodList = true;
            Intent intent = new Intent(getApplicationContext(), DailyNeedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {

        onCloseActivityAndResetValues();
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