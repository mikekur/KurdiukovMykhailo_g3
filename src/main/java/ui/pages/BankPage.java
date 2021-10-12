package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class BankPage extends MainPage {


    public BankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//input[@id='currency_amount']")
    public WebElement currencyAmount;

    @FindBy(xpath = "//select[@id='converter_currency']")
    public WebElement converterCurrency;

    @FindBy(xpath = "//p[@id='RUB']//input[@id='currency_exchange']")
    public WebElement convertedAmountRUB;

    @FindBy(xpath = "//p[@id='EUR']//input[@id='currency_exchange']")
    public WebElement convertedAmountEUR;

    @FindBy(xpath = "//p[@id='USD']//input[@id='currency_exchange']")
    public WebElement convertedAmountUSD;

    @FindBy(xpath = "//p[@id='UAH']//input[@id='currency_exchange']")
    public WebElement convertedAmountUAH;

    @FindBy(xpath = "//p[@id='PLN']//input[@id='currency_exchange']")
    public WebElement convertedAmountPLN;

    public Select createDropDownSelect(WebElement dropDownSelect){
        return new Select(dropDownSelect);
    }
    public void setCurrencyAmount(double amount){
        currencyAmount.clear();
        currencyAmount.sendKeys(String.valueOf(amount));
    }

    public void changeCurrencyToRub(){
       Select currencyDropDown = createDropDownSelect(converterCurrency);
       currencyDropDown.selectByValue("RUB");
    }
    public void changeCurrencyToUAH(){
        Select currencyDropDown = createDropDownSelect(converterCurrency);
        currencyDropDown.selectByValue("UAH");
    }
    public void changeCurrencyToUSD(){
        Select currencyDropDown = createDropDownSelect(converterCurrency);
        currencyDropDown.selectByValue("USD");
    }
    public void changeCurrencyToEUR(){
        Select currencyDropDown = createDropDownSelect(converterCurrency);
        currencyDropDown.selectByValue("EUR");
    }
    public void changeCurrencyToPLN(){
        Select currencyDropDown = createDropDownSelect(converterCurrency);
        currencyDropDown.selectByValue("PLN");
    }

    public String getValueUSD(){
        return convertedAmountUSD.getAttribute("value");
    }
    public String getValueEUR(){
        return convertedAmountEUR.getAttribute("value");
    }
    public String getValueRub(){
        return convertedAmountRUB.getAttribute("value");
    }
    public String getValueUAH(){
        return convertedAmountUAH.getAttribute("value");
    }
    public String getValuePLN(){
        return convertedAmountPLN.getAttribute("value");
    }

    public void openFinancePage() {
        openUrl("https://finance.i.ua/converter/");
    }
}
