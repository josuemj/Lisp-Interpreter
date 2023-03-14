/**
 * All func related with error controls.
 */

public class Controller {

    /**
     * Method returns the prefix expression together
     * @param codeLine
     * @return
     */
    public static String getPrefix(String codeLine){
        //Taking out "()"
        System.out.println("INITIAL LINE "+codeLine);
        String operator = codeLine.substring(1,codeLine.length()-1);
        System.out.println("ALL PREFIX: "+operator);
        return operator;
    }

}
