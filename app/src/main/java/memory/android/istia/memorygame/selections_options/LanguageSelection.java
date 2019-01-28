package memory.android.istia.memorygame.selections_options;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.enums.EnumLanguage;
import memory.android.istia.memorygame.enums.EnumSettings;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 * Sélection de la langue
 */
public class LanguageSelection implements StateSelection {

    private Context context;

    public LanguageSelection(Context context){
        this.context = context;
    }

    @Override
    public Enum next(Enum enumeration) {
        if(enumeration instanceof EnumLanguage){
            EnumLanguage enumLanguage = (EnumLanguage) enumeration;

            switch(enumLanguage){
                case FRENCH:
                    SharedPreferenceManager.write(EnumSettings.LANGUAGE_SELECTED, EnumLanguage.ENGLISH.ordinal());
                    return EnumLanguage.ENGLISH;
                case ENGLISH:
                    SharedPreferenceManager.write(EnumSettings.LANGUAGE_SELECTED, EnumLanguage.RUSSIAN.ordinal());
                    return EnumLanguage.RUSSIAN;
                case RUSSIAN:
                    SharedPreferenceManager.write(EnumSettings.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());
                    return EnumLanguage.FRENCH;
            }
        }

        SharedPreferenceManager.write(EnumSettings.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());
        return EnumLanguage.FRENCH;
    }

    @Override
    public Enum previous(Enum enumeration) {
        if(enumeration instanceof EnumLanguage){
            EnumLanguage enumLanguage = (EnumLanguage) enumeration;

            switch(enumLanguage){
                case FRENCH:
                    SharedPreferenceManager.write(EnumSettings.LANGUAGE_SELECTED, EnumLanguage.RUSSIAN.ordinal());
                    return EnumLanguage.ENGLISH;
                case ENGLISH:
                    SharedPreferenceManager.write(EnumSettings.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());
                    return EnumLanguage.RUSSIAN;
                case RUSSIAN:
                    SharedPreferenceManager.write(EnumSettings.LANGUAGE_SELECTED, EnumLanguage.ENGLISH.ordinal());
                    return EnumLanguage.FRENCH;
            }
        }

        SharedPreferenceManager.write(EnumSettings.LANGUAGE_SELECTED, EnumLanguage.FRENCH.ordinal());
        return EnumLanguage.FRENCH;
    }

    @Override
    public void updateView(View view) {

        if(view instanceof ImageView) {
            ImageView imageViewFlag = (ImageView) view;

            EnumLanguage currentLanguage = EnumLanguage.values()[SharedPreferenceManager.read(EnumSettings.LANGUAGE_SELECTED, 0)];

            switch (currentLanguage) {
                case ENGLISH:
                    imageViewFlag.setImageResource(R.drawable.english_flag);
                    break;
                case FRENCH:
                    imageViewFlag.setImageResource(R.drawable.french_flag);
                    break;
                case RUSSIAN:
                    imageViewFlag.setImageResource(R.drawable.russia_flag);
                    break;
            }

            Locale locale = new Locale(currentLanguage.toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;

            if (context != null) {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
        }
    }
}
