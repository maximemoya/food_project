package fr.maxime.mygame01.DailyNeedView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.ProfileDailyNeedsClass;
import fr.maxime.mygame01.MyObjects.Sauvegarde;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

import static fr.maxime.mygame01.MyObjects.TransitionClass.myLib;
import static fr.maxime.mygame01.MyObjects.TransitionClass.profileActual;

public class ProfileMenuActivity extends AppCompatActivity {

    private Context context;
    private Button cancelButton, validateButton, modifyButton, eraseButton;
    private LinearLayout linearLayoutHorizontalScrollView;
    private ArrayList<ProfileDailyNeedsClass> listProfiles;
    private ProfileDailyNeedsClass selectedProfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_menu);

        context = this;
        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.PROFILEMENU.ordinal();
        TransitionClass.setMusic(context, R.raw.ed_music_menu_profil_v01);

        listProfiles = TransitionClass.myLib.listDailyNeedsProfil;

        cancelButton = findViewById(R.id.profileMenuActivity_BtnCancel);
        validateButton = findViewById(R.id.profileMenuActivity_BtnValidate);
        modifyButton = findViewById(R.id.profileMenuActivity_BtnModify);
        eraseButton = findViewById(R.id.profileMenuActivity_BtnErase);
        linearLayoutHorizontalScrollView = findViewById(R.id.profileMenuActivity_LinearLayoutScrollViewHorizontal);
        linearLayoutHorizontalScrollView.removeAllViews();

        selectedProfile = TransitionClass.profileActual;
        affichageDansHorizontalScrollView();

        // Validate Button :
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onValidateClick();
            }
        });

        // Modify Button :
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModifyClick();
            }
        });

        // Erase Button :
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEraseClick();
            }
        });

        // CANCEL BUTTON :
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelOrBackPressed();
            }
        });

    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private void onValidateClick(){
        if (selectedProfile != null) {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
            TransitionClass.profileActual = selectedProfile;

            // serialize :
            Sauvegarde save = new Sauvegarde(myLib.listDailyNeedsProfil, TransitionClass.profileActual);
            MaximeToolsLayout.serializeSauvegarde(context,TransitionClass.saveName, save);

            Intent intent = new Intent(getApplicationContext(), DailyNeedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        }
    }

    private void onModifyClick(){
        if (selectedProfile != null) {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
            TransitionClass.profileActual = selectedProfile;
            TransitionClass.isOnModifyProfile = true;

            Intent intent = new Intent(getApplicationContext(), DailyNeedCreateProfilActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        }
    }

    private void onEraseClick(){
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
        if (selectedProfile != null && listProfiles.size() > 1) {
            alertDialogEraseClick();
        } else {
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        }
    }

    private void alertDialogEraseClick() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(context.getString(R.string.etes_vous_sur_de_vouloir_effacer) + " " + selectedProfile.getPseudo() + " ?");

        // OUI :
        alertDialogBuilder.setPositiveButton(context.getString(R.string.oui),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());
                        listProfiles.remove(selectedProfile);
                        TransitionClass.myLib.listDailyNeedsProfil = listProfiles;
                        TransitionClass.profileActual = listProfiles.get(0);
                        selectedProfile = TransitionClass.profileActual;
                        affichageDansHorizontalScrollView();

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

    private void affichageDansHorizontalScrollView() {

        linearLayoutHorizontalScrollView.removeAllViews();

        for (final ProfileDailyNeedsClass profile : listProfiles) {

            // Layout de la case :
            LinearLayout layoutCaseProfile = new LinearLayout(context);
            layoutCaseProfile.setLayoutParams(MaximeToolsLayout.setLayoutParams(context, 350, 350,
                    0, 20, 0, 0, 0, 0));
            layoutCaseProfile.setOrientation(LinearLayout.VERTICAL);
            if(profile == selectedProfile){
                layoutCaseProfile.setBackgroundResource(R.drawable.button_border_yellow);
            }
            else {
                layoutCaseProfile.setBackgroundResource(R.drawable.layout_yellow_border);
            }

            layoutCaseProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    selectedProfile = profile;
                    affichageDansHorizontalScrollView();

                }
            });

            // TitleName :
            TextView titleNameTextView = new TextView(context);
            titleNameTextView.setLayoutParams(MaximeToolsLayout.setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                    10, 10, 5, 0, 0, 0));
            titleNameTextView.setTextSize(14);
            titleNameTextView.setTextColor(context.getColor(R.color.profilMenu_titleName));
            titleNameTextView.setTypeface(Typeface.create("casual", Typeface.BOLD));
            titleNameTextView.setText(context.getString(R.string.profilMenu_titleName));

            // NamePseudo :
            TextView namePseudoTextView = new TextView(context);
            namePseudoTextView.setLayoutParams(MaximeToolsLayout.setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                    0, 0, 5, 5, 0, 0));
            namePseudoTextView.setTextSize(14);
            namePseudoTextView.setTextColor(context.getColor(R.color.profilMenu_namePseudo));
            Typeface myFont = ResourcesCompat.getFont(context, R.font.courgette);
            namePseudoTextView.setTypeface(myFont);
            namePseudoTextView.setGravity(Gravity.CENTER);
            namePseudoTextView.setText(profile.getPseudo());

            // TitleAge :
            TextView titleAgeTextView = new TextView(context);
            titleAgeTextView.setLayoutParams(MaximeToolsLayout.setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                    10, 10, 5, 0, 0, 0));
            titleAgeTextView.setTextSize(14);
            titleAgeTextView.setTextColor(context.getColor(R.color.profilMenu_titleName));
            titleAgeTextView.setTypeface(Typeface.create("casual", Typeface.BOLD));
            titleAgeTextView.setText(context.getString(R.string.profilMenu_titleAge));

            // Age :
            TextView ageTextView = new TextView(context);
            ageTextView.setLayoutParams(MaximeToolsLayout.setLayoutParams(context, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                    0, 0, 5, 5, 0, 0));
            ageTextView.setTextSize(14);
            ageTextView.setTextColor(context.getColor(R.color.profilMenu_namePseudo));
            ageTextView.setTypeface(myFont);
            ageTextView.setGravity(Gravity.CENTER);
            ageTextView.setText(String.valueOf(profile.getAge()));

            // Creation de la case Profile :
            layoutCaseProfile.addView(titleNameTextView);
            layoutCaseProfile.addView(namePseudoTextView);
            layoutCaseProfile.addView(titleAgeTextView);
            layoutCaseProfile.addView(ageTextView);
            linearLayoutHorizontalScrollView.addView(layoutCaseProfile);
        }

        if (myLib.listDailyNeedsProfil.size() < TransitionClass.MAXPROFILENUMBER){
            // Creation de la case + :
            TextView addProfileTextView = new TextView(context);
            addProfileTextView.setLayoutParams(MaximeToolsLayout.setLayoutParams(context, 350, 350,
                    0, 20, 0, 0, 0, 0));
            addProfileTextView.setBackgroundResource(R.drawable.layout_yellow_border);
            addProfileTextView.setTextSize(100);
            addProfileTextView.setTextColor(context.getColor(R.color.profilMenu_titleName));
            addProfileTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            addProfileTextView.setTypeface(Typeface.create("casual", Typeface.BOLD));
            addProfileTextView.setText("+");
            addProfileTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TransitionClass.isOnModifyProfile = false;
                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.ValidationSound.ordinal());
                    Intent intent = new Intent(ProfileMenuActivity.this, DailyNeedCreateProfilActivity.class);
                    startActivity(intent);
                }
            });

            linearLayoutHorizontalScrollView.addView(addProfileTextView);
        }

    }

    private void cancelOrBackPressed() {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {
        cancelOrBackPressed();
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