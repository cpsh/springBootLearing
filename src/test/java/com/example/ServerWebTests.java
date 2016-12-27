package com.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by shichp on 2016/12/27.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringApplicationConfiguration(SpringbootDemoApplication.class)
//@WebIntegrationTest(randomPort = true)
@WebIntegrationTest
public class ServerWebTests {
    // private static FirefoxDriver browser;
    private static FirefoxDriver fx_browser;
    private static ChromeDriver chm_browser;

    /*
    //用随机端口启动
    @Value("${local.server.port}")
    private int port;
*/
    //注入端口号
    @BeforeClass
    public static void openBrowser() {
        // 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
        System.setProperty("webdriver.firefox.bin", "E:\\software\\tools\\Mozilla Firefox\\firefox.exe");
        fx_browser = new FirefoxDriver();
        fx_browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        /*
        // 如果你的 chrome 没有安装在默认目录，那么必须在程序中设置
        System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
        chm_browser = new ChromeDriver();
        chm_browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        */
    }

    //关闭浏览器
    @AfterClass
    public static void closeBrowser() {
        fx_browser.quit();
        //chm_browser.quit();
    }


    @Test
    public void addBookToEmptyList() {
        //String baseUrl = "http://localhost:" + port + "/readingList/cpsh";
        String baseUrl = "http://localhost:8080/readingList/cpsh";

        System.out.println("baseURL : " + baseUrl);

        fx_browser.get(baseUrl);
        assertEquals("You have no books in your book list", fx_browser.findElementByTagName("div").getText());

        fx_browser.findElementByName("title")
                .sendKeys("BOOK TITLE");
        fx_browser.findElementByName("author")
                .sendKeys("BOOK AUTHOR");
        fx_browser.findElementByName("isbn")
                .sendKeys("1234567890");
        fx_browser.findElementByName("description")
                .sendKeys("DESCRIPTION");
        fx_browser.findElementByTagName("form")
                .submit();
        WebElement dl =
                fx_browser.findElementByCssSelector("dt.bookHeadline");
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)",
                dl.getText());
        WebElement dt =
                fx_browser.findElementByCssSelector("dd.bookDescription");
        assertEquals("DESCRIPTION", dt.getText());


    }
}
