package com.programmers.java.baseball.io;

import com.programmers.java.baseball.model.BallCount;

public interface Output {
    void ballCount(BallCount ballCount);

    void inputError();

    void correct();
}
