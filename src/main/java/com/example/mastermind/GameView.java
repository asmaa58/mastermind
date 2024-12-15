package com.example.mastermind;

import java.util.List;
import java.util.Scanner;

class GameView implements GameViewInterface {

    private Scanner scanner;

    public GameView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean promptUserForGameStart(int codeLength, String rangeLabel, int numOfAttempts) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'promptUserForGameStart'");
    }

    @Override
    public String promptUserForGuess(int codeLength) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'promptUserForGuess'");
    }

    @Override
    public void userWon() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userWon'");
    }

    @Override
    public void userLost(String answer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userLost'");
    }

    @Override
    public void printHeader(List<Guess> guesses) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printHeader'");
    }

}
