package fr.maxime.mygame01.MyObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;

import java.io.Serializable;
import java.util.ArrayList;

import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MySpriteClass;

public class EmotionClass extends MySpriteClass implements Serializable {

    private int emotionState;
    private ArrayList<Integer> rawImageEmotionList = new ArrayList<>();
    private ArrayList<Integer> soundEmotionList = new ArrayList<>();
    private ArrayList<String> nameEmotionList = new ArrayList<>();

    public float moveX = 0;
    public float moveY = 0;
    private float velocity = (float) 100.0;
    private String name;

    private float touchXstockage;
    private float touchYstockage;

    private float posXCenter;
    private float posYCenter;


    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public EmotionClass(Context context) {
        super(context, TransitionClass.myLib.bitmapEmotionList, LibraryArrayBitmapDrawingRessources.emotionEnum.NEUTRE.ordinal());
        rawImageEmotionList = TransitionClass.myLib.rawBitmapImageEmotionList;
        soundEmotionList = TransitionClass.myLib.soundEmotionList;
        nameEmotionList = TransitionClass.myLib.nameEmotionList;

        emotionState = LibraryArrayBitmapDrawingRessources.emotionEnum.NEUTRE.ordinal();
        TransitionClass.soundEmotion = MediaPlayer.create(context, soundEmotionList.get(0));
    }

    public EmotionClass(Context context, ArrayList<Bitmap> bitmapArrayList, ArrayList<Integer> soundEmotionList, ArrayList<String> nameEmotionList) {
        super(context, bitmapArrayList, LibraryArrayBitmapDrawingRessources.emotionEnum.NEUTRE.ordinal());
        this.soundEmotionList = soundEmotionList;
        this.nameEmotionList = nameEmotionList;
        emotionState = LibraryArrayBitmapDrawingRessources.emotionEnum.NEUTRE.ordinal();
        TransitionClass.soundEmotion = MediaPlayer.create(context, soundEmotionList.get(0));
    }

    public Bitmap getDrawAnimateImage() {
        setRectImagePosition();
        return bitmapArrayList.get(emotionState);
    }

    // ---------------------------------------------------------------------------------------------
    // My methods :

    public void move() {
        if (    posXCenter < touchXstockage + velocity/2 &&
                posXCenter > touchXstockage - velocity/2 &&
                posYCenter < touchYstockage + velocity/2 &&
                posYCenter >touchYstockage - velocity/2) {

        } else {
            positionX += moveX;
            positionY += moveY;
            posXCenter = positionX + (float) (width / 2);
            posYCenter = positionY + (float) (height / 2);
        }

    }

    public void moveByTouchingScreen(float touchX, float touchY) {

        touchXstockage = touchX;
        touchYstockage = touchY;

        posXCenter = positionX + (float) (width / 2);
        posYCenter = positionY + (float) (height / 2);

        float deltaX = touchX - (float) posXCenter;
        float deltaY = touchY - (float) posYCenter;

        if(deltaX == 0){
            deltaX += 0.1;
        }
        else if (deltaX < 1 && deltaX > -1) {
            deltaX = deltaX / Math.abs(deltaX);
        }

        if (deltaY == 0 ){
            deltaY += 0.1;
        }
        else if (deltaY < 1 && deltaY > -1) {
            deltaY = deltaY / Math.abs(deltaY);
        }

        float distance = (float) Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));

        moveX = (deltaX/distance) * velocity;
        moveY = (deltaY/distance) * velocity;

    }

    public void soundStart() {
        if (TransitionClass.soundEmotion != null) {
            TransitionClass.soundEmotion.start();
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Getter / Setter :

    public int getEmotionRawIntImage(){
        return rawImageEmotionList.get(emotionState);
    }

    public int getEmotionState() {
        return emotionState;
    }

    public void setEmotionState(int indexEmotion) {
        emotionState = indexEmotion;
        if (soundEmotionList.get(emotionState) > 0) {
            if (TransitionClass.soundEmotion != null) {
                TransitionClass.soundEmotion.stop();
                TransitionClass.soundEmotion.reset();
                TransitionClass.soundEmotion.release();
            }
            TransitionClass.soundEmotion = MediaPlayer.create(context, soundEmotionList.get(emotionState));

        } else {
            TransitionClass.soundEmotion.stop();
            TransitionClass.soundEmotion.reset();
            TransitionClass.soundEmotion.release();
            TransitionClass.soundEmotion = null;
        }

        name = nameEmotionList.get(indexEmotion);
    }

}
