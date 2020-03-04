package com.belatrix.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.belatrix.base.Base;
import com.belatrix.tests.Hook;

public class EbayDetalle extends Hook {

	ExtentTest logger;

	public EbayDetalle(ExtentTest logger) {
		super();
		this.logger = logger;
	}

	List<String> writeList = new ArrayList<String>();

	By checkboxmarcaLocator;
	By checkboxtamanioLocator;
	By opcionlistaLocator;

	By resultadosbusquedaLocator = By.xpath("//h1[@class='srp-controls__count-heading']");
	By spanresultadoLocator = By.tagName("span");
	By botonDropDownLocator1 = By.xpath("//div[@class='srp-controls--selected-value']");
	By botonDropDownLocator2 = By.xpath("//button[@id='w23-button']");
	By cantidadresultLocator = By.xpath("//li[contains(@id,'srp-river-results-listing')]");
	By nombreProductoLocator = (By.xpath("//h3[@class='s-item__title']"));
	By precioProductoLocator = (By.xpath("//span[@class='s-item__price']"));

	public void seleccionarMarcaAzar() throws InterruptedException {

		/*
		 * Seleccionamos marca al azar List<WebElement> options =
		 * dropdownList.findElements(locator); int i = (int) (Math.random() *
		 * options.size());
		 */

	}

	public void seleccionarMarca(String nombreMarca) throws InterruptedException {
		checkboxmarcaLocator = By.xpath("//input[@type='checkbox' and @aria-label='" + nombreMarca + "']");
		if (waitPresenceOfElementLocated(getDriver(), "10", checkboxmarcaLocator)) {
			click(checkboxmarcaLocator);
			logger.log(Status.PASS, "Se selecciona la marca: " + nombreMarca);
		} else {
			logger.log(Status.FAIL, "No encuentra marca: " + nombreMarca);
			Assert.fail("No encuentra marca: " + nombreMarca);
		}

	}

	public void seleccionarTamaño(String tamaño) throws InterruptedException {
		checkboxtamanioLocator = By.xpath("//input[@type='checkbox' and @aria-label='" + tamaño + "']");
		if (waitPresenceOfElementLocated(getDriver(), "10", checkboxmarcaLocator)) {
			click(checkboxtamanioLocator);
			logger.log(Status.PASS, "Se selecciona el size: " + tamaño);
		} else {
			logger.log(Status.FAIL, "No se selecciona el tamaño: " + tamaño);
			Assert.fail("No se selecciona el tamaño: " + tamaño);
		}

	}

	public void obtenernumeroResultados() throws InterruptedException {

		if (waitPresenceOfElementLocated(getDriver(), "5", resultadosbusquedaLocator)) {
			WebElement listaresutado = findElement(resultadosbusquedaLocator);
			List<WebElement> resultado = findElements(listaresutado, spanresultadoLocator);
			String textoresultado = getText(resultado.get(0));
			imprimirTexto("La cantidad de resultados es: " + textoresultado);
			imprimirSeparador();
			logger.log(Status.PASS, "Se obtuvo resultados de la busqueda");
		} else {
			logger.log(Status.FAIL, "No se obtuvo resultados de la busqueda");
			Assert.fail("No se obtuvo resultados de la busqueda");
		}

	}

	public void ordenarPor(String nombreOrden) throws InterruptedException {
		WebElement desplegarLista = null;
		WebElement elegirOpcion = null;
		opcionlistaLocator = By.xpath("//span[contains(text(), '" + nombreOrden + "')]");

		if (waitPresenceOfElementLocated(getDriver(), "5", botonDropDownLocator1)) {
			desplegarLista = findElement(botonDropDownLocator1);
			if (waitPresenceOfElementLocated(getDriver(), "10", opcionlistaLocator)) {
				elegirOpcion = findElement(opcionlistaLocator);
				moverDesdeAcaHastaAlla(desplegarLista, elegirOpcion);
				logger.log(Status.PASS, "Da click en la lista que se usa para ordenar");
			}

		} else if (waitPresenceOfElementLocated(getDriver(), "5", botonDropDownLocator2)) {
			desplegarLista = findElement(botonDropDownLocator2);
			click(botonDropDownLocator2);
			if (waitPresenceOfElementLocated(getDriver(), "10", opcionlistaLocator)) {
				elegirOpcion = findElement(opcionlistaLocator);
				click(elegirOpcion);
				logger.log(Status.PASS, "Da click en la lista que se usa para ordenar");
			}

		} else {
			logger.log(Status.FAIL, "Falla al dar click en la lista que se usa para ordenar");
			Assert.fail("Falla al dar click en la lista que se usa para ordenar");
		}

	}

	public void confirmarCantidadProductos(String numero) {

		if (waitNumberOfElementsToBeMoreThan(getDriver(), "10", cantidadresultLocator, numero)) {
			imprimirTexto("Tiene mas de " + numero + " elemento(s)");
			imprimirSeparador();
			logger.log(Status.PASS, "Tiene como minimo " + numero + " elemento(s)");
		} else {
			logger.log(Status.FAIL, "No tiene como minimo " + numero + " elemento(s)");
			Assert.fail("No tiene como minimo " + numero + " elemento(s)");
		}

	}

