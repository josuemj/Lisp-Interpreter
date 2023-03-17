public class setq {
    
    /**
     * Método que verifica si la línea de código es un comando setq
     * @param codeLine
     * @return
     */
    public static boolean isSetq(String codeLine){
        return codeLine.startsWith("(setq ");
    }
    
    /**
     * Método que ejecuta el comando setq
     * @param codeLine
     */
    public static void runSetq(String codeLine){
        String[] splitLine = codeLine.substring(6).split(" ");
        String variable = splitLine[0];
        String value = splitLine[1];
        // Aquí se debe asignar la variable con su valor correspondiente
        System.out.println("Variable " + variable + " asignada con el valor " + value);
    }

    
}
