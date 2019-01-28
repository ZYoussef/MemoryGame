package memory.android.istia.memorygame.game;

import memory.android.istia.memorygame.game.end_game_checker.IEndGameChecker;

/**Interface pour le GameManager, implémentation du DP Observer (sujet)
 *
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
interface IGameManager {

    /** Permet de rajouter un vérificateur de fin de partie
     * @param endGameChecker Nouveau observer à attacher
     */
    void attach(IEndGameChecker endGameChecker);

    /**
     * Notifier tous les vérificateurs de fin de partie lorsqu'une action est effectué
     */
    void notifyEndGameCheckers();
}
