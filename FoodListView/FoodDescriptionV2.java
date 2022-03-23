package fr.maxime.mygame01.FoodListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.GameViewActivity;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.ProfileDailyNeedsClass;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScore;
import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScoreGlobalElements;
import fr.maxime.mygame01.game.GameV2Activity;
import fr.maxime.mygame01.myIdeaBox.TestRegexActivity;

public class FoodDescriptionV2 extends AppCompatActivity {

    private Context context;

    FoodClass food;
    private LinearLayout linearLayoutScrollView;
    private TextView textElement, textViewQuantitePour100g, textViewQuantiteJournaliere;
    private Button cancelButton;

    private double valueQuantityFoodPerElement;
    private boolean isDailyNeedPercent = false;
    private int valueIndexQuantityFoodByElement = LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UN100gr.ordinal();

    private String[] descriptionFoodArrayElements;
    private String[] arrayStringTitleQuantityFood;

    private ProfileDailyNeedsClass profil = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_description_v2);

        context = this;
        descriptionFoodArrayElements = LibraryArrayBitmapDrawingRessources.getdescriptionFoodArrayElements(context);

        TransitionClass.isStartingFoodList = true;

        if(TransitionClass.profileActual != null){
            profil = TransitionClass.profileActual;
        }
        food = TransitionClass.foodstatic;

        // CancelButton :
        cancelButton = findViewById(R.id.descriptionFood_cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClick();
            }
        });

        // TITRES STATIQUES :
        ImageView imagefood = findViewById(R.id.descriptionFoodImageViewFoodBitmap);
        imagefood.setImageResource(food.bitmapRawRessource);

        TextView foodNameTextView = findViewById(R.id.descriptionFoodName);
        foodNameTextView.setText(food.name);

        TextView foodCategoryTextView = findViewById(R.id.descriptionFoodTextInputCategoryName);
        foodCategoryTextView.setText(food.categoryName);

        // TITRE TEXTE QUANTITE DE L'ALIMENT :
        textViewQuantitePour100g = findViewById(R.id.descriptionFoodQuantiteText);
        textViewQuantitePour100g.setBackgroundResource(R.drawable.button_border_dark_elements);
        textViewQuantitePour100g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                valueIndexQuantityFoodByElement = food.getValueIndexQuantityFoodByElementOnClick(context, valueIndexQuantityFoodByElement);

                // FINAL :
                getAffichageFoodDescriptionSelonElements();

            }
        });

        // TITRE TEXT QUANTITE ALIMENT : 100g en 100mL selon LIQUIDE OU SOLIDE :
        arrayStringTitleQuantityFood = food.getArrayTextTitleFoodDescriptionV2Quantity(context);

        // TITRE TEXTE BESOIN DE L'UTILISATEUR en % :
        textViewQuantiteJournaliere = findViewById(R.id.descriptionFoodBesoinJournalierText);
        textViewQuantiteJournaliere.setBackgroundResource(R.drawable.button_border_dark_elements);
        textViewQuantiteJournaliere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                isDailyNeedPercent = !isDailyNeedPercent;
                getAffichageFoodDescriptionSelonElements();
            }
        });

        textElement = findViewById(R.id.maxDescriptionTextElement);

        linearLayoutScrollView = findViewById(R.id.descriptionFoodLinearLayoutScroolView);
        linearLayoutScrollView.removeAllViews();

        getAffichageFoodDescriptionSelonElements();
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    // TO CONVERT DP IN PIXELS :
    private int convertDpToPixel(int yourDpMeasure) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                yourDpMeasure,
                r.getDisplayMetrics()
        );
    }

    private Double arrondir3decimales(Double nombre) {
        return (double) Math.round(nombre * 1000) / 1000;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getAffichageFoodDescriptionSelonElements() {

        linearLayoutScrollView.removeAllViews();

        for (int i = 0; i < descriptionFoodArrayElements.length; i++) {

            // LAYOUT LIGNE POUR CHAQUE ELEMENT, DANS SCROLLVIEW :
            LinearLayout layout = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, convertDpToPixel(20), 0, convertDpToPixel(20));
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setForegroundGravity(Gravity.CENTER_VERTICAL);
            layout.setGravity(Gravity.CENTER_VERTICAL);

            // TEXTE ELEMENT (ex : Calories :) :
            TextView textElement = new TextView(this);
            params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = (float) 1.0;
            params.leftMargin = convertDpToPixel(5);
            textElement.setLayoutParams(params);
            textElement.setGravity(Gravity.CENTER);
            textElement.setTypeface(Typeface.create("sans-serif-condensed", Typeface.ITALIC));
            textElement.setTextSize(22);
            // FONT : using DownloadGooglefont :
            // Typeface myfont = ResourcesCompat.getFont(this, R.font.courgette);
            // myfont = getResources().getFont(R.font.courgette); # FOR API >26 ;) #

            // TEXTE QUANTITE PAR ELEMENT PAR ALIMENT :
            TextView textQuantitePour100g = new TextView(this);
            params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = (float) 1.0;
            params.leftMargin = convertDpToPixel(10);
            params.rightMargin = convertDpToPixel(10);
            textQuantitePour100g.setLayoutParams(params);
            textQuantitePour100g.setGravity(Gravity.CENTER);
            textQuantitePour100g.setTypeface(Typeface.create("sans-serif-condensed", Typeface.ITALIC));
            textQuantitePour100g.setTextSize(22);

            // TEXTE QUANTITE JOURNALIERE SELON UTILISATEUR :
            TextView textQuantiteJournaliere = new TextView(this);
            params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = (float) 1.0;
            params.rightMargin = convertDpToPixel(5);
            textQuantiteJournaliere.setLayoutParams(params);
            textQuantiteJournaliere.setGravity(Gravity.CENTER);
            textQuantiteJournaliere.setTypeface(Typeface.create("sans-serif-condensed", Typeface.ITALIC));
            textQuantiteJournaliere.setTextSize(22);

            // DEFINITION LES COULEURS D'AFFICHAGE DES TEXTES FOODQUANTITY ET DAILYNEED
            // nombre pair (si == 1 nombre impaire) :
            if (i % 2 == 0) {
                textQuantiteJournaliere.setTextColor(context.getResources().getColor(R.color.maxDescriptionQuantiteJournaliereVertTresClair));
            } else {
                textQuantiteJournaliere.setTextColor(context.getResources().getColor(R.color.maxDescriptionQuantiteJournaliereVertClair));
            }
            textQuantitePour100g.setTextColor(context.getResources().getColor(R.color.maxDescriptionQuantiteBrun));

            // Recoloriage des lignes selon Le pourcentage des besoins quotidien combles :
            if(profil != null){
                double foodQuantity = food.arrayValuesFoodPer100g[i]*LibraryArrayBitmapDrawingRessources.arrayMultipliarQuantityAmount[valueIndexQuantityFoodByElement];
                if( foodQuantity >= profil.getDailyNeedDOUBLEValuesList().get(i)){
                    textElement.setTextColor(context.getResources().getColor(R.color.nutriscore_rouge));
                    textQuantitePour100g.setTextColor(context.getResources().getColor(R.color.nutriscore_rouge));
                    textQuantiteJournaliere.setTextColor(context.getResources().getColor(R.color.nutriscore_rouge));
                }
                else if(profil.getDailyNeedDOUBLEValuesList().get(i) - foodQuantity <= profil.getDailyNeedDOUBLEValuesList().get(i)/2){
                    textElement.setTextColor(context.getResources().getColor(R.color.nutriscore_orange));
                    textQuantitePour100g.setTextColor(context.getResources().getColor(R.color.nutriscore_orange));
                    textQuantiteJournaliere.setTextColor(context.getResources().getColor(R.color.nutriscore_orange));
                }
            }

            // DEFINITION TEXTE DES ELEMENTS :
            textElement.setText(descriptionFoodArrayElements[i]);

            // DEFINITION TEXTE TITRE QUANTITY FOOD PER ELEMENT :
            textViewQuantitePour100g.setText(arrayStringTitleQuantityFood[valueIndexQuantityFoodByElement]);
            // VALEUR DECIMALE QUANTITE ELEMENT PAR ALIMENT :
            textQuantitePour100g.setText(food.getTextQuantityAccordingPortionAndElement(valueIndexQuantityFoodByElement, i));

            // DEFINITION TEXTE TITRE DAILYNEED :
            textViewQuantiteJournaliere.setText(getTITLEtextDailyNeed(isDailyNeedPercent));
            // VALEUR DECIMALE OU POURCENT DES BESOINS JOURNALIERS PAR ELEMENTS :
            textQuantiteJournaliere.setText(food.getTextDailyNeed(isDailyNeedPercent, valueIndexQuantityFoodByElement, i));

            layout.addView(textElement);
            layout.addView(textQuantitePour100g);
            layout.addView(textQuantiteJournaliere);
            linearLayoutScrollView.addView(layout);
        }
    }

    private String getTITLEtextDailyNeed(boolean isDailyNeedPercent){
        if(isDailyNeedPercent){
            return context.getResources().getString(R.string.daily_need_percent);
        }
        else {
            return context.getResources().getString(R.string.daily_need);
        }
    }

    private void onCancelClick(){
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());

        if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.GAME.ordinal()){
            Intent intent = new Intent(getApplicationContext(), GameV2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.FOODSELECTORV2.ordinal()){
            Intent intent = new Intent(getApplicationContext(), FoodSelectorActivityV2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.RESULTFOODSELECTORV2NUTRISCORE.ordinal()){
            Intent intent = new Intent(getApplicationContext(), ResultFoodSelectorV2ActivityNutriScore.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.RESULTFOODSELECTORV2NUTRISCOREGLOBALELEMENTS.ordinal()){
            Intent intent = new Intent(getApplicationContext(), ResultFoodSelectorV2ActivityNutriScoreGlobalElements.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.FOODLIST.ordinal()){
            Intent intent = new Intent(getApplicationContext(), FoodListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.TESTREGEX.ordinal()){
            Intent intent = new Intent(getApplicationContext(), TestRegexActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
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