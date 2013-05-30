package com.taitooz.app;

/**This class represents the element Scissors.**/
public class Scissors extends Element {

  /**{@inheritDoc}**/
  @Override
  Result compareWith(final Element theElement) {
    return theElement.compareWithScissors();
  }

  /**{@inheritDoc}**/
  @Override
  Result compareWithStone() {
    return Result.LOSE;
  }

  /**{@inheritDoc}**/
  @Override
  Result compareWithScissors() {
    return Result.DRAW;
  }

  /**{@inheritDoc}**/
  @Override
  Result compareWithPaper() {
    return Result.WIN;
  }
}
