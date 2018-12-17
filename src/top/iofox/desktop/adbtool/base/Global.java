package top.iofox.desktop.adbtool.base;

public class Global {
    public static final String PROP_ADB_PATH = "ADB_PATH";

    public static String getPropAdbPath(){
        return System.getProperty(PROP_ADB_PATH);
    }

    public static void setPropAdbPath(String propAdbPath) {
        System.setProperty(PROP_ADB_PATH,propAdbPath);
    }
}
