package codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

public class Test {
    Nodo raiz;
    List<Nodo> ndFirstBest = new ArrayList<>();
    int valorRaiz = 0;


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
        Nodo meta = new Nodo(torre2, torre1, torre3);

        Nodo h1 = new Nodo(raiz, raiz);
        Nodo h2 = new Nodo(raiz, raiz);

        h1.getTorre2().add(h1.getTorre1().remove(h1.getTorre1().size() - 1));
        h2.getTorre3().add(h2.getTorre1().remove(h2.getTorre1().size() - 1));

        int valh1 = calculaValorAsterisco(h1, meta);
        h1.setValor(valh1);
        int valh2 = calculaValorAsterisco(h2, meta);
        h2.setValor(valh2);

        Nodo h3 = new Nodo(h1, h1);
        h3.getTorre3().add(h3.getTorre1().remove(h3.getTorre1().size() - 1));
        int valh3 = calculaValorAsterisco(h3, meta);
        h3.setValor(valh3);

        Nodo h4 = new Nodo(h3, h3);
        Nodo h5 = new Nodo(h3, h3);
        h4.getTorre3().add(h4.getTorre2().remove(h4.getTorre2().size() - 1));
        h5.getTorre1().add(h5.getTorre2().remove(h5.getTorre2().size() - 1));

        int valh4 = calculaValorAsterisco(h4, meta);
        h4.setValor(valh4);
        int valh5 = calculaValorAsterisco(h5, meta);
        h5.setValor(valh5);

        Nodo h6 = new Nodo(h4, h4);
        h6.getTorre2().add(h6.getTorre1().remove(h6.getTorre1().size() - 1));

        int valh6 = calculaValorAsterisco(h6, meta);
        h6.setValor(valh6);

        Nodo h7 = new Nodo(h6, h6);
        Nodo h8 = new Nodo(h6, h6);
        h7.getTorre1().add(h7.getTorre3().remove(h7.getTorre3().size() - 1));
        h8.getTorre2().add(h8.getTorre3().remove(h8.getTorre3().size() - 1));

        int valh7 = calculaValorAsterisco(h7, meta);
        h7.setValor(valh7);
        int valh8 = calculaValorAsterisco(h8, meta);
        h8.setValor(valh8);

        Nodo h9 = new Nodo(h7, h7);
        h9.getTorre2().add(h9.getTorre3().remove(h9.getTorre3().size() - 1));

        int valh9 = calculaValorAsterisco(h9, meta);
        h9.setValor(valh9);

        Nodo nmeta = new Nodo(h9, h9);
        nmeta.getTorre2().add(nmeta.getTorre1().remove(nmeta.getTorre1().size() - 1));

        //System.out.println("Nodo meta:");
        //System.out.println(nmeta.toString());

        //System.out.println("Nodo anterior:");
        //System.out.println(nmeta.getPadre().toString());

        //System.out.println("Anterior al anterior");
        //System.out.println(nmeta.getPadre().getPadre().getPadre().toString());

        //System.out.println("Valores en torre 2");
        //int[] valores = nmeta.getPadre().valoresTorre2();
        ndFirstBest.add(h1);
        ndFirstBest.add(h9);
        ndFirstBest.add(h2);
        ndFirstBest.add(h3);
        ndFirstBest.add(h4);
        ndFirstBest.add(h5);
        ndFirstBest.add(h6);
        ndFirstBest.add(h7);
        ndFirstBest.add(h8);
        
        System.out.println("Antes de ordenar:");
        ndFirstBest.forEach((t) -> System.out.println(t.toString() + " valor: " + t.getValor()));

        ordenarMenorMayor();
        
        System.out.println("\nDespues de ordenar:");
        ndFirstBest.forEach((t) -> System.out.println(t.toString() + " valor: " + t.getValor()));

        Nodo jeje = ndFirstBest.remove(0);
        System.out.println(jeje.toString());
        System.out.println("\n" + calculaValorAsterisco(jeje, meta));

        /*jeje = ndFirstBest.remove(0);
        System.out.println(jeje.toString());
        System.out.println(calculaValorAsterisco(jeje, meta));

        jeje = ndFirstBest.remove(0);
        System.out.println(jeje.toString());
        System.out.println(calculaValorAsterisco(jeje, meta));

        jeje = ndFirstBest.remove(0);
        System.out.println(jeje.toString());
        System.out.println(calculaValorAsterisco(jeje, meta));*/
    }

    public void ordenarMenorMayor() {
        Collections.sort(ndFirstBest, new Comparator<Nodo>() {
            @Override
            public int compare(Nodo n1, Nodo n2) {
                return new Integer(n1.getValor()).compareTo(new Integer(n2.getValor()));
            }
        });
    }

    public int calculaValorRaiz(Nodo n) {
        if(n.equals(raiz)) {
            return 0;
        }else {
            return 1 + calculaValorRaiz(n.getPadre());
        }
    }

    public int calculaValorAsterisco(Nodo n, Nodo meta) {
        return calculaValorRaiz(n) - calculaValor(n, meta);
    }

    public int calculaValor(Nodo n, Nodo ndMeta) {
        int[] vNodo1 = n.valoresTorre2();
        int[] vNodoM = ndMeta.valoresTorre2();
        int aux = 0;
        for(int i = 0; i < vNodo1.length; i++){
            if(vNodo1[i] == vNodoM[i]) {
                aux++;
            }
        }
        return aux;
    }

    public void ordenarArray() {
        Collections.sort(ndFirstBest, new Comparator<Nodo>() {
            @Override
            public int compare(Nodo n1, Nodo n2) {
                return new Integer(n2.getValor()).compareTo(new Integer(n1.getValor()));
            }
        });
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
