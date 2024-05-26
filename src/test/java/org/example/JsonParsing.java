package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonParsing {
    String jsonStringRAM = "{\n" +
            "   \"responseId\": \"AUTOMATION1\",\n" +
            "   \"services\": [\"DIGITIZATION\"],\n" +
            "   \"decision\": \"REQUIRE_ADDITIONAL_AUTHENTICATION\",\n" +
            "   \"cvcResponse\": \"MATCH\",\n" +
            "   \"tokenRequestorId\": \"50110030273\",\n" +
            "   \"activationMethods\":    [\n" +
            "            {\n" +
            "         \"type\": \"CARDHOLDER_TO_USE_MOBILE_APP\",\n" +
            "         \"value\": \"Royal Bank Of Scotland\"\n" +
            "      },\n" +
            "            {\n" +
            "         \"type\": \"TEXT_TO_CARDHOLDER_NUMBER\",\n" +
            "         \"value\": \"*******6995\"\n" +
            "      },\n" +
            "            {\n" +
            "         \"type\": \"CARDHOLDER_TO_CALL_MANNED_NUMBER\",\n" +
            "         \"value\": \"0345 307 3336\"\n" +
            "      }\n" +
            "   ]\n" +
            "}";

    String jsonStringAS = "{ \"responseId\":\"AUTOMATION1\", \"services\":[ \"DIGITIZATION\" ], \"decision\":\"REQUIRE_ADDITIONAL_AUTHENTICATION\", \"cvcResponse\":\"MATCH\", \"tokenRequestorId\":\"50110030273\" }";
    String jsonStringDAC= "{ \"responseId\":\"AUTOMATION1\" }";

    String fieldsRequiredStringRAM = "responseId,services,decision,cvcResponse,tokenRequestorId,activationMethods";
    String fieldsRequiredStringAS = "responseId     ,    decision, cvcResponse, tokenRequestorId , services";
    String fieldsRequiredStringDAC = "responseId";

    String fieldsExpectedStringRAM = "AUTOMATION1, [DIGITIZATION], REQUIRE_ADDITIONAL_AUTHENTICATION, MATCH, 50110030273, [CARDHOLDER_TO_CALL_MANNED_NUMBER, TEXT_TO_CARDHOLDER_NUMBER]";
    String fieldsExpectedStringAS = "AUTOMATION1, [DIGITIZATION], REQUIRE_ADDITIONAL_AUTHENTICATION, MATCH, 50110030273";
    String fieldsExpectedStringDAC = "AUTOMATION1";

    public String removeCommaStartEnd(String str) {
        int length = str.length();
        // Remove leading comma
        str = (length > 0 && str.charAt(0) == ',' ? str.substring(1) : str);
        // Remove trailing comma
        return (length > 0 && str.charAt(length - 1) == ',' ? str.substring(0, length - 1) : str);
    }

    public void actualVSmatch(List<String> actualList1, List<String> fieldsExpectedStringList) {
        //List<String> temp1 = new ArrayList<>(actualList1);
        //List<String> temp2 = new ArrayList<>(fieldsExpectedStringList);

        for (String item : fieldsExpectedStringList) {
            if (actualList1.contains(item)) {
                System.out.println("Yes="+item);
            }
            else {
                System.out.println("No="+item);
                break;
            }
        }
    }

    public boolean arrayInJson(String s){
        boolean isMatch = s.length() >= 2 &&
                s.charAt(0) == '[' &&
                Character.isLetter(s.charAt(1));
        //System.out.println("isMatch=="+isMatch);
        return isMatch;
    }

    public boolean arrayOfJson(String s){
        boolean isMatch = s.length() >= 2 &&
                s.charAt(0) == '[' &&
                s.charAt(1) == '{';
        //System.out.println("isMatch1=="+isMatch);
        return isMatch;
    }

    @Test
    public void jsonParsing() throws IOException {
        // Access each field and print its value
        /*for (Map.Entry<String, Object> entry : jsonData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.equalsIgnoreCase(field))
                System.out.println(key + ": " + value);
        }*/

        String[] fieldsRequiredStringArray = fieldsRequiredStringRAM.split("\\s*,\\s*");

        System.out.println("fieldsRequiredStringArray: " + fieldsRequiredStringArray.length + " : "
                + Arrays.toString(fieldsRequiredStringArray));

        List<String> activationMethodsList = new ArrayList<>();
        List<String> actualList = new ArrayList<>();
        List<String> actualListFinal = new ArrayList<>();

        List<String> fieldsExpectedStringList = new ArrayList<>();
        StringBuffer sb = new StringBuffer(); //fieldsExpectedStringArray
        StringBuffer sb1 = new StringBuffer(); //filedsActual

        String[] fieldsExpectedStringArray = fieldsExpectedStringRAM.split("\\[");
        for (String s : fieldsExpectedStringArray) {
            String temp;
            if (s.contains("]")) {
                temp = removeCommaStartEnd(s.trim()).replaceAll("]", "");
            } else {
                temp = removeCommaStartEnd(s.trim());
            }
            sb.append(temp + ",");
        }
        String[] s1 = removeCommaStartEnd(String.valueOf(sb)).split(",");
        for (String s : s1) {
            fieldsExpectedStringList.add(s.trim());
        }

        System.out.println("fieldsExpectedStringList: " + fieldsExpectedStringList.size() + " : " + fieldsExpectedStringList);

        ObjectMapper mapper = new ObjectMapper();

        // Parse the JSON string into a Map
        Map<String, Object> jsonData = mapper.readValue(jsonStringRAM, Map.class);
        int i;
        for (i = 0; i < fieldsRequiredStringArray.length; i++) {
            //if (fieldsRequiredStringArray[i].trim().equalsIgnoreCase("activationMethods")) {
            //System.out.println("fieldsRequiredStringArray[i].trim()=="+i+"=="+fieldsRequiredStringArray[i].trim());
            //System.out.println("jsonData.get(fieldsRequiredStringArray[i].trim())=="+i+"=="+jsonData.get(fieldsRequiredStringArray[i].trim()));

            if (arrayOfJson(jsonData.get(fieldsRequiredStringArray[i].trim()).toString().trim())) {
                // Get the activation methods list
                List<Map<String, Object>> activationMethodsMapList = (List<Map<String, Object>>) jsonData.get(fieldsRequiredStringArray[i].trim());//jsonData.get("activationMethods");
                if (!activationMethodsMapList.isEmpty()) {
                    // Access each activation method and its "type" field
                    for (Map<String, Object> method : activationMethodsMapList) {
                        String type = (String) method.get("type");
                        String value = (String) method.get("value");

                        activationMethodsList.add(type);
                        sb1.append(type.trim() + ",");
                    }
                    actualList.add(activationMethodsList.toString());
                }
            } //else if (fieldsRequiredStringArray[i].trim().equalsIgnoreCase("services")) {
            else if (arrayInJson(jsonData.get(fieldsRequiredStringArray[i].trim()).toString())) {
                List<String> servicesList = (List<String>) jsonData.get(fieldsRequiredStringArray[i].trim());//jsonData.get("services");
                actualList.add(String.valueOf(servicesList));
                for (String item : servicesList)
                    sb1.append(item.trim() + ",");
            } else {
                sb1.append(jsonData.get(fieldsRequiredStringArray[i].trim()).toString().trim() + ",");
                actualList.add(jsonData.get(fieldsRequiredStringArray[i].trim()).toString());
            }
            //System.out.println("sb1=="+sb1);
        }

        String[] s2 = removeCommaStartEnd(String.valueOf(sb1)).split(",");
        for (String s : s2) {
            actualListFinal.add(s.trim());
        }

        System.out.println("actualListFinal: " + actualListFinal.size() + " : " + actualListFinal);

        //System.out.println("\nfieldsExpectedString: " + fieldsExpectedString.length() + " : " + fieldsExpectedString);

        actualVSmatch(actualListFinal, fieldsExpectedStringList);
    }
}
