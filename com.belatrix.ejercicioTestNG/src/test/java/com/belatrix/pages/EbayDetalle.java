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

import com.belatrix.base.Base;

public class EbayDetalle extends Base {

	public EbayDetalle() {
		super();
		// TODO Auto-generated constructor stub
	}

	List<String> writeList = new ArrayList<String>();
	
	By checkboxmarcaLocator;
	By checkboxtamanioLocator;
	By opcionlistaLocator;

	By resultadosLocator = By.xpath("//h1[@class='srp-controls__count-heading']");
	By spanresultadoLocator = By.tagName("span");
	//By botonDropDownLocator = By.xpath("//button[@type='button' and @aria-controls='w9']");
	//By botonDropDownLocator = By.id("w23-button-w0");
	By botonDropDownLocator1 = By.xpath("//div[@class='srp-controls--selected-value']");
	By botonDropDownLocator2 = By.xpath("//button[@id='w23-button']");
	By nombreProductoLocator = (By.xpath("//h3[@class='s-item__title']"));
	By precioProductoLocator = (By.xpath("//span[@class='s-item__price']"));


	public void seleccionarMarca() throws InterruptedException {

		/*
		 * Seleccionamos marca al azar List<WebElement> options =
		 * dropdownList.findElements(locator); int i = (int) (Math.random() *
		 * options.size());
		 */

	}

	public void seleccionarMarca(String nombreMarca) throws InterruptedException {
		checkboxmarcaLocator=By.xpath("//input[@type='checkbox' and @aria-label='"+nombreMarca+"']");
		waitElement(checkboxmarcaLocator);
		click(checkboxmarcaLocator);


	}

	public void seleccionarTamaño(String tamaño) throws InterruptedException {
		checkboxtamanioLocator=By.xpath("//input[@type='checkbox' and @aria-label='"+tamaño+"']");
		waitElement(checkboxtamanioLocator);
		click(checkboxtamanioLocator);

	}

	public void obtenernumeroResultados() throws InterruptedException {
		waitElement(resultadosLocator);
		WebElement listaresutado = findElement(resultadosLocator);
		List<WebElement> resultado = findElements(listaresutado, spanresultadoLocator);
		String textoresultado = getText(resultado.get(0));
		System.out.println("La cantidad de resultados es: " + textoresultado);
	

	}

	public void ordenarPor(String nombreOrden) throws InterruptedException {
		//
		WebElement desplegarLista=null;
		WebElement elegirOpcion=null;
		opcionlistaLocator=By.xpath("//span[contains(text(), '"+nombreOrden+"')]");
		
		
		if(isDisplayed(botonDropDownLocator1))  {
			desplegarLista=findElement(botonDropDownLocator1);
			waitElement(opcionlistaLocator);
			elegirOpcion = findElement(opcionlistaLocator);
			moveToElementosByDos(desplegarLista,elegirOpcion);
		}else if(isDisplayed(botonDropDownLocator2)) {
			desplegarLista=findElement(botonDropDownLocator2);
			click(botonDropDownLocator2);
			waitElement(opcionlistaLocator);
			elegirOpcion = findElement(opcionlistaLocator);
			click(elegirOpcion);
			//
		}		
		

	}

	public String tomarResultados(String numero) throws InterruptedException {

		List<WebElement> nombres = findElements(nombreProductoLocator);
		List<WebElement> precios = findElements(precioProductoLocator);

		String[] arrNombres;
		String[] arrPrecios;
		int contador = 0;
		int numeroInt = Integer.parseInt(numero);
		arrNombres = new String[numeroInt];
		arrPrecios = new String[numeroInt];
		for (int i = 0; i < nombres.size(); i++) {
			if (i < numeroInt) {
				arrNombres[i] = getText(nombres.get(i));
				arrPrecios[i] = getText(precios.get(i));
				System.out.println("NOMBRE: " + getText(nombres.get(i)) + " , PRECIO: " + getText(precios.get(i)));
				contador++;
			}
		}
		
		System.out.println("--------------------------------------------------------------------------------------");
		String contadorString = String.valueOf(contador);
		return contadorString;
	}

	public void imprimirResultados( String concepto, String modo) throws InterruptedException 
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
				if(isDisplayed(botonDropDownLocator1))  {
					desplegarLista=findElement(botonDropDownLocator1);
					moveToElementosByDos(desplegarLista,elegirOpcion);
				}else if(isDisplayed(botonDropDownLocator2)) {
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
		System.out.println("--------------------------------------------------------------------------------------");
		Thread.sleep(4000);
	}



}
