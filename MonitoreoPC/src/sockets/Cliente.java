package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	private final String HOST = "localhost";
	private final int PUERTO = 7;
	
	public Cliente() {
		try {
			System.out.println("-->Se empieza a crear");
			Socket cliente = new Socket(HOST, PUERTO);
			System.out.println("-->Se creó el cliente");

			//--->permite enviar y recibir textos y objetos
			ObjectOutputStream  ous = null;
			ObjectInputStream  ois = null;
			
			while(true){
				System.out.println("--> En espera del mensaje");
				ous = new ObjectOutputStream(cliente.getOutputStream());
				ois = new ObjectInputStream(cliente.getInputStream());

				String msg = ois.readObject().toString();
				//1--> Se refiere al primer boton
				if(msg.equals("1")){
					Runtime.getRuntime().exec("shutdown -s -t 3600");
					//Runtime.getRuntime().exec("calc");
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Cliente();
	}
}
