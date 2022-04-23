package codigo;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private List<Integer> torre1 = new ArrayList<>();
    private List<Integer> torre2 = new ArrayList<>();
    private List<Integer> torre3 = new ArrayList<>();
    private Nodo nPadre;
    private int valor;

    //#region Constructores
    public Nodo(List<Integer> t1, List<Integer> t2, List<Integer> t3){
        torre1.addAll(t1);
        torre2.addAll(t2);
        torre3.addAll(t3);
        nPadre = null;
        valor = 0;

    }

    public Nodo(Nodo n){
        torre1.addAll(n.getTorre1());
        torre2.addAll(n.getTorre2());
        torre3.addAll(n.getTorre3());
        nPadre = null;
        valor = 0;
    }

    public Nodo(Nodo n, Nodo nPadre) {
        torre1.addAll(n.getTorre1());
        torre2.addAll(n.getTorre2());
        torre3.addAll(n.getTorre3());
        this.nPadre = nPadre;
        valor = 0;
    }
    //#endregion

    public void insertNodoPadre(Nodo nPadre) {
        this.nPadre = nPadre;
    }

    public int topTorre(int torre) {
        int top = 0;
        try{
            if(torre == 1) {
                top = torre1.get(torre1.size() - 1);
            }
            if(torre == 2) {
                top = torre2.get(torre2.size() - 1);
            }
            if(torre == 3) {
                top = torre3.get(torre3.size() - 1);
            }
        }catch(Exception e) {
            return top;
        }
        return top;
    }

    public int[] valoresTorre2() {
        int a = 0;
        for (Integer integer : torre2) {
            a++;
        }
        int[] aux = new int[a];
        for (int i = 0; i < a; i++) {
            aux[i] = torre2.get(i);
        }
        return aux;
    }
    public String toString() {
        return this.getTorre1() + " " + this.getTorre2() + " " + this.getTorre3();
    }

    //#region setters - getters
    public void setTorre1(List<Integer> t1){
        torre1.addAll(t1);
    }

    public void setTorre2(List<Integer> t2){
        torre2.addAll(t2);
    }

    public void setTorre3(List<Integer> t3){
        torre3.addAll(t3);
    }

    public List<Integer> getTorre1(){
        return this.torre1;
    }

    public List<Integer> getTorre2(){
        return this.torre2;
    }

    public List<Integer> getTorre3(){
        return torre3;
    }

    public Nodo getPadre() {
        return nPadre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    //#endregion

    
    public boolean equals(Nodo n) {
        return (this.torre1.equals(n.torre1) && this.torre2.equals(n.torre2) && this.torre3.equals(n.torre3));
    }

}
