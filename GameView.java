package fr.maxime.mygame01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

import fr.maxime.mygame01.FoodListView.FoodDescriptionV2;
import fr.maxime.mygame01.MenuView.MenuActivity;
import fr.maxime.mygame01.MyObjects.EmotionClass;
import fr.maxime.mygame01.MyObjects.FoodClass;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.ResultView.ResultViewActivity;

public class GameView extends View {

    public Context context;
    public Canvas canvas;
    public Handler handler;
    public Runnable runnable;
    public final long UPDATE_MILLISEC = 40L; // environ 25fps

    public LibraryArrayBitmapDrawingRessources myLib; // librairie donnees

    public static int screenSizeX;
    public static int screenSizeY;
    public Rect rectBackground, rectDroppingZone;
    public Bitmap imgBackground, imgloupeOeil, imgBackArrow;

    public ArrayList<FoodClass> listeObjetCollisionable;
    public ArrayList<FoodClass> listeFruitLegume = new ArrayList<>();
    public ArrayList<FoodClass> listeAutresAliments = new ArrayList<>();

    public ArrayList<FoodClass> listeObjetOnDroppingZone = new ArrayList<>();

    public int[] listePositionObjectOnDroppingZone = {0, 0, 0, 0, 0};

    public int index1, index2, index3, index4, index11, index22, index33, index44, indexOnTouch;
    public FoodClass foodIndex1, foodIndex2, foodIndex3, foodIndex4, foodOnTouch;
    public boolean isFoodOnTouch = false;

    public MySpriteClass loupeOeil;

    public EmotionClass character1;

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public GameView(Context context) {
        super(context);
        this.context = context;

        screenSizeX = MenuActivity.screenSizeX;
        screenSizeY = MenuActivity.screenSizeY;

        rectBackground = new Rect(0, 0, screenSizeX, screenSizeY);
        rectDroppingZone = new Rect(0, screenSizeY - (screenSizeY / 4), screenSizeX, screenSizeY);
        imgBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background01_v2);

        imgBackArrow = BitmapFactory.decodeResource(getResources(), R.drawable.fleche_retour_v2);

        // TransitionClass :

        TransitionClass.activityToBackUpByCancel = LibraryArrayBitmapDrawingRessources.enumActivityBackUpByCancel.GAME.ordinal();
        TransitionClass.setMusic(context, R.raw.music_intro);

        // Objects

        // LoupeOeil
        imgloupeOeil = BitmapFactory.decodeResource(getResources(), R.drawable.loupe_oeil);
        loupeOeil = new MySpriteClass(context, imgloupeOeil) {
            public Bitmap getDrawAnimateImage() {
                return imgloupeOeil;
            }
        };
        loupeOeil.setPosition(loupeOeil.width / 4, loupeOeil.height / 4);

        myLib = TransitionClass.myLib;

        // food :
        listeObjetCollisionable = TransitionClass.selectedFoodList;

//        Collections.sort(listeObjetCollisionable, FoodClass.comparatorCategory);

        for (FoodClass food : listeObjetCollisionable) {
            if (food.category <= 2) {
                listeFruitLegume.add(food);
                food.resetPosition();
                food.setGoingToRight(true);

            } else {
                listeAutresAliments.add(food);
                food.resetPosition();
                food.setGoingToRight(false);
            }

        }
        Collections.shuffle(listeAutresAliments);
        Collections.shuffle(listeFruitLegume);

        resetPositionFoodList(listeAutresAliments, false);
        resetPositionFoodList(listeFruitLegume, true);

        if (TransitionClass.foodstatic != null) {
            for (FoodClass food : listeObjetCollisionable) {

                if (food.name.equals(TransitionClass.foodstatic.name)) {
                    food.setPosition(screenSizeX / 2, 50);
                    food.imgSpeed = 8;
                }
            }
        }

        // dropZoneFoodList :

