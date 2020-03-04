package com.belatrix.metodos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class MetodosWaits {
	public WebDriver driver;
	public WebDriverWait ewait;

	public MetodosWaits() {

	}

	public boolean waitPresenceOfElementLocated(WebDriver driver, String tiempo, By localizador) {
		try {
			int tiempoInt = Integer.parseInt(tiempo);
			ewait = new WebDriverWait(driver, tiempoInt);
			ewait.until(ExpectedConditions.presenceOfElementLocated(localizador));
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public WebElement waitPresenceOfElement(WebDriver driver, String tiempo, By localizador) {
		int tiempoInt = Integer.parseInt(tiempo);
		ewait = new WebDriverWait(driver, tiempoInt);
		WebElement elemento = ewait.until(ExpectedConditions.presenceOfElementLocated(localizador));
		return elemento;
	}

	public boolean waitVisibilityOfElementLocated(WebDriver driver, String tiempo, By localizador) {
		try {
			int tiempoInt = Integer.parseInt(tiempo);
			ewait = new WebDriverWait(driver, tiempoInt);
			ewait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean textToBePresentElement(WebDriver driver, String tiempo, By localizador, String texto) {

		try {
			int tiempoInt = Integer.parseInt(tiempo);
			ewait = new WebDriverWait(driver, tiempoInt);
			ewait.until(ExpectedConditions.textToBePresentInElementLocated(localizador, texto));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean waitPresenceOfAllElementsLocated(WebDriver driver, String tiempo, By localizador) {

		try {
			int tiempoInt = Integer.parseInt(tiempo);
			ewait = new WebDriverWait(driver, tiempoInt);
			ewait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(localizador));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean waitElementtobeClickeable(WebDriver driver, String tiempo, By localizador) {

		try {
			int tiempoInt = Integer.parseInt(tiempo);
			ewait = new WebDriverWait(driver, tiempoInt);
			ewait.until(ExpectedConditions.elementToBeClickable(localizador));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean waitElementtobeSelected(WebDriver driver, String tiempo, By localizador) {

		try {
			int tiempoInt = Integer.parseInt(tiempo);
			ewait = new WebDriverWait(driver, tiempoInt);
			ewait.until(ExpectedConditions.elementToBeSelected(localizador));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean waitNumberOfElementsToBeMoreThan(WebDriver driver, String tiempo, By localizador, String numero) {
		try {
			int tiempoInt = Integer.parseInt(tiempo);
			int numeroInt = Integer.parseInt(numero);
			ewait = new WebDriverWait(driver, tiempoInt);
			ewait.until(ExpectedConditions.numberOfElementsToBeMoreThan(localizador, numeroInt));
			return true;

		} catch (Exception e) {
			return false;
		}

	}

}
