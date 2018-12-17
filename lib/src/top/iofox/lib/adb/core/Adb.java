package top.iofox.lib.adb.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Locale;

public class Adb {
    private static boolean debug = false;
    protected static String adbPath = "";
    private Adb() {

    }

    public static void setAdbPath(String adbPath) {
        Adb.adbPath = adbPath;
    }

    /**
     * -a         listen on all network interfaces, not just localhost
     * -d         use USB device (error if multiple devices connected)
     * -e         use TCP/IP device (error if multiple TCP/IP devices available)
     * -s SERIAL  use device with given serial (overrides $ANDROID_SERIAL)
     * -t ID      use device with given transport id
     * -H         name of adb server host [default=localhost]
     * -P         port of adb server [default=5037]
     * -L SOCKET  listen on given socket for adb server [default=tcp:localhost:5037]
     * Adb.target(x).shell().click
     * Adb.target(x).version()
     * Adb.target(x).pm().install();
     */
    public static void setDebug(boolean debug) {
        Adb.debug = debug;
    }

    public static Target getDeault() {
        return new Target();
    }

    public static Target[] getTargets() {
        String result = adb("devices -l");
        String[] split = result.split("\n");
        Target[] targets = new Target[split.length - 1];
        if (split.length > 0) {
            for (int i = 1; i < split.length; i++) {
                Target.Device device = new Target.Device();
                String line = split[i];
                int deviceIdx = line.indexOf("device");
                int offlineIdx = line.indexOf("offline");
                String[] map;
                if (deviceIdx != -1) {
                    map = line.substring(deviceIdx + 7).split(" ");
                    String name = line.substring(0, deviceIdx).trim();
                    device.setSerial(name);
                    device.setStatus("device");
                } else if (offlineIdx != -1) {
                    map = line.substring(offlineIdx + 8).split(" ");
                    String name = line.substring(0, offlineIdx).trim();
                    device.setSerial(name);
                    device.setStatus("offline");
                } else {
                    map = new String[0];
                }

                for (int j = 0; j < map.length; j++) {
                    if (map[j].startsWith("product:")) {
                        device.setProduct(map[j].substring(8));
                    }
                    if (map[j].startsWith("usb:")) {
                        device.setUsb(map[j].substring(4));
                    }
                    if (map[j].startsWith("model:")) {
                        device.setModel(map[j].substring(6));
                    }
                    if (map[j].startsWith("device:")) {
                        device.setDevice(map[j].substring(7));
                    }
                    if (map[j].startsWith("transport_id:")) {
                        device.setTransportId(Integer.valueOf(map[j].substring(13)));
                    }
                }
                targets[i - 1] = new Target(device);
            }
        }
        return targets;
    }

    protected static String adb(String pattern, Object... values) {
        return rawExecute(format(adbPath + " " + pattern, values));
    }

    private static String readResult(InputStream inputStream, InputStream errorStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        inputStream.close();
        if (sb.toString().isEmpty()) {
            while ((line = errorReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            sb.insert(0, "[error]");
        }
        errorStream.close();
        return sb.toString();
    }

    protected static String rawExecute(Base.Command command) {
        return rawExecute(command.string());
    }

    protected static String rawExecute(String command) {
        if (debug) {
            System.out.println("[" + Calendar.getInstance().getTime().toString() + "]" + command);
        }

        String output;
        try {
            Process p = Runtime.getRuntime().exec(command);
            output = readResult(p.getInputStream(), p.getErrorStream());
        } catch (IOException e) {
            e.printStackTrace();
            output = "[error]" + e.getMessage();
        }
        if (output.startsWith("[error]")) {
            if (debug) {
                System.err.println(output.replace("[error]", "[" + Calendar.getInstance().getTime().toString() + "]"));
            }
            output = "";
        } else {
            if (debug) {
                System.out.println(output);
            }
        }
        return output;
    }

    protected static String format(String pattern, Object... values) {
        return String.format(Locale.US, pattern, values);
    }

}

