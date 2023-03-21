import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    /**
     * Lee el archivo txt que contiene el codigo.
     * @return
     */
    public static ArrayList<String> Read(){
        ArrayList<String> codigo = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/lisp.txt"))) {
            String line;
            System.out.println("Codigo original:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                codigo.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("leido");
        System.out.println("");
        return codigo;
    }
}
