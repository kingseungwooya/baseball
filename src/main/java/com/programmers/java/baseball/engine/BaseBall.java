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

            BallCount ballCount = ballCount(answer, inputNumbers.get());
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

        long count = inputString.chars()
                .filter(Character::isDigit) // 입력된 값이 숫자가 맞는가?
                .map(Character::getNumericValue) // 문자에서 숫자값을 반환
                .filter(i -> i > 0) // 양수만 갖고오자
                .distinct() // 중복 제거
                .count();
        if (count != COUNT_OF_NUMBERS) return Optional.empty();
        return Optional.of(
                new Numbers( // 이미 앞에서 검증이 다 끝났다.
                        inputString.chars()
                                .map(Character::getNumericValue) // 숫자로 바꿔주고
                                .boxed()// Integer는 wrapper타입이다.
                                .toArray(Integer[]::new)
                )
        );
    }

}
