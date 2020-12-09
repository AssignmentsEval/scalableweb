package com.kopuz.scalableweb.scalableweb.exception;

public class NoRecordFoundException extends RuntimeException {

  /**
   * Exception class which gives message No Record Found for the specified ID
   *
   * @param message
   */
  public NoRecordFoundException(final String message) {
    super(message);
  }
}
