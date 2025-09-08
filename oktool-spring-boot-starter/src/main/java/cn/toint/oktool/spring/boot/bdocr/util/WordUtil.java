package cn.toint.oktool.spring.boot.bdocr.util;

import cn.hutool.v7.core.collection.CollUtil;
import cn.toint.oktool.spring.boot.bdocr.model.Word;

import java.util.List;

/**
 * @author Toint
 * @dete 2025/9/9
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
