package plantasZombies.logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
* Declaración de la clase leerFichero
* @author Marina Fernández
*/
public class leerFichero {
    
    /**
    * Método para leer fichero donde guardar datos del jugador
    * @return leer fichero
    */
    public HashMap<String, Jugador> leerFicheroPuntuacion() {
        HashMap<String, Jugador> puntuacion = new HashMap<String, Jugador>();

        try{
            File file = new File("src/plantasZombies/puntuacion.txt");
            BufferedReader br = new BufferedReader(new FileReader(file)); //Leer fichero
            String st, dni;
            String[] fila;
            while ((st = br.readLine()) != null){
                fila = st.split(" ");
                dni = fila[0];
                puntuacion.put(dni, new Jugador(dni, fila[1], Integer.parseInt(fila[2])));
            }
        }catch(Exception e){
            System.err.println(e);
        }
        return puntuacion;
    }
}