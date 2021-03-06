package Servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author JAIR
 */
public class Servidor {
   // ServidorArchivoSocket s = new ServidorArchivoSocket();

    //Inicializamos el puerto y el numero maximo de conexciones que acepta el servidor
    private final int puerto = 2027;
    private final int noConexiones = 20;
    //Creamos una lista de sockets, donde guardaremos los sockets que se vayan conectando
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();

    //Funcion para que el servidor empieze a recibir conexiones de clientes
    public void escuchar() {
        
        try {       
            ServidorArchivoSocket s = new ServidorArchivoSocket();
            s.start();
            //reamos el socket servidor
            ServerSocket servidor = new ServerSocket(puerto, noConexiones);
            //Ciclo infinito para estar escuchando por nuevos clientes
            while (true) {
                System.out.println("Escuchando....");
                //Cuando un cliente se conecte guardamos el socket en nuestra lista
                Socket cliente = servidor.accept();
                usuarios.add(cliente);
                //Instanciamos un hilo que estara atendiendo al cliente y lo ponemos a escuchar
                
                Runnable run = new HiloServidor(cliente, usuarios);
                Thread hilo = new Thread(run);
                hilo.start();
                /*ServidorArchivoSocket s = new ServidorArchivoSocket();
                s.iniciar();*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Funcion main para correr el servidor
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.escuchar();
        

        //ServidorArchivoSocket servidorArchivo = new ServidorArchivoSocket();
        //servidorArchivo.ser();
        //System.out.println("kjsbljbdslkjbas");
    }
}
