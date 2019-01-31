package memory.android.istia.memorygame.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import memory.android.istia.memorygame.R;
import memory.android.istia.memorygame.enums.EnumSettings;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 * Fragment responsable des cartes de jeu, gère le click + le retournement de carte
 */
public class CardFragment extends Fragment implements View.OnClickListener {

    private int mID;
    private int mPairNumber;
    private int mImageId;
    private int mBackImageId;
    private boolean mPairFound;
    private int mWidth;
    private int mHeight;

    private ImageView mCardImage;

    public static CardFragment newInstance(int id, int pairNumber, int image, int backImage) {
        CardFragment myFragment = new CardFragment();
        Bundle bd = new Bundle();
        bd.putInt("id", id);
        bd.putInt("pairNumber", pairNumber);
        bd.putInt("image", image);
        bd.putInt("backImage", backImage);
        myFragment.setArguments(bd);
        return myFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        if(getArguments() != null){
            this.mID = getArguments().getInt("id");
            this.mBackImageId = getArguments().getInt("backImage");
            this.mImageId = getArguments().getInt("image");
            this.mPairNumber = getArguments().getInt("pairNumber");
        }

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCardImage = view.findViewById(R.id.imageViewCard);
        mCardImage.setImageResource(this.mBackImageId);
        mCardImage.setOnClickListener(this);

        this.mCardImage.getLayoutParams().height = this.mHeight;
        this.mCardImage.getLayoutParams().width = this.mWidth;
    }

    /**
     * Transmet l'information du click au GameFragment
     * @param v view
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == mCardImage.getId()){
            vibrate();
            if(getParentFragment() instanceof GameFragment){
                ((GameFragment) getParentFragment()).clickOnCard(this.mID);
            }
        }
    }

    /**
     * Permet de faire vibrer le téléphone si l'option est activée
     */
    private void vibrate(){
        if(SharedPreferenceManager.read(EnumSettings.VIBRATION_IS_ON, false)
                && getActivity() != null){
            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                vibrator.vibrate(100);
            }
        }
    }

    /**
     * Effectue une animation de rotation sur mCardImage et change l'image selon visible
     * L'image est changée entre deux animations lorsque la carte n'est plus visible
     * @param visible carte visible ou non
     */
    public void setCardVisibility(final boolean visible){
        ObjectAnimator animation = ObjectAnimator.ofFloat(mCardImage, "rotationY", 0f, 90f);
        animation.setDuration(250);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(visible){
                    mCardImage.setImageResource(mImageId);
                }
                else{
                    mCardImage.setImageResource(mBackImageId);
                }

                ObjectAnimator animation2 = ObjectAnimator.ofFloat(mCardImage, "rotationY", 90f, 0f);
                animation2.setDuration(250);
                animation2.setInterpolator(new AccelerateDecelerateInterpolator());
                animation2.start();
            }
        });
    }

    public void resizeCard(int newWidth, int newHeight) {
        this.mHeight = newHeight;
        this.mWidth = newWidth;
    }

    public int getCardID(){
        return this.mID;
    }

    public int getPairNumber(){
        return this.mPairNumber;
    }

    public void setPairFound(boolean found){
        this.mPairFound = found;
    }

    public boolean getPairFound(){
        return this.mPairFound;
    }
}
