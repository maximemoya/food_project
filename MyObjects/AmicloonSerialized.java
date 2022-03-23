package fr.maxime.mygame01.MyObjects;

public class AmicloonSerialized {

    private final int[] birthDate;
    private final int idNumber;

    private final int[] actualDate;

    private final Double[] arrayDoubleValuesByElements;                                             // valeurs Elements dans estomac reinitialisees tous les jours

    private final int vie;
    private final int intelligence;
    private final int force;
    private final int humeur;
    private final int proprete;

    public AmicloonSerialized(Amicloon amicoonToSerialize) {

        birthDate = amicoonToSerialize.getBirthDate();
        idNumber = amicoonToSerialize.getIdNumber();

        actualDate = TransitionClass.getDate();

        arrayDoubleValuesByElements = amicoonToSerialize.getArrayDoubleValuesByElements();

        vie = amicoonToSerialize.getVie();
        intelligence = amicoonToSerialize.getIntelligence();
        force = amicoonToSerialize.getForce();
        humeur = amicoonToSerialize.getHumeur();
        proprete = amicoonToSerialize.getProprete();
    }

    // ---------------------------------------------------------------------------------------------
    // Getters :

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

    public int getVie() {
        return vie;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getForce() {
        return force;
    }

    public int getHumeur() {
        return humeur;
    }

    public int getProprete() {
        return proprete;
    }
}
