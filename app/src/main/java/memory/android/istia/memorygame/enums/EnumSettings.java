package memory.android.istia.memorygame.enums;

import android.support.annotation.NonNull;

/**
 * Enumeration des paramètres disponibles
 */
public enum EnumSettings {
    FIRST_START{
        @NonNull
        @Override
        public String toString() {
            return "FIRST_START";
        }
    },
    SOUND_IS_ON{
        @NonNull
        @Override
        public String toString() {
            return "SOUND_IS_ON";
        }
    },
    VIBRATION_IS_ON{
        @NonNull
        @Override
        public String toString() {
            return "VIBRATION_IS_ON";
        }
    },
    LANGUAGE_SELECTED{
        @NonNull
        @Override
        public String toString() {
            return "LANGUAGE_SELECTED";
        }
    },
    DECK_SELECTED{
        @NonNull
        @Override
        public String toString() {
            return "DECK_SELECTED";
        }
    },
    SCORES_EASY{
        @NonNull
        @Override
        public String toString() {
            return "SCORES_EASY";
        }
    },
    SCORES_MEDIUM{
        @NonNull
        @Override
        public String toString() {
            return "SCORES_MEDIUM";
        }
    },
    SCORES_HARD{
        @NonNull
        @Override
        public String toString() {
            return "SCORES_HARD";
        }
    }
}
