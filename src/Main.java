import java.util.Scanner;
public class Main{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la expresión prefix para evaluar: ");
        String prefixExpression = scanner.nextLine();

        int result = Aritmetica.evaluatePrefix(prefixExpression);
        System.out.println("El resultado de la evaluación es: " + result);

        scanner.close();
    }
}
