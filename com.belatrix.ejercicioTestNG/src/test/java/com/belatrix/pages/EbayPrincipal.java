package com.belatrix.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.belatrix.base.Base;

public class EbayPrincipal extends Base {
	
	ExtentTest logger;

	public EbayPrincipal(ExtentTest logger) {
		super();
		this.logger=logger;
		// TODO Auto-generated constructor stub
	}
	
	By cajaBusqueda = By.id("gh-ac");
	By botonBusqueda = By.id("gh-btn");

	public void ingresarProducto(String nombreproducto) throws InterruptedException {
		waitElement(cajaBusqueda);
		if (isDisplayed(cajaBusqueda)) {
			/* Ingresamos el nombre del producto en la caja de texto */
			sendKeys(nombreproducto, cajaBusqueda);
			logger.log(Status.PASS, "Se encontró: "+nombreproducto);
		}
	}

	public void elegirCategoria(String nombreCategoria) throws InterruptedException {
		/* Elegimos la categoria */
	}

	public void buscarProducto() throws InterruptedException {
		waitElement(botonBusqueda);
		if (isDisplayed(botonBusqueda)) {
			/* clic en el boton para buscar el producto */
			click(botonBusqueda);
			
		}
	}

}
