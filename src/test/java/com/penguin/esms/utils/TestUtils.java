package com.penguin.esms.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import com.penguin.esms.components.staff.Role;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.utils.TestCase;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TestUtils {

    public static List<TestCase> readTestDataFromCsv(String filePath, List<String> input, List<String> expected) throws IOException {
        List<TestCase> testCases = new ArrayList<>();

        String fileName = filePath;
        String currentFolderPath = System.getProperty("user.dir"); // Get the current working directory

        String fullFilePath = Paths.get(currentFolderPath, fileName).toString();

        try (Reader reader = new FileReader(fullFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                TestCase testCase = new TestCase();
                for (String i : input)
                    testCase.getInput().put(i, csvRecord.get(i));
                for (String i : expected)
                    testCase.getExpected().put(i, csvRecord.get(i));
                testCases.add(testCase);
            }
        }

        return testCases;
    }
}
