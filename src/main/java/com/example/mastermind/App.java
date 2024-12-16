package com.example.mastermind;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameViewInterface view = new GameView(scanner);
        GameModel model = new GameModel();

        scanner.close();
    }
}
