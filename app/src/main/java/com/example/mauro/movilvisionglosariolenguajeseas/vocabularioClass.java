package com.example.mauro.movilvisionglosariolenguajeseas;

/**
 * Created by Mauro on 22/10/2018.
 */

public class vocabularioClass {

    int fondoletra;
    String letra;
    int imagen1;
    String palabra;

    public vocabularioClass() {
    }

    public vocabularioClass(int fondoletra, String letra, int imagen1, String palabra) {
        this.fondoletra = fondoletra;
        this.letra = letra;
        this.imagen1 = imagen1;
        this.palabra = palabra;
    }

    public int getFondoletra() {
        return fondoletra;
    }

    public void setFondoletra(int fondoletra) {
        this.fondoletra = fondoletra;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getImagen1() {
        return imagen1;
    }

    public void setImagen1(int imagen1) {
        this.imagen1 = imagen1;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
}
