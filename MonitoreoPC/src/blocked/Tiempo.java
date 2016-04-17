/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocked;

/**
 *
 * @author Administrador
 */




//import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class Tiempo  {

    private Timer timer = new Timer(); 
    private int segundos=0;
    //jFrameBlocked frame;
    //Clase interna que funciona como contador
    class Contador extends TimerTask {
        public void run() {
            segundos++;
            // System.out.println("segundo: " + segundos);
            //if(segundos % 2 == 0){
              //  System.out.println("hola");
                 /* System.out.println(jFrameBlocked.alto);*/
                /* mipanel p2 = new mipanel(jFrameBlocked.ancho,jFrameBlocked.alto,"foto-rojo.jpg");
                 * frame.add( p2 , BorderLayout.CENTER);
                 * p2.repaint();*/
                
            //}
            if(segundos == 60){
                Detener();
                Alarma.sonido.close();
            }
        }
    }
    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void Contar()
    {
        this.segundos=0;
        timer = new Timer();
        timer.schedule(new Contador(), 0, 1000);
    }
    //Detiene el contador
    public void Detener() {
        timer.cancel();
    }
   
}


