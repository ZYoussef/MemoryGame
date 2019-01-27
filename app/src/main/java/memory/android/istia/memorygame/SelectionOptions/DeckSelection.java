package memory.android.istia.memorygame.SelectionOptions;

import memory.android.istia.memorygame.enums.EnumDeck;
import memory.android.istia.memorygame.enums.EnumSharedPreferences;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

public class DeckSelection implements StateSelection {

    @Override
    public Enum next(Enum enumeration) {
        if(enumeration instanceof EnumDeck){
            EnumDeck enumDeck = (EnumDeck) enumeration;
            switch(enumDeck){
                case INCREDIBLES:
                    SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.KIDS.ordinal());
                    return EnumDeck.KIDS;
                case KIDS:
                    SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.FRUITS.ordinal());
                    return EnumDeck.FRUITS;
                case FRUITS:
                    SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
                    return EnumDeck.INCREDIBLES;
            }
        }

        SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
        return EnumDeck.INCREDIBLES;
    }

    @Override
    public Enum previous(Enum enumeration) {
        if(enumeration instanceof EnumDeck){
            EnumDeck enumDeck = (EnumDeck) enumeration;
            switch(enumDeck){
                case INCREDIBLES:
                    SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.FRUITS.ordinal());
                    return EnumDeck.FRUITS;
                case KIDS:
                    SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
                    return EnumDeck.INCREDIBLES;
                case FRUITS:
                    SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.KIDS.ordinal());
                    return EnumDeck.KIDS;
            }
        }

        SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
        return EnumDeck.INCREDIBLES;
    }
}
