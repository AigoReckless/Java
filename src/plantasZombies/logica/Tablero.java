package plantasZombies.logica;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import java.util.HashMap;

/**
* Declaración de la clase Tablero
* @author Marina Fernández
*/
public class Tablero {

    protected Scanner Teclado = new Scanner(System.in);
    private int filas;
    private int contadorTurno;
    private int soles;
    private int numZombiesRestantes;
    private int numZombiesCreados;
    private int numZombiesMax;
    private int turnosSinZombies;
    private int columnas;
    private String dificultad;
    private String nombre;
    private final String[][] tablero;
    private List<Zombies> listZombies = new ArrayList<Zombies>(); //Array de zombies normales
    private List<Girasol> listGirasoles = new ArrayList<Girasol>(); //Array de girasol
    private List<LanzaGuisantes> listLanzaGuisantes = new ArrayList<LanzaGuisantes>(); //Array de lanzaguisantes
    private List<Nuez> listNueces = new ArrayList<Nuez>(); //Array de nuez
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel(); 
    private List<Caracubo> listCaracubo = new ArrayList<Caracubo>(); //Array de zombies caracubo
    private List<Deportista> listDeportista = new ArrayList<Deportista>(); //Array de zombies deportistas
    private HashMap<String, Jugador> listJugadores = new HashMap<String, Jugador>(); //Hashmap para guardar los datos del jugador

    /**
    * Creación del constructor
    * @param filas Filas del tablero
    * @param columnas Columnas del tablero
    * @param zombiesPartida Zombies que deben salir dependiendo de la dificultad
    * @param sinZombies Finalización de la partida porque mueren los zombies
    * @param listJugadores Jugadores que juegan
    */
    public Tablero(int filas, int columnas, int zombiesPartida, int sinZombies, HashMap<String, Jugador> listJugadores) {
        this.filas = filas;
        this.columnas = columnas;
        this.numZombiesRestantes = zombiesPartida; //Zombies que quedan por salir en partida
        this.numZombiesMax = zombiesPartida; //Zombies totales dependiendo de la dificultad
        this.numZombiesCreados = 0; //Contador de los zombies que van apareciendo
        this.turnosSinZombies = sinZombies; //Final partida -> zombies pierden
        this.tablero = new String[filas][columnas]; //Nuevo tablero
        this.soles = 50; //Soles de inicio
        this.listJugadores = listJugadores; //Lista de jugadores
    }
    
    /**
    * Método para crear el tablero en el panel
    */
    public void iniciarTablero() {     
        frame.setSize(600,400);
        frame.setVisible(true);
        panel.setLayout(null);
        panel.setBounds(400,80,200,200); 
        frame.add(panel);
        pintarTablero();
    }

    /**
    * Método para pintar y que se muestre el tablero
    */
    public void pintarTablero() {            
        Component[] componentList = panel.getComponents();
        for(Component c : componentList){
            if(c instanceof JLabel){
                panel.remove(c); //Borrar jlabel
            }
        }
        panel.revalidate();
        panel.repaint(); //Renderizado
        
        for (int x = 0; x < this.filas; x++) { //Crear las jlabels
            for (int y = 0; y < this.columnas; y++) {
                JLabel label = new JLabel();
                if (tablero[x][y] == null) {
                    label.setText("----");
                } else {
                    label.setIcon(new ImageIcon("src/plantasZombies/images/" + tablero[x][y] + ".png"));
                    //Imagen en label -> ImageIcon imgThisImg = new ImageIcon("src/plantasZombies/images/" + tablero[x][y] + ".png");
                }
                panel.add(label); //Colocar las jlabels
                label.setSize(60, 100); //Tamaño del tablero
                label.setLocation(40 + y * 40 , 20 + x * 40); //Ejes del tablero
            }
        }
        System.out.println("Tienes " + this.soles + " soles, es el turno " + this.contadorTurno + " y quedan " + this.numZombiesRestantes + " zombies.");
        System.out.println("(Teclear ayuda para lista de comandos. <Enter> para terminar el turno.)");
    }

