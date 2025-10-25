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

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author Toint
 * @since 2025/5/31
 */
public class AssertTest {
    @Test
    void notNull() {
        Assert.notNull("test", "must not be null");
    }

    @Test
    void validate() {
        // 原生使用
        // 报错: jakarta.validation.ValidationException: pojo 不能为null
        final Pojo pojo = new Pojo();
        pojo.setName("test");
        pojo.setAge(18);
        pojo.setPojo(null);
        Assertions.assertThrows(ValidationException.class, () -> Assert.validate(pojo));
    }

    private static class Pojo {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Pojo getPojo() {
            return pojo;
        }

        public void setPojo(Pojo pojo) {
            this.pojo = pojo;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null || getClass() != object.getClass()) return false;
            Pojo pojo1 = (Pojo) object;
            return Objects.equals(name, pojo1.name) && Objects.equals(age, pojo1.age) && Objects.equals(pojo, pojo1.pojo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, pojo);
        }

        @NotBlank
        private String name;

        @NotNull
        private Integer age;

        @Valid
        @NotNull
        private Pojo pojo;
    }
}