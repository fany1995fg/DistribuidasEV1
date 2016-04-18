package gui;

import java.awt.Dimension;
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
import sockets.ServidorCaptura;




import sockets.ServidorCaptura;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JSeparator;
import javax.swing.Box;
import javax.swing.border.LineBorder;


import comandos.Captura;
import comandos.Procesos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;

public class Formulario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JButton btnApagar;
	private JButton btnReiniciar;
	private JButton btnDetener;
	private JButton btnEnvArchivo;
	private JButton btnTraerArchivo;
	private JButton btnEnviarMensaje;
	private JButton btnDetener2;
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
		setTitle("MonitorPC");
		setResizable(false);
		setForeground(Color.WHITE);
		setBackground(UIManager.getColor("Button.disabledShadow"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1043, 390);
		
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		
		// AGREGANDO UN TAMAÑO FIJO AL FORMULARIO //
		setMaximumSize ( new Dimension ( 600, 450 ) );
		setMinimumSize ( new Dimension ( 600, 450 ) );
	
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
		
		btnDetener = new JButton("Bloquear Pantalla");
		btnDetener.setHorizontalAlignment(SwingConstants.LEFT);
		btnDetener.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/Access-Denied.png")));
		btnDetener.setForeground(Color.BLACK);
		btnDetener.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDetener.setBackground(Color.WHITE);
		btnDetener.addActionListener(this);
		btnDetener.setBounds(741, 104, 223, 49);
		contentPane.add(btnDetener);
		
		btnEnviarMensaje = new JButton("Enviar Mensaje");
		btnEnviarMensaje.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/mensaje.png")));
		btnEnviarMensaje.setForeground(Color.BLACK);
		btnEnviarMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEnviarMensaje.setBackground(Color.WHITE);
		btnEnviarMensaje.addActionListener(this);
		btnEnviarMensaje.setBounds(551, 234, 182, 51);
		contentPane.add(btnEnviarMensaje);
		
		btnTraerArchivo = new JButton("Traer Archivo");
		btnTraerArchivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
					ClaseFrame miVentana = new ClaseFrame();
					miVentana.setTitle("");
					miVentana.setVisible(true);
				
			}
		});
		btnTraerArchivo.setHorizontalAlignment(SwingConstants.LEFT);
		btnTraerArchivo.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/traer.png")));
		btnTraerArchivo.setForeground(Color.BLACK);
		btnTraerArchivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTraerArchivo.setBackground(Color.WHITE);
		btnTraerArchivo.addActionListener(this);
		btnTraerArchivo.setBounds(551, 165, 182, 49);
		contentPane.add(btnTraerArchivo);
		
		btnEnvArchivo = new JButton("Enviar Archivo");
		btnEnvArchivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					FormularioEnvio view = new FormularioEnvio();
		            view.setVisible(true);
			}
		});
		btnEnvArchivo.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/enviar-archivos.png")));
		btnEnvArchivo.setForeground(Color.BLACK);
		btnEnvArchivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEnvArchivo.setBackground(Color.WHITE);
		btnEnvArchivo.addActionListener(this);
		btnEnvArchivo.setBounds(551, 105, 182, 49);
		contentPane.add(btnEnvArchivo);
		
		btnVerProcesosRemotos = new JButton("Ver Procesos Remotos");
		btnVerProcesosRemotos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AdministradordeTareas view = new AdministradordeTareas();
	            view.setVisible(true);
			}
		});
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
		
		btnDetener2 = new JButton("Detener");
		btnDetener2.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/adblock1.png")));
		btnDetener2.setForeground(Color.BLACK);
		btnDetener2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDetener2.setBackground(Color.WHITE);
		btnDetener2.addActionListener(this);
		btnDetener2.setBounds(390, 235, 151, 49);
		contentPane.add(btnDetener2);
		
		JLabel lblNewLabel = new JLabel("MAQUINAS CONECTADAS");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(40, 57, 256, 21);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 339, 217);
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
		verticalBox.setBorder(new LineBorder(new Color(224, 255, 255), 2));
		verticalBox.setBackground(SystemColor.activeCaption);
		verticalBox.setBounds(375, 82, 617, 237);
		contentPane.add(verticalBox);
		
		JLabel lblNewLabel_1 = new JLabel("Conexiones Verificadas");
		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(375, 57, 151, 27);
		contentPane.add(lblNewLabel_1);
		
		JButton btnPaint = new JButton("");
		btnPaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				
				if(fila != -1){
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					String ip = m.getValueAt(fila, 0).toString();
					server.paint(ip);
					
				}else{
					System.out.println("No ha seleccionado nada");
				}
			}
		});
		btnPaint.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/paint.png")));
		btnPaint.setForeground(Color.BLACK);
		btnPaint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPaint.setBackground(Color.WHITE);
		btnPaint.setBounds(868, 355, 45, 43);
		contentPane.add(btnPaint);
		
		JButton btnNotepad = new JButton("");
		btnNotepad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
              int fila = table.getSelectedRow();
				
				if(fila != -1){
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					String ip = m.getValueAt(fila, 0).toString();
					server.notepad(ip);
					
				}else{
					System.out.println("No ha seleccionado nada");
				}
			}
				
			
		});
		btnNotepad.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/notepad (2).png")));
		btnNotepad.setForeground(Color.BLACK);
		btnNotepad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNotepad.setBackground(Color.WHITE);
		btnNotepad.setBounds(813, 355, 45, 43);
		contentPane.add(btnNotepad);
		
		JLabel lblNewLabel_2 = new JLabel("Utilitarios");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(813, 330, 95, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblBloquearProgramas = new JLabel("Bloquear Programas");
		lblBloquearProgramas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBloquearProgramas.setBounds(501, 330, 151, 21);
		contentPane.add(lblBloquearProgramas);
		
		JButton btnChrome = new JButton("");
		btnChrome.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/chrome.png")));
		btnChrome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	              int fila = table.getSelectedRow();
					
					if(fila != -1){
						DefaultTableModel m = (DefaultTableModel) table.getModel();
						String ip = m.getValueAt(fila, 0).toString();
						server.chrome(ip);
						
					}else{
						System.out.println("No ha seleccionado nada");
					}
				}
					
		});
		btnChrome.setForeground(Color.BLACK);
		btnChrome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChrome.setBackground(Color.WHITE);
		btnChrome.setBounds(496, 355, 45, 43);
		contentPane.add(btnChrome);
		
		JButton btnWord = new JButton("");
		btnWord.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/Word.png")));
		btnWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	              int fila = table.getSelectedRow();
					
					if(fila != -1){
						DefaultTableModel m = (DefaultTableModel) table.getModel();
						String ip = m.getValueAt(fila, 0).toString();
						server.word(ip);
						
					}else{
						System.out.println("No ha seleccionado nada");
					}
				}
		});
		btnWord.setForeground(Color.BLACK);
		btnWord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnWord.setBackground(Color.WHITE);
		btnWord.setBounds(551, 355, 45, 43);
		contentPane.add(btnWord);
		
		JButton btnExcel = new JButton("");
		btnExcel.setIcon(new ImageIcon(Formulario.class.getResource("/utiles/excel.png")));
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	              int fila = table.getSelectedRow();
					
					if(fila != -1){
						DefaultTableModel m = (DefaultTableModel) table.getModel();
						String ip = m.getValueAt(fila, 0).toString();
						server.excel(ip);
						
					}else{
						System.out.println("No ha seleccionado nada");
					}
				}
		});
		btnExcel.setForeground(Color.BLACK);
		btnExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcel.setBackground(Color.WHITE);
		btnExcel.setBounds(607, 355, 45, 43);
		contentPane.add(btnExcel);
		
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
		if (arg0.getSource() == btnDetener2) {
			do_btnDetener2_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnEnviarMensaje) {
			do_btnEnviarMensaje_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnTraerArchivo) {
			do_btnTraerArchivo_actionPerformed(arg0);
		}
		
		if (arg0.getSource() == btnDetener) {
			do_btnDetener_actionPerformed(arg0);
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
	protected void do_btnDetener_actionPerformed(ActionEvent arg0) {
	       int fila = table.getSelectedRow();
			
			if(fila != -1){
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				String ip = m.getValueAt(fila, 0).toString();
				server.detener(ip);
			}else{
				JOptionPane.showMessageDialog(this, "Seleccione una fila");
			}
	}
	

	protected void do_btnTraerArchivo_actionPerformed(ActionEvent arg0) {
       int fila = table.getSelectedRow();
		
		if(fila != -1){
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			String ip = m.getValueAt(fila, 0).toString();

			ClaseFrame file = new ClaseFrame();	
			server.traer(ip);
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		
		
		}
		
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
	protected void do_btnDetener2_actionPerformed(ActionEvent arg0) {

		int fila = table.getSelectedRow();
		
		if(fila != -1){
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			String ip = m.getValueAt(fila, 0).toString();
			server.anular(ip);
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		}
		
	}
	protected void do_btnCapturarPantalla_actionPerformed(ActionEvent arg0) {
		
       int fila = table.getSelectedRow();
		
		if(fila != -1){
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			String ip = m.getValueAt(fila, 0).toString();

			ServidorCaptura file = new ServidorCaptura();	
			server.captura(ip);
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		}
		
        /*int fila = table.getSelectedRow();
		
		if(fila != -1){
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			String ip = m.getValueAt(fila, 0).toString();
			
			server.detener(ip);
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		}
		
		try
		{
		System.out.println("[ Captura iniciada ]");
		//sleep 5 sg
		Thread.currentThread().sleep(5*1000);
		String FILENAME="C:/server/captura01.png";
		Captura.captura(FILENAME);
		System.out.println("[ Captura finalizada ]");
		//
		catch(Exception e)
		{
		e.printStackTrace();
		}*/
		
		}

	protected void do_btnVerProcesosRemotos_actionPerformed(ActionEvent arg0) {

int fila = table.getSelectedRow();
		
		if(fila != -1){
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			String ip = m.getValueAt(fila, 0).toString();

			AdministradordeTareas file = new AdministradordeTareas();	
			server.traer(ip);
		}else{
			JOptionPane.showMessageDialog(this, "Seleccione una fila");
		
		
		}
	}
}