	public void imprimirCantidadProductos(String numero) throws InterruptedException {
		if (waitPresenceOfElementLocated(getDriver(), "10", cantidadresultLocator)) {
			List<WebElement> nombres = findElements(nombreProductoLocator);
			List<WebElement> precios = findElements(precioProductoLocator);
			int numeroInt = Integer.parseInt(numero);
			for (int i = 0; i < nombres.size(); i++) {
				if (i < numeroInt) {

					System.out.println("NOMBRE: " + getText(nombres.get(i)) + " , PRECIO: " + getText(precios.get(i)));
				}
			}
			imprimirSeparador();
			logger.log(Status.PASS, "Se pudo imprimir los " + numero + " elemento(s)");
		} else {
			logger.log(Status.FAIL, "No se pudo imprimir los " + numero + " elemento(s)");
			Assert.fail("No se pudo imprimir los " + numero + " elemento(s)");
		}
	}

	public void imprimirEnOrdenPrecioOProducto(String concepto, String modo) throws InterruptedException {
		String[] arrNombres = null;
		String[] arrPrecios = null;

		if (concepto.equals("nombre")) {
			if (waitVisibilityOfElementLocated(getDriver(), "15", nombreProductoLocator)) {
				List<WebElement> nombres = findElements(nombreProductoLocator);
				List<WebElement> precios = findElements(precioProductoLocator);

				arrNombres = new String[nombres.size()];
				arrPrecios = new String[precios.size()];
				for (int i = 0; i < nombres.size(); i++) {

					arrNombres[i] = getText(nombres.get(i));
					arrPrecios[i] = getText(precios.get(i));
				}
				if (modo.contains("asc")) {
					Arrays.sort(arrNombres);
					logger.log(Status.PASS, "Se pudo imprimir producto por orden ascendente");
				} else if (modo.contains("desc")) {
					Arrays.sort(arrNombres, Collections.reverseOrder());
					logger.log(Status.PASS, "Se pudo imprimir producto por orden descedente");
				}
			} else {
				logger.log(Status.FAIL, "No pudo encontrar los elementos");
				Assert.fail("No pudo encontrar los elementos");
			}

		} else if (concepto.equals("precio")) {
			if (modo.contains("asc")) {
				WebElement desplegarLista = null;
				WebElement elegirOpcion = null;
				opcionlistaLocator = By.xpath("//span[contains(text(), 'bajo primero')]");
				elegirOpcion = findElement(opcionlistaLocator);
				if (waitPresenceOfElementLocated(getDriver(), "15", botonDropDownLocator1)) {
					desplegarLista = findElement(botonDropDownLocator1);
					moverDesdeAcaHastaAlla(desplegarLista, elegirOpcion);
					logger.log(Status.PASS, "Se pudo imprimir precios por orden ascendente");
				} else if (waitPresenceOfElementLocated(getDriver(), "15", botonDropDownLocator2)) {
					desplegarLista = findElement(botonDropDownLocator2);
					click(botonDropDownLocator2);
					click(elegirOpcion);
					logger.log(Status.PASS, "Se pudo imprimir precios por orden ascendente");
				} else {
					logger.log(Status.FAIL, "No se pudo imprimir precios por orden ascendente");
					Assert.fail("No se pudo imprimir precios por orden ascendente");
				}
			} else if (modo.contains("desc")) {
				WebElement desplegarLista = null;
				WebElement elegirOpcion = null;
				opcionlistaLocator = By.xpath("//span[contains(text(), 'alto primero')]");
				elegirOpcion = findElement(opcionlistaLocator);
				if (waitPresenceOfElementLocated(getDriver(), "15", botonDropDownLocator1)) {
					desplegarLista = findElement(botonDropDownLocator1);
					moverDesdeAcaHastaAlla(desplegarLista, elegirOpcion);
					logger.log(Status.PASS, "Se pudo imprimir precios por orden descendente");
				} else if (waitPresenceOfElementLocated(getDriver(), "15", botonDropDownLocator2)) {
					desplegarLista = findElement(botonDropDownLocator2);
					click(botonDropDownLocator2);
					click(elegirOpcion);
					logger.log(Status.PASS, "Se pudo imprimir precios por orden descendente");
				} else {
					logger.log(Status.FAIL, "No se pudo imprimir precios por orden descendente");
					Assert.fail("No se pudo imprimir precios por orden descendente");
				}
			}

			if (waitVisibilityOfElementLocated(getDriver(), "15", nombreProductoLocator)) {
				List<WebElement> nombres = findElements(nombreProductoLocator);
				List<WebElement> precios = findElements(precioProductoLocator);

				arrNombres = new String[nombres.size()];
				arrPrecios = new String[precios.size()];
				for (int i = 0; i < nombres.size(); i++) {

					arrNombres[i] = getText(nombres.get(i));
					arrPrecios[i] = getText(precios.get(i));

				}
			} else {
				logger.log(Status.FAIL, "No pudo encontrar los elementos");
				Assert.fail("No pudo encontrar los elementos");
			}

		}

		for (int i = 0; i < arrNombres.length; i++) {
			imprimirTexto(arrNombres[i]);
		}
		imprimirSeparador();

	}

}
