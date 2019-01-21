package memory.android.istia.memorygame.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.fragments.CreditsFragment;
import memory.android.istia.memorygame.fragments.EndGameFragment;
import memory.android.istia.memorygame.fragments.GameFragment;
import memory.android.istia.memorygame.fragments.GameParametersFragment;
import memory.android.istia.memorygame.fragments.MainMenuFragment;
import memory.android.istia.memorygame.fragments.ScoreFragment;
import memory.android.istia.memorygame.fragments.SettingsFragment;

/**
 * FragmentController--- Gère l'ensemble des fragment du jeu (Singleton)
 *  S'occupe d'afficher les fragments sur l'activité
 *
 *  Source : https://github.com/sromku/memory-game/blob/master/app/src/main/java/com/snatik/matches/engine/ScreenController.java
 *
 * @author Sébastien, Thomas, Youssef
 *
 * @version 1.0
 */
public class FragmentController {

    private static FragmentController mInstance;
    private FragmentManager mFragmentManager;
    private List<Fragments> openedFragments;

    public static enum Fragments
    {
        MAIN_MENU,
        SETTINGS,
        SCORES,
        CREDITS,
        GAME_PARAMETERS,
        GAME,
        END_GAME
    }

    public void setFragmentManager(FragmentManager fm){
        this.mFragmentManager = fm;
    }

    private FragmentController(FragmentManager fm){
        this.setFragmentManager(fm);
        openedFragments = new ArrayList<>();
    }

    public static void init(FragmentManager fragmentManager){
        mInstance = new FragmentController(fragmentManager);
    }


    public static FragmentController getInstance(){
        if(mInstance == null){
            throw new IllegalStateException("FragmentController has not been initialised");
        }

        return mInstance;
    }

    /**
     *  Affichera le fragment spécifié
     * @param screen Fragment à afficher
     */
    public void openFragment(Fragments screen) {
        Fragment fragment = getFragment(screen);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        openedFragments.add(screen);
    }

    public void openFragmentWithData(Fragments screen, Bundle bundle){
        Fragment fragment = getFragment(screen);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        openedFragments.add(screen);
    }

    public void openFragmentAsPopup(Fragments screen, Bundle bundle){
        EndGameFragment fragment = (EndGameFragment) getFragment(screen);
        fragment.setArguments(bundle);
        fragment.show(mFragmentManager, "test");
    }

    /**
     * Override de onBack
     * Permet d'afficher l'ancien fragment
     * (Par exemple en plein jeu, on pourrait afficher un "êtes vous sûr de vouloir quitter" au lieu
     * de simplement revenir à l'activité précédente
     * @return
     */
    public boolean onBack() {
        if (openedFragments.size() > 0) {
            openedFragments.remove(openedFragments.size() - 1);
            if (openedFragments.size() == 0) {
                return true;
            }
            Fragments screen = openedFragments.get(openedFragments.size() - 1);
            openedFragments.remove(openedFragments.size() - 1);
            openFragment(screen);
            return false;
        }
        return true;
    }

    private Fragment getFragment(Fragments frag){
        switch(frag){
            case MAIN_MENU: return new MainMenuFragment();
            case SETTINGS: return new SettingsFragment();
            case SCORES: return new ScoreFragment();
            case CREDITS: return new CreditsFragment();
            case GAME_PARAMETERS: return new GameParametersFragment();
            case GAME: return new GameFragment();
            case END_GAME: return new EndGameFragment();
        }

        return null;
    }

}
