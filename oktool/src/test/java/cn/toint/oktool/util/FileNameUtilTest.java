package cn.toint.oktool.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Toint
 * @since 2025/9/10
 */
public class FileNameUtilTest {

    @Test
    void renameMainTest() {
        Assertions.assertEquals("1.pdf", FileNameUtil.renameMain("a.b.pdf", "1"));
        Assertions.assertEquals("a.pdf", FileNameUtil.renameMain(null, "a.pdf"));
        Assertions.assertEquals("a.pdf", FileNameUtil.renameMain("", "a.pdf"));
    }
}
