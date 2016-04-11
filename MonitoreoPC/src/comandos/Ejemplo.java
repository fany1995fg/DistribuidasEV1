package comandos;

import java.io.IOException;

public class Ejemplo {

	public static void main(String[] args) {
		try {
			//permite ejecutar comandos
			
			//-->aplicaciones
			//Runtime.getRuntime().exec("calc");
			//Runtime.getRuntime().exec("regedit");
			//Runtime.getRuntime().exec("calc");
			//Runtime.getRuntime().exec("cmd");
			//Runtime.getRuntime().exec("notepad");
			
			
			//--->sirve para abrir el explorer por defauld
			// Runtime.getRuntime().exec("explorer http://www.google.com");
			
			//--->Apagar
			//Runtime.getRuntime().exec("shutdown -s");
			
			//--->Reinicio
			//Runtime.getRuntime().exec("shutdown -r");
			
			//--->Sirve para apagar la PC en un lapso de 1 hora
			Runtime.getRuntime().exec("shutdown -s -t 3600");
			
			//--> Ejecutar programas
			//Runtime.getRuntime().exec("C:\\Archivos de programa\\Winamp\\winamp.exe");
			//Runtime.getRuntime().exec("C:/Archivos de programa/Windows Live/Messenger/msnmsgr.exe")
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
