package top.starwish.StarTool.Misc;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

//源代码来自: https://blog.csdn.net/u014174854/article/details/79816693
public class ServerInfo {
    public static String getOsName(){
        String osName = System.getProperty("os.name");
        return osName;
    }

    public static long getUsedMemory(){
        int kb = 1024;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb / kb;
        return usedMemory;
    }

    public static long getTotalMemorySize(){
        int kb = 1024;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb / kb;
        return totalMemorySize;
    }

    public static String getJVMVersion(){
        String JVMVersion = ManagementFactory.getRuntimeMXBean().getVmVersion();
        return JVMVersion;
    }
}
