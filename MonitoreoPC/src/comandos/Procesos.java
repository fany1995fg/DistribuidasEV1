package comandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 4ng3r C0d!|\|G
 * 
 * VER PROCESOS CON JAVA
 * */
public class Procesos {
 
 public static void main(String[] args) throws IOException {
  verProcesos();
 }

 private static void verProcesos() throws IOException {
  // LLAMAMOS LA VARIABLE DE ENTORNO WINDOWS Y EL PROGRAMA Q GESTIONA
  // LOS PROCESOS
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
