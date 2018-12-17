package memory.android.istia.memorygame.themes;

import java.util.List;

/**
 * Theme--- Déscription du thème
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class CardTheme {

        /**
         * id identifiant du thème
         */
        public int id;

        /**
         * name nom du thème
         */
        public String name;

        /**
         * backgroundImageUrl
         */
        public String backgroundImageUrl;

        /**
         * tileImageUrls Listes des images (liens/noms) à utiliser pour ce thème
         */
        public List<String> tileImageUrls;

        /**
         * backgroundSoundUrl musique de fond à jouer avec ce thème
         */
        public String backgroundSoundUrl;

        /**
         * clickSoundUrl Son à jouer lors d'un click
         */
        public String clickSoundUrl;

        /**
         * Constructeur
         */
        public CardTheme(){}


}
