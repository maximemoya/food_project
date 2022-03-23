package fr.maxime.mygame01.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;

import fr.maxime.mygame01.FoodListView.FoodDescriptionSimplifyV1;
import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.EmotionClass;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.MySpriteClass;
import fr.maxime.mygame01.R;

public class GameView_v2 extends View {

    private Context context;
    private Canvas canvas;
    private Handler handler;
    private Runnable runnable;
    private final long UPDATE_MILLISEC = 40L; // environ 25fps

    private float touchX = 0, touchY = 0;
    private int action;

    private Rect rectBackground, rectCancelButton, rectDroppingZone, rectBoutonAvanceRapide,
            rectBoutonStopStart;
    private Bitmap imgBackground, imgButtonCancel, imgDroppingZoneVide, imgDroppingZoneRemplie,
            imgButtonAvanceRapideIdle, imgButtonAvanceRapideOnClick,
            imgButtonStopStartIdle, imgButtonStopStartOnCLick,
            imgLoupeOeilIdle, imgLoupeOeilOnAction;

    private MySpriteClass mrLoupeOeil;
    private EmotionClass mrEmotion;

    private ArrayList<FoodClass> foodList = new ArrayList<>(TransitionClass.selectedFoodList);
    private ArrayList<FoodClass> foodListLevel1Droite = new ArrayList<>();
    private ArrayList<FoodClass> foodListLevel2Gauche = new ArrayList<>();
    private ArrayList<FoodClass> foodListSelected = new ArrayList<>();
    private ArrayList<FoodClass> foodListOnDropRelease = new ArrayList<>();
    private ArrayList<FoodClass> listeObjetOnDroppingZone = new ArrayList<>();

    private FoodClass foodOnTouch;
    private int indexOnTouch, indexTextListManager = 0, changeSpeed = 0;
    private boolean isFoodOnTouch = false,
            isBoutonAvanceRapideOnTouch = false,
            isBoutonStartStopOnTouch = false,
            isLoupeOeilShow = false;

    private final int speedUp = 60;
    private final float posXTextListManager = (float) MenuActivity.screenSizeX * 70 / 100;
    private final float posYTextListManager = (float) MenuActivity.screenSizeY * 5 / 100;

    private MediaPlayer mediaPlayerSound;

    private Paint paint = new Paint(), circlePaint = new Paint();

    enum enumIndexListManager {
        fibre, lipide, glucide, proteine;
    }

    String[] nameListManager;

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public GameView_v2(Context context) {
        super(context);

        this.context = context;

        nameListManager = new String[]{
                context.getString(R.string.text_fibre),
                context.getString(R.string.text_lipide),
                context.getString(R.string.text_glucide),
                context.getString(R.string.text_proteine)};

        // VERIF TRANSITION CLASS :
        if (TransitionClass.foodOnDropZoneList != null) {
            if (TransitionClass.foodOnDropZoneList.size() > 0) {
                listeObjetOnDroppingZone = TransitionClass.foodOnDropZoneList;

                for (FoodClass food : listeObjetOnDroppingZone) {
                    foodList.remove(food);
                    foodList.add(food);
                }

            }
        }


        // IMAGE ET BACKGROUND :

        // TEXT :
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(55);

        // bg et btn cancel :
        rectBackground = new Rect(0, 0, MenuActivity.screenSizeX, MenuActivity.screenSizeY);
        rectCancelButton = new Rect(15, 15, 150, 150);
        imgBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background_choose_food_game_v4);
        imgButtonCancel = BitmapFactory.decodeResource(getResources(), R.drawable.btn_cancel);

        // drop zone plate :
        imgDroppingZoneVide = BitmapFactory.decodeResource(getResources(), R.drawable.game_plat_vide);
        imgDroppingZoneRemplie = BitmapFactory.decodeResource(getResources(), R.drawable.game_plat_rempli);
        int rectDropZoneX = (MenuActivity.screenSizeX - imgDroppingZoneVide.getWidth()) / 2;
        int rectDropZoneY = (MenuActivity.screenSizeY - imgDroppingZoneVide.getHeight() - 50);
        rectDroppingZone = new Rect(rectDropZoneX, rectDropZoneY, rectDropZoneX + imgDroppingZoneVide.getWidth(), rectDropZoneY + imgDroppingZoneVide.getHeight());

