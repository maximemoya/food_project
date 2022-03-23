package fr.maxime.mygame01.FoodSelectorV2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.maxime.mygame01.DailyNeedView.DailyNeedActivity;
import fr.maxime.mygame01.FoodListView.FoodDescriptionV2;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.EmotionClass;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.SuiviAlimentaireClass;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;
import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScore;
import fr.maxime.mygame01.ResultView.ResultFoodSelectorV2ActivityNutriScoreGlobalElements;
import fr.maxime.mygame01.ResultView.ResultSuiviRepasActivity;
import fr.maxime.mygame01.ResultView.ResultViewActivity;

public class FoodSelectorActivityV2 extends AppCompatActivity {

    private Context context;

    private ImageView cancelImageView, emotionImageView, infoImageView, quantitySmallImageView, quantityMediumImageView, quantityBigImageView;
    private LinearLayout profileLinearLayout;
    private TextView profileTextView, categoryText, nutriScoreTextView, eatTextView;
    private EditText quantityEditText;

    private String[] arrayEditTextQuantityText = new String[]{" < 100g", " +/- 200g", " > 200g"};

    private GridView gridView;
    private EmotionClass emotionSquare;

    private LinearLayout linearLayoutScroolViewChooseFoodList;
    private ArrayList<FoodClass> foodChosenList = new ArrayList<>();

    private boolean isLongTouch = false;
    private int quantityChosenAmount = -2;

    private FoodClass food;

    private int quantityPersonalInput = 0;
    public enum enumQuantityChosenAmount {
        SPECIFIC(0), SMALL(-1), MEDIUM(-2), BIG(-3);

        enumQuantityChosenAmount(int i) {
        }

