package fr.maxime.mygame01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.maxime.mygame01.FoodListView.FoodDescriptionV2;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.TransitionClass;

public class RemerciementsActivity extends AppCompatActivity {

    private Context context;

    private LinearLayout linearLayoutInScrollView;

    private String[] arrayName, arrayFonction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remerciements);

        context = this;

        TransitionClass.setMusic(context, R.raw.ed_music_profile);

        linearLayoutInScrollView = findViewById(R.id.remerciementActivity_LinearLayoutInScrollView);

        arrayName = new String[]
                {"Edward HARDY", "A mes parents", "A ma famille et mes amis","Application créée par :", "MatchYourMeal_Alpha"};
        arrayFonction = new String[]
                {"Ingénieur Son : #Doug", "Qui m'ont soutenu", "Qui m'ont aidé", "Maxime MOYA", "LE 06 Décembre 2020 en France"};

        miseAJourAffichage();
    }

    // ---------------------------------------------------------------------------------------------
    // My METHODS :

    private void miseAJourAffichage(){

        for (int i = 0; i < arrayName.length; i++){

            // LinearLayout :
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                            10, 10, 10, 5, Gravity.CENTER, 0));
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            // TextView name :
            TextView textViewName = new TextView(context);
            textViewName.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0, 10, 0, 0, Gravity.START, 0));
            textViewName.setTextColor(context.getColor(R.color.colorMaxPrimaryWHITE));
            textViewName.setTypeface(Typeface.create("serif",Typeface.BOLD));
            textViewName.setPadding(10,0,10,0);
            textViewName.setTextSize(26);
            textViewName.setText(arrayName[i]);

            // TextView function :
            TextView textViewFunction = new TextView(context);
            textViewFunction.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0, 10, 20, 0, Gravity.START, 0));
            textViewFunction.setTextColor(getColor(R.color.nutriscore_orange));
            textViewFunction.setTypeface(Typeface.create("casual",Typeface.BOLD_ITALIC));
            textViewFunction.setTextSize(20);
            textViewFunction.setText(arrayFonction[i]);

            // Add views in Layout :
            linearLayout.addView(textViewName);
            linearLayout.addView(textViewFunction);
            linearLayoutInScrollView.addView(linearLayout);
        }

    }

    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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