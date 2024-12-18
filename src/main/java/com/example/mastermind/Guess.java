package com.example.mastermind;

class Guess {
  private final String guess;
  private final int attemptsRemaining;
  private final int exactMatches;
  private final int partialMatches;

  Guess(final String guess, final String answer, final int attemptsRemaining) {
    this.guess = guess;
    this.attemptsRemaining = attemptsRemaining;
    this.exactMatches = calcExactMatches(guess, answer);
    this.partialMatches = calcPartialMatches(guess, answer);
  }

  String getGuess() {
    return guess;
  }

  int getExactMatches() {
    return exactMatches;
  }

  int getPartialMatches() {
    return partialMatches;
  }

  int getAttemptsRemaining() {
    return attemptsRemaining;
  }

  private final int calcExactMatches(final String guess, final String answer) {
    int exactMatches = 0;
    for (int i = 0; i < answer.length(); i++) {
      if (guess.charAt(i) == answer.charAt(i)) {
        exactMatches++;
      }
    }
    return exactMatches;
  }

  private final int calcPartialMatches(final String guess, final String answer) {
    final char[] guessList = guess.toCharArray();

    int commonElementsCount = 0;
    for (final char answerChar : answer.toCharArray()) {
      for (int j = 0; j < answer.length(); j++) {
        final char guessChar = guessList[j];
        if (guessChar == answerChar) {
          guessList[j] = ' ';
          commonElementsCount++;
          break;
        }
      }
    }
    return commonElementsCount - exactMatches;
  }
}
