package fr.maxime.mygame01.Etagere;

import java.util.ArrayList;

public class OnBoardingItemPage {

    private String titleText = "TITRE";
    public ArrayList<RepasMakerClass> arrayListRepas = new ArrayList<>();

    // ---------------------------------------------------------------------------------------------
    // GETTER / SETTER :

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public ArrayList<RepasMakerClass> getArrayListRepas() {
        return arrayListRepas;
    }

    public void setArrayListRepas(ArrayList<RepasMakerClass> arrayListRepas) {
        this.arrayListRepas = arrayListRepas;
    }
}
