package memory.android.istia.memorygame.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;
import android.util.Log;

import java.util.ArrayList;
import java.util.Set;

import memory.android.istia.memorygame.enums.EnumDeck;
import memory.android.istia.memorygame.enums.EnumLanguage;
import memory.android.istia.memorygame.enums.EnumSharedPreferences;

/**
 * SharedPreferenceManager--- Gestion des données de l'application
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class SharedPreferenceManager {

    private static SharedPreferences mSharedPreference;

    //Données disponible dans le SharedPreference

    private SharedPreferenceManager() {}

    /**
     * Initialisation générale du manager, à n'appeler qu'une fois au lancement de l'application
     * En cas de premier lancement, initialisation de valeur par défaut pour les options
     * @param context Context de l'application
     */
    public static void init(Context context){
        if(mSharedPreference == null)
            mSharedPreference = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);

        //Si premier lancement de l'application, on met les valeurs par défaut pour certaines options
        if(!mSharedPreference.contains(EnumSharedPreferences.FIRST_START.toString()))
            writeDefaultSettings();
    }

    /**
     * Ecriture des préférences par défaut, exécuter seulement lors du premier lancement
     * ou si aucune donnée n'a été trouvée dans SharedPreference
     *
     * TODO - Application mise en fr par défaut, à voir pour mettre suivant la langue du téléphone
     */
    private static void writeDefaultSettings(){
        write(EnumSharedPreferences.FIRST_START, false);
        write(EnumSharedPreferences.SOUND_IS_ON, true);
        write(EnumSharedPreferences.VIBRATION_IS_ON, true);
        write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
        write(EnumSharedPreferences.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());

        ArraySet<String> default_Score = new ArraySet<>();

        write(EnumSharedPreferences.SCORES_EASY, default_Score);
        write(EnumSharedPreferences.SCORES_MEDIUM, default_Score);
        write(EnumSharedPreferences.SCORES_HARD, default_Score);
    }


    /**
     * Lecture d'une donnée dans SharedPreference
     * @param key  Clé à aller chercher
     * @param defValue Valeur par défaut si la clé n'est pas trouvée
     * @return Valeur recherché
     */
    public static String read(EnumSharedPreferences key, String defValue) {
        return mSharedPreference.getString(key.toString(), defValue);
    }

    /**
     * Ecriture d'une donnée dans SharedPreference
     * @param key  Clé sur laquelle on veut écrire
     * @param value  Valeur à écrire
     */
    public static void write(EnumSharedPreferences key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putString(key.toString(), value);
        prefsEditor.apply();
    }

    public static boolean read(EnumSharedPreferences key, boolean defValue) {
        return mSharedPreference.getBoolean(key.toString(), defValue);
    }

    public static void write(EnumSharedPreferences key, Boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putBoolean(key.toString(), value);
        prefsEditor.apply();
    }

    public static Set<String> read(EnumSharedPreferences key, Set<String> defValue) {
        return mSharedPreference.getStringSet(key.toString(), defValue);
    }

    public static void write(EnumSharedPreferences key, Set<String> value) {
        Log.d("test", "SAVE SCORE");
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putStringSet(key.toString(), value);
        prefsEditor.apply();
    }

    public static int read(EnumSharedPreferences key, int defValue) {
        return mSharedPreference.getInt(key.toString(), defValue);
    }

    public static void write(EnumSharedPreferences key, int value) {
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putInt(key.toString(), value);
        prefsEditor.apply();
    }
}
