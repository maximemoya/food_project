package fr.maxime.mygame01.ResultView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import fr.maxime.mygame01.FoodListView.FoodListActivity;
import fr.maxime.mygame01.MyObjects.ProfileDailyNeedsClass;
import fr.maxime.mygame01.FoodListView.FoodDescriptionV2;
import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

public class ResultFoodSelectorV2ActivityNutriScore extends AppCompatActivity {

    private Context context;
    private TextView nutriScoreTextView, dayTimeTextView, besoinActuelTextView;
    private LinearLayout linearLayoutHorizontalScroll, linearLayoutVerticalScroll;
    private Button cancelButton;

    private ProfileDailyNeedsClass profileUser;
    private ArrayList<FoodClass> foodList = new ArrayList<>();
    private Double[] arrayTotalFoodValues;
    private String nutriScoreCharacter = "";
    private int nutriScoreColor = 0;

    private Typeface myfont;
    //private boolean besoinJournalierPercent = false;
    private boolean besoinActualPercent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_nutri_score_food_selector_v2);

        context = this;

        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.RESULTFOODSELECTORV2NUTRISCORE.ordinal();
        TransitionClass.activityToBackUpByCancelLevel2 = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.RESULTFOODSELECTORV2NUTRISCORE.ordinal();

        if (TransitionClass.resetMusicForFoodSelectorV2) {
            TransitionClass.setMusic(context, R.raw.ed_guitard_total_long);
            TransitionClass.resetMusicForFoodSelectorV2 = false;
        }

        cancelButton = findViewById(R.id.resultNutriScorefoodSelectorV2_cancelButton);
        nutriScoreTextView = findViewById(R.id.resultNutriScorefoodSelectorV2_nutriScoreTextView);
        dayTimeTextView = findViewById(R.id.resultNutriScorefoodSelectorV2_TextViewTimeofTheDay);
        besoinActuelTextView = findViewById(R.id.resultNutriScorefoodSelectorV2_BesoinJournalierTextView);
        linearLayoutHorizontalScroll = findViewById(R.id.resultNutriScorefoodSelectorV2_linearLayoutHorizontalScrollView);
        linearLayoutVerticalScroll = findViewById(R.id.resultNutriScorefoodSelectorV2_linearLayoutVerticalScrollView);

        profileUser = TransitionClass.profileActual;
        foodList = TransitionClass.foodOnDropZoneList;
        arrayTotalFoodValues = TransitionClass.arrayTotalFoodValues;
        nutriScoreCharacter = TransitionClass.nutriScoreCharacter;
        nutriScoreColor = TransitionClass.nutriScoreColor;

        // CancelButton :
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClick();
            }
        });

        // DayTime :
        dayTimeTextView.setText(TransitionClass.getDayMomentText());

        // NutriScore :
        nutriScoreTextView.setText(nutriScoreCharacter);
        nutriScoreTextView.setTextColor(this.getResources().getColor(nutriScoreColor));

        // BesoinJournalier :
