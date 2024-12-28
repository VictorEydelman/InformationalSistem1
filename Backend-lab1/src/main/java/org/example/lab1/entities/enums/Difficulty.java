package org.IS.lab1.entities.enums;

public enum Difficulty {
    EASY,
    NORMAL,
    HARD,
    VERY_HARD,
    IMPOSSIBLE;

    public Difficulty getNextDifficulty() {
        int nextDifficulty = (this.ordinal() + 1);
        if(nextDifficulty >= Difficulty.values().length){
            return Difficulty.values()[nextDifficulty-1];
        } else {
            return Difficulty.values()[nextDifficulty];
        }
    }
    public Difficulty getLastDifficulty() {
        int lastDifficulty = (this.ordinal() - 1);
        if(lastDifficulty <= -1){
            return Difficulty.values()[lastDifficulty+1];
        } else {
            return Difficulty.values()[lastDifficulty];
        }
    }
}
