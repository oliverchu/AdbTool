package top.iofox.lib.adb.core;

public class Target extends Base {

    private Device device;

    public Target(Device device) {
        super(Adb.adbPath + " -s " + device.getSerial());
        this.device = device;
    }

    /**
     * default
     */
    public Target() {
        super(Adb.adbPath);
        device = new Device();
    }

    public Shell shell() {
        return new Shell(this);
    }

    /**
     * adb push [src] [dst]
     *
     * @param src
     * @param dst
     */
    public void push(String src, String dst) {
        Adb.rawExecute(newCommand().append("push").append(src).append(dst));
    }

    /**
     * adb pull [dst] [src]
     *
     * @param dst
     * @param src
     */
    public void pull(String dst, String src) {
        Adb.rawExecute(newCommand().append("push").append(dst).append(src));
    }

    public void sync() {

    }

    /**
     * adb exec-out screencap -p > sc.png
     *
     * @param path 本地路径,png结尾
     */
    public void screenCapture(String path) {
        // TODO: 18-12-16 HAS ERROR
        Adb.rawExecute(newCommand().append("exec-out screencap -p >").append(path));
    }

    /**
     * 重启手机
     */
    public void reboot() {
        Adb.rawExecute(newCommand().append("reboot"));
    }

    /**
     * 进入Recovery模式
     */
    public void recovery() {
        Adb.rawExecute(newCommand().append("reboot recovery"));
    }

    /**
     * 进入Fastboot模式
     */
    public void fastboot() {
        Adb.rawExecute(newCommand().append("reboot bootloader"));
    }

    public Device getDevice() {
        return device;
    }



    public void setDevice(Device device) {
        this.device = device;
    }



    @Override
    public String onCreateCommand() {
        return "";
    }

    public static class Device {

        private String serial;
        private String usb;
        private String product;
        private String model;
        private String device;
        private int transportId;
        private String status;

        public String getSerial() {
            return serial;
        }

        protected void setSerial(String serial) {
            this.serial = serial;
        }

        public String getUsb() {
            return usb;
        }

        protected void setUsb(String usb) {
            this.usb = usb;
        }

        public String getProduct() {
            return product;
        }

        protected void setProduct(String product) {
            this.product = product;
        }

        public String getModel() {
            return model;
        }

        protected void setModel(String model) {
            this.model = model;
        }

        public String getDevice() {
            return device;
        }

        protected void setDevice(String device) {
            this.device = device;
        }

        public int getTransportId() {
            return transportId;
        }

        protected void setTransportId(int transportId) {
            this.transportId = transportId;
        }

        public String getStatus() {
            return status;
        }

        protected void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Target{" +
                    ", serial='" + serial + '\'' +
                    ", usb='" + usb + '\'' +
                    ", product='" + product + '\'' +
                    ", model='" + model + '\'' +
                    ", device='" + device + '\'' +
                    ", transportId=" + transportId +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

}