        public int geti() {
            switch (this.ordinal()) {
                case 1:
                    return -1;
                case 2:
                    return -2;
                case 3:
                    return -3;
                default:
                    return 0;
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selector_v2);

        // Initialisation :
        context = this;

        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.FOODSELECTORV2.ordinal();
        if(TransitionClass.resetMusicForFoodSelectorV2){
            TransitionClass.setMusic(context, R.raw.ed_guitard_total_long);
            TransitionClass.resetMusicForFoodSelectorV2 = false;
        }

        // NutriScoreTextView :
        nutriScoreTextView = findViewById(R.id.foodSelectorV2_nutriScoreTextView);
        nutriScoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TransitionClass.nutriScoreCharacter.equals("")) {

                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                    storeValuesBeforeJump();

                    Intent intent = new Intent(context, ResultFoodSelectorV2ActivityNutriScore.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
                else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                    Toast.makeText(context, context.getString(R.string.aucun_objet_selectionne), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // QuantitySelectionImageView :
        quantitySmallImageView = findViewById(R.id.foodSelectorV2_QuantitySmallImageView);
        quantitySmallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeEditTextInput(quantityEditText);

                if (quantityChosenAmount != enumQuantityChosenAmount.SMALL.geti()) {
                    quantityChosenAmount = enumQuantityChosenAmount.SMALL.geti();
                    quantityEditText.setText(arrayEditTextQuantityText[0]);
                    quantityEditText.setTextColor(context.getColor(R.color.marron1));
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    quantitySmallImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.SMALLSELECTED.ordinal()));
                    quantityMediumImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.MEDIUM.ordinal()));
                    quantityBigImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.BIG.ordinal()));

                } else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                }
            }
        });
        quantityMediumImageView = findViewById(R.id.foodSelectorV2_QuantityMediumImageView);
        quantityMediumImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeEditTextInput(quantityEditText);

                if (quantityChosenAmount != enumQuantityChosenAmount.MEDIUM.geti()) {
                    quantityChosenAmount = enumQuantityChosenAmount.MEDIUM.geti();
                    quantityEditText.setText(arrayEditTextQuantityText[1]);
                    quantityEditText.setTextColor(context.getColor(R.color.marron1));
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    quantityMediumImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.MEDIUMSELECTED.ordinal()));
                    quantitySmallImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.SMALL.ordinal()));
                    quantityBigImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.BIG.ordinal()));
                } else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                }
            }
        });
        quantityBigImageView = findViewById(R.id.foodSelectorV2_QuantityBigImageView);
        quantityBigImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeEditTextInput(quantityEditText);

                if (quantityChosenAmount != enumQuantityChosenAmount.BIG.geti()) {
                    quantityChosenAmount = enumQuantityChosenAmount.BIG.geti();
                    quantityEditText.setText(arrayEditTextQuantityText[2]);
                    quantityEditText.setTextColor(context.getColor(R.color.quantityBig));
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    quantityBigImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.BIGSELECTED.ordinal()));
                    quantitySmallImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.SMALL.ordinal()));
                    quantityMediumImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.MEDIUM.ordinal()));
                } else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                }
            }
        });

        // QuantityEditText :
        quantityEditText = findViewById(R.id.foodSelectorV2_quantityEditText);
        quantityEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                quantityEditText.setText("");
                quantityPersonalInput = 0;
                return false;
            }
        });

        quantityEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                try {
                    quantityPersonalInput = Integer.parseInt(quantityEditText.getText().toString());
                } catch (Exception e) {
                    quantityPersonalInput = keyCode-7;
                    if (quantityPersonalInput > 10){
                        quantityPersonalInput = 0;
                    }
                }
                if (0 < quantityPersonalInput && quantityPersonalInput < 10000) {

                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    quantityEditText.setTextColor(context.getColor(R.color.quantityPerso));
                    quantityChosenAmount = quantityPersonalInput;
                    quantitySmallImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.SMALL.ordinal()));
                    quantityMediumImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.MEDIUM.ordinal()));
                    quantityBigImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.BIG.ordinal()));

                } else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                    quantityEditText.setText("");
                }

                return false;
            }
        });

        quantityEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                closeKeyboard();
            }
        });

        // TEXT CATEGORY :
        categoryText = findViewById(R.id.foodSelectorV2_mainTextView);

        // ScrollViewHorizontal FoodChosenList :
        linearLayoutScroolViewChooseFoodList = findViewById(R.id.foodSelectorV2_LinearLayoutScrollViewChooseFoodList);
        linearLayoutScroolViewChooseFoodList.removeAllViews();

        // LOAD FoodChosenList View add in ScrollViewHorizontal :
        loadValuesAfterJump();

        // InfoImageView :
        infoImageView = findViewById(R.id.foodSelectorV2_infoImageView);
        infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TransitionClass.nutriScoreCharacter.equals("")) {

                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                    storeValuesBeforeJump();

                    Intent intent = new Intent(context, ResultFoodSelectorV2ActivityNutriScoreGlobalElements.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
                else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                    Toast.makeText(context, context.getString(R.string.aucun_objet_selectionne), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // EmotionImageView :
        emotionSquare = new EmotionClass(context);
        emotionImageView = findViewById(R.id.foodSelectorV2_emotionImageView);
        emotionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                TransitionClass.foodOnDropZoneList = foodChosenList;

                Intent intent = new Intent(context, ResultViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);

            }
        });

        // BUTTON PROFILE :
        profileTextView = findViewById(R.id.foodSelectorV2_ProfileTextView);
        profileTextView.setText(TransitionClass.profileActual.getPseudo());
        if (TransitionClass.profileActual.getPseudo().length() <= 5){
            profileTextView.setTextSize(24);
        }
        else if (TransitionClass.profileActual.getPseudo().length() <= 8){
            profileTextView.setTextSize(18);
        }
        else if (TransitionClass.profileActual.getPseudo().length() <= 10){
            profileTextView.setTextSize(16);
        }
        else {
            profileTextView.setTextSize(14);
        }

        profileLinearLayout = findViewById(R.id.foodSelectorV2_ProfileButton);
        profileLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                storeValuesBeforeJump();
                Intent intent = new Intent(FoodSelectorActivityV2.this, DailyNeedActivity.class);
                startActivity(intent);
            }
        });

        // BUTTON CANCEL :
        cancelImageView = findViewById(R.id.foodSelectorV2_CancelButton);
        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());

                if (TransitionClass.levelCategoryGridView == 0) {
                    resetVariableBeforeEXIT();
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    FoodSelectorAdapterV2 adapterFoodSelectorV2_2 = new FoodSelectorAdapterV2(FoodSelectorActivityV2.this, TransitionClass.myLib.globalVisualFoodCategoryList.get(TransitionClass.alimentationType));
                    gridView.setAdapter(adapterFoodSelectorV2_2);
                    adapterFoodSelectorV2_2.notifyDataSetChanged();

                    categoryText.setText(R.string.text_category);
                    TransitionClass.levelCategoryGridView = 0;
                }
            }
        });

        // GRIDVIEW :
        gridView = findViewById(R.id.foodSelectorV2_GridView);
        if (TransitionClass.levelCategoryGridView == 0) {
            FoodSelectorAdapterV2 adapterFoodSelectorV2 = new FoodSelectorAdapterV2(this, TransitionClass.myLib.globalVisualFoodCategoryList.get(TransitionClass.alimentationType));
            gridView.setAdapter(adapterFoodSelectorV2);
            adapterFoodSelectorV2.notifyDataSetChanged();
        } else if (TransitionClass.levelCategoryGridView == 1) {
            FoodSelectorAdapterV2 adapterFoodSelectorV2_1 = new FoodSelectorAdapterV2(this, TransitionClass.myLib.globalFoodCategoryList.get(TransitionClass.alimentationType).get(TransitionClass.indexCategoryGridView));
            gridView.setAdapter(adapterFoodSelectorV2_1);
            adapterFoodSelectorV2_1.notifyDataSetChanged();

            categoryText.setText(TransitionClass.myLib.globalVisualFoodCategoryList.get(TransitionClass.alimentationType).get(TransitionClass.indexCategoryGridView).categoryName);
        }


        // PARTIE LOGIQUE GRIDVIEW :

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                if (TransitionClass.levelCategoryGridView == 1) {

                    isLongTouch = true;
                    final FoodClass food = TransitionClass.myLib.globalFoodCategoryList.get(TransitionClass.alimentationType).get(TransitionClass.indexCategoryGridView).get(position);
                    TransitionClass.positionFoodInCategoryGridView = position;
                    TransitionClass.stockageFood(food);
                    TransitionClass.foodOnDropZoneList = foodChosenList;

                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());

                    Intent intent = new Intent(context, FoodDescriptionV2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                closeEditTextInput(quantityEditText);

                if (!isLongTouch) {

                    // passe en mode interieur aux categories :
                    if (TransitionClass.levelCategoryGridView == 0) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        FoodSelectorAdapterV2 adapterFoodSelectorV2_1 = new FoodSelectorAdapterV2(FoodSelectorActivityV2.this, TransitionClass.myLib.globalFoodCategoryList.get(TransitionClass.alimentationType).get(position));
                        gridView.setAdapter(adapterFoodSelectorV2_1);
                        adapterFoodSelectorV2_1.notifyDataSetChanged();

                        categoryText.setText(TransitionClass.myLib.globalVisualFoodCategoryList.get(TransitionClass.alimentationType).get(position).categoryName);
                        TransitionClass.indexCategoryGridView = position;
                        TransitionClass.levelCategoryGridView = 1;

                    }
                    // dans mode intra-categories on ajoute la nourriture a la liste foodChosen :
                    else {
                        food = TransitionClass.myLib.globalFoodCategoryList.get(TransitionClass.alimentationType).get(TransitionClass.indexCategoryGridView).get(position);

                        // si l'aliment selectionne n'est pas present dans foodChosen :
                        // AJOUTE ALIMENT DANS FOODLIST :
                        if (!foodChosenList.contains(food)) {

                            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                            food.setQuantity(quantityChosenAmount);
                            quantityEditText.setText(food.getQuantityString(context));
                            foodChosenList.add(food);
                            getEmotionAddFood(food);
                            emotionImageView.setImageResource(emotionSquare.getEmotionRawIntImage());
                            TransitionClass.getInstantNutriScoreV2ABC(context, foodChosenList, nutriScoreTextView);
                            getScrollViewHorizontalFoodChosenList(food);
                        }
                        // si l'aliment selectionne est deja present dans foodChosen :
                        else {
                            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                            Toast.makeText(context, "\" " + food.name + " \"" + getString(R.string.est_deja_present_Text), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        // Bouton EAT :
        eatTextView = findViewById(R.id.foodSelectorV2_EatTextView);
        eatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(foodChosenList.size() > 0 && TransitionClass.profileActual != null){

                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.ValidationSound.ordinal());

                    TransitionClass.calculateTimeDayMoment();
                    int timeDayMoment = TransitionClass.getTimeDayMoment();
                    int[] dateArray = TransitionClass.getDate();

                    ArrayList<FoodClass> foodChosenList = new ArrayList<>();

                    for(FoodClass food : FoodSelectorActivityV2.this.foodChosenList){
                        foodChosenList.add(new FoodClass(food));
                    }

                    resetVariableBeforeEXIT();


//                  TEST STOCKAGE SAUVEGARDE POUR 1mois 1utilisateur, environ 20Ko le 23/11/20 :
//                    for(int i = 0; i < 31; i++){
//
//                        dateArray[0] += i;
//                        SuiviAlimentaireClass alimentsManges = new SuiviAlimentaireClass(timeDayMoment, textDayMoment, dateArray, foodChosenList);
//                        TransitionClass.profileActual.arrayListSuiviAlimentaireClass.add(alimentsManges);
//                    }

                    SuiviAlimentaireClass alimentsManges = new SuiviAlimentaireClass(timeDayMoment, dateArray, foodChosenList);
                    TransitionClass.profileActual.setADDArrayListSuiviAlimentaireClass(alimentsManges);

                    Intent intent = new Intent(getApplicationContext(), ResultSuiviRepasActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                    Toast.makeText(context, context.getString(R.string.aucun_objet_selectionne), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            if(touchY < (float) MenuActivity.screenSizeY * 2/3){
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                closeNoGoodEditTextInput(quantityEditText);
            }
        }

        return super.onTouchEvent(event);
    }

    // ---------------------------------------------------------------------------------------------
    // My METHODS :

    private void loadValuesAfterJump() {
        foodChosenList = TransitionClass.foodOnDropZoneList;
        quantityChosenAmount = TransitionClass.quantityAmountChosen;

        // Pour foodChosenList :
        if (foodChosenList != null) {
            TransitionClass.getInstantNutriScoreV2ABC(context, foodChosenList, nutriScoreTextView);
            for (FoodClass food : foodChosenList) {
                getScrollViewHorizontalFoodChosenList(food);
            }
        } else {
            foodChosenList = new ArrayList<>();
        }

        // Pour quantityChosenAmount :
        if (quantityChosenAmount == enumQuantityChosenAmount.SMALL.geti()) {
            quantitySmallImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.SMALLSELECTED.ordinal()));
            quantityMediumImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.MEDIUM.ordinal()));
            quantityBigImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.BIG.ordinal()));
        } else if (quantityChosenAmount == enumQuantityChosenAmount.MEDIUM.geti()) {
            quantitySmallImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.SMALL.ordinal()));
            quantityMediumImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.MEDIUMSELECTED.ordinal()));
            quantityBigImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.BIG.ordinal()));
        } else if (quantityChosenAmount == enumQuantityChosenAmount.BIG.geti()) {
            quantitySmallImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.SMALL.ordinal()));
            quantityMediumImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.MEDIUM.ordinal()));
            quantityBigImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.BIGSELECTED.ordinal()));
        } else {
            quantitySmallImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.SMALL.ordinal()));
            quantityMediumImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.MEDIUM.ordinal()));
            quantityBigImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.BIG.ordinal()));
            String text = quantityChosenAmount + "g";
            quantityEditText.setText(text);
        }

    }

    private void storeValuesBeforeJump() {
        TransitionClass.foodOnDropZoneList = foodChosenList;
        TransitionClass.quantityAmountChosen = quantityChosenAmount;
    }

    private void closeNoGoodEditTextInput(EditText editText) {

            closeEditTextInput(editText);

            if (editText.getText().toString().equals("")
            || editText.getText().toString().equals(arrayEditTextQuantityText[0])
            || editText.getText().toString().equals(arrayEditTextQuantityText[1])
            || editText.getText().toString().equals(arrayEditTextQuantityText[2])){
                quantityChosenAmount = enumQuantityChosenAmount.MEDIUM.geti();
                quantityMediumImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.MEDIUMSELECTED.ordinal()));
                quantitySmallImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.SMALL.ordinal()));
                quantityBigImageView.setImageResource(TransitionClass.myLib.intQuantityFoodImageList.get(LibraryArrayBitmapDrawingRessources.enumQuantityFoodIntResList.BIG.ordinal()));

            }
            else {
                try {
                    quantityChosenAmount = Integer.parseInt(editText.getText().toString());
                }
                catch (Exception ignored){
                    quantityChosenAmount = 0;
                }

            }


    }

    private void closeEditTextInput(EditText editText) {
        closeKeyboard();
        editText.clearFocus();
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private Double arrondir1decimales(Double nombre) {
        return (double) Math.round(nombre * 10) / 10;
    }

    public void getEmotionRemoveFood(FoodClass food) {
        if (food.categoryName.equals(context.getResources().getString(R.string.proteine))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.DECEPTION.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.legumineux))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.VEXER.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.fruit))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.TRISTESSE.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.feculent))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.STRESS.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.sucrerie))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.COLERE.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.friture))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.CONTENT1.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.alcool))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.TAQUIN.ordinal());
            emotionSquare.soundStart();
        }
        else {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.DECEPTION.ordinal());
            emotionSquare.soundStart();
        }
    }

    public void getEmotionAddFood(FoodClass food) {
        if (food.categoryName.equals(context.getResources().getString(R.string.proteine))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.INTERET.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.legumineux))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.CONTENT1.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.fruit))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.CONTENT2.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.feculent))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.TAQUIN.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.sucrerie))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.AMOUR.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.friture))) {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.DEGOUTER.ordinal());
            emotionSquare.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.alcool))) {
            Toast.makeText(context, context.getString(R.string.alcool_avec_moderation), Toast.LENGTH_SHORT).show();
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.INTERET.ordinal());
            emotionSquare.soundStart();
        }
        else {
            emotionSquare.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.MACHIAVELIQUE.ordinal());
            emotionSquare.soundStart();
        }
    }

    private void resetVariableBeforeEXIT() {
        TransitionClass.foodOnDropZoneList = new ArrayList<>();
        TransitionClass.levelCategoryGridView = 0;
        TransitionClass.indexCategoryGridView = 0;
    }

    private void getScrollViewHorizontalFoodChosenList(FoodClass food) {
        final FoodClass food1 = food;

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams(context, 155, LinearLayout.LayoutParams.MATCH_PARENT,
                0,0,0,0,Gravity.CENTER,0));

        // Remove food by click :
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                foodChosenList.remove(food1);
                linearLayoutScroolViewChooseFoodList.removeView(view);
                getEmotionRemoveFood(food1);
                emotionImageView.setImageResource(emotionSquare.getEmotionRawIntImage());
                TransitionClass.getInstantNutriScoreV2ABC(context, foodChosenList, nutriScoreTextView);
            }
        });

        TransitionClass.getInstantNutriScoreV2ABC(context, foodChosenList, nutriScoreTextView);

        // ImageView food in foodChosenList :
        ImageView imagefood = new ImageView(context);
        imagefood.setLayoutParams(MaximeToolsLayout.setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, 0,
                5,5,5,0,Gravity.CENTER,3));
        imagefood.setImageResource(food.bitmapRawRessource);

        // TextView to Visualize food in foodChosenList :
        TextView textViewQuantity = new TextView(context);
        textViewQuantity.setLayoutParams(MaximeToolsLayout.setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, 0,
                5, 5, 0, 10, Gravity.CENTER, 1));
        String textQuantityFood = food.getQuantityString(context);

        textViewQuantity.setText(textQuantityFood);
        textViewQuantity.setTextSize(12);
        textViewQuantity.setTypeface(Typeface.create("monospace", Typeface.BOLD));
        textViewQuantity.setGravity(Gravity.CENTER);

        if (food.quantityID == enumQuantityChosenAmount.SMALL.geti()) {
            textViewQuantity.setTextColor(context.getResources().getColor(R.color.quantitySmall));
        } else if (food.quantityID == enumQuantityChosenAmount.MEDIUM.geti()) {
            textViewQuantity.setTextColor(context.getColor(R.color.quantityMedium));
        } else if (food.quantityID == enumQuantityChosenAmount.SPECIFIC.geti()) {
            textViewQuantity.setTextColor(context.getColor(R.color.quantitySpecific));
        } else {
            textViewQuantity.setTextColor(context.getColor(R.color.quantityBig));
        }

        linearLayout.addView(imagefood);
        linearLayout.addView(textViewQuantity);

        linearLayoutScroolViewChooseFoodList.addView(linearLayout);
    }


    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {
        resetVariableBeforeEXIT();
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