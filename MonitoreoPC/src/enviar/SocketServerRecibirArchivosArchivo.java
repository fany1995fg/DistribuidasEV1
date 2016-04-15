package enviar;

import java.net.*;
import java.io.*;

public class SocketServerRecibirArchivosArchivo {

	private final Integer PUERTO = 13;

	private Socket cliente;


	public SocketServerRecibirArchivosArchivo() {
		System.out.println("FileServer: esperando peticiones TCP/IP");
		System.out.println("_______________________________________");

		try {
			ServerSocket servidor = new ServerSocket(PUERTO);
			while (true) {

				cliente = servidor.accept();
				
				
				//Flujos de envio de Objetos 
				ObjectOutputStream ous =
					new ObjectOutputStream(cliente.getOutputStream());
			
				ObjectInputStream ois = 
					new ObjectInputStream(cliente.getInputStream());

				String nombre = (String)ois.readObject();

				
				// /esta escuchando hasta que
												// alguien se conecte
				System.out.println("------SE ATENDIO A UN CLIENTE--------------");
				System.out.println("EL CLIENTE " + cliente.getInetAddress());
				
				
				File archivoDestino = new File("C:/server/"+nombre);
				FileOutputStream fos = new FileOutputStream(archivoDestino);
				
				DataInputStream entrada =	new DataInputStream(cliente.getInputStream());
				int leido;
				while ((leido = entrada.read()) != -1) {
					fos.write(leido);
				}
				fos.close();
				
				
				cliente.close();


			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SocketServerRecibirArchivosArchivo();
	}

}