    /**
    * Método para generar los girasoles
    * @param fila Fila donde colocar el girasol
    * @param columna Columna donde generar el girasol
    * @return girasol generado
    */
    public boolean crearGirasol(int fila, int columna) {
        //Solo se puede crear un girasol si hay al menos 20 soles disponibles
        Girasol girasol = new Girasol(fila, columna);
        if (this.soles >= girasol.getCoste() && this.tablero[columna][fila] == null) {
            this.soles = this.soles - girasol.getCoste();
            this.tablero[columna][fila] = "girasol";
            this.listGirasoles.add(girasol);
            return true;
        } else {
            return false;
        }
    }

    /**
    * Método para generar los lanzaguisantes
    * @param fila Fila donde colocar el lanzaguisantes
    * @param columna Columna donde generar el lanzaguisantes
    * @return lanzaguisantes generado
    */
    public boolean crearLanzaguisantes(int fila, int columna) {
        //Solo se puede crear un lanzaguisantes si hay al menos 50 soles disponibles
        LanzaGuisantes lanzaGuisantes = new LanzaGuisantes(fila, columna);
        if (this.soles >= lanzaGuisantes.getCoste() && this.tablero[columna][fila] == null) {
            this.soles = this.soles - lanzaGuisantes.getCoste();
            this.tablero[columna][fila] = "lanzaGuisante";
            this.listLanzaGuisantes.add(lanzaGuisantes);
            return true;
        } else {
            return false;
        }
    }
    
    /**
    * Método para generar las nueces
    * @param fila Fila donde colocar la nuez
    * @param columna Columna donde generar la nuez
    * @return nuez generada
    */
    public boolean crearNuez(int fila, int columna) {
        //Solo se puede crear una nuez si hay al menos 50 soles disponibles
        Nuez nuez = new Nuez(fila, columna);
        if (this.soles >= nuez.getCoste() && this.tablero[columna][fila] == null) {
            this.soles = this.soles - nuez.getCoste();
            this.tablero[columna][fila] = "nuez";
            this.listNueces.add(nuez);
            return true;
        } else {
            return false;
        }
    }
    
    /**
    * Método para pasar al siguiente turno
    * @return turno siguiente
    */
    public boolean siguienteTurno() {
        this.contadorTurno++;
        this.soles += 10 * listGirasoles.size() * ((this.contadorTurno + 1) % 2);
        boolean terminado = false;
        if(dispararLanzaGuisantes()) { //Si la partida ha terminado y ganan las plantas
            System.out.println("Plantas ganan");
            calcularPuntos(); //Calcular los puntos del jugador
            terminado = true;
            System.exit(0); //Stop run
        }else{
            if(moverZombies() || moverCaracubo() || moverDeportista()) { //Si la partida ha terminado y ganan los zombies
                System.out.println("Zombies ganan");
                terminado = true;
                System.exit(0); //Stop run
            }else{
                crearZombie();
                crearCaracubo();
                crearDeportista();
                pintarTablero();
            }
        }
        return terminado;
    }
      
    /**
    * Método para crear los zombies caracubo
    */
    public void crearCaracubo(){ //Salen aleatoriamente todos los zombies
        if(this.turnosSinZombies <= this.contadorTurno && Math.random() >= 0.5 && this.numZombiesCreados < this.numZombiesMax){            
            Caracubo caracubo = new Caracubo ((int) Math.floor(Math.random() * this.filas), this.columnas - 1);
            this.listCaracubo.add(caracubo);
            this.numZombiesCreados++;
            this.tablero[caracubo.getX()][caracubo.getY()] = "caracubo";
        }
    }
       
    /**
    * Método para crear los zombies deportistas
    */
    public void crearDeportista(){
        if(this.turnosSinZombies <= this.contadorTurno && Math.random() >= 0.5 && this.numZombiesCreados < this.numZombiesMax){          
            Deportista deportista = new Deportista ((int) Math.floor(Math.random() * this.filas), this.columnas - 1);
            this.listDeportista.add(deportista);
            this.numZombiesCreados++;
            this.tablero[deportista.getX()][deportista.getY()] = "deportista";
        }
    }
        