        // btn speed up :
        rectBoutonAvanceRapide = new Rect(MenuActivity.screenSizeX * 70 / 100, MenuActivity.screenSizeY * 60 / 100, MenuActivity.screenSizeX * 80 / 100, MenuActivity.screenSizeY * 70 / 100);
        imgButtonAvanceRapideIdle = BitmapFactory.decodeResource(getResources(), R.drawable.game_avance_rapide_idle);
        imgButtonAvanceRapideOnClick = BitmapFactory.decodeResource(getResources(), R.drawable.game_avance_rapide_onclick);

        // btn start & stop :
        rectBoutonStopStart = new Rect(MenuActivity.screenSizeX * 50 / 100, MenuActivity.screenSizeY * 60 / 100, MenuActivity.screenSizeX * 60 / 100, MenuActivity.screenSizeY * 70 / 100);
        imgButtonStopStartIdle = BitmapFactory.decodeResource(getResources(), R.drawable.game_arret_play_button_idle);
        imgButtonStopStartOnCLick = BitmapFactory.decodeResource(getResources(), R.drawable.game_arret_play_button_onclick);

        // MrLoupeOeil :
        imgLoupeOeilIdle = BitmapFactory.decodeResource(getResources(), R.drawable.loupe_oeil);
        imgLoupeOeilOnAction = BitmapFactory.decodeResource(getResources(), R.drawable.loupe_oeil_on_action);
        ArrayList<Bitmap> imglistLoupeOeil = new ArrayList<>();
        imglistLoupeOeil.add(imgLoupeOeilIdle);
        imglistLoupeOeil.add(imgLoupeOeilOnAction);
        mrLoupeOeil = new MySpriteClass(context, imglistLoupeOeil, 0);
        mrLoupeOeil.setPosition(-500, -500);
        mrLoupeOeil.setOnTouch(false);

        // MrEmotion :
        mrEmotion = new EmotionClass(context, TransitionClass.myLib.bitmapEmotionList, TransitionClass.myLib.soundEmotionList, TransitionClass.myLib.nameEmotionList);
        mrEmotion.setPosition(-500, -500);

        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(context.getColor(R.color.quantityPerso));

        // TransitionClass :
        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.GAME.ordinal();

        mediaPlayerSound = MediaPlayer.create(context, R.raw.game_sound_start_stop);

        // FoodList :
        initCstFoodListLevelbyCategory();

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
        this.canvas = canvas;

        // Background :
        canvas.drawBitmap(imgBackground, null, rectBackground, null);

        // TEXTs :
        canvas.drawRect(800, 800, 40, 50, paint);
        canvas.drawText(foodListLevel2Gauche.size() + " aliments dans liste du haut", 50, 200, paint);
        canvas.drawText(foodListLevel1Droite.size() + " aliments dans liste du bas", 50, 300, paint);
        canvas.drawText(foodList.size() + " aliments au total", 50, 400, paint);
        canvas.drawText(foodListSelected.size() + " aliments selected", 50, 500, paint);
        canvas.drawText(listeObjetOnDroppingZone.size() + " aliments dans l'assiette", 50, 600, paint);
        canvas.drawText(foodListOnDropRelease.size() + " aliments released", 50, 700, paint);
//        if (listeObjetOnDroppingZone != null) {
//            for (int i = 0; i < listeObjetOnDroppingZone.size(); i++){
//
//                canvas.drawText(listeObjetOnDroppingZone.get(i).name + " = " + i, 50, 60 * i + 100, paint);
//            }
//        }

        canvas.drawText(" --> " + nameListManager[indexTextListManager], posXTextListManager, posYTextListManager, paint);

        // CancelButton :
        canvas.drawBitmap(imgButtonCancel, null, rectCancelButton, null);

        // DropZoneBg :
        if (listeObjetOnDroppingZone.size() > 0) {
            canvas.drawBitmap(imgDroppingZoneRemplie, null, rectDroppingZone, null);

            // affichage nombre food dans dropZone :
            if (listeObjetOnDroppingZone.size() < 10) {
                canvas.drawText(String.valueOf(listeObjetOnDroppingZone.size()), rectDroppingZone.centerX() - 15, rectDroppingZone.bottom - 60, paint);
            } else {
                canvas.drawText(String.valueOf(listeObjetOnDroppingZone.size()), rectDroppingZone.centerX() - 30, rectDroppingZone.bottom - 60, paint);
            }
        } else {
            canvas.drawBitmap(imgDroppingZoneVide, null, rectDroppingZone, null);
        }

        // Bouton avance rapide :
        if (isBoutonAvanceRapideOnTouch) {
            canvas.drawBitmap(imgButtonAvanceRapideOnClick, null, rectBoutonAvanceRapide, null);
        } else {
            canvas.drawBitmap(imgButtonAvanceRapideIdle, null, rectBoutonAvanceRapide, null);
        }

