package tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProviderPracticeTest
{
    @DataProvider(name = "basicData")
    public Object[][] basicData()
    {
        return new Object[][] {{"abc@yahoo.com", "password1"},{"abc@gmail.com", "password2"}};
    }

    @Test(dataProvider = "basicData")
    public void DemoTest(String email, String password)
    {
        System.out.println(email);
        System.out.println(password);
    }

    @DataProvider(name = "HashMapData")
    public Object[][] HashMapData()
    {
        HashMap<String, String> user1 = new HashMap<>();
        user1.put("email", "abc@yahoo.com");
        user1.put("password", "Password1");

        HashMap<String, String> user2 = new HashMap<>();
        user2.put("email", "abc@gmail.com");
        user2.put("password", "Password2");


        return new Object[][] {{user1},{user2}};
    }

    @Test(dataProvider =  "HashMapData")
    public void TestHashMapData(HashMap<String, String> user)
    {
        System.out.println(user.get("email"));
        System.out.println(user.get("password"));
    }

    @DataProvider(name = "getJsonData")
    public Object[][] getJsonData() throws IOException {
        // Define the path to the JSON file
        File jsonFile = new File("src/test/resources/testData_tc1.json");

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

    @Test(dataProvider = "getJsonData", groups = "smoke")
    public void TestJsonData(HashMap<String, String> user)
    {
        System.out.println(user.get("email"));
        System.out.println(user.get("password"));
    }
}
