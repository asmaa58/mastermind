package com.example.mastermind;

import java.util.List;

interface GameViewInterface {
    // Events
    boolean promptUserForGameStart(String rangeLabel);

    String promptUserForGuess();

    // Display
    void userWon();

    void userLost(String answer);

    void printHeader(List<Guess> guesses);
}
