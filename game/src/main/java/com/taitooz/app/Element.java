package com.taitooz.app;

/**
 * Abstract class to represents the behaviors of the Elements.
 */
public abstract class Element {

  /**This method compare a element with the element in the representation of
   * the generic class.**/
  abstract Result compareWith(final Element theElement);

  /**The double dispach compare with stone.**/
  abstract Result compareWithStone();

  /**The double dispach compare with Scissors.**/
  abstract Result compareWithScissors();

  /**The double dispach compare with Paper.**/
  abstract Result compareWithPaper();

}
