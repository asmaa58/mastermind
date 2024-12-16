package com.example.mastermind;

class GameSettings {
    private final int codeLength;
    private final int symbolsCount;
    private final int maxAttempts;
    private final CodeType codeType;

    // Defaults
    private final int DEFAULT_CODE_LENGTH = 4;
    private final int DEFAULT_SYMBOLS_COUNT = 4;
    private final int DEFAULT_MAX_ATTEMPTS = 10;
    private final CodeType DEFAULT_CODE_TYPE = CodeType.NUMBER;

    GameSettings() {
        codeLength = DEFAULT_CODE_LENGTH;
        symbolsCount = DEFAULT_SYMBOLS_COUNT;
        maxAttempts = DEFAULT_MAX_ATTEMPTS;
        codeType = DEFAULT_CODE_TYPE;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public int getSymbolsCount() {
        return symbolsCount;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}
