package com.ws.match;

import com.ws.match.utils.AlgorithmUtils;
import org.junit.jupiter.api.Test;

/**
 * @Author: 王顺
 * @Date: 2023/6/25 16:38
 * @Version 1.0
 */
public class AlgorithmTest {
    @Test
    void test() {
        String str1 = "123456";
        String str2 = "456";
        int i = AlgorithmUtils.minDistance(str1, str2);
        System.out.println(i);
    }
}
