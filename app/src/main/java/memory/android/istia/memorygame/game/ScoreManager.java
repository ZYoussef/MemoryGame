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
        loadScoresFromSharedPreference();
    }

    private void loadScoresFromSharedPreference() {
        Set<String> set = null;
        set = SharedPreferenceManager.read(SharedPreferenceManager.Settings.SCORES, set);
        scores = new ArrayList<Integer>();

        for(String str : set){
            scores.add(Integer.getInteger(str));
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

        if(scores.size() < 10){
            scores.add(score);
            return;
        }

        int index = scores.size() + 1;
        for(int i = 0; i < scores.size(); i++){
            if( score > scores.get(i)) index = i;
        }

        if(index <= scores.size()){
            scores.add(score);
            scores.remove(index);
        }
    }

    private void saveScores(){
        Collections.sort(scores);

        ArraySet<String> set = new ArraySet<>();

        for(int score : scores){
            set.add(String.valueOf(score));
            Log.d("test", "saving " + score);
        }

        SharedPreferenceManager.write(SharedPreferenceManager.Settings.SCORES, set);
        Log.d("test", set.size() + " scores saved");
    }

    public ArrayList<Integer> getScores() {
        loadScoresFromSharedPreference();
        return this.scores;
    }
}
