import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) {
        String[] palabras = {
            "gato", "perro", "pájaro",
            "manzana", "banana", "naranja",
            "rojo", "azul", "verde"
        };

        // Ordenar las palabras antes de jugar
        ordenarPalabras(palabras);

        // Pedir al usuario que ingrese la palabra a buscar
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa una palabra para buscar: ");
        String palabraBuscada = scanner.nextLine();

        // Buscar la palabra
        if (buscarPalabra(palabras, palabraBuscada)) {
            System.out.println("La palabra '" + palabraBuscada + "' se encuentra en la lista.");
        } else {
            System.out.println("La palabra '" + palabraBuscada + "' no se encuentra en la lista.");
        }

        // Elegir una palabra aleatoria
        Random rand = new Random();
        String palabraAdivinar = palabras[rand.nextInt(palabras.length)];

        // Inicializar variables del juego
        char[] letrasAdivinadas = new char[26];
        int contador = 0;
        int intentos = 6;

        // Comenzar el juego
        while (intentos > 0) {
            // Mostrar el progreso de la palabra
            StringBuilder progreso = new StringBuilder();
            for (char letra : palabraAdivinar.toCharArray()) {
                if (new String(letrasAdivinadas, 0, contador).indexOf(letra) >= 0) {
                    progreso.append(letra);
                } else {
                    progreso.append('_');
                }
            }
            System.out.println("Palabra: " + progreso);

            // Pedir al usuario que adivine una letra
            System.out.print("Adivina una letra: ");
            char letra = scanner.nextLine().toLowerCase().charAt(0);

            // Comprobar si la letra está en la palabra
            if (palabraAdivinar.indexOf(letra) >= 0) {
                letrasAdivinadas[contador] = letra; // Guardar letra adivinada
                contador++; // Incrementar el contador
                System.out.println("¡Correcto!");
            } else {
                intentos--;
                System.out.println("Incorrecto. Te quedan " + intentos + " intentos.");
            }

            // Comprobar si el jugador ha ganado
            if (progreso.toString().indexOf('_') < 0) {
                System.out.println("¡Ganaste! La palabra era: " + palabraAdivinar);
                break;
            }
        }

        if (intentos == 0) {
            System.out.println("Perdiste. La palabra era: " + palabraAdivinar);
        }

        scanner.close();
    }

    public static void ordenarPalabras(String[] palabras) {
        for (int i = 0; i < palabras.length - 1; i++) {
            for (int j = 0; j < palabras.length - 1 - i; j++) {
                if (palabras[j].compareTo(palabras[j + 1]) > 0) {
                    // Intercambiar palabras
                    String temp = palabras[j];
                    palabras[j] = palabras[j + 1];
                    palabras[j + 1] = temp;
                }
            }
        }
    }

    public static boolean buscarPalabra(String[] palabras, String palabraBuscada) {
        for (String palabra : palabras) {
            if (palabra.equalsIgnoreCase(palabraBuscada)) {
                return true; // Palabra encontrada
            }
        }
        return false; // Palabra no encontrada
    }
}
