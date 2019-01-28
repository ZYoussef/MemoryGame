package memory.android.istia.memorygame.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import memory.android.istia.memorygame.MainActivity;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.utils.FragmentController;

/**
 * Frageent du menu principal
 */
public class MainMenuFragment extends Fragment implements View.OnClickListener {

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button playButton = view.findViewById(R.id.buttonPlay);
        Button settingButton = view.findViewById(R.id.buttonSettings);
        Button scoreButton = view.findViewById(R.id.buttonRank);
        Button creditButton = view.findViewById(R.id.buttonCredit);

        playButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);
        scoreButton.setOnClickListener(this);
        creditButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).playClickSound();
        }

        switch (v.getId()) {
            case R.id.buttonPlay:
                FragmentController.getInstance().openFragment(FragmentController.Fragments.GAME_PARAMETERS);
                break;
            case R.id.buttonSettings:
                FragmentController.getInstance().openFragment(FragmentController.Fragments.SETTINGS);
                break;
            case R.id.buttonRank:
                FragmentController.getInstance().openFragment(FragmentController.Fragments.SCORES);
                break;
            case R.id.buttonCredit:
                FragmentController.getInstance().openFragment(FragmentController.Fragments.CREDITS);
                break;
            default:
                FragmentController.getInstance().openFragment(FragmentController.Fragments.GAME_PARAMETERS);
                break;
        }
    }


}
