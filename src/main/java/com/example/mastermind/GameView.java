package com.example.mastermind;

import java.util.List;
import java.util.Scanner;

class GameView implements GameViewInterface {

    private final String PROMPT_SYMBOL = Ansi.CYAN + "> " + Ansi.RESET;

    private final Scanner scanner;

    GameView(final Scanner scanner) {
        this.scanner = scanner;
        if (isRunningInIDEConsole()) {
            System.err.println(Ansi.RED + "ERROR: Running in IDE console is not supported.\nPlease run in a terminal."
                    + Ansi.RESET);
            System.exit(1);
        }
    }

    @Override
    public boolean promptUserForGameStart(int codeLength, String rangeLabel, int numOfAttempts, CodeType codeType) {
        System.out.print(Ansi.CLEAR);

        printBanner();

        System.out.print("""
                Mastermind is a simple guessing game between you and the computer.
                The PC will create a secret code.
                Try to guess the code in the fewest number of attempts.
                """);

        final String rangeLabelFormatted;
        if (codeType == CodeType.COLOR)
            rangeLabelFormatted = "colors " + colorColorCode(rangeLabel) + ", and colors can be repeated";
        else if (codeType == CodeType.NUMBER)
            rangeLabelFormatted = Ansi.BOLD + "numbers " + rangeLabel + Ansi.RESET + ", and numbers can be repeated";
        else
            throw new UnsupportedOperationException("CodeType '" + codeType + "' rangeLabel is not implemented.");

        System.out.println();
        System.out.println(Ansi.BLUE + "Instructions" + Ansi.RESET);
        System.out.println("1. You have " +
                Ansi.BOLD + numOfAttempts + " attempts" + Ansi.RESET
                + " to guess the code.");
        System.out.println("2. The code is " + Ansi.BOLD + codeLength + " letters" + Ansi.RESET + " long.");
        System.out.println("3. The code is made of " + rangeLabelFormatted + ".");

        System.out.println();
        System.out.print(PROMPT_SYMBOL + "Would you like to start a game? " +
                Ansi.DIM + "(y/n) " + Ansi.RESET);

        final String userAnswer = scanner.nextLine();
        final boolean shouldStartGame = userAnswer.toLowerCase().startsWith("y");
        return shouldStartGame;
    }

    private String colorColorCode(String text) {
        String colored = "";
        colored += Ansi.INVERT;
        colored += Ansi.BOLD;
        for (char c : text.toUpperCase().toCharArray()) {
            switch (c) {
                case 'R':
                    colored += Ansi.RED + "R";
                    break;
                case 'G':
                    colored += Ansi.GREEN + "G";
                    break;
                case 'B':
                    colored += Ansi.BLUE + "B";
                    break;
                case 'Y':
                    colored += Ansi.YELLOW + "Y";
                    break;
                case 'M':
                    colored += Ansi.MAGENTA + "M";
                    break;
                case 'C':
                    colored += Ansi.CYAN + "C";
                    break;
                default:
                    throw new IllegalArgumentException("Unknown color '" + c + "'");
            }
        }
        colored += Ansi.RESET;
        return colored;
    }
    @Override
    public String promptUserForGuess(int codeLength) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'promptUserForGuess'");
    }

    @Override
    public void userWon() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userWon'");
    }

    @Override
    public void userLost(String answer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userLost'");
    }

    @Override
    public void printHeader(List<Guess> guesses) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printHeader'");
    }

    private boolean isRunningInIDEConsole() {
        return System.console() == null;
    }

    private void printBanner() {
        final String banner = """
                [38;5;148m███╗[38;5;184m   █[38;5;178m██╗ █[38;5;214m████[38;5;208m╗ ██[38;5;209m█████[38;5;203m╗███[38;5;204m████[38;5;198m█╗███[38;5;199m████[38;5;163m╗███[38;5;164m███╗ [0m
                [38;5;148m███[38;5;184m█╗ ██[38;5;178m██║█[38;5;214m█╔══[38;5;208m██╗██[38;5;209m╔═══[38;5;203m═╝╚═[38;5;204m═██╔═[38;5;198m═╝██[38;5;199m╔════[38;5;163m╝██╔[38;5;164m══██[38;5;128m╗[0m
                [38;5;148m██╔[38;5;184m████[38;5;178m╔██║█[38;5;214m████[38;5;208m██║█[38;5;209m█████[38;5;203m█╗  [38;5;204m ██║[38;5;198m   ██[38;5;199m███╗[38;5;163m  ██[38;5;164m████╔[38;5;128m╝[0m
                [38;5;148m██║[38;5;184m╚██╔[38;5;178m╝██║[38;5;214m██╔══[38;5;208m██║╚[38;5;209m════[38;5;203m██║  [38;5;204m ██║[38;5;198m   █[38;5;199m█╔══╝[38;5;163m  ██[38;5;164m╔══█[38;5;128m█╗[0m
                [38;5;148m██[38;5;184m║ ╚═[38;5;178m╝ ██║[38;5;214m██║ [38;5;208m ██║[38;5;209m█████[38;5;203m██║ [38;5;204m  ██║[38;5;198m   █[38;5;199m████[38;5;163m██╗██[38;5;164m║  █[38;5;128m█║[0m
                [38;5;148m╚═[38;5;184m╝   [38;5;178m  ╚═[38;5;214m╝╚═╝ [38;5;208m ╚═╝[38;5;209m╚═══[38;5;203m═══╝ [38;5;204m  ╚═[38;5;198m╝   [38;5;199m╚════[38;5;163m══╝╚[38;5;164m═╝  [38;5;128m╚═╝[0m
                [38;5;148m█[38;5;184m██╗ [38;5;178m  ██[38;5;214m█╗██╗[38;5;208m███╗[38;5;209m   ██[38;5;203m╗███[38;5;204m███╗
                [38;5;184m████╗[38;5;178m ███[38;5;214m█║██[38;5;208m║████[38;5;209m╗  █[38;5;203m█║██[38;5;204m╔══██[38;5;198m╗
                [38;5;184m██╔█[38;5;178m███╔█[38;5;214m█║██[38;5;208m║██╔[38;5;209m██╗ █[38;5;203m█║██[38;5;204m║  █[38;5;198m█║
                [38;5;184m██║╚[38;5;178m██╔╝[38;5;214m██║██[38;5;208m║██║[38;5;209m╚██╗[38;5;203m██║██[38;5;204m║  █[38;5;198m█║
                [38;5;184m██║[38;5;178m ╚═╝ [38;5;214m██║█[38;5;208m█║██[38;5;209m║ ╚██[38;5;203m██║█[38;5;204m████[38;5;198m█╔╝
                [38;5;184m╚═╝[38;5;178m    [38;5;214m ╚═╝╚[38;5;208m═╝╚═[38;5;209m╝  ╚[38;5;203m═══╝╚[38;5;204m════[38;5;198m═╝[0m
                    """;
        System.out.println(banner);
    }

}
