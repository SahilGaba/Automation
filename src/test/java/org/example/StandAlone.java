package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Map;


public class StandAlone extends TestBase{

    @Test
    public void restPost() {
        baseURI = "https://reqres.in";
        Response res = given().body(new File("body.json"))
                .when().post("/api/register")
                .then().extract().response();
        System.out.println(res.statusCode() + " <> " + res.asString());
    }

    @Test
    public void Sel() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://google.com");

        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void rough() {

    }
    }
