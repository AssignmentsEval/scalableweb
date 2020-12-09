package com.kopuz.scalableweb.scalableweb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.kopuz.scalableweb.scalableweb.entity.BinaryData;
import com.kopuz.scalableweb.scalableweb.entity.ResultOfDifference;
import com.kopuz.scalableweb.scalableweb.enums.Side;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ScalableWebServiceTest {

  @Autowired private ScalableWebService scalableWebService;

  @AfterClass
  public static void tearDown() {
    FileUtils.deleteQuietly(new File("files/124-LEFT"));
    FileUtils.deleteQuietly(new File("files/124-RIGHT"));
  }

  @Test
  public void testWriteToLeftFile() throws IOException {

    String binaryDataToWrite = "SmF2YWNvZGVnZWVrcw==";
    BinaryData binaryData = BinaryData.builder().data(binaryDataToWrite).build();

    scalableWebService.writeToFile(new Long(124), binaryData, Side.LEFT);
    File file = FileUtils.getFile("files/124-LEFT");
    assertEquals(file.exists(), true);
  }

  @Test
  public void testWriteToRightFile() throws IOException {

    String binaryDataToWrite = "SmF2YWNvZGVnZWVRcw==";
    BinaryData binaryData = BinaryData.builder().data(binaryDataToWrite).build();

    scalableWebService.writeToFile(new Long(124), binaryData, Side.RIGHT);
    File file = FileUtils.getFile("files/124-RIGHT");
    assertEquals(file.exists(), true);
  }

  @Test
  public void testFindDifference() throws IOException {

    ResultOfDifference findDifference = scalableWebService.findDifference(new Long("124"));
    assertNotNull(findDifference.getNonEqualDataList());
    assertEquals(findDifference.isEqual(), false);
    assertEquals(findDifference.isEqualSize(), true);
    assertEquals(findDifference.getNonEqualDataList().get(0).getLength(), 1);
    assertEquals(findDifference.getNonEqualDataList().get(0).getOffSet(), 11);
  }
}
