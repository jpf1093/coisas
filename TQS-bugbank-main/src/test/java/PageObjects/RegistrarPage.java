package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrarPage {
    private WebDriver driver;

    public RegistrarPage(WebDriver driver) {

        this.driver = driver;
    }

    public WebElement getRegistrarButton() {
        
        return driver.findElement(By.xpath("//button[contains(text(), 'Registrar')]"));
    }

    public WebElement getEmailInput(){

        return driver.findElement(By.xpath("(//input[@name='email'])[2]"));
    }

    public WebElement getNameInput(){

        return driver.findElement(By.name("name"));
    }

    public WebElement getPasswordInput(){

        return driver.findElement(By.xpath("(//input[@name='password'])[2]"));
    }

    public WebElement getPasswordConfirmInput(){

        return driver.findElement(By.name("passwordConfirmation"));
    }

    public WebElement getSaldoTogle() {
        
        return driver.findElement(By.id("toggleAddBalance")); 
    }

    public WebElement getCadastrarButton() {
        
        return driver.findElement(By.xpath("//button[contains(text(), 'Cadastrar')]"));
    }

    public WebElement getConta() {
        
        return driver.findElement(By.id("modalText")); 
    }

    public WebElement getFecharModalButton() {
        
        return driver.findElement(By.id("btnCloseModal")); 
    }
}
