package br.com.jfce.apibancotalentos.model;

public enum Genero {
    MASCULINO("M"),
    FEMININO("F");

    private String genero;
    Genero(String genero){
        this.genero = genero;
    }

    public String getGenero(){
        return this.genero;
    }
}
