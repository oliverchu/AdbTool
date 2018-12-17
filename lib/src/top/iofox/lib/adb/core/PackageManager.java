package top.iofox.lib.adb.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Package manager (package) commands:
 * help
 * Print this help text.
 * <p>
 * path [--user USER_ID] PACKAGE
 * Print the path to the .apk of the given PACKAGE.
 * <p>
 * dump PACKAGE
 * Print various system state associated with the given PACKAGE.
 * <p>
 * list features
 * Prints all features of the system.
 * <p>
 * has-feature FEATURE_NAME [version]
 * Prints true and returns exit status 0 when system has a FEATURE_NAME,
 * otherwise prints false and returns exit status 1
 * <p>
 * list instrumentation [-f] [TARGET-PACKAGE]
 * Prints all test packages; optionally only those targeting TARGET-PACKAGE
 * Options:
 * -f: dump the name of the .apk file containing the test package
 * <p>
 * list libraries
 * Prints all system libraries.
 * <p>
 * list packages [-f] [-d] [-e] [-s] [-3] [-i] [-l] [-u] [-U]
 * [--uid UID] [--user USER_ID] [FILTER]
 * Prints all packages; optionally only those whose name contains
 * the text in FILTER.  Options are:
 * -f: see their associated file
 * -d: filter to only show disabled packages
 * -e: filter to only show enabled packages
 * -s: filter to only show system packages
 * -3: filter to only show third party packages
 * -i: see the installer for the packages
 * -l: ignored (used for compatibility with older releases)
 * -U: also show the package UID
 * -u: also include uninstalled packages
 * --uid UID: filter to only show packages with the given UID
 * --user USER_ID: only list packages belonging to the given user
 * <p>
 * list permission-groups
 * Prints all known permission groups.
 * <p>
 * list permissions [-g] [-f] [-d] [-u] [GROUP]
 * Prints all known permissions; optionally only those in GROUP.  Options are:
 * -g: organize by group
 * -f: print all information
 * -s: short summary
 * -d: only list dangerous permissions
 * -u: list only the permissions users will see
 * <p>
 * resolve-activity [--brief] [--components] [--user USER_ID] INTENT
 * Prints the activity that resolves to the given INTENT.
 * <p>
 * query-activities [--brief] [--components] [--user USER_ID] INTENT
 * Prints all activities that can handle the given INTENT.
 * <p>
 * query-services [--brief] [--components] [--user USER_ID] INTENT
 * Prints all services that can handle the given INTENT.
 * <p>
 * query-receivers [--brief] [--components] [--user USER_ID] INTENT
 * Prints all broadcast receivers that can handle the given INTENT.
 * <p>
 * install [-lrtsfdg] [-i PACKAGE] [--user USER_ID|all|current]
 * [-p INHERIT_PACKAGE] [--install-location 0/1/2]
 * [--originating-uri URI] [---referrer URI]
 * [--abi ABI_NAME] [--force-sdk]
 * [--preload] [--instantapp] [--full] [--dont-kill]
 * [--force-uuid internal|UUID] [--pkg PACKAGE] [-S BYTES] [PATH|-]
 * Install an application.  Must provide the apk data to install, either as a
 * file path or '-' to read from stdin.  Options are:
 * -l: forward lock application
 * -R: disallow replacement of existing application
 * -t: allow test packages
 * -i: specify package name of installer owning the app
 * -s: install application on sdcard
 * -f: install application on internal flash
 * -d: allow version code downgrade (debuggable packages only)
 * -p: partial application install (new split on top of existing pkg)
 * -g: grant all runtime permissions
 * -S: size in bytes of package, required for stdin
 * --user: install under the given user.
 * --dont-kill: installing a new feature split, don't kill running app
 * --originating-uri: set URI where app was downloaded from
 * --referrer: set URI that instigated the install of the app
 * --pkg: specify expected package name of app being installed
 * --abi: override the default ABI of the platform
 * --instantapp: cause the app to be installed as an ephemeral install app
 * --full: cause the app to be installed as a non-ephemeral full app
 * --install-location: force the install location:
 * 0=auto, 1=internal only, 2=prefer external
 * --force-uuid: force install on to disk volume with given UUID
 * --force-sdk: allow install even when existing app targets platform
 * codename but new one targets a final API level
 * <p>
 * install-create [-lrtsfdg] [-i PACKAGE] [--user USER_ID|all|current]
 * [-p INHERIT_PACKAGE] [--install-location 0/1/2]
 * [--originating-uri URI] [---referrer URI]
 * [--abi ABI_NAME] [--force-sdk]
 * [--preload] [--instantapp] [--full] [--dont-kill]
 * [--force-uuid internal|UUID] [--pkg PACKAGE] [-S BYTES]
 * Like "install", but starts an install session.  Use "install-write"
 * to push data into the session, and "install-commit" to finish.
 * <p>
 * install-write [-S BYTES] SESSION_ID SPLIT_NAME [PATH|-]
 * Write an apk into the given install session.  If the path is '-', data
 * will be read from stdin.  Options are:
 * -S: size in bytes of package, required for stdin
 * <p>
 * install-commit SESSION_ID
 * Commit the given active install session, installing the app.
 * <p>
 * install-abandon SESSION_ID
 * Delete the given active install session.
 * <p>
 * set-install-location LOCATION
 * Changes the default install location.  NOTE this is only intended for debugging;
 * using this can cause applications to break and other undersireable behavior.
 * LOCATION is one of:
 * 0 [auto]: Let system decide the best location
 * 1 [internal]: Install on internal device storage
 * 2 [external]: Install on external media
 * <p>
 * get-install-location
 * Returns the current install location: 0, 1 or 2 as per set-install-location.
 * <p>
 * move-package PACKAGE [internal|UUID]
 * <p>
 * move-primary-storage [internal|UUID]
 * <p>
 * pm uninstall [-k] [--user USER_ID] [--versionCode VERSION_CODE] PACKAGE [SPLIT]
 * Remove the given package name from the system.  May remove an entire app
 * if no SPLIT name is specified, otherwise will remove only the split of the
 * given app.  Options are:
 * -k: keep the data and cache directories around after package removal.
 * --user: remove the app from the given user.
 * --versionCode: only uninstall if the app has the given version code.
 * <p>
 * clear [--user USER_ID] PACKAGE
 * Deletes all data associated with a package.
 * <p>
 * enable [--user USER_ID] PACKAGE_OR_COMPONENT
 * disable [--user USER_ID] PACKAGE_OR_COMPONENT
 * disable-user [--user USER_ID] PACKAGE_OR_COMPONENT
 * disable-until-used [--user USER_ID] PACKAGE_OR_COMPONENT
 * default-state [--user USER_ID] PACKAGE_OR_COMPONENT
 * These commands change the enabled state of a given package or
 * component (written as "package/class").
 * <p>
 * hide [--user USER_ID] PACKAGE_OR_COMPONENT
 * unhide [--user USER_ID] PACKAGE_OR_COMPONENT
 * <p>
 * suspend [--user USER_ID] TARGET-PACKAGE
 * Suspends the specified package (as user).
 * <p>
 * unsuspend [--user USER_ID] TARGET-PACKAGE
 * Unsuspends the specified package (as user).
 * <p>
 * grant [--user USER_ID] PACKAGE PERMISSION
 * revoke [--user USER_ID] PACKAGE PERMISSION
 * These commands either grant or revoke permissions to apps.  The permissions
 * must be declared as used in the app's manifest, be runtime permissions
 * (protection level dangerous), and the app targeting SDK greater than Lollipop MR1.
 * <p>
 * reset-permissions
 * Revert all runtime permissions to their default state.
 * <p>
 * set-permission-enforced PERMISSION [true|false]
 * <p>
 * get-privapp-permissions TARGET-PACKAGE
 * Prints all privileged permissions for a package.
 * <p>
 * get-privapp-deny-permissions TARGET-PACKAGE
 * Prints all privileged permissions that are denied for a package.
 * <p>
 * get-oem-permissions TARGET-PACKAGE
 * Prints all OEM permissions for a package.
 * <p>
 * set-app-link [--user USER_ID] PACKAGE {always|ask|never|undefined}
 * get-app-link [--user USER_ID] PACKAGE
 * <p>
 * trim-caches DESIRED_FREE_SPACE [internal|UUID]
 * Trim cache files to reach the given free space.
 * <p>
 * create-user [--profileOf USER_ID] [--managed] [--restricted] [--ephemeral]
 * [--guest] USER_NAME
 * Create a new user with the given USER_NAME, printing the new user identifier
 * of the user.
 * <p>
 * remove-user USER_ID
 * Remove the user with the given USER_IDENTIFIER, deleting all data
 * associated with that user
 * <p>
 * set-user-restriction [--user USER_ID] RESTRICTION VALUE
 * <p>
 * get-max-users
 * <p>
 * get-max-running-users
 * <p>
 * compile [-m MODE | -r REASON] [-f] [-c] [--split SPLIT_NAME]
 * [--reset] [--check-prof (true | false)] (-a | TARGET-PACKAGE)
 * Trigger compilation of TARGET-PACKAGE or all packages if "-a".  Options are:
 * -a: compile all packages
 * -c: clear profile data before compiling
 * -f: force compilation even if not needed
 * -m: select compilation mode
 * MODE is one of the dex2oat compiler filters:
 * assume-verified
 * extract
 * verify
 * quicken
 * space-profile
 * space
 * speed-profile
 * speed
 * everything
 * -r: select compilation reason
 * REASON is one of:
 * first-boot
 * boot
 * install
 * bg-dexopt
 * ab-ota
 * inactive
 * shared
 * --reset: restore package to its post-install state
 * --check-prof (true | false): look at profiles when doing dexopt?
 * --secondary-dex: compile app secondary dex files
 * --split SPLIT: compile only the given split name
 * <p>
 * force-dex-opt PACKAGE
 * Force immediate execution of dex opt for the given PACKAGE.
 * <p>
 * bg-dexopt-job
 * Execute the background optimizations immediately.
 * Note that the command only runs the background optimizer logic. It may
 * overlap with the actual job but the job scheduler will not be able to
 * cancel it. It will also run even if the device is not in the idle
 * maintenance mode.
 * <p>
 * reconcile-secondary-dex-files TARGET-PACKAGE
 * Reconciles the package secondary dex files with the generated oat files.
 * <p>
 * dump-profiles TARGET-PACKAGE
 * Dumps method/class profile files to
 * /data/misc/profman/TARGET-PACKAGE.txt
 * <p>
 * snapshot-profile TARGET-PACKAGE [--code-path path]
 * Take a snapshot of the package profiles to
 * /data/misc/profman/TARGET-PACKAGE[-code-path].prof
 * If TARGET-PACKAGE=android it will take a snapshot of the boot image
 * <p>
 * set-home-activity [--user USER_ID] TARGET-COMPONENT
 * Set the default home activity (aka launcher).
 * <p>
 * set-installer PACKAGE INSTALLER
 * Set installer package name
 * <p>
 * get-instantapp-resolver
 * Return the name of the component that is the current instant app installer.
 * <p>
 * set-harmful-app-warning [--user <USER_ID>] <PACKAGE> [<WARNING>]
 * Mark the app as harmful with the given warning message.
 * <p>
 * get-harmful-app-warning [--user <USER_ID>] <PACKAGE>
 * Return the harmful app warning message for the given app, if present
 * <p>
 * uninstall-system-updates
 * Remove updates to all system applications and fall back to their /system version.
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
public class PackageManager extends Base {

