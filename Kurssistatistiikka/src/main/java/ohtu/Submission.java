package ohtu;

public class Submission {
    private int week;
    private int[] exercises;
    private int hours;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }
    
    public int getHours() {
        return hours;
    }
    
    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }
    
    public int[] getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        String exercisesString = "";
        for (int i = 0; i < exercises.length; i++) {
            exercisesString+=" " + exercises[i];
        }
        
        return " viikko "+week+": tehtyjä tehtäviä yhteensä: "+exercises.length+", aikaa kuluui "+hours+" tuntia, tehdyt tehtävät: "+exercisesString;
    }
    
}