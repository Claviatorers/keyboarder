package model;


public enum Zone {
    Zone1(" "), Zone2("миапкетьронг"), Zone3("шлбувс"), Zone4("цычщдю"), Zone5("ёйфяжэзхъ");

    private final String chars;
    Zone(String chars){
        this.chars = chars;
    }

    public String getChars() {
        return chars;
    }}
