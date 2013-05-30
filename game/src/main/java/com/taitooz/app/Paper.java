package com.taitooz.app;

/**This class represents the element Paper.**/
public class Paper extends Element {

  /**{@inheritDoc}**/
  @Override
  Result compareWith(final Element theElement) {
    return theElement.compareWithPaper();
  }

  /**{@inheritDoc}**/
  @Override
  Result compareWithStone() {
    return Result.WIN;
  }

  /**{@inheritDoc}**/
  @Override
  Result compareWithScissors() {
    return Result.LOSE;
  }

  /**{@inheritDoc}**/
  @Override
  Result compareWithPaper() {
    return Result.DRAW;
  }
}
