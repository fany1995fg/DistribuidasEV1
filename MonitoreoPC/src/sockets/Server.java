package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			
			public void detener(String ip){
				try {
					for (Socket aux : clientes) {
						String ipCliente = aux.getInetAddress().toString();
						System.out.println("aux->" + ipCliente + " - ip-> " + ip);
					
						if(ipCliente.equals(ip)){
							ObjectOutputStream ous =	new ObjectOutputStream(aux.getOutputStream());
							ObjectInputStream ois =	new ObjectInputStream(aux.getInputStream());
							ous.writeObject("4");
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			 public void captura(String ip/*,String fileName*/) {
				try {
					for (Socket aux : clientes) {
						String ipCliente = aux.getInetAddress().toString();
						System.out.println("aux->" + ipCliente + " - ip-> " + ip);
					
						if(ipCliente.equals(ip)){
							ObjectOutputStream ous =	new ObjectOutputStream(aux.getOutputStream());
							ObjectInputStream ois =	new ObjectInputStream(aux.getInputStream());
							ous.writeObject("5");
						
							/*
							Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
							Rectangle screenRectangle = new Rectangle(screenSize);
							Robot robot = new Robot();
							BufferedImage image = robot.createScreenCapture(screenRectangle);
							ImageIO.write(image, "png", new File(fileName));*/
							}
							

						}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			 public void paint(String ip){
					try {
						for (Socket aux : clientes) {
							String ipCliente = aux.getInetAddress().toString();
							System.out.println("aux->" + ipCliente + " - ip-> " + ip);
						
							if(ipCliente.equals(ip)){
								ObjectOutputStream ous =	new ObjectOutputStream(aux.getOutputStream());
								ObjectInputStream ois =	new ObjectInputStream(aux.getInputStream());
								ous.writeObject("6");
								
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 
			 public void notepad(String ip){
					try {
						for (Socket aux : clientes) {
							String ipCliente = aux.getInetAddress().toString();
							System.out.println("aux->" + ipCliente + " - ip-> " + ip);
						
							if(ipCliente.equals(ip)){
								ObjectOutputStream ous =	new ObjectOutputStream(aux.getOutputStream());
								ObjectInputStream ois =	new ObjectInputStream(aux.getInputStream());
								ous.writeObject("7");
								
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 
			 public void Procesos(String ip){
					try {
						for (Socket aux : clientes) {
							String ipCliente = aux.getInetAddress().toString();
							System.out.println("aux->" + ipCliente + " - ip-> " + ip);
						
							if(ipCliente.equals(ip)){
								ObjectOutputStream ous =	new ObjectOutputStream(aux.getOutputStream());
								ObjectInputStream ois =	new ObjectInputStream(aux.getInputStream());
								ous.writeObject("8");
								 String consola = System.getenv("windir")+"\\System32\\"+"tasklist.exe";
								  // Ejecutamos el comando
								  Process proceso=Runtime.getRuntime().exec(consola);
								  //OBTENEMOS EL BUFFER DE SALIDA
								  BufferedReader entrada = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
								  String tmp;
								  while((tmp=entrada.readLine())!=null){
								   System.out.println(tmp);
								  }
								  entrada.close();
								 }
							}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
		 
			 public void traer(String ip){
					try {
						for (Socket aux : clientes) {
							String ipCliente = aux.getInetAddress().toString();
							System.out.println("aux->" + ipCliente + " - ip-> " + ip);
						
							if(ipCliente.equals(ip)){
								ObjectOutputStream ous =	new ObjectOutputStream(aux.getOutputStream());
								ObjectInputStream ois =	new ObjectInputStream(aux.getInputStream());
								ous.writeObject("9");
								
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 
			 
			 
			
		}
