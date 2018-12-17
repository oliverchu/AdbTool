package top.iofox.lib.adb.core;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Activity manager (activity) commands:
 * help
 * Print this help text.
 * start-activity [-D] [-N] [-W] [-P <FILE>] [--start-profiler <FILE>]
 * [--sampling INTERVAL] [--streaming] [-R COUNT] [-S]
 * [--track-allocation] [--user <USER_ID> | current] <INTENT>
 * Start an Activity.  Options are:
 * -D: enable debugging
 * -N: enable native debugging
 * -W: wait for launch to complete
 * --start-profiler <FILE>: start profiler and send results to <FILE>
 * --sampling INTERVAL: use sample profiling with INTERVAL microseconds
 * between samples (use with --start-profiler)
 * --streaming: stream the profiling output to the specified file
 * (use with --start-profiler)
 * -P <FILE>: like above, but profiling stops when app goes idle
 * --attach-agent <agent>: attach the given agent before binding
 * --attach-agent-bind <agent>: attach the given agent during binding
 * -R: repeat the activity launch <COUNT> times.  Prior to each repeat,
 * the top activity will be finished.
 * -S: force stop the target app before starting the activity
 * --track-allocation: enable tracking of object allocations
 * --user <USER_ID> | current: Specify which user to run as; if not
 * specified then run as the current user.
 * --windowingMode <WINDOWING_MODE>: The windowing mode to launch the activity into.
 * --activityType <ACTIVITY_TYPE>: The activity type to launch the activity as.
 * start-service [--user <USER_ID> | current] <INTENT>
 * Start a Service.  Options are:
 * --user <USER_ID> | current: Specify which user to run as; if not
 * specified then run as the current user.
 * start-foreground-service [--user <USER_ID> | current] <INTENT>
 * Start a foreground Service.  Options are:
 * --user <USER_ID> | current: Specify which user to run as; if not
 * specified then run as the current user.
 * stop-service [--user <USER_ID> | current] <INTENT>
 * Stop a Service.  Options are:
 * --user <USER_ID> | current: Specify which user to run as; if not
 * specified then run as the current user.
 * broadcast [--user <USER_ID> | all | current] <INTENT>
 * Send a broadcast Intent.  Options are:
 * --user <USER_ID> | all | current: Specify which user to send to; if not
 * specified then send to all users.
 * --receiver-permission <PERMISSION>: Require receiver to hold permission.
 * instrument [-r] [-e <NAME> <VALUE>] [-p <FILE>] [-w]
 * [--user <USER_ID> | current] [--no-hidden-api-checks]
 * [--no-window-animation] [--abi <ABI>] <COMPONENT>
 * Start an Instrumentation.  Typically this target <COMPONENT> is in the
 * form <TEST_PACKAGE>/<RUNNER_CLASS> or only <TEST_PACKAGE> if there
 * is only one instrumentation.  Options are:
 * -r: print raw results (otherwise decode REPORT_KEY_STREAMRESULT).  Use with
 * [-e perf true] to generate raw output for performance measurements.
 * -e <NAME> <VALUE>: set argument <NAME> to <VALUE>.  For test runners a
 * common form is [-e <testrunner_flag> <value>[,<value>...]].
 * -p <FILE>: write profiling data to <FILE>
 * -m: Write output as protobuf to stdout (machine readable)
 * -f <Optional PATH/TO/FILE>: Write output as protobuf to a file (machine
 * readable). If path is not specified, default directory and file name will
 * be used: /sdcard/instrument-logs/log-yyyyMMdd-hhmmss-SSS.instrumentation_data_proto
 * -w: wait for instrumentation to finish before returning.  Required for
 * test runners.
 * --user <USER_ID> | current: Specify user instrumentation runs in;
 * current user if not specified.
 * --no-hidden-api-checks: disable restrictions on use of hidden API.
 * --no-window-animation: turn off window animations while running.
 * --abi <ABI>: Launch the instrumented process with the selected ABI.
 * This assumes that the process supports the selected ABI.
 * trace-ipc [start|stop] [--dump-file <FILE>]
 * Trace IPC transactions.
 * start: start tracing IPC transactions.
 * stop: stop tracing IPC transactions and dump the results to file.
 * --dump-file <FILE>: Specify the file the trace should be dumped to.
 * profile [start|stop] [--user <USER_ID> current] [--sampling INTERVAL]
 * [--streaming] <PROCESS> <FILE>
 * Start and stop profiler on a process.  The given <PROCESS> argument
 * may be either a process name or pid.  Options are:
 * --user <USER_ID> | current: When supplying a process name,
 * specify user of process to profile; uses current user if not specified.
 * --sampling INTERVAL: use sample profiling with INTERVAL microseconds
 * between samples
 * --streaming: stream the profiling output to the specified file
 * dumpheap [--user <USER_ID> current] [-n] [-g] <PROCESS> <FILE>
 * Dump the heap of a process.  The given <PROCESS> argument may
 * be either a process name or pid.  Options are:
 * -n: dump native heap instead of managed heap
 * -g: force GC before dumping the heap
 * --user <USER_ID> | current: When supplying a process name,
 * specify user of process to dump; uses current user if not specified.
 * set-debug-app [-w] [--persistent] <PACKAGE>
 * Set application <PACKAGE> to debug.  Options are:
 * -w: wait for debugger when application starts
 * --persistent: retain this value
 * clear-debug-app
 * Clear the previously set-debug-app.
 * set-watch-heap <PROCESS> <MEM-LIMIT>
 * Start monitoring pss size of <PROCESS>, if it is at or
 * above <HEAP-LIMIT> then a heap dump is collected for the user to report.
 * clear-watch-heap
 * Clear the previously set-watch-heap.
 * bug-report [--progress | --telephony]
 * Request bug report generation; will launch a notification
 * when done to select where it should be delivered. Options are:
 * --progress: will launch a notification right away to show its progress.
 * --telephony: will dump only telephony sections.
 * force-stop [--user <USER_ID> | all | current] <PACKAGE>
 * Completely stop the given application package.
 * crash [--user <USER_ID>] <PACKAGE|PID>
 * Induce a VM crash in the specified package or process
 * kill [--user <USER_ID> | all | current] <PACKAGE>
 * Kill all background processes associated with the given application.
 * kill-all
 * Kill all processes that are safe to kill (cached, etc).
 * make-uid-idle [--user <USER_ID> | all | current] <PACKAGE>
 * If the given application's uid is in the background and waiting to
 * become idle (not allowing background services), do that now.
 * monitor [--gdb <port>]
 * Start monitoring for crashes or ANRs.
 * --gdb: start gdbserv on the given port at crash/ANR
 * watch-uids [--oom <uid>]
 * Start watching for and reporting uid state changes.
 * --oom: specify a uid for which to report detailed change messages.
 * hang [--allow-restart]
 * Hang the system.
 * --allow-restart: allow watchdog to perform normal system restart
 * restart
 * Restart the user-space system.
 * idle-maintenance
 * Perform idle maintenance now.
 * screen-compat [on|off] <PACKAGE>
 * Control screen compatibility mode of <PACKAGE>.
 * package-importance <PACKAGE>
 * Print current importance of <PACKAGE>.
 * to-uri [INTENT]
 * Print the given Intent specification as a URI.
 * to-intent-uri [INTENT]
 * Print the given Intent specification as an intent: URI.
 * to-app-uri [INTENT]
 * Print the given Intent specification as an android-app: URI.
 * switch-user <USER_ID>
 * Switch to put USER_ID in the foreground, starting
 * execution of that user if it is currently stopped.
 * get-current-user
 * Returns id of the current foreground user.
 * start-user <USER_ID>
 * Start USER_ID in background if it is currently stopped;
 * use switch-user if you want to start the user in foreground
 * unlock-user <USER_ID> [TOKEN_HEX]
 * Attempt to unlock the given user using the given authorization token.
 * stop-user [-w] [-f] <USER_ID>
 * Stop execution of USER_ID, not allowing it to run any
 * code until a later explicit start or switch to it.
 * -w: wait for stop-user to complete.
 * -f: force stop even if there are related users that cannot be stopped.
 * is-user-stopped <USER_ID>
 * Returns whether <USER_ID> has been stopped or not.
 * get-started-user-state <USER_ID>
 * Gets the current state of the given started user.
 * track-associations
 * Enable association tracking.
 * untrack-associations
 * Disable and clear association tracking.
 * get-uid-state <UID>
 * Gets the process state of an app given its <UID>.
 * attach-agent <PROCESS> <FILE>
 * Attach an agent to the specified <PROCESS>, which may be either a process name or a PID.
 * get-config [--days N] [--device] [--proto]
 * Retrieve the configuration and any recent configurations of the device.
 * --days: also return last N days of configurations that have been seen.
 * --device: also output global device configuration info.
 * --proto: return result as a proto; does not include --days info.
 * supports-multiwindow
 * Returns true if the device supports multiwindow.
 * supports-split-screen-multi-window
 * Returns true if the device supports split screen multiwindow.
 * suppress-resize-config-changes <true|false>
 * Suppresses configuration changes due to user resizing an activity/task.
 * set-inactive [--user <USER_ID>] <PACKAGE> true|false
 * Sets the inactive state of an app.
 * get-inactive [--user <USER_ID>] <PACKAGE>
 * Returns the inactive state of an app.
 * set-standby-bucket [--user <USER_ID>] <PACKAGE> active|working_set|frequent|rare
 * Puts an app in the standby bucket.
 * get-standby-bucket [--user <USER_ID>] <PACKAGE>
 * Returns the standby bucket of an app.
 * send-trim-memory [--user <USER_ID>] <PROCESS>
 * [HIDDEN|RUNNING_MODERATE|BACKGROUND|RUNNING_LOW|MODERATE|RUNNING_CRITICAL|COMPLETE]
 * Send a memory trim event to a <PROCESS>.  May also supply a raw trim int level.
 * display [COMMAND] [...]: sub-commands for operating on displays.
 * move-stack <STACK_ID> <DISPLAY_ID>
 * Move <STACK_ID> from its current display to <DISPLAY_ID>.
 * stack [COMMAND] [...]: sub-commands for operating on activity stacks.
 * start <DISPLAY_ID> <INTENT>
 * Start a new activity on <DISPLAY_ID> using <INTENT>
 * move-task <TASK_ID> <STACK_ID> [true|false]
 * Move <TASK_ID> from its current stack to the top (true) or
 * bottom (false) of <STACK_ID>.
 * resize <STACK_ID> <LEFT,TOP,RIGHT,BOTTOM>
 * Change <STACK_ID> size and position to <LEFT,TOP,RIGHT,BOTTOM>.
 * resize-animated <STACK_ID> <LEFT,TOP,RIGHT,BOTTOM>
 * Same as resize, but allow animation.
 * resize-docked-stack <LEFT,TOP,RIGHT,BOTTOM> [<TASK_LEFT,TASK_TOP,TASK_RIGHT,TASK_BOTTOM>]
 * Change docked stack to <LEFT,TOP,RIGHT,BOTTOM>
 * and supplying temporary different task bounds indicated by
 * <TASK_LEFT,TOP,RIGHT,BOTTOM>
 * move-top-activity-to-pinned-stack: <STACK_ID> <LEFT,TOP,RIGHT,BOTTOM>
 * Moves the top activity from
 * <STACK_ID> to the pinned stack using <LEFT,TOP,RIGHT,BOTTOM> for the
 * bounds of the pinned stack.
 * positiontask <TASK_ID> <STACK_ID> <POSITION>
 * Place <TASK_ID> in <STACK_ID> at <POSITION>
 * list
 * List all of the activity stacks and their sizes.
 * info <WINDOWING_MODE> <ACTIVITY_TYPE>
 * Display the information about activity stack in <WINDOWING_MODE> and <ACTIVITY_TYPE>.
 * remove <STACK_ID>
 * Remove stack <STACK_ID>.
 * task [COMMAND] [...]: sub-commands for operating on activity tasks.
 * lock <TASK_ID>
 * Bring <TASK_ID> to the front and don't allow other tasks to run.
 * lock stop
 * End the current task lock.
 * resizeable <TASK_ID> [0|1|2|3]
 * Change resizeable mode of <TASK_ID> to one of the following:
 * 0: unresizeable
 * 1: crop_windows
 * 2: resizeable
 * 3: resizeable_and_pipable
 * resize <TASK_ID> <LEFT,TOP,RIGHT,BOTTOM>
 * Makes sure <TASK_ID> is in a stack with the specified bounds.
 * Forces the task to be resizeable and creates a stack if no existing stack
 * has the specified bounds.
 * update-appinfo <USER_ID> <PACKAGE_NAME> [<PACKAGE_NAME>...]
 * Update the ApplicationInfo objects of the listed packages for <USER_ID>
 * without restarting any processes.
 * write
 * Write all pending state to storage.
 *
 * <INTENT> specifications include these flags and arguments:
 * [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>]
 * [-c <CATEGORY> [-c <CATEGORY>] ...]
 * [-n <COMPONENT_NAME>]
 * [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]
 * [--esn <EXTRA_KEY> ...]
 * [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]
 * [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]
 * [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]
 * [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]
 * [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]
 * [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]
 * [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
 * (mutiple extras passed as Integer[])
 * [--eial <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
 * (mutiple extras passed as List<Integer>)
 * [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
 * (mutiple extras passed as Long[])
 * [--elal <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
 * (mutiple extras passed as List<Long>)
 * [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
 * (mutiple extras passed as Float[])
 * [--efal <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
 * (mutiple extras passed as List<Float>)
 * [--esa <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
 * (mutiple extras passed as String[]; to embed a comma into a string,
 * escape it using "\,")
 * [--esal <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
 * (mutiple extras passed as List<String>; to embed a comma into a string,
 * escape it using "\,")
 * [-f <FLAG>]
 * [--grant-read-uri-permission] [--grant-write-uri-permission]
 * [--grant-persistable-uri-permission] [--grant-prefix-uri-permission]
 * [--debug-log-resolution] [--exclude-stopped-packages]
 * [--include-stopped-packages]
 * [--activity-brought-to-front] [--activity-clear-top]
 * [--activity-clear-when-task-reset] [--activity-exclude-from-recents]
 * [--activity-launched-from-history] [--activity-multiple-task]
 * [--activity-no-animation] [--activity-no-history]
 * [--activity-no-user-action] [--activity-previous-is-top]
 * [--activity-reorder-to-front] [--activity-reset-task-if-needed]
 * [--activity-single-top] [--activity-clear-task]
 * [--activity-task-on-home] [--activity-match-external]
 * [--receiver-registered-only] [--receiver-replace-pending]
 * [--receiver-foreground] [--receiver-no-abort]
 * [--receiver-include-background]
 * [--selector]
 * [<URI> | <PACKAGE> | <COMPONENT>]
 */
public class ActivityManager extends Base {

    public ActivityManager(Base base) {
        super(base);
    }

    @Override
    public String onCreateCommand() {
        return "am";
    }

    public static final String TRIM_MEMORY_LEVEL_HIDDEN = "HIDDEN";
    public static final String TRIM_MEMORY_RUNNING_MODERATE = "RUNNING_MODERATE";
    public static final String TRIM_MEMORY_BACKGROUND = "BACKGROUND";
    public static final String TRIM_MEMORY_RUNNING_LOW = "RUNNING_LOW";
    public static final String TRIM_MEMORY_MODERATE = "MODERATE";
    public static final String TRIM_MEMORY_RUNNING_CRITICAL = "RUNNING_CRITICAL";
    public static final String TRIM_MEMORY_COMPLETE = "COMPLETE";

    /**
     * adb shell am start -n com.tencent.mm/.ui.LauncherUI
     * 例如：start("com.tencent.mm",".ui.LauncherUI")； //启动微信
     *
     * @param packageName  包名
     * @param activityPath Activity相对路径
     */
    public void start(String packageName, String activityPath) {
        start(packageName, activityPath, null);
    }

    /**
     * adb shell am start -n com.tencent.mm/.ui.LauncherUI --es "key1" "string"
     *
     * @param packageName  包名
     * @param activityPath Activity相对路径
     * @param intent       参数
     */
    public void start(String packageName, String activityPath, Intent intent) {
        Command command = newCommand().append("start -n").append(packageName + "/" + activityPath);
        if (intent != null) {
            appendExtra(command, intent);
        }
        Adb.rawExecute(command);
    }

    /**
     * adb shell am startservice -n com.android.systemui/.SystemUIService
     * 例如：startService("com.android.systemui",".SystemUIService")； //启动系统UI服务
     *
     * @param packageName 包名
     * @param servicePath 服务Service相对路径
     */
    public void startService(String packageName, String servicePath) {
        Command command = newCommand().append("startservice -n").append(packageName + "/" + servicePath);
        Adb.rawExecute(command);
    }

    /**
     * 强制关闭应用
     *
     * @param packageName 包名
     */
    public void foreStop(String packageName) {
        Adb.rawExecute(newCommand().append("force-stop").append(packageName));
    }

    /**
     * 向进程发送收紧内存的命令
     * TRIM_MEMORY_LEVEL_HIDDEN
     * TRIM_MEMORY_RUNNING_MODERATE
     * TRIM_MEMORY_BACKGROUND
     * TRIM_MEMORY_RUNNING_LOW 收紧内存命令
     * TRIM_MEMORY_MODERATE
     * TRIM_MEMORY_RUNNING_CRITICAL
     * TRIM_MEMORY_COMPLETE
     *
     * @param pid   进程pid
     * @param level ActivityManager.TRIM_MEMORY_X
     */
    public void trimMemory(int pid, String level) {
        Adb.rawExecute(newCommand().append("send-trim-memory").append(pid).append(level));
    }


    /**
     * 向设备发送广播
     *
     * @param intent 附带参数
     */
    public void sendBroadcast(Intent intent) {
        if (intent != null) {
            if (intent.getAction() == null) {
                throw new IllegalArgumentException("action can not be null!");
            }
            Command cmd = newCommand().append("broadcast");
            cmd.append("-a").append(intent.getAction());
            if (intent.getDataUri() != null) {
                cmd.append("-d").append(intent.getDataUri());
            }
            if (intent.getMimeType() != null) {
                cmd.append("-t").append(intent.getMimeType());
            }
            if (intent.getComponent() != null) {
                cmd.append("-n").append(intent.getComponent());
            }
            if (intent.getFlags() > 0) {
                cmd.append("-f").append(intent.getFlags());
            }
            appendExtra(cmd,intent);
            Adb.rawExecute(cmd);
        }
    }

    private Command appendExtra(Command cmd, Intent intent) {
        if (intent.getExtras() != null && intent.getExtras().size() > 0) {
            for (String key : intent.getExtras().keySet()) {
                Object o = intent.getExtras().get(key);
                if (o == null) {
                    cmd.append("--esn");
                } else if (o instanceof Integer) {
                    cmd.append("--ei");
                } else if (o instanceof Boolean) {
                    cmd.append("--ez");
                } else if (o instanceof String) {
                    cmd.append("--es");
                } else if (o instanceof Long) {
                    cmd.append("--el");
                } else if (o instanceof Float) {
                    cmd.append("--ef");
                } else if (o instanceof URI) {
                    cmd.append("--eu");
                } else if (o instanceof int[]) {
                    cmd.append("--eia");
                } else if (o instanceof long[]) {
                    cmd.append("--ela");
                }
                cmd.append(o);
            }
        }
        return cmd;
    }

    public class Intent {
        private String action;
        private String dataUri;
        private String mimeType;
        private Map<String, Object> extras;
        private String component;
        private int flags = -1;

        public Intent flags(int flags) {
            this.flags = flags;
            return this;
        }

        public Intent component(String component) {
            this.component = component;
            return this;
        }

        public Intent action(String action) {
            this.action = action;
            return this;
        }

        public Intent dataUri(String dataUri) {
            this.dataUri = dataUri;
            return this;
        }

        public Intent mimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Intent appendExtra(String key, int value) {
            append(key, value);
            return this;
        }

        public Intent appendExtra(String key, int[] value) {
            append(key, value);
            return this;
        }

        public Intent appendExtra(String key, long[] value) {
            append(key, value);
            return this;
        }

        public Intent appendExtra(String key, float value) {
            append(key, value);
            return this;
        }

        public Intent appendExtra(String key, URI value) {
            append(key, value);
            return this;
        }

        public Intent appendExtraNone(String key) {
            append(key, null);
            return this;
        }

        public Intent appendExtra(String key, boolean value) {
            append(key, value);
            return this;
        }

        public Intent appendExtra(String key, String value) {
            append(key, value);
            return this;
        }

        private void append(String key, Object value) {
            if (extras == null) {
                extras = new HashMap<>();
            }
            extras.put(key, value);
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getDataUri() {
            return dataUri;
        }

        public void setDataUri(String dataUri) {
            this.dataUri = dataUri;
        }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public Map<String, Object> getExtras() {
            return extras;
        }

        public void setExtras(Map<String, Object> extras) {
            this.extras = extras;
        }

        public String getComponent() {
            return component;
        }

        public void setComponent(String component) {
            this.component = component;
        }

        public int getFlags() {
            return flags;
        }

        public void setFlags(int flags) {
            this.flags = flags;
        }

        public class Action {
            /**
             * 这里是一些常用的系统状态广播
             */
            public static final String ACTION_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";    //网络连接发生变化
            public static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";    //屏幕点亮
            public static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";    //屏幕熄灭
            public static final String ACTION_BATTERY_LOW = "android.intent.action.BATTERY_LOW";    //电量低，会弹出电量低提示框
            public static final String ACTION_BATTERY_OKAY = "android.intent.action.BATTERY_OKAY";    //电量恢复了
            public static final String ACTION_DEVICE_STORAGE_LOW = "android.intent.action.DEVICE_STORAGE_LOW";    //存储空间过低
            public static final String ACTION_DEVICE_STORAGE_OK = "android.intent.action.DEVICE_STORAGE_OK";    //存储空间恢复
            public static final String ACTION_PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";    //安装了新的应用
            public static final String ACTION_STATE_CHANGE = "android.net.wifi.STATE_CHANGE";    //WiFi连接状态发生变化
            public static final String ACTION_WIFI_STATE_CHANGED = "android.net.wifi.WIFI_STATE_CHANGED";    //WiFi 状态变为启用/关闭/正在启动/正在关闭/未知
            public static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";    //电池电量发生变化
            public static final String ACTION_INPUT_METHOD_CHANGED = "android.intent.action.INPUT_METHOD_CHANGED";    //系统输入法发生变化
            public static final String ACTION_ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";    //外部电源连接
            public static final String ACTION_ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";    //外部电源断开连接
            public static final String ACTION_DREAMING_STARTED = "android.intent.action.DREAMING_STARTED";    //系统开始休眠
            public static final String ACTION_DREAMING_STOPPED = "android.intent.action.DREAMING_STOPPED";    //系统停止休眠
            public static final String ACTION_WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED";    //壁纸发生变化
            public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";    //插入耳机
            public static final String ACTION_MEDIA_UNMOUNTED = "android.intent.action.MEDIA_UNMOUNTED";    //卸载外部介质
            public static final String ACTION_MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";    //挂载外部介质
            public static final String ACTION_POWER_SAVE_MODE_CHANGED = "android.os.action.POWER_SAVE_MODE_CHANGED";    //省电模式开启
        }
    }


}
