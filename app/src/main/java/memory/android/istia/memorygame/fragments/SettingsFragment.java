package memory.android.istia.memorygame.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.nio.InvalidMarkException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import memory.android.istia.memorygame.MainActivity;
import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.SelectionOptions.LanguageSelection;
import memory.android.istia.memorygame.SelectionOptions.StateSelection;
import memory.android.istia.memorygame.enums.EnumLanguage;
import memory.android.istia.memorygame.enums.EnumSharedPreferences;
import memory.android.istia.memorygame.utils.FragmentController;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 */
public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private CheckBox mCheckBoxSound;
    private CheckBox mCheckBoxVibration;
    private Button mButtonValidate;
    private Button buttonLanguageNext;
    private Button buttonLanguagePrevious;
    private ImageView imageViewFlag;

    private LanguageSelection languageSelection;


    public SettingsFragment() {
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCheckBoxSound = view.findViewById(R.id.checkBoxSound);
        mCheckBoxVibration = view.findViewById(R.id.checkBoxVibration);
        mButtonValidate = view.findViewById(R.id.button_settings_validate);
        buttonLanguageNext = view.findViewById(R.id.buttonLanguageNext);
        buttonLanguagePrevious = view.findViewById(R.id.buttonLanguagePrevious);
        imageViewFlag = view.findViewById(R.id.imageViewFlag);


        mCheckBoxSound.setOnCheckedChangeListener(this);
        mCheckBoxVibration.setOnCheckedChangeListener(this);
        mButtonValidate.setOnClickListener(this);
        buttonLanguagePrevious.setOnClickListener(this);
        buttonLanguageNext.setOnClickListener(this);

        languageSelection = new LanguageSelection();

        if(SharedPreferenceManager.read(EnumSharedPreferences.SOUND_IS_ON, true)){
            mCheckBoxSound.setChecked(true);
        }

        if(SharedPreferenceManager.read(EnumSharedPreferences.VIBRATION_IS_ON, true)){
            mCheckBoxVibration.setChecked(true);
        }

        updateLanguage();
    }

    /**
     * Activer/désactiver le son
     */
    private void switchSound(){
        if(mCheckBoxSound.isChecked()){
            SharedPreferenceManager.write(EnumSharedPreferences.SOUND_IS_ON, true);
        }
        else{
            SharedPreferenceManager.write(EnumSharedPreferences.SOUND_IS_ON, false);
        }
    }

    /**
     * Activer/désactiver les vibrations
     */
    private void switchVibration(){
        if(mCheckBoxVibration.isChecked()){
            SharedPreferenceManager.write(EnumSharedPreferences.VIBRATION_IS_ON, true);
        }
        else{
            SharedPreferenceManager.write(EnumSharedPreferences.VIBRATION_IS_ON, false);
        }
    }

    /**
     * Listener pour les checkbox (son et vibration)
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch(buttonView.getId()){
            case R.id.checkBoxSound:
                switchSound();
                break;
            case R.id.checkBoxVibration:
                switchVibration();
                break;
        }

        if(getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).playClickSound();
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_settings_validate:
                if(getActivity() != null && getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).setMusic(SharedPreferenceManager.read(EnumSharedPreferences.SOUND_IS_ON, true));
                }
                FragmentController.getInstance().openFragment(FragmentController.Fragments.MAIN_MENU);
                break;
            case R.id.buttonLanguageNext:
                selectNextLanguage();
                break;
            case R.id.buttonLanguagePrevious:
                selectPreviousLanguage();
        }
        ((MainActivity) getActivity()).playClickSound();
    }

    private void selectNextLanguage() {
        EnumLanguage currentLanguage = EnumLanguage.values()[SharedPreferenceManager.read(EnumSharedPreferences.LANGUAGE_SELECTED, 0)];
        languageSelection.next(currentLanguage);
        updateLanguage();
    }

    private void selectPreviousLanguage() {
        EnumLanguage currentLanguage = EnumLanguage.values()[SharedPreferenceManager.read(EnumSharedPreferences.LANGUAGE_SELECTED, 0)];
        languageSelection.previous(currentLanguage);
        updateLanguage();
    }

    private void updateLanguage() {
        EnumLanguage currentLanguage = EnumLanguage.values()[SharedPreferenceManager.read(EnumSharedPreferences.LANGUAGE_SELECTED, 0)];

        switch(currentLanguage){
            case ENGLISH: imageViewFlag.setImageResource(R.drawable.english_flag);
            break;
            case FRENCH: imageViewFlag.setImageResource(R.drawable.french_flag);
            break;
            case RUSSIAN: imageViewFlag.setImageResource(R.drawable.russia_flag);
            break;
        }

        Locale locale = new Locale(currentLanguage.toString());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;

        if(getActivity() != null){
            getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());
        }


    }
}
