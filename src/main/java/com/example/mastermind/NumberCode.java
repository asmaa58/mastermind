package com.example.mastermind;

import java.util.Random;

class NumberCode extends Code {

    NumberCode(int codeLength) {
        super(codeLength);
    }

    @Override
    String generateRandomCode() {
        String code = "";
        Random random = new Random();
        while (code.length() != codeLength) {
            code += random.nextInt(codeLength) + 1;
        }
        return code;
    }

    @Override
    char[] initSymbolsList() {
        char[] symbolsList = new char[codeLength];
        for (int i = 0; i < codeLength; i++) {
            symbolsList[i] = (char) (i + 1);
        }
        return symbolsList;
    }

    @Override
    String initRangelabel() {
        return "1-" + codeLength;
    }

    @Override
    int initMaximumCodeLength() {
        return 9;
    }
}
