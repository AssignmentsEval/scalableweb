package com.kopuz.scalableweb.scalableweb.util;

import com.kopuz.scalableweb.scalableweb.entity.BinaryData;
import com.kopuz.scalableweb.scalableweb.enums.Side;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

/**
 * Helper class which is used to write to file and read from file
 */
public class FileHelper {

  /**
   * Writes Binary Data To File under the files directory of the project
   *
   * @param binaryData
   * @throws IOException
   */
  public static void writetoFile(BinaryData binaryData) throws IOException {
    FileUtils.writeByteArrayToFile(
        ResourceUtils.getFile("files/" + binaryData.getId() + "-" + binaryData.getSide()),
        binaryData.getDataInBytes());
  }

  /**
   * Reads Binary Data From File with the ID and side under the files folder of the project
   *
   * @param id
   * @param side
   * @return
   * @throws IOException
   */
  public static byte[] readFile(Long id, Side side) throws IOException {
    return FileUtils.readFileToByteArray(ResourceUtils.getFile("files/" + id + "-" + side));
  }
}
