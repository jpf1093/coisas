package Tasks;

import PageObjects.RegistrarPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegistrarTask {

    private WebDriver driver;
    private RegistrarPage registrarPage;
    WebDriverWait wait;

    public RegistrarTask(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        registrarPage = new RegistrarPage(this.driver);
    }

    public String registrar(String email, String nome, String senha) {

        registrarPage.getRegistrarButton().click();

        wait.until(ExpectedConditions.visibilityOf(registrarPage.getCadastrarButton()));

        registrarPage.getEmailInput().sendKeys(email);
        registrarPage.getNameInput().sendKeys(nome);
        registrarPage.getPasswordInput().sendKeys(senha);
        registrarPage.getPasswordConfirmInput().sendKeys(senha);
        registrarPage.getSaldoTogle().click();
        registrarPage.getCadastrarButton().click();

        wait.until(ExpectedConditions.visibilityOf(registrarPage.getConta()));

        String textoConta = registrarPage.getConta().getText();

        registrarPage.getFecharModalButton().click(); 

        driver.navigate().refresh();

        return textoConta;
    }
}
