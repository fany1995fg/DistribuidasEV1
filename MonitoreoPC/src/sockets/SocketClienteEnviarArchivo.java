package sockets;

import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

public class SocketClienteEnviarArchivo {

	private final String HOST = JOptionPane.showInputDialog("Introduce Ip del Cliente") ;
	
	private final Integer PUERTO = 13;

	public SocketClienteEnviarArchivo(String ruta) {
		try {
			Socket cliente = new Socket(HOST, PUERTO);

			//Flujos de envio de Objetos 
			ObjectOutputStream ous =
				new ObjectOutputStream(cliente.getOutputStream());
		
			ObjectInputStream ois = 
				new ObjectInputStream(cliente.getInputStream());

			
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

			
			//Se obtiene los bytes del archivo		
			File f = new File(ruta);

			//Enviamos el nombre
			ous.writeObject(f.getName());
			
			FileInputStream fis = new FileInputStream(f);
			
			//Se quita los bytes para enviarlo al socket server
			int aux = -1;
			while( (aux = fis.read()) != -1){
				salida.write(aux);
			}
			
			fis.close();
			
			salida.close();
			cliente.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	

	

}
