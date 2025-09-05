public class Jugador {
    private int puntos;
    private int juegosGanados;
    private String nombre;

    public Jugador(String nombre) {
        this.puntos = 0;
        this.juegosGanados = 0;
        this.nombre = nombre;
    }
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
    public String toString() {
        return "Jugador: " + nombre +
                ", puntos: " + puntos +
                ", juegosGanados: " + juegosGanados;
    }
}