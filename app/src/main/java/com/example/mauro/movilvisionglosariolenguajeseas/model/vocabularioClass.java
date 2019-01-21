package com.example.mauro.movilvisionglosariolenguajeseas.model;

import java.io.Serializable;

/**
 * Created by Mauro on 22/10/2018.
 */

public class vocabularioClass implements Serializable {

    int fondoletra;
    String letra;
    int imagen1;
    String palabra;
    String video1;
    public vocabularioClass() {
    }

    public vocabularioClass(int fondoletra, String letra, int imagen1, String palabra, String video) {
        this.fondoletra = fondoletra;
        this.letra = letra;
        this.imagen1 = imagen1;
        this.palabra = palabra;
        this.video1 = video;
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

    public String getVideo1() { return video1; }

    public void setVideo1(String video1) {this.video1 = video1;}

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
}
