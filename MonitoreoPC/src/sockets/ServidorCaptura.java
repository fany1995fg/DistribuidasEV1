package sockets;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;

public class ServidorCaptura extends JFrame implements ActionListener, Runnable {
    ServerSocket s;
    static Socket con;
    static ImageIcon img;
    static JLabel lblEst;
    static JLabel lblImg;
	static Boolean est = false;
    static ObjectInputStream entrada;
    static ByteArrayInputStream bufferImg;
    static BufferedImage imagen;
    static JButton btnIni;
    static JTextField txtPuerto;
    Thread t;
	
    
    public ServidorCaptura(){
    	setBackground(new Color(0, 255, 127));
        ini();
    }
    
    final void ini(){
        getContentPane().setLayout(null);
        JLabel label = new JLabel("Puerto");
        label.setForeground(new Color(255, 255, 255));
        label.setBounds(10, 24, 67, 20);
        getContentPane().add(label);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtPuerto = new JTextField(5);
        txtPuerto.setBounds(73, 26, 52, 20);
        getContentPane().add(txtPuerto);
        txtPuerto.setText("5000");
        btnIni = new JButton("Iniciar");
        btnIni.setBounds(135, 23, 82, 24);
        getContentPane().add(btnIni);
        btnIni.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        JLabel lblEst = new JLabel("Esperando Iniciar");
        lblEst.setForeground(new Color(255, 255, 255));
        lblEst.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEst.setBounds(222, 21, 136, 29);
        getContentPane().add(lblEst);
        
        lblImg = new JLabel();
        lblImg.setBounds(0, 0, 384, 72);
        lblImg.setVerticalAlignment(SwingConstants.TOP);
        lblImg.setIcon(new ImageIcon(ServidorCaptura.class.getResource("/utiles/fondo3.png")));
        lblImg.setForeground(new Color(0, 250, 154));
        lblImg.setBackground(new Color(255, 255, 240));
        this.getContentPane().add(lblImg);
        btnIni.addActionListener(this);
        
        this.setTitle("Servidor");
        this.setSize(400, 110);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }    
    
    void iniServ(){
        try {
        	lblEst.setText("Esperando al Cliente por el puerto: " + txtPuerto.getText());
            s = new ServerSocket(Integer.parseInt(txtPuerto.getText()));
            con = s.accept();
            if (con.isConnected()){
                btnIni.setText("Salir");
                lblEst.setText("Cliente Conectado: " + con.getRemoteSocketAddress());
                this.setBounds(0, 200, 800, 600);
                this.setLocationRelativeTo(null);
                est = true;
            }
        } catch (NumberFormatException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    void finServ()
    {
        est = false;
        try {
            con.close();
            s.close();
            this.dispose();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static  void procImagenes()
    {
        while(est){
            try{
                lblEst.setText("Conectado");
                entrada = new ObjectInputStream(con.getInputStream());
                byte[] bytesImg = (byte[]) entrada.readObject();
                bufferImg = new ByteArrayInputStream(bytesImg);
                imagen = ImageIO.read(bufferImg);
                img = new ImageIcon(imagen);
                lblImg.setIcon(img);
            }
            catch (IOException | ClassNotFoundException e){
                System.err.println(e.getMessage());
                est = false;
                lblEst.setText("Desconectado");
                //this.setSize(400, 100);
                //this.setLocationRelativeTo(null);
                //this.remove(lblImg);
            }   
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIni)
        {            
            if ("Iniciar".equals(btnIni.getText()))
            {
                if (!txtPuerto.getText().isEmpty()){
                    this.iniServ();
                    if (t == null){
                        t = new Thread(this, "ServidorImagenes");
                        t.start();
                    }
                }
                else
                {
                    lblEst.setText("Eso no es un puerto");
                }
            }
            else
            {
                this.finServ();
            }
        }
    }

    @Override
    public void run() {
        this.procImagenes();
    }
}