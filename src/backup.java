//Cond line detection
if(codeLine.contains("cond")){ 
    System.out.println("COND DETECTED");
    ArrayList<String> condLines = new ArrayList<String>();
    for(int j = i + 1; j < fullCode.size(); j++){
        String currentLine = fullCode.get(j);
        if(currentLine.contains("cond")){
            System.out.println("ERROR: NESTED COND CLAUSES ARE NOT ALLOWED");
            break;
        } else if(!currentLine.trim().isEmpty()){
            condLines.add(currentLine);
        }
    }
    for(String clause : condLines){
        String[] clauseSplit = clause.split("=>");
        if(clauseSplit.length == 2){
            if(Predicates.evaluate(clauseSplit[0].trim())){
                System.out.println(Aritmetica.evaluatePrefix(clauseSplit[1].trim()));
                break;
            }
        } else{
            System.out.println("ERROR: INVALID COND CLAUSE");
        }
    }
}