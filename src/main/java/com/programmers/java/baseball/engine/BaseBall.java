package com.programmers.java.baseball.engine;

import com.programmers.java.baseball.io.Input;
import com.programmers.java.baseball.io.NumberGenerator;
import com.programmers.java.baseball.io.Output;
import com.programmers.java.baseball.model.BallCount;
import com.programmers.java.baseball.model.Numbers;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
public class BaseBall implements Runnable {
    private final int COUNT_OF_NUMBERS = 3;

    NumberGenerator generator;
    Input input;
    Output output;

    @Override
    public void run() {
        Numbers answer = generator.generate(COUNT_OF_NUMBERS);
        while (true) {
            String inputString = input.input("숫자를 맞춰보세용");
            Optional<Numbers> inputNumbers = parse(inputString);
            if (inputNumbers.isEmpty()) {
                output.inputError();
                continue;
            }

            BallCount ballCount = ballCount(answer, inputNumbers);
            output.ballCount(ballCount);
            if (ballCount.getStrike() == COUNT_OF_NUMBERS) {
                output.correct();
                break;
            }
        }
    }

    private BallCount ballCount(Numbers answer, Numbers inputNumber) {
        AtomicInteger strike = new AtomicInteger();
        AtomicInteger ball = new AtomicInteger();
        answer.indexOfForEach((a, i) -> {
            inputNumber.indexOfForEach((n, j) -> {
                if (!a.equals(n)) return;
                if (i.equals(j)) strike.addAndGet(1);
                else ball.addAndGet(1);
            });
        });
        return new BallCount(strike.get(), ball.get());
    }

    // 입력값 오류시 Optional을 이용해 처리
    private Optional<Numbers> parse(String inputString) {
        return null;
    }

}
