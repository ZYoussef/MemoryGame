package memory.android.istia.memorygame.enums;

import android.support.annotation.NonNull;

/**
 * Enumeration des difficultés disponibles
 */
public enum EnumDifficulty {
    EASY{
        @NonNull
        @Override
        public String toString() {
            return "easy";
        }
    },
    MEDIUM{
        @NonNull
        @Override
        public String toString() {
            return "medium";
        }
    },
    HARD{
        @NonNull
        @Override
        public String toString() {
                    return "hard";
                }
    }
}
