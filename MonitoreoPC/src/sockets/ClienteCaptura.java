package sockets;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ClienteCaptura extends JFrame implements ActionListener, Runnable {
    Socket c;
    Boolean est = false;
    ObjectOutputStream salida;
    ByteArrayOutputStream imgSalida;
    JButton btnIni;
    JTextField txtIp, txtPuerto;
    JLabel lblEst;
    Thread t;
    private JLabel lblImg;
    
    public ClienteCaptura()
    {    
        this.ini();
    }
    
    final void ini(){
        btnIni = new JButton("Iniciar");
        btnIni.setBounds(263, 13, 90, 23);
        btnIni.addActionListener(this);
        txtIp = new JTextField(10);
        txtIp.setBounds(30, 14, 114, 20);
        txtIp.setText("Ingresa IP Servidor");
        txtPuerto = new JTextField(5);
        txtPuerto.setBounds(207, 14, 46, 20);
        txtPuerto.setText("5000");
        lblEst = new JLabel("Esperando Iniciar");
        lblEst.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEst.setForeground(new Color(255, 255, 255));
        lblEst.setBounds(362, 16, 119, 14);
        getContentPane().setLayout(null);
        
        JLabel label = new JLabel("Ip:");
        label.setFont(new Font("Tahoma", Font.BOLD, 13));
        label.setForeground(new Color(255, 255, 255));
        label.setBounds(10, 9, 20, 29);
        getContentPane().add(label);
        getContentPane().add(txtIp);
        JLabel label_1 = new JLabel("Puerto:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        label_1.setForeground(new Color(255, 255, 255));
        label_1.setBounds(154, 16, 61, 14);
        getContentPane().add(label_1);
        getContentPane().add(txtPuerto);
        getContentPane().add(btnIni);
        getContentPane().add(lblEst);
        
        lblImg = new JLabel();
        lblImg.setBounds(0, 0, 544, 62);
        lblImg.setIcon(new ImageIcon(ClienteCaptura.class.getResource("/utiles/fondo3.png")));
        lblImg.setVerticalAlignment(SwingConstants.TOP);
        lblImg.setForeground(new Color(0, 250, 154));
        lblImg.setBackground(new Color(255, 255, 240));
        getContentPane().add(lblImg);
        
        this.setTitle("Cliente");
        this.setBounds(0, 0, 527, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    void IniCon(){
         try {
            //Iniciar Socket para la conexion con el Servidor
                c = new Socket(txtIp.getText(), Integer.parseInt(txtPuerto.getText()));
                lblEst.setText("Estableciendo Conexion con el Servidor");
                if (c.isConnected()){
                    est = true;
                    btnIni.setText("Salir");
                    lblEst.setText("Conectado con el servidor");
                    txtPuerto.setEnabled(false);
                    txtIp.setEnabled(false);
                }
        } catch (NumberFormatException | IOException e){
            System.out.println(e.toString());
            lblEst.setText("No se puede establecer la conexion");
            est = false;
        }
    }
    
    void enviarImagenes(){
            while(est){
                try{
                    if (c != null)
                    {
                        //Iniciar el Objeto Stream con el cual se enviaran los datos
                        salida = new ObjectOutputStream(c.getOutputStream());
                        //crear el Array de Bytes del para guardar la info anterior
                        imgSalida = new ByteArrayOutputStream();
                        //Crear la Imagen y guardarlo en el objeto anterior
                        ImageIO.write(pantallazo(), "jpeg", imgSalida);
                        //Crear un Array de Bytes de la imagen capturada
                        byte[] bytesImg = imgSalida.toByteArray();
                        //Enviar el array de Bytes
                        salida.writeObject(bytesImg);
                        //vaciar Buffer
                        imgSalida.flush();
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    lblEst.setText("Desconectado");
                    est = false;
                }
            }
    }
    
    void finCon()
    {
        est = false;
        try {
            c.close();
            this.dispose();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        lblEst.setText("Esperando Iniciar");
        txtPuerto.setEnabled(true);
    }
    
    BufferedImage pantallazo() throws Exception {
        //obtener el tamaño de la pantalla
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        //crear un objeto del tipo Rectangle con el tamaño obtenido
        Rectangle screenRectangle = new Rectangle(screenSize);
        //inicializar un objeto robot
        Robot r = new Robot();
        //obtener una captura de pantalla del tamaño de la pantalla
        BufferedImage image = r.createScreenCapture(screenRectangle);
        
        return image;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIni)
        {            
            if ("Iniciar".equals(btnIni.getText()))
            {
                if (!txtPuerto.getText().isEmpty() || !txtIp.getText().isEmpty()){
                    this.IniCon();
                    
                    if (est && t == null){
                        t = new Thread(this, "ClienteImagens");
                        t.start();
                    }
                }
                else
                {
                    lblEst.setText("Direccion IP o Puerto Incorrecto");
                }
            }
            else
            {
                this.finCon();
            }
        }
    }

    @Override
    public void run() {
        this.enviarImagenes();
    }
}
