package cn.zhouyafeng.itchat4j.nio.ddo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.security.SecureRandom;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @time 2020-09-08 09:28:50
 **/
@Data
@Slf4j
@NoArgsConstructor
public class Niotest {
    // 输入
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("out.txt");
            FileChannel fileChannel = fileOutputStream.getChannel();
            // 底层数组长度512
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            // 需要写入的内容
            byte[] wbytes = "my name is gg".getBytes();
            for (int i = 0; i < wbytes.length; i++) {
                byteBuffer.put(wbytes[i]);
            }
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            fileOutputStream.close();
        } catch (Exception ex) {
            log.error("error:{}", ex);
        }
    }

    // 输出
    public static void main3(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream(
                    "/home/van/soft/ideaproject/itchat4j/src/main/java/cn/zhouyafeng/itchat4j/nio/test/Niotest.java");
            FileChannel fileChannel = fileInputStream.getChannel();
            // 底层数组长度
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            // 将数据读取到buffer 中
            fileChannel.read(byteBuffer);
            // 输入输出流转换
            byteBuffer.flip();
            while (byteBuffer.remaining() > 0) {
                byte b = byteBuffer.get();
                log.info("{}", (char) b);
            }
            fileInputStream.close();
        } catch (Exception ex) {
            log.error("{}", ex);
        }
    }

    public static void main2(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            int random = new SecureRandom().nextInt();
            intBuffer.put(random);
        }
        // 流读写状态转换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            log.info("{}", intBuffer.get());
        }
    }

    public static void main1(String[] args) {
        try {
            log.info("xx");
            RandomAccessFile aFile = new RandomAccessFile(
                    "/home/van/soft/ideaproject/itchat4j/src/main/java/cn/zhouyafeng/itchat4j/nio/test/Niotest.java",
                    "rw");
            FileChannel inChannel = aFile.getChannel();
            // 创建缓冲区
            ByteBuffer buf = ByteBuffer.allocate(48);
            // 首次载入数据到缓冲区
            int bytesRead = inChannel.read(buf);
            Selector selector = Selector.open();
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                // 切换为读取模式
                buf.flip();
                // 循环检测缓冲区中是否还有数据
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                // 读完后 清空缓冲区
                buf.clear();
                // 循环载入数据到缓冲区
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (Exception ex) {
            log.error("error：{}", ex);
        }
    }
}
