package memory.android.istia.memorygame.enums;

import android.support.annotation.NonNull;

/**
 * Enumeration des langages disponibles
 */
public enum EnumLanguage {
    FRENCH{
        @NonNull
        @Override
        public String toString() {
            return "fr";
        }
    },
    ENGLISH{
        @NonNull
        @Override
        public String toString() {
            return "en";
        }
    },
    RUSSIAN{
        @NonNull
        @Override
        public String toString() {
                    return "ru";
                }
    }
}
