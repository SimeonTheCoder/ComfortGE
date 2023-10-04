package parser;

import sensors.SensorData;

public class RuntimeExecutor {
    public static int[] ints;
    public static float[] floats;
    public static String[] strings;
    public static boolean[] bools;

    public static void init() {
        ints = new int[255];
        floats = new float[255];
        strings = new String[255];
        bools = new boolean[255];
    }

    public static void execute(float[][] parsed) {
        for(int i = 0; i < parsed.length; i++) {
            switch((int) parsed[i][0]) {
                case 1: {
                    //DEFINE
                    if(parsed[i][4] == 0) {
                        //float
                        if(parsed[i][3] == 0) {
                            floats[(int) parsed[i][1]] = floats[(int) parsed[i][2]];
                        }else{
                            floats[(int) parsed[i][1]] = parsed[i][2];
                        }
                    }else if(parsed[i][4] == 1) {
                        //int
                        if(parsed[i][3] == 0) {
                            ints[(int) parsed[i][1]] = ints[(int) parsed[i][2]];
                        }else{
                            ints[(int) parsed[i][1]] = (int) parsed[i][2];
                        }
                    }else if(parsed[i][4] == 2) {
                        //bool
                        if(parsed[i][3] == 0) {
                            bools[(int) parsed[i][1]] = bools[(int) parsed[i][2]];
                        }else if(parsed[i][5] == 1) {
                            bools[(int) parsed[i][1]] = SensorData.keyStatus[(int) parsed[i][2]];
                        }else{
                            bools[(int) parsed[i][1]] = (parsed[i][2] == 1);
                        }
                    }

                    break;
                }

                case 2: {
                    boolean boolVal;

                    if(parsed[i][8] == 0) {
                        boolVal = bools[(int) parsed[i][1]];
                    }else{
                        boolVal = SensorData.keyStatus[(int) parsed[i][1]];
                    }

                    //IF
                    if(parsed[i][7] == 0) {
                        //float
                        float valA, valB;

                        if(parsed[i][5] == 0) {
                            valA = floats[(int) parsed[i][3]];
                        }else{
                            valA = parsed[i][3];
                        }

                        if(parsed[i][6] == 0) {
                            valB = floats[(int) parsed[i][4]];
                        }else{
                            valB = parsed[i][4];
                        }

                        if(boolVal) {
                            floats[(int) parsed[i][2]] = valA;
                        }else{
                            floats[(int) parsed[i][2]] = valB;
                        }
                    }else if(parsed[i][7] == 1) {
                        //int
                        int valA, valB;

                        if(parsed[i][5] == 0) {
                            valA = ints[(int) parsed[i][3]];
                        }else{
                            valA = (int) parsed[i][3];
                        }

                        if(parsed[i][6] == 0) {
                            valB = ints[(int) parsed[i][4]];
                        }else{
                            valB = (int) parsed[i][4];
                        }

                        if(boolVal) {
                            ints[(int) parsed[i][2]] = valA;
                        }else{
                            ints[(int) parsed[i][2]] = valB;
                        }
                    }
                }
            }

            //MATH
            if(parsed[i][0] == 0 && parsed[i][7] == 0) {
                int storeVar = (int) parsed[i][1];

                float valA, valB;

                if(parsed[i][5] == 1) {
                    valA = parsed[i][2];
                }else{
                    valA = floats[(int) parsed[i][2]];
                }

                if(parsed[i][6] == 1) {
                    valB = parsed[i][3];
                }else{
                    valB = floats[(int) parsed[i][3]];
                }

                float result = 0;

                switch((int) parsed[i][4]) {
                    case 0:
                        result = valA + valB;
                        break;
                    case 1:
                        result = valA - valB;
                        break;
                    case 2:
                        result = valA * valB;
                        break;
                    case 3:
                        result = valA / valB;
                        break;
                }

                floats[storeVar] = result;
            }
        }
    }
}
