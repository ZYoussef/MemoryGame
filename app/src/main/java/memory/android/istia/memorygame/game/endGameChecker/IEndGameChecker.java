package memory.android.istia.memorygame.game.endGameChecker;


/**
 * IEndGameChecker---
 *  Cette interface s'occupe des vérifications des conditions de fin de jeu
 *  Lorsque la condition est rempli, notifie le GameManager que le jeu est fini (défaite ou victoire)
 *
 *  TODO : Toutes ces classes sont à lancer dans un service/thread
 *
 * @author Sébastien, Thomas, Youssef
 * @version 1.0
 */
public interface IEndGameChecker {

    void notifyGameManager();
}