    /**
    * Método para crear los zombies normales
    */
    public void crearZombie(){
        if(this.turnosSinZombies <= this.contadorTurno && Math.random() >= 0.6 && this.numZombiesCreados < this.numZombiesMax){           
            Zombies zombie = new Zombies((int) Math.floor(Math.random() * this.filas), this.columnas - 1);
            this.listZombies.add(zombie);
            this.numZombiesCreados++;
            this.tablero[zombie.getX()][zombie.getY()] = "zombie";
        }
    }
    
    /**
    * Método para que los zombies deportistas se muevan por el tablero
    * @return zombie deportista se desplaza por el array
    */
    public boolean moverDeportista(){
        boolean terminado = false;     
        for(int i = 0; i < this.listDeportista.size(); i++){
            Deportista deportista = this.listDeportista.get(i);
            deportista.setVelocidad((deportista.getVelocidad()+ 2) % 1); //Velocidad a la que se mueve (1 casilla cada turno)
            if(deportista.getVelocidad() % 1 == 0){
                if(this.tablero[deportista.getX()][deportista.getY() - 1] == null){ //Se desplaza de izquierda a derecha
                    this.tablero[deportista.getX()][deportista.getY()] = null;
                    deportista.setY(deportista.getY() - 1);
                    this.tablero[deportista.getX()][deportista.getY()] = "deportista";
                        if(deportista.getY() == 0){
                            terminado = true; //Un zombie deportista gana y termina la partida
                        }
                }else{
                    String zombies = this.tablero[deportista.getX()][deportista.getY() - 1].split("\\(")[0];
                    if(zombies.equals("zombie") || zombies.equals("caracubo")){ //También salen los demás zombies
                        this.tablero[deportista.getX() + 1][deportista.getY()] = "deportista";
                    }
                    String planta = this.tablero[deportista.getX()][deportista.getY() - 1].split("\\(")[0];
                    if(planta.equals("girasol") || planta.equals("lanzaGuisante") || planta.equals("nuez")){
                        ataqueZombie(deportista.getX(), deportista.getY() - 1, planta); //Daño que hace el zombie a las plantas
                    }
                }
            }
        }
        return terminado;
    }

    /**
    * Método para que los zombies caracubos se muevan por el tablero
    * @return zombie caracubo se desplaza por el array
    */
    public boolean moverCaracubo() {
        boolean terminado = false;
        for(int i = 0; i < this.listCaracubo.size(); i++) {
            Caracubo caracubo = this.listCaracubo.get(i);
            caracubo.setVelocidad((caracubo.getVelocidad()+ 1) % 4); //Velocidad a la que se mueve (1 casilla cada 4 turnos)
            if(caracubo.getVelocidad() % 4 == 0){ 
                if(this.tablero[caracubo.getX()][caracubo.getY() - 1] == null){ //Se desplaza de izquierda a derecha
                    this.tablero[caracubo.getX()][caracubo.getY()] = null;
                    caracubo.setY(caracubo.getY() - 1);
                    this.tablero[caracubo.getX()][caracubo.getY()] = "caracubo";
                    if(caracubo.getY() == 0){
                        terminado = true; //Un zombie caracubo gana y termina la partida
                    }
                }else{
                    String zombies = this.tablero[caracubo.getX()][caracubo.getY() - 1].split("\\(")[0];
                    if(zombies.equals("zombie") || zombies.equals("deportista")){ //También salen los demás zombies
                        this.tablero[caracubo.getX()][caracubo.getY()] = "caracubo";
                    }
                    String planta = this.tablero[caracubo.getX()][caracubo.getY() - 1].split("\\(")[0];
                    if(planta.equals("girasol") || planta.equals("lanzaGuisante")|| planta.equals("nuez")){
                        ataqueZombie(caracubo.getX(), caracubo.getY() - 1, planta); //Daño que hace el zombie a las plantas
                    }
                }
            }
        }
        return terminado;
    }

