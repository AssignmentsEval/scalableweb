package com.kopuz.scalableweb.scalableweb.util;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * Helper class which decodes decodedData int
 */
public class JSONHelper {

  /**
   * Decodes encoded data into the byteArray to write it into File in decoded format
   *
   * @param encodedData
   * @return
   */
  public static byte[] decodeBase64String(String encodedData) {

    byte[] byteArray = Base64.decodeBase64(encodedData.getBytes());
    return byteArray;
  }
}
