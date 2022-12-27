package com.programmers.java.baseball;

import com.programmers.java.baseball.io.Input;
import com.programmers.java.baseball.io.Output;
import com.programmers.java.baseball.model.BallCount;

import java.util.Scanner;

public class Console implements Input, Output {
    // 한번사용해서 계속 사용하는것은  final을 선언해준다.
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public void ballCount(BallCount ballCount) {
        System.out.println(ballCount.getStrike() + " Strikes, " + ballCount.getBall() + " balls");
    }

    @Override
    public void inputError() {
        System.out.println("입력이 잘못되었습니다.");
    }

    @Override
    public void correct() {
        System.out.println(" 정답입니다. ");
    }
}