        if (TransitionClass.foodOnDropZoneList != null) {
            listeObjetOnDroppingZone = TransitionClass.foodOnDropZoneList;
        }
        if (TransitionClass.foodOnDropZonePositionArray != null) {
            listePositionObjectOnDroppingZone = TransitionClass.foodOnDropZonePositionArray;
        }
        if (listeObjetOnDroppingZone.size() > 0) {
            for (FoodClass food : listeObjetOnDroppingZone) {
                if (food.indexTemporary == 0) {
                    food.setPosition(screenSizeX * 200 / 1080, screenSizeY * 1360 / 1920);
                    listePositionObjectOnDroppingZone[0] = 1;
                    index11 = 1;

                } else if (food.indexTemporary == 1) {
                    food.setPosition(screenSizeX * 700 / 1080, screenSizeY * 1360 / 1920);
                    listePositionObjectOnDroppingZone[1] = 1;
                    index22 = 1;

                } else if (food.indexTemporary == 2) {
                    food.setPosition(screenSizeX * 450 / 1080, screenSizeY * 1480 / 1920);
                    listePositionObjectOnDroppingZone[2] = 1;

                } else if (food.indexTemporary == 3) {
                    food.setPosition(screenSizeX * 180 / 1080, screenSizeY * 1580 / 1920);
                    listePositionObjectOnDroppingZone[3] = 1;
                    index33 = 1;

                } else if (food.indexTemporary == 4) {
                    food.setPosition(screenSizeX * 730 / 1080, screenSizeY * 1580 / 1920);
                    listePositionObjectOnDroppingZone[4] = 1;
                    index44 = 1;
                }
                food.setOnDropZone(true);
            }
        }

        // character1 :
        character1 = new EmotionClass(context, myLib.bitmapEmotionList, myLib.soundEmotionList, myLib.nameEmotionList);
        character1.setPosition(screenSizeX * 13 / 100, (screenSizeY * 65 / 100));

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
        canvas.drawBitmap(imgBackArrow, screenSizeX - imgBackArrow.getWidth() - 10, 0, null);

        // loupeOeil :
        canvas.drawBitmap(imgloupeOeil, (float) loupeOeil.positionX, (float) loupeOeil.positionY, null);
        loupeOeil.setRectImagePosition();

        // character1 :
        canvas.drawBitmap(character1.getDrawAnimateImage(), character1.positionX, character1.positionY, null);

        // food :
        for (FoodClass food : listeObjetCollisionable) {
            canvas.drawBitmap(food.getDrawAnimateImage(), food.positionX, food.positionY, null);
        }
        for (FoodClass food : listeFruitLegume) {
            if (food.isOnIdlePosition()) {
                int index = listeFruitLegume.indexOf(food);
                // pair :
                if (index % 2 == 0) {
                    food.setPosition((200) - (food.width + 20) * (listeFruitLegume.size() - 3), 800);
                }
                // impaire :
                else {
                    food.setPosition((200) - (food.width + 20) * (listeFruitLegume.size() - 3), 800 + food.height / 2);
                }
                food.setOnIdlePosition(false);
            }
        }
        for (FoodClass food : listeAutresAliments) {
            if (food.isOnIdlePosition()) {
                int index = listeAutresAliments.indexOf(food);
                // pair :
                if (index % 2 == 0) {
                    food.setPosition((screenSizeX - 500) + (food.width + 20) * (listeAutresAliments.size() - 3), 300);
                }
                // impaire :
                else {
                    food.setPosition((screenSizeX - 500) + (food.width + 20) * (listeAutresAliments.size() - 3), 300 + food.height / 2);
                }
                food.setOnIdlePosition(false);
            }
        }

        // food onTouching :
        for (FoodClass food : listeObjetCollisionable) {

            if (food.isOnTouch) {
                foodOnTouch = food;
                indexOnTouch = listeObjetCollisionable.indexOf(food);
                isFoodOnTouch = true;
            }
        }

        // Ordre affichage selon position ou OnTouch

