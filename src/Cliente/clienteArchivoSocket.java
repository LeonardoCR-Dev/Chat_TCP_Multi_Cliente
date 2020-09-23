package Cliente;

import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;

class clienteArchivoSocket {

    
    public void iniCliente (){
        DataInputStream input;
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int in;
        byte[] byteArray;
        //Fichero a transferir
        javax.swing.JFileChooser ventanaEscojer = new javax.swing.JFileChooser();
        int seleccion = ventanaEscojer.showOpenDialog(ventanaEscojer);
        String path = ventanaEscojer.getSelectedFile().getAbsolutePath();

        
        final String filename = path;
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                /*
            
                 */
                final File localFile = new File(filename);
                Socket client = new Socket("localhost", 5000);
                bis = new BufferedInputStream(new FileInputStream(localFile));
                bos = new BufferedOutputStream(client.getOutputStream());
                //Enviamos el nombre del fichero
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                dos.writeUTF(localFile.getName());
                //Enviamos el fichero
                byteArray = new byte[8192];
                while ((in = bis.read(byteArray)) != -1) {
                    bos.write(byteArray, 0, in);
                }

                bis.close();
                bos.close();

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    
    }
}
