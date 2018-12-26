package client;

import model.Difficulty;


public enum DifficultyLevel {
    Easy, Medium, Hard;

    public String toRussian(){
        switch (this){
            case Easy: return "Легкий";
            case Medium: return "Средний";
            case Hard: return "Сложный";
        }
        return null;
    }

    public static DifficultyLevel getLevelByName(String name){
        switch (name){
            case "Легкий": return Easy;
            case "Средний": return Medium;
            case "Сложный": return Hard;
        }
        return null;
    }
}
