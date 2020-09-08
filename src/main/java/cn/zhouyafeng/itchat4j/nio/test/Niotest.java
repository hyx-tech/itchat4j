package cn.zhouyafeng.itchat4j.nio.test;

import java.nio.IntBuffer;

import org.junit.Test;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LZJ
 * @time 2020-09-08 09:28:50
 **/
@Data
@Slf4j
@NoArgsConstructor
public class Niotest {

    IntBuffer intBuffer;

    // <mvn>mvn exec:java -Dexec.mainClass="cn.zhouyafeng.itchat4j.nio.test.Niotest"</mvn>
    public static void main(String[] args) {
        log.info("xx");
    }
}
