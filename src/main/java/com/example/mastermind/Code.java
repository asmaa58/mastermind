package com.example.mastermind;

abstract class Code {
    private final char[] symbolsList;
    private final String rangeLabel;
    private final int maximumCodeLength;

    final int codeLength;

    Code(int codeLength) {
        this.codeLength = codeLength;

        symbolsList = initSymbolsList();
        rangeLabel = initRangelabel();
        maximumCodeLength = initMaximumCodeLength();
    }

    abstract String generateRandomCode();

    abstract char[] initSymbolsList();

    abstract String initRangelabel();

    abstract int initMaximumCodeLength();

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
