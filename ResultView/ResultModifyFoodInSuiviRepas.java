package fr.maxime.mygame01.ResultView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.SuiviAlimentaireClass;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

public class ResultModifyFoodInSuiviRepas extends AppCompatActivity {

    private Context context;
    private TextView textDate;
    private LinearLayout layoutInHorizontalScrollView;
    private Button buttonCancel, buttonValidate, buttonModify, buttonErase;
    private SuiviAlimentaireClass suiviAlimentaire;
    private ArrayList<FoodClass> foodList = new ArrayList<>();
    private FoodClass foodSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_modify_food_in_suivi_repas);

        context = this;

        textDate = findViewById(R.id.resultModifyFoodInSuiviRepas_dateTextView);
        layoutInHorizontalScrollView = findViewById(R.id.resultModifyFoodInSuiviRepas_LinearLayoutScrollViewHorizontal);
        buttonCancel = findViewById(R.id.resultModifyFoodInSuiviRepas_BtnCancel);
        buttonValidate = findViewById(R.id.resultModifyFoodInSuiviRepas_BtnValidate);
        buttonModify = findViewById(R.id.resultModifyFoodInSuiviRepas_BtnModify);
        buttonErase = findViewById(R.id.resultModifyFoodInSuiviRepas_BtnErase);

        suiviAlimentaire = TransitionClass.getSuiviAlimentaireClassArrayListStatic();
        foodList = suiviAlimentaire.getArrayListFoodEaten();

        // ON CLICK ButtonCANCEL :
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClick();
            }
        });

        // ON CLICK ButtonVALIDATE :
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onValidateClick();
            }
        });

        // ON CLICK ButtonMODIFY :
        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModifyClick();
            }
        });

        // ON CLICK ButtonERASE :
        buttonErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEraseClick();
            }
        });

        init();

    }


    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private void onCancelClick() {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        Intent intent = new Intent(getApplicationContext(), ResultSuiviRepasActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void onValidateClick() {
        if (foodSelected != null) {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
            // TO CONTINUE...

        } else {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
            Toast.makeText(context, context.getString(R.string.aucun_objet_selectionne), Toast.LENGTH_SHORT).show();
        }
    }

    private void onModifyClick() {
        if (foodSelected != null) {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
            // TO CONTINUE...

        } else {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
            Toast.makeText(context, context.getString(R.string.aucun_objet_selectionne), Toast.LENGTH_SHORT).show();
        }
    }

    private void onEraseClick() {

        if (foodSelected != null) {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
            alertDialogEraseClick();
        } else {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
            Toast.makeText(context, context.getString(R.string.aucun_objet_selectionne), Toast.LENGTH_SHORT).show();
        }

    }

    private void alertDialogEraseClick() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(context.getString(R.string.etes_vous_sur_de_vouloir_effacer) + " " + foodSelected.name + " ?");

        // OUI :
        alertDialogBuilder.setPositiveButton(context.getString(R.string.oui),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        foodList.remove(foodSelected);
                        foodSelected = null;
                        affichageRefresh();
                    }
                });

        // NON :
        alertDialogBuilder.setNegativeButton(context.getString(R.string.non),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                        alertDialogBuilder.show().dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void init() {

        textDate.setText(suiviAlimentaire.getDateNumberFormat());
        affichageRefresh();
    }

    private void affichageRefresh() {

        layoutInHorizontalScrollView.removeAllViews();

        for (final FoodClass food : foodList) {

            // LINEARLAYOUT LAYOUTFOOD :
            LinearLayout layoutFood = new LinearLayout(context);
            layoutFood.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, 300, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0, 20, 0, 0, 0, 0));
            layoutFood.setOrientation(LinearLayout.VERTICAL);

            if (food == foodSelected) {
                layoutFood.setBackgroundResource(R.drawable.layout_lightwhite_border);
            } else {
                layoutFood.setBackgroundResource(R.drawable.button_border_brownlight);
            }

            layoutFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    foodSelected = food;
                    affichageRefresh();
                }
            });

            // IMAGEVIEW IMAGEFOOD :
            ImageView imageFood = new ImageView(context);
            imageFood.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0, 0, 0, 0, Gravity.CENTER, 0));
            imageFood.setImageResource(food.bitmapRawRessource);

            // TEXTVIEW QUANTITY :
            TextView textFoodQuantity = new TextView(context);
            textFoodQuantity.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0, 0, 5, 5, Gravity.CENTER, 0));
            textFoodQuantity.setGravity(Gravity.CENTER);
            Typeface myCourgettefont = ResourcesCompat.getFont(this, R.font.courgette);
            textFoodQuantity.setTypeface(myCourgettefont, Typeface.NORMAL);
            textFoodQuantity.setTextSize(16);

            textFoodQuantity.setText(food.getQuantityString(context));
            textFoodQuantity.setTextColor(food.getColorQuantityText());

            // ADDVIEW IN LAYOUTFOOD :
            layoutFood.addView(imageFood);
            layoutFood.addView(textFoodQuantity);
            layoutInHorizontalScrollView.addView(layoutFood);

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