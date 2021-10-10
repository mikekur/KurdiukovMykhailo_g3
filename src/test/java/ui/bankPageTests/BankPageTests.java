package ui.bankPageTests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pages.BankPage;
import ui.pages.MainPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BankPageTests {

    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public MainPage mainPage;
    public BankPage bankPage;

    @Before
    public void setUp(){
        logger = Logger.getLogger(getClass());
        File fileChromeDriver = new File("./drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());
        webDriver = new ChromeDriver();
        logger.info("Get chromeDriver");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("browser was opened");
        mainPage = new MainPage(webDriver);
        bankPage = new BankPage(webDriver);
        bankPage.openUrl("https://finance.i.ua/converter/");
    }

    @After
    public void tearDown() throws Exception{
        webDriver.quit();
        logger.info("test ended, browser closed");
    }


    @Test
    public void testWithMinusValue(){
        logger.info("test for minus value started");
        bankPage.changeCurrencyToUSD();
        bankPage.setCurrencyAmount(-1);
        assertNotEquals("0.0",bankPage.getValueUAH());
    }

    @Test
    public void testForZeroValue(){
        logger.info("test for zero value started");
        bankPage.changeCurrencyToEUR();
        bankPage.setCurrencyAmount(0);
        assertEquals("0.0", bankPage.getValueRub());
    }

    @Test
    public void testForCurrencyChanging(){
        logger.info("test for currency changing started");
        bankPage.changeCurrencyToUAH();
        bankPage.setCurrencyAmount(10);
        String oldRubValue = bankPage.getValueRub();
        bankPage.changeCurrencyToEUR();
        String newRubValue = bankPage.getValueRub();
        assertNotEquals(oldRubValue,newRubValue);
    }


}
