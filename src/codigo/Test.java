package codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
    Nodo raiz;

    public Test() {
        List<Integer> torre1 = new ArrayList<>();
        List<Integer> torre2 = new ArrayList<>();
        List<Integer> torre3 = new ArrayList<>();

        int dc = 3;
        while(dc != 0) {
            torre1.add(dc);
            dc--;
        }

        raiz = new Nodo(torre1, torre2, torre3);

        Nodo aux = new Nodo(raiz);

        Nodo h1 = new Nodo(raiz, raiz);
        Nodo h2 = new Nodo(raiz, raiz);

        h1.getTorre2().add(h1.getTorre1().remove(h1.getTorre1().size() - 1));
        h2.getTorre3().add(h2.getTorre1().remove(h2.getTorre1().size() - 1));

        Nodo h3 = new Nodo(h1, h1);
        h3.getTorre3().add(h3.getTorre1().remove(h3.getTorre1().size() - 1));

        Nodo h4 = new Nodo(h3, h3);
        Nodo h5 = new Nodo(h3, h3);
        h4.getTorre3().add(h4.getTorre2().remove(h4.getTorre2().size() - 1));
        h5.getTorre1().add(h5.getTorre2().remove(h5.getTorre2().size() - 1));

        Nodo h6 = new Nodo(h4, h4);
        h6.getTorre2().add(h6.getTorre1().remove(h6.getTorre1().size() - 1));

        Nodo h7 = new Nodo(h6, h6);
        Nodo h8 = new Nodo(h6, h6);
        h7.getTorre1().add(h7.getTorre3().remove(h7.getTorre3().size() - 1));
        h8.getTorre2().add(h8.getTorre3().remove(h8.getTorre3().size() - 1));

        Nodo h9 = new Nodo(h7, h7);
        h9.getTorre2().add(h9.getTorre3().remove(h9.getTorre3().size() - 1));

        Nodo meta = new Nodo(h9, h9);
        meta.getTorre2().add(meta.getTorre1().remove(meta.getTorre1().size() - 1));

        System.out.println("Nodo meta:");
        System.out.println(meta.toString());

        System.out.println("Nodo anterior:");
        System.out.println(meta.getPadre().toString());

        System.out.println("Anterior al anterior");
        System.out.println(meta.getPadre().getPadre().toString());

        System.out.println("Recorrido:");
        imprimeRecorrido(meta);

    }

    public Nodo imprimeRecorrido(Nodo n) {
        if(n.equals(raiz)) {
            System.out.println(n.toString());
            return n;
        }
        System.out.println(n.toString());
        return imprimeRecorrido(n.getPadre());
        
    }


    public static void main(String[] args) {
        new Test();




    }
    
}
