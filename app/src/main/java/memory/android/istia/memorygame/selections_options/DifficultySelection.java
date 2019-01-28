package memory.android.istia.memorygame.selections_options;

import android.view.View;
import android.widget.TextView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.enums.EnumDifficulty;

/**
 * Sélection de la difficulté
 */
public class DifficultySelection implements StateSelection {

    private EnumDifficulty selectedDifficulty;

    public DifficultySelection(){
        selectedDifficulty = EnumDifficulty.EASY;
    }

    @Override
    public Enum next(Enum enumeration) {
        if(enumeration instanceof EnumDifficulty){
            EnumDifficulty enumerationDifficulty = (EnumDifficulty) enumeration;

            if(enumerationDifficulty == EnumDifficulty.EASY){
                selectedDifficulty =  EnumDifficulty.MEDIUM;
            }
            else{
                selectedDifficulty =  EnumDifficulty.HARD;
            }
        }

        return selectedDifficulty;
    }

    @Override
    public Enum previous(Enum enumeration) {
        if(enumeration instanceof EnumDifficulty){
            EnumDifficulty enumerationDifficulty = (EnumDifficulty) enumeration;

            if(enumerationDifficulty == EnumDifficulty.HARD){
                selectedDifficulty =  EnumDifficulty.MEDIUM;
            }
            else{
                selectedDifficulty =  EnumDifficulty.EASY;
            }
        }

        return selectedDifficulty;
    }

    @Override
    public void updateView(View view) {
        if(view instanceof TextView){
            TextView tvDifficulty = (TextView) view;
            switch(selectedDifficulty){
                case EASY:
                    tvDifficulty.setText(R.string.easy);
                    break;
                case MEDIUM:
                    tvDifficulty.setText(R.string.medium);
                    break;
                case HARD:
                    tvDifficulty.setText(R.string.hard);
                    break;
            }
        }
    }
}
