package com.belatrix.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.belatrix.base.Base;

public class EbayPrincipal extends Base {

	public EbayPrincipal() {
		super();
		// TODO Auto-generated constructor stub
	}

	By cajaBusqueda = By.id("gh-ac");
	By botonBusqueda = By.id("gh-btn");

	public void ingresarProducto(String nombreproducto) throws InterruptedException {

		if (isDisplayed(cajaBusqueda)) {
			/* Ingresamos el nombre del producto en la caja de texto */
			sendKeys(nombreproducto, cajaBusqueda);
		}

	}

	public void elegirCategoria(String nombreCategoria) throws InterruptedException {
		/* Elegimos la categoria */
	}

	public void buscarProducto() throws InterruptedException {

		if (isDisplayed(botonBusqueda)) {
			/* clic en el boton para buscar el producto */
			click(botonBusqueda);

		}

	}

}
