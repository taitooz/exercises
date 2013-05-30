package com.taitooz.app;

import junit.framework.TestCase;

/**
 * Unit test for this simple game.
 */
public class AppTest extends TestCase {
  /**Player one a two with the elements stone.**/
  public Stone pOneStone = new Stone();
  public Stone pTwoStone = new Stone();

  /**Player one a two with the elements Scissors.**/
  public Scissors pOneScissors = new Scissors();
  public Scissors pTwoScissors = new Scissors();

  /**Player one a two with the elements Paper.**/
  public Paper pOnePaper = new Paper();
  public Paper pTwoPaper = new Paper();

  /**Test the draws.**/
  public void testDraws()  {
    assertEquals(Result.DRAW, pOnePaper.compareWith(pTwoPaper));
    assertEquals(Result.DRAW, pOneScissors.compareWith(pTwoScissors));
    assertEquals(Result.DRAW, pOneStone.compareWith(pTwoStone));
  }

  /**@ All results are based on the view of the second player. @
   * e.g.
   * P1->STONE  AGAINST  P2->SCISSORS = LOSE P2
   * P1->STONE  AGAINST  P2->PAPER    = WIN P2
   **/

  /**Test the Stone**/
  public void testStone() {
    assertEquals(Result.LOSE, pOneStone.compareWith(pTwoScissors));
    assertEquals(Result.WIN, pOneStone.compareWith(pTwoPaper));
  }

  /**Test the Paper.**/
  public void testPaper() {
    assertEquals(Result.LOSE, pOnePaper.compareWith(pTwoStone));
    assertEquals(Result.WIN, pOnePaper.compareWith(pTwoScissors));
  }

  /**Test the Scissors.**/
  public void testScissors() {
    assertEquals(Result.LOSE, pOneScissors.compareWith(pTwoPaper));
    assertEquals(Result.WIN, pOneScissors.compareWith(pTwoStone));
  }

}
