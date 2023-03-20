import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;

public class Defun {
    public static HashMap<String, ArrayList<String>> savedFunctions = new HashMap<String, ArrayList<String>>();
    public static boolean isDefun(String codeLine){
        if(codeLine.contains("defun")){
            return true;
        }
        return false;
    }

    public static boolean isFunction(String codeLine){
        for(String st: savedFunctions.keySet()){
            if(codeLine.contains(st)){
                return true;
            }
        }
        return false;
    }
    public static String whichFunction(String codeLine){
        for(String st: savedFunctions.keySet()){
            if(codeLine.contains(st)){
                return st;
            }
        }
        return null;
    }

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

    public static void runFunctionNP(String codeLine){
        ArrayList<String> exe = savedFunctions.get(whichFunction(codeLine));
        exe.remove(0);
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("----FUNCTION IS RUNNING----");
        Interpreter.mainDecoder(exe);
        System.out.println("----FUNCTION FINISHED RUNNING----");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

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
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("----FUNCTION IS RUNNING----");
        Interpreter.mainDecoder(exe);
        System.out.println("----FUNCTION FINISHED RUNNING----");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }


    public static String insertVar(String code, String var, String value){
        String codeReplaced = "";
        if(code.contains(" "+var+" ")){
            String[] tem = code.split(" "+var+" ");
            for(String st: tem){
                codeReplaced+=(" "+value+" ");
            }
            code = codeReplaced;
        }
        if(code.contains("("+var+" ")){
            String[] tem = code.split("("+var+" ");
            for(String st: tem){
                codeReplaced+=(" "+value+" ");
            }
            code = codeReplaced;
        }
        if(code.contains(" "+var+")")){
            String[] tem = code.split(" "+var+")");
            for(String st: tem){
                codeReplaced+=(" "+value+" ");
            }
            code = codeReplaced;
        }
        return code;
    }
}