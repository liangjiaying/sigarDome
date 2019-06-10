package com.ljy;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

public class SigerDome {

    public static void main(String[] args) throws Exception {
        Sigar sigar = SigarUtil.sigar;
        Mem mem = sigar.getMem();
        // CPU
        System.out.println("cpu总百分比情况 "+sigar.getCpuPerc());
        // RAM
        System.out.println("当前内存使用量/内存总量:    "
                + mem.getUsed() / 1024L / 1024L / 1024L + "/"
                + mem.getTotal() / 1024L / 1024L / 1024L + "GB");
        // DISK
        FileSystem fslist[] = sigar.getFileSystemList();
        long total = 0L;
        long free = 0L;
        for (int i = 0; i < fslist.length; i++) {
            FileSystem fs = fslist[i];
            FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
            switch (fs.getType()) {
                case 2:
                    total += usage.getTotal();
                    free += usage.getFree();
                    break;
                default:
                    break;
            }
        }
        System.out.println("磁盘剩余大小/总磁盘大小:    " + free/1024L/1024L + "/" + total/1024L/1024L + "GB");
    }
}
