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
	
	  public boolean waitPresenceOfElementLocated(WebDriver driver,String tiempo,By localizador) {
		  try {
			  int tiempoInt=Integer.parseInt(tiempo);
				ewait=new WebDriverWait(driver,tiempoInt);
				//if(ewait.until(ExpectedConditions.presenceOfElementLocated(localizador)) != null) {
				ewait.until(ExpectedConditions.presenceOfElementLocated(localizador));
					return true;
				/*}else {
					Assert.fail("No encontro el elemento");
					return false;
				}*/
		  }catch (Exception e) {
			// TODO: handle exception
		}
			  //Assert.fail("No encontro el elemento");
			return false;
		}
		  
		
	    
	  
	  
	  public WebElement waitPresenceOfElement(WebDriver driver,String tiempo,By localizador)
	  { 
		  int tiempoInt=Integer.parseInt(tiempo); 
		  ewait=new WebDriverWait(driver,tiempoInt);
		  WebElement elemento=ewait.until(ExpectedConditions.presenceOfElementLocated(localizador));
		  return elemento;
	  }
	  
	  public void textToBePresentElement(WebDriver driver,String tiempo,By localizador,String texto) {
			int tiempoInt=Integer.parseInt(tiempo);
			ewait=new WebDriverWait(driver,tiempoInt);
			ewait.until(ExpectedConditions.textToBePresentInElementLocated(localizador, texto)); 
		    
	  }
	  
	  public void waitPresenceOfAllElementsLocated(WebDriver driver,String tiempo,By localizador) {
			int tiempoInt=Integer.parseInt(tiempo);
			ewait=new WebDriverWait(driver,tiempoInt);
			ewait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(localizador)); 
		    
		  }
	  
	  public void waitElementtobeClickeable(WebDriver driver,String tiempo,By localizador) {
			int tiempoInt=Integer.parseInt(tiempo);
			ewait=new WebDriverWait(driver,tiempoInt);
			ewait.until(ExpectedConditions.elementToBeClickable(localizador)); 		    
		  }
	  
	  public void waitElementtobeSelected(WebDriver driver,String tiempo,By localizador) {
			int tiempoInt=Integer.parseInt(tiempo);
			ewait=new WebDriverWait(driver,tiempoInt);
			ewait.until(ExpectedConditions.elementToBeSelected(localizador)); 
		    
		  }
	  
	  public boolean waitNumberOfElementsToBeMoreThan(WebDriver driver,String tiempo,By localizador,String numero) {
		  	try {
		  		int tiempoInt=Integer.parseInt(tiempo);
				int numeroInt=Integer.parseInt(numero);
				ewait=new WebDriverWait(driver,tiempoInt);
				ewait.until(ExpectedConditions.numberOfElementsToBeMoreThan(localizador,numeroInt));
					return true;
				
		  	}catch (Exception e) {
		  		return false;
			}
			
		    
		  }
	  
	  
	
	

}
