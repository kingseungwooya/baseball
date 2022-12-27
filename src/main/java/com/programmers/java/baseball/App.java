package com.programmers.java.baseball;

import com.programmers.java.baseball.engine.BaseBall;
import com.programmers.java.baseball.io.Input;
import com.programmers.java.baseball.io.NumberGenerator;
import com.programmers.java.baseball.io.Output;
import com.programmers.java.baseball.model.Numbers;

public class App {
    public static void main(String[] args) {

        NumberGenerator numberGenerator = new FakerNumberGenerator();
        Console console = new Console(); // input, output으로도 사용 가능
        new BaseBall(numberGenerator, console, console).run();
    }
}