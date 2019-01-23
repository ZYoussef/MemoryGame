package memory.android.istia.memorygame.game;

import android.util.ArraySet;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import memory.android.istia.memorygame.utils.SharedPreferenceManager;

public class ScoreManager {

    private static ScoreManager instance =null;
    private ArrayList<Integer> scores;

    private ScoreManager()
    {
        this.scores = new ArrayList<>();
        loadScoresFromSharedPreference();
    }

    private void loadScoresFromSharedPreference() {
        Set<String> set = null;
        set = SharedPreferenceManager.read(SharedPreferenceManager.Settings.SCORES, set);

        for(String str : set){
            this.scores.add(Integer.parseInt(str));
        }

        Log.d("test", scores.size() +  " scores loaded");

    }

    public static ScoreManager getInstance()
    {
        if (instance == null)
        {
            instance = new ScoreManager();
        }
        return instance;
    }

    public void addToScore(int score){

        if( this.scores.size() < 10){
            this.scores.add(score);
            return;
        }

        int index =  this.scores.size() + 1;
        for(int i = 0; i <  this.scores.size(); i++){
            if( score >  this.scores.get(i)) index = i;
        }

        if(index <=  this.scores.size()){
            this.scores.add(score);
            this.scores.remove(index);
        }
    }

    private void saveScores(){
        Collections.sort( this.scores);

        ArraySet<String> set = new ArraySet<>();

        for(int score :  this.scores){
            set.add(String.valueOf(score));
            Log.d("test", "saving " + score);
        }

        SharedPreferenceManager.write(SharedPreferenceManager.Settings.SCORES, set);
        Log.d("test", set.size() + " scores saved");
    }

    public ArrayList<Integer> getScores() {
        loadScoresFromSharedPreference();
        Log.d("test",  this.scores+ " here");
        return this.scores;
    }
}
