package com.ljy;

import com.google.common.io.Resources;
import org.hyperic.sigar.Sigar;

import java.io.File;

public class SigarUtil {

    public static final Sigar sigar  = iniSigar();

    /** 
     * Windows
     *   sigar-amd64-winnt.dll拷贝到java jdk的安装路径下的bin目录
     * Centos
     *   libsigar-amd64-linux.so 拷贝到/usr/lib64/下面
     */
    private static Sigar iniSigar() {
        try {

            String path = System.getProperty("java.library.path");
            if (OsCheck.getOperatingSystemType() == OsCheck.OSType.Windows) {
                String file = Resources.getResource("sigar/sigar-amd64-winnt.dll").getFile();
                File classPath = new File(file).getParentFile();
                path += ";" + classPath.getCanonicalPath();
            } else {
                String file = Resources.getResource("sigar/libsigar-amd64-linux.so").getFile();
                File classPath = new File(file).getParentFile();
                path += ":" + classPath.getCanonicalPath();
            }
            System.setProperty("java.library.path", path);

            return new Sigar();
        } catch (Exception e) {
            return null;
        }
    }
}