    public static final String OPTION_ALL_PACKAGES = "";
    public static final String OPTION_SYSTEM_PACKAGES = "-s";
    public static final String OPTION_USER_PACKAGES = "-3";
    public static final String OPTION_AVALIABLE_PACKAGES = "-e";
    public static final String OPTION_UNAVALIABLE_PACKAGES = "-d";
    public static final String OPTION_PERMISISON_DANGEROUS = "-d";
    public static final String OPTION_PERMISISON_USER = "-u";

    public PackageManager(Base base) {
        super(base);
    }

    @Override
    public String onCreateCommand() {
        return "pm";
    }

    /**
     * option:
     * -l	锁定应用程序
     * -r	重新安装应用，且保留应用数据
     * -t	允许测试apk被安装
     * -i <INSTALLER_PACKAGE_NAME>	指定安装包的包名
     * -s	安装到sd卡
     * -f	安装到系统内置存储中（默认安装位置）
     * -d	允许降级安装（同一应用低级换高级）
     * -g	授予应用程序清单中列出的所有权限（只有6.0系统可用）
     *
     * @param path 安装包位置
     */
    public void install(String path, String option) {
        Adb.rawExecute(newCommand().append("install").append(path));
    }

    /**
     * 清除应用数据和缓存
     *
     * @param packageName 程序包名
     */
    public void clearDataAndCache(String packageName) {
        Adb.rawExecute(newCommand().append("clear").append(packageName));
    }

