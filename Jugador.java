// Clase Jugador que representa a un jugador en el juego

public class Jugador {
    private int puntos;
    private int juegosGanados;
    private String nombre;

    // Constructor
    public Jugador(String nombre) {
        this.puntos = 0;
        this.juegosGanados = 0;
        this.nombre = nombre;
    }

    // Gets y sets
    public int getPuntos() {
        return puntos;
    }
    public int getJuegosGanados() {
        return juegosGanados;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public void setJuegosGanados(int juegosGanados) {
        this.juegosGanados = juegosGanados;
    }
    public String getNombre() {
        return nombre;
    }
    
    @Override
    // Convertir a String
    public String toString() {
        return "Jugador: " + nombre +
                ", puntos: " + puntos +
                ", juegosGanados: " + juegosGanados;
    }
}