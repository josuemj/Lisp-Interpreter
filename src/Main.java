import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> codigazo = Reader.Read();

        for (String st: codigazo){
            System.out.println(st);
        }
    }
}