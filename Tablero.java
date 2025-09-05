
import java.util.ArrayList;

public class Tablero {
    private ArrayList<Carta> cartas;
    private int tamano;

    public Tablero(int n) {
        this.tamano = n;
        this.cartas = new ArrayList<>();
    }
    public int getTamano() {
        return tamano;
    }
    public ArrayList<Carta> getCartas() {
        return cartas;
    }


}