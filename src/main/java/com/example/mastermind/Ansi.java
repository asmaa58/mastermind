package com.example.mastermind;

enum Ansi {
    GREEN("\u001B[32m"),
    BLUE("\u001B[34m"),
    YELLOW("\u001B[33m"),
    WHITE("\u001B[37m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    MAGENTA("\u001B[35m"),
    CYAN("\u001B[36m"),
    INVERT("\u001B[7m"),
    BOLD("\u001B[1m"),
    DIM("\u001B[2m"),
    CLEAR("\u001B[2J\u001B[H"),
    RESET("\u001B[0m");

    private final String colorCode;

    private Ansi(String colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public String toString() {
        return colorCode;
    }
}
