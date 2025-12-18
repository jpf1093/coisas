package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransferirPage {

    private WebDriver driver;

    public TransferirPage (WebDriver driver){

        this.driver = driver;
    }

    public WebElement getNumeroConta(){

        return driver.findElement(By.name("accountNumber"));
    }

    public WebElement getDigitoConta(){

        return driver.findElement(By.name("digit"));
    }

    public WebElement getVlrTransf(){

        return driver.findElement(By.name("transferValue"));
    }

    public WebElement getDescricao() {
        
        return driver.findElement(By.name("description"));
    }
    
    public WebElement getTransfButton() {
        
        return driver.findElement(By.xpath("//button[contains(text(), 'Transferir agora')]"));
    }
    
    public WebElement getSairButton() {
        
        return driver.findElement(By.id("btnExit"));
    }
    
    public WebElement getVoltarButton() {
        
        return driver.findElement(By.id("btnBack"));
    }
    
    public WebElement getFecharModalBtn() {
        
        return driver.findElement(By.id("btnCloseModal"));
    }
    
}
