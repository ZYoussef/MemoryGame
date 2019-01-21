package memory.android.istia.memorygame.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.utils.FragmentController;


public class GameParametersFragment extends Fragment implements View.OnClickListener {

    private Button mButtonPlay;
    private Button buttonNextDeck;
    private Button buttonPreviousDeck;
    private ImageView imageViewDeck;

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


        mButtonPlay.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.button_click);
        mp.start();
        switch(v.getId()){
            case R.id.buttonGameParameterPlay:
                Bundle args = new Bundle();
                args.putString("difficulty", "easy");
                FragmentController.getInstance().openFragmentWithData(FragmentController.Fragments.GAME, args);
                break;
        }
    }
}
