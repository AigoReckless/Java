package plantasZombies.logica;

import plantasZombies.presentacion.Login;
import java.util.HashMap;

/**
* Declaración de la clase Main
* @author Marina Fernández
*/
public class Main {

    /**
    * Método main del juego
    * @param args
    */
    public static void main(String[] args) {
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            new Login().setVisible(true);
            }
        });
        Comandos comandos = new Comandos(); //Comandos del juego
        Tablero tablero = new Tablero(0, 0, 0, 0, new HashMap<>()); //Tablero y hashmap
        String comando = "";
        boolean terminado = true;
        Login login = new Login();
        leerFichero fichero = new leerFichero(); //Para leer el fichero "puntuación.txt"
        HashMap<String, Jugador> listJugadores = fichero.leerFicheroPuntuacion();
        
        comandos.menu(); //Comandos del menú principal
        do {
            comandos.setCommand(); //Comando modificado
            comando = comandos.getComandoIntroducido(); //Devuelve el comando introducido

            switch (comando.split(" ")[0].toUpperCase()) {
                case "N": //Nueva partida
                    int filas = Integer.parseInt(comando.split(" ")[1]); //Filas a introducir
                    int columnas = Integer.parseInt(comando.split(" ")[2]); //Columnas a introducir
                    String dificultad = comando.split(" ")[3]; //Dificultada a introducir
                    
                    switch(dificultad.toUpperCase()){
                        case "BAJA": //Dificultad baja = 5 zombies total que aparecen en el turno 10
                            tablero = new Tablero(filas, columnas, 5, 10, listJugadores);
                            terminado = false;
                            break;
                        case "MEDIA": //Dificultad media = 15 zombies taotal que aparecen en el turno 7
                            tablero = new Tablero(filas, columnas, 15, 7, listJugadores);
                            terminado = false;
                            break;
                        case "ALTA": //Dificultada alta = 25 zombies en total que aparecen en el turno 5
                            tablero = new Tablero(filas, columnas, 25, 5, listJugadores);
                            terminado = false;
                            break;
                        case "IMPOSIBLE": //Dificultad imposible = 50 zombies en total que aparecen en el turno 5
                            tablero = new Tablero(filas, columnas, 50, 5, listJugadores);
                            terminado = false;
                            break;
                    }
                    if(!terminado){
                        tablero.iniciarTablero(); //Crear tablero
                    }
                    break;
                case "G": //Crear girasol
                    if(!terminado){
                        int posicionColumna = Integer.parseInt(comando.split(" ")[1]); //Columna donde colocarlo
                        int posicionFila = Integer.parseInt(comando.split(" ")[2]); //Fila donde colocarlo
                        if (tablero.crearGirasol(posicionFila - 1, posicionColumna - 1)) {
                            tablero.pintarTablero(); //Pintar en el tablero
                        }
                    }
                    break;
                case "L": //Crear lanzaguisantes
                    if(!terminado){
                        int columnaPosicion = Integer.parseInt(comando.split(" ")[1]); //Columna donde colocarlo
                        int filaPosicion = Integer.parseInt(comando.split(" ")[2]); //Fila donde colocarlo
                        if (tablero.crearLanzaguisantes(filaPosicion - 1, columnaPosicion - 1)) {
                            tablero.pintarTablero(); //Pintar en el tablero
                        }
                    }
                    break;
                case "NU": //Crear nuez
                    if(!terminado){
                        int columnaPosicion = Integer.parseInt(comando.split(" ")[1]); //Columna donde colocarlo
                        int filaPosicion = Integer.parseInt(comando.split(" ")[2]); //Fila donde colocarlo
                        if (tablero.crearNuez(filaPosicion - 1, columnaPosicion - 1)) {
                            tablero.pintarTablero(); //Pintar en el tablero
                        }
                    }
                    break;
                case "": //Para pasar de turno no introducir nada
                    if(!terminado){
                        terminado = tablero.siguienteTurno(); //Siguiente turno
                    }
                    break;
                case "S": //Terminar juego
                    System.out.println("Fin del juego");
                    System.exit(0); //Stop run
                    break;
                case "AYUDA": //Comando de ayuda
                    comandos.menu();
                    break;
                default: //Si el comando no es de los mencionados
                    System.out.println("Comando incorrecto");
            }
        } while (!comandos.getComandoIntroducido().equals("S"));
    }
}