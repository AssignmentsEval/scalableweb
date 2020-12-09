package com.kopuz.scalableweb.scalableweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Displays offset and length of the non equal data of byte array which is used to display it after
 * the get request of nonEqualData in json format
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NonEqualData {

  @JsonProperty("length")
  private int length;

  @JsonProperty("offSet")
  private int offSet;
}
