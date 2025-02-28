package actividad3_10servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Actividad3_10Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(6001);
        System.out.println("Servidor iniciado...");

        int numero = (int) (1 + 25 * Math.random());
        System.out.println("NUMERO A ADIVINAR=> " + numero);

        ObjetoCompartido objeto = new ObjetoCompartido(numero);
        int id = 0;
        while (true) {
            Socket cliente = new Socket();
            cliente = servidor.accept();
            id++;
            HiloServidorAdivina hilo = new HiloServidorAdivina(cliente, objeto, id);
            hilo.start();
        }
    }
}