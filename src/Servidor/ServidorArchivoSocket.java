package Servidor;

import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

class ServidorArchivoSocket extends Thread{

    //JFileChooser chooser;
    //String ruta;
    /*public static void main(String[] args) {
        //ser();
    }*/

   
    @Override
    public void run() {
        iniciar();
        
    }

    public ServidorArchivoSocket() {
       // System.out.println("Servidor De Archivos Iniciado...");
        //iniciar();
    }

    public void iniciar() {
        System.out.println("Inicia Servidor De Archivos...");
        ServerSocket server;
        Socket connection;

        DataOutputStream output;
        BufferedInputStream bis;
        BufferedOutputStream bos;

        byte[] receivedData;
        int in;
        String file;

        try {
            //Servidor Socket en el puerto 5000
            server = new ServerSocket(5000);
            JFileChooser chooser = new JFileChooser();
            //chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setDialogTitle("Elija donde guardar el archivo recibido");

            while (true) {
                //Aceptar conexiones
                connection = server.accept();
                //Buffer de 1024 bytes
                receivedData = new byte[1024];
                bis = new BufferedInputStream(connection.getInputStream());
                DataInputStream dis = new DataInputStream(connection.getInputStream());
                //Recibimos el nombre del fichero
                file = dis.readUTF();

                file = file.substring(file.indexOf('\\') + 1, file.length());
                //Para guardar fichero recibido
                chooser.showOpenDialog(chooser);
                //Object a = chooser.getCurrentDirectory().getPath();
                Object a = chooser.getSelectedFile();
                System.out.println(chooser.getSelectedFile() + "-*-*-*-*");
                String ruta = String.valueOf(a);
                System.out.println("Ruta:: " + ruta);
                bos = new BufferedOutputStream(new FileOutputStream(ruta + "/" + file));
                while ((in = bis.read(receivedData)) != -1) {
                    bos.write(receivedData, 0, in);
                }
                bos.close();
                dis.close();
                JOptionPane.showMessageDialog(null, "Se guardo correctamente el archivo en: " + ruta);
                
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    /*   public static void ser() {
    System.out.println("Inicia Servidor De Archivos...");
    ServerSocket server;
    Socket connection;
    DataOutputStream output;
    BufferedInputStream bis;
    BufferedOutputStream bos;
    byte[] receivedData;
    int in;
    String file;
    try {
    //Servidor Socket en el puerto 5000
    server = new ServerSocket(5000);
    JFileChooser chooser = new JFileChooser();
    //chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    chooser.setDialogTitle("Elija donde guardar el archivo recibido");
    while (true) {
    //Aceptar conexiones
    connection = server.accept();
    //Buffer de 1024 bytes
    receivedData = new byte[1024];
    bis = new BufferedInputStream(connection.getInputStream());
    DataInputStream dis = new DataInputStream(connection.getInputStream());
    //Recibimos el nombre del fichero
    file = dis.readUTF();
    file = file.substring(file.indexOf('\\') + 1, file.length());
    //Para guardar fichero recibido
    chooser.showOpenDialog(chooser);
    //Object a = chooser.getCurrentDirectory().getPath();
    Object a = chooser.getSelectedFile();
    System.out.println(chooser.getSelectedFile() + "-*-*-*-*");
    String ruta = String.valueOf(a);
    System.out.println("Ruta:: " + ruta);
    bos = new BufferedOutputStream(new FileOutputStream(ruta + "/" + file));
    while ((in = bis.read(receivedData)) != -1) {
    bos.write(receivedData, 0, in);
    }
    bos.close();
    dis.close();
    JOptionPane.showMessageDialog(null, "Se guardo correctamente el archivo en: "+ruta);
    return;
    }
    } catch (Exception e) {
    System.err.println(e);
    }
    }*/
}
