package fr.maxime.mygame01.MyObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaPlayer;

import fr.maxime.mygame01.R;

public class AmicloonSprite {

    private Context context;

    private float posX, posY, width, height;
    private int imgFrame;
    public boolean isOnScreen, isOnTouch, isOnResetPosition, isAnimated;

    private Rect rectImage;
    private Bitmap imageBitmap;
    private int imageRawIntDrawable;

    // ANIMATION :
    public int animationEnumINT = 0;
    public enum enumAnimation {
        neutre, content;
    }

    private int animationContentVar = 0;


    // LIBRAIRIE :

        // IMAGE :
    private final int[] rawIntDrawableAmicloon = {R.drawable.amicloon_neutral,
            R.drawable.amicloon_happy_left, R.drawable.amicloon_happy_right};
    public enum enumRawIntDrawable {
        neutre, contentGauche, contentDroit;
    }

        // SON :
    private final int[] rawIntSoundAmicloon = {R.raw.emotion_machiavelique};
    public enum enumRawIntSound {
        amical;
    }

    // ---------------------------------------------------------------------------------------------
    // Constructor :
    public AmicloonSprite(Context context) {

        this.context = context;
        setImageAmicloon(enumRawIntDrawable.neutre.ordinal());
        resetPosition();

    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    // --- ANIMATION :
    public Bitmap animationNeutre() {

        isAnimated = false;
        setImageAmicloon(enumRawIntDrawable.neutre.ordinal());
        return imageBitmap;

    }

    public Bitmap animationContent() {

        if (animationContentVar == 0) {

            isAnimated = true;
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.SharedSound.ordinal());
            setImageAmicloon(enumRawIntDrawable.neutre.ordinal());
            animationContentVar++;

        } else if (animationContentVar <= 3) {

            setImageAmicloon(enumRawIntDrawable.contentGauche.ordinal());
            animationContentVar++;

        } else if (animationContentVar <= 4) {

            setImageAmicloon(enumRawIntDrawable.neutre.ordinal());
            animationContentVar++;

        } else if (animationContentVar <= 6) {

            setImageAmicloon(enumRawIntDrawable.contentDroit.ordinal());
            animationContentVar++;

        } else if (animationContentVar <= 7) {

            setImageAmicloon(enumRawIntDrawable.neutre.ordinal());
            animationContentVar++;

        } else if (animationContentVar <= 9) {

            setImageAmicloon(enumRawIntDrawable.contentGauche.ordinal());
            animationContentVar++;

        } else if (animationContentVar <= 10) {

            setImageAmicloon(enumRawIntDrawable.neutre.ordinal());
            animationContentVar++;

        } else if (animationContentVar <= 12) {

            setImageAmicloon(enumRawIntDrawable.contentDroit.ordinal());
            animationContentVar++;

        } else{
            setImageAmicloon(enumRawIntDrawable.neutre.ordinal());
            animationEnumINT = enumAnimation.neutre.ordinal();
            animationContentVar = 0;
            isAnimated = false;
        }
        return imageBitmap;

    }

    // ---

    public void resetPosition() {
        this.posX = -100 - width;
        this.posY = -100 - height;
        isOnScreen = false;
        isOnTouch = false;
        isAnimated = false;
        isOnResetPosition = true;
        imgFrame = 0;
        setRectImagePosition();
    }

    public void resetPosition(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        isOnScreen = false;
        isOnTouch = false;
        isAnimated = false;
        isOnResetPosition = true;
        imgFrame = 0;
        setRectImagePosition();
    }

    public void setRectImagePosition() {
        float rectWidth = posX + width;
        float rectHeight = posY + height;
        rectImage = new Rect((int) posX, (int) posY, (int) rectWidth, (int) rectHeight);
    }

    public float[] getArrayPositionXY() {
        float[] array = new float[2];
        array[0] = posX;
        array[1] = posY;

        return array;
    }

    public void setPosition(float x, float y) {
        posX = x;
        posY = y;
        setRectImagePosition();
    }

    public void setImageAmicloon(int enumRawIntDrawable) {

        imageRawIntDrawable = enumRawIntDrawable;
        imageBitmap = BitmapFactory.decodeResource(context.getResources(), rawIntDrawableAmicloon[enumRawIntDrawable]);
        width = imageBitmap.getWidth();
        height = imageBitmap.getHeight();

    }

    // ---

    public Rect getRect() {
        return rectImage;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

}