    public void setPackageVisible(String packageOrComponent, boolean visible) {
        Command command = newCommand();
        if (visible) {
            command.append("unhide");
        } else {
            command.append("hide");
        }
        Adb.rawExecute(command.append(packageOrComponent));
    }

    /**
     * 卸载应用
     *
     * @param packageName 包名
     * @param removeData  是否删除数据 (添加-k参数)
     */
    public void uninstall(String packageName, boolean removeData) {
        Command cmd = newCommand().append("uninstall");
        if (!removeData) {
            cmd.append("-k");
        }
        cmd.append(packageName);
        Adb.rawExecute(cmd);
    }


    public String[] getPathByPackage(String packageName) {
        return getStringArray("path", packageName);
    }

    public String[] listFeatures() {
        return getStringArray("list features", "");
    }

    public String[] listLibraries() {
        return getStringArray("list libraries", "");
    }

    private String[] getStringArray(String cmd, String param) {
        String path = Adb.rawExecute(newCommand().append(cmd).append(param));
        String[] lines = path.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = shiftRightBy(lines[i], ":");
        }
        return lines;
    }

    public List<Package> listSystemPackages() {
        return listPackageOption(OPTION_SYSTEM_PACKAGES);
    }

    public List<Package> listUserPackages() {
        return listPackageOption(OPTION_USER_PACKAGES);
    }

    public List<Package> listAvaliablePackages() {
        return listPackageOption(OPTION_AVALIABLE_PACKAGES);
    }

    public List<Package> listAllPackages() {
        return listPackageOption(OPTION_ALL_PACKAGES);
    }

    public List<Package> listUnavaliablePackages() {
        return listPackageOption(OPTION_UNAVALIABLE_PACKAGES);
    }

    /**
     * -d	使用过滤器，只显示禁用的应用的包名
     * -e	使用过滤器，只显示可用的应用的包名
     * -s	使用过滤器，只显示系统应用的包名
     * -3	使用过滤器，只显示第三方应用的包名
     *
     * @param option 参上
     * @return List
     */
    public List<Package> listPackageOption(String option) {
        String s = Adb.rawExecute(newCommand().append("list packages -f -i").append(option));
        String[] lines = s.split("\n");
        List<Package> packages = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            int installerIdx = lines[i].lastIndexOf("installer");
            String substring = lines[i].substring(7, installerIdx).trim();
            String installer = lines[i].substring(installerIdx + 10);
            String path = substring.substring(0, substring.lastIndexOf("="));
            String packageName = substring.substring(substring.lastIndexOf("=") + 1);
            Package pk = new Package(path, packageName, installer);
            packages.add(pk);
        }
        return packages;
    }

    /**
     * adb shell pm list permissions -f -g
     *
     * @param option
     */
    public List<PermisisonGroup> listPermissionGroupOption(String option) {
        String s = Adb.rawExecute(newCommand().append("list permissions -f -g").append(option));
        s = s.substring(s.indexOf("+"));
        String[] split = s.split("\\+ group:");
        List<PermisisonGroup> groups = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            String[] group = split[i].split("\\+ permission:");
            PermisisonGroup g = new PermisisonGroup();
            List<Permission> pList = new ArrayList<>();
            for (int j = 0; j < group.length; j++) {
                String[] items = group[j].split("\n");
                g.setPermissions(pList);
                if (j == 0) {
                    //group
                    g.setName(items[0]);
                    g.setPackageName(shiftRightBy(items[1], ":"));
                    g.setLabel(shiftRightBy(items[2], ":"));
                    g.setDescription(shiftRightBy(items[3], ":"));
                } else {
                    Permission p = new Permission();
                    p.setName(items[0]);
                    p.setPackageName(shiftRightBy(items[1], ":"));
                    p.setLabel(shiftRightBy(items[2], ":"));
                    p.setDescription(shiftRightBy(items[3], ":"));
                    p.setProtectionLevel(shiftRightBy(items[4], ":"));
                    p.setPermisisonGroup(g);
                    pList.add(p);
                }
            }
            groups.add(g);
        }
        return groups;
    }

    public void grant(String packageName, String permissionName) {
        Adb.rawExecute(newCommand().append("grant").append(packageName).append(permissionName));
    }

    public void revoke(String packageName, String permissionName) {
        Adb.rawExecute(newCommand().append("revoke").append(packageName).append(permissionName));
    }

    public List<Permission> listPermissionOption(String option) {
        List<PermisisonGroup> groups = listPermissionGroupOption(option);
        List<Permission> permissions = new ArrayList<>();
        for (PermisisonGroup g : groups) {
            permissions.addAll(g.getPermissions());
        }
        return permissions;
    }

    private String shiftRightBy(String value, String pattern) {
        return value.substring(value.indexOf(pattern) + 1);
    }

    public static class PermisisonGroup {
        private String name;
        private String packageName;
        private String label;
        private String description;
        private List<Permission> permissions;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Permission> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<Permission> permissions) {
            this.permissions = permissions;
        }

        @Override
        public String toString() {
            return "PermisisonGroup{" +
                    "name='" + name + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", label='" + label + '\'' +
                    ", description='" + description + '\'' +
                    ", permissions=" + permissions +
                    '}';
        }
    }

    public static class Permission {
        private String name;
        private String packageName;
        private String label;
        private String description;
        private String protectionLevel;
        private PermisisonGroup permisisonGroup;

        public PermisisonGroup getPermisisonGroup() {
            return permisisonGroup;
        }

        public void setPermisisonGroup(PermisisonGroup permisisonGroup) {
            this.permisisonGroup = permisisonGroup;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProtectionLevel() {
            return protectionLevel;
        }

        public void setProtectionLevel(String protectionLevel) {
            this.protectionLevel = protectionLevel;
        }

        @Override
        public String toString() {
            return "Permission{" +
                    "name='" + name + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", label='" + label + '\'' +
                    ", description='" + description + '\'' +
                    ", protectionLevel='" + protectionLevel + '\'' +
                    '}';
        }
    }

    public static class Package {
        private String path;
        private String packageName;
        private String installer;

        public Package() {
        }

        public Package(String path, String packageName, String installer) {
            this.path = path;
            this.packageName = packageName;
            this.installer = installer;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getInstaller() {
            return installer;
        }

        public void setInstaller(String installer) {
            this.installer = installer;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Package) {
                Package p = (Package) obj;
                if (p.getPackageName() != null && p.getPackageName().equals(packageName)) {
                    return true;
                } else return p.getPackageName() == null && packageName == null;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Package{" +
                    "path='" + path + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", installer='" + installer + '\'' +
                    '}';
        }
    }


}
