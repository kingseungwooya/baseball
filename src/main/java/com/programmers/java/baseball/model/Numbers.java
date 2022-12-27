package com.programmers.java.baseball.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiConsumer;

@AllArgsConstructor
@Getter
public class Numbers {
    private Integer[] nums;

    public void indexOfForEach(BiConsumer<Integer, Integer> consumer) {
        for (int i = 0; i < nums.length; i++) {
            consumer.accept(nums[i], i);
        }
    }
}
