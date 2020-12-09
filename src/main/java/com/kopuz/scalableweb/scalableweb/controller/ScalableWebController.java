package com.kopuz.scalableweb.scalableweb.controller;

import com.kopuz.scalableweb.scalableweb.entity.BinaryData;
import com.kopuz.scalableweb.scalableweb.entity.ResultOfDifference;
import com.kopuz.scalableweb.scalableweb.enums.Side;
import com.kopuz.scalableweb.scalableweb.service.ScalableWebService;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class which is used to make connection with webservices that shows the difference
 * between files and posting left side and right side files with the specified ID
 */
@RestController
public class ScalableWebController {

  private ScalableWebService scalableWebService;

  private ScalableWebController(ScalableWebService scalableWebService) {
    this.scalableWebService = scalableWebService;
  }

  /**
   * Finds the difference between the files within the id specifieid
   *
   * @param id
   * @return
   * @throws IOException
   */
  @GetMapping("//v1/diff/{id}")
  public ResultOfDifference resultOfDifference(@PathVariable("id") Long id) throws IOException {

    ResultOfDifference resultOfDifference = scalableWebService.findDifference(id);

    return resultOfDifference;
  }

  /**
   * Posts the new file and decodes the data into a flie for Left Sided Files
   *
   * @param id
   * @param binaryData
   * @throws IOException
   */
  @PostMapping("/v1/diff/{id}/left")
  public void putNewDataFileForLeft(@PathVariable("id") Long id, @RequestBody BinaryData binaryData)
      throws IOException {

    scalableWebService.writeToFile(id, binaryData, Side.LEFT);
  }

  /**
   * Posts the new file and decodes the data into a file for Right Sided Files
   *
   * @param id
   * @param binaryData
   * @throws IOException
   */
  @PostMapping("/v1/diff/{id}/right")
  public void putNewDataFileForRight(
      @PathVariable("id") Long id, @RequestBody BinaryData binaryData)
      throws IOException { // TODO you might have to fix that binary Data

    scalableWebService.writeToFile(id, binaryData, Side.RIGHT);
  }
}
