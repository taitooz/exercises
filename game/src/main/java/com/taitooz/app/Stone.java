package com.taitooz.app;

/**This class represents the element Stone.**/
public class Stone extends Element {

  /**{@inheritDoc}
   *
   * @param theElement is the element, rock, paper, scissors that will be compared.
   * @return aResult of this comparation.
   */
  @Override
  Result compareWith(final Element theElement) {
    return theElement.compareWithStone();
  }

  /**{@inheritDoc}**/
  @Override
  Result compareWithScissors() {
    return Result.WIN;
  }

  /**{@inheritDoc}**/
  @Override
  Result compareWithPaper() {
    return Result.LOSE;
  }

  /**{@inheritDoc}**/
  @Override
  Result compareWithStone() {
     return Result.DRAW;
  }
}
