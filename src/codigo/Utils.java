package codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    private Utils() {throw new IllegalStateException("Clase de utilerías.");}

    public static int leerInt() {
        int aux = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
			aux = Integer.parseInt(br.readLine());
            br.close();
		} catch (NumberFormatException | IOException e) {
			System.out.println("Dato inválido.");
            System.out.println("Ingrese un dato válido.");
            aux = leerInt();
		}
        return aux;
    }

    public static String leerString() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String aux = "";
        try {
            aux = br.readLine();
            br.close();
        } catch(IOException e){
            System.out.println("Dato inválido.");
            aux = leerString();
        }
        return aux;
    }
    
}
