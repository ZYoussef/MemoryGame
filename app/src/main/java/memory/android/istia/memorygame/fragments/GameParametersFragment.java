package memory.android.istia.memorygame.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.utils.FragmentController;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;


public class GameParametersFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Button mButtonPlay;
    private Button buttonNextDeck;
    private Button buttonPreviousDeck;
    private Button buttonNextDificulty;
    private Button buttonPreviousDifficulty;
    private CheckBox checkBoxTimeLimit;
    private CheckBox checkBoxHitLimit;

    private ImageView imageViewDeck;
    private TextView textViewDifficulty;

    private String selectedDifficulty;
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
        View view = inflater.inflate(R.layout.fragment_game_parameters, container, false);

        mButtonPlay = view.findViewById(R.id.buttonGameParameterPlay);
        buttonNextDeck = view.findViewById(R.id.buttonDeckNext);
        buttonPreviousDeck = view.findViewById(R.id.buttonDeckPrevious);
        imageViewDeck = view.findViewById(R.id.imageViewDeck);
        buttonNextDificulty = view.findViewById(R.id.buttonNextDifficulty);
        buttonPreviousDifficulty = view.findViewById(R.id.buttonPreviousDifficulty);
        textViewDifficulty = view.findViewById(R.id.textViewDifficulty);
        checkBoxHitLimit = view.findViewById(R.id.checkBoxHitLimit);
        checkBoxTimeLimit = view.findViewById(R.id.checkBoxTimeLimit);


        mButtonPlay.setOnClickListener(this);
        buttonPreviousDeck.setOnClickListener(this);
        buttonNextDeck.setOnClickListener(this);
        buttonPreviousDifficulty.setOnClickListener(this);
        buttonNextDificulty.setOnClickListener(this);

        checkBoxTimeLimit.setOnCheckedChangeListener(this);
        checkBoxHitLimit.setOnCheckedChangeListener(this);

        selectedDifficulty = "easy";
        return view;
    }

    @Override
    public void onClick(View v) {
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.button_click);
        mp.start();
        switch(v.getId()){
            case R.id.buttonGameParameterPlay:
                Bundle args = new Bundle();
                args.putString("difficulty", selectedDifficulty);
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

    private void setNextDifficulty(){
        switch(selectedDifficulty){
            case "easy":
                selectedDifficulty = "medium";
                textViewDifficulty.setText(R.string.medium);
                break;
            case "medium":
                selectedDifficulty = "hard";
                textViewDifficulty.setText(R.string.hard);
                break;
        }
    }

    private void setPreviousDifficulty(){
        switch(selectedDifficulty){
            case "hard":
                selectedDifficulty = "medium";
                textViewDifficulty.setText(R.string.medium);
                break;
            case "medium":
                selectedDifficulty = "easy";
                textViewDifficulty.setText(R.string.easy);
                break;
        }
    }

    private void setNextDeck(){
        switch(SharedPreferenceManager.read(SharedPreferenceManager.Settings.DECK_SELECTED, "")){
            case "incredibles" :
                SharedPreferenceManager.write(SharedPreferenceManager.Settings.DECK_SELECTED, "kid");
                break;
            case "kid" :
                SharedPreferenceManager.write(SharedPreferenceManager.Settings.DECK_SELECTED, "incredibles");
                break;
        }

        updateDeckImageView();
    }

    private void updateDeckImageView(){
        switch(SharedPreferenceManager.read(SharedPreferenceManager.Settings.DECK_SELECTED, "")){
            case "incredibles" :
                imageViewDeck.setImageResource(R.drawable.deck_card_incredible);
                break;
            case "kid" :
                imageViewDeck.setImageResource(R.drawable.deck_card_kid);
                break;
        }
    }

    private void setPreviousDeck(){
        switch(SharedPreferenceManager.read(SharedPreferenceManager.Settings.DECK_SELECTED, "")){
            case "incredibles" :
                SharedPreferenceManager.write(SharedPreferenceManager.Settings.DECK_SELECTED, "kid");
                break;
            case "kid" :
                SharedPreferenceManager.write(SharedPreferenceManager.Settings.DECK_SELECTED, "incredibles");
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
