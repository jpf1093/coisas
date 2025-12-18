package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExtratoPage {

    private WebDriver driver;

    public ExtratoPage (WebDriver driver){

        this.driver = driver;
    }

    public List<WebElement> getDescricao(){

        return driver.findElements(By.id("textDescription"));
    }
    
    public List<WebElement> getVlrTransf(){
        
        return driver.findElements(By.id("textTransferValue"));
    }

    public WebElement getLogOutButton() {

        return driver.findElement(By.id("btnExit"));
    }

    public WebElement getSaldo(){
        
        return driver.findElement(By.id("textBalanceAvailable"));
    }
}
