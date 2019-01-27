package memory.android.istia.memorygame.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import memory.android.istia.memorygame.MainActivity;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.enums.EnumDeck;
import memory.android.istia.memorygame.enums.EnumDifficulty;
import memory.android.istia.memorygame.enums.EnumSharedPreferences;
import memory.android.istia.memorygame.utils.FragmentController;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;


public class GameParametersFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Button mButtonPlay;
    private Button buttonNextDeck;
    private Button buttonPreviousDeck;
    private Button buttonNextDifficulty;
    private Button buttonPreviousDifficulty;
    private CheckBox checkBoxTimeLimit;
    private CheckBox checkBoxHitLimit;

    private ImageView imageViewDeck;
    private TextView textViewDifficulty;

    private EnumDifficulty selectedDifficulty;
    private boolean timeLimitSet;
    private boolean hitLimitSet;

    public GameParametersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_parameters, container, false);
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) getActivity()).playClickSound();
        switch(v.getId()){
            case R.id.buttonGameParameterPlay:
                Bundle args = new Bundle();
                args.putInt("difficulty", selectedDifficulty.ordinal());
                args.putBoolean("timeLimit", timeLimitSet);
                args.putBoolean("hitLimit", hitLimitSet);

                FragmentController.getInstance().openFragmentWithData(FragmentController.Fragments.GAME, args);
                break;
            case R.id.buttonDeckNext:
                setNextDeck();
                break;
            case R.id.buttonDeckPrevious:
                setPreviousDeck();
                break;
            case R.id.buttonNextDifficulty:
                setNextDifficulty();
                break;
            case R.id.buttonPreviousDifficulty:
                setPreviousDifficulty();
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonPlay = view.findViewById(R.id.buttonGameParameterPlay);
        buttonNextDeck = view.findViewById(R.id.buttonDeckNext);
        buttonPreviousDeck = view.findViewById(R.id.buttonDeckPrevious);
        imageViewDeck = view.findViewById(R.id.imageViewDeck);
        buttonNextDifficulty = view.findViewById(R.id.buttonNextDifficulty);
        buttonPreviousDifficulty = view.findViewById(R.id.buttonPreviousDifficulty);
        textViewDifficulty = view.findViewById(R.id.textViewDifficulty);
        checkBoxHitLimit = view.findViewById(R.id.checkBoxHitLimit);
        checkBoxTimeLimit = view.findViewById(R.id.checkBoxTimeLimit);

        EnumDeck deckSelected = EnumDeck.values()[SharedPreferenceManager.read(EnumSharedPreferences.DECK_SELECTED, 0)];
        switch(deckSelected){
            case INCREDIBLES: imageViewDeck.setImageResource(R.drawable.deck_card_incredible); break;
            case KIDS:  imageViewDeck.setImageResource(R.drawable.deck_card_kid); break;
            case FRUITS: imageViewDeck.setImageResource(R.drawable.deck_card_fruit); break;
        }

        this.selectedDifficulty = EnumDifficulty.EASY;


        mButtonPlay.setOnClickListener(this);
        buttonPreviousDeck.setOnClickListener(this);
        buttonNextDeck.setOnClickListener(this);
        buttonPreviousDifficulty.setOnClickListener(this);
        buttonNextDifficulty.setOnClickListener(this);

        checkBoxTimeLimit.setOnCheckedChangeListener(this);
        checkBoxHitLimit.setOnCheckedChangeListener(this);
    }

    private void setNextDifficulty(){
        switch(selectedDifficulty){
            case EASY:
                selectedDifficulty = EnumDifficulty.MEDIUM;
                textViewDifficulty.setText(R.string.medium);
                break;
            case MEDIUM:
                selectedDifficulty = EnumDifficulty.HARD;
                textViewDifficulty.setText(R.string.hard);
                break;
        }
    }

    private void setPreviousDifficulty(){
        switch(selectedDifficulty){
            case HARD:
                selectedDifficulty = EnumDifficulty.MEDIUM;
                textViewDifficulty.setText(R.string.medium);
                break;
            case MEDIUM:
                selectedDifficulty = EnumDifficulty.EASY;
                textViewDifficulty.setText(R.string.easy);
                break;
        }
    }

    private void setNextDeck(){
        EnumDeck deckSelected = EnumDeck.values()[SharedPreferenceManager.read(EnumSharedPreferences.DECK_SELECTED, 0)];

        switch(deckSelected){
            case INCREDIBLES:
                SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.KIDS.ordinal());
                break;
            case KIDS:
                SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.FRUITS.ordinal());
                break;
            case FRUITS:
                SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
                break;
        }

        updateDeckImageView();
    }

    private void updateDeckImageView(){
        EnumDeck deckSelected = EnumDeck.values()[SharedPreferenceManager.read(EnumSharedPreferences.DECK_SELECTED, 0)];

        switch(deckSelected){
            case INCREDIBLES:
                imageViewDeck.setImageResource(R.drawable.deck_card_incredible);
                break;
            case KIDS:
                imageViewDeck.setImageResource(R.drawable.deck_card_kid);
                break;
            case FRUITS:
                imageViewDeck.setImageResource(R.drawable.deck_card_fruit);
                break;
        }
    }

    private void setPreviousDeck(){
        EnumDeck deckSelected = EnumDeck.values()[SharedPreferenceManager.read(EnumSharedPreferences.DECK_SELECTED, 0)];

        switch(deckSelected){
            case INCREDIBLES:
                SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.FRUITS.ordinal());
                break;
            case KIDS:
                SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.INCREDIBLES.ordinal());
                break;
            case FRUITS:
                SharedPreferenceManager.write(EnumSharedPreferences.DECK_SELECTED, EnumDeck.KIDS.ordinal());
                break;
        }

        updateDeckImageView();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()){
            case R.id.checkBoxHitLimit:
                if(checkBoxHitLimit.isChecked()) hitLimitSet = true;
                else hitLimitSet = false;
                break;
            case R.id.checkBoxTimeLimit:
                if(checkBoxTimeLimit.isChecked()) timeLimitSet = true;
                else timeLimitSet = false;
                break;
        }
    }
}
