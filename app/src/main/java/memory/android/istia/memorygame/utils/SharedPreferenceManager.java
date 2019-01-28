package memory.android.istia.memorygame.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;
import android.util.Log;

import java.util.Set;

import memory.android.istia.memorygame.enums.EnumDeck;
import memory.android.istia.memorygame.enums.EnumLanguage;
import memory.android.istia.memorygame.enums.EnumSettings;

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
        if(!mSharedPreference.contains(EnumSettings.FIRST_START.toString()))
            writeDefaultSettings();
    }

    /**
     * Ecriture des préférences par défaut, exécuter seulement lors du premier lancement
     * ou si aucune donnée n'a été trouvée dans SharedPreference
     *
     */
    private static void writeDefaultSettings(){
        write(EnumSettings.FIRST_START, false);
        write(EnumSettings.SOUND_IS_ON, true);
        write(EnumSettings.VIBRATION_IS_ON, false);
        write(EnumSettings.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
        write(EnumSettings.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());

        ArraySet<String> defaultScore = new ArraySet<>();

        write(EnumSettings.SCORES_EASY, defaultScore);
        write(EnumSettings.SCORES_MEDIUM, defaultScore);
        write(EnumSettings.SCORES_HARD, defaultScore);
    }


    /**
     * Lecture d'une donnée dans SharedPreference
     * @param key  Clé à aller chercher
     * @param defValue Valeur par défaut si la clé n'est pas trouvée
     * @return Valeur recherché
     */
    public static String read(EnumSettings key, String defValue) {
        return mSharedPreference.getString(key.toString(), defValue);
    }

    /**
     * Ecriture d'une donnée dans SharedPreference
     * @param key  Clé sur laquelle on veut écrire
     * @param value  Valeur à écrire
     */
    public static void write(EnumSettings key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putString(key.toString(), value);
        prefsEditor.apply();
    }

    public static boolean read(EnumSettings key, boolean defValue) {
        return mSharedPreference.getBoolean(key.toString(), defValue);
    }

    public static void write(EnumSettings key, Boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putBoolean(key.toString(), value);
        prefsEditor.apply();
    }

    public static Set<String> read(EnumSettings key, Set<String> defValue) {
        return mSharedPreference.getStringSet(key.toString(), defValue);
    }

    public static void write(EnumSettings key, Set<String> value) {
        Log.d("test", "SAVE SCORE");
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putStringSet(key.toString(), value);
        prefsEditor.apply();
    }

    public static int read(EnumSettings key, int defValue) {
        return mSharedPreference.getInt(key.toString(), defValue);
    }

    public static void write(EnumSettings key, int value) {
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putInt(key.toString(), value);
        prefsEditor.apply();
    }
}
