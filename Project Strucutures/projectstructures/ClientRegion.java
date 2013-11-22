package projectstructures;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientRegion<K> extends Region<K>{
	

    private static ServerSocket servidor; //
    private static Socket cliente; //Socket para conectarse con el cliente
    private static String ip = "127.0.0.1"; //ip a la cual se conecta
    
    public static Cliente main; 
    
    public ClientRegion(){
    
    
    
        ExecutorService executor = Executors.newCachedThreadPool(); 
 
        try {
            System.out.println("Buscando Servidor ...");
            cliente = new Socket(InetAddress.getByName(ip), 11111); //comunicarme con el servidor
            System.out.println("Conectado a :" + cliente.getInetAddress().getHostName());
    
           
            
            //Ejecucion de los Threads
            executor.execute(new ThreadRecibe(cliente, main));
            executor.execute(new ThreadEnvia(cliente, main)); 
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } //Fin del catch
        finally {
        }
        executor.shutdown();
    }
}
































}