    /**
    * Método para que los zombies normales se muevan por el tablero
    * @return zombie normal se desplaza por el array
    */    
    public boolean moverZombies(){
        boolean terminado = false;        
        for(int i = 0; i < this.listZombies.size(); i++){
            Zombies zombie = this.listZombies.get(i);
            zombie.setVelocidad((zombie.getVelocidad()+ 1) % 2); //Velocidad a la que se mueve (1 casilla cada 2 turnos)
            if(zombie.getVelocidad() % 2 == 0){
                if(this.tablero[zombie.getX()][zombie.getY() - 1] == null){ //Se desplaza de izquierda a derecha
                    this.tablero[zombie.getX()][zombie.getY()] = null;
                    zombie.setY(zombie.getY() - 1);
                    this.tablero[zombie.getX()][zombie.getY()] = "zombie";
                    if(zombie.getY() == 0){
                        terminado = true; //Un zombie normal gana y termina la partida
                    }
                }else{
                    String zombies = this.tablero[zombie.getX()][zombie.getY() - 1].split("\\(")[0];
                    if(zombies.equals("deportista") || zombies.equals("caracubo")){  //También salen los demás zombies
                        this.tablero[zombie.getX() +1][zombie.getY()] = "zombie";
                    }
                    String planta = this.tablero[zombie.getX()][zombie.getY() - 1].split("\\(")[0];
                    if(planta.equals("girasol") || planta.equals("lanzaGuisante")|| planta.equals("nuez")){
                        ataqueZombie(zombie.getX(), zombie.getY() - 1, planta);  //Daño que hace el zombie a las plantas
                    }
                }
            }
        }
        return terminado;
    }
    
    /**
    * Método para que todos los zombies ataquen
    * @param fila Fila donde está el zombie
    * @param columna Columna donde está el zombie
    * @param tipoPlanta Lista de todas las plantas a las que atacan
    */
    public void ataqueZombie(int fila, int columna, String tipoPlanta){
        if(tipoPlanta.equals("girasol")){ //Atacar a los girasoles
            for(int i = 0; i < listGirasoles.size(); i++){
                Girasol girasol = listGirasoles.get(i);
                if(girasol.getY() == fila && girasol.getX() == columna){ //Direción de ataque
                    girasol.setResistencia(girasol.getResistencia() - 1); //Daño que hacen
                    if(girasol.getResistencia() == 0){ //Si resistencia = 0 borrar la label
                        this.tablero[girasol.getY()][girasol.getX()] = null;
                        this.listGirasoles.remove(i);
                    }
                }
            }
        }else{
            for(int i = 0; i < listLanzaGuisantes.size(); i++){ //Atacar a los lanzaguisantes
                LanzaGuisantes lanzaGuisantes  = listLanzaGuisantes.get(i);
                if(lanzaGuisantes.getY() == fila && lanzaGuisantes.getX() == columna){ //Dirección de ataque
                    lanzaGuisantes.setResistencia(lanzaGuisantes.getResistencia() - 1); //Daño que hacen
                    if(lanzaGuisantes.getResistencia() ==  0){ //Si ressistencia = 0 borrar label
                        this.tablero[lanzaGuisantes.getY()][lanzaGuisantes.getX()] = null;
                        this.listLanzaGuisantes.remove(i);
                    }
                }
            }
        }
        if(tipoPlanta.equals("nuez")){ //Atacar a las nueces
           for(int i = 0; i < listNueces.size(); i++){
             Nuez Nuez = listNueces.get(i);
             if(Nuez.getY() == fila && Nuez.getX() == columna){ //Dirección de ataque
               Nuez.setResistencia(Nuez.getResistencia() - 1); //Daño que hacen
                if(Nuez.getResistencia() == 0){ //Si resistencia = 0 borrar label
                  this.tablero[Nuez.getY()][Nuez.getX()] = null;
                     this.listNueces.remove(i);
                }
            }
        }
    }
}

