package memory.android.istia.memorygame.SelectionOptions;

import memory.android.istia.memorygame.enums.EnumLanguage;
import memory.android.istia.memorygame.enums.EnumSharedPreferences;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

public class LanguageSelection implements StateSelection {

    @Override
    public Enum next(Enum enumeration) {
        if(enumeration instanceof EnumLanguage){
            EnumLanguage enumLanguage = (EnumLanguage) enumeration;

            switch(enumLanguage){
                case FRENCH:
                    SharedPreferenceManager.write(EnumSharedPreferences.LANGUAGE_SELECTED, EnumLanguage.ENGLISH.ordinal());
                    return EnumLanguage.ENGLISH;
                case ENGLISH:
                    SharedPreferenceManager.write(EnumSharedPreferences.LANGUAGE_SELECTED, EnumLanguage.RUSSIAN.ordinal());
                    return EnumLanguage.RUSSIAN;
                case RUSSIAN:
                    SharedPreferenceManager.write(EnumSharedPreferences.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());
                    return EnumLanguage.FRENCH;
            }
        }

        SharedPreferenceManager.write(EnumSharedPreferences.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());
        return EnumLanguage.FRENCH;
    }

    @Override
    public Enum previous(Enum enumeration) {
        if(enumeration instanceof EnumLanguage){
            EnumLanguage enumLanguage = (EnumLanguage) enumeration;

            switch(enumLanguage){
                case FRENCH:
                    SharedPreferenceManager.write(EnumSharedPreferences.LANGUAGE_SELECTED, EnumLanguage.RUSSIAN.ordinal());
                    return EnumLanguage.ENGLISH;
                case ENGLISH:
                    SharedPreferenceManager.write(EnumSharedPreferences.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());
                    return EnumLanguage.RUSSIAN;
                case RUSSIAN:
                    SharedPreferenceManager.write(EnumSharedPreferences.LANGUAGE_SELECTED, EnumLanguage.ENGLISH.ordinal());
                    return EnumLanguage.FRENCH;
            }
        }

        SharedPreferenceManager.write(EnumSharedPreferences.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());
        return EnumLanguage.FRENCH;
    }
}
