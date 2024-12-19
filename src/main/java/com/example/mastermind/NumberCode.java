package com.example.mastermind;

import java.util.Random;

class NumberCode extends Code {

    NumberCode(final GameSettings gameSettings) {
        super(gameSettings);
    }

    @Override
    String generateRandomCode(int codeLength, int symbolsCount, char[] symbolsList) {
        final Random random = new Random();

        String randomCode = "";
        while (randomCode.length() != codeLength) {
            randomCode += random.nextInt(symbolsCount) + 1;
        }
        return randomCode;
    }

    @Override
    char[] initSymbolsList(int symbolsCount) {
        final char[] symbolsList = new char[symbolsCount];
        for (int i = 0; i < symbolsCount; i++) {
            symbolsList[i] = (char) (i + 1);
        }
        return symbolsList;
    }

    @Override
    String initRangelabel(int symbolsCount) {
        return "1 to " + symbolsCount;
    }

    @Override
    int initMaxSymbolsCount() {
        return 9;
    }

    @Override
    boolean isUserGuessValid(String userGuess, int codeLength, int symbolsCount) {
        return userGuess.matches("^[1-" + symbolsCount + "]{" + codeLength + "}$");
    }
}
