package cn.toint.oktool.spring.boot.bdocr.model;

import java.util.Objects;

/**
 * @author Toint
 * @date 2025/9/8
 */
public class Word {
    private Integer row;
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(row, word1.row) && Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, word);
    }

    @Override
    public String toString() {
        return "Word{" +
                "row=" + row +
                ", word='" + word + '\'' +
                '}';
    }
}
