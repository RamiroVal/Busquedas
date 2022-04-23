package codigo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {

    // Uso de pilas para Búsqueda en Profundidad y colas para Búsqueda en Amplitud.
    private ArrayDeque<Nodo> ndAbiertos = new ArrayDeque<>();
    private List<Nodo> ndCerrados = new ArrayList<>();
    private Nodo estMeta;
    private Nodo estActual;
    private char tBusqueda;

    public Main(char tBusqueda, int cDiscos){
        this.tBusqueda = tBusqueda;
        inicializar(cDiscos);
    }

    public void imprimeNodos(List<Nodo> n) {
        for (Nodo nodo : n) {
            imprimeEstado(nodo);
        }
    }

    public void imprimeNodos(Deque<Nodo> n) {
        for (Nodo nodo : n) {
            imprimeEstado(nodo);
        }
    }

    public void busquedaAmplitud() {
        int iteraciones = 0;
        while(true) {
            System.out.println("--------Inicio--------");
            if(ndAbiertos.isEmpty()) {
                System.out.println("Solución no encontrada.");
                System.out.println("Cantidad de iteraciones: " + iteraciones);
                return;
            }
            System.out.println("Nodos abiertos:");
            imprimeNodos(ndAbiertos);
            System.out.println("Nodos cerrados:");
            imprimeNodos(ndCerrados);
            estActual = ndAbiertos.pop();
            if(estActual.equals(estMeta)) {
                System.out.println("Solución encontrada!");
                imprimeEstado(estActual);
                System.out.println("Iteraciones totales: " + iteraciones);
                return;
            }else {
                System.out.println("Nodo Actual");
                imprimeEstado(estActual);
                sucesores(estActual);
                ndCerrados.add(estActual);
            }
            iteraciones++;
        }
    }

    public void busquedaProfundidad() {
        int iteraciones = 0;
        while(true) {
            System.out.println("--------Inicio--------");
            if(ndAbiertos.isEmpty()) {
                System.out.println("Solución no encontrada.");
                System.out.println("Cantidad de iteraciones: " + iteraciones);
                return;
            }
            
            System.out.println("Nodos abiertos:");
            imprimeNodos(ndAbiertos);
            
            System.out.println("Nodos cerrados:");
            imprimeNodos(ndCerrados);
            estActual = ndAbiertos.pop();
            if(estActual.equals(estMeta)) {
                System.out.println("Solución encontrada!");
                imprimeEstado(estActual);
                System.out.println("Iteraciones totales: " + iteraciones);
                return;
            } else {
                System.out.println("Nodo Actual");
                imprimeEstado(estActual);
                
                ndCerrados.add(estActual);
            }
            sucesores(estActual);
            iteraciones++;
        }
        
    }

    public void sucesores(Nodo n) {
        int top1 = n.topTorre(1);
        int top2 = n.topTorre(2);
        int top3 = n.topTorre(3);

        Nodo n1 = new Nodo(n);
        Nodo n2 = new Nodo(n);
        Nodo n3 = new Nodo(n);
        Nodo n4 = new Nodo(n);
        Nodo n5 = new Nodo(n);
        Nodo n6 = new Nodo(n);

        if(top1 > 0) {
            sucesorT1(n1, n2, top1, top2, top3);
        }
        if(top2 > 0) {
            sucesorT2(n3, n4, top1, top2, top3);
        }
        if(top3 > 0) {
            sucesorT3(n5, n6, top1, top2, top3);
        }

    }

    public boolean nodoRepetido(Nodo n) {
        for (Nodo nodo : ndAbiertos) {
            if(nodo.equals(n)){
                return true;
            }
        }
        for (Nodo nodo : ndCerrados) {
            if(nodo.equals(n)){
                return true;
            }
        }
        return estActual.equals(n);
    }

    //#region Sucesores
    /***
     * Sólo pueden ocurrir 3 casos:
     * 1.- El disco se puede mover a la torre 2 y a la torre 3.
     * 2.- El disco sólo se puede mover a la torre 2.
     * 3.- El disco sólo se puede mover a la torre 3.
     * @param n1 Nodo auxiliar 1
     * @param n2 Nodo auxiliar 2
     * @param top1 Valor top en torre 1
     * @param top2 Valor top en torre 2
     * @param top3 Valor top en torre 3
     */
    public void sucesorT1(Nodo n1, Nodo n2, int top1, int top2, int top3) {
        if(top1 != 0) {
            // Caso 1
            if((top2 == 0 && top3 == 0) || (top1 < top2 && top3 == 0) || (top1 < top3 && top2 == 0) || (top1 < top2 && top1 < top3)) {
                n1.getTorre2().add(n1.getTorre1().remove(n1.getTorre1().size() - 1));
                n2.getTorre3().add(n2.getTorre1().remove(n2.getTorre1().size() - 1));
            }
            // Caso 2
            else if((top1 < top2 && top1 > top3) || (top2 == 0 && top1 > top3)) {
                n1.getTorre2().add(n1.getTorre1().remove(n1.getTorre1().size() - 1));
            }
            else if((top1 < top3 && top1 > top2) || (top3 == 0 && top1 > top2)) {
                n1.getTorre3().add(n1.getTorre1().remove(n1.getTorre1().size() - 1));
            }
        }

        if(!nodoRepetido(n1)) {
            // Si es en profundidad se usa pila, si no, se utiliza cola.
            if(tBusqueda == 'p') {
                ndAbiertos.push(n1);
            }else {
                ndAbiertos.add(n1);
            }
        }
        if(!nodoRepetido(n2)) {
            if(tBusqueda == 'p') {
                ndAbiertos.push(n2);
            }else {
                ndAbiertos.add(n2);
            }
        }

    }

    public void sucesorT2(Nodo n1, Nodo n2, int top1, int top2, int top3) {
        if(top2 != 0) {
            // Caso 1
            if((top1 == 0 && top3 == 0) || (top2 < top1 && top3 == 0) || (top2 < top3 && top1 == 0) || (top2 < top1 && top2 < top3)) {
                n1.getTorre3().add(n1.getTorre2().remove(n1.getTorre2().size() - 1));
                n2.getTorre1().add(n2.getTorre2().remove(n2.getTorre2().size() - 1));

                // n1.getTorre1().add(n1.getTorre2().remove(n1.getTorre2().size() - 1));
                // n2.getTorre3().add(n2.getTorre2().remove(n2.getTorre2().size() - 1));
            }
            // Caso 2
            else if((top2 < top1 && top2 > top3) || (top1 == 0 && top2 > top3)) {
                n1.getTorre1().add(n1.getTorre2().remove(n1.getTorre2().size() - 1));
            }
            else if((top2 < top3 && top2 > top1) || (top3 == 0 && top2 > top1)) {
                n1.getTorre3().add(n1.getTorre2().remove(n1.getTorre2().size() - 1));
            }
        }

        if(!nodoRepetido(n1)){
            if(tBusqueda == 'p') {
                ndAbiertos.push(n1);
            }else {
                ndAbiertos.add(n1);
            }
        }
        if(!nodoRepetido(n2)) {
            if(tBusqueda == 'p') {
                ndAbiertos.push(n2);
            }else {
                ndAbiertos.add(n2);
            }
        }
        
    }

    public void sucesorT3(Nodo n1, Nodo n2, int top1, int top2, int top3) {
        if(top3 != 0) {
            // Caso 1
            if((top2 == 0 && top1 == 0) || (top3 < top2 && top1 == 0) || (top3 < top1 && top2 == 0) || (top3 < top2 && top3 < top1)) {
                n1.getTorre1().add(n1.getTorre3().remove(n1.getTorre3().size() - 1));
                n2.getTorre2().add(n2.getTorre3().remove(n2.getTorre3().size() - 1));
            }
            // Caso 2
            else if((top3 < top2 && top3 > top1) || (top2 == 0 && top3 > top1)) {
                n1.getTorre2().add(n1.getTorre3().remove(n1.getTorre3().size() - 1));
            }
            else if((top3 < top1 && top3 > top2) || (top1 == 0 && top3 > top2)) {
                n1.getTorre1().add(n1.getTorre3().remove(n1.getTorre3().size() - 1));
            }
        }

        if(!nodoRepetido(n1)) {
            if(tBusqueda == 'p') {
                ndAbiertos.push(n1);
            }else {
                ndAbiertos.add(n1);
            }
        }
        if(!nodoRepetido(n2)) {
            if(tBusqueda == 'p') {
                ndAbiertos.push(n2);
            }else {
                ndAbiertos.add(n2);
            }
        }

    }
    //#endregion

    public void inicializar(int dc) {
        List<Integer> torre1 = new ArrayList<>();
        List<Integer> torre2 = new ArrayList<>();
        List<Integer> torre3 = new ArrayList<>();

        while(dc != 0) {
            torre1.add(dc);
            dc--;
        }

        estMeta = new Nodo(torre2, torre1, torre3);
        Nodo raiz = new Nodo(torre1, torre2, torre3);
        if(tBusqueda == 'p'){
            ndAbiertos.push(raiz);
            busquedaProfundidad();
        }else {
            ndAbiertos.add(raiz);
            busquedaAmplitud();
        }
    }
    
    public void imprimeEstado(Nodo n) {
        System.out.println(n.toString());
    }
    
    
    public static void main(String[] args) {
        System.out.println("Ingrese la cantidad de discos:");
        int discos = Utils.leerInt();
        System.out.println("Ingrese tipo de búsqueda (A/P):");
        char tb = Utils.leerString().toLowerCase().charAt(0);
        new Main(tb, discos);
    }
}
