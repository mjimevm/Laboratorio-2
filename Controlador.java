// Clase Controlador que maneja la lógica del juego

import java.util.ArrayList;


public class Controlador {
    // Atributos propios
    private ArrayList<Carta> cartas;
    private ArrayList<Jugador> jugadores;

    // Constructor
    public Controlador(int tamano) {
        cartas = new ArrayList<>();
        jugadores = new ArrayList<>();
        inicializarCartas(tamano);
    }

    // Para inciar las cartas
    public void inicializarCartas(int tamano) {
        cartas.clear();
        int pares = (tamano * tamano) / 2;
        for (int i = 1; i <= pares; i++) {
            char simbolo = (char) ('A' + i - 1);
            cartas.add(new Carta(simbolo));
            cartas.add(new Carta(simbolo));
        }
        for (int i = cartas.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Carta temp = cartas.get(i);
            cartas.set(i, cartas.get(j));
            cartas.set(j, temp);
        }
    }

    // Gets
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    // Para setear el tamaño del tablero
    public void cambiarTamano(int tamano) {
        inicializarCartas(tamano);
    }

    // Métodos del juego
    public boolean seleccionarCarta(int fila, int col, int tamano) {
        int idx = fila * tamano + col;
        if (idx >= 0 && idx < cartas.size() && !cartas.get(idx).isMatched() && !cartas.get(idx).isRevealed()) {
            cartas.get(idx).setRevealed(true);
            return true;
        }
        return false;
    }

    public boolean compararCartas(int fila1, int col1, int fila2, int col2, int tamano, Jugador jugador) {
        int idx1 = fila1 * tamano + col1;
        int idx2 = fila2 * tamano + col2;
        Carta carta1 = cartas.get(idx1);
        Carta carta2 = cartas.get(idx2);
        if (carta1.getSimbolo() == carta2.getSimbolo()) {
            carta1.setMatched(true);
            carta2.setMatched(true);
            jugador.setPuntos(jugador.getPuntos() + 1);
            return true;
        }
        return false;
    }

    public void ocultarCarta(int fila, int col, int tamano) {
        int idx = fila * tamano + col;
        if (idx >= 0 && idx < cartas.size()) {
            cartas.get(idx).setRevealed(false);
        }
    }

    public boolean todasEmparejadas(int tamano) {
        for (Carta carta : cartas) {
            if (!carta.isMatched()) {
                return false;
            }
        }
        return true;
    }

    public Carta getCarta(int fila, int col, int tamano) {
        int idx = fila * tamano + col;
        return cartas.get(idx);
    }
}