package fr.maxime.mygame01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import fr.maxime.mygame01.MenuView.MenuActivity;

public class MySpriteClass extends androidx.appcompat.widget.AppCompatImageView{

    protected Context context;

    protected int positionX = 200;
    protected int positionY = 200;
    protected int screenWidth = MenuActivity.screenSizeX;
    protected int screenHeight = MenuActivity.screenSizeY;
    protected int width;
    protected int height;
    protected int sideCollide = 0;
    protected int indexTemporary = 9;

    public enum sideCollideEnum{
        LEFT, UP, RIGHT, DOWN;

        public int action(){
            switch (this){
                case LEFT: return 1;
                case UP: return 2;
                case RIGHT:return 3;
                case DOWN: return 4;
                default: return 0;
            }
        }
    }

    protected boolean isOnTouch = false;
    protected boolean isOnCollide = false;
    protected boolean isOnClick = false;
    protected boolean isOnIdlePosition = false;
    protected boolean isOnScreen = false;
    protected boolean isAnimateOnDraw = false;

    protected boolean isGoingToRight = true;

    protected Bitmap bitmap;
    protected int intRawBitmap;
    public ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();
    protected int imgFrame = 0;
    protected int imgSpeed = 0;
    protected Rect rectImage;

    //----------------------------------------------------------------------------------------------
    // Constructor :

    public MySpriteClass(Context context, ArrayList<Bitmap> bitmapArrayList, int indexIdleBitmap){
        super(context);

        this.context = context;
        this.bitmapArrayList = bitmapArrayList;

        bitmap = bitmapArrayList.get(indexIdleBitmap);
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        setRectImagePosition();
    }

    public MySpriteClass(Context context, Bitmap bitmap) {
        super(context);

        this.context = context;
        this.bitmap = bitmap;

        width = this.bitmap.getWidth();
        height = this.bitmap.getHeight();

        setRectImagePosition();
    }

    public MySpriteClass(Context context) {
        super(context);
    }

    //----------------------------------------------------------------------------------------------
    // My methods :

    public void resetPosition(int posY) {
        // from left to right
        if (isGoingToRight) {
            positionX = -10 - width;
        }
        // from right to left
        else {
            positionX = screenWidth + 10 + width;
        }
        positionY = posY;
        isOnScreen = false;
        isOnTouch = false;
        imgFrame = 0;
        setRectImagePosition();
    }

    public int[] getHigherWidthandHeight(ArrayList<Bitmap> list) {

        int[] testArray = new int[2];

        for (Bitmap img : list) {
            if (testArray[0] < img.getWidth())
                testArray[0] = img.getWidth();

            if (testArray[1] < img.getHeight())
                testArray[1] = img.getHeight();
        }

        return testArray;
    } // Renvoie un tableau [maxWidth,maxHeight] selon images de la liste testée.

    public int[] getLowerWidthandHeight(ArrayList<Bitmap> list) {

        int[] testArray = getHigherWidthandHeight(list);

        for (Bitmap img : list) {
            if (testArray[0] > img.getWidth())
                testArray[0] = img.getWidth();

            if (testArray[1] > img.getHeight())
                testArray[1] = img.getHeight();
        }

        return testArray;
    } // Renvoie un tableau [lowWidth,lowHeight] selon images de la liste testée.

    public Boolean touchIt(float touchX, float touchY) {
        if (touchX > positionX && touchX < positionX + width && touchY > positionY && touchY < positionY + height) {
            isOnTouch = true;
            return true;
        }
        isOnTouch = false;
        return false;
    }

    public Boolean collideIt(Rect rectToTestCollide) {
        if (Rect.intersects(rectToTestCollide, rectImage)) {
            isOnCollide = true;
            return true;
        }
        isOnCollide = false;
        return false;
    }

    public static Comparator<MySpriteClass> comparatorIndexTemporary = new Comparator<MySpriteClass>() {
        @Override
        public int compare(MySpriteClass f1, MySpriteClass f2) {
            return f1.indexTemporary - f2.indexTemporary;
        }

    };

    //----------------------------------------------------------------------------------------------
    // Getter and Setter

    public int[] getPosition() {
        int[] array = new int[2];
        array[0] = positionX;
        array[1] = positionY;

        return array;
    } // Renvoie un tableau [posX,posY]

    public void setPosition(int x, int y) {
        positionX = x;
        positionY = y;
        setRectImagePosition();
    }

    public Rect getRectImage() {
        setRectImagePosition();
        return rectImage;
    }

    public void setRectImagePosition() {
        int rectWidth = positionX + width;
        int rectHeight = positionY + height;
        rectImage = new Rect(positionX, positionY, rectWidth, rectHeight);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getWidthMaxime() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeightMaxime() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Boolean getOnTouch() {
        return isOnTouch;
    }

    public void setOnTouch(Boolean onTouch) {
        isOnTouch = onTouch;
    }

    public boolean isOnClick() {
        return isOnClick;
    }

    public void setOnClick(boolean onClick) {
        isOnClick = onClick;
    }

    public Boolean getOnCollide() {
        return isOnCollide;
    }

    public void setOnCollide(Boolean onCollide) {
        isOnCollide = onCollide;
    }

    public boolean isOnIdlePosition() {
        return isOnIdlePosition;
    }

    public void setOnIdlePosition(boolean onIdlePosition) {
        isOnIdlePosition = onIdlePosition;
    }

    public boolean isOnScreen() {
        return isOnScreen;
    }

    public void setOnScreen(boolean onScreen) {
        isOnScreen = onScreen;
    }

    public boolean isAnimateOnDraw() {
        return isAnimateOnDraw;
    }

    public void setAnimateOnDraw(boolean animateOnDraw) {
        isAnimateOnDraw = animateOnDraw;
    }

    public boolean isGoingToRight() {
        return isGoingToRight;
    }

    public void setGoingToRight(boolean goingToRight) {
        isGoingToRight = goingToRight;
    }

    public int getImgFrame() {
        return imgFrame;
    }

    public void setImgFrame(int imgFrame) {
        this.imgFrame = imgFrame;
    }

    public int getImgSpeed() {
        return imgSpeed;
    }

    public void setImgSpeed(int imgSpeed) {
        this.imgSpeed = imgSpeed;
    }

    public void setRectImagePosition(Rect rectImage) {
        this.rectImage = rectImage;
    }

    public int getSideCollide() {
        return sideCollide;
    }

    public void setSideCollide(int sideCollide) {
        this.sideCollide = sideCollide;
    }
}
