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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServidorCaptura extends JFrame implements ActionListener, Runnable {
    ServerSocket s;
    static Socket con;
    static ImageIcon img;
    static JLabel lblImg;
	static JLabel lblEst;
	static Boolean est = false;
    static ObjectInputStream entrada;
    static ByteArrayInputStream bufferImg;
    static BufferedImage imagen;
    static JButton btnIni;
    static JTextField txtPuerto;
    Thread t;
    
    public ServidorCaptura(){
        ini();
    }
    
    final void ini(){
        txtPuerto = new JTextField(5);
        txtPuerto.setText("5000");
        btnIni = new JButton("Iniciar");
        btnIni.addActionListener(this);
        lblEst = new JLabel("Esperando Iniciar");
        JPanel p = new JPanel();
        
        this.getContentPane().add(p, BorderLayout.NORTH);
        
        p.setLayout(new FlowLayout());
        p.add(new JLabel("Puerto"));
        p.add(txtPuerto);
        p.add(btnIni);
        p.add(lblEst);
        
        lblImg = new JLabel();
        this.getContentPane().add(lblImg, BorderLayout.CENTER);
        
        this.setTitle("Servidor");
        this.setSize(400, 100);
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