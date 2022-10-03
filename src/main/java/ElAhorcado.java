import java.util.ArrayList;
import java.util.Scanner;

public class ElAhorcado {
    public static void main(String[] args){
        llamarFunciones();
    }
    public static void llamarFunciones(){
        String [] palabras = crearPalabras();
        String palabraElegida = elegirPalabra(palabras);
        System.out.println(palabraElegida);
        char [] palabraSeparada = separarPalabras(palabraElegida);
        char [] palabraGuiones = transformarPalabraAGuiones(palabraSeparada);
        juegoTerminado(palabraGuiones,palabraSeparada,palabraElegida);
    }
    public static String[] crearPalabras(){
        String [] palabras = {"espina","juvenil","lechuga","postura","vacuno","queso","social","ketchup","nervio","virus"};
        return palabras;
    }
    public static String elegirPalabra(String[] palabras){
        String palabraElegida = palabras[(int)(Math.random()*(palabras.length))];
        return palabraElegida;
    }
    public static char[] separarPalabras(String palabraElegida){
        char [] palabraSeparada = palabraElegida.toCharArray();
        return palabraSeparada;
    }
    public static char [] transformarPalabraAGuiones(char[] palabraSeparada){
        char [] palabraGuiones = new char[palabraSeparada.length];
        for (int i = 0; i < palabraGuiones.length; i++) {
            palabraGuiones[i] = '_';
        }
        return palabraGuiones;
    }
    public static String pedirLetra(){
        String letra = "";

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Por favor ingrese una letra.");
            letra = scanner.nextLine();
        }while (!esLetra(letra));

        return letra;
    }

    public static boolean esLetra(String letra) {
        boolean validador = false;
        while (!validador) {
            validador = letra.matches("[a-zA-Z]");
            if (!validador) {
                return false;
            }
        }
        return true;
    }
    public static boolean letraAcertada(char letra, char[] palabraSeparada){
        boolean AlgunaLetraAcertada = false;
        for (int i = 0; i < palabraSeparada.length; i++) {
            if(palabraSeparada[i] == letra){
                AlgunaLetraAcertada = true;
            }
        }
        return AlgunaLetraAcertada;
    }
    public static boolean hayGuiones(char [] array){
        for(char l : array){
            if(l == '_'){
                return true;
            }
        }
        return false;
    }
    public static void juegoTerminado(char [] palabraGuiones, char[] palabraSeparada, String palabraElegida){
        boolean juegoGanado = false;
        int aciertos = 0;
        int intentos = 10;
        do{
            System.out.println(palabraGuiones);
            char letra = pedirLetra().charAt(0);
            boolean algunaLetraAcertada = letraAcertada(letra,palabraSeparada);
            if(!algunaLetraAcertada){
                System.out.println("No has acertado ninguna letra \n");
                intentos--;
                if(intentos == 0){
                    System.out.println("No te quedan intentos");
                    break;
                }
            }
            else{
                for (int i = 0; i < palabraGuiones.length; i++) {
                    if(letra == palabraSeparada[i]){
                        palabraGuiones[i] = letra;
                    }
                }
                aciertos++;
                System.out.println("Tu letra coincide con alguna de la palabra secreta \n");
                if (aciertos == palabraElegida.length()) {
                    juegoGanado = !hayGuiones(palabraGuiones);
                    System.out.println("Felicidades, has ganado, la palabra era: "+palabraElegida);
                }
            }
        }while (!juegoGanado);
    }
}