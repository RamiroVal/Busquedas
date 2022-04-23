package codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    private Utils() {throw new IllegalStateException("Clase de utilerías.");}

    public static int leerInt() {
		try {
			return Integer.parseInt(leerString());
		}catch(NumberFormatException e) {
			System.out.print("Dato inválido: ");
			return leerInt();
		}
	}

	public static String leerString() {
		String sdato = "";
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader nose = new BufferedReader(isr);
			sdato =nose.readLine() ;
		}catch(IOException e) {
			System.err.println("Error: "+ e.getMessage());
		}
		return sdato;
	}
    
}
