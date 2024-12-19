package com.example.mastermind;

import java.util.List;
import java.util.Scanner;

class GameView implements GameViewInterface {

    private final String PROMPT_SYMBOL = Ansi.CYAN + "> " + Ansi.RESET;

    private final Scanner scanner;

    private final GameSettings settings;

    GameView(final Scanner scanner, final GameSettings settings) {
        this.scanner = scanner;
        this.settings = settings;
        if (isRunningInIDEConsole()) {
            System.err.println(Ansi.RED + "ERROR: Running in IDE console is not supported.\nPlease run in a terminal."
                    + Ansi.RESET);
            System.exit(1);
        }
    }

    @Override
    public boolean promptUserForGameStart(String rangeLabel) {
        System.out.print(Ansi.CLEAR);

        printBanner();

        System.out.print("""
                Mastermind is a simple guessing game between you and the computer.
                The PC will create a secret code.
                Try to guess the code in the fewest number of attempts.
                """);

        final CodeType codeType = settings.getCodeType();
        final int numOfAttempts = settings.getMaxAttempts();
        final int codeLength = settings.getCodeLength();
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
    public String promptUserForGuess() {
        System.out.print(PROMPT_SYMBOL + "enter a " + settings.getCodeLength() + " letter code: ");
        return scanner.nextLine().toUpperCase();
    }

    @Override
    public void userWon() {
        System.out.println(Ansi.GREEN + "" + Ansi.BOLD + """
                         __   __           __        __
                         \\ \\ / /__  _   _  \\ \\      / /__  _ __
                          \\ V / _ \\| | | |  \\ \\ /\\ / / _ \\| '_ \\
                           | | (_) | |_| |   \\ V  V / (_) | | | |
                           |_|\\___/ \\__,_|    \\_/\\_/ \\___/|_| |_|
                """ + Ansi.RESET);
    }

    @Override
    public void userLost(String answer) {
        System.out.println(Ansi.RED + "" + Ansi.BOLD + """
                  ____                         ___
                 / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __
                | |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|
                | |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |
                 \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|
                  """ + Ansi.RESET);
        System.out.println("The code was " + colorColorCode(answer));
    }

    private final String CHAR_BORDER_SIDE_V = "║";
    private final String CHAR_BORDER_SIDE_H = "═";

    private final char CHAR_CORNER_UPPER_LEFT = '╔';
    private final char CHAR_CORNER_UPPER_RIGHT = '╗';
    private final char CHAR_CORNER_LOWER_LEFT = '╚';
    private final char CHAR_CORNER_LOWER_RIGHT = '╝';

    private final String CHAR_ROW_DIVIDER_SIDE = "─";
    private final char CHAR_ROW_DIVIDER_LEFT = '╟';
    private final char CHAR_ROW_DIVIDER_RIGHT = '╢';
    private final char CHAR_ROW_DIVIDER_COLUMN = '┼';

    private final char CHAR_COLUMN_DIVIDER = '│';

    private final char CHAR_COLUMN_UPPER = '╤';
    private final char CHAR_COLUMN_LOWER = '╧';

    private void printTopAndBottomBorder(int[] columnsWidth, char left, char right, char betweenColumns, String dash) {
        System.out.print(left);
        final int numOfColumns = columnsWidth.length;
        int columnWidth;
        for (int columnIndex = 0; columnIndex < numOfColumns; columnIndex++) {
            columnWidth = columnsWidth[columnIndex];
            System.out.print(dash.repeat(columnWidth + 2));
            if (columnIndex != numOfColumns - 1)
                System.out.print(betweenColumns);
        }
        System.out.println(right);
    }

    @Override
    public void printHeader(final List<Guess> guesses) {
        final String[] header = { "Guess", "Exact Matches", "Partial Matches", "Attempts Remaining" };
        final int COLUMNS_COUNT = header.length;
        final int ROWS_COUNT = guesses.size() + 1;

        // Convert List<Guess> to String[][]
        String[][] rows = new String[ROWS_COUNT][COLUMNS_COUNT];
        rows[0] = header;
        for (int guessIndex = 0; guessIndex < guesses.size(); guessIndex++) {
            Guess guess = guesses.get(guessIndex);
            rows[guessIndex + 1] = new String[] { guess.getGuess(),
                    String.valueOf(guess.getExactMatches()),
                    String.valueOf(guess.getPartialMatches()),
                    String.valueOf(guess.getAttemptsRemaining())
            };
        }

        // Populate columnsWidths
        final int[] columnsWidths = new int[COLUMNS_COUNT];
        for (int columnIndex = 0; columnIndex < COLUMNS_COUNT; columnIndex++) {
            int maxColumnWidth = 0;
            for (int rowIndex = 0; rowIndex < ROWS_COUNT; rowIndex++) {
                int cellLength = rows[rowIndex][columnIndex].length();
                if (cellLength > maxColumnWidth)
                    maxColumnWidth = cellLength;
            }
            columnsWidths[columnIndex] = maxColumnWidth;
        }

        System.out.print(Ansi.CLEAR);
        printTopAndBottomBorder(
                columnsWidths,
                CHAR_CORNER_UPPER_LEFT,
                CHAR_CORNER_UPPER_RIGHT,
                CHAR_COLUMN_UPPER,
                CHAR_BORDER_SIDE_H);

        for (int rowIndex = 0; rowIndex < ROWS_COUNT; rowIndex++) {
            // Print a data row
            for (int columnIndex = 0; columnIndex < COLUMNS_COUNT; columnIndex++) {
                if (columnIndex == 0)
                    System.out.print(CHAR_BORDER_SIDE_V + " ");
                else
                    System.out.print(CHAR_COLUMN_DIVIDER + " ");

                String cell = rows[rowIndex][columnIndex];
                final int rightPaddingCount = columnsWidths[columnIndex] + 1 - cell.length(); // Should be before
                                                                                              // coloring

                // Color guess text, ignoring the header and other columns
                if (settings.getCodeType() == CodeType.COLOR && columnIndex == 0 && rowIndex != 0)
                    cell = colorColorCode(cell);
                else if (columnIndex == 3 && rowIndex != 0 && Integer.valueOf(cell) <= 3)
                    cell = Ansi.INVERT + (Ansi.RED + cell) + Ansi.RESET;

                // int columnWidth = columnsWidths[columnIndex] + 1;
                // System.out.printf("%-" + columnWidth + "s", cell);
                System.out.print(cell);
                System.out.print(" ".repeat(rightPaddingCount));
            }
            System.out.println(CHAR_BORDER_SIDE_V);

            // Print divider between rows (as long as it's not the last row)
            if (rowIndex != ROWS_COUNT - 1) {
                printTopAndBottomBorder(columnsWidths, CHAR_ROW_DIVIDER_LEFT, CHAR_ROW_DIVIDER_RIGHT,
                        CHAR_ROW_DIVIDER_COLUMN, CHAR_ROW_DIVIDER_SIDE);
            }
        }

        printTopAndBottomBorder(
                columnsWidths,
                CHAR_CORNER_LOWER_LEFT,
                CHAR_CORNER_LOWER_RIGHT,
                CHAR_COLUMN_LOWER,
                CHAR_BORDER_SIDE_H);
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

    @Override
    public void printReminder(String rangeLabel) {
        if (settings.getCodeType() == CodeType.COLOR)
            rangeLabel = colorColorCode(rangeLabel);
        System.out.println(Ansi.DIM + "code is made of: " + rangeLabel + Ansi.RESET);
    }

}
