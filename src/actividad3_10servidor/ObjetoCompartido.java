package actividad3_10servidor;

public class ObjetoCompartido {

    private int numero;
    private boolean acabo;
    private int ganador = 0;

    public ObjetoCompartido(int numero) {
        this.numero = numero;
        this.acabo = false;
    }

    public synchronized boolean seAcabo() {
        return acabo;
    }

    public synchronized int getGanador() {
        return ganador;
    }

    public synchronized String nuevaJugada(int jugador, int suNumero) {
        String cad = "";

        if (!seAcabo()) {

            if (suNumero > numero) {
                cad = "Numero que has dado es demasiado grande";
            } else if (suNumero < numero) {
                cad = "Numero que has dado es demasiado pequeño";
            } else if (suNumero == numero) {
                cad = "Jugador " + jugador + " gana, adivinó el número: " + numero;
                acabo = true;
                ganador = jugador;
            }

        } else
            cad = " EL Jugador " + ganador + " adivinó el número: " + numero;

        return cad;
    }
}