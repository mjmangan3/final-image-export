package com.vereview.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mjmangan on 10/1/17.
 */
public class ExportUtilsTest {

    @Test
    public void getExtensionTest(){
        try {
            Path t1 = Paths.get("\\\\192.168.22.177\\data\\vereview\\case_13_3413\\binary\\02\\00\\ORIGINAL\\PAGE_000001.png.gz");
            Path t2 = Paths.get("\\\\192.168.22.177\\data\\vereview\\case_13_3413\\binary\\03\\6e\\1f\\ORIGINAL\\PAGE_000002.tif.gz");
            Path t3 = Paths.get("\\\\192.168.22.177\\data\\vereview\\case_13_3413\\binary\\01\\10\\16\\ORIGINAL\\PAGE_000001.jpg.gz");
            Path t4 = Paths.get("\\\\192.168.22.177\\data\\vereview\\case_13_3413\\binary\\01\\10\\16\\ORIGINAL\\PAGE_000001");
            Assert.assertEquals("png", ExportUtils.getExtension(t1));
            Assert.assertEquals("tif", ExportUtils.getExtension(t2));
            Assert.assertEquals("jpg", ExportUtils.getExtension(t3));
            Assert.assertEquals("", ExportUtils.getExtension(t4));
        }catch (Throwable t){
            Assert.fail(ExceptionUtils.getStackTrace(t));
        }
    }

    @Test
    public void mergePathTest(){
        Path absolute = Paths.get("\\\\192.168.22.116\\Users\\bob\\Desktop\\Data\\LOAD DATA\\AllState\\to_process\\007-13-3413-2013-12-19-C-FTP\\VE20131218001\\IMAGES\\IMG_0000001", "VESCAN0000000001.tif");
        Path endDir = Paths.get("\\\\192.168.22.116\\Users\\bob\\Desktop\\Data\\LOAD DATA\\AllState\\post_process");
        Path startDir = Paths.get("\\\\192.168.22.116\\Users\\bob\\Desktop\\Data\\LOAD DATA\\AllState\\to_process");
        Path expected = Paths.get("\\\\192.168.22.116\\Users\\bob\\Desktop\\Data\\LOAD DATA\\AllState\\post_process\\007-13-3413-2013-12-19-C-FTP\\VE20131218001\\IMAGES\\IMG_0000001", "VESCAN0000000001.tif");
        Path actual = ExportUtils.mergePaths(startDir, endDir, absolute);
        System.out.println(actual.toAbsolutePath().toString());
        Assert.assertEquals(expected, actual);

    }
}
