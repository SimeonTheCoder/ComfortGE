package sensors;

public class SensorData {
    public static boolean mouse;
    public static boolean[] keyStatus;
    public static boolean[] keyTyped;

    public static void initSensors() {
        keyStatus = new boolean[256];
        keyTyped = new boolean[256];
    }
}
