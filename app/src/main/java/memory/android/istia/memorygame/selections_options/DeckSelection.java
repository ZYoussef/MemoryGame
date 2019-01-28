package memory.android.istia.memorygame.selections_options;

import android.view.View;
import android.widget.ImageView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.enums.EnumDeck;
import memory.android.istia.memorygame.enums.EnumSettings;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 * Sélection des decks (suivant et précédent)
 */
public class DeckSelection implements StateSelection {

    @Override
    public Enum next(Enum enumeration) {
        if(enumeration instanceof EnumDeck){
            EnumDeck enumDeck = (EnumDeck) enumeration;
            switch(enumDeck){
                case INCREDIBLES:
                    SharedPreferenceManager.write(EnumSettings.DECK_SELECTED, EnumDeck.KIDS.ordinal());
                    return EnumDeck.KIDS;
                case KIDS:
                    SharedPreferenceManager.write(EnumSettings.DECK_SELECTED, EnumDeck.FRUITS.ordinal());
                    return EnumDeck.FRUITS;
                case FRUITS:
                    SharedPreferenceManager.write(EnumSettings.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
                    return EnumDeck.INCREDIBLES;
            }
        }

        SharedPreferenceManager.write(EnumSettings.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
        return EnumDeck.INCREDIBLES;
    }

    @Override
    public Enum previous(Enum enumeration) {
        if(enumeration instanceof EnumDeck){
            EnumDeck enumDeck = (EnumDeck) enumeration;
            switch(enumDeck){
                case INCREDIBLES:
                    SharedPreferenceManager.write(EnumSettings.DECK_SELECTED, EnumDeck.FRUITS.ordinal());
                    return EnumDeck.FRUITS;
                case KIDS:
                    SharedPreferenceManager.write(EnumSettings.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
                    return EnumDeck.INCREDIBLES;
                case FRUITS:
                    SharedPreferenceManager.write(EnumSettings.DECK_SELECTED, EnumDeck.KIDS.ordinal());
                    return EnumDeck.KIDS;
            }
        }

        SharedPreferenceManager.write(EnumSettings.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
        return EnumDeck.INCREDIBLES;
    }

    @Override
    public void updateView(View view) {
        if(view instanceof ImageView){

            ImageView imageViewDeck = (ImageView) view;
            EnumDeck deckSelected = EnumDeck.values()[SharedPreferenceManager.read(EnumSettings.DECK_SELECTED, 0)];

            switch(deckSelected){
                case INCREDIBLES:
                    imageViewDeck.setImageResource(R.drawable.deck_card_incredible);
                    break;
                case KIDS:
                    imageViewDeck.setImageResource(R.drawable.deck_card_kid);
                    break;
                case FRUITS:
                    imageViewDeck.setImageResource(R.drawable.deck_card_fruit);
                    break;
            }
        }
    }
}