        if (index11 == 1) {
            for (FoodClass food : listeObjetCollisionable) {
                if (food.indexTemporary == 0) {
                    index1 = listeObjetCollisionable.indexOf(food);
                    foodIndex1 = food;
                }
            }

            listeObjetCollisionable.remove(index1);
            listeObjetCollisionable.add(0, foodIndex1);

            index11 = 0;
        }
        if (index22 == 1) {
            for (FoodClass food : listeObjetCollisionable) {
                if (food.indexTemporary == 1) {
                    index2 = listeObjetCollisionable.indexOf(food);
                    foodIndex2 = food;
                }
            }

            listeObjetCollisionable.remove(index2);
            listeObjetCollisionable.add(0, foodIndex2);

            index22 = 0;
        }
        if (index33 == 1) {
            for (FoodClass food : listeObjetCollisionable) {
                if (food.indexTemporary == 3) {
                    index3 = listeObjetCollisionable.indexOf(food);
                    foodIndex3 = food;
                }
            }

            listeObjetCollisionable.remove(index3);
            listeObjetCollisionable.add(foodIndex3);

            index33 = 0;
        }
        if (index44 == 1) {
            for (FoodClass food : listeObjetCollisionable) {
                if (food.indexTemporary == 4) {
                    index4 = listeObjetCollisionable.indexOf(food);
                    foodIndex4 = food;
                }
            }
            listeObjetCollisionable.remove(index4);
            listeObjetCollisionable.add(foodIndex4);

            index44 = 0;
        }

        if (isFoodOnTouch) {
            listeObjetCollisionable.remove(indexOnTouch);
            listeObjetCollisionable.add(foodOnTouch);
            isFoodOnTouch = false;
        }


        // LOGIC PART

