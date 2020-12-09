package com.kopuz.scalableweb.scalableweb.service;

import com.kopuz.scalableweb.scalableweb.entity.BinaryData;
import com.kopuz.scalableweb.scalableweb.entity.NonEqualData;
import com.kopuz.scalableweb.scalableweb.entity.ResultOfDifference;
import com.kopuz.scalableweb.scalableweb.enums.Side;
import com.kopuz.scalableweb.scalableweb.exception.NoRecordFoundException;
import com.kopuz.scalableweb.scalableweb.util.FileHelper;
import com.kopuz.scalableweb.scalableweb.util.JSONHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * ScalableWebService has business logic which finds the difference between file and writes the
 * decoded data into the fles
 */
@Service
public class ScalableWebService {

  /**
   * Finds the difference between the left file and right file and shows the differences in offset
   * and length type to mark the difference between the files
   *
   * @param id
   * @return
   * @throws IOException
   */
  public ResultOfDifference findDifference(Long id) throws IOException {

    // check if the left side is null
    byte[] leftByteArray = FileHelper.readFile(id, Side.LEFT);

    /** Checks if the left file is empty or not. */
    if (leftByteArray == null || leftByteArray.length == 0) {
      throw new NoRecordFoundException(
          "Left File is file. Therefore application can not go on working!");
    }

    byte[] rightByteArray = FileHelper.readFile(id, Side.RIGHT);

    /** Checks if the right file is empty or not */
    if (rightByteArray == null || rightByteArray.length == 0) {
      throw new NoRecordFoundException(
          "Right File is file. Therefore application can not go on working!");
    }

    /**
     * Checks if the size of the left file is equal to othe size of the right file in terms of
     * encoded ByteArray
     */
    if (leftByteArray.length != rightByteArray.length) {
      return ResultOfDifference.builder()
          .equal(false)
          .equalSize(false)
          .nonEqualDataList(new ArrayList<>())
          .build();
    }

    List<NonEqualData> nonEqualDataList = exploreNonEqualDataOfBytes(leftByteArray, rightByteArray);

    /**
     * Checks if there is any difference between the files. If there is then reports the differences
     * in offset and length
     */
    if (nonEqualDataList.size() != 0) {
      return ResultOfDifference.builder()
          .equal(false)
          .equalSize(true)
          .nonEqualDataList(nonEqualDataList)
          .build();
    } else {
      return ResultOfDifference.builder()
          .equal(true)
          .equalSize(true)
          .nonEqualDataList(nonEqualDataList)
          .build();
    }
  }

  /**
   * Finding the difference of the left encoded byte array and right encoded byte array
   *
   * @param leftArray
   * @param rightArray
   * @return
   */
  private List<NonEqualData> exploreNonEqualDataOfBytes(byte[] leftArray, byte[] rightArray) {

    List<NonEqualData> nonEqualDataList = new ArrayList<>();
    int length = 0;
    int offSet = 0;

    for (int index = 0; index < rightArray.length; index++) {

      if (leftArray[index] == rightArray[index]) {

        if (offSet == 0) {
          continue;
        }

        nonEqualDataList.add(NonEqualData.builder().offSet(offSet).length(length).build());
        offSet = 0;
        length = 1;

      } else {

        offSet = index;
        length++;

        // for the final array which will not be able to add in the first check
        if (index == rightArray.length - 1) {
          nonEqualDataList.add(NonEqualData.builder().offSet(offSet).length(length).build());
        }
      }
    }

    return nonEqualDataList;
  }

  /**
   * Writes Binary Data into the File with the Specified Side and ID
   *
   * @param id
   * @param binaryData
   * @param left
   * @throws IOException
   */
  public void writeToFile(Long id, BinaryData binaryData, Side left) throws IOException {

    binaryData =
        BinaryData.builder()
            .id(id)
            .data(binaryData.getData())
            .dataInBytes(JSONHelper.decodeBase64String(binaryData.getData()))
            .side(left)
            .build();

    FileHelper.writetoFile(binaryData);
  }
}
