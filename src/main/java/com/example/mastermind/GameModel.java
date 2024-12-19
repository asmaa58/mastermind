package com.example.mastermind;

import java.util.ArrayList;
import java.util.List;

class GameModel {
    private final GameSettings gameSettings;
    private GameState gameState;
    private final List<Guess> guessList;
    private final Code code;

    GameModel() {
        gameSettings = new GameSettings();
        guessList = new ArrayList<Guess>(gameSettings.getMaxAttempts());
        gameState = GameState.RUNNING;

        final CodeType codeType = gameSettings.getCodeType();
        switch (codeType) {
            case NUMBER:
                code = new NumberCode(gameSettings);
                break;
            case COLOR:
                code = new ColorCode(gameSettings);
                break;
            default:
                throw new EnumConstantNotPresentException(CodeType.class, codeType.name());
        }
    }

    GameState getGameState() {
        return gameState;
    }

    String getRangeLabel() {
        return code.getRangeLabel();
    }

    GameSettings getGameSettings() {
        return gameSettings;
    }

    List<Guess> getGuessList() {
        return guessList;
    }

    boolean isUserGuessValid(String guessText) {
        return code.isUserGuessValid(guessText, gameSettings.getCodeLength(), gameSettings.getSymbolsCount());
    }

    String getSolution() {
        return code.getCodeText();
    }

    void addGuess(String guessText) {
        final int attemptsRemaining = gameSettings.getMaxAttempts() - (guessList.size() + 1);
        Guess guess = new Guess(guessText, code.getCodeText(), attemptsRemaining);
        guessList.add(guess);

        if (guess.getExactMatches() == gameSettings.getCodeLength())
            gameState = GameState.USER_WON;
        else if (attemptsRemaining == 0)
            gameState = GameState.USER_LOST;
    }
}
