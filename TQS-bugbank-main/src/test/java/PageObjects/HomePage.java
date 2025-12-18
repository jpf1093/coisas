package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    public WebElement getConta() {

        return driver.findElement(By.id("textAccountNumber"));
    }

    public WebElement getSaldoConta() {

        return driver.findElement(By.xpath("//p[@id='textBalance']/span"));
    }

    public WebElement getTransferirButton() {

        return driver.findElement(By.xpath("(//div[starts-with(@class, 'home__ContainerButton-sc-')])[1]/a"));
    }

    public WebElement getSairButton() {

        return driver.findElement(By.id("btnExit"));
    }

    public WebElement getExtratoButton() {

        return driver.findElement(By.id("btn-EXTRATO"));
    }
}
