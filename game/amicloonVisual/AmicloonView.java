package fr.maxime.mygame01.game.amicloonVisual;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.Amicloon;
import fr.maxime.mygame01.MyObjects.AmicloonSprite;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

public class AmicloonView extends View {

    // Partie Affichage et Constructeur :
    private Context context;
    private final Handler handler;
    private final Runnable runnable;

    // Partie Interractions :
    private float touchX = 0, touchY = 0;

    // ---> POPUP ActivityAmicloon :
    private Dialog popupFoodShopDialog;
    private Button popupFoodStoreBuyButton;
    private ImageView popupCancelImageView;

    // ---> AMICLOON :
    private AmicloonSprite amicloonSprite;
    private int amicloonMotion = 0;


    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public AmicloonView(Context context) {
        super(context);

        amicloonSprite = new AmicloonSprite(context);
        amicloonSprite.setPosition((float) MenuActivity.screenSizeX / 2 - amicloonSprite.getWidth(), (float) MenuActivity.screenSizeY / 2);

        // LOGIC PART to update display :
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    // ---------------------------------------------------------------------------------------------
    // Partie Affichage :

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Animation Amicloon :
        if (amicloonSprite.animationEnumINT == AmicloonSprite.enumAnimation.neutre.ordinal()){

            if ( amicloonMotion % 2 == 0){
                //pair
                amicloonSprite.setPosition(amicloonSprite.getArrayPositionXY()[0], amicloonSprite.getArrayPositionXY()[1] + 5);
            }
            else {
                // impair
                amicloonSprite.setPosition(amicloonSprite.getArrayPositionXY()[0], amicloonSprite.getArrayPositionXY()[1] - 5);
            }

            if (amicloonMotion < 40){
                amicloonSprite.setPosition(amicloonSprite.getArrayPositionXY()[0] + 10, amicloonSprite.getArrayPositionXY()[1]);
                amicloonMotion ++;
            }
            else if (amicloonMotion < 80){
                amicloonSprite.setPosition(amicloonSprite.getArrayPositionXY()[0] - 10, amicloonSprite.getArrayPositionXY()[1]);
                amicloonMotion ++;
            }
            else {
                amicloonMotion = 0;
            }

            canvas.drawBitmap(amicloonSprite.animationNeutre(), amicloonSprite.getArrayPositionXY()[0], amicloonSprite.getArrayPositionXY()[1], null);
        }
        else if (amicloonSprite.animationEnumINT == AmicloonSprite.enumAnimation.content.ordinal()){
            canvas.drawBitmap(amicloonSprite.animationContent(), amicloonSprite.getArrayPositionXY()[0], amicloonSprite.getArrayPositionXY()[1], null);

        }

        // LOGIC PART to update display :
        long UPDATE_MILLISEC = 40L; // 1000 / 40 = 25fps
        handler.postDelayed(runnable, UPDATE_MILLISEC);
    }


    // ---------------------------------------------------------------------------------------------
    // Partie Interractions :

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        touchX = event.getX();
        touchY = event.getY();
        int action = event.getAction();

        // ACTIONS
        if (action == MotionEvent.ACTION_DOWN) {

            if(isTouchIntersectRect(amicloonSprite.getRect())){
                if (!amicloonSprite.isAnimated){
                    amicloonSprite.animationEnumINT = AmicloonSprite.enumAnimation.content.ordinal();
                }
            }

            //POPUP FoodShop :
            if (touchY > MenuActivity.screenSizeY - 200) {
//                popupFoodShopDialog = new Dialog(context);
//                showPopupActivitiesAmicloon();
            }


        } else if (action == MotionEvent.ACTION_MOVE) {

        } else if (action == MotionEvent.ACTION_UP) {

        }

        return true;
    }

    // ---------------------------------------------------------------------------------------------
    // MyMethods :

    private void showPopupActivitiesAmicloon() {
        popupFoodShopDialog.setContentView(R.layout.popup_info_le_saviez_vous_fruit);

        popupCancelImageView = popupFoodShopDialog.findViewById(R.id.popup_info_lesaviezvous_ImageViewCancel);
        popupCancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());
                popupFoodShopDialog.dismiss();
            }
        });

        // BuyButton :
        popupFoodStoreBuyButton = popupFoodShopDialog.findViewById(R.id.popup_info_lesaviezvous_buttonContinue);
        popupFoodStoreBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.ValidationSound.ordinal());
                popupFoodShopDialog.dismiss();

            }
        });

        popupFoodShopDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupFoodShopDialog.show();
    }

    private boolean isTouchIntersectRect(Rect rectangle) {

        return touchX > rectangle.left
                && touchX < rectangle.right
                && touchY > rectangle.top
                && touchY < rectangle.bottom;

    }
}
