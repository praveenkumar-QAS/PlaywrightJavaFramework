package utils;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DataProviderUtil
{
    public static Object[][] getJsonDataToMap(String filePath)
    {
        // Define the path to the JSON file
        File jsonFile = new File(filePath);

        // Use Jackson ObjectMapper to read the file
        ObjectMapper mapper = new ObjectMapper();

        // Deserialize the JSON array into a List of Maps
        List<Map<String, String>> data = mapper.readValue(
                jsonFile,
                new TypeReference<List<Map<String, String>>>() {}
        );

        // Convert List<Map> into Object[][] for TestNG
        Object[][] testData = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i);
        }

        return testData;
    }
}
