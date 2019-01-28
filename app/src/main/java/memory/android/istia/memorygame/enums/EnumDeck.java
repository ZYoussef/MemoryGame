package memory.android.istia.memorygame.enums;

import android.support.annotation.NonNull;

/**
 * Enumeration des decks disponibles
 */
public enum EnumDeck {
    INCREDIBLES{
        @NonNull
        @Override
        public String toString() {
            return "incredibles";
        }
    },
    KIDS{
        @NonNull
        @Override
        public String toString() {
            return "kids";
        }
    },
    FRUITS{
        @NonNull
        @Override
        public String toString() {
            return "fruits";
        }
    }
}
