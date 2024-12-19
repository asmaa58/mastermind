package com.example.mastermind;

import java.util.Random;

class ColorCode extends Code {

    private final String ALL_LETTERS = "RGBYMC";

    ColorCode(final GameSettings gameSettings) {
        super(gameSettings);
    }

    @Override
    String generateRandomCode(int codeLength, int symbolsCount, char[] symbolsList) {
        final Random random = new Random();

        int randomIndex;
        String randomCode = "";
        while (randomCode.length() != codeLength) {
            randomIndex = random.nextInt(symbolsCount);
            randomCode += symbolsList[randomIndex];
        }
        return randomCode;
    }

    @Override
    char[] initSymbolsList(int symbolsCount) {
        return ALL_LETTERS.substring(0, symbolsCount).toCharArray();
    }

    @Override
    String initRangelabel(int symbolsCount) {
        return ALL_LETTERS.substring(0, symbolsCount);
    }

    @Override
    int initMaxSymbolsCount() {
        return ALL_LETTERS.length();
    }

    @Override
    boolean isUserGuessValid(String userGuess, int codeLength, int symbolsCount) {
        return userGuess.matches("^[" + ALL_LETTERS.substring(0, symbolsCount) + "]{" + codeLength + "}$");
    }
}
