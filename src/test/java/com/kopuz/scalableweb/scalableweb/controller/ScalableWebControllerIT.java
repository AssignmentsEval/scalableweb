package com.kopuz.scalableweb.scalableweb.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kopuz.scalableweb.scalableweb.entity.BinaryData;
import com.kopuz.scalableweb.scalableweb.enums.Side;
import com.kopuz.scalableweb.scalableweb.service.ScalableWebService;
import com.kopuz.scalableweb.scalableweb.util.FileHelper;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScalableWebControllerIT {

  @Autowired private MockMvc mvc;

  private String jsonParsedData;

  @Before
  public void setUp() throws JsonProcessingException {

    BinaryData binaryData = BinaryData.builder().data("SmF2YWNvZGVnZWVrcw==").build();
    ObjectMapper mapper = new ObjectMapper();
    jsonParsedData = mapper.writeValueAsString(binaryData);
  }

  @AfterClass
  public static void tearDown() {

  }

  @Test
  public void putNewDataFileForRight() throws Exception {

    mvc.perform(
            MockMvcRequestBuilders.post("/v1/diff/123/right")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParsedData))
        .andExpect(status().isOk())
        .andReturn();

    byte[] fileInBytes =
        FileHelper.readFile(new Long("123"), Side.RIGHT);
    assertNotNull(fileInBytes);
  }

  @Test
  public void putNewDataFileForLeft() throws Exception {

    mvc.perform(
            MockMvcRequestBuilders.post("/v1/diff/123/left")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParsedData))
        .andExpect(status().isOk())
        .andReturn();

    byte[] fileInBytes = FileHelper.readFile(new Long("123"), Side.LEFT);
    assertNotNull(fileInBytes);
  }

  @Test
  public void resultOfDifference() throws Exception {

    MvcResult result =
        mvc.perform(
                MockMvcRequestBuilders.get("/v1/diff/123")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonParsedData))
            .andExpect(status().isOk())
            .andReturn();

    String resultOfDifference = result.getResponse().getContentAsString();
    assertEquals(
        resultOfDifference,
        "{\"equal\":true,\"equalsize\":true,\"nonEqualDataList\":[]}");
  }
}
