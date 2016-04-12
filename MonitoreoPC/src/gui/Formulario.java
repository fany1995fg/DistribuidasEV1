package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sockets.Server;


import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JSeparator;
import javax.swing.Box;
import javax.swing.border.LineBorder;

public class Formulario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JButton btnApagar;
	private JButton btnReiniciar;
	private JButton btnBloquear;
	private JButton btnEnvArchivo;
	private JButton btnTraerArchivo;
	private JButton btnEnviarMensaje;
	private JButton btnBloquearProgramas;
	private JButton btnCapturarPantalla;
	private JButton btnVerProcesosRemotos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario frame = new Formulario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */


	private Server server = null;
	
	public Formulario() {
		setForeground(Color.WHITE);
		setBackground(UIManager.getColor("Button.disabledShadow"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1043, 390);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/off.jpg")));
		btnApagar.setForeground(Color.BLACK);
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnApagar.setBackground(Color.WHITE);
		btnApagar.setHorizontalAlignment(SwingConstants.LEFT);
		btnApagar.addActionListener(this);
		btnApagar.setBounds(390, 103, 151, 51);
		contentPane.add(btnApagar);
		
		btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.setToolTipText("");
		btnReiniciar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReiniciar.setBackground(Color.WHITE);
		btnReiniciar.setForeground(Color.BLACK);
		btnReiniciar.setHorizontalAlignment(SwingConstants.LEFT);
		btnReiniciar.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/restart1.png")));
		btnReiniciar.addActionListener(this);
		btnReiniciar.setBounds(390, 165, 151, 49);
		contentPane.add(btnReiniciar);
		
		btnBloquear = new JButton("Bloquear");
		btnBloquear.setHorizontalAlignment(SwingConstants.LEFT);
		btnBloquear.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/adblock1.png")));
		btnBloquear.setForeground(Color.BLACK);
		btnBloquear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBloquear.setBackground(Color.WHITE);
		btnBloquear.addActionListener(this);
		btnBloquear.setBounds(394, 236, 151, 49);
		contentPane.add(btnBloquear);
		
		btnEnviarMensaje = new JButton("Enviar Mensaje");
		btnEnviarMensaje.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/mensaje.png")));
		btnEnviarMensaje.setForeground(Color.BLACK);
		btnEnviarMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEnviarMensaje.setBackground(Color.WHITE);
		btnEnviarMensaje.addActionListener(this);
		btnEnviarMensaje.setBounds(551, 234, 182, 51);
		contentPane.add(btnEnviarMensaje);
		
		btnTraerArchivo = new JButton("Traer Archivo");
		btnTraerArchivo.setHorizontalAlignment(SwingConstants.LEFT);
		btnTraerArchivo.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/traer.png")));
		btnTraerArchivo.setForeground(Color.BLACK);
		btnTraerArchivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTraerArchivo.setBackground(Color.WHITE);
		btnTraerArchivo.addActionListener(this);
		btnTraerArchivo.setBounds(551, 165, 182, 49);
		contentPane.add(btnTraerArchivo);
		
		btnEnvArchivo = new JButton("Enviar Archivo");
		btnEnvArchivo.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/enviar-archivos.png")));
		btnEnvArchivo.setForeground(Color.BLACK);
		btnEnvArchivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEnvArchivo.setBackground(Color.WHITE);
		btnEnvArchivo.addActionListener(this);
		btnEnvArchivo.setBounds(551, 105, 182, 49);
		contentPane.add(btnEnvArchivo);
		
		btnVerProcesosRemotos = new JButton("Ver Procesos Remotos");
		btnVerProcesosRemotos.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerProcesosRemotos.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/process_accept.png")));
		btnVerProcesosRemotos.setForeground(Color.BLACK);
		btnVerProcesosRemotos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVerProcesosRemotos.setBackground(Color.WHITE);
		btnVerProcesosRemotos.addActionListener(this);
		btnVerProcesosRemotos.setBounds(743, 236, 221, 49);
		contentPane.add(btnVerProcesosRemotos);
		
		btnCapturarPantalla = new JButton("Capturar Pantalla");
		btnCapturarPantalla.setHorizontalAlignment(SwingConstants.LEFT);
		btnCapturarPantalla.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/screen.png")));
		btnCapturarPantalla.setForeground(Color.BLACK);
		btnCapturarPantalla.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCapturarPantalla.setBackground(Color.WHITE);
		btnCapturarPantalla.addActionListener(this);
		btnCapturarPantalla.setBounds(743, 165, 221, 49);
		contentPane.add(btnCapturarPantalla);
		
		btnBloquearProgramas = new JButton("Bloquear Programas");
		btnBloquearProgramas.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/Access-Denied.png")));
		btnBloquearProgramas.setForeground(Color.BLACK);
		btnBloquearProgramas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBloquearProgramas.setBackground(Color.WHITE);
		btnBloquearProgramas.addActionListener(this);
		btnBloquearProgramas.setBounds(739, 105, 225, 49);
		contentPane.add(btnBloquearProgramas);
		
		JLabel lblNewLabel = new JLabel("MAQUINAS CONECTADAS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(40, 57, 256, 21);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 339, 133);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IP", "Puerto", "Estado"
			}
		));
		scrollPane.setViewportView(table);
		
		//Se crea el server y se le comparte la tabla
		server = new Server(table);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setToolTipText("");
		verticalBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verticalBox.setForeground(SystemColor.activeCaption);
		verticalBox.setBorder(new LineBorder(SystemColor.activeCaption));
		verticalBox.setBackground(SystemColor.activeCaption);
		verticalBox.setBounds(375, 88, 617, 231);
		contentPane.add(verticalBox);
		
		JLabel lblNewLabel_1 = new JLabel("Conexiones Verificadas");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(375, 57, 151, 27);
		contentPane.add(lblNewLabel_1);
		
		//Crea un hilo(No interrumpa a la GUI)
		//Ejecucion en paralelo a la GUI
		
		Thread hilo = new Thread(new Runnable() {
			public void run() {
				server.levantaServer();
			}
		});
		hilo.start();
		
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnVerProcesosRemotos) {
			do_btnVerProcesosRemotos_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnCapturarPantalla) {
			do_btnCapturarPantalla_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnBloquearProgramas) {
			do_btnBloquearProgramas_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnEnviarMensaje) {
			do_btnEnviarMensaje_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnTraerArchivo) {
			do_btnTraerArchivo_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnEnvArchivo) {
			do_btnEnvArchivo_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnBloquear) {
			do_btnBloquear_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnReiniciar) {
			do_btnReiniciar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnApagar) {
			do_btnApagar_actionPerformed(arg0);
		}
	}
	protected void do_btnApagar_actionPerformed(ActionEvent arg0) {
		int fila = table.getSelectedRow();
		
		if(fila != -1){
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			String ip = m.getValueAt(fila, 0).toString();
			server.apagar(ip);
			
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		}
	}
	
	protected void do_btnReiniciar_actionPerformed(ActionEvent arg0) {
		int fila = table.getSelectedRow();
		
		if(fila != -1){
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			String ip = m.getValueAt(fila, 0).toString();
			
			server.reiniciar(ip);
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		}
	}
	protected void do_btnBloquear_actionPerformed(ActionEvent arg0) {
	}
	protected void do_btnEnvArchivo_actionPerformed(ActionEvent arg0) {
		int fila = table.getSelectedRow();
		
		if(fila != -1){
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			String ip = m.getValueAt(fila, 0).toString();
			
			FrmSeleccionFile file = new FrmSeleccionFile();	
			String filePath = file.lbl.getText();
			
			System.out.println(filePath);
			//server.enviarFile(ip,filePath);	
			
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		}

	}
	protected void do_btnTraerArchivo_actionPerformed(ActionEvent arg0) {
	}
	protected void do_btnEnviarMensaje_actionPerformed(ActionEvent arg0) {
		int fila = table.getSelectedRow();
		
		if(fila != -1){
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			String ip = m.getValueAt(fila, 0).toString();
			
			String mensaje = JOptionPane.showInputDialog("Ingrese mensaje");
	
			if(mensaje != null && mensaje.trim().length()>0){
				server.enviarMensaje(ip,mensaje);	
			}else{
				JOptionPane.showMessageDialog(this, "Ingrese mensaje");
			}
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		}

	}
	protected void do_btnBloquearProgramas_actionPerformed(ActionEvent arg0) {
	}
	protected void do_btnCapturarPantalla_actionPerformed(ActionEvent arg0) {
	}
	protected void do_btnVerProcesosRemotos_actionPerformed(ActionEvent arg0) {
	}
}
