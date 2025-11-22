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

package cn.toint.oktool.spring.boot.bdocr.util;

import cn.hutool.v7.core.collection.CollUtil;
import cn.toint.oktool.spring.boot.bdocr.model.Word;

import java.util.List;

/**
 * @author Toint
 * @since 2025/9/9
 */
public class WordUtil {
    public static String getFirstWord(List<Word> words) {
        if (CollUtil.isEmpty(words)) return null;
        return words.getFirst().getWord();
    }

    public static String getWord(Word word) {
        if (word == null) return null;
        return word.getWord();
    }
}
