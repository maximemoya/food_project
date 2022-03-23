package fr.maxime.mygame01.MyObjects;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

import fr.maxime.mygame01.R;

public class Amicloon {

    private String name;
    private final int[] birthDate;
    private final int idNumber;

    private int[] actualDate;

    private Double[] arrayDoubleValuesByElements;                                                   // valeurs Elements dans estomac reinitialisees tous les jours
    private final Double[] arrayDoubleValuesMAXbyElements;                                                // valeurs ELements Max FINALES definies par le profil.

    private int vie;
    private int intelligence;
    private int force;
    private int humeur;
    private int proprete;

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public Amicloon(Context context) {

        // NAME :
        if (TransitionClass.langageActual.equals(TransitionClass.languageArraySelector
                [TransitionClass.enumLanguageSelector.Anglais.ordinal()])) {
            name = TransitionClass.profileActual.getPseudo() + "'s " + context.getString(R.string.amicloon);
        } else {
            name = context.getString(R.string.amicloon) + " de " + TransitionClass.profileActual.getPseudo();
        }

        // CARACTERISTICS :
        birthDate = TransitionClass.getDate();
        idNumber = TransitionClass.profileActual.getIdNumber();

        vie = 100;
        intelligence = 1;
        force = 1;
        humeur = 50;
        proprete = 100;

        actualDate = TransitionClass.getDate();

        // arrayList values en direct :
        arrayDoubleValuesByElements = new Double[TransitionClass.profileActual.getDailyNeedDOUBLEValuesList().size()];
        Arrays.fill(arrayDoubleValuesByElements, 0.0);

        // arrayList values Maximale :
        arrayDoubleValuesMAXbyElements = new Double[TransitionClass.profileActual.getDailyNeedDOUBLEValuesList().size()];
        Arrays.fill(arrayDoubleValuesMAXbyElements, 0.0);
        for (int i = 0; i < TransitionClass.profileActual.getDailyNeedDOUBLEValuesList().size(); i++) {
            arrayDoubleValuesMAXbyElements[i] = TransitionClass.profileActual.getDailyNeedDOUBLEValuesList().get(i);
        }

    }

    // Constructor deserialized :

    public Amicloon(String pseudo, AmicloonSerialized amicloonSerialized, ArrayList<Double> arrayListDailyNeedDOUBLEValuesList) {

        // NAME :
        if (TransitionClass.langageActual.equals(TransitionClass.languageArraySelector
                [TransitionClass.enumLanguageSelector.Anglais.ordinal()])) {
            name = pseudo + "'s Amicloon";
        } else {
            name = "Amicloon de " + pseudo;
        }


        // CARACTERISTICS :
        birthDate = amicloonSerialized.getBirthDate();
        idNumber = amicloonSerialized.getIdNumber();

        vie = amicloonSerialized.getVie();
        intelligence = amicloonSerialized.getIntelligence();
        force = amicloonSerialized.getForce();
        humeur = amicloonSerialized.getHumeur();
        proprete = amicloonSerialized.getProprete();

        actualDate = amicloonSerialized.getActualDate();

        getVerifDate(amicloonSerialized);

        // MaxValues :
        arrayDoubleValuesMAXbyElements = new Double[arrayListDailyNeedDOUBLEValuesList.size()];
        Arrays.fill(arrayDoubleValuesMAXbyElements, 0.0);
        for (int i = 0; i < arrayListDailyNeedDOUBLEValuesList.size(); i++) {
            arrayDoubleValuesMAXbyElements[i] = arrayListDailyNeedDOUBLEValuesList.get(i);
        }

    }

    // ---------------------------------------------------------------------------------------------
    // MyMethods :

    public int getPercentValueByElements(int enumElementsList) {

        return (int) ((arrayDoubleValuesByElements[enumElementsList] * 100) / arrayDoubleValuesMAXbyElements[enumElementsList]);
    }

    public String getPercentValueByElementsToSTRING(int enumElementsList) {

        return getPercentValueByElements(enumElementsList) + " %";
    }

    public void getVerifDate(AmicloonSerialized amicloonSerialized) {

        if(Arrays.equals(TransitionClass.getDate(), actualDate)){
            arrayDoubleValuesByElements = amicloonSerialized.getArrayDoubleValuesByElements();

        } else {
            arrayDoubleValuesByElements = new Double[amicloonSerialized.getArrayDoubleValuesByElements().length];
            Arrays.fill(arrayDoubleValuesByElements, 0.0);

            if (proprete >= 50) {
                proprete -= 50;
            }

        }
    }

    // ---------------------------------------------------------------------------------------------
    // SETTERS :

    public void setArrayDoubleValuesByElements(Double[] arrayDoubleValuesByElements) {

        this.arrayDoubleValuesByElements = arrayDoubleValuesByElements;

    }

    // ---------------------------------------------------------------------------------------------
    // Getters :

    public String getDateBirthdayToString(Context context) {

        if (TransitionClass.langageActual.equals(TransitionClass.languageArraySelector[TransitionClass.enumLanguageSelector.Anglais.ordinal()])) {
            return context.getString(R.string.naissance) + " : " + birthDate[1] + " / " + birthDate[0] + " / " + birthDate[2];
        } else {
            return context.getString(R.string.naissance) + " : le " + birthDate[0] + " / " + birthDate[1] + " / " + birthDate[2];
        }

    }

    public String getName() {
        return name;
    }

    public int[] getBirthDate() {
        return birthDate;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public int[] getActualDate() {
        return actualDate;
    }

    public Double[] getArrayDoubleValuesByElements() {
        return arrayDoubleValuesByElements;
    }

    public Double[] getArrayDoubleValuesMAXbyElements() {
        return arrayDoubleValuesMAXbyElements;
    }

    public int getVie() {
        return vie;
    }

    public String getVieToString() {
        return vie + " %";
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getIntelligenceToString() {
        return intelligence + " %";
    }

    public int getForce() {
        return force;
    }

    public String getForceToString() {
        return force + " %";
    }

    public int getHumeur() {
        return humeur;
    }

    public String getHumeurToString() {
        return humeur + " %";
    }

    public int getProprete() {
        return proprete;
    }

    public String getPropreteToString() {
        return proprete + " %";
    }
}
