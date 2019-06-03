package plantasZombies.logica;

import java.util.Scanner;

/**
* Declaración de la clase Comandos
* @author Marina Fernández
*/
public class Comandos {

    private String comandoIntroducido;
    private String comandoCompleto;
    protected Scanner Teclado = new Scanner(System.in);
    String select = null;

    /**
    * Muestra por pantalla el menú de los comandos
    */
    public void menu() {
        System.out.println("N - <filas> <columnas> <Dificultad>: Nueva partida (Dificultad: BAJA, MEDIA, ALTA, IMPOSIBLE).\n"
                + "G - <fila> <columna>: colocar girasol.\n"
                + "L - <fila> <columna>: colocar LanzaGuisantes.\n"
                + "NU - <fila> <columna>: colocar Nuez.\n"
                + "S - Salir de la aplicación.\n"
                + "<Enter>: Pasar Turno\n");
    }

    /**
    * Devuelve el comando que se ha introducido por teclado
    * @return comando introducido
    */
    public String getComandoIntroducido() {
        return this.comandoIntroducido;
    }

    /**
    * Devuelve el comando correto que se ha introducido por teclado
    * @return comando completo
    */
    public String getComandoCompleto() {
        return this.comandoCompleto;
    }

    /**
    * Modifica el comando que se ha introducido
    */    
    public void setCommand(){
        this.comandoIntroducido = Teclado.nextLine();
    }
}