package memory.android.istia.memorygame.selections_options;

import android.view.View;

/**
 * Interface pour la sélection d'options dans les différents menus
 */
public interface StateSelection {
     Enum next(Enum enumeration);
     Enum previous(Enum enumeration);
     void updateView(View view);
}