//        besoinJournalierTextView.setBackgroundResource(R.drawable.button_border_dark_elements);
//        besoinJournalierTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TransitionClass.soundOnClick.seekTo(1);
//                TransitionClass.soundOnClick.start();
//                if (besoinJournalierPercent) {
//                    besoinJournalierPercent = false;
//                    besoinJournalierTextView.setText(context.getResources().getText(R.string.daily_need));
//                } else {
//                    besoinJournalierPercent = true;
//                    besoinJournalierTextView.setText(context.getResources().getText(R.string.daily_need_percent));
//                }
//                affichageVerticalValuesInScrollView();
//            }
//        });

        // Besoin ACTUEL :
        besoinActuelTextView.setBackgroundResource(R.drawable.button_border_dark_elements);
        besoinActuelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                besoinActualPercent = !besoinActualPercent;
                affichageVerticalValuesInScrollView();
            }
        });

        // Horizontal ScrollView Layout :
        affichageHorizontalFoodList();

        // creation Font pour afficher les DoubleValues :
        myfont = ResourcesCompat.getFont(this, R.font.courgette);

        // Vertical ScrollView Layout :
        affichageVerticalValuesInScrollView();

    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private void affichageHorizontalFoodList(){
        linearLayoutHorizontalScroll.removeAllViews();
        for (FoodClass food : foodList) {

            final FoodClass food2 = food;
            final LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (this, 150, LinearLayout.LayoutParams.MATCH_PARENT,
                            10, 10, 0, 0,  Gravity.CENTER, 0));
            linearLayout.setOrientation(LinearLayout.VERTICAL);


            // Click sur aliment dans la liste :
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransitionClass.soundOnClick.seekTo(1);
                    TransitionClass.soundOnClick.start();

                    TransitionClass.stockageFood(food2);

                    Intent intent = new Intent(context, FoodDescriptionV2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
            });

            // IMAGE FOOD :
            ImageView imageView0 = new ImageView(this);
            imageView0.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (this, LinearLayout.LayoutParams.MATCH_PARENT, 0,
                            0, 0, 0, 0,  Gravity.CENTER, 3));
            imageView0.setBackgroundResource(food.bitmapRawRessource);

            // TEXT FOOD QUANTITY :
            TextView textView0 = new TextView(this);
            textView0.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (this, LinearLayout.LayoutParams.MATCH_PARENT, 0,
                            0, 0, 0, 0, Gravity.CENTER, 1));
            textView0.setTextSize(14);
            textView0.setTypeface(Typeface.create("monospace", Typeface.BOLD));
            textView0.setGravity(Gravity.CENTER);
            if (food.quantityID == FoodSelectorActivityV2.enumQuantityChosenAmount.SMALL.geti()) {
                textView0.setTextColor(context.getColor(R.color.quantitySmall));
            } else if (food.quantityID == FoodSelectorActivityV2.enumQuantityChosenAmount.MEDIUM.geti()) {
                textView0.setTextColor(context.getColor(R.color.quantityMedium));
            } else if (food.quantityID == FoodSelectorActivityV2.enumQuantityChosenAmount.SPECIFIC.geti()) {
                textView0.setTextColor(context.getColor(R.color.quantitySpecific));
            } else {
                textView0.setTextColor(context.getColor(R.color.quantityBig));
            }

            String text = food.getQuantityString(context);
            textView0.setText(text);

            linearLayout.addView(imageView0);
            linearLayout.addView(textView0);
            linearLayoutHorizontalScroll.addView(linearLayout);
        }
    }

    private void affichageVerticalValuesInScrollView() {
        linearLayoutVerticalScroll.removeAllViews();
        int[] colorArrayForElementsText = TransitionClass.getColorElementsCompareActualNeedToArrayDoubleValueElements(context, arrayTotalFoodValues);
        //           ArrayList<Integer> arrayListColorText = TransitionClass.getColorInstantaneeFoodTextViewComparingAllElements(arrayTotalFoodValues, profileUser);

        for (int i = 0; i < 8; i++) {

            if (i == 5) {
                i++;
            }

            // set text Elements name (ex: Calories :) :
            final TextView textView1 = new TextView(this);
            textView1.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, 0, LinearLayout.LayoutParams.WRAP_CONTENT, 5, 0, 0, 0, Gravity.CENTER, 3));
            textView1.setTextSize(18);
            textView1.setTypeface(Typeface.create("", Typeface.NORMAL));
            textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView1.setPadding(0,10,0,10);
            textView1.setBackgroundResource(R.drawable.button_border_dark_elements);

            String text = TransitionClass.myLib.comparatorArrayName[i + 2];
            textView1.setText(text);

            textView1.setTextColor(colorArrayForElementsText[i]);

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

            // set text Value of elements (ex : 320 kcal) :
            TextView textView2 = new TextView(this);
            textView2.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, 0, LinearLayout.LayoutParams.WRAP_CONTENT, 0, 0, 0, 0, Gravity.CENTER, 3));
            textView2.setTextSize(18);
            textView2.setTypeface(myfont);
            textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            if (i == 0) {
                text = arrayTotalFoodValues[i] + " kCal";
            } else {
                text = arrayTotalFoodValues[i] + " g";
            }
            textView2.setText(text);

            textView2.setTextColor(colorArrayForElementsText[i]);

            // set image de l'aliment qui contient le plus de l'element cible :
            ImageView imageView1 = new ImageView(this);
            imageView1.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, 0, 100, 5, 5, 5, 5, Gravity.CENTER, (float) 0.8));
            ArrayList<Integer> rawIntImage = getImageFoodByComparing(foodList);
            imageView1.setImageResource(rawIntImage.get(i));

            // set text ActualNeed User Values (ex: 2000 kCal) :
            TextView textView3 = new TextView(this);
            textView3.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, 0, LinearLayout.LayoutParams.WRAP_CONTENT, 0, 0, 0, 0, Gravity.CENTER, 3));
            textView3.setTextSize(20);
            textView3.setTypeface(myfont);
            textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            if (besoinActualPercent) {
                besoinActuelTextView.setText(context.getResources().getText(R.string.actual_need_percent));
                text = TransitionClass.getArrayStringByElementsForActualNeedPercent(arrayTotalFoodValues)[i];
            } else {
                besoinActuelTextView.setText(context.getResources().getText(R.string.actual_need));
                text = TransitionClass.getArrayStringByElementsForActualNeedWithInferiorSign(arrayTotalFoodValues)[i];

            }
            textView3.setText(text);

            textView3.setTextColor(colorArrayForElementsText[i]);

            // creation du LinearLayout :
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams(this, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0, 0, 10, 0, Gravity.CENTER_VERTICAL, 0));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            linearLayout.addView(textView1);
            linearLayout.addView(textView2);
            linearLayout.addView(imageView1);
            linearLayout.addView(textView3);
            linearLayoutVerticalScroll.addView(linearLayout);
        }
    }

    private ArrayList<Integer> getImageFoodByComparing(ArrayList<FoodClass> arrayListFood) {

        ArrayList<Integer> intImgRewsultList = new ArrayList<>();

        Collections.sort(arrayListFood, FoodClass.comparatorCaloriesMultiplyByQuantity);
        intImgRewsultList.add(foodList.get(0).bitmapRawRessource);
        Collections.sort(arrayListFood, FoodClass.comparatorLipideMultiplyByQuantity);
        intImgRewsultList.add(foodList.get(0).bitmapRawRessource);
        Collections.sort(arrayListFood, FoodClass.comparatorAcideGrasSatureMultiplyByQuantity);
        intImgRewsultList.add(foodList.get(0).bitmapRawRessource);
        Collections.sort(arrayListFood, FoodClass.comparatorGlucideMultiplyByQuantity);
        intImgRewsultList.add(foodList.get(0).bitmapRawRessource);
        Collections.sort(arrayListFood, FoodClass.comparatorSucreMultiplyByQuantity);
        intImgRewsultList.add(foodList.get(0).bitmapRawRessource);

        // squeeze element Fibres :
        intImgRewsultList.add(foodList.get(0).bitmapRawRessource);

        Collections.sort(arrayListFood, FoodClass.comparatorProteineMultiplyByQuantity);
        intImgRewsultList.add(foodList.get(0).bitmapRawRessource);
        Collections.sort(arrayListFood, FoodClass.comparatorSelMultiplyByQuantity);
        intImgRewsultList.add(foodList.get(0).bitmapRawRessource);

        return intImgRewsultList;
    }


    private void onCancelClick(){
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        Intent intent = new Intent(getApplicationContext(), FoodSelectorActivityV2.class);
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