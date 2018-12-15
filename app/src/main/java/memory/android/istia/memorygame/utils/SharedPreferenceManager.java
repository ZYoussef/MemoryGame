package memory.android.istia.memorygame.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferenceManager--- Gestion des données de l'application
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class SharedPreferenceManager {

    private static SharedPreferences mSharedPreference;

    //Données disponible dans le SharedPreference
    public enum Settings {
        FIRST_START{
            @Override
            public String toString() {
                return "FIRST_START";
            }
        },
        SOUND_IS_ON{
            @Override
            public String toString() {
                return "SOUND_IS_ON";
            }
        },
        VIBRATION_IS_ON{
            @Override
            public String toString() {
                return "VIBRATION_IS_ON";
            }
        },
        LANGUAGE_SELECTED{
            @Override
            public String toString() {
                return "LANGUAGE_SELECTED";
            }
        }}

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
        if(!mSharedPreference.contains(Settings.FIRST_START.toString()))
            writeDefaultSettings();
    }

    /**
     * Ecriture des préférences par défaut, exécuter seulement lors du premier lancement
     * ou si aucune donnée n'a été trouvée dans SharedPreference
     *
     * TODO - Application mise en fr par défaut, à voir pour mettre suivant la langue du téléphone
     */
    private static void writeDefaultSettings(){
        write(Settings.FIRST_START, false);
        write(Settings.SOUND_IS_ON, true);
        write(Settings.VIBRATION_IS_ON, true);
        write(Settings.LANGUAGE_SELECTED, "fr");
    }


    /**
     * Lecture d'une donnée dans SharedPreference
     * @param key  Clé à aller chercher
     * @param defValue Valeur par défaut si la clé n'est pas trouvée
     * @return Valeur recherché
     */
    public static String read(Settings key, String defValue) {
        return mSharedPreference.getString(key.toString(), defValue);
    }

    /**
     * Ecriture d'une donnée dans SharedPreference
     * @param key  Clé sur laquelle on veut écrire
     * @param value  Valeur à écrire
     */
    public static void write(Settings key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putString(key.toString(), value);
        prefsEditor.apply();
    }

    public static boolean read(Settings key, boolean defValue) {
        return mSharedPreference.getBoolean(key.toString(), defValue);
    }

    public static void write(Settings key, Boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPreference.edit();
        prefsEditor.putBoolean(key.toString(), value);
        prefsEditor.apply();
    }
}
