package memory.android.istia.memorygame.fragments;

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
import memory.android.istia.memorygame.selections_options.DeckSelection;
import memory.android.istia.memorygame.selections_options.DifficultySelection;
import memory.android.istia.memorygame.enums.EnumDeck;
import memory.android.istia.memorygame.enums.EnumDifficulty;
import memory.android.istia.memorygame.enums.EnumSettings;
import memory.android.istia.memorygame.utils.CustomFragmentManager;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 * Responsable des paramètres de jeu :
 *  - Deck
 *  -Difficulté
 *  - Limite de temps
 *  - Limite de coups
 */
public class GameParametersFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private CheckBox checkBoxTimeLimit;
    private CheckBox checkBoxHitLimit;

    private ImageView imageViewDeck;
    private TextView textViewDifficulty;

    private EnumDifficulty selectedDifficulty;
    private DifficultySelection difficultySelection;

    private DeckSelection deckSelection;

    private boolean timeLimitSet;
    private boolean hitLimitSet;

    public GameParametersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_parameters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button mButtonPlay = view.findViewById(R.id.buttonGameParameterPlay);
        Button buttonNextDeck = view.findViewById(R.id.buttonDeckNext);
        Button buttonPreviousDeck = view.findViewById(R.id.buttonDeckPrevious);
        imageViewDeck = view.findViewById(R.id.imageViewDeck);
        Button buttonNextDifficulty = view.findViewById(R.id.buttonNextDifficulty);
        Button buttonPreviousDifficulty = view.findViewById(R.id.buttonPreviousDifficulty);
        Button buttonClose = view.findViewById(R.id.buttonClose);
        textViewDifficulty = view.findViewById(R.id.textViewDifficulty);
        checkBoxHitLimit = view.findViewById(R.id.checkBoxHitLimit);
        checkBoxTimeLimit = view.findViewById(R.id.checkBoxTimeLimit);

        mButtonPlay.setOnClickListener(this);
        buttonPreviousDeck.setOnClickListener(this);
        buttonNextDeck.setOnClickListener(this);
        buttonPreviousDifficulty.setOnClickListener(this);
        buttonNextDifficulty.setOnClickListener(this);
        buttonClose.setOnClickListener(this);

        checkBoxTimeLimit.setOnCheckedChangeListener(this);
        checkBoxHitLimit.setOnCheckedChangeListener(this);

        initValues();
    }

    /**
     * Initialisation des valeurs par défaut
     */
    private void initValues() {
        EnumDeck deckSelected = EnumDeck.values()[SharedPreferenceManager.read(EnumSettings.DECK_SELECTED, 0)];
        switch(deckSelected){
            case INCREDIBLES: imageViewDeck.setImageResource(R.drawable.deck_card_incredible); break;
            case KIDS:  imageViewDeck.setImageResource(R.drawable.deck_card_kid); break;
            case FRUITS: imageViewDeck.setImageResource(R.drawable.deck_card_fruit); break;
        }

        this.selectedDifficulty = EnumDifficulty.EASY;
        this.difficultySelection = new DifficultySelection();
        this.deckSelection = new DeckSelection();
    }

    @Override
    public void onClick(View v) {

        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).playClickSound();
        }

        switch(v.getId()){
            case R.id.buttonGameParameterPlay:
                play();
                break;
            case R.id.buttonClose:
                CustomFragmentManager.getInstance().openFragment(CustomFragmentManager.Fragments.MAIN_MENU);
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
            default:
                CustomFragmentManager.getInstance().openFragment(CustomFragmentManager.Fragments.MAIN_MENU);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).playClickSound();
        }

        if(buttonView.getId() == R.id.checkBoxHitLimit){
            hitLimitSet = checkBoxHitLimit.isChecked();
        }
        else if(buttonView.getId() == R.id.checkBoxTimeLimit){
            timeLimitSet = checkBoxTimeLimit.isChecked();
        }
    }

    private void play() {
        Bundle args = new Bundle();
        args.putInt("difficulty", selectedDifficulty.ordinal());
        args.putBoolean("timeLimit", timeLimitSet);
        args.putBoolean("hitLimit", hitLimitSet);

        CustomFragmentManager.getInstance().openFragmentWithData(CustomFragmentManager.Fragments.GAME, args);
    }

    private void setNextDifficulty(){
        selectedDifficulty = (EnumDifficulty) difficultySelection.next(selectedDifficulty);
        difficultySelection.updateView(textViewDifficulty);
    }

    private void setPreviousDifficulty(){
        selectedDifficulty = (EnumDifficulty) difficultySelection.previous(selectedDifficulty);
        difficultySelection.updateView(textViewDifficulty);
    }

    private void setNextDeck(){
        EnumDeck deckSelected = EnumDeck.values()[SharedPreferenceManager.read(EnumSettings.DECK_SELECTED, 0)];
        this.deckSelection.next(deckSelected);
        this.deckSelection.updateView(imageViewDeck);
    }

    private void setPreviousDeck(){
        EnumDeck deckSelected = EnumDeck.values()[SharedPreferenceManager.read(EnumSettings.DECK_SELECTED, 0)];
        this.deckSelection.previous(deckSelected);
        this.deckSelection.updateView(imageViewDeck);
    }
}
