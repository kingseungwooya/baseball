package com.programmers.java.baseball;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        // Random값 생성
        String title = faker.name().title();
        System.out.println(title);
    }
}