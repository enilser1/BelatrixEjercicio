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

public class EbayDetalle extends Base {

	ExtentTest logger;

	public EbayDetalle(ExtentTest logger) {
		super();
		this.logger=logger;
		// TODO Auto-generated constructor stub
	}

	List<String> writeList = new ArrayList<String>();
	
	By checkboxmarcaLocator;
	By checkboxtamanioLocator;
	By opcionlistaLocator;

	By resultadosbusquedaLocator = By.xpath("//h1[@class='srp-controls__count-heading']");
	By spanresultadoLocator = By.tagName("span");
	//By botonDropDownLocator = By.xpath("//button[@type='button' and @aria-controls='w9']");
	//By botonDropDownLocator = By.id("w23-button-w0");
	By botonDropDownLocator1 = By.xpath("//div[@class='srp-controls--selected-value']");
	By botonDropDownLocator2 = By.xpath("//button[@id='w23-button']");
	By cantidadresultLocator=By.xpath("//li[contains(@id,'srp-river-results-listing')]");
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
		checkboxmarcaLocator=By.xpath("//input[@type='checkbox' and @aria-label='"+nombreMarca+"']");
		if(waitPresenceOfElementLocated(getDriver(), "10", checkboxmarcaLocator)) {
			click(checkboxmarcaLocator);
			logger.log(Status.PASS, "Se seleccionó la marca: "+nombreMarca);
		}
		
	}

	public void seleccionarTamaño(String tamaño) throws InterruptedException {
		checkboxtamanioLocator=By.xpath("//input[@type='checkbox' and @aria-label='"+tamaño+"']");
		if(waitPresenceOfElementLocated(getDriver(), "10", checkboxmarcaLocator)) {
			click(checkboxtamanioLocator);
			logger.log(Status.PASS, "Se seleccionó el tamaño: "+tamaño);
		}
		

	}

	public void obtenernumeroResultados() throws InterruptedException {
		
		if(waitPresenceOfElementLocated(getDriver(), "5", resultadosbusquedaLocator)) {
			WebElement listaresutado = findElement(resultadosbusquedaLocator);
			List<WebElement> resultado = findElements(listaresutado, spanresultadoLocator);
			String textoresultado = getText(resultado.get(0));
			imprimirTexto("La cantidad de resultados es: " + textoresultado);
			imprimirSeparador();
		}


	}

	public void ordenarPor(String nombreOrden) throws InterruptedException {
		WebElement desplegarLista=null;
		WebElement elegirOpcion=null;
		opcionlistaLocator=By.xpath("//span[contains(text(), '"+nombreOrden+"')]");
		
		if(waitPresenceOfElementLocated(getDriver(), "5", botonDropDownLocator1)) {
			desplegarLista=findElement(botonDropDownLocator1);
			if(waitPresenceOfElementLocated(getDriver(), "10", opcionlistaLocator)) {
				elegirOpcion = findElement(opcionlistaLocator);
				moveToElementosByDos(desplegarLista,elegirOpcion);
			}
		}else if(waitPresenceOfElementLocated(getDriver(), "5", botonDropDownLocator2)) {
			desplegarLista=findElement(botonDropDownLocator2);
			click(botonDropDownLocator2);
			if(waitPresenceOfElementLocated(getDriver(), "10", opcionlistaLocator)) {
				elegirOpcion = findElement(opcionlistaLocator);
				click(elegirOpcion);
			}

		}	
		
		

	}
	public void confirmarCantidadProductos(String numero) {
		/*
		 * waitElement(cantidadresultLocator); List<WebElement> nombres =
		 * findElements(cantidadresultLocator); int numeroInt=Integer.parseInt(numero);
		 * System.out.println(""+nombres.size());
		 * Assert.assertTrue(nombres.size()>numeroInt,"NO SE EJECUTO CORRECTAMENTE");
		 */
		
		if(waitNumberOfElementsToBeMoreThan(getDriver(), "10", cantidadresultLocator,numero)) {
			imprimirTexto("Tiene mas de "+numero+" elemento(s)");
			imprimirSeparador();
		}
		
		//WebDriverWait wait=new WebDriverWait(driver,10);
		//WebElement cantidadPasajeross=wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cantidadresultLocator,2));
		//List<WebElement> cantidadPasajeros=wait.until(ExpectedConditions.numberOfE(cantidadresultLocator, 3));
		
	}

	public void imprimirCantidadProductos(String numero) throws InterruptedException {
		if(waitPresenceOfElementLocated(getDriver(), "10", cantidadresultLocator)) {
			List<WebElement> nombres = findElements(nombreProductoLocator);
			List<WebElement> precios = findElements(precioProductoLocator);
			int numeroInt = Integer.parseInt(numero);
			for (int i = 0; i < nombres.size(); i++) {
				if (i < numeroInt) {
				
					System.out.println("NOMBRE: " + getText(nombres.get(i)) + " , PRECIO: " + getText(precios.get(i)));
				
				}
			}			
			imprimirSeparador();
		}
	}

	public void imprimirEnOrdenPrecioOProducto( String concepto, String modo) throws InterruptedException 
	{
		String[] arrNombres = null;
		String[] arrPrecios = null;
		
		
		if (concepto.equals("nombre")) {
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
			} else if (modo.contains("desc")) {
				Arrays.sort(arrNombres, Collections.reverseOrder());
			}

		} else if (concepto.equals("precio")) {
			if (modo.contains("asc")) {
				WebElement desplegarLista=null;
				WebElement elegirOpcion=null;
				opcionlistaLocator=By.xpath("//span[contains(text(), 'bajo primero')]");
				elegirOpcion = findElement(opcionlistaLocator);
				if(waitPresenceOfElementLocated(getDriver(), "5", botonDropDownLocator1)) {
					desplegarLista=findElement(botonDropDownLocator1);
					moveToElementosByDos(desplegarLista,elegirOpcion);
				}else if(waitPresenceOfElementLocated(getDriver(), "5", botonDropDownLocator2)) {
					desplegarLista=findElement(botonDropDownLocator2);
					click(botonDropDownLocator2);
					click(elegirOpcion);
					
				}
			} else if (modo.contains("desc")) {
				WebElement desplegarLista=null;
				WebElement elegirOpcion=null;
				opcionlistaLocator=By.xpath("//span[contains(text(), 'alto primero')]");
				elegirOpcion = findElement(opcionlistaLocator);
				if(isDisplayed(botonDropDownLocator1))  {
					desplegarLista=findElement(botonDropDownLocator1);
					moveToElementosByDos(desplegarLista,elegirOpcion);
				}else if(isDisplayed(botonDropDownLocator2)) {
					desplegarLista=findElement(botonDropDownLocator2);
					click(botonDropDownLocator2);
					click(elegirOpcion);
				}
			}			
			List<WebElement> nombres = findElements(nombreProductoLocator);
			List<WebElement> precios = findElements(precioProductoLocator);

			arrNombres = new String[nombres.size()];
			arrPrecios = new String[precios.size()];
			for (int i = 0; i < nombres.size(); i++) {
			
					arrNombres[i] = getText(nombres.get(i));
					arrPrecios[i] = getText(precios.get(i));
			

			}
			
			
		}

		for (int i = 0; i < arrNombres.length; i++) {
			System.out.println(arrNombres[i]);
		}
		imprimirSeparador();
		Thread.sleep(6000);
	}



}
