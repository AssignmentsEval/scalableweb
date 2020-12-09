package com.kopuz.scalableweb.scalableweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result of the Difference POJO class which is the result of the differences between files and
 * annotated with JSONProperty to show the results as a response of POST Request
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultOfDifference {

  @JsonProperty("equal")
  private boolean equal;

  @JsonProperty("equalsize")
  private boolean equalSize;

  @JsonProperty("nonEqualDataList")
  private List<NonEqualData> nonEqualDataList;
}