        // Bouton start / stop :
        if (isBoutonStartStopOnTouch) {
            canvas.drawBitmap(imgButtonStopStartOnCLick, null, rectBoutonStopStart, null);
            for (FoodClass food : foodList) {
                food.setOnIdlePosition(true);
            }
        } else {
            canvas.drawBitmap(imgButtonStopStartIdle, null, rectBoutonStopStart, null);
            for (FoodClass food : foodList) {
                food.setOnIdlePosition(false);
            }
        }

        // MrLoupeOeil :
        if (isLoupeOeilShow) {
            mrLoupeOeil.setPosition(-30, MenuActivity.screenSizeY * 83 / 100);

            if (mrLoupeOeil.getOnTouch()) {
                canvas.drawBitmap(mrLoupeOeil.bitmapArrayList.get(1), null, mrLoupeOeil.getRectImage(), null);
            } else {
                canvas.drawBitmap(mrLoupeOeil.bitmapArrayList.get(0), null, mrLoupeOeil.getRectImage(), null);
            }
        } else {
            mrLoupeOeil.setPosition(-500, -500);
        }

        // MrEmotion :
        if (listeObjetOnDroppingZone.size() > 0) {
            mrEmotion.setPosition(MenuActivity.screenSizeX - mrEmotion.getWidthMaxime() - 75, MenuActivity.screenSizeY * 80 / 100 - mrEmotion.getHeightMaxime());

            int radiusInit = 115;
            int radiusCalculate = radiusInit + (int) 10 * listeObjetOnDroppingZone.size() / 2;

            canvas.drawCircle(mrEmotion.getPositionX() + (float) mrEmotion.getWidthMaxime() / 2,
                    mrEmotion.getPositionY() + (float) mrEmotion.getHeightMaxime() - 10 + radiusCalculate,
                    radiusCalculate,
                    circlePaint);

            int textMangerPosX;

            if (TransitionClass.langageActual.equals(TransitionClass.languageArraySelector[TransitionClass.enumLanguageSelector.Anglais.ordinal()])) {
                textMangerPosX = mrEmotion.getPositionX();

            } else {
                textMangerPosX = mrEmotion.getPositionX() - (int) mrEmotion.getWidthMaxime() / 4;
            }

            if (listeObjetOnDroppingZone.size() > 10) {
                canvas.drawText(context.getString(R.string.manger_text),
                        textMangerPosX,
                        mrEmotion.getPositionY() + mrEmotion.getHeightMaxime() + 185,
                        paint);
            } else {
                canvas.drawText(context.getString(R.string.manger_text),
                        textMangerPosX,
                        mrEmotion.getPositionY() + mrEmotion.getHeightMaxime() + radiusCalculate,
                        paint);
            }

            canvas.drawBitmap(mrEmotion.getDrawAnimateImage(), null, mrEmotion.getRectImage(), null);

        } else {
            mrEmotion.setPosition(-500, -500);
        }

        // food :
        updateFoodOnDraw();

        // LOGIC PART to update display :
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
        action = event.getAction();

        // ACTIONS
        if (action == MotionEvent.ACTION_DOWN) {

            onActionDown();
        } else if (action == MotionEvent.ACTION_MOVE) {

            onActionMove();
        } else if (action == MotionEvent.ACTION_UP) {

            onActionUp();
        }

