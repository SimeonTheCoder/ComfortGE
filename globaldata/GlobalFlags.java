package globaldata;

public class GlobalFlags {
    public static boolean DEBUG = false;
    public static boolean RUNNING = false;

    public static void setDEBUG(boolean DEBUG) {
        GlobalFlags.DEBUG = DEBUG;
    }

    public static void setRUNNING(boolean RUNNING) {
        GlobalFlags.RUNNING = RUNNING;
    }
}
