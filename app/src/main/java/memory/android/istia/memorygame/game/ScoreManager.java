package memory.android.istia.memorygame.game;

import android.util.ArraySet;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import memory.android.istia.memorygame.enums.EnumDifficulty;
import memory.android.istia.memorygame.enums.EnumSettings;
import memory.android.istia.memorygame.utils.SharedPreferenceManager;

/**
 * ScoreManager - Singleton
 * Gère l'enregistrement des scores pour chaque niveau de difficulté
 */
public class ScoreManager {

    private static ScoreManager instance =null;

    private ArrayList<Integer> scoresEasy;
    private ArrayList<Integer> scoresMedium;
    private ArrayList<Integer> scoresHard;

    private ScoreManager()
    {
        this.scoresEasy = new ArrayList<>();
        this.scoresMedium = new ArrayList<>();
        this.scoresHard = new ArrayList<>();

        loadScoresFromSharedPreference();
    }

    private void loadScoresFromSharedPreference() {
        Set<String> set = null;

        set = SharedPreferenceManager.read(EnumSettings.SCORES_EASY, set);
        if(set != null && !set.isEmpty()){
            this.scoresEasy = new ArrayList<>();
            for(String str : set){
                this.scoresEasy.add(Integer.parseInt(str));
            }
        }


        set = SharedPreferenceManager.read(EnumSettings.SCORES_MEDIUM, set);
        if(set != null && !set.isEmpty()) {
            this.scoresMedium = new ArrayList<>();
            for (String str : set) {
                this.scoresMedium.add(Integer.parseInt(str));
            }
        }

        set = SharedPreferenceManager.read(EnumSettings.SCORES_HARD, set);
        if(set != null && !set.isEmpty()) {
            this.scoresHard = new ArrayList<>();
            for (String str : set) {
                this.scoresHard.add(Integer.parseInt(str));
            }
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

    void addToScore(int score, EnumDifficulty difficulty){
        if(score > 0){
            ArrayList<Integer> scores;
            switch(difficulty){
                case EASY: scores = this.scoresEasy; break;
                case MEDIUM: scores = this.scoresMedium; break;
                case HARD: scores = this.scoresHard; break;
                default: scores = this.scoresEasy;
            }


            int scoreToSave = 3;
            if( scores.size() < scoreToSave){
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
    }

    private void saveScores(){

        ArraySet<String> set = new ArraySet<>();
        for(int score :  this.scoresEasy){
            set.add(String.valueOf(score));
        }
        SharedPreferenceManager.write(EnumSettings.SCORES_EASY, set);

        set = new ArraySet<>();
        for(int score :  this.scoresMedium){
            set.add(String.valueOf(score));
        }
        SharedPreferenceManager.write(EnumSettings.SCORES_MEDIUM, set);

        set = new ArraySet<>();
        for(int score :  this.scoresHard){
            set.add(String.valueOf(score));
        }
        SharedPreferenceManager.write(EnumSettings.SCORES_HARD, set);
    }

    public List<Integer> getScoresEasy() {
        loadScoresFromSharedPreference();
        Collections.sort( this.scoresEasy, Collections.reverseOrder());
        return this.scoresEasy;
    }

    public List<Integer> getScoresMedium() {
        loadScoresFromSharedPreference();
        Collections.sort( this.scoresMedium,Collections.reverseOrder());
        return this.scoresMedium;
    }

    public List<Integer> getScoresHard() {
        loadScoresFromSharedPreference();
        Collections.sort( this.scoresHard,Collections.reverseOrder());
        return this.scoresHard;
    }
}
