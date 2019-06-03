package plantasZombies.logica;

/**
* Declaración de la clase de planta Nuez
* @author Marina Fernández
*/
public class Nuez {
    public int resistencia;
    public int daño;
    public int x; //Filas
    public int y; //Columnas
    public int coste;
    public String imageURL;
    
    /**
    * Creación del constructor
    * @param fila Fila en la que colocar la nuez
    * @param columna Columna en la que colocar la nuez
    */
    public Nuez(int fila, int columna){
        this.x = fila;
        this.y = columna;
        this.resistencia = 5; //Dura 10 turnos
        this.daño = 0; //No hace daño
        this.coste = 50; //Cuesta 50 soles
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getDaño() {
        return daño;
    }

    /**
    * Devuelve el eje x donde colocar la nuez (fila)
    * @return x Eje x
    */     
    public int getX() {
        return x;
    }

    /**
    * Devuelve el eje y donde colocar la nuez (columna)
    * @return y Eje y
    */
    public int getY() {
        return y;
    }

    /**
    * Devuelve los soles que cuesta generar una nuez
    * @return coste Coste de soles
    */      
    public int getCoste() {
        return coste;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }

    /**
    * Modifica el eje x donde colocar el girasol (fila)
    * @param x Eje x
    */    
    public void setX(int x) {
        this.x = x;
    }

    /**
    * Modifica el eje y donde colocar el girasol (columna)
    * @param y Eje y
    */    
    public void setY(int y) {
        this.y = y;
    }

    /**
    * Modifica cuantos soles cuesta generar un girasol
    * @param coste Coste de soles
    */    
    public void setCoste(int coste) {
        this.coste = coste;
    } 
}