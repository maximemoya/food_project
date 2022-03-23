package fr.maxime.mygame01.DailyNeedView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import fr.maxime.mygame01.FoodSelectorV2.FoodSelectorActivityV2;
import fr.maxime.mygame01.GameViewActivity;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.ProfileDailyNeedsClass;
import fr.maxime.mygame01.MyObjects.Sauvegarde;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

import static fr.maxime.mygame01.MyObjects.TransitionClass.myLib;

public class DailyNeedCreateProfilActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Context context;

    LinearLayout morphologieLinearLayout;

    ArrayList<ImageView> morphologieImageViewList = new ArrayList<>();
    ImageView imageViewMorphologie, imageViewMorphologie1, imageViewMorphologie2, imageViewMorphologie3;

    EditText nameInput, ageInput, tallInput;
    Button buttonValidate, cancelButton;
    Spinner sexSpinner;
    String[] sexArray;

    String name = "";
    Integer sexe = -1;
    Integer age = 0;
    Integer tall = 0;
    Integer morphologie = 0; // 1 legere | 2 moyenne | 3 lourde

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_daily_need_profil);
        context = this;

        TransitionClass.resetMusicForFoodSelectorV2 = true;
        if(TransitionClass.activityToBackUpByCancel != LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.PROFILEMENU.ordinal()){
            TransitionClass.setMusic(context, R.raw.ed_music_profile);
        }

        // Cancel Button :
        cancelButton = findViewById(R.id.dailyNeedProfilCreation_cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClick();
            }
        });

        // NAME INPUT :
        nameInput = findViewById(R.id.editTextPersonName);
        nameInput.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        nameInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nameInput.getText().toString().equals("")) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    closeKeyboard();
                    nameInput.clearFocus();
                } else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                }
            }
        });
        nameInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                return false;
            }
        });

        // AGE INPUT :
        ageInput = findViewById(R.id.editTextAgeNumber);
        ageInput.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ageInput.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    age = Integer.parseInt(ageInput.getText().toString());
                } catch (Exception e) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                    ageInput.setText("");
                }

                if (9 < age && age < 120) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    tallInput.requestFocus();
                } else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                    ageInput.setText("");
                }
            }
        });
        ageInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                return false;
            }
        });

        // TALL INPUT :
        tallInput = findViewById(R.id.editTextTallNumber);
        tallInput.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tallInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tall = Integer.parseInt(tallInput.getText().toString());
                } catch (Exception e) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                    tallInput.setText("");
                }
                if (125 < tall && tall < 230) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    closeKeyboard();
                    tallInput.clearFocus();
                } else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                    tallInput.setText("");
                }
            }
        });
        tallInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                return false;
            }
        });

        // SEX INPUT :
        sexArray = new String[]{this.getResources().getString(R.string.male), this.getResources().getString(R.string.female)};
        sexSpinner = findViewById(R.id.spinner2sex);
        sexSpinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sexArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(adapter);
        sexSpinner.setOnItemSelectedListener(this);

        sexSpinner.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                return false;
            }
        });

        // MORPHOLOGIE LAYOUT :
        morphologieDisplay(morphologie);

        // VALIDATION BUTTON INPUT :
        buttonValidate = findViewById(R.id.buttonValidateUserDailyNeed);
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userValidityInformations(nameInput, ageInput, tallInput, morphologie)) {

                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.ValidationSound.ordinal());

                    if (TransitionClass.isOnModifyProfile){
                        TransitionClass.profileActual.setPseudo(name);
                        TransitionClass.profileActual.setSex(sexe);
                        TransitionClass.profileActual.setAge(age);
                        TransitionClass.profileActual.setTaille(tall);
                        TransitionClass.profileActual.setMorphologie(morphologie);
                        TransitionClass.profileActual.setCalculPoidsIdeal(context);
                    }
                    else {
                        // Method to generate idNumberNewProfile :
                        int idNumberNewProfile = ProfileDailyNeedsClass.getGenerateIdNumberNewProfile();

                        ProfileDailyNeedsClass profile = new ProfileDailyNeedsClass(context, name, sexe, age, tall, morphologie, idNumberNewProfile);
                        TransitionClass.profileActual = profile;
                        TransitionClass.myLib.listDailyNeedsProfil.add(profile);
                    }

                    // serialize :
                    Sauvegarde save = new Sauvegarde(TransitionClass.myLib.listDailyNeedsProfil, TransitionClass.profileActual);
                    MaximeToolsLayout.serializeSauvegarde(context,TransitionClass.saveName, save);

                    if (TransitionClass.isOnModifyProfile){
                        TransitionClass.isOnModifyProfile = false;
                        Intent intent = new Intent(getApplicationContext(), ProfileMenuActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), DailyNeedActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                } else {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                    Toast.makeText(context, getString(R.string.informations_non_valides), Toast.LENGTH_LONG).show();
                }
            }
        });

        // Check Store Profile in myLib :
        if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.PROFILEMENU.ordinal()) {
            nameInput.setText(TransitionClass.profileActual.getPseudo());
            sexSpinner.setSelection(TransitionClass.profileActual.getSex());
            String text = TransitionClass.profileActual.getAge().toString();
            ageInput.setText(text);
            text = TransitionClass.profileActual.getTaille().toString();
            tallInput.setText(text);
        }
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private void onCancelClick(){
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.MENU.ordinal()) {

            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.GAME.ordinal()) {

            Intent intent = new Intent(getApplicationContext(), GameViewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.FOODSELECTORV2.ordinal()) {

            Intent intent = new Intent(getApplicationContext(), FoodSelectorActivityV2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (TransitionClass.activityToBackUpByCancel == LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.PROFILEMENU.ordinal()) {

            Intent intent = new Intent(getApplicationContext(), ProfileMenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void morphologieDisplay(final int morphologieInput) {
        morphologieLinearLayout = findViewById(R.id.morphologieLinearLayoutProfilActivity);
        morphologieLinearLayout.removeAllViews();
        int selected0 = 0;
        int selected1 = 0;
        int selected2 = 0;

        imageViewMorphologie = null;
        imageViewMorphologie1 = null;
        imageViewMorphologie2 = null;
        imageViewMorphologie3 = null;
        morphologieImageViewList.clear();

        if (morphologie > 0 && morphologie <= 3) {
            this.morphologie = morphologieInput;
            if (morphologie == 1) {
                selected0 = 3;
            } else if (morphologie == 2) {
                selected1 = 3;
            } else {
                selected2 = 3;
            }

        } else {
            this.morphologie = 0;
        }

        // homme
        if (sexe <= 0) {
            imageViewMorphologie = new ImageView(context);
            imageViewMorphologie.setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMEFIN.ordinal() + selected0));
            imageViewMorphologie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    morphologie = 1;
                    morphologieImageViewList.get(0).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMEFIN.ordinal() + 3));
                    morphologieImageViewList.get(1).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMEMOYEN.ordinal()));
                    morphologieImageViewList.get(2).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMESOLIDE.ordinal()));
                    Toast.makeText(context, getString(R.string.ossatureLegere), Toast.LENGTH_SHORT).show();
                }
            });
            morphologieImageViewList.add(imageViewMorphologie);

            imageViewMorphologie = new ImageView(context);
            imageViewMorphologie.setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMEMOYEN.ordinal() + selected1));
            imageViewMorphologie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    morphologie = 2;
                    morphologieImageViewList.get(0).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMEFIN.ordinal()));
                    morphologieImageViewList.get(1).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMEMOYEN.ordinal() + 3));
                    morphologieImageViewList.get(2).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMESOLIDE.ordinal()));
                    Toast.makeText(context, getString(R.string.ossatureMediane), Toast.LENGTH_SHORT).show();
                }
            });
            morphologieImageViewList.add(imageViewMorphologie);

            imageViewMorphologie = new ImageView(context);
            imageViewMorphologie.setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMESOLIDE.ordinal() + selected2));
            imageViewMorphologie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    morphologie = 3;
                    morphologieImageViewList.get(0).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMEFIN.ordinal()));
                    morphologieImageViewList.get(1).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMEMOYEN.ordinal()));
                    morphologieImageViewList.get(2).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.HOMMESOLIDE.ordinal() + 3));
                    Toast.makeText(context, getString(R.string.ossatureLourde), Toast.LENGTH_SHORT).show();
                }
            });
            morphologieImageViewList.add(imageViewMorphologie);

        }
        // femme
        else {
            imageViewMorphologie = new ImageView(context);
            imageViewMorphologie.setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMEFINE.ordinal() + selected0));
            imageViewMorphologie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    morphologie = 1;
                    morphologieImageViewList.get(0).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMEFINE.ordinal() + 3));
                    morphologieImageViewList.get(1).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMEMOYENNE.ordinal()));
                    morphologieImageViewList.get(2).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMESOLIDE.ordinal()));
                    Toast.makeText(context, getString(R.string.ossatureLegere), Toast.LENGTH_SHORT).show();
                }
            });

            morphologieImageViewList.add(imageViewMorphologie);
            imageViewMorphologie = new ImageView(context);
            imageViewMorphologie.setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMEMOYENNE.ordinal() + selected1));
            imageViewMorphologie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    morphologie = 2;
                    morphologieImageViewList.get(0).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMEFINE.ordinal()));
                    morphologieImageViewList.get(1).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMEMOYENNE.ordinal() + 3));
                    morphologieImageViewList.get(2).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMESOLIDE.ordinal()));
                    Toast.makeText(context, getString(R.string.ossatureMediane), Toast.LENGTH_SHORT).show();
                }
            });

            morphologieImageViewList.add(imageViewMorphologie);
            imageViewMorphologie = new ImageView(context);
            imageViewMorphologie.setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMESOLIDE.ordinal() + selected2));
            imageViewMorphologie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    morphologie = 3;
                    morphologieImageViewList.get(0).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMEFINE.ordinal()));
                    morphologieImageViewList.get(1).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMEMOYENNE.ordinal()));
                    morphologieImageViewList.get(2).setImageBitmap(myLib.bitmapMorphologieList.get(LibraryArrayBitmapDrawingRessources.morphologieEnum.FEMMESOLIDE.ordinal() + 3));
                    Toast.makeText(context, getString(R.string.ossatureLourde), Toast.LENGTH_SHORT).show();
                }
            });
            morphologieImageViewList.add(imageViewMorphologie);
        }

        for (int i = 0; i < morphologieImageViewList.size(); i++) {

            //Separator Layout
            LinearLayout separatorLayout = new LinearLayout(this);
            separatorLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT));

            morphologieLinearLayout.addView(separatorLayout);
            morphologieLinearLayout.addView(morphologieImageViewList.get(i));


        }


    }

    public boolean userValidityInformations(EditText _name, EditText _age, EditText _tall, Integer morphologie) {

        name = _name.getText().toString();
        if (_age.getText().toString().equals("")) {
            age = 0;
        } else {
            age = Integer.parseInt(_age.getText().toString());
        }
        if (_tall.getText().toString().equals("")) {
            tall = 0;
        } else {
            tall = Integer.parseInt(_tall.getText().toString());
        }
        if (morphologie < 1 || morphologie > 3) {
            morphologie = 0;
        }

        return !name.equals("") && age > 0 && tall > 0 && morphologie != 0;
    }

    // ---------------------------------------------------------------------------------------------
    // Spinner Adapter View Methods :

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (sexe < 0) {
            sexe = position;
        } else {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
            sexe = position;
        }
        morphologieDisplay(morphologie);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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