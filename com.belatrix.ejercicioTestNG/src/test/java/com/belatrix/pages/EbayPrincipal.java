package com.belatrix.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.belatrix.base.Base;
import com.belatrix.tests.Hook;

public class EbayPrincipal extends Hook {

	ExtentTest logger;

	public EbayPrincipal(ExtentTest logger) {
		super();
		this.logger = logger;
		// TODO Auto-generated constructor stub
	}

	By cajaBusqueda = By.id("gh-ac");
	By botonBusqueda = By.id("gh-btn");

	public void ingresarProducto(String nombreproducto) throws InterruptedException {

		if (waitPresenceOfElementLocated(getDriver(), "10", cajaBusqueda)) {

			sendKeys(nombreproducto, cajaBusqueda);
			logger.log(Status.PASS, "Se encontro: " + nombreproducto);
		} else {
			logger.log(Status.FAIL, "No pudo ingresar " + nombreproducto);
			Assert.fail("No pudo ingresar " + nombreproducto);
		}
	}

	public void elegirCategoria(String nombreCategoria) throws InterruptedException {
		/* Ejm: Elegimos la categoria */
	}

	public void buscarProducto() throws InterruptedException {

		if (waitPresenceOfElementLocated(getDriver(), "10", botonBusqueda)) {

			click(botonBusqueda);
			logger.log(Status.PASS, "Se dio clic en boton para buscar");

		} else {
			logger.log(Status.FAIL, "No pudo dar clic en boton para buscar");
			Assert.fail("No pudo dar clic en boton para buscar");
		}
	}

}
