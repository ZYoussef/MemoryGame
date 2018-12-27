package memory.android.istia.memorygame.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import memory.android.istia.memorygame.utils.FragmentController;

import memory.android.istia.memorygame.R;

/**
 * Fragement du menu principale.
 */
public class MainMenuFragment extends Fragment implements View.OnClickListener {

    private Button mPlayButton, mSettingButton, mScoreButton, mCreditButton;

    public MainMenuFragment() {
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
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        mPlayButton = (Button) view.findViewById(R.id.playButton);
        mSettingButton = (Button) view.findViewById(R.id.settingsButton);
        mScoreButton= (Button) view.findViewById(R.id.scoreButton);
        mCreditButton= (Button) view.findViewById(R.id.creditButton);

        mPlayButton.setOnClickListener(this);
        mSettingButton.setOnClickListener(this);
        mScoreButton.setOnClickListener(this);
        mCreditButton.setOnClickListener(this);

        return view;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                FragmentController.getInstance().openFragment(FragmentController.Fragments.GAME_PARAMETERS);
                break;
            case R.id.settingsButton:
                FragmentController.getInstance().openFragment(FragmentController.Fragments.SETTINGS);
                break;
            case R.id.scoreButton:
                FragmentController.getInstance().openFragment(FragmentController.Fragments.SCORES);
                break;
            case R.id.creditButton:
                FragmentController.getInstance().openFragment(FragmentController.Fragments.CREDITS);
                break;


        }
    }


}
