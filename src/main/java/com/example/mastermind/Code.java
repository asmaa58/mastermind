package com.example.mastermind;

abstract class Code {
    private final String code;
    private final char[] symbolsList;
    private final String rangeLabel;
    private final int maximumCodeLength;

    Code(final GameSettings settings) {
        final int symbolsCount = settings.getSymbolsCount();
        final int codeLength = settings.getCodeLength();

        symbolsList = initSymbolsList(symbolsCount);
        rangeLabel = initRangelabel(symbolsCount);
        maximumCodeLength = initMaximumCodeLength();
        code = generateRandomCode(codeLength, symbolsCount, symbolsList);
    }

    abstract String generateRandomCode(int codeLength, int symbolsCount, char[] symbolsList);

    abstract char[] initSymbolsList(int symbolsCount);

    abstract String initRangelabel(int symbolsCount);

    abstract int initMaximumCodeLength();

    abstract boolean isUserGuessValid(String userGuess, int codeLength, int symbolsCount);

    final char[] getSymbolsList() {
        return symbolsList;
    }

    final String getRangeLabel() {
        return rangeLabel;
    }

    final int getMaximumCodeLength() {
        return maximumCodeLength;
    }
}
