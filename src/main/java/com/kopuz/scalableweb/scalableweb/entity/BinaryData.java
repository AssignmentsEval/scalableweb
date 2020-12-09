package com.kopuz.scalableweb.scalableweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kopuz.scalableweb.scalableweb.enums.Side;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity which keeps the id , side, encoded data and also decoded types as dataInBytes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BinaryData {

  private Long id;

  private Side side;

  @JsonProperty("data")
  private String data;

  private byte[] dataInBytes;
}
