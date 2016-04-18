package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


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
				//----------Apagar Equipo-----------//	
				//1--> Se refiere al primer boton
				if(msg.equals("1")){
					Runtime.getRuntime().exec("shutdown -s -t 3600");
				}
				//----------Reiniciar Equipo-----------//	
				else if(msg.equals("2")){
					//Runtime.getRuntime().exec("shutdown -r");
				}
				//-----------Enviar Mensaje-----------//	
				
				else if(msg.equals("3")){
					String mensaje = ois.readObject().toString();
					JOptionPane.showMessageDialog(null, mensaje);
				}
				//-----------Bloquear Pantalla-----------//	
				else if(msg.equals("4")){
					Runtime.getRuntime().exec("rundll32.exe user32.dll,LockWorkStation");
					}
				//-----------Paint-----------//		
				else if(msg.equals("6")){
                Runtime.getRuntime().exec("mspaint");
				}
				//-----------Notepad-----------//		
				else if(msg.equals("7")){
                Runtime.getRuntime().exec("notepad");
				}
				//----------VER PROCESOS--------//
				//else if (msg.equals("8")){
					//String consola = System.getenv("windir")+"\\System32\\"+"tasklist.exe";
					//Runtime.getRuntime().exec(consola);
				//}
				
				//--------BLOQUEAR CHROME--------//
				else if(msg.equals("10")){
					Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
				}
				//--------BLOQUEAR WORD--------//
				else if(msg.equals("11")){
					Runtime.getRuntime().exec("taskkill /f /im WINWORD.exe");
				}
				//--------BLOQUEAR EXCEL--------//
				else if(msg.equals("12")){
					Runtime.getRuntime().exec("taskkill /f /im excel.exe");
				}
				//--------ANULAR INSTRUCCIONES--------//
				else if(msg.equals("13")){
					Runtime.getRuntime().exec("shutdown -a");
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
