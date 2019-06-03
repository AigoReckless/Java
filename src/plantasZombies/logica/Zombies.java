package plantasZombies.logica;

/**
* Declaración de la clase Zombies
* @author Marina Fernández
*/
public class Zombies {
    
    public int resistencia;
    public int daño;
    public int velocidad;
    public int x; //Filas
    public int y; //Columnas
    public String imageURL;

    /**
    * Creación del constructor
    * @param fila Fila en la que aparecerá el zombie normal
    * @param columna Columna en la que aparecerá el zombie normal
    */
    public Zombies(int fila, int columna){
        this.x = fila;
        this.y = columna;
        this.resistencia = 6; //Aguanta 5 golpes
        this.daño = 1; //Hace 1 de daño a las plantas
        this.velocidad = 2; //Se mueve 1 casilla cada 2 turnos
    }
        
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Modifica el eje x donde se genera un zombie (fila)
     * @param x Eje x
     */  
    public void setX(int x) {
        this.x = x;
    }

    /**
    * Modifica el eje y donde se genera un zombie (columna)
    * @param y Eje y
    */    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getResistencia() {
        return resistencia;
    }

    public int getDaño() {
        return daño;
    }

    public int getVelocidad() {
        return velocidad;
    }

    /**
    * Devuelve el eje x donde aparece el zombie (fila)
    * @return x Eje x
    */    
    public int getX() {
        return x;
    }

    /**
    * Devuelve el eje y donde aparece el zombie (columna)
    * @return y Eje y
    */    
    public int getY() {
        return y;
    }     
}