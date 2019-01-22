package memory.android.istia.memorygame.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import memory.android.istia.memorygame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment implements View.OnClickListener {

    private int mID;
    private int mPairNumber;
    private int mImageId;
    private int mBackImageId;
    private boolean mCardVisible;
    private boolean mPairFound;

    private int width;
    private int height;

    private ImageView mCardImage;




    public CardFragment() {
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        this.mID = getArguments().getInt("id");
        this.mBackImageId = getArguments().getInt("backImage");
        this.mImageId = getArguments().getInt("image");
        this.mPairNumber = getArguments().getInt("pairNumber");

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCardImage = view.findViewById(R.id.imageViewCard);
        mCardImage.setImageResource(this.mBackImageId);
        mCardImage.setOnClickListener(this);



        this.mCardImage.getLayoutParams().height = this.height;
        this.mCardImage.getLayoutParams().width = this.width;

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mCardImage.getId()){
            ( (GameFragment) getParentFragment() ).clickOnCard(this.mID);
        }
    }

    public void setCardVisibility(boolean visible){
        if(visible){
            this.mCardImage.setImageResource(this.mImageId);
            this.mCardVisible = true;
        }
        else{
            this.mCardImage.setImageResource(this.mBackImageId);
            this.mCardVisible = false;
        }
    }

    public void resizeCard(int newWidth, int newHeight) {
        this.height = newHeight;
        this.width = newWidth;
    }

    public boolean isCardVisible(){
        return this.mCardVisible;
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
