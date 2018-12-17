package memory.android.istia.memorygame.themes;

import java.util.ArrayList;

/**
 * CardThemeFactory--- Permet de créer les différents thèmes des cartes de jeu, indépendament du thème d'affichage
 * Quatre thèmes sont à disposition :
 *  - Noel | Période de fête
 *  - Pixar | Héros de Pixar
 *  - Disney | Héros de Disney
 *  - UNO  | Carte du jeu UNO classique
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public class CardThemeFactory {

    public static final int NB_IMAGE= 30;

    // Lien du dossier contenant les images à utiliser
    public static String URI_DRAWABLE = "drawable://";

    /**
     * Création du thème Pixar pour les cartes.
     * @return le thème créé
     */
    public static CardTheme createPixarCardTheme() {
        CardTheme theme = new CardTheme();
        theme.id = 1;
        theme.name = "Pixar";
        theme.backgroundImageUrl = URI_DRAWABLE + "back_pixar";
        theme.tileImageUrls = new ArrayList<>();
        for (int i = 1; i <= NB_IMAGE; i++) {
            theme.tileImageUrls.add(URI_DRAWABLE + String.format("pixar_%d", i));
        }
        return theme;
    }


}
