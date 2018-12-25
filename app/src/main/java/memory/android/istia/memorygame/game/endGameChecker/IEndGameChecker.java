package memory.android.istia.memorygame.game.endGameChecker;


/**
 * IEndGameChecker--- DP Observer (observateur)
 *  Cette interface s'occupe des vérifications des conditions de fin de jeu
 *  Lorsque la condition est rempli, notifie le GameManager que le jeu est fini (défaite ou victoire)
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public interface IEndGameChecker {

    /**
     * Signale au GameManager que la partie est finie
     */
    void notifyGameManager();

    /**
     * Appelée par le GameManager à chaque coup joué, met à jour
     * les informations de la partie (nb de coups joués)
     */
    void update();

    /**
     * Calcul du score selon le mode de jeu.
     * Coups limités : Le score correspond au % des coups joués par rapport au nombre limite des coups.
     * Contre la montre : le score correspond au % du temps effectué.
     * Si celui ci est <= 30%, le score est considéré comme parfait.
     * @return le score calculé
     */
    int CalculateScore();

}
