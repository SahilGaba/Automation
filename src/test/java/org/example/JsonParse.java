package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Response {
    public String getResponseId() {
        return responseId;
    }

    public List<String> getServices() {
        return services;
    }

    public String getDecision() {
        return decision;
    }

    public String getCvcResponse() {
        return cvcResponse;
    }

    public String getTokenRequestorId() {
        return tokenRequestorId;
    }

    public List<ActivationMethod> getActivationMethods() {
        return activationMethods;
    }

    private String responseId;
    private List<String> services;
    private String decision;
    private String cvcResponse;
    private String tokenRequestorId;
    private List<ActivationMethod> activationMethods;

}

class ActivationMethod {
    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    private String type;
    private String value;

}

public class JsonParse {
    public String removeCommaStartEnd(String str) {
        int length = str.length();
        // Remove leading comma
        str = (length > 0 && str.charAt(0) == ',' ? str.substring(1) : str);
        // Remove trailing comma
        return (length > 0 && str.charAt(length - 1) == ',' ? str.substring(0, length - 1) : str);
    }

    public void actualVSmatch(List<String> actualList, List<String> fieldsExpectedStringList) {
        //List<String> temp1 = new ArrayList<>(actualList);
        //List<String> temp2 = new ArrayList<>(fieldsExpectedStringList);
        boolean flag=true;

        for (String item : fieldsExpectedStringList) {
            if (!actualList.contains(item)) {
                System.out.println(item);
                flag=false;
                break;
            }
        }
        if (!flag){
            System.out.println("Not Matched!");
        }
        else
            System.out.println("Matched!");
    }

    @Test
    public void jsonParse() throws JsonProcessingException {
        // Assuming you have the Jackson ObjectMapper dependency added

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
        String jsonStringDAC = "{ \"responseId\":\"AUTOMATION1\" }";

        String fieldsRequiredString = "responseId,services,decision,cvcResponse,tokenRequestorId,activationMethods";

        String fieldsExpectedStringRAM = "AUTOMATION1, [DIGITIZATION], REQUIRE_ADDITIONAL_AUTHENTICATION, MATCH, 50110030273, [CARDHOLDER_TO_CALL_MANNED_NUMBER, TEXT_TO_CARDHOLDER_NUMBER]";
        String fieldsExpectedStringAS = "AUTOMATION1, [DIGITIZATION], REQUIRE_ADDITIONAL_AUTHENTICATION, MATCH, 50110030273";
        String fieldsExpectedStringDAC = "AUTOMATION1";

        String[] fieldsRequiredStringArray = fieldsRequiredString.split("\\s*,\\s*");

        List<String> fieldsRequiredStringList = Arrays.asList(fieldsRequiredStringArray);
        /*List<String> fieldsRequiredStringList = new ArrayList<>();
        for (String element : fieldsRequiredStringArray) {
            fieldsRequiredStringList.add(element);
        }*/
        System.out.println("fieldsRequiredStringList: " + fieldsRequiredStringList.size() + " : " + fieldsRequiredStringList);

        List<String> fieldsExpectedStringList = new ArrayList<>();
        StringBuffer sb = new StringBuffer(); //fieldsExpectedStringArray

        String[] fieldsExpectedStringArray = fieldsExpectedStringAS.split("\\[");

        if (fieldsExpectedStringArray.length == 1 && !fieldsExpectedStringAS.contains(",")) {
            fieldsExpectedStringList.add(fieldsExpectedStringAS);
        } else {//if (fieldsExpectedStringArray.length>1) {
            for (String s : fieldsExpectedStringArray) {
                String temp;
                if (s.contains("]")) {
                    temp = s.trim().replaceAll("]", "");
                } else {
                    temp = s.trim();
                }
                sb.append(temp);
            }

            String[] s1 = String.valueOf(sb).split(",");
            for (String s : s1) {
                fieldsExpectedStringList.add(s.trim());
            }
        }
        System.out.println("fieldsExpectedStringList: " + fieldsExpectedStringList.size() + " : " + fieldsExpectedStringList);

        ObjectMapper mapper = new ObjectMapper();
        Response response = mapper.readValue(jsonStringAS, Response.class);

        List<String> actualList = new ArrayList<>();

// Accessing top-level fields
        String responseId = response.getResponseId();
        List<String> services = response.getServices();
        String decision = response.getDecision();
        String cvcResponse = response.getCvcResponse();
        String tokenRequestorId = response.getTokenRequestorId();

        for (String item : fieldsRequiredStringList) {

            if (item.equalsIgnoreCase("responseId")) {
                if (responseId == null) {
                    actualList.add(null);
                } else {
                    actualList.add(responseId);
                }
            }

            if (item.equalsIgnoreCase("services")) {
                if (services == null) {
                    actualList.add(null);
                } else {
                    for (String service : services) {
                        actualList.add(service);
                    }
                }
            }

            if (item.equalsIgnoreCase("decision")) {
                if (decision == null) {
                    actualList.add(null);
                } else {
                    actualList.add(decision);
                }
            }

            if (item.equalsIgnoreCase("cvcResponse")) {
                if (cvcResponse == null) {
                    actualList.add(null);
                } else {
                    actualList.add(cvcResponse);
                }
            }

            if (item.equalsIgnoreCase("tokenRequestorId")) {
                if (tokenRequestorId == null) {
                    actualList.add(null);
                } else {
                    actualList.add(tokenRequestorId);
                }
            }

            List<ActivationMethod> activationMethods = response.getActivationMethods();
            if (item.equalsIgnoreCase("activationMethods")) {
                if (activationMethods == null) {
                    actualList.add(null);
                } else {
                    for (ActivationMethod method : activationMethods) {
                        String type = method.getType();
                        String value = method.getValue();
                        actualList.add(type);
                        actualList.add(value);
                    }
                }
            }
        }

        System.out.println("actualList: " + actualList.size() + " : " + actualList);
        actualVSmatch(actualList, fieldsExpectedStringList);
    }
}
