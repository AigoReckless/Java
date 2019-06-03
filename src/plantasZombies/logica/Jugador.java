package plantasZombies.logica;

/**
* Declaración de la clase Jugador
* @author Marina Fernández
*/
public class Jugador {
    private String nombre;
    private String dni;    
    private Integer puntuacion;


    /**
    * Creación del constructor
     * @param dni DNI del jugador
     * @param nombre Nombre del jugador
     * @param puntuacion Puntuación final que ha conseguido el jugador
    */
    public Jugador(String dni, String nombre, Integer puntuacion) {
        this.nombre = nombre;
        this.dni = dni;
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    /**
    * Modifica la puntuacion que ha conseguido el jugador en la partida
    * @param puntuacion
    */
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
    * Devuelve la puntuación final que ha conseguido el jugador en la partida
    * @return puntuacion Puntuación = suma valor plantas que haya en el tablero + soles que no se han gastado
    */
    public Integer getPuntuacion() {
        return puntuacion;
    }
}