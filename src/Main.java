import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> codigazo = Reader.Read(); //Array structure to save all code lines
        Interpreter.mainDecoder(codigazo); // Interpreter will receive all code
    }
}
