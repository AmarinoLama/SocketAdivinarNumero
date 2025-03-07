package actividad3_10Cliente;

import actividad3_10servidor.Datos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {


    public static void main(String[] args) {

        try {
            System.out.println("PROGRAMA CLIENTE INICIADO....");
            Socket cliente = new Socket("localhost", 6001);

            ObjectOutputStream fsalida = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream fentrada = new ObjectInputStream(cliente.getInputStream());
            Scanner sc = new Scanner(System.in);

            Datos datos = (Datos) fentrada.readObject();

            while (datos.isJuega() && !datos.isGana() && datos.getIntentos() < 5) {
                System.out.println(datos.getCadena());
                datos.setCadena(sc.nextLine());
                fsalida.writeObject(datos);

                datos = (Datos) fentrada.readObject();
            }

            System.out.println(datos.getCadena());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en Cliente: " + e.getMessage());
        }
    }
}