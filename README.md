# AdbTool [For Java]


这个工具是我花了几个晚上通宵写的，它是您的Adb助手，有点类似uiautomator，我的更易使用和理解，可以自己实现许多自动化功能。

> //TODO 我将尽可能在未来一段时间中添加更多功能

获取调试设备
```java
// 设置调试模式
Adb.setDebug(true);

// 获取所有设备
Target[] targets = Adb.getTargets();

//设备信息
Target target = targets[0];
System.out.println(target.getDevice().toString());

//重启手机、进入Recovery模式、进入Fastboot模式
target.reboot();
target.revocery();
target.fastboot();
```

Shell点击、输入文本、滑动等等）
```java

//模拟点击
target.shell().click(100,100);

//模拟长按
target.shell().longClick(500,500);

//模拟滑动
target.shell().swipe(100,100,500,500);

//模拟按键输入
target.shell().pressKey(KeyCode.KEYCODE_CALENDAR);

//模拟输入文本
target.shell().input("something");
```

AM Activity Manager
```java
//模拟发送广播
target.shell().am().sendBroadcast(new Broadcast().action("app.iofox.top").appendExtra("p1",1).appendExtra("p2","string").appendExtra("p3",true).dataUri("http://example.com"));

```

PM 包管理
```java
//获取所有用户安装的包
List<PackageManager.Package> packages = target.shell().pm().listUserPackages();

//获取所有危险权限组
List<PackageManager.PermisisonGroup> groups = target.shell().pm().listPermissionGroupOption(OPTION_PERMISISON_DANGEROUS);

//获取所有用户可见权限组
List<PackageManager.PermisisonGroup> groups = target.shell().pm().listPermissionGroupOption(OPTION_PERMISISON_USER);

//获取所有危险权限
List<PackageManager.Permission> permissions = target.shell().pm().listPermissionOption(PackageManager.OPTION_PERMISISON_DANGEROUS);

//注意：PermisisonGroup中包含该组下所有Permission，同时Permission中也包含该权限所属的PermisisonGroup


//获取设备所依赖的Libraries
target.shell().pm().listLibraries();

//获取设备所依赖的Features
target.shell().pm().listFeatures();

//获取包名所在的路径
target.shell().pm().getPathByPackage("com.tencent.mm");

//安装应用
target.shell().pm().install(path,option);

//清除数据和缓存
target.shell().pm().clearDataAndCache(packageName);

//卸载应用
target.shell().pm().uninstall(packageName,true);
```

---

Apache License 2.0
