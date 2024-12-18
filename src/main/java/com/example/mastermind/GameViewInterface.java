package com.example.mastermind;

import java.util.List;

interface GameViewInterface {
    // Events
    boolean promptUserForGameStart(int codeLength, String rangeLabel, int numOfAttempts, CodeType codeType);

    String promptUserForGuess(int codeLength);

    // Display
    void userWon();

    void userLost(String answer);

    void printHeader(List<Guess> guesses);
}
