import java.util.ArrayList;
import java.util.HashMap;

public class Defun {

    public static HashMap<String, ArrayList<String>> savedFunctions = new HashMap<String, ArrayList<String>>();

    /**
     * Revisa si la linea de código tiene el string "defun"
     * @param codeLine
     * @return true si fue encontrado
     */
    public static boolean isDefun(String codeLine){
        if(codeLine.contains("defun")){
            return true;
        }
        return false;
    }

    /**
     * Revisa si el nombre de alguna funcion guardada se encuentre dentro de la linea de codigo
     * @param codeLine
     * @return true si fue encontrado
     */
    public static boolean isFunction(String codeLine){
        for(String st: savedFunctions.keySet()){
            if(codeLine.contains(st)){
                return true;
            }
        }
        return false;
    }

    /**
     * Revisa qué función se encuentra dentro del código buscando si contiene el nombre
     * @param codeLine
     * @return String, nombre de la funcion
     */
    public static String whichFunction(String codeLine){
        for(String st: savedFunctions.keySet()){
            if(codeLine.contains(st)){
                return st;
            }
        }
        return null;
    }

    /**
     * De la linea de código ingresada, obtiene el nombre de la función y la guarda como su key en
     * el hasmap SavedFunctions. Luego, ingresa los parametros separados en espacios en la primera
     * posición del arraylist que es su contenido. En las siguientes posiciones del arraylist,
     * ingresa las lineas del código, eliminando los espacios y el paréntesis final.
     * @param codeLines
     */
    public static void saveDefun(ArrayList<String> codeLines){
        String[] argument = codeLines.get(0).split(" ");
        ArrayList<String> executable = new ArrayList<String>();
        String parameters = "";
        for (int i = 2; i< argument.length; i++){
            if(i==2){
                parameters = parameters+(argument[i].substring(1, argument[i].length())+" ");
            } else if(i==argument.length-1){
                parameters = parameters+(argument[i].substring(0, argument[i].length()-1));
            } else{
                parameters = parameters+ argument[i]+" ";
            }
        }
        System.out.println("Parameters: "+parameters);
        executable.add(parameters);
        for (int i = 1; i<codeLines.size(); i++){
            if(i==codeLines.size()-1){
                String tem = codeLines.get(i).substring(1, codeLines.get(i).length() - 1);
                codeLines.set(i, tem);
            } else{
                String tem = codeLines.get(i).substring(1, codeLines.get(i).length());
                codeLines.set(i, tem);
            }
            executable.add(codeLines.get(i));
        }
        savedFunctions.put(argument[1],executable);
    }

    /**
     * Si la funcion no tiene parámetros, solo corre las lienas de código
     * llamando recursivamente a intreprete
     * @param codeLine
     */
    public static void runFunctionNP(String codeLine){
        ArrayList<String> exe = savedFunctions.get(whichFunction(codeLine));
        exe.remove(0);
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("----FUNCTION IS RUNNING----");
        Interpreter.mainDecoder(exe);
        System.out.println("----FUNCTION FINISHED RUNNING----");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    /**
     * Si la funcion tiene parámetros, asococia cada valor al parámetro que planea
     * sustituir dentro de una tabla, luego llama a la funcion insertVar para que el código
     * sea ejecutado
     * @param codeLine
     */
    public static void runFunction(String codeLine){
        ArrayList<String> exe = savedFunctions.get(whichFunction(codeLine));
        int parameterNum = exe.get(0).split(" ").length;
        System.out.println(parameterNum);
        String[][] parameters = new String[exe.get(0).length()][2];
        String[] instance = codeLine.split(" ");
        String[] parameterNames = exe.get(0).split(" ");
        for (int i = 1; i<instance.length; i++){
            if (i==instance.length-1){
                instance[i] = instance[i].substring(0, instance[i].length() - 1);
            }
            parameters[i-1][0] = parameterNames[i-1];
            parameters[i-1][1] = instance[i];
        }
        for( int i = 0; i<parameterNum; i++){
            System.out.println(parameters[i][0] + " "+ parameters[i][1]);
        }
        exe.remove(0);
        for (int i = 0; i<exe.size(); i++){
            for(int j = 0; j<parameterNum; j++){
                if(exe.get(i).contains(parameters[j][0])){
                    exe.set(i,insertVar(exe.get(i), parameters[j][0],parameters[j][1]));
                }
            }
        }
        for(String st: exe){
            System.out.printf(st);
        }
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("----FUNCTION IS RUNNING----");
        Interpreter.mainDecoder(exe);
        System.out.println("----FUNCTION FINISHED RUNNING----");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    /**
     * con la linea de código, busca todos las variables que aparezcan
     * y las sustituye por sus valores adecuados. Luego, regresa esta linea de código
     *
     * @param code
     * @param var
     * @param value
     * @return String, código con variables sustituidas por valores.
     */
    public static String insertVar(String code, String var, String value){
        String codeReplaced = "";
        String[] tem;
        if(code.contains(" "+var+" ")){
            tem = code.split(" "+var+" ");
            for(int i = 0; i<tem.length;i++){
                if(i!=tem.length-1){
                    codeReplaced+=(tem[i]+" "+value+" ");
                } else{
                    codeReplaced+=tem[i];
                }
            }
            code = codeReplaced;
        }

        if(code.contains(" "+var)){
            tem = code.split(" "+var);
            codeReplaced = "";
            for(int i = 0; i<tem.length;i++){
                if(i!=tem.length-1){
                    codeReplaced+=(tem[i]+" "+value);
                } else{
                    codeReplaced+=tem[i];
                }
            }
            code = codeReplaced;
        }

        if(code.contains(var+" ")){
            tem = code.split(var+" ");
            codeReplaced = "";
            for(int i = 0; i<tem.length;i++){
                if(i!=tem.length-1){
                    codeReplaced+=(tem[i]+value+" ");
                } else{
                    codeReplaced+=tem[i];
                }
            }
            code = codeReplaced;
        }
        return code;
    }
}