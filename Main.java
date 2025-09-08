// Universidad del Valle de Guatemala
// Ejercicio de Laboratorio 2 POO - Juego de Memoria
// Maria Jimena Vásquz Meléndez - 25092

// Importar la librería Scanner para las entradas de usuario
import java.util.Scanner;


// La clase principal es Main, donde se ejecuta el flujo del juego
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido al juego de memoria!");
        // Es la variable inicial, el tamaño es 0 para definirlo después
        int tamano = 0;
        Controlador controlador = new Controlador(2); // Este es el tamaño temporal

        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\nMenú:");
            System.out.println("1. Nueva Partida");
            System.out.println("2. Crear Jugadores");
            System.out.println("3. Ver puntajes");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");
            opcion = teclado.nextInt();
            while (opcion < 1 || opcion > 4) {
                System.out.println("Opción no válida. Inténtalo de nuevo.");
                opcion = teclado.nextInt();
            }
            switch (opcion) {

                // La opcion 1 es para iniciar una nueva partida con los jugadores ya creados
                case 1:
                    // Se verifica que haya al menos 2 jugadores
                    if (controlador.getJugadores().size() < 2) {
                        System.out.println("No hay jugadores suficientes. Cree los jugadores primero.");
                        break;
                    }

                    // El tamaño del tablero se define aquí
                    System.out.print("De qué tamaño quieres el tablero? (Múltiplos de 2, mínimo 2, máximo 7): ");
                    tamano = teclado.nextInt();
                    // El tamaño debe ser un múltiplo de 2, mayor que 1 y máximo 8 sino tira error
                    while (tamano < 2 || tamano % 2 != 0 || tamano > 8) {
                        System.out.println("El tamaño debe ser un múltiplo de 2, mayor que 1 y máximo 8. Inténtalo de nuevo:");
                        tamano = teclado.nextInt();
                    }
                    controlador.cambiarTamano(tamano);
                    for (Jugador j : controlador.getJugadores()) j.setPuntos(0);
                    int turno = 0;
                    while (!controlador.todasEmparejadas(tamano)) {
                        Jugador jugadorActual = controlador.getJugadores().get(turno);
                        System.out.println("\nTurno de: " + jugadorActual.getNombre());

                        // Se muestra el tablero
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
                                if (carta.isMatched() || carta.isRevealed()) {
                                    System.out.print("  " + carta.getSimbolo() + " ");
                                } else {
                                    System.out.print(" [■]");
                                }
                                idx++;
                            }
                            System.out.println();
                        }

                        int fila1 = -1, col1 = -1;
                        boolean seleccionValida1 = false;
                        while (!seleccionValida1) {
                            System.out.print("Seleccione la primera carta (fila): ");
                            fila1 = teclado.nextInt();
                            System.out.print("Seleccione la primera carta (columna): ");
                            col1 = teclado.nextInt();
                            seleccionValida1 = controlador.seleccionarCarta(fila1, col1, tamano);
                            if (!seleccionValida1)
                                System.out.println("Selección inválida. Intente nuevamente.");
                        }

                        // Mostrar tablero (solo una carta 1 revelada)
                        System.out.print("     ");
                        for (int col = 0; col < tamano; col++) {
                            System.out.printf("%3d ", col);
                        }
                        System.out.println();
                        idx = 0;
                        for (int fila = 0; fila < tamano; fila++) {
                            System.out.printf("%3d ", fila);
                            for (int col = 0; col < tamano; col++) {
                                Carta carta = controlador.getCartas().get(idx);
                                if (carta.isMatched() || carta.isRevealed()) {
                                    System.out.print("  " + carta.getSimbolo() + " ");
                                } else {
                                    System.out.print(" [■]");
                                }
                                idx++;
                            }
                            System.out.println();
                        }

                        int fila2 = -1, col2 = -1;
                        boolean seleccionValida2 = false;
                        while (!seleccionValida2) {
                            System.out.print("Seleccione la segunda carta (fila): ");
                            fila2 = teclado.nextInt();
                            System.out.print("Seleccione la segunda carta (columna): ");
                            col2 = teclado.nextInt();
                            if (fila1 == fila2 && col1 == col2) {
                                System.out.println("No puedes seleccionar la misma carta dos veces.");
                                continue;
                            }
                            seleccionValida2 = controlador.seleccionarCarta(fila2, col2, tamano);
                            if (!seleccionValida2)
                                System.out.println("Selección inválida o repetida. Intente nuevamente.");
                        }

                        // Mostrar tablero (se revelan ambas cartas)
                        System.out.print("     ");
                        for (int col = 0; col < tamano; col++) {
                            System.out.printf("%3d ", col);
                        }
                        System.out.println();
                        idx = 0;
                        for (int fila = 0; fila < tamano; fila++) {
                            System.out.printf("%3d ", fila);
                            for (int col = 0; col < tamano; col++) {
                                Carta carta = controlador.getCartas().get(idx);
                                if (carta.isMatched() || carta.isRevealed()) {
                                    System.out.print("  " + carta.getSimbolo() + " ");
                                } else {
                                    System.out.print(" [■]");
                                }
                                idx++;
                            }
                            System.out.println();
                        }

                        boolean esPar = controlador.compararCartas(fila1, col1, fila2, col2, tamano, jugadorActual);
                        if (esPar) {
                            System.out.println("¡Emparejaste un par!");
                        } else {
                            System.out.println("No es un par.");
                            teclado.nextLine();
                            controlador.ocultarCarta(fila1, col1, tamano);
                            controlador.ocultarCarta(fila2, col2, tamano);

                            // Mostrar tablero después de ocultar
                            System.out.print("     ");
                            for (int col = 0; col < tamano; col++) {
                                System.out.printf("%3d ", col);
                            }
                            System.out.println();
                            idx = 0;
                            for (int fila = 0; fila < tamano; fila++) {
                                System.out.printf("%3d ", fila);
                                for (int col = 0; col < tamano; col++) {
                                    Carta carta = controlador.getCartas().get(idx);
                                    if (carta.isMatched() || carta.isRevealed()) {
                                        System.out.print("  " + carta.getSimbolo() + " ");
                                    } else {
                                        System.out.print(" [■]");
                                    }
                                    idx++;
                                }
                                System.out.println();
                            }
                            turno = 1 - turno; 
                        }
                    }
                    // Se muestran los resultados
                    System.out.println("\nPuntajes finales:");
                    for (Jugador jugador : controlador.getJugadores()) {
                        System.out.println(jugador.getNombre() + ": " + jugador.getPuntos());
                    }
                    // Se muestra el ganador
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
                    break;

                // La opcion 2 es para crear los jugadores
                case 2:

                    // Solamente se permiten dos jugadores, si quiere crear otros, se reinician los jugadores
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
                    // Se recorre la lista de jugadores y se piden los nombres
                    for (int i = controlador.getJugadores().size() + 1; i <= 2; i++) {
                        System.out.print("Ingrese el nombre del jugador " + i + ": ");
                        String nombre = teclado.next();
                        controlador.getJugadores().add(new Jugador(nombre));
                    }
                    System.out.println("Jugadores creados correctamente");
                    break;

                // La opcion 3 es para ver los puntajes de los jugadores
                case 3:
                    if (controlador.getJugadores().isEmpty()) {
                        System.out.println("No hay jugadores aún. Cree los jugadores primero");
                        break;
                    }
                    System.out.println("Puntajes de los jugadores:");
                    for (Jugador jugador : controlador.getJugadores()) {
                        System.out.println(jugador);
                    }
                    break;

                // La opcion 4 es para salir del juego
                case 4:
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    break;


                // Si la opcion no es válida, se le notifica al usuario
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }
    }
}