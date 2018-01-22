package client;

/**
 * Created by User on 16.09.2017
 */
public enum DifficultyLevel {
    Elementary, Easy, Medium, Hard, Master;

    public String toRussian(){
        switch (this){
            case Elementary: return "Начальный";
            case Easy: return "Легкий";
            case Medium: return "Средний";
            case Hard: return "Сложный";
            case Master: return "Очень сложный";
        }
        return null;
    }
}
