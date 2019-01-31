package memory.android.istia.memorygame.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import memory.android.istia.memorygame.MainActivity;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.selections_options.LanguageSelection;
import memory.android.istia.memorygame.enums.EnumLanguage;
import memory.android.istia.memorygame.enums.EnumSettings;
import memory.android.istia.memorygame.services.AudioService;
import memory.android.istia.memorygame.utils.CustomFragmentManager;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 */
public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private CheckBox mCheckBoxSound;
    private CheckBox mCheckBoxVibration;
    private ImageView imageViewFlag;

    private LanguageSelection languageSelection;

    //Pour ne pas avoir le son du click quand on modifie les checkbox à l'initialisation
    private boolean allowSound = false;


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCheckBoxSound = view.findViewById(R.id.checkBoxSound);
        mCheckBoxVibration = view.findViewById(R.id.checkBoxVibration);
        Button mButtonValidate = view.findViewById(R.id.button_settings_validate);
        Button buttonLanguageNext = view.findViewById(R.id.buttonLanguageNext);
        Button buttonLanguagePrevious = view.findViewById(R.id.buttonLanguagePrevious);
        imageViewFlag = view.findViewById(R.id.imageViewFlag);


        mCheckBoxSound.setOnCheckedChangeListener(this);
        mCheckBoxVibration.setOnCheckedChangeListener(this);
        mButtonValidate.setOnClickListener(this);
        buttonLanguagePrevious.setOnClickListener(this);
        buttonLanguageNext.setOnClickListener(this);

        languageSelection = new LanguageSelection(getContext());

        if(SharedPreferenceManager.read(EnumSettings.SOUND_IS_ON, false)){
            mCheckBoxSound.setChecked(true);
        }

        if(SharedPreferenceManager.read(EnumSettings.VIBRATION_IS_ON, false)){
            mCheckBoxVibration.setChecked(true);
        }

        updateLanguage();
        allowSound = true;
    }

    /**
     * Activer/désactiver le son
     */
    private void switchSound(){
        SharedPreferenceManager.write(EnumSettings.SOUND_IS_ON, mCheckBoxSound.isChecked());
    }

    /**
     * Activer/désactiver les vibrations
     */
    private void switchVibration(){
        SharedPreferenceManager.write(EnumSettings.VIBRATION_IS_ON, mCheckBoxVibration.isChecked());
    }

    /**
     * Listener pour les checkbox (son et vibration)
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.checkBoxSound){
            switchSound();
        }
        else if(buttonView.getId() == R.id.checkBoxVibration){
            switchVibration();
        }

        if(getActivity() instanceof MainActivity && this.allowSound){
            ((MainActivity) getActivity()).playClickSound();
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_settings_validate:
                if(getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).setMusic(SharedPreferenceManager.read(EnumSettings.SOUND_IS_ON, true));
                }
                CustomFragmentManager.getInstance().openFragment(CustomFragmentManager.Fragments.MAIN_MENU);
                break;
            case R.id.buttonLanguageNext:
                selectNextLanguage();
                break;
            case R.id.buttonLanguagePrevious:
                selectPreviousLanguage();
                break;
            default:
                CustomFragmentManager.getInstance().openFragment(CustomFragmentManager.Fragments.MAIN_MENU);
                break;
        }
        ((MainActivity) getActivity()).playClickSound();
    }

    private void selectNextLanguage() {
        EnumLanguage currentLanguage = EnumLanguage.values()[SharedPreferenceManager.read(EnumSettings.LANGUAGE_SELECTED, 0)];
        languageSelection.next(currentLanguage);
        languageSelection.updateView(imageViewFlag);
    }

    private void selectPreviousLanguage() {
        EnumLanguage currentLanguage = EnumLanguage.values()[SharedPreferenceManager.read(EnumSettings.LANGUAGE_SELECTED, 0)];
        languageSelection.previous(currentLanguage);
        languageSelection.updateView(imageViewFlag);
    }

    private void updateLanguage() {
        languageSelection.updateView(imageViewFlag);
    }
}
