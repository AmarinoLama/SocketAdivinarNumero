package actividad3_10Cliente;

import actividad3_10servidor.Datos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {


    public static void main(String[] args) {

        try {
            System.out.println("PROGRAMA CLIENTE INICIADO....");
            Socket cliente = new Socket("localhost", 6001);

            ObjectOutputStream fsalida = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream fentrada = new ObjectInputStream(System.in);

            Datos datos = (Datos) fentrada.readObject();

            System.out.println(datos.getCadena());

            do {
                System.out.println("partida");
            } while (datos.isJuega());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en Cliente: " + e.getMessage());
        }
    }
}