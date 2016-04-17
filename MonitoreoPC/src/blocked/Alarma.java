
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocked;

/**
 *
 * @author Administrador
 */
//import java.io.File;
//import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Alarma {
        public static Clip sonido;
        
        public void Reproducir(String archivo,int bd,int duracion){
             URL path = Alarma.class.getResource("/sonido/"+archivo);
            try {
                sonido = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path);
                //File a = new File(Alarma.class.getClass().getResource("/sonido/alarma.wav").toURI());
                //sonido.open(AudioSystem.getAudioInputStream(path));
                sonido.open(audioInputStream);
                sonido.start();
               // System.out.println("Reproduciendo 10s. de sonido...");
                if(bd == 0){
                    sonido.loop(Clip.LOOP_CONTINUOUSLY);// reproduce el fichero de sonido una y otra vez sin parar. 
                    Tiempo tiempo = new Tiempo();
                    tiempo.Contar();
                }else{
                    while (sonido.isRunning()){
                      
                        // Se cierra el clip. Significa que acabo el archivo y tendremos que cerrarlo
                        sonido.close(); 
                   
                    }
                
                }
                //Thread.sleep(60000); // 1000 milisegundos (10 segundos)
                //sonido.close();
            }
                catch (Exception tipoerror) {
                System.out.println("" + tipoerror);
        }

    }
        /*public void Detener(){
         * sonido.close();
         * }*/
     
 }
