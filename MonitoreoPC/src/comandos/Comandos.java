package comandos;


public class Comandos {
	

	public static void main(String[] str) {
		try {
			//Apagar
			//Runtime.getRuntime().exec("shutdown -s");
			
			//Reinicio
			//Runtime.getRuntime().exec("shutdown -r");
			
			//sirve para apagar la PC en un lapso de 1 hora hay que tener en cuenta que el tiempo que se asigna es en segundos
			//Runtime.getRuntime().exec("shutdown -s -t 3600");
			
			//sirve para reiniciar la PC
			//Runtime.getRuntime().exec("shutdown -r -t 3600");
			
			////sirve para anular las instrucciones de -s y -r en el caso que queramos canselar el apagado de la PC o el receteo.
			//Runtime.getRuntime().exec("shutdown -a");
			
			//sirve para abrir programas del sistema tambien podemos poner estas otras para que vean que funciona con cualquier *.exe:
			//Runtime.getRuntime().exec("regedit");
			
			//Runtime.getRuntime().exec("calc");
			//Runtime.getRuntime().exec("cmd");
			//Runtime.getRuntime().exec("notepad");
			//Runtime.getRuntime().exec("C:\\Archivos de programa\\Winamp\\winamp.exe");
			//Runtime.getRuntime().exec("C:/Archivos de programa/Windows Live/Messenger/msnmsgr.exe");
			
			//sirve para abrir el explorer por defauld te lo pone en mis documentos pero tambien puede asignarle una direccion espesifica o bien una pagina de internet como esta en mi ejemplo
			//Runtime.getRuntime().exec("explorer http://www.google.com");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}