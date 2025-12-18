package TestCases;

import Framework.TestBase;
import Framework.Utils.TakeScreenshot;

import Tasks.*;
import Validacao.*;

import java.time.Duration;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class RealizarTransferenciaTest extends TestBase {

    private final WebDriver driver = getDriverManager();

    LoginTask loginTask = new LoginTask(driver);
    RegistrarTask registrarTask = new RegistrarTask(driver);
    TransfTask transfTask = new TransfTask(driver);
    TransfValidacao validacao = new TransfValidacao(driver);

    Faker faker = new Faker(new Locale("pt", "BR"));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    private double vlrTransf = 500;
    private String descricaoTransf = "Transferencia teste";

    @Test
    public void transferenciaTest() {
        try {
            Conta contaOrigem = new Conta(faker.internet().emailAddress(), faker.internet().password(),
                    faker.name().username());
            Conta contaDestino = new Conta(faker.internet().emailAddress(), faker.internet().password(),
                    faker.name().username());

            String contaOrigemNr = registrarTask.registrar(contaOrigem.getEmail(), contaOrigem.getUserName(),
                    contaOrigem.getSenha());
            String contaDestinoNr = registrarTask.registrar(contaDestino.getEmail(), contaDestino.getUserName(),
                    contaDestino.getSenha());

            String[] partesConta1 = contaOrigemNr.substring("A conta ".length(), contaOrigemNr.indexOf(" foi criada"))
                    .split("-");
            contaOrigem.setNumero(partesConta1[0]);
            contaOrigem.setDigito(partesConta1[1]);

            String[] partesConta2 = contaDestinoNr.substring("A conta ".length(), contaDestinoNr.indexOf(" foi criada"))
                    .split("-");
            contaDestino.setNumero(partesConta2[0]);
            contaDestino.setDigito(partesConta2[1]);

            contaOrigem.setSaldoinicial(loginTask.obterSaldoConta(contaOrigem.getEmail(), contaOrigem.getSenha()));
            contaDestino.setSaldoinicial(loginTask.obterSaldoConta(contaDestino.getEmail(), contaDestino.getSenha()));

            loginTask.loginConta(contaOrigem.getEmail(), contaOrigem.getSenha());
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("textAccountNumber")));
            transfTask.transferirValor(contaDestino.getNumero(), contaDestino.getDigito(), vlrTransf, descricaoTransf);
            validacao.validationVlrEnviado(contaOrigem, vlrTransf);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
            loginTask.loginConta(contaDestino.getEmail(), contaDestino.getSenha());

            validacao.validationConta(contaDestino.getContaFormatada());
            validacao.validationTransf(contaDestino, descricaoTransf, vlrTransf);

        } catch (Exception e) {
            TakeScreenshot.attachPageScreenshot(driver, "ERRO na Transferência");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
