package com.manahotel.be.auto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@WebMvcTest(AuthenticationController.class)
@SpringBootTest
public class LoginTest {
    private WebDriver driver;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java program\\SEP\\core_mana_hotel_be\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() throws InterruptedException {
        driver.get("http://localhost:3000/login");
        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/form/div[3]/button"));

        usernameInput.sendKeys("h");
        passwordInput.sendKeys("h");
        loginButton.click();
        Thread.sleep(6000);
        String expectedHomePageUrl = "http://localhost:3000/";
        Assertions.assertEquals(expectedHomePageUrl, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
