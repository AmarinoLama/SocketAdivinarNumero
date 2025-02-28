package actividad3_10servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloServidorAdivina extends Thread {

    ObjectInputStream fentrada;
    ObjectOutputStream fsalida;

    Socket socket = null;

    // Objeto para gestionar la partida
    ObjetoCompartido objeto;

    // ID del cliente (el jugador)
    int identificador;
    int intentos = 0;

    public HiloServidorAdivina(Socket s, ObjetoCompartido objeto, int id) {
        this.socket = s;
        this.objeto = objeto;
        this.identificador = id;
        try {
            fsalida = new ObjectOutputStream(socket.getOutputStream());
            fentrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR DE E/S en HiloServidor: " + e.getMessage());
        }
    }

    public void run() {

        try {
            System.out.println("=>Cliente conectado: " + identificador);
            int intentos = 0;

            Datos datos = new Datos("Adivina un NUMERO ENTRE 1 Y 25", intentos, identificador);

            if (objeto.seAcabo()) {
                datos.setCadena("LO SENTIMOS, EL JUEGO HA TERMINADO, YA HAN ADIVINADO EL NUMERO");
                datos.setJuega(false);
            }

            fsalida.reset();
            fsalida.writeObject(datos);

            while (!objeto.seAcabo() && !(datos.getIntentos() == 5)) {

                int numecli = 0;

                Datos d = (Datos) fentrada.readObject();
                numecli = Integer.parseInt(d.getCadena());

                String cad = objeto.nuevaJugada(identificador, numecli);
                intentos++;
                datos = new Datos(cad, intentos, identificador);

                if (objeto.seAcabo()) {
                    datos.setJuega(false);
                    if (identificador == objeto.getGanador())
                        datos.setGana(true);
                }

                fsalida.reset();
                fsalida.writeObject(datos);

            }

            if (objeto.seAcabo()) {
                System.out.println("EL JUEGO SE HA ACABADO.....");
                System.out.println("\t==>Desconecta: " + identificador);
            }

            fsalida.close();
            fentrada.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en Hilo al cerrar cliente: " + identificador);
            System.out.println("Texto del error: " + e.getMessage());
        }
    }
}