        handler.postDelayed(runnable, UPDATE_MILLISEC);
    }

    // ---------------------------------------------------------------------------------------------
    // Partie Interractions :

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();
        int action = event.getAction();

        ArrayList<FoodClass> temporaryList;

        // ACTIONS
        if (action == MotionEvent.ACTION_DOWN) {

            //food
            Collections.reverse(listeObjetCollisionable);
            for (FoodClass food : listeObjetCollisionable) {
                if (touchX > food.getPositionX() &&
                        touchX < food.getPositionX() + food.width &&
                        touchY > food.getPositionY() &&
                        touchY < food.getPositionY() + food.height) {

                    food.setOnTouch(true);
                    food.setOnClick(true);
                    food.setOnIdlePosition(false);

                    TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                    Toast.makeText(context, "" + food.name, Toast.LENGTH_SHORT).show();

                    // retirer un aliment de la droppingZone :

                    if (listeObjetOnDroppingZone.contains(food)) {
                        listePositionObjectOnDroppingZone[food.indexTemporary] = 0;
                        listeObjetOnDroppingZone.remove(food);

                        TransitionClass.foodOnDropZoneList = listeObjetOnDroppingZone;

                        food.indexTemporary = 0;
                        food.setPosition(screenSizeX / 2, screenSizeY / 2);
                        food.setOnTouch(false);
                        food.setOnDropZone(false);
                        getEmotionRemoveFoodFromDropZone(food);
                    }
                    break;
                }
            }
            Collections.reverse(listeObjetCollisionable);

        }

        else if (action == MotionEvent.ACTION_MOVE) {

            //food
            for (FoodClass food : listeObjetCollisionable) {

                if (food.getOnTouch()) {

                    food.setPositionX((int) touchX - food.width / 2);
                    food.setPositionY((int) touchY - food.height / 2);
                    food.setRectImagePosition();

                    temporaryList = new ArrayList<>(listeObjetCollisionable);
                    temporaryList.remove(food);

                    // collision with same Class Objects
                    for (FoodClass food2 : temporaryList)
                        if (Rect.intersects(food.rectImage, food2.rectImage)) {
                            if (food.positionX + food.width / 2 <= food2.positionX) {
                                food2.sideCollide = MySpriteClass.sideCollideEnum.LEFT.action();
                            } else if (food.positionX - food2.width / 2 >= food2.positionX) {
                                food2.sideCollide = MySpriteClass.sideCollideEnum.RIGHT.action();
                            } else if (food.positionY + food.height / 2 <= food2.positionY) {
                                food2.sideCollide = MySpriteClass.sideCollideEnum.UP.action();
                            } else if (food.positionY - food2.height / 2 >= food2.positionY) {
                                food2.sideCollide = MySpriteClass.sideCollideEnum.DOWN.action();
                            }
                        }
                }
            }

        }

        else if (action == MotionEvent.ACTION_UP) {

            //food
            for (FoodClass food : listeObjetCollisionable) {

                if (food.getOnTouch()) {

                    food.setOnTouch(false);

                    // food dans loupeOeil TRANSITION ECRAN
                    if (Rect.intersects(food.rectImage, loupeOeil.rectImage)) {

                        TransitionClass.stockageFood(food);

                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());

                        Intent intent = new Intent(getContext(), FoodDescriptionV2.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        getContext().startActivity(intent);

                    }

                    // food dans dropBox
                    if (Rect.intersects(food.rectImage, rectDroppingZone)) {
                        positionOnDropZone(food);

                        // POUR TRIER LA LISTE
                        Collections.sort(listeObjetOnDroppingZone, FoodClass.comparatorCategoryName.thenComparing(FoodClass.comparatorGlucide));

                    }
                    return true;
                }

            }

            // touch EmotionSquare :
            if (character1.touchIt(touchX, touchY)) {

                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.RightClick.ordinal());

                Intent intent = new Intent(getContext(), ResultViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(intent);


//                if (character1.getEmotionState() < character1.bitmapArrayList.size() - 1) {
//                    character1.setEmotionState(character1.getEmotionState() + 1);
//                } else {
//                    character1.setEmotionState(0);
//                }
//                character1.soundStart();
            }

            // Touch BackButton :
            if (touchX > (float) MenuActivity.screenSizeX * 3 / 4 && touchY < (float) MenuActivity.screenSizeY / 5) {

                TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.WrongClick.ordinal());

                resetOnDroppingZoneFood();

                Intent intent = new Intent(getContext(), MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(intent);
            }

        }

        return true;
    }

    public void positionOnDropZone(FoodClass food) {

        if (listeObjetOnDroppingZone.size() < 5) {

            if (listePositionObjectOnDroppingZone[0] == 0) {
                food.setPosition(screenSizeX * 200 / 1080, screenSizeY * 1360 / 1920);
                listePositionObjectOnDroppingZone[0] = 1;
                food.indexTemporary = 0;
                index11 = 1;

            } else if (listePositionObjectOnDroppingZone[1] == 0) {
                food.setPosition(screenSizeX * 700 / 1080, screenSizeY * 1360 / 1920);
                listePositionObjectOnDroppingZone[1] = 1;
                food.indexTemporary = 1;
                index22 = 1;

            } else if (listePositionObjectOnDroppingZone[2] == 0) {
                food.setPosition(screenSizeX * 450 / 1080, screenSizeY * 1480 / 1920);
                listePositionObjectOnDroppingZone[2] = 1;
                food.indexTemporary = 2;

            } else if (listePositionObjectOnDroppingZone[3] == 0) {
                food.setPosition(screenSizeX * 180 / 1080, screenSizeY * 1580 / 1920);
                listePositionObjectOnDroppingZone[3] = 1;
                food.indexTemporary = 3;
                index33 = 1;

            } else if (listePositionObjectOnDroppingZone[4] == 0) {
                food.setPosition(screenSizeX * 730 / 1080, screenSizeY * 1580 / 1920);
                listePositionObjectOnDroppingZone[4] = 1;
                food.indexTemporary = 4;
                index44 = 1;
            }

            food.setOnDropZone(true);
            listeObjetOnDroppingZone.add(food);
            Collections.sort(listeObjetOnDroppingZone, FoodClass.comparatorIndexTemporary);

            TransitionClass.foodOnDropZonePositionArray = listePositionObjectOnDroppingZone;
            TransitionClass.foodOnDropZoneList = listeObjetOnDroppingZone;
            Toast.makeText(context, " size : " + TransitionClass.foodOnDropZoneList.size(), Toast.LENGTH_SHORT).show();

            // Emotion carre en action :

            if (food.categoryName.equals(context.getResources().getString(R.string.proteine))) {
                character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.INTERET.ordinal());
                character1.soundStart();
            } else if (food.categoryName.equals(context.getResources().getString(R.string.legumineux))) {
                character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.CONTENT1.ordinal());
                character1.soundStart();
            } else if (food.categoryName.equals(context.getResources().getString(R.string.fruit))) {
                character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.CONTENT2.ordinal());
                character1.soundStart();
            } else if (food.categoryName.equals(context.getResources().getString(R.string.feculent))) {
                character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.TAQUIN.ordinal());
                character1.soundStart();
            } else if (food.categoryName.equals(context.getResources().getString(R.string.sucrerie))) {
                character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.AMOUR.ordinal());
                character1.soundStart();
            } else if (food.categoryName.equals(context.getResources().getString(R.string.friture))) {
                character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.DEGOUTER.ordinal());
                character1.soundStart();
            } else {
                character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.MACHIAVELIQUE.ordinal());
                character1.soundStart();
            }

        } else {
            food.setPosition(screenSizeX / 2, screenSizeY / 4);
            character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.DEGOUTER.ordinal());
            character1.soundStart();

        }

    }

    public void resetOnDroppingZoneFood() {
        for (FoodClass food : listeObjetOnDroppingZone) {
            food.indexTemporary = 0;
            food.resetPosition();
        }
        TransitionClass.foodOnDropZoneList = null;
        TransitionClass.foodOnDropZonePositionArray = null;
    }

    public void getEmotionRemoveFoodFromDropZone(FoodClass food) {
        if (food.categoryName.equals(context.getResources().getString(R.string.proteine))) {
            character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.DECEPTION.ordinal());
            character1.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.legumineux))) {
            character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.VEXER.ordinal());
            character1.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.fruit))) {
            character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.TRISTESSE.ordinal());
            character1.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.feculent))) {
            character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.STRESS.ordinal());
            character1.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.sucrerie))) {
            character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.COLERE.ordinal());
            character1.soundStart();
        } else if (food.categoryName.equals(context.getResources().getString(R.string.friture))) {
            character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.CONTENT1.ordinal());
            character1.soundStart();
        } else {
            character1.setEmotionState(LibraryArrayBitmapDrawingRessources.emotionEnum.DECEPTION.ordinal());
            character1.soundStart();
        }
    }

    public void resetPositionFoodList(ArrayList<FoodClass> foodlist, boolean goToRight) {
        if (goToRight) {
            // liste aliments deplacement vers la droite :
            for (FoodClass food : foodlist) {
                food.setGoingToRight(true);
                int index = foodlist.indexOf(food);
                // pair :
                if (index % 2 == 0) {
                    food.setPosition((200) - (food.width + 20) * index, 700);
                }
                // impaire :
                else {
                    food.setPosition((200) - (food.width + 20) * index, 700 + food.height / 2);
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
                    food.setPosition((screenSizeX - 500) + (food.width + 20) * index, 300);
                }
                // impaire :
                else {
                    food.setPosition((screenSizeX - 500) + (food.width + 20) * index, 300 + food.height / 2);
                }
                food.setOnIdlePosition(false);
            }
        }


    }

    public void drawingFoodFromList(ArrayList<FoodClass> foodList) {

        // ATTENTION PAS TERMINEE !!!
        // NE PAS UTILISER
        // DANGER /!\ --------------------------------------


        for (FoodClass food : foodList) {
            canvas.drawBitmap(food.getDrawAnimateImage(), food.positionX, food.positionY, null);
            if (food.isOnIdlePosition()) {
                // Si deplacement vers la droite :
                if (food.isGoingToRight()) {
                    int index = foodList.indexOf(food);
                    // FruitLegume :
                    if (index < 50) {
                        // pair :
                        if (index % 2 == 0) {
                            food.setPosition((200) - (food.width + 20) * (foodList.size() - 3), 800);
                        }
                        // impaire :
                        else {
                            food.setPosition((200) - (food.width + 20) * (foodList.size() - 3), 800 + food.height / 2);
                        }
                        food.setOnIdlePosition(false);
                    }
                    // AutresAliments :
                    else {
                        // pair :
                        if (index % 2 == 0) {
                            food.setPosition((200) - (food.width + 20) * (foodList.size() - 3), 800);
                        }
                        // impaire :
                        else {
                            food.setPosition((200) - (food.width + 20) * (foodList.size() - 3), 800 + food.height / 2);
                        }
                        food.setOnIdlePosition(false);
                    }


                }
                // Si deplacement vers la gauche :
                else {
                    int index = foodList.indexOf(food);
                    // pair :
                    if (index % 2 == 0) {
                        food.setPosition((screenSizeX - 500) + (food.width + 20) * (foodList.size() - 3), 300);
                    }
                    // impaire :
                    else {
                        food.setPosition((screenSizeX - 500) + (food.width + 20) * (foodList.size() - 3), 300 + food.height / 2);
                    }
                    food.setOnIdlePosition(false);
                }

            }
        }


    }

}



