import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Alejandro Bajo P�rez
 * @author Ismael Mart�n Ram�rez
 */

public class ManejadorDiccionario {

	private ArrayList<String> palabras;
	private File rutaDiccionario;

	/**
	 * Constructor
	 */
	public ManejadorDiccionario() {
		this.palabras = new ArrayList<>();
		this.rutaDiccionario = new File("diccionario" + File.separator + "spanish.dic");
	}

	/**
	 * M�todo que sirve para leer el fichero y a�adir las palabras que contiene a un
	 * ArrayList
	 * 
	 * @return
	 */
	public ArrayList<String> cargarDiccionario() {
		try {
			// Abro flujos
			BufferedReader br = new BufferedReader(new FileReader(rutaDiccionario));
			// Leo el fichero
			String linea;
			while ((linea = br.readLine()) != null) {
				this.palabras.add(linea);
			}
			// Cierro flujos
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error de lectura");
		}
		return palabras;
	}

	public File getRutaDiccionario() {
		return rutaDiccionario;
	}

	public void setRutaDiccionario(File rutaDiccionario) {
		this.rutaDiccionario = rutaDiccionario;
	}
}