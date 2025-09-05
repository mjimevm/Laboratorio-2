import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido al juego de memoria!");

        int tamano = 0;
        Controlador controlador = new Controlador(2); // tamaño provisional

        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\nMenú:");
            System.out.println("1. Nueva Partida");
            System.out.println("2. Crear Jugadores");
            System.out.println("3. Ver puntajes");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");

            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
            } else {
                System.out.println("Debe ingresar un número.");
                teclado.next();
                continue;
            }

            switch (opcion) {
                case 1:
                    if (controlador.getJugadores().size() < 2) {
                        System.out.println("No hay jugadores suficientes. Cree los jugadores primero.");
                        break;
                    }
                    System.out.print("De qué tamaño quieres el tablero? (Múltiplos de 2, mínimo 2, máximo 7): ");
                    tamano = teclado.nextInt();
                    while (tamano < 2 || tamano % 2 != 0 || tamano > 7) {
                        System.out.println("El tamaño debe ser un múltiplo de 2, mayor que 1 y máximo 7. Inténtalo de nuevo:");
                        tamano = teclado.nextInt();
                    }
                    controlador.cambiarTamano(tamano);
                    for (Jugador j : controlador.getJugadores()) j.setPuntos(0);
                    int turno = 0;
                    while (!controlador.todasEmparejadas(tamano)) {
                        Jugador jugadorActual = controlador.getJugadores().get(turno);
                        System.out.println("\nTurno de: " + jugadorActual.getNombre());
                        mostrarTablero(controlador, tamano);

                        int fila1 = -1, col1 = -1;
                        boolean seleccionValida1 = false;
                        while (!seleccionValida1) {
                            System.out.print("Seleccione la primera carta (fila columna): ");
                            fila1 = teclado.nextInt();
                            col1 = teclado.nextInt();
                            seleccionValida1 = controlador.seleccionarCarta(fila1, col1, tamano);
                            if (!seleccionValida1)
                                System.out.println("Selección inválida. Intente nuevamente.");
                        }
                        mostrarTablero(controlador, tamano);

                        int fila2 = -1, col2 = -1;
                        boolean seleccionValida2 = false;
                        while (!seleccionValida2) {
                            System.out.print("Seleccione la segunda carta (fila columna): ");
                            fila2 = teclado.nextInt();
                            col2 = teclado.nextInt();
                            if (fila1 == fila2 && col1 == col2) {
                                System.out.println("No puedes seleccionar la misma carta dos veces.");
                                continue;
                            }
                            seleccionValida2 = controlador.seleccionarCarta(fila2, col2, tamano);
                            if (!seleccionValida2)
                                System.out.println("Selección inválida o repetida. Intente nuevamente.");
                        }
                        mostrarTablero(controlador, tamano);

                        boolean esPar = controlador.compararCartas(fila1, col1, fila2, col2, tamano, jugadorActual);
                        if (esPar) {
                            System.out.println("¡Emparejaste un par!");
                        } else {
                            System.out.println("No es un par.");
                            teclado.nextLine();
                            controlador.ocultarCarta(fila1, col1, tamano);
                            controlador.ocultarCarta(fila2, col2, tamano);
                            mostrarTablero(controlador, tamano);
                            turno = 1 - turno; 
                        }
                    }
                    mostrarResultados(controlador);
                    mostrarGanador(controlador);
                    break;

                case 2:
                    if (controlador.getJugadores().size() >= 2) {
                        System.out.println("Ya hay 2 jugadores creados.");
                        System.out.println("¿Quiere reiniciar los jugadores? (s/n)");
                        String respuesta = teclado.next();
                        if (respuesta.equalsIgnoreCase("s")) {
                            controlador.getJugadores().clear();
                        } else {
                            break;
                        }
                    }
                    for (int i = controlador.getJugadores().size() + 1; i <= 2; i++) {
                        System.out.print("Ingrese el nombre del jugador " + i + ": ");
                        String nombre = teclado.next();
                        controlador.getJugadores().add(new Jugador(nombre));
                    }
                    System.out.println("Jugadores creados correctamente.");
                    break;

                case 3:
                    if (controlador.getJugadores().isEmpty()) {
                        System.out.println("No hay jugadores aún. Cree los jugadores primero.");
                        break;
                    }
                    System.out.println("Puntajes de los jugadores:");
                    for (Jugador jugador : controlador.getJugadores()) {
                        System.out.println(jugador);
                    }
                    break;

                case 4:
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }
        teclado.close();
    }

    public static void mostrarTablero(Controlador controlador, int tamano) {
        System.out.print("     ");
        for (int col = 0; col < tamano; col++) {
            System.out.printf("%3d ", col);
        }
        System.out.println();
        int idx = 0;
        for (int fila = 0; fila < tamano; fila++) {
            System.out.printf("%3d ", fila);
            for (int col = 0; col < tamano; col++) {
                Carta carta = controlador.getCartas().get(idx);
                if (carta.isMatched()) {
                    System.out.print("  " + carta.getSimbolo() + " ");
                } else if (carta.isRevealed()) {
                    System.out.print("  " + carta.getSimbolo() + " ");
                } else {
                    System.out.print(" [■]");
                }
                idx++;
            }
            System.out.println();
        }
    }

    public static void mostrarResultados(Controlador controlador) {
        System.out.println("\nPuntajes finales:");
        for (Jugador jugador : controlador.getJugadores()) {
            System.out.println(jugador.getNombre() + ": " + jugador.getPuntos());
        }
    }

    public static void mostrarGanador(Controlador controlador) {
        Jugador j1 = controlador.getJugadores().get(0);
        Jugador j2 = controlador.getJugadores().get(1);
        if (j1.getPuntos() > j2.getPuntos()) {
            j1.setJuegosGanados(j1.getJuegosGanados() + 1);
            System.out.println("Ganador: " + j1.getNombre());
        } else if (j2.getPuntos() > j1.getPuntos()) {
            j2.setJuegosGanados(j2.getJuegosGanados() + 1);
            System.out.println("Ganador: " + j2.getNombre());
        } else {
            System.out.println("¡Empate!");
        }
    }
}