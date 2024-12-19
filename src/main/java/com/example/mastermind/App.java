package com.example.mastermind;

import java.util.Scanner;

class App {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final GameModel model = new GameModel();
        final GameViewInterface view = new GameView(scanner, model.getGameSettings());
        final GamePresenter presenter = new GamePresenter(view, model);

        scanner.close();
    }
}
