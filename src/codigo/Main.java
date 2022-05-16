package codigo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {

    // Uso de pilas para Búsqueda en Profundidad y colas para Búsqueda en Amplitud.
    private ArrayDeque<Nodo> ndAbiertos = new ArrayDeque<>();
    private List<Nodo> ndCerrados = new ArrayList<>();
    private List<Nodo> ndFirstBest = new LinkedList<>();
    private LinkedList<Nodo> ndAsterisco = new LinkedList<>();
    private Nodo raiz;
    private Nodo estMeta;
    private Nodo estActual;
    private char tBusqueda;
    private int movimientos;

    public Main(char tBusqueda, int cDiscos){
        this.tBusqueda = tBusqueda;
        movimientos = 0;
        inicializar(cDiscos);
    }

    public void imprimeNodos(List<Nodo> n) {
        for (Nodo nodo : n) {
            imprimeEstado(nodo);
        }
    }

    public void imprimeNodos(LinkedList<Nodo> n) {
        for (Nodo nodo : n) {
            imprimeEstado(nodo);
        }
    }

    public void imprimeNodos(Deque<Nodo> n) {
        for (Nodo nodo : n) {
            imprimeEstado(nodo);
        }
    }

    public Nodo imprimeRecorrido(Nodo n) {
        if(n.equals(raiz)) {
            System.out.println(n.toString());
            return n;
        }
        System.out.println(n.toString());
        movimientos++;
        return imprimeRecorrido(n.getPadre());
        
    }

    public void iniciaBusqueda() {
        int iteraciones = 0;
        while(true) {
            System.out.println("--------Inicio--------");
            if(tBusqueda != 'm' && tBusqueda != 's') {
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
                    System.out.println("-------Recorrido Final-------");
                    imprimeRecorrido(estActual);
                    System.out.println("Cantidad de movimientos para llegar a la solución: " + movimientos);
                    return;
                }else {
                    System.out.println("Nodo Actual");
                    imprimeEstado(estActual);
                    sucesores(estActual);
                    ndCerrados.add(estActual);
                }
                iteraciones++;
            }else if(tBusqueda == 'm') {
                if(ndFirstBest.isEmpty()) {
                    System.out.println("Solución no encontrada.");
                    System.out.println("Cantidad de iteraciones: " + iteraciones);
                    return;
                }
                System.out.println("Nodos abiertos:");
                imprimeNodos(ndFirstBest);
                System.out.println("Nodos cerrados:");
                imprimeNodos(ndCerrados);
                estActual = ndFirstBest.remove(0);
                if(estActual.equals(estMeta)) {
                System.out.println("Solución encontrada!");
                imprimeEstado(estActual);
                System.out.println("Iteraciones totales: " + iteraciones);
                System.out.println("-------Recorrido Final-------");
                imprimeRecorrido(estActual);
                System.out.println("Cantidad de movimientos para llegar a la solución: " + movimientos);
                return;
            }else {
                System.out.println("Nodo Actual");
                imprimeEstado(estActual);
                sucesores(estActual);
                ndCerrados.add(estActual);
            }
            iteraciones++;
            }else {
                if(ndAsterisco.isEmpty()) {
                    System.out.println("Solución no encontrada.");
                    System.out.println("Cantidad de iteraciones: " + iteraciones);
                    return;
                }
                System.out.println("Nodos abiertos:");
                imprimeNodos(ndAsterisco);
                System.out.println("Nodos cerrados:");
                imprimeNodos(ndCerrados);
                estActual = ndAsterisco.remove(0);
                if(estActual.equals(estMeta)) {
                System.out.println("Solución encontrada!");
                imprimeEstado(estActual);
                System.out.println("Iteraciones totales: " + iteraciones);
                System.out.println("-------Recorrido Final-------");
                imprimeRecorrido(estActual);
                System.out.println("Cantidad de movimientos para llegar a la solución: " + movimientos);
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
    }

    public void sucesores(Nodo n) {
        int top1 = n.topTorre(1);
        int top2 = n.topTorre(2);
        int top3 = n.topTorre(3);

        Nodo n1 = new Nodo(n, n);
        Nodo n2 = new Nodo(n, n);
        Nodo n3 = new Nodo(n, n);
        Nodo n4 = new Nodo(n, n);
        Nodo n5 = new Nodo(n, n);
        Nodo n6 = new Nodo(n, n);

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
        if(tBusqueda == 'p' || tBusqueda == 'a') {
            for (Nodo nodo : ndAbiertos) {
                if(nodo.equals(n)){
                    return true;
                }
            }
        }else if(tBusqueda == 'm') {
            for (Nodo nodo : ndFirstBest) {
                if(nodo.equals(n)){
                    return true;
                }
            }
        }else {
            for (Nodo nodo : ndAsterisco) {
                if(nodo.equals(n)){
                    return true;
                }
            }
        }
        
        for (Nodo nodo : ndCerrados) {
            if(nodo.equals(n)){
                return true;
            }
        }
        return estActual.equals(n);
    }

    public int calculaValor(Nodo n) {
        int[] vNodo1 = n.valoresTorre2();
        int[] vNodoM = n.valoresTorre2();
        int aux = 0;
        for(int i = 0; i < vNodo1.length; i++){
            if(vNodo1[i] == vNodoM[i]) {
                aux++;
            }
        }
        return aux;
    }

    public int calculaValorRaiz(Nodo n) {
        if(n.equals(raiz)) {
            return 0;
        }else {
            return 1 + calculaValorRaiz(n.getPadre());
        }
    }

    public int calculaValorAsterisco(Nodo n) {
        return calculaValorRaiz(n) - calculaValor(n);
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
        // Caso 1
        if((top2 == 0 && top3 == 0) || (top1 < top2 && top3 == 0) || (top1 < top3 && top2 == 0) || (top1 < top2 && top1 < top3)) {
            n1.getTorre2().add(n1.getTorre1().remove(n1.getTorre1().size() - 1));
            n2.getTorre3().add(n2.getTorre1().remove(n2.getTorre1().size() - 1));
            if(!nodoRepetido(n2)) {
                if(tBusqueda == 'p') {
                    ndAbiertos.push(n2);
                }else if(tBusqueda == 'a') {
                    ndAbiertos.add(n2);
                }else if(tBusqueda == 'm'){
                    int valor = calculaValor(n2);
                    n2.setValor(valor);
                    if(ndFirstBest.isEmpty() || ndFirstBest.get(ndFirstBest.size() - 1).getValor() <= valor) {
                        ndFirstBest.add(n2);
                    }else if(ndFirstBest.get(0).getValor() >= valor) {
                        ndFirstBest.add(0, n2);
                    }else {
                        ndFirstBest.add(n2);
                        ordenarArray();
                    }
                }else {
                    int valor = calculaValorAsterisco(n2);
                    n2.setValor(valor);
                    if(ndAsterisco.isEmpty() || ndAsterisco.get(0).getValor() >= valor){
                        ndAsterisco.addFirst(n2);
                    }else if(ndAsterisco.get(ndAsterisco.size() - 1).getValor() <= valor){
                        ndAsterisco.add(n2);
                    }else {
                        ndAsterisco.add(n2);
                        ordenarMenorMayor();
                    }
                }
            }

        }
        // Caso 2
        else if((top1 < top2 && top1 > top3) || (top2 == 0 && top1 > top3)) {
            n1.getTorre2().add(n1.getTorre1().remove(n1.getTorre1().size() - 1));
        }
        // Caso 3
        else if((top1 < top3 && top1 > top2) || (top3 == 0 && top1 > top2)) {
            n1.getTorre3().add(n1.getTorre1().remove(n1.getTorre1().size() - 1));
        }

        if(!nodoRepetido(n1)) {
            if(tBusqueda == 'p') {
                ndAbiertos.push(n1);
            }else if(tBusqueda == 'a') {
                ndAbiertos.add(n1);
            }else if(tBusqueda == 'm') {
                int valor = calculaValor(n1);
                n1.setValor(valor);
                if(ndFirstBest.isEmpty() || ndFirstBest.get(ndFirstBest.size() - 1).getValor() <= valor) {
                    ndFirstBest.add(n1);
                }else {
                    ndFirstBest.add(n1);
                    ordenarArray();
                }
            }else {
                int valor = calculaValorAsterisco(n1);
                n1.setValor(valor);
                if(ndAsterisco.isEmpty() || ndAsterisco.get(0).getValor() >= valor) {
                    ndAsterisco.addFirst(n1);
                }else {
                    ndAsterisco.add(n1);
                    ordenarMenorMayor();
                }
            }
        }
        
    }
    
    public void sucesorT2(Nodo n1, Nodo n2, int top1, int top2, int top3) {
        // Caso 1
        if((top1 == 0 && top3 == 0) || (top2 < top1 && top3 == 0) || (top2 < top3 && top1 == 0) || (top2 < top1 && top2 < top3)) {
            n1.getTorre3().add(n1.getTorre2().remove(n1.getTorre2().size() - 1));
            n2.getTorre1().add(n2.getTorre2().remove(n2.getTorre2().size() - 1));
            if(!nodoRepetido(n2)) {
                if(tBusqueda == 'p') {
                    ndAbiertos.push(n2);
                }else if(tBusqueda == 'a') {
                    ndAbiertos.add(n2);
                }else if(tBusqueda == 'm'){
                    int valor = calculaValor(n2);
                    n2.setValor(valor);
                    if(ndFirstBest.isEmpty() || ndFirstBest.get(ndFirstBest.size() - 1).getValor() <= valor) {
                        ndFirstBest.add(n2);
                    }else if(ndFirstBest.get(0).getValor() >= valor) {
                        ndFirstBest.add(0, n2);
                    }else {
                        ndFirstBest.add(n2);
                        ordenarArray();
                    }
                }else {
                    int valor = calculaValorAsterisco(n2);
                    n2.setValor(valor);
                    if(ndAsterisco.isEmpty() || ndAsterisco.get(0).getValor() >= valor){
                        ndAsterisco.addFirst(n2);
                    }else if(ndAsterisco.get(ndAsterisco.size() - 1).getValor() <= valor){
                        ndAsterisco.add(n2);
                    }else {
                        ndAsterisco.add(n2);
                        ordenarMenorMayor();
                    }
                }
            }
        }
        // Caso 2
        else if((top2 < top1 && top2 > top3) || (top1 == 0 && top2 > top3)) {
            n1.getTorre1().add(n1.getTorre2().remove(n1.getTorre2().size() - 1));
        }
        // Caso 3
        else if((top2 < top3 && top2 > top1) || (top3 == 0 && top2 > top1)) {
            n1.getTorre3().add(n1.getTorre2().remove(n1.getTorre2().size() - 1));
        }

        if(!nodoRepetido(n1)) {
            if(tBusqueda == 'p') {
                ndAbiertos.push(n1);
            }else if(tBusqueda == 'a') {
                ndAbiertos.add(n1);
            }else if(tBusqueda == 'm') {
                int valor = calculaValor(n1);
                n1.setValor(valor);
                if(ndFirstBest.isEmpty() || ndFirstBest.get(ndFirstBest.size() - 1).getValor() <= valor) {
                    ndFirstBest.add(n1);
                }else {
                    ndFirstBest.add(n1);
                    ordenarArray();
                }
            }else {
                int valor = calculaValorAsterisco(n1);
                n1.setValor(valor);
                if(ndAsterisco.isEmpty() || ndAsterisco.get(0).getValor() >= valor) {
                    ndAsterisco.addFirst(n1);
                }else {
                    ndAsterisco.add(n1);
                    ordenarMenorMayor();
                }
            }
        }
        
    }
    
    public void sucesorT3(Nodo n1, Nodo n2, int top1, int top2, int top3) {
        // Caso 1
        if((top2 == 0 && top1 == 0) || (top3 < top2 && top1 == 0) || (top3 < top1 && top2 == 0) || (top3 < top2 && top3 < top1)) {
            n1.getTorre1().add(n1.getTorre3().remove(n1.getTorre3().size() - 1));
            n2.getTorre2().add(n2.getTorre3().remove(n2.getTorre3().size() - 1));
            if(!nodoRepetido(n2)) {
                if(tBusqueda == 'p') {
                    ndAbiertos.push(n2);
                }else if(tBusqueda == 'a') {
                    ndAbiertos.add(n2);
                }else if(tBusqueda == 'm'){
                    int valor = calculaValor(n2);
                    n2.setValor(valor);
                    if(ndFirstBest.isEmpty() || ndFirstBest.get(ndFirstBest.size() - 1).getValor() <= valor) {
                        ndFirstBest.add(n2);
                    }else if(ndFirstBest.get(0).getValor() >= valor) {
                        ndFirstBest.add(0, n2);
                    }else {
                        ndFirstBest.add(n2);
                        ordenarArray();
                    }
                }else {
                    int valor = calculaValorAsterisco(n2);
                    n2.setValor(valor);
                    if(ndAsterisco.isEmpty() || ndAsterisco.get(0).getValor() >= valor){
                        ndAsterisco.addFirst(n2);
                    }else if(ndAsterisco.get(ndAsterisco.size() - 1).getValor() <= valor){
                        ndAsterisco.add(n2);
                    }else {
                        ndAsterisco.add(n2);
                        ordenarMenorMayor();
                    }
                }
            }
        }
        // Caso 2
        else if((top3 < top2 && top3 > top1) || (top2 == 0 && top3 > top1)) {
            n1.getTorre2().add(n1.getTorre3().remove(n1.getTorre3().size() - 1));
        }
        // Caso 3
        else if((top3 < top1 && top3 > top2) || (top1 == 0 && top3 > top2)) {
            n1.getTorre1().add(n1.getTorre3().remove(n1.getTorre3().size() - 1));
        }

        if(!nodoRepetido(n1)) {
            if(tBusqueda == 'p') {
                ndAbiertos.push(n1);
            }else if(tBusqueda == 'a') {
                ndAbiertos.add(n1);
            }else if(tBusqueda == 'm') {
                int valor = calculaValor(n1);
                n1.setValor(valor);
                if(ndFirstBest.isEmpty() || ndFirstBest.get(ndFirstBest.size() - 1).getValor() <= valor) {
                    ndFirstBest.add(n1);
                }else {
                    ndFirstBest.add(n1);
                    ordenarArray();
                }
            }else {
                int valor = calculaValorAsterisco(n1);
                n1.setValor(valor);
                if(ndAsterisco.isEmpty() || ndAsterisco.get(0).getValor() >= valor) {
                    ndAsterisco.addFirst(n1);
                }else {
                    ndAsterisco.add(n1);
                    ordenarMenorMayor();
                }
            }
        }

    }
    //#endregion

    public void ordenarArray() {
        Collections.sort(ndFirstBest, new Comparator<Nodo>() {
            @Override
            public int compare(Nodo n1, Nodo n2) {
                return new Integer(n2.getValor()).compareTo(new Integer(n1.getValor()));
            }
        });
    }

    public void ordenarMenorMayor() {
        Collections.sort(ndAsterisco, new Comparator<Nodo>() {
            @Override
            public int compare(Nodo n1, Nodo n2) {
                return new Integer(n1.getValor()).compareTo(new Integer(n2.getValor()));
            }
        });
    }

    public void inicializar(int dc) {
        List<Integer> torre1 = new ArrayList<>();
        List<Integer> torre2 = new ArrayList<>();
        List<Integer> torre3 = new ArrayList<>();

        while(dc != 0) {
            torre1.add(dc);
            dc--;
        }

        estMeta = new Nodo(torre2, torre1, torre3);
        raiz = new Nodo(torre1, torre2, torre3);
        if(tBusqueda == 'p'){
            ndAbiertos.push(raiz);
            iniciaBusqueda();
        }else if(tBusqueda == 'a') {
            ndAbiertos.add(raiz);
            iniciaBusqueda();
        }else if(tBusqueda == 'm') {
            ndFirstBest.add(raiz);
            iniciaBusqueda();
        }else {
            ndAsterisco.add(raiz);
            iniciaBusqueda();
        }
    }
    
    public void imprimeEstado(Nodo n) {
        System.out.println(n.toString());
    }
    
    
    public static void main(String[] args) {
        System.out.println("Ingrese la cantidad de discos:");
        int discos = Utils.leerInt();
        System.out.println("Ingrese tipo de búsqueda (A/P/M/S):");
        char tb = Utils.leerString().toLowerCase().charAt(0);
        new Main(tb, discos);
    }
}
