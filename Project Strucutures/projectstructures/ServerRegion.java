package projectstructures;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerRegion<K>  extends Region<K>{
    private static ServerSocket servidor; //
    private static Socket conexion; //Socket para conectarse con el cliente
    private static String ip = "127.0.0.1"; //ip a la cual se conecta
    public static Server main; 
    
 public ServerRegion(){
       
 
        ExecutorService executor = Executors.newCachedThreadPool(); //Para correr los threads
 
        try {
            //main.mostrarMensaje("No se encuentra Servidor");
            servidor = new ServerSocket(11111, 100); 
            System.out.println("Esperando Cliente ...");

            //Bucle infinito para esperar conexiones de los clientes
            while (true){
                try {
                    conexion = servidor.accept(); //Permite al servidor aceptar conexiones        

                    //main.mostrarMensaje("Conexion Establecida");
                    System.out.println("Conectado a : " + conexion.getInetAddress().getHostName());

                    //Ejecucion de los threads
                    executor.execute(new ThreadRecibe(conexion, main)); //client
                    executor.execute(new ThreadEnvia(conexion, main));
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } //Fin del catch
        finally {
        }
        executor.shutdown();
    }
}


}
