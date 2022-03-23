package fr.maxime.mygame01.MyObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileDailyNeedsClassSerializableV2 implements Serializable {

    private final AmicloonSerialized amicloonSerialized;
    private final int idNumber;
    private final String pseudo;
    private final Integer sex;                                                                            // 0 male, 1 femme
    private final Integer age;
    private final Integer taille;                                                                         // en cm
    private final Integer morphologie;                                                                    // 1 fin | 2 moyen | 3 solide
    private final Integer poidsIdeal;                                                                     // en kg
    private final Integer typeAlimentation;                                                               // 0 noraml | 1 vegetarien | 2 vegetalien

    private final SuiviAlimentaireClassSerializableV2[] arraySuiviAlimentaireClassSerializableV2;         // tableau du suivi des aliments manges selon date et la periode de la journee;

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public ProfileDailyNeedsClassSerializableV2(ProfileDailyNeedsClass profile) {

        this.idNumber = profile.getIdNumber();
        this.pseudo = profile.getPseudo();
        this.sex = profile.getSex();
        this.age = profile.getAge();
        this.taille = profile.getTaille();
        this.morphologie = profile.getMorphologie();
        this.poidsIdeal = profile.getPoidsIdeal();
        this.typeAlimentation = profile.getTypeAlimentation();

        arraySuiviAlimentaireClassSerializableV2 = selectionDesElementsASauvegarderDansSuiviAlimentaireClass(profile.getArrayListSuiviAlimentaireClass());

        amicloonSerialized = serializationAmicoonClass(profile.getAmicoon());

    }


    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private SuiviAlimentaireClassSerializableV2[] selectionDesElementsASauvegarderDansSuiviAlimentaireClass(ArrayList<SuiviAlimentaireClass> listSuiviAlimentaire) {

        SuiviAlimentaireClassSerializableV2[] arraySuiviAlimentaireSerializableV2 = new SuiviAlimentaireClassSerializableV2[listSuiviAlimentaire.size()];

        for (SuiviAlimentaireClass suiviAlimentaire : listSuiviAlimentaire) {

            int index = listSuiviAlimentaire.indexOf(suiviAlimentaire);

            SuiviAlimentaireClassSerializableV2 suiviAlimentaireSerializableV2 = new SuiviAlimentaireClassSerializableV2(suiviAlimentaire);
            arraySuiviAlimentaireSerializableV2[index] = suiviAlimentaireSerializableV2;

        }

        return arraySuiviAlimentaireSerializableV2;

    }

    private AmicloonSerialized serializationAmicoonClass(Amicloon amiconToSerialize){

        try {
            return new AmicloonSerialized(amiconToSerialize);
        }
        catch (Exception e){
            return null;
        }

    }

    // ---------------------------------------------------------------------------------------------
    // GETTER AND SETTER :

    public AmicloonSerialized getAmicloonSerialized() {
        return amicloonSerialized;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Integer getSex() {
        return sex;
    }

    public Integer getMorphologie() {
        return morphologie;
    }

    public Integer getPoidsIdeal() {
        return poidsIdeal;
    }

    public Integer getTypeAlimentation() {
        return typeAlimentation;
    }

    public SuiviAlimentaireClassSerializableV2[] getArraySuiviAlimentaireClassSerializableV2() {
        return arraySuiviAlimentaireClassSerializableV2;
    }
}
