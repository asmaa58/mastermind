package com.example.mastermind;

class Guess {
  private final String guess;
  private final int exactMatches;
  private final int partialMatches;
  private final int attemptsRemaining;

  public Guess(String guess, int exactMatches, int partialMatches, int attemptsRemaining) {
    this.guess = guess;
    this.exactMatches = exactMatches;
    this.partialMatches = partialMatches;
    this.attemptsRemaining = attemptsRemaining;
  }

  public String getGuess() {
    return guess;
  }

  public int getExactMatches() {
    return exactMatches;
  }

  public int getPartialMatches() {
    return partialMatches;
  }

  public int getAttemptsRemaining() {
    return attemptsRemaining;
  }
}