    /**
    * Método para que los lanzaguisantes disparen y hagan daño a los zombies
    * @return Los lanzaguisantes disparan
    */
    public boolean dispararLanzaGuisantes(){
        boolean terminado = false; //Si la partida no ha terminado
        for(int i = 0; i < this.listLanzaGuisantes.size(); i++){
            LanzaGuisantes lanzaGuisantes = this.listLanzaGuisantes.get(i);
            boolean disparado = false; //No dispara porque no hay zombies
            for(int j = 0; j < this.listZombies.size() && !disparado; j++){
                Zombies zombie = this.listZombies.get(j);
                if(lanzaGuisantes.getY() == zombie.getX()){
                    disparado = true; //Si dispara
                    zombie.setResistencia(zombie.getResistencia() - lanzaGuisantes.getDaño()); //Disparo hace daño
                    this.tablero[zombie.getX()][zombie.getY()] = "zombie"; //Dispara a zombie normal
                    if(zombie.getResistencia() == 0){ //Cuando la resistencia del zombie llega a 0 -> borrar label zombie
                        this.tablero[zombie.getX()][zombie.getY()] = null; //Dirección de disparo
                        this.listZombies.remove(j);
                        this.numZombiesRestantes--; //Contador de zombies que quedan por aparecer
                        if(this.numZombiesCreados == this.numZombiesMax && this.numZombiesRestantes == 0){ //Zombies que aún tienen que aparecer
                            terminado = true; //Termina la partida -> Zombies ganan
                        }
                    }
                }
            }
        }
        for(int i = 0; i < this.listLanzaGuisantes.size(); i++){
            LanzaGuisantes lanzaGuisantes = this.listLanzaGuisantes.get(i);
            boolean disparado = false; //No disparar
            for(int c = 0; c < this.listCaracubo.size() && !disparado; c++){
                Caracubo Caracubo = this.listCaracubo.get(c);
                if(lanzaGuisantes.getY() == Caracubo.getX()){
                    disparado = true; //Disparar
                    Caracubo.setResistencia(Caracubo.getResistencia() - lanzaGuisantes.getDaño()); //Disparo hace daño
                    this.tablero[Caracubo.getX()][Caracubo.getY()] = "caracubo"; //Dispara a zombie caracubo
                    if(Caracubo.getResistencia() == 0){ //Cuando resistencia = 0 borrar label zombie
                        this.tablero[Caracubo.getX()][Caracubo.getY()] = null; //Dirección de disparo
                        this.listCaracubo.remove(c);
                        this.numZombiesRestantes--; //Contador de zombies que quedan por aparecer
                        if(this.numZombiesCreados == this.numZombiesMax && this.numZombiesRestantes == 0){ //Zombies que aún tienen que aparecer
                            terminado = true; //Termina la partida -> Zombies ganan
                        }
                    }
                }
            }
        }
        for(int i = 0; i < this.listLanzaGuisantes.size(); i++){
            LanzaGuisantes lanzaGuisantes = this.listLanzaGuisantes.get(i);
            boolean disparado = false; //No disparar
            for(int c = 0; c < this.listDeportista.size() && !disparado; c++){
                Deportista deportista = this.listDeportista.get(c);
                if(lanzaGuisantes.getY() == deportista.getX()){
                    disparado = true; //Disparar
                    deportista.setResistencia(deportista.getResistencia() - lanzaGuisantes.getDaño()); //Disparo hace daño
                    this.tablero[deportista.getX()][deportista.getY()] = "deportista"; //Disparar a zombie deportista
                    if(deportista.getResistencia() == 0){ //Si resistencia = 0 -> borrar label zombie
                        this.tablero[deportista.getX()][deportista.getY()] = null; //Dirección de disparo 
                        this.listDeportista.remove(c);
                        this.numZombiesRestantes--; //Contador de zombies que quedan por aparecer
                        if(this.numZombiesCreados == this.numZombiesMax && this.numZombiesRestantes == 0){ //Zombies que aún tienen que aparecer
                            terminado = true; //Termina la partida -> Zombies ganan
                        }
                    }
                }
            }
        }
        return terminado;
    }
    
    /**
    * Método para calcular los puntos que consigue un jugador y apuntarlos en el fichero
    */
    public void calcularPuntos(){
        Integer pnt = this.soles + listGirasoles.size() * 20 + listLanzaGuisantes.size() * 50 + listNueces.size() * 50;
        //Puntos = soles restantes + precio de las plantas colocadas
        try{
            File inputFile = new File("src/plantasZombies/puntuacion.txt"); //Apuntar puntuación en fichero
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
            this.listJugadores.put("72263844Y", new Jugador("72263844Y", "Marina", pnt));
            Jugador jgd;
            for (String key : listJugadores.keySet()) {
                jgd = this.listJugadores.get(key);
                writer.write(jgd.getDni() + " " + jgd.getNombre()+ " " + jgd.getPuntuacion() + "\n");
            }
            writer.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}