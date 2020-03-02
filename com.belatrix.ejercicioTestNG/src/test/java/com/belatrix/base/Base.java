package com.belatrix.base;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	public static WebDriver driver;
	
	public Base() {
		String driverByOS="";
	
		
		if (driver == null) {
			
			//if(System.getProperty("os.name").equals("Windows 10")) {
				driverByOS="./src/test/resources/chromedriver/chromedriver.exe";
			/*
			 * }else { driverByOS="./src/test/resources/otracarpeta/chromedriverLinux.exe";
			 * }
			 */
			System.setProperty("webdriver.chrome.driver", driverByOS);
			/*ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);*/
			driver = new ChromeDriver();
			driver.manage().window().maximize();

        }
	}
	
	public WebDriver chromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		if (driver == null) {
			driver = new ChromeDriver();

        }
		
		return driver;
	}
	
	public void pagina(String url) {
		driver.get(url);
	}
	
	public void capturarPantalla(String nombre) throws IOException, InterruptedException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		//org.openqa.selenium.io.FileHandler.copy(from, to);
		org.openqa.selenium.io.FileHandler.copy(scrFile, new File("./src/test/resources/screenshot/"+nombre+".jpg"));
		Thread.sleep(2000);
		
	}
	
	public void moveToElementosByDos(WebElement elemento1,WebElement elemento2) throws InterruptedException{
		Actions action = new Actions(driver); 
		action.moveToElement(elemento1).moveToElement(elemento2).click().build().perform();
		
		
	}

	public WebElement findElement(By localizador) {
		return driver.findElement(localizador);
	}
	

	
	public void waitElement(By localizador) {
		WebDriverWait ewait=new WebDriverWait(driver,10);
		ewait.until(ExpectedConditions.presenceOfElementLocated(localizador));
	}
	
	
	
	public Boolean isDisplayed(By localizador) {
		try {
			return driver.findElement(localizador).isDisplayed();
		}catch(org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void click(By localizador) {
		driver.findElement(localizador).click();
	}
	
	public void click(WebElement elemento) {		
		elemento.click();
	}
	
	public void sendKeys(String textoEntrada, By localizador) {
		driver.findElement(localizador).sendKeys(textoEntrada);
	}
	
	
	public List<WebElement> findElements(By localizador){
		return driver.findElements(localizador);
	}
	
	public List<WebElement> findElements(WebElement elemento,By localizador){
		return elemento.findElements(localizador);
	}
	
	public String getText(WebElement elemento) {
		return elemento.getText();
	}
	
	public String getText(By localizador) {
		return driver.findElement(localizador).getText();
	}

}
