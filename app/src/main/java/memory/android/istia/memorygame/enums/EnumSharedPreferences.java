package memory.android.istia.memorygame.enums;

public enum EnumSharedPreferences {
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
    },
    DECK_SELECTED{
        @Override
        public String toString() {
            return "DECK_SELECTED";
        }
    },
    SCORES_EASY{
        @Override
        public String toString() {
            return "SCORES_EASY";
        }
    },
    SCORES_MEDIUM{
        @Override
        public String toString() {
            return "SCORES_MEDIUM";
        }
    },
    SCORES_HARD{
        @Override
        public String toString() {
            return "SCORES_HARD";
        }
    }
}
