package fr.maxime.mygame01.MyObjects;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class Sauvegarde implements Serializable {

    private transient ArrayList<ProfileDailyNeedsClass> listDailyNeedsProfil = new ArrayList<>();   // liste des profils enregistrés pour DailyNeedActivity

    private int indexLastProfileUsedInListDailyNeedsProfil;
    private ProfileDailyNeedsClassSerializableV2[] arrayDailyNeedsProfilesSerializableV2;

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public Sauvegarde() {
        super();
    }

    public Sauvegarde(ArrayList<ProfileDailyNeedsClass> listeProfiles, ProfileDailyNeedsClass lastProfileUsed) {
        listDailyNeedsProfil = listeProfiles;
        indexLastProfileUsedInListDailyNeedsProfil = listDailyNeedsProfil.indexOf(lastProfileUsed);

        arrayDailyNeedsProfilesSerializableV2 = getSerializedProfileArray();
    }

    // ---------------------------------------------------------------------------------------------
    // My Methods :

    private ProfileDailyNeedsClassSerializableV2[] getSerializedProfileArray(){

        ProfileDailyNeedsClassSerializableV2[] arrayProfilesSerializableV2 = new ProfileDailyNeedsClassSerializableV2[listDailyNeedsProfil.size()];

        for (ProfileDailyNeedsClass profileDailyNeeds : listDailyNeedsProfil){

            int index = listDailyNeedsProfil.indexOf(profileDailyNeeds);

            ProfileDailyNeedsClassSerializableV2 profileSerializableV2 = new ProfileDailyNeedsClassSerializableV2(profileDailyNeeds);
            arrayProfilesSerializableV2[index] = profileSerializableV2;

        }

        return arrayProfilesSerializableV2;

    }

    public void deserialized(Context context, ArrayList<FoodClass> listAllFood){

        listDailyNeedsProfil.clear();

        for (ProfileDailyNeedsClassSerializableV2 profileSerialized : arrayDailyNeedsProfilesSerializableV2){
            ArrayList<SuiviAlimentaireClass> suiviAlimentaireDeserializedArrayList = new ArrayList<>();

            for (SuiviAlimentaireClassSerializableV2 suiviAlimentaireSerialized : profileSerialized.getArraySuiviAlimentaireClassSerializableV2()){

                suiviAlimentaireSerialized.deserializationFoodClass(listAllFood);
                SuiviAlimentaireClass suiviAlimentaireDeserialized =
                        new SuiviAlimentaireClass(suiviAlimentaireSerialized.getTimeDayMoment(),
                                suiviAlimentaireSerialized.getIntArrayDate(),
                                suiviAlimentaireSerialized.getFoodClassArrayListDeserialized());

                suiviAlimentaireDeserializedArrayList.add(suiviAlimentaireDeserialized);

            }

            ProfileDailyNeedsClass profileDailyNeedsDeserialized =
                    new ProfileDailyNeedsClass(context,
                            profileSerialized.getPseudo(),
                            profileSerialized.getSex(),
                            profileSerialized.getAge(),
                            profileSerialized.getTaille(),
                            profileSerialized.getMorphologie(),
                            profileSerialized.getIdNumber(),
                            profileSerialized.getAmicloonSerialized());

            profileDailyNeedsDeserialized.setPoidsIdeal(profileSerialized.getPoidsIdeal());
            profileDailyNeedsDeserialized.calculDailyNeedDOUBLEValues(context);
            profileDailyNeedsDeserialized.setTypeAlimentation(profileSerialized.getTypeAlimentation());
            profileDailyNeedsDeserialized.setArrayListSuiviAlimentaireClass(suiviAlimentaireDeserializedArrayList);

            listDailyNeedsProfil.add(profileDailyNeedsDeserialized);

        }

    }

    // ---------------------------------------------------------------------------------------------
    // GETTER and SETTER :

    public ArrayList<ProfileDailyNeedsClass> getListDailyNeedsProfil() {
        return listDailyNeedsProfil;
    }

    public ProfileDailyNeedsClass getLastProfileUsed() {
        return listDailyNeedsProfil.get(indexLastProfileUsedInListDailyNeedsProfil);
    }

}
