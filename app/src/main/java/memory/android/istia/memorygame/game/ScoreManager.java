package memory.android.istia.memorygame.game;

import android.util.ArraySet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import memory.android.istia.memorygame.enums.EnumDifficulty;
import memory.android.istia.memorygame.enums.EnumSharedPreferences;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

public class ScoreManager {

    private static int score_to_save = 3;

    private static ScoreManager instance =null;

    private ArrayList<Integer> scores_easy;
    private ArrayList<Integer> scores_medium;
    private ArrayList<Integer> scores_hard;

    private ScoreManager()
    {
        this.scores_easy = new ArrayList<>();
        this.scores_medium = new ArrayList<>();
        this.scores_hard = new ArrayList<>();

        loadScoresFromSharedPreference();
    }

    private void loadScoresFromSharedPreference() {
        Set<String> set = null;

        set = SharedPreferenceManager.read(EnumSharedPreferences.SCORES_EASY, set);
        for(String str : set){
            this.scores_easy.add(Integer.parseInt(str));
        }

        set = SharedPreferenceManager.read(EnumSharedPreferences.SCORES_MEDIUM, set);
        for(String str : set){
            this.scores_medium.add(Integer.parseInt(str));
        }

        set = SharedPreferenceManager.read(EnumSharedPreferences.SCORES_HARD, set);
        for(String str : set){
            this.scores_hard.add(Integer.parseInt(str));
        }

    }

    public static ScoreManager getInstance()
    {
        if (instance == null)
        {
            instance = new ScoreManager();
        }
        return instance;
    }

    public void addToScore(int score, EnumDifficulty difficulty){
        ArrayList<Integer> scores;
        switch(difficulty){
            case EASY: scores = this.scores_easy; break;
            case MEDIUM: scores = this.scores_medium; break;
            case HARD: scores = this.scores_hard; break;
            default: scores = this.scores_easy;
        }

        if( scores.size() < score_to_save){
            scores.add(score);
            saveScores();
            return;
        }

        int index =  scores.size() + 1;
        for(int i = 0; i <  scores.size(); i++){
            if( score >  scores.get(i)) index = i;
        }

        if(index <=  scores.size()){
            scores.add(score);
            scores.remove(index);
        }

        saveScores();
    }

    private void saveScores(){
        Collections.sort( this.scores_easy);
        Collections.sort( this.scores_medium);
        Collections.sort( this.scores_hard);

        ArraySet<String> set = new ArraySet<>();
        for(int score :  this.scores_easy){
            set.add(String.valueOf(score));
        }
        SharedPreferenceManager.write(EnumSharedPreferences.SCORES_EASY, set);

        set = new ArraySet<>();
        for(int score :  this.scores_medium){
            set.add(String.valueOf(score));
        }
        SharedPreferenceManager.write(EnumSharedPreferences.SCORES_MEDIUM, set);

        set = new ArraySet<>();
        for(int score :  this.scores_hard){
            set.add(String.valueOf(score));
        }
        SharedPreferenceManager.write(EnumSharedPreferences.SCORES_HARD, set);
    }

    public ArrayList<Integer> getScores_easy() {
        loadScoresFromSharedPreference();
        return this.scores_easy;
    }

    public ArrayList<Integer> getScores_medium() {
        loadScoresFromSharedPreference();
        return this.scores_medium;
    }

    public ArrayList<Integer> getScores_hard() {
        loadScoresFromSharedPreference();
        return this.scores_hard;
    }
}
