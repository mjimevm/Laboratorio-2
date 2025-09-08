
// Clase Tablero que representa el tablero del juego
import java.util.ArrayList;

public class Tablero {
    //  Atributos
    private ArrayList<Carta> cartas;
    private int tamano;

    // Constructor
    public Tablero(int n) {
        this.tamano = n;
        this.cartas = new ArrayList<>();
    }
    // Gets
    public int getTamano() {
        return tamano;
    }
    public ArrayList<Carta> getCartas() {
        return cartas;
    }


}