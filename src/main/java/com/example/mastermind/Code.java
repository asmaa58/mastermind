package com.example.mastermind;

abstract class Code {
    private final String code;

    private final char[] symbolsList;
    private final String rangeLabel;
    private final int maxSymbolsCount;

    Code(final GameSettings settings) {
        final int symbolsCount = settings.getSymbolsCount();
        final int codeLength = settings.getCodeLength();

        symbolsList = initSymbolsList(symbolsCount);
        rangeLabel = initRangelabel(symbolsCount);
        maxSymbolsCount = initMaxSymbolsCount();
        code = generateRandomCode(codeLength, symbolsCount, symbolsList);
    }

    abstract String generateRandomCode(int codeLength, int symbolsCount, char[] symbolsList);

    abstract char[] initSymbolsList(int symbolsCount);

    abstract String initRangelabel(int symbolsCount);

    abstract int initMaxSymbolsCount();

    abstract boolean isUserGuessValid(String userGuess, int codeLength, int symbolsCount);

    final char[] getSymbolsList() {
        return symbolsList;
    }

    final String getRangeLabel() {
        return rangeLabel;
    }

    final int getMaxSymbolsCount() {
        return maxSymbolsCount;
    }

    final String getCodeText() {
        return code;
    }
}
