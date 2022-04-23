package codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) {
        System.out.println("IN");
        int a = Utils.leerInt();
        System.out.println("Ingrese un nombre:");
        String aux = Utils.leerString();
        System.out.println("Hola " + aux + " " + a);
    }
    
}
