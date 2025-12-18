package Tasks;

import PageObjects.HomePage;
import PageObjects.LoginPage;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTask {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    WebDriverWait wait;
    NumberFormat formatadorNumero = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    public LoginTask(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(this.driver);
        homePage = new HomePage(this.driver);
    }

    public double obterSaldoConta(String email, String senha) {

        loginConta(email, senha);

        By saldoLocator = By.xpath("//p[@id='textBalance']/span");
        wait.until(ExpectedConditions.presenceOfElementLocated(saldoLocator));

        String saldo = homePage.getSaldoConta().getText();

        String apenasNumero = saldo.replace('\u00A0', ' ').replace("R$", "").trim().replace(" ", "");

        homePage.getSairButton().click();

        try {
            Number saldoInicial = formatadorNumero.parse(apenasNumero);
            return saldoInicial.doubleValue();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public void loginConta(String email, String senha) {
        loginPage.getUserEmailInput().sendKeys(email);
        loginPage.getPasswordInput().sendKeys(senha);
        loginPage.getLoginButton().click();
    }
}
