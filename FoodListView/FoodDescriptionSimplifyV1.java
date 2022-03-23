package fr.maxime.mygame01.FoodListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.ProfileDailyNeedsClass;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;
import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScore;
import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScoreGlobalElements;
import fr.maxime.mygame01.game.GameV2Activity;
import fr.maxime.mygame01.myIdeaBox.TestRegexActivity;

public class FoodDescriptionSimplifyV1 extends AppCompatActivity {

    private Context context;

    FoodClass food;
    private LinearLayout linearLayoutScrollView;
    private Button cancelButton;

    private int valueIndexQuantityFoodByElement = LibraryArrayBitmapDrawingRessources.enumValueIndexQuantityFoodByElement.UN100gr.ordinal();

    private String[] descriptionFoodArrayElements;

    private ProfileDailyNeedsClass profil = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_description_simplify_v1);

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

        linearLayoutScrollView = findViewById(R.id.descriptionFoodLinearLayoutScroolView);
        linearLayoutScrollView.removeAllViews();

        getAffichageFoodDescriptionSelonElements();
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getAffichageFoodDescriptionSelonElements() {

        linearLayoutScrollView.removeAllViews();

        for (int i = 0; i < descriptionFoodArrayElements.length; i++) {

            // LAYOUT LIGNE POUR CHAQUE ELEMENT, DANS SCROLLVIEW :
            LinearLayout layout = new LinearLayout(context);
            layout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    10,10,20,20,Gravity.CENTER,0));
            layout.setOrientation(LinearLayout.HORIZONTAL);

            // TEXTE ELEMENT (ex : Calories :) :
            TextView textElement = new TextView(this);
            textElement.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, 300, ViewGroup.LayoutParams.WRAP_CONTENT,
                            0,0,0,0,Gravity.CENTER,1));
            textElement.setGravity(Gravity.CENTER);
            textElement.setTypeface(Typeface.create("sans-serif-condensed", Typeface.ITALIC));
            textElement.setTextSize(22);
            // FONT : using DownloadGooglefont :
            // Typeface myfont = ResourcesCompat.getFont(this, R.font.courgette);
            // myfont = getResources().getFont(R.font.courgette); # FOR API >26 ;) #

            // TEXTE QUANTITE PAR ELEMENT PAR ALIMENT :
            TextView textValeurMoyennePour100g = new TextView(this);
            textValeurMoyennePour100g.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, 250, ViewGroup.LayoutParams.WRAP_CONTENT,
                            0,0,0,0,Gravity.CENTER,1));
            textValeurMoyennePour100g.setGravity(Gravity.CENTER);
            textValeurMoyennePour100g.setTypeface(Typeface.create("sans-serif-condensed", Typeface.ITALIC));
            textValeurMoyennePour100g.setTextSize(22);

            // IMAGE POUCEFOOD selon ELEMENT :
            ImageView imagePouceImageView = new ImageView(this);
            imagePouceImageView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, ViewGroup.LayoutParams.WRAP_CONTENT, 100,
                            0,0,0,0,Gravity.CENTER,1));

            // DEFINITION TEXTE DES ELEMENTS :
            textElement.setText(descriptionFoodArrayElements[i]);

            // Text selon valeur Moyenne des ELEMENTs PAR ALIMENT compares a la valeur moyenne globale:
            textValeurMoyennePour100g.setText(food.getTextPlusOuMoinsQueLaValeurMoyenneDeAllFood(valueIndexQuantityFoodByElement, i));

            // Image Pouce selon valeur Moyenne des ELEMENTs PAR ALIMENT compares a la valeur moyenne globale:
            imagePouceImageView.setImageResource(TransitionClass.myLib.intIMGPouceFood[food.getValuePouce()]);

            // SET TEXT COLOR :
            if (food.getValuePouce() == LibraryArrayBitmapDrawingRessources.enumValuePouceFood.pouceVert.ordinal()){
                textValeurMoyennePour100g.setTextColor(context.getColor(R.color.nutriscore_vert1));
            }
            else {
                textValeurMoyennePour100g.setTextColor(context.getColor(R.color.nutriscore_rouge));
            }

            layout.addView(textElement);
            layout.addView(imagePouceImageView);
            layout.addView(textValeurMoyennePour100g);
            linearLayoutScrollView.addView(layout);
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