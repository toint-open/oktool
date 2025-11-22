/*
 * Copyright 2025 Toint (599818663@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
