package memory.android.istia.memorygame.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.utils.FragmentController;


public class GameParametersFragment extends Fragment implements View.OnClickListener {

    private Button mButtonPlay;

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


        mButtonPlay.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonGameParameterPlay:
                Bundle args = new Bundle();
                args.putString("difficulty", "easy");
                FragmentController.getInstance().openFragmentWithData(FragmentController.Fragments.GAME, args);
                break;
        }
    }
}
