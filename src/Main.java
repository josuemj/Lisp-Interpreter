import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        ArrayList<String> codigo = Reader.Read(); //Array structure to save all code lines
        Interpreter.mainDecoder(codigo); // Interpreter will receive all code
    }
}