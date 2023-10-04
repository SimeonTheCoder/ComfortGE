package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
    public static List<String> intNames;
    public static List<String> floatNames;
    public static List<String> stringNames;
    public static List<String> boolNames;

    public static void init() {
        intNames = new ArrayList<>();
        floatNames = new ArrayList<>();
        stringNames = new ArrayList<>();
        boolNames = new ArrayList<>();

        floatNames.add("posx");
        floatNames.add("posy");
        floatNames.add("rot");
    }

    public static float[][] parse(String[] input) {
        List<float[]> parsedList = new ArrayList<>();

        for(int i = 0; i < input.length; i++) {
            float[] currParse = new float[10];

            String line = input[i].trim();
            line = line.replace("\t", "");

            String[] content = line.split(" ");

            switch (content[0]) {
                case "float" : {
                    floatNames.add(content[1]);

                    currParse[0] = 1;
                    currParse[1] = floatNames.indexOf(content[1]);

                    boolean flag = false;

                    if(floatNames.contains(content[3])) {
                        currParse[2] = floatNames.indexOf(content[3]);
                    }else{
                        flag = true;
                        currParse[2] = Float.parseFloat(content[3]);
                    }

                    currParse[3] = flag ? 1 : 0;
                    currParse[4] = 0; //float

                    parsedList.add(currParse);

                    break;
                }

                case "int" : {
                    intNames.add(content[1]);

                    currParse[0] = 1;
                    currParse[1] = intNames.indexOf(content[1]);

                    boolean flag = false;

                    if(intNames.contains(content[3])) {
                        currParse[2] = intNames.indexOf(content[3]);
                    }else{
                        flag = true;
                        currParse[2] = Integer.parseInt(content[3]);
                    }

                    currParse[3] = flag ? 1 : 0;
                    currParse[4] = 1; //int

                    parsedList.add(currParse);

                    break;
                }

                case "bool" : {
                    boolNames.add(content[1]);

                    currParse[0] = 1;
                    currParse[1] = boolNames.indexOf(content[1]);

                    boolean flag = false;

                    if(boolNames.contains(content[3])) {
                        currParse[2] = boolNames.indexOf(content[3]);
                    }else if(content[3].equals("inp")) {
                        currParse[2] = content[4].charAt(0);
                        currParse[5] = 1;
                        flag = true;
                    }else{
                        flag = true;
                        currParse[2] = content[3].equals("true") ? 1 : 0;
                    }

                    currParse[3] = flag ? 1 : 0;
                    currParse[4] = 2; //bool

                    parsedList.add(currParse);

                    break;
                }

                case "if" : {
                    if(floatNames.contains(content[3])) {
                        currParse[0] = 2;

                        int offset = 0;

                        if(boolNames.contains(content[1])) {
                            currParse[1] = boolNames.indexOf(content[1]);
                        } else if (content[1].equals("inp")){
                            offset = 1;
                            currParse[1] = content[2].charAt(0);
                        }

                        int var = floatNames.indexOf(content[3 + offset]);

                        currParse[2 + offset] = var;

                        float valA, valB;
                        boolean flagA = false, flagB = false;

                        if(floatNames.contains(content[5 + offset])) {
                            valA = Integer.parseInt(content[5 + offset]);
                        }else{
                            flagA = true;
                            valA = Float.parseFloat(content[5 + offset]);
                        }
                        currParse[3 + offset] = valA;

                        if(floatNames.contains(content[7 + offset])) {
                            valB = Integer.parseInt(content[7 + offset]);
                        }else{
                            flagB = true;
                            valB = Float.parseFloat(content[7 + offset]);
                        }
                        currParse[4 + offset] = valB;

                        currParse[5 + offset] = flagA ? 1 : 0;
                        currParse[6 + offset] = flagB ? 1 : 0;

                        currParse[7 + offset] = 0; //float
                        currParse[8] = offset;

                        parsedList.add(currParse);
                    }else if(intNames.contains(content[3])) {
                        currParse[0] = 2;
                        currParse[1] = boolNames.indexOf(content[1]);

                        int var = intNames.indexOf(content[3]);

                        currParse[2] = var;

                        float valA, valB;
                        boolean flagA = false, flagB = false;

                        if(intNames.contains(content[5])) {
                            valA = Integer.parseInt(content[5]);
                        }else{
                            flagA = true;
                            valA = Integer.parseInt(content[5]);
                        }
                        currParse[3] = valA;

                        if(intNames.contains(content[7])) {
                            valB = Integer.parseInt(content[7]);
                        }else{
                            flagB = true;
                            valB = Integer.parseInt(content[7]);
                        }
                        currParse[4] = valB;

                        currParse[5] = flagA ? 1 : 0;
                        currParse[6] = flagB ? 1 : 0;

                        currParse[7] = 1; //int
                    }

                    break;
                }
            }

            if(floatNames.contains(content[0])) {
                currParse[0] = 0;

                boolean flagA = false;
                boolean flagB = false;

                int varA = floatNames.indexOf(content[0]);
                currParse[1] = varA;

                int varB, varC;

                if(floatNames.contains(content[2])) {
                    varB = floatNames.indexOf(content[2]);
                }else{
                    flagA = true;
                    varB = Integer.parseInt(content[2]);
                }
                currParse[2] = varB;

                if(floatNames.contains(content[4])) {
                    varC = floatNames.indexOf(content[4]);
                }else{
                    flagB = true;
                    varC = Integer.parseInt(content[4]);
                }
                currParse[3] = varC;

                int operation = -1;

                switch(content[3]) {
                    case "+":
                        operation = 0;
                        break;
                    case "-":
                        operation = 1;
                        break;
                    case "*":
                        operation = 2;
                        break;
                    case "/":
                        operation = 3;
                        break;
                }

                currParse[4] = operation;

                currParse[5] = flagA ? 1 : 0;
                currParse[6] = flagB ? 1 : 0;

                currParse[7] = 0;

                parsedList.add(currParse);
            }
        }

        float[][] parsed = new float[parsedList.size()][10];

        for(int i = 0; i < parsedList.size(); i ++) {
            parsed[i] = parsedList.get(i);
        }

        return parsed;
    }
}
