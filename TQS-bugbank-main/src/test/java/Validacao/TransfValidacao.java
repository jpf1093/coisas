package Validacao;

import PageObjects.ExtratoPage;
import PageObjects.HomePage;
import TestCases.Conta;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.Utils.TakeScreenshot;

public class TransfValidacao {

    private WebDriver driver;
    private HomePage homePage;
    private ExtratoPage extratoPage;
    WebDriverWait wait;
    NumberFormat formatadorNumero = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    public TransfValidacao(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        homePage = new HomePage(this.driver);
        extratoPage = new ExtratoPage(this.driver);

    }

    public void validationConta(String conta) {
        String label = homePage.getConta().getText();

        conta = "Conta digital: " + conta;

        try {
            Assertions.assertEquals(conta, label);

            TakeScreenshot.attachPageScreenshot(driver, "Conta Validada");
            TakeScreenshot.saveScreenshotFile(driver, "Conta Validad" + System.currentTimeMillis());

        } catch (Throwable e) {
            TakeScreenshot.attachPageScreenshot(driver, "ERRO nr Conta");
            TakeScreenshot.saveScreenshotFile(driver, "ERRO nr Conta" + System.currentTimeMillis());
            e.printStackTrace();
            Assertions.fail("Erro ao validar numero da Conta.", e);
        }
    }

    public void validationVlrEnviado(Conta contaOrigem, double vlrTransf) {
        if (vlrTransf < 0 || vlrTransf > contaOrigem.getSaldoinicial()) {
            vlrTransf = 0;
        }
        homePage.getExtratoButton().click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("textBalanceAvailable")));

        String label = extratoPage.getSaldo().getText();

        vlrTransf = contaOrigem.getSaldoinicial() - vlrTransf;

        String labelNormalizado = label.replace('\u00A0', ' ').replace("R$", "").trim().replace(" ", "");

        try {
            double vlrAtualNaTela = formatadorNumero.parse(labelNormalizado).doubleValue();

            Assertions.assertEquals(vlrTransf, vlrAtualNaTela);
            TakeScreenshot.attachPageScreenshot(driver, "Saldo da Conta Origem Validado");
            TakeScreenshot.saveScreenshotFile(driver, "Saldo da Conta Origem Validado" + System.currentTimeMillis());

        } catch (java.text.ParseException e) {
            TakeScreenshot.attachPageScreenshot(driver, "ERRO Saldo Conta Origem");
            TakeScreenshot.saveScreenshotFile(driver, "ERRO Saldo Conta Origem" + System.currentTimeMillis());
            e.printStackTrace();
            Assertions.fail("Erro ao validar saldo da Conta Origem.", e);
        }

        extratoPage.getLogOutButton().click();
    }

    public void validationTransf(Conta contaDestino, String descricao, double vlrTransf) {
        if (vlrTransf < 0 || vlrTransf > contaDestino.getSaldoinicial()) {
            vlrTransf = 0;
        }

        homePage.getExtratoButton().click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("textDescription")));

        String labelSaldo = extratoPage.getSaldo().getText();

        double vlrSaldoEsperado = contaDestino.getSaldoinicial() + vlrTransf;

        String labelSaldoNormalizado = labelSaldo.replace('\u00A0', ' ').replace("R$", "").trim().replace(" ", "");

        try {
            double vlrAtualNaTela = formatadorNumero.parse(labelSaldoNormalizado).doubleValue();

            Assertions.assertEquals(vlrSaldoEsperado, vlrAtualNaTela);
            TakeScreenshot.attachPageScreenshot(driver, "Saldo da Conta Destino Validado");
            TakeScreenshot.saveScreenshotFile(driver, "Saldo da Conta Destino Validado" + System.currentTimeMillis());
        } catch (java.text.ParseException e) {
            TakeScreenshot.attachPageScreenshot(driver, "ERRO Saldo Conta Destino");
            TakeScreenshot.saveScreenshotFile(driver, "ERRO Saldo Conta Destino" + System.currentTimeMillis());
            e.printStackTrace();
            Assertions.fail("Erro ao validar saldo da Conta Destino.", e);
        }

        List<WebElement> descricoes = extratoPage.getDescricao();
        List<WebElement> valores = extratoPage.getVlrTransf();

        int ultimoIndice = descricoes.size() - 1;

        if (ultimoIndice == 0) {
            descricao = "Saldo adicionado ao abrir conta";
            vlrTransf = contaDestino.getSaldoinicial();
        }

        String ultimaDescricaoNaTela = descricoes.get(ultimoIndice).getText().trim();
        Assertions.assertEquals(descricao.trim(), ultimaDescricaoNaTela);

        String ultimoValorNaTela = valores.get(ultimoIndice).getText();
        String atualNormalizado = ultimoValorNaTela.replace('\u00A0', ' ').replace("R$", "").trim().replace(" ", "");

        try {
            double atualFormatado = formatadorNumero.parse(atualNormalizado).doubleValue();

            Assertions.assertEquals(vlrTransf, atualFormatado);
            TakeScreenshot.attachPageScreenshot(driver, "Vlr transferencia Validada");
            TakeScreenshot.saveScreenshotFile(driver, "Vlr transferencia Validada" + System.currentTimeMillis());
        } catch (java.text.ParseException e) {
            TakeScreenshot.attachPageScreenshot(driver, "ERRO vlr transferencia");
            TakeScreenshot.saveScreenshotFile(driver, "ERRO vlr transferencia" + System.currentTimeMillis());
            e.printStackTrace();
            Assertions.fail("Erro ao validar vlr transferencia.", e);
        }

        extratoPage.getLogOutButton().click();
    }
}
