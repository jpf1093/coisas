package Tasks;

import PageObjects.TransferirPage;
import PageObjects.HomePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransfTask {

    private WebDriver driver;
    private TransferirPage transfPage;
    private HomePage homePage;
    WebDriverWait wait;

    public TransfTask(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        transfPage = new TransferirPage(this.driver);
        homePage = new HomePage(this.driver);
    }

    public void transferirValor(String nrConta, String digito, double valor, String descricao) {
        
        homePage.getTransferirButton().click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("accountNumber")));

        transfPage.getNumeroConta().sendKeys(nrConta);
        transfPage.getDigitoConta().sendKeys(digito);
        transfPage.getVlrTransf().sendKeys(String.valueOf(valor));
        transfPage.getDescricao().sendKeys(descricao);
        transfPage.getTransfButton().click();

        wait.until(ExpectedConditions.visibilityOf(transfPage.getFecharModalBtn()));

        transfPage.getFecharModalBtn().click();
        transfPage.getVoltarButton().click();
    }
}
