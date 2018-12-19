package model;

/**
 * Created by Александр on 19.12.2018 in 20:34.
 */
public enum Zone {
    Zone1(" "), Zone2("миапкетьронг"), Zone3("шлбувс"), Zone4("цычщдю"), Zone5("ёйфяжэзхъ");

    private final String chars;
    Zone(String chars){
        this.chars = chars;
    }

    public String getChars() {
        return chars;
    }}
