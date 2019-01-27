package memory.android.istia.memorygame.SelectionOptions;

import android.widget.TextView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.enums.EnumDifficulty;

public class DifficultySelection implements StateSelection {

    private TextView tvDifficulty;

    public DifficultySelection(TextView tvDifficulty) {
        this.tvDifficulty = tvDifficulty;
    }

    @Override
    public Enum next(Enum enumeration) {
        if(enumeration instanceof EnumDifficulty){
            EnumDifficulty enumerationDifficulty = (EnumDifficulty) enumeration;

            switch(enumerationDifficulty){
                case EASY:
                    this.tvDifficulty.setText(R.string.medium);
                    return EnumDifficulty.MEDIUM;
                case MEDIUM:
                    this.tvDifficulty.setText(R.string.hard);
                    return EnumDifficulty.HARD;
            }
        }

        this.tvDifficulty.setText(R.string.medium);
        return EnumDifficulty.MEDIUM;
    }

    @Override
    public Enum previous(Enum enumeration) {
        if(enumeration instanceof EnumDifficulty){
            EnumDifficulty enumerationDifficulty = (EnumDifficulty) enumeration;

            switch(enumerationDifficulty){
                case MEDIUM:
                    this.tvDifficulty.setText(R.string.easy);
                    return EnumDifficulty.EASY;
                case HARD:
                    this.tvDifficulty.setText(R.string.medium);
                    return EnumDifficulty.MEDIUM;
            }
        }

        this.tvDifficulty.setText(R.string.medium);
        return EnumDifficulty.MEDIUM;
    }
}
