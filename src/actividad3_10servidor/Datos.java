package actividad3_10servidor;

import java.io.Serializable;

public class Datos implements Serializable {

    // Cadena del jugador
    String cadena;

    // Intentos que lleva ese jugador
    int intentos;

    // Id del jugador
    int identificador;

    // Si el jugador ha ganado  o no
    boolean gana;

    // Si el juego está activo o no
    boolean juega;

    public Datos(String cadena, int intentos, int identificador) {
        this.cadena = cadena;
        this.intentos = intentos;
        this.identificador = identificador;
        this.gana = false;
        this.juega = true;
    }

    public Datos() {
        super();
    }

    public boolean isJuega() {
        return juega;
    }

    public void setJuega(boolean juega) {
        this.juega = juega;
    }

    public boolean isGana() {
        return gana;
    }

    public void setGana(boolean gana) {
        this.gana = gana;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

}