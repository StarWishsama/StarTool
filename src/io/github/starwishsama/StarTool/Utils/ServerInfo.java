package io.github.starwishsama.StarTool.Utils;

import java.lang.management.ManagementFactory;

public class ServerInfo {
    static Runtime run = Runtime.getRuntime();
    public static String getOsName(){
        String osName = System.getProperty("os.name");
        return osName;
    }

    public static long getUsedMemory() {
        return (run.totalMemory() - run.freeMemory()) / 1048576;
    }

    public static long getMaxMemory() {
        return run.maxMemory() / 1048576;
    }

    public static String getJVMVersion() {
        return ManagementFactory.getRuntimeMXBean().getVmVersion();
    }
}
