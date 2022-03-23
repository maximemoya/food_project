package fr.maxime.mygame01.game.amicloonVisual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;
import fr.maxime.mygame01.game.GameMenuAmicloonActivity;

import static android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;

public class AmicloonVisualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.GAME.ordinal();

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(FLAG_KEEP_SCREEN_ON);

        AmicloonView amicloonView = new AmicloonView(this);
        setContentView(amicloonView);
    }

    // ---------------------------------------------------------------------------------------------
    // ANDROID CELLPHONE NATIVE OPTION PART :

    @Override
    public void onBackPressed() {
        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
        Intent intent = new Intent(getApplicationContext(), GameMenuAmicloonActivity.class);
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