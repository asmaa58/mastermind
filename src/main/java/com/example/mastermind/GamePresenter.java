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
        String rangeLabel = model.getRangeLabel();

        boolean shouldStartGame = view.promptUserForGameStart(rangeLabel);

        if (!shouldStartGame)
            System.exit(0);

        view.printHeader(model.getGuessList());
        while (model.getGameState() == GameState.RUNNING) {
            view.printReminder(rangeLabel);

            String userGuess;
            do {
                userGuess = view.promptUserForGuess();
            } while (!model.isUserGuessValid(userGuess));

            model.addGuess(userGuess);
            view.printHeader(model.getGuessList());
        }
    }
}
