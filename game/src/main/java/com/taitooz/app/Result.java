package com.taitooz.app;

/**This enum represents the Result of the match.**/
public enum Result {

  WIN("Win"),

  LOSE("Lose"),

  DRAW("Draw");

  private String message;

  /** Constructor
   * @param theMessage
   */
  private Result(final String theMessage) {
    message = theMessage;
  }

  public String getMessage() {
    return message;
  }
}
