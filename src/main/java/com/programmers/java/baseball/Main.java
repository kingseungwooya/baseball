package com.programmers.java.baseball;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        User user = new User(24, "김승우");
        User user2 = new User(24, "김승우");

        System.out.println(user.equals(user2));
    }
}