        return true;
    }

    // ------------
    // ACTIONDOWN :
    private void onActionDown() {

        // Bouton AVANCE RAPIDE :
        onTouchDownButtonSpeedUp();

        // Bouton START STOP :
        onTouchDownButtonStartStop();

        // Bouton CHANGE CATEGORY LIST MANAGER haut a droite :
        onTouchDownButtonCategoryListManager();

        // FOOD :
        onTouchDownFood();

    }

    //      -Bouton AVANCE RAPIDE :
    private void onTouchDownButtonSpeedUp() {
        if (isTouchIntersectRect(rectBoutonAvanceRapide)) {
            isBoutonAvanceRapideOnTouch = true;
            TransitionClass.playYourMediaPlayerSound(context, mediaPlayerSound, R.raw.game_sound_forward_begin);
        }
    }

    //      -Bouton START STOP :
    private void onTouchDownButtonStartStop() {
        if (isTouchIntersectRect(rectBoutonStopStart)) {

            if (!isBoutonStartStopOnTouch) {
                TransitionClass.playYourMediaPlayerSound(context, mediaPlayerSound, R.raw.game_sound_start_stop);
            } else {
                TransitionClass.playYourMediaPlayerSound(context, mediaPlayerSound, R.raw.game_sound_forward_begin);
            }

            isBoutonStartStopOnTouch = !isBoutonStartStopOnTouch;
        }
    }

    //      -Bouton CHANGE CATEGORY LIST MANAGER haut a droite :
    private void onTouchDownButtonCategoryListManager() {
        if (touchX > posXTextListManager && touchY < posYTextListManager) {

            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.ValidationSound.ordinal());

            indexTextListManager++;
            if (indexTextListManager == nameListManager.length) {
                indexTextListManager = 0;
            }

            listManagerOrganiser();

            for (FoodClass food : foodListLevel1Droite) {
                food.setIndexOnTemporaryList(foodListLevel1Droite.indexOf(food));
            }
            for (FoodClass food : foodListLevel2Gauche) {
                food.setIndexOnTemporaryList(foodListLevel2Gauche.indexOf(food));
            }
            resetPositionInitFoodList(foodListLevel1Droite, true);
            resetPositionInitFoodList(foodListLevel2Gauche, false);
        }
    }

    //      -FOOD :
    private void onTouchDownFood() {

        Collections.reverse(foodList);

        for (FoodClass food : foodList) {
            if (touchX > food.getPositionX() &&
                    touchX < food.getPositionX() + food.getWidthMaxime() &&
                    touchY > food.getPositionY() &&
                    touchY < food.getPositionY() + food.getHeightMaxime()) {

                int indexfood = foodList.indexOf(food);
                foodList.add(food);
                foodList.remove(indexfood);

                TransitionClass.foodstatic = food;

                // Show MrLoupeOeil :
                isLoupeOeilShow = true;

                food.setOnTouch(true);
                food.setOnClick(true);
                food.setOnIdlePosition(false);

                // Retire food de sa liste precedente :
                if (foodListLevel1Droite.contains(food)) {
                    foodListLevel1Droite.remove(food);
                    for (FoodClass food2 : foodListLevel1Droite) {
                        food2.setIndexOnTemporaryList(foodListLevel1Droite.indexOf(food2));
                    }
                } else if (foodListLevel2Gauche.contains(food)) {
                    foodListLevel2Gauche.remove(food);
                    for (FoodClass food2 : foodListLevel2Gauche) {
                        food2.setIndexOnTemporaryList(foodListLevel2Gauche.indexOf(food2));
                    }
                } else {
                    listeObjetOnDroppingZone.remove(food);
                    foodListOnDropRelease.remove(food);
                }

                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                Toast.makeText(context, food.name, Toast.LENGTH_SHORT).show();

//                    // retirer un aliment de la droppingZone :
//
//                    if (listeObjetOnDroppingZone.contains(food)) {
//                        listeObjetOnDroppingZone.remove(food);
//
//                        TransitionClass.foodOnDropZoneList = listeObjetOnDroppingZone;
//
//                        food.setPosition(MenuActivity.screenSizeX / 2, MenuActivity.screenSizeY / 2);
//                        food.setOnTouch(false);
//                        food.setOnDropZone(false);
//                        getEmotionRemoveFoodFromDropZone(food);
//                    }
                break;
            }
        }
        Collections.reverse(foodList);
    }

    // ------------
    // ACTIONMOVE :
    private void onActionMove() {

        // FOOD :
        onMoveFood();
    }

    //      -FOOD :
    private void onMoveFood() {
        for (FoodClass food : foodList) {

            if (food.getOnTouch()) {

                food.setPositionX((int) touchX - food.getWidthMaxime() / 2);
                food.setPositionY((int) touchY - food.getHeightMaxime() / 2);
                food.setRectImagePosition();

                // MrLoupeOeil :
                mrLoupeOeil.setOnTouch(Rect.intersects(food.getRectImage(), mrLoupeOeil.getRectImage()));

                // collision with same Class Objects :
//                    temporaryList = new ArrayList<>(foodList);
//                    temporaryList.remove(food);
////                    for (FoodClass food2 : temporaryList)
//                        if (Rect.intersects(food.getRectImage(), food2.getRectImage())) {
//                            if (food.getPositionX() + food.getWidthMaxime() / 2 <= food2.getPositionX()) {
//                                food2.setSideCollide(MySpriteClass.sideCollideEnum.LEFT.action());
//                            } else if (food.getPositionX() - food2.getWidthMaxime() / 2 >= food2.getPositionX()) {
//                                food2.setSideCollide(MySpriteClass.sideCollideEnum.RIGHT.action());
//                            } else if (food.getPositionY() + food.getHeightMaxime() / 2 <= food2.getPositionY()) {
//                                food2.setSideCollide(MySpriteClass.sideCollideEnum.UP.action());
//                            } else if (food.getPositionY() - food2.getHeightMaxime() / 2 >= food2.getPositionY()) {
//                                food2.setSideCollide(MySpriteClass.sideCollideEnum.DOWN.action());
//                            }
//                        }
            }
        }
    }

    // ----------
    // ACTIONUP :
    private void onActionUp() {

        // Bouton AVANCE RAPIDE :
        onTouchUpButtonSpeedUp();

        // Bouton CANCEL :
        onTouchUpCancelButton();

        // MrLoupeOeil :
        isLoupeOeilShow = false;

        // EAT :
        onTouchUpEAT();

        // FOOD :
        onTouchUpFood();

    }

    //      -Bouton AVANCE RAPIDE :
    private void onTouchUpButtonSpeedUp() {
        if (isBoutonAvanceRapideOnTouch) {
            isBoutonAvanceRapideOnTouch = false;
            TransitionClass.playYourMediaPlayerSound(context, mediaPlayerSound, R.raw.game_sound_forward_end);
            for (FoodClass food : foodList) {
                food.setImgSpeed(12);
            }
        }
    }

    //      -Bouton CANCEL :
    private void onTouchUpCancelButton() {
        if (isTouchIntersectRect(rectCancelButton)) {

            resetOnDroppingZoneFood();
            TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());

            Intent intent = new Intent(getContext(), GameMenuAmicloonActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getContext().startActivity(intent);
        }
    }

    //      -EAT :
    private void onTouchUpEAT(){
        if (listeObjetOnDroppingZone.size() > 0) {

            if(touchX>(float) MenuActivity.screenSizeX *75/100 && touchY > (float) MenuActivity.screenSizeY*75/100){

                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());

                for (FoodClass food : listeObjetOnDroppingZone) {
                    food.resetPosition();
                }
                TransitionClass.foodOnDropZoneList = listeObjetOnDroppingZone;
                TransitionClass.foodOnDropZonePositionArray = null;

                Intent intent = new Intent(getContext(), GameMenuAmicloonActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(intent);
            }

        }
    }

    //      -FOOD :
    private void onTouchUpFood() {
        for (FoodClass food : foodList) {

            if (food.getOnTouch()) {

                food.setOnTouch(false);
                changeSpeed = 2;

                // food dans MrLoupeOeil :
                if (Rect.intersects(food.getRectImage(), mrLoupeOeil.getRectImage())) {

                    positionOnDropZone(food);

                    TransitionClass.stockageFood(food);

                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());

                    Intent intent = new Intent(getContext(), FoodDescriptionSimplifyV1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(intent);

                }

                // food dans dropBox
                else if (Rect.intersects(food.getRectImage(), rectDroppingZone)) {
                    positionOnDropZone(food);

                }
                // food hors de drop zone
                else {

                    listeObjetOnDroppingZone.remove(food);
                    TransitionClass.foodOnDropZoneList = listeObjetOnDroppingZone;
                    food.setOnDropZone(false);
                    if (!foodListOnDropRelease.contains(food)) {
                        foodListOnDropRelease.add(food);
                    }
                }

            }

        }
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private void initCstFoodListLevelbyCategory() {

        for (FoodClass food : foodList) {

            food.resetPosition();
        }

        listManagerOrganiser();

        Collections.shuffle(foodListLevel1Droite);
        Collections.shuffle(foodListLevel2Gauche);

        for (FoodClass food : foodListLevel1Droite) {
            food.setIndexOnTemporaryList(foodListLevel1Droite.indexOf(food));
            food.setOnIdlePosition(false);
        }

        for (FoodClass food : foodListLevel2Gauche) {
            food.setIndexOnTemporaryList(foodListLevel2Gauche.indexOf(food));
            food.setOnIdlePosition(false);
        }

        resetPositionInitFoodList(foodListLevel1Droite, true);
        resetPositionInitFoodList(foodListLevel2Gauche, false);

    }

    private void resetPositionInitFoodList(ArrayList<FoodClass> foodlist, boolean goToRight) {
        if (goToRight) {
            // liste aliments deplacement vers la droite :
            for (FoodClass food : foodlist) {

                food.setGoingToRight(true);
                int index = foodlist.indexOf(food);
                // pair :
                if (index % 2 == 0) {
                    food.setPosition((200) - (food.getWidthMaxime() + 20) * index, (MenuActivity.screenSizeY * 35 / 100));
                }
                // impaire :
                else {
                    food.setPosition((200) - (food.getWidthMaxime() + 20) * index, (MenuActivity.screenSizeY * 35 / 100) + food.getHeightMaxime() / 2);
                }
                food.setOnIdlePosition(false);
            }
        } else {
            // liste aliments deplacement vers la gauche :
            for (FoodClass food : foodlist) {

                food.setGoingToRight(false);
                int index = foodlist.indexOf(food);
                // pair :
                if (index % 2 == 0) {
                    food.setPosition((MenuActivity.screenSizeX - 500) + (food.getWidthMaxime() + 20) * index, (MenuActivity.screenSizeY * 10 / 100));
                }
                // impaire :
                else {
                    food.setPosition((MenuActivity.screenSizeX - 500) + (food.getWidthMaxime() + 20) * index, (MenuActivity.screenSizeY * 10 / 100) + food.getHeightMaxime() / 2);
                }
                food.setOnIdlePosition(false);
            }
        }
    }

    private void updateFoodOnDraw() {

        // POUR IMGFOOD EN DEHORS DE L'ECRAN :
//        for (FoodClass food : foodList) {
//            if (food.getPositionX() < -food.getWidthMaxime() || food.getPositionX() > MenuActivity.screenSizeX) {
//
//                if (food.getPositionY() > -400) {
//                    petitPoucetFood(food);
//                }
//            }
//
//        }

        // AFFICHAGE Food dans DropZone :
        for (FoodClass food : listeObjetOnDroppingZone) {
            food.setPosition(MenuActivity.screenSizeX * 40 / 100, MenuActivity.screenSizeY * 72 / 100);
//            canvas.drawBitmap(food.getDrawAnimateImage(), null, food.getRectImage(), null);
        }

        // POUR IMGFOOD onTouching :
        for (FoodClass food : foodList) {

            if (food.getOnTouch()) {
                if (!foodListSelected.contains(food)) {
                    foodListSelected.add(food);
                    foodOnTouch = food;
                    indexOnTouch = foodList.indexOf(food);
                    isFoodOnTouch = true;
                }
                break;
            } else {
                foodListSelected.remove(food);

            }
        }

        // Ralentir quand on selectionne un aliment :
        if (isFoodOnTouch) {
            isFoodOnTouch = false;
            changeSpeed = 1;
            foodList.remove(indexOnTouch);
            foodList.add(foodOnTouch);
        }

        if (changeSpeed == 1){
            for (FoodClass food : foodList) {

                food.setImgSpeed(3);
            }
            changeSpeed = 0;
        }
        else if (changeSpeed == 2){
            for (FoodClass food : foodList) {

                food.setImgSpeed(12);
            }
            changeSpeed = 0;
        }


        // AVANCE RAPIDE quand bouton avance rapide est actif :
        if (isBoutonAvanceRapideOnTouch) {
            for (FoodClass food : foodList) {
                food.setImgSpeed(speedUp);
            }
        }

        // RESET POSITION :
        resetPositionFoodByFood(foodListLevel1Droite, true);
        resetPositionFoodByFood(foodListLevel2Gauche, false);

//        for (FoodClass food : foodList) {
//            if (!foodListLevel1Droite.contains(food) && !foodListLevel2Gauche.contains(food)) {
//                food.setPosition(-500, -500);
//                food.setOnIdlePosition(true);
//            }
//        }

        // AFFICHAGE Food General :
        for (FoodClass food : foodList) {
            petitPoucetFood(food);

            if (foodListOnDropRelease.size() > 0) {
                if (foodListOnDropRelease.contains(food)) {
                    if (food.getPositionX() > MenuActivity.screenSizeX || food.getPositionX() < -food.getWidthMaxime()) {
                        if (!listManagerOrganiserPetitPoucet(food)) {
                            food.setPosition(-500, -500);
                            food.setOnIdlePosition(true);
                        }
                        foodListOnDropRelease.remove(food);
                    }
                }
            }


            canvas.drawBitmap(food.getDrawAnimateImage(), food.getPositionX(), food.getPositionY(), null);
        }

    }

    private void resetPositionFoodByFood(ArrayList<FoodClass> foodlist, boolean goToRight) {

        if (goToRight) {
            // liste aliments deplacement vers la droite :

            for (FoodClass food : foodlist) {

                // condition est sorti par la droite ou est en attente en haut x = - 500 et y = -500;
                if (food.getPositionX() > MenuActivity.screenSizeX || food.getPositionY() < -400) {

                    // La liste d'image est plus grande que la largeur de l'ecran :
                    if (foodlist.size() * (food.getWidthMaxime()) > MenuActivity.screenSizeX) {

                        // pair :
                        if (food.getIndexOnTemporaryList() % 2 == 0) {
                            food.setPosition(foodlist.get(foodlist.size() - 1).getPositionX() - food.getWidthMaxime() - 20, (MenuActivity.screenSizeY * 35 / 100));
                        }
                        // impaire :
                        else {
                            food.setPosition(foodlist.get(foodlist.size() - 1).getPositionX() - food.getWidthMaxime() - 20, (MenuActivity.screenSizeY * 35 / 100) + food.getHeightMaxime() / 2);
                        }
                    }
                    // La liste d'image est plus petite que la largeur de l'ecran :
                    else {
                        // pair :
                        if (food.getIndexOnTemporaryList() % 2 == 0) {
                            food.setPosition(-(food.getWidthMaxime() + 20), (MenuActivity.screenSizeY * 35 / 100));
                        }
                        // impaire :
                        else {
                            food.setPosition(-(food.getWidthMaxime() + 20), (MenuActivity.screenSizeY * 35 / 100) + food.getHeightMaxime() / 2);
                        }
                    }

                    foodlist.remove(food);
                    foodlist.add(food);
                    food.setGoingToRight(true);

                    break;
                }

            }
        } else {
            // liste aliments deplacement vers la gauche :

            for (FoodClass food : foodlist) {

                // condition est sorti par la gauche ou est en attente en haut x = - 500 et y = -500;
                if (food.getPositionX() < -food.getWidthMaxime() || food.getPositionY() < -400) {

                    // La liste d'image est plus grande que la largeur de l'ecran :
                    if (foodlist.size() * (food.getWidthMaxime()) > MenuActivity.screenSizeX) {
                        // pair :
                        if (food.getIndexOnTemporaryList() % 2 == 0) {
                            food.setPosition(foodlist.get(foodlist.size() - 1).getPositionX() + food.getWidthMaxime() + 20, (MenuActivity.screenSizeY * 10 / 100));
                        }
                        // impaire :
                        else {
                            food.setPosition(foodlist.get(foodlist.size() - 1).getPositionX() + food.getWidthMaxime() + 20, (MenuActivity.screenSizeY * 10 / 100) + food.getHeightMaxime() / 2);
                        }
                    }
                    // La liste d'image est plus petite que la largeur de l'ecran :
                    else {
                        // pair :
                        if (food.getIndexOnTemporaryList() % 2 == 0) {
                            food.setPosition((MenuActivity.screenSizeX + 20), (MenuActivity.screenSizeY * 10 / 100));
                        }
                        // impaire :
                        else {
                            food.setPosition((MenuActivity.screenSizeX + 20), (MenuActivity.screenSizeY * 10 / 100) + food.getHeightMaxime() / 2);
                        }
                    }

                    foodlist.remove(food);
                    foodlist.add(food);
                    food.setGoingToRight(false);

                    break;
                }
            }
        }

    }

    private void petitPoucetFood(FoodClass food) {
        if (!foodListLevel1Droite.contains(food) && !foodListLevel2Gauche.contains(food)
                && !foodListSelected.contains(food) && !listeObjetOnDroppingZone.contains(food)
                && !foodListOnDropRelease.contains(food)) {

            if (!listManagerOrganiserPetitPoucet(food)) {
                food.setPosition(-500, -500);
                food.setOnIdlePosition(true);
            }

        }
    }

    private boolean isTouchIntersectRect(Rect rectangle) {

        return touchX > rectangle.left
                && touchX < rectangle.right
                && touchY > rectangle.top
                && touchY < rectangle.bottom;

    }

    private void positionOnDropZone(FoodClass food) {

        food.setOnDropZone(true);
        ondropZoneAddFood(food);


        // Emotion carre en action :

        if (food.categoryName.equals(context.getResources().getString(R.string.proteine))) {
            mrEmotion.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.INTERET.ordinal());
            mrEmotion.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.legumineux))) {
            mrEmotion.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.CONTENT1.ordinal());
            mrEmotion.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.fruit))) {
            mrEmotion.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.CONTENT2.ordinal());
            mrEmotion.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.feculent))) {
            mrEmotion.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.TAQUIN.ordinal());
            mrEmotion.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.sucrerie))) {
            mrEmotion.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.AMOUR.ordinal());
            mrEmotion.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.friture))) {
            mrEmotion.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.DEGOUTER.ordinal());
            mrEmotion.soundStart();
        } else {
            mrEmotion.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.MACHIAVELIQUE.ordinal());
            mrEmotion.soundStart();
        }

    }

    private void resetOnDroppingZoneFood() {
        for (FoodClass food : listeObjetOnDroppingZone) {
            food.resetPosition();
        }
        TransitionClass.foodOnDropZoneList.clear();
        TransitionClass.foodOnDropZonePositionArray = null;
    }

    private void listManagerOrganiser() {

        foodListLevel2Gauche.clear();
        foodListLevel2Gauche = new ArrayList<>();

        foodListLevel1Droite.clear();
        foodListLevel1Droite = new ArrayList<>();

        for (FoodClass food : foodList) {

            if (!listeObjetOnDroppingZone.contains(food)) {
                listManagerOrganiserPetitPoucet(food);
            }

        }

    }

    private boolean listManagerOrganiserPetitPoucet(FoodClass food) {

//        food.setOnIdlePosition(false);

        // LIPIDES :
        if (indexTextListManager == enumIndexListManager.lipide.ordinal()) {

            if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.condiment.index()
                    || food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.produit_laitier.index()) {

                if (!food.name.equals(context.getString(R.string.sucre))) {

                    return internMethodlistManagerOrganiserPetitPoucet(foodListLevel2Gauche, food);
                }


            } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.friture.index()
                    || food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.fromage.index()) {

                return internMethodlistManagerOrganiserPetitPoucet(foodListLevel1Droite, food);

            } else {
                return false;
            }

        }

        // GLUCIDES :
        else if (indexTextListManager == enumIndexListManager.glucide.ordinal()) {

            if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.feculent.index()
                    || food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.sucrerie.index()
                    || food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.cereales.index()
                    || food.name.equals(context.getString(R.string.sucre))) {

                return internMethodlistManagerOrganiserPetitPoucet(foodListLevel2Gauche, food);

            } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.boisson.index()
                    || food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.alcool.index()) {


                return internMethodlistManagerOrganiserPetitPoucet(foodListLevel1Droite, food);

            } else {
                return false;
            }

        }

        // PROTEINES :
        else if (indexTextListManager == enumIndexListManager.proteine.ordinal()) {

            if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.viande.index()
                    || food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.poisson.index()
                    || food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.oeuf.index()) {

                return internMethodlistManagerOrganiserPetitPoucet(foodListLevel2Gauche, food);

            } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.champignon.index()
                    || food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.fruitDeMer.index()) {

                return internMethodlistManagerOrganiserPetitPoucet(foodListLevel1Droite, food);

            } else {
                return false;
            }

        }

        // FIBRES ET AUTRES ALIMENTS :
        else {

            if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.legumineux.index()) {

                return internMethodlistManagerOrganiserPetitPoucet(foodListLevel2Gauche, food);

            } else if (food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.fruit.index()
                    || food.category == LibraryArrayBitmapDrawingRessources.categoryEnum.fruitSec.index()) {

                return internMethodlistManagerOrganiserPetitPoucet(foodListLevel1Droite, food);

            } else {
                return false;
            }
        }

        return false;

    }

    private boolean internMethodlistManagerOrganiserPetitPoucet(ArrayList<FoodClass> foodlistToAddFood, FoodClass food) {

        if (foodlistToAddFood.size() > food.getIndexOnTemporaryList()) {
            foodlistToAddFood.add(food.getIndexOnTemporaryList(), food);
        } else {
            foodlistToAddFood.add(0, food);
        }

        for (FoodClass food2 : foodlistToAddFood) {
            food2.setIndexOnTemporaryList(foodlistToAddFood.indexOf(food2));
        }

        return true;

    }

    private void ondropZoneAddFood(FoodClass food) {
        if (!listeObjetOnDroppingZone.contains(food)) {
            listeObjetOnDroppingZone.add(food);
        }
        TransitionClass.foodOnDropZoneList = listeObjetOnDroppingZone;
    }

    private Double[] calculateDoubleTotalElementsFood(){

        for (FoodClass food : listeObjetOnDroppingZone){
            food.setQuantity(FoodClass.enumQuantityChosenAmount.MEDIUM.ordinal());
        }

        return FoodClass.getCalculateTotalDoubleValueByElementOfAFoodList(listeObjetOnDroppingZone);
    }

}
