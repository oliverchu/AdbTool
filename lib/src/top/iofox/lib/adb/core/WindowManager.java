package top.iofox.lib.adb.core;

/**
 * size [reset|WxH|WdpxHdp]
 * Return or override display size.
 * width and height in pixels unless suffixed with 'dp'.
 * density [reset|DENSITY]
 * Return or override display density.
 * overscan [reset|LEFT,TOP,RIGHT,BOTTOM]
 * Set overscan area for display.
 * scaling [off|auto]
 * Set display scaling mode.
 * dismiss-keyguard
 * Dismiss the keyguard, prompting user for auth if necessary.
 */
public class WindowManager extends Base {
    public WindowManager(Base base) {
        super(base);
    }

    @Override
    public String onCreateCommand() {
        return "wm";
    }

    public String getSize() {
        return Adb.rawExecute(newCommand().append("size"));
    }

    public String getDensity() {
        return Adb.rawExecute(newCommand().append("density"));
    }

    /**
     * 设置屏幕分辨率（px）
     *
     * @param width  宽度(px)
     * @param height 高度(px)
     */
    public void setScreenSize(int width, int height) {
        Adb.rawExecute(newCommand().append("size").append(width).append(height));
    }

    /**
     * 恢复默认屏幕分辨率
     */
    public void resetScreenSize() {
        Adb.rawExecute(newCommand().append("size reset"));
    }

    public void setScreenDensity(int density) {
        Adb.rawExecute(newCommand().append("density").append(density));
    }

    public void resetScreenDensity() {
        Adb.rawExecute(newCommand().append("density reset"));
    }

    /**
     * 设置留白区域
     *
     * @param left   留白左边
     * @param top    留白顶部
     * @param right  留白右边
     * @param bottom 留白下边
     */
    public void setScreenOverscan(int left, int top, int right, int bottom) {
        Adb.rawExecute(newCommand().append("overscan").append(left).append(",").append(top).append(",").append(right).append(",").append(bottom));
    }

    /**
     * 重设留白区域
     */
    public void resetScreenOverscan() {
        Adb.rawExecute(newCommand().append("density reset"));
    }

}
