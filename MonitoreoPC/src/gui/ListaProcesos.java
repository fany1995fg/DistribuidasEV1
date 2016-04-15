package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JSpinner;

public class ListaProcesos extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaProcesos frame = new ListaProcesos();
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
	public ListaProcesos() {
		setTitle("Procesos");
		setBackground(SystemColor.textHighlight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 422);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDeProcesos = new JLabel("LISTA DE PROCESOS DEL CLIENTE");
		lblListaDeProcesos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListaDeProcesos.setBackground(SystemColor.desktop);
		lblListaDeProcesos.setBounds(88, 30, 289, 22);
		contentPane.add(lblListaDeProcesos);
		
		JList list = new JList();
		list.setBackground(SystemColor.info);
		list.setBounds(29, 63, 396, 294);
		contentPane.add(list);
	}
}
