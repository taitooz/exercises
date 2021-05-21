/*
Se tiene un arreglo ORIGINAL precargado de secuencias de números de
tamaño MAX con ceros entre secuencias, al principio y al final del arreglo.
Además se tiene un arreglo ORDEN1 precargado de tamaño MAX. ORDEN1
tiene posiciones de inicio de secuencia de ORIGINAL que permite recorrer de
forma ascendente y consecutiva las secuencias que suman una cantidad par
(ORDEN1 tiene valores -1 al final de las posiciones válidas). Se pide:
– Dada una posición válida ingresada por el usuario desde teclado, eliminar la
secuencia en ORIGINAL con inicio en esa posición y actualizar el arreglo ORDEN1.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio_19 {

    public static int MAX = 10;

    public static void main(String[] args) {
        int[] original = {0, 1, 3, 2, 0, 7, 0, 3, 1, 0};
        int[] orden1 = {7, 1, -1, -1, -1, -1, -1, -1, -1, -1};
        int posicion;
        int tamanio = 0;
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        try {
            imprimir_arreglo_linea_int(original);
            imprimir_arreglo_linea_int(orden1);
            System.out.println("Ingrese una posicion: ");
            posicion = Integer.valueOf(entrada.readLine());

            if ((original[posicion] != 0) && (original[posicion - 1] == 0)) {
                System.out.println("Aca empieza una secuencia");
                eliminar_secuencia(original, posicion);
                actualizar_arreglo_indices(orden1, posicion, tamanio);
            } else {
                System.out.println("La posicion no corresponde a un inicio de secuencia");
            }
            imprimir_arreglo_linea_int(original);
            imprimir_arreglo_linea_int(orden1);
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }


    public static void actualizar_arreglo_indices(int[] orden1, int posicion, int tamanio) {
        int indice = 0;
        while (indice < MAX - 1) {
            if (orden1[indice] != posicion) {
                indice++;
            } else if (orden1[indice] == posicion) {
                corrimiento_a_izquierda(orden1, posicion); //aca hay un problema cuando indice es 0
                System.out.println("la secuencia eliminada era par");
                for (int i = 0; i < MAX; i++) {
                    if ((orden1[i] > posicion) && (orden1[i] != -1)) {
                        orden1[i] = (posicion + 3); //como traer el valor de un parametro calculado en otro metodo
                    }
                }
            }
        }
    }


    public static void eliminar_secuencia(int[] arrint, int posicion) {
        int fin, pos, tamanio;
        pos = posicion; //estas variables auxiliares me confunden
        fin = buscar_fin(arrint, pos);
        System.out.println("La secuencia termina en " + fin);
        tamanio = fin - pos + 1; //a este valor lo quiero usar en otro metodo
        System.out.println("El tamanio es " + tamanio);
        for (int i = 0; i <= tamanio; i++) {
            corrimiento_a_izquierda(arrint, pos);
        }
    }

    public static int buscar_fin(int[] arrint, int pos) {
        while ((pos < MAX - 1) && (arrint[pos] != 0)) {
            pos++;
        }
        return pos - 1;
    }

    public static void corrimiento_a_izquierda(int[] arrint, int posicion) {
        while (posicion < MAX - 1) {
            arrint[posicion] = arrint[posicion + 1];
            posicion++;
        }
    }

    public static void imprimir_arreglo_linea_int(int[] arr) {
        System.out.print("Arreglo de secuencias int\n|");
        for (int pos = 0; pos < MAX; pos++) {
            System.out.print(arr[pos] + "|");
        }
        System.out.print("\n");
    }

}
