package fr.maxime.mygame01.Etagere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fr.maxime.mygame01.FoodListView.FoodListActivity;
import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.GameViewActivity;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;
import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScore;
import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScoreGlobalElements;

public class EtagereFoodSelectorActivity extends AppCompatActivity {

    private Context context;
    private ImageView cancelButton;
    private TextView validateButton;
    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnBoardingIndicator;

    //Test POPUP INFO :
    private Dialog popupInfoDialog;
    private Button popupContinueButton;
    private ImageView popupCancelImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etagere_food_selector);

        context = this;
        TransitionClass.setMusic(context, R.raw.ed_guitard_haut);

        // Test POPUP INFO :
        popupInfoDialog = new Dialog(context);
        showPopupInfo();
        //---

        cancelButton = findViewById(R.id.etagereFoodSelectorActivity_cancelImageView);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCancelButton();
            }
        });

        validateButton = findViewById(R.id.etagereFoodSelectorActivity_ValidateTextView);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickValidateButton();
            }
        });

        layoutOnBoardingIndicator = findViewById(R.id.etagereFoodSelectorActivity_layoutOnBoardingIndicator);

        setupOnBoardingItemPages();

        ViewPager2 onBoardingViewPager = findViewById(R.id.etagereFoodSelectorActivity_viewPager2);
        onBoardingViewPager.setAdapter(onBoardingAdapter);

        setupOnBoardingIndicator();
        setCurrentOnBoardingIndicator(0);

        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private void showPopupInfo() {
        popupInfoDialog.setContentView(R.layout.popup_info_le_saviez_vous_fruit);
        popupCancelImageView = popupInfoDialog.findViewById(R.id.popup_info_lesaviezvous_ImageViewCancel);
        popupCancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                popupInfoDialog.dismiss();
            }
        });
        popupContinueButton = popupInfoDialog.findViewById(R.id.popup_info_lesaviezvous_buttonContinue);
        popupContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.ValidationSound.ordinal());
                popupInfoDialog.dismiss();
            }
        });

        popupInfoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupInfoDialog.show();
    }

    private void setupOnBoardingItemPages() {

        ArrayList<OnBoardingItemPage> onBoardingItemPageArrayList = new ArrayList<>();

        String[] periodeJournee = new String[]{
                context.getString(R.string.matin),
                context.getString(R.string.midi),
                context.getString(R.string.apres_midi),
                context.getString(R.string.soir)};

        for (int i = 0; i < periodeJournee.length; i++) {
            OnBoardingItemPage page = new OnBoardingItemPage();
            page.setTitleText(periodeJournee[i]);
            for (RepasMakerClass repas : repasListAccordingFoodTypeRestriction(TransitionClass.myLib.repasMakerList)) {
                if (repas.getTypeOfMeal() == i) {
                    page.arrayListRepas.add(repas);
                }
            }
            onBoardingItemPageArrayList.add(page);
        }

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItemPageArrayList);

    }

    private ArrayList<RepasMakerClass> repasListAccordingFoodTypeRestriction(ArrayList<RepasMakerClass> repasListToCheck) {

        ArrayList<RepasMakerClass> repasArrayListToReturn = new ArrayList<>();

        // VEGETARIEN :
        if (TransitionClass.profileActual.getTypeAlimentation() == LibraryArrayBitmapDrawingRessources.enumTypeOfFood.VEGETARIEN.ordinal()) {

            for (RepasMakerClass repas : repasListToCheck) {

                if (repas.isVegetarian()) {
                    repasArrayListToReturn.add(repas);
                }

            }
        }


        // VEGAN :
        else if (TransitionClass.profileActual.getTypeAlimentation() == LibraryArrayBitmapDrawingRessources.enumTypeOfFood.VEGAN.ordinal()) {

            for (RepasMakerClass repas : repasListToCheck) {

                if (repas.isVegan()) {
                    repasArrayListToReturn.add(repas);
                }

            }

        }

        // SANS PORC ET ALCOOL :
        else if (TransitionClass.profileActual.getTypeAlimentation() == LibraryArrayBitmapDrawingRessources.enumTypeOfFood.SANSPORCETALCOOL.ordinal()) {

            for (RepasMakerClass repas : repasListToCheck) {

                if (!repas.isPorkOrAlcohol()) {
                    repasArrayListToReturn.add(repas);
                }

            }

        }
        // SANS RESTRICTION :
        else {
            return repasListToCheck;
        }

        return repasArrayListToReturn;

    }

    private void setupOnBoardingIndicator() {

        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16, 0, 16, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageResource(R.drawable.onboardindicator_no_active);
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicator.addView(indicators[i]);
        }

    }

    private void setCurrentOnBoardingIndicator(int index) {
        int childCount = layoutOnBoardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnBoardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageResource(R.drawable.onboardindicator_active);
            } else {
                imageView.setImageResource(R.drawable.onboardindicator_no_active);
            }
        }
    }

    private void clickValidateButton() {

        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());

    }

    private void clickCancelButton() {

        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        TransitionClass.onBoardingAdapterlinearLayoutArrayList.clear();

        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {
        clickCancelButton();
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