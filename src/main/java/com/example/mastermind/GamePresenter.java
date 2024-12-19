package com.example.mastermind;

class GamePresenter {
    private final GameViewInterface view;
    private final GameModel model;

    GamePresenter(GameViewInterface view, GameModel model) {
        this.view = view;
        this.model = model;
        startGame();
    }

    private void startGame() {
        GameSettings settings = model.getGameSettings();

        boolean shouldStartGame = view.promptUserForGameStart(
                model.getRangeLabel());

        if (!shouldStartGame)
            System.exit(0);
    }
}
