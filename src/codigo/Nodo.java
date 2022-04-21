package codigo;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private List<Integer> torre1 = new ArrayList<Integer>();
    private List<Integer> torre2 = new ArrayList<Integer>();
    private List<Integer> torre3 = new ArrayList<Integer>();
    private boolean visitado;

    //#region Constructores
    public Nodo(List<Integer> t1, List<Integer> t2, List<Integer> t3){
        torre1.addAll(t1);
        torre2.addAll(t2);
        torre3.addAll(t3);
        visitado = false;

    }

    public Nodo(Nodo n){
        torre1.addAll(n.getTorre1());
        torre2.addAll(n.getTorre2());
        torre3.addAll(n.getTorre3());
    }
    //#endregion

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
    //#endregion
    
}
