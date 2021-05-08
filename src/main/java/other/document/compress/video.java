package other.document.compress;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;

/**
 * @author orcakill
 * @date 2021/5/8  9:08
 **/
public class video {
    /*将爬取b站的1个视频,进行压缩处理*/
    public static void main(String[] args) throws FileNotFoundException {
        /*提取路径*/
        String Path1="F://test//video_find";
        /*压缩路径*/
        String Path2="F://test//video_compress";
        /*将路径下的视频全部进行压缩*/
        boolean b1=video_compress(Path1,Path2);

    }
    public static boolean video_compress(String path1,String path2) throws FileNotFoundException {
        /*提取路径*/
        File dir1=new File(path1);
        /*判断提取路径是否存在*/
        if (!dir1.exists() || !dir1.isDirectory()) {// 判断是否存在目录
            System.out.println("提取路径"+path1+"不存在");
            return false;
        }
        /*压缩路径*/
        File dir2=new File(path2);
        /*判断提取路径是否存在*/
        if (!dir2.exists() || !dir2.isDirectory()) {// 判断是否存在目录
            System.out.println("压缩路径"+path2+"不存在，创建压缩路径");
            dir2.mkdirs();
            return false;
        }
        /*提取路径所有文件*/
        String[] files1 = dir1.list();
        /*压缩路径所有文件*/
        String[] files2;
        files2 = dir2.list();
        List<String> list1;
        assert files2 != null;
        list1 = Arrays.asList(files2);
        assert files1 != null;
        for (String s : files1) {
            int address = s.lastIndexOf(".");
            String zip = s;
            String path = path1 + s;
            zip = zip.substring(0, address);
            zip = zip + ".zip";
            if (!list1.contains(zip)) {
                FileOutputStream fos1 = new FileOutputStream(path);
                video.toZip(path2, fos1, true);
            }

        }


        return  true;
    }

    /**
     23
     * 压缩成ZIP 方法1
     24
     * @param srcDir 压缩文件夹路径
    25
     * @param out    压缩文件输出流
    26
     * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
    27
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
    28
     * @throws RuntimeException 压缩失败会抛出运行时异常
    29
     */

    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure) throws RuntimeException{
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        }
        else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            }
        }
    }
}
