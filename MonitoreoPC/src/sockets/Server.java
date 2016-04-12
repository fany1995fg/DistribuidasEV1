package sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Server {

	private final int PUERTO = 7;
	private JTable tabla;
	private Socket cliente = null;
	private LinkedList<Socket> clientes = new LinkedList<Socket>();
	
	public Server(JTable tabla) {
		this.tabla = tabla;
	}

	public void levantaServer() {
		try {
			ServerSocket server = new ServerSocket(PUERTO);
	
			while (true) {
				System.out.println("-->SE ESPERA A UN CLIENTE");
				
				cliente = server.accept();
				clientes.add(cliente);
				System.out.println("-->SE ATIENDE A UN CLIENTE");
				
				String ipCliente = cliente.getInetAddress().toString();
				
				System.out.println("--> Llego: " + ipCliente);
				
				//La ip se coloca en la tabla(GUI)
				DefaultTableModel m = (DefaultTableModel) tabla.getModel();
				m.addRow(new String[]{ ipCliente, "7","Conectado"});
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void apagar(String ip){
		try {
			for (Socket aux : clientes) {
				String ipCliente = aux.getInetAddress().toString();
				System.out.println("aux->" + ipCliente + " - ip-> " + ip);
			
				if(ipCliente.equals(ip)){
					ObjectOutputStream ous =	new ObjectOutputStream(aux.getOutputStream());
					ObjectInputStream ois =	new ObjectInputStream(aux.getInputStream());
					ous.writeObject("1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
			public void reiniciar(String ip){
				try {
					for (Socket aux : clientes) {
						String ipCliente = aux.getInetAddress().toString();
						System.out.println("aux->" + ipCliente + " - ip-> " + ip);
					
						if(ipCliente.equals(ip)){
							ObjectOutputStream ous =	new ObjectOutputStream(aux.getOutputStream());
							ObjectInputStream ois =	new ObjectInputStream(aux.getInputStream());
							ous.writeObject("2");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			public void enviarMensaje(String ip, String mensaje){
				try {
					for (Socket aux : clientes) {
						String ipCliente = aux.getInetAddress().toString();
						System.out.println("aux->" + ipCliente + " - ip-> " + ip);
					
						if(ipCliente.equals(ip)){
							ObjectOutputStream ous =	new ObjectOutputStream(aux.getOutputStream());
							ObjectInputStream ois =	new ObjectInputStream(aux.getInputStream());
							ous.writeObject("3");
							ous.writeObject(mensaje);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
