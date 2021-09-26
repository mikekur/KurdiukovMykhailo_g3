package org.example;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Logger logger = Logger.getLogger(getClass());;
    WebDriver webDriver;

    @Before
    public void setUp(){
        File fileChromeDriver = new File("./drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());
        webDriver = new ChromeDriver();
        logger.info("ChromeDriver init");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Implicit timeout - 10 seconds");
        webDriver.get("https://logging.apache.org/log4j/2.x/");
        logger.info("Browser opened");
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Chrome closed(quit command)");
    }

    @Test
    public void seleniumTest() {
        logger.info("First test started");
        String exTitle = webDriver.findElement(By.xpath("//main[@class='span10']/h1")).getText();
        Assert.assertEquals("Apache Log4j 2", exTitle);
        webDriver.findElement(By.xpath("//main/section[2]/p/a[@href='manual/index.html']")).click();
        String titleText = webDriver.findElement(By.xpath("//main/section/h2")).getText();
        Assert.assertEquals("Welcome to Log4j 2!",titleText);
        webDriver.findElement(By.xpath("//header/div/div/a[@id='bannerLeft']")).click();
        String exTittleSponsor = webDriver.findElement(By.xpath("//div[@class='hero-unit']/h1")).getText();
        Assert.assertEquals("Apache logging services", exTittleSponsor);
        logger.info("First test ended");
    }

    @Test
    public void seleniumTest_2() throws InterruptedException {
        logger.info("Second test started");
        webDriver.findElement(By.xpath("//div[@id='breadcrumbs']/ul/li[7]/a")).click();
        webDriver.findElement(By.xpath("//header/nav/div/div[3]/ul/li")).click();
        WebElement updatedSearchInput = new WebDriverWait(webDriver, 5).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='search-filter-input']")));
        updatedSearchInput.clear();
        updatedSearchInput.sendKeys("Log4j");
        String searchResult = webDriver.findElement(By.xpath("//*[@id='search-result-container']/div[2]/a/div[2]/div/span")).getText();
        Assert.assertTrue(searchResult.contains("Log4j"));
        logger.info("Second test ended");
    }
}

