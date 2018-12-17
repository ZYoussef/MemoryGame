package memory.android.istia.memorygame.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.utils.FragmentController;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 */
public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private List<String> mAvailableLanguages;
    private CheckBox mCheckBoxSound;
    private CheckBox mCheckBoxVibration;
    private Spinner mSpinnerLanguage;
    private Button mButtonValidate;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAvailableLanguages = new ArrayList<String>(){
            {
                add("A");
                add("B");
                add("C");
                add("D");
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);

        mCheckBoxSound = view.findViewById(R.id.checkBoxSound);
        mCheckBoxVibration = view.findViewById(R.id.checkBoxVibration);
        mSpinnerLanguage = view.findViewById(R.id.spinnerLanguages);
        mButtonValidate = view.findViewById(R.id.button_settings_validate);

        mCheckBoxSound.setOnCheckedChangeListener(this);
        mCheckBoxVibration.setOnCheckedChangeListener(this);
        mButtonValidate.setOnClickListener(this);

        //Spinner adapter
        String[] t = new String[]{"A", "B"};
        ArrayAdapter<String> adapter = new CustomSpinnerAdapter(getActivity(), R.layout.custom_dropdown_image, t);
        mSpinnerLanguage.setAdapter(adapter);

        return view;
    }

    /**
     * Activer/désactiver le son
     */
    private void switchSound(){
        if(mCheckBoxSound.isChecked()){
            SharedPreferenceManager.write(SharedPreferenceManager.Settings.SOUND_IS_ON, true);
        }
        else{
            SharedPreferenceManager.write(SharedPreferenceManager.Settings.SOUND_IS_ON, false);
        }
    }

    /**
     * Activer/désactiver les vibrations
     */
    private void switchVibration(){
        if(mCheckBoxVibration.isChecked()){
            SharedPreferenceManager.write(SharedPreferenceManager.Settings.VIBRATION_IS_ON, true);
        }
        else{
            SharedPreferenceManager.write(SharedPreferenceManager.Settings.VIBRATION_IS_ON, false);
        }
    }

    /**
     * Listener pour les checkbox (son et vibration)
     * @param buttonView
     * @param isChecked
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
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_settings_validate){
            //Retour au menu principal
            FragmentController.getInstance().openFragment(FragmentController.Fragments.MAIN_MENU);
        }
    }

    public class CustomSpinnerAdapter extends ArrayAdapter<String>{

        public CustomSpinnerAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.custom_dropdown_image, parent, false);
            TextView label=(TextView)row.findViewById(R.id.textViewCountryName);
            label.setText("Test");

            ImageView icon=(ImageView)row.findViewById(R.id.imageViewFlag);
            icon.setImageResource(android.R.drawable.ic_menu_add);

            return row;
        }
    }
}
