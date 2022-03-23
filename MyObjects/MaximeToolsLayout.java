package fr.maxime.mygame01.MyObjects;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class MaximeToolsLayout {

    public static LinearLayout.LayoutParams setLayoutParams(Context context, int layout_width, int layout_height, int margin_left, int margin_right, int margin_top, int margin_bottom, int gravity, float weight ){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layout_width, layout_height);
        params.setMargins(convertDpToPixel(context, margin_left), convertDpToPixel(context,margin_top), convertDpToPixel(context,margin_right), convertDpToPixel(context,margin_bottom));
        params.gravity= gravity;
        params.weight= weight;
        return params;
    }

    private static int convertDpToPixel(Context context, int yourDpMeasure) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                yourDpMeasure,
                r.getDisplayMetrics()
        );
    }

    public static void serializeSauvegarde(Context context, String filename, Sauvegarde sauvegarde){

        // POUR G-SON SERIALIZER : Add in build.gradle(Module: xxx)
        // dependencies {
        //    implementation 'com.google.code.gson:gson:2.8.2'

        Gson gson = new Gson();
        String gsonString = gson.toJson(sauvegarde);

        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(gsonString.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Sauvegarde deserializeSauvegarde(Context context, String filename){

        // POUR G-SON SERIALIZER : Add in build.gradle(Module: xxx)
        // dependencies {
        //    implementation 'com.google.code.gson:gson:2.8.2'

        FileInputStream fis;
        try {
            fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while (true){
                try {
                    if ((line = bufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sb.append(line);
            }

            String json = sb.toString();
            Gson gson = new Gson();

            return gson.fromJson(json, Sauvegarde.class);

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return null;
    }

    // for Arraylist :
    public static ArrayList<Object> deserializeArraylist(){

        String json = "[xxx]";
        Gson gson = new Gson();

        Type objectType = new TypeToken<ArrayList<Object>>(){}.getType();

        return gson.fromJson(json, objectType);
    }

    // EXPERIMENTAL :
//    public static <T> Class deserializeStringJSON(Context context, String filename, Class<T> classOfT){
//
//        // FIXME: 20/11/2020
//
//        // POUR G-SON SERIALIZER : Add in build.gradle(Module: xxx)
//        // dependencies {
//        //    implementation 'com.google.code.gson:gson:2.8.2'
//
//        FileInputStream fis;
//        try {
//            fis = context.openFileInput(filename);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader bufferedReader = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while (true){
//                try {
//                    if ((line = bufferedReader.readLine()) == null) break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                sb.append(line);
//            }
//
//            String json = sb.toString();
//            Gson gson = new Gson();
//
//            return gson.fromJson(json, (Type) classOfT);
//
//        } catch (FileNotFoundException fileNotFoundException) {
//            fileNotFoundException.printStackTrace();
//        }
//        return null;
//    }
}
