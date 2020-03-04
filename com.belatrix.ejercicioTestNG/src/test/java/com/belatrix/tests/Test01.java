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

public class Test01 extends Hook{
	
	
	
	@Test(dataProvider = "authenticationData",description="CASO DE PRUEBA BELATRIX",enabled = true)
  public void test(String objeto,String marca,String tamanio,String cantidad) throws InterruptedException {

		
    	//logger=report.createTest("Ejercicio Belatrix");
    	
			
    	  
    	  ebayPrincipal.ingresarProducto(objeto);
    	  
    	  
		  ebayPrincipal.buscarProducto(); 
		  
		 
		  ebayDetalle.seleccionarMarca(marca);
		  
		  
		  ebayDetalle.seleccionarTamaño(tamanio);
		  
		  logger.log(Status.INFO, "Imprimimos resultados en consola");
		  ebayDetalle.obtenernumeroResultados();
		  
		  logger.log(Status.INFO, "Ordenamos de menos a mayor precio");
		  ebayDetalle.ordenarPor("bajo primero");
		  
		  logger.log(Status.INFO, "Ingresamos la cantidad de resultados que queremos");
		  ebayDetalle.confirmarCantidadProductos(cantidad);
		  ebayDetalle.imprimirCantidadProductos(cantidad);
		  logger.pass("Paso con exito"); 
		  logger.log(Status.PASS, "Paso con exito 2");
		  logger.log(Status.FAIL, "Fallo");
		  
		  
		  //Assert.assertEquals(resultado, ebayDetalle.tomarResultados(resultado));
		  logger.log(Status.INFO, "Imprimimos nombre de producto de manera ascendente");
		  ebayDetalle.imprimirEnOrdenPrecioOProducto("nombre", "asc");
		  
		  logger.log(Status.INFO, "Imprimimos nombre de producto por sus precios en forma descendente");
		  ebayDetalle.imprimirEnOrdenPrecioOProducto("precio", "desc");
		 
  }
  
  @DataProvider(name="authenticationData")
  public Object[][] getData() throws Exception {
	  
	  //Object[][] testObjArray = readExcelFile2.readExcel("./src/test/resources/excel/Test.xlsx","Hoja1");
	  Object[][] data = new Object[1][4];
	  data[0][0]="shoes"; data[0][1]="PUMA"; data[0][2]="10"; data[0][3]="5";
      return (data);
	 
  }
  
  

}
