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
 * Fragment pour les crédits
 * Aucune action spéciale ici
 */
public class CreditsFragment extends Fragment implements View.OnClickListener {

    public CreditsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credits, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getView() != null){
            Button buttonBack = getView().findViewById(R.id.buttonMenuCredit);
            buttonBack.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonMenuCredit){
            if(getActivity() instanceof MainActivity){
                ((MainActivity) getActivity()).playClickSound();
            }

            FragmentController.getInstance().openFragment(FragmentController.Fragments.MAIN_MENU);
        }
    }
}
