package com.belatrix.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.belatrix.base.Base;
import com.belatrix.pages.EbayDetalle;
import com.belatrix.pages.EbayPrincipal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.File;

import org.testng.annotations.AfterClass;

public class Test01 extends Base{
	EbayPrincipal ebayPrincipal;
	EbayDetalle ebayDetalle;
	
	ExtentReports report;
	ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite() {
		//ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Belatrix.html"));
		ExtentHtmlReporter extent=new ExtentHtmlReporter("./Reports/BelatrixReport.html");
		
		report = new ExtentReports();
		report.attachReporter(extent);
	}
	
	@BeforeClass
	  public void beforeClass() {
		
		ebayPrincipal = new EbayPrincipal();
		ebayDetalle = new EbayDetalle();
		ebayPrincipal.pagina("https://www.ebay.com/");
		
		
		
		
	  }
	
	
	@Test(dataProvider = "authenticationData")
  public void test(String objeto,String marca,String tamanio,String cantidad) throws InterruptedException {

		
    	logger=report.createTest("Ejercicio Belatrix");
    	logger.info("Empieza la aplicacion");
			
    	  logger.log(Status.INFO, "Ingresamos el producto");
    	  ebayPrincipal.ingresarProducto(objeto);
    	  
    	  logger.log(Status.INFO, "Presionamos el boton buscar");
		  ebayPrincipal.buscarProducto(); 
		  
		  logger.log(Status.INFO, "Seleccionamos la marca");
		  ebayDetalle.seleccionarMarca(marca);
		  
		  logger.log(Status.INFO, "Seleccionamos el tamanio");
		  ebayDetalle.seleccionarTamaño(tamanio);
		  
		  logger.log(Status.INFO, "Imprimimos resultados en consola");
		  ebayDetalle.obtenernumeroResultados();
		  
		  logger.log(Status.INFO, "Ordenamos de menos a mayor precio");
		  ebayDetalle.ordenarPor("bajo primero");
		  
		  logger.log(Status.INFO, "Ingresamos la cantidad de resultados que queremos");
		  if(cantidad.equals(ebayDetalle.tomarResultados(cantidad))) {
		  logger.pass("Paso con exito"); 
		  logger.log(Status.PASS, "Paso con exito 2");
		  }else {
			  logger.log(Status.FAIL, "Fallo");
		  }
		  
		  //Assert.assertEquals(resultado, ebayDetalle.tomarResultados(resultado));
		  logger.log(Status.INFO, "Imprimimos nombre de producto de manera ascendente");
		  ebayDetalle.imprimirResultados("nombre", "asc");
		  
		  logger.log(Status.INFO, "Imprimimos nombre de producto por sus precios en forma descendente");
		  ebayDetalle.imprimirResultados("precio", "desc");
		 
  }
  
  @DataProvider(name="authenticationData")
  public Object[][] getData() throws Exception {
	  
	  //Object[][] testObjArray = readExcelFile2.readExcel("./src/test/resources/excel/Test.xlsx","Hoja1");
	  Object[][] data = new Object[1][4];
	  data[0][0]="shoes"; data[0][1]="PUMA"; data[0][2]="10"; data[0][3]="5";
      return (data);
	 
  }
  @AfterClass
  public void afterClass() {
	  report.flush();
	  cerrarNavegador();
  }

}
