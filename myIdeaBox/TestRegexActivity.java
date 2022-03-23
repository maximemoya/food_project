package fr.maxime.mygame01.myIdeaBox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.maxime.mygame01.FoodListView.FoodDescriptionV2;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

public class TestRegexActivity extends AppCompatActivity {

    private Context context;
    private EditText editTextInput;
    private LinearLayout linearLayoutInVerticalScrollView;

    private ArrayList<FoodClass> foodTotalList = TransitionClass.selectedFoodList;
    private ArrayList<FoodClass> foodMatchedList = new ArrayList<>();

    private String inputTextSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_regex);

        context = this;
        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.TESTREGEX.ordinal();

        linearLayoutInVerticalScrollView = findViewById(R.id.testRegexActivity_linearLayoutInVerticalScrollView);

        inputTextSaved = TransitionClass.regexSavedInput;

        editTextInput = findViewById(R.id.testRegexActivity_editText);
        if (!inputTextSaved.equals("")) {
            editTextInput.setText(inputTextSaved);
            miseAJourRegex();
        }

        editTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                inputTextSaved = s.toString();
                miseAJourRegex();

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (editTextInput.getText().length() > 9) {
                    closeKeyboard();
                    editTextInput.clearFocus();
                }
            }
        });

        miseAJourAffichage();

    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private void miseAJourRegex() {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
        foodMatchedList.clear();

        Pattern pattern = Pattern.compile(inputTextSaved);

        for (FoodClass food : foodTotalList) {

            String name = food.name;
            name = Normalizer.normalize(name, Normalizer.Form.NFKD);
//                    name = name.replaceAll("[^\\p{ASCII}]", "");
            name = name.replaceAll("\\p{M}", "");
            name = name.toLowerCase();

            Matcher matcher = pattern.matcher(name);
            if (matcher.lookingAt()) {
                if (!foodMatchedList.contains(food)) {
                    foodMatchedList.add(food);
                }
            }
        }

        for (FoodClass food : foodTotalList) {

            String name = food.name;
            name = Normalizer.normalize(name, Normalizer.Form.NFKD);
//                    name = name.replaceAll("[^\\p{ASCII}]", "");
            name = name.replaceAll("\\p{M}", "");
            name = name.toLowerCase();

            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                if (!foodMatchedList.contains(food)) {
                    foodMatchedList.add(food);
                }
            }
        }

        if (foodMatchedList.size() > 0) {
            miseAJourAffichage();
        } else {
            linearLayoutInVerticalScrollView.removeAllViews();
        }
    }

    private void miseAJourAffichage() {

        linearLayoutInVerticalScrollView.removeAllViews();

        // Si aucun match :
        if (foodMatchedList.size() == 0) {

            Collections.sort(foodTotalList, FoodClass.comparatorName);
            createFoodLayout(foodTotalList);

        }
        // Si match :
        else {

            createFoodLayout(foodMatchedList);
        }

    }

    private void createFoodLayout(ArrayList<FoodClass> foodlistToDisplay) {
        for (final FoodClass food : foodlistToDisplay) {

            // LinearLayout :
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                            10, 10, 10, 5, Gravity.CENTER, 0));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                    TransitionClass.foodstatic = food;
                    TransitionClass.regexSavedInput = inputTextSaved;

                    Intent intent = new Intent(getApplicationContext(), FoodDescriptionV2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
            });

            // ImageView :
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, 150, 200,
                            0, 10, 0, 0, Gravity.CENTER, 0));
            imageView.setImageResource(food.bitmapRawRessource);

            // TextView :
            TextView textViewName = new TextView(context);
            textViewName.setLayoutParams(MaximeToolsLayout.setLayoutParams
                    (context, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                            0, 10, 0, 0, Gravity.CENTER, 0));
            textViewName.setTextColor(context.getColor(R.color.colorMaxPrimaryWHITE));
            textViewName.setTextSize(20);
            textViewName.setText(food.name);

            // Add views in Layout :
            linearLayout.addView(imageView);
            linearLayout.addView(textViewName);
            linearLayoutInVerticalScrollView.addView(linearLayout);

        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void resetVariableBeforeEXIT() {
        TransitionClass.regexSavedInput = "";
    }

    private void onCancelClick() {

        resetVariableBeforeEXIT();

        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
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