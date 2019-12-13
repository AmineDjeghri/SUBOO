package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bouchons.VersionBouchon;
import calculator.Calculator;
import itf.IAction;
import itf.ICalculator;
import itf.IVersion;
import version.VersionSingleton;

public class CalculatorTest {

	ICalculator calculator;
	IVersion version;
	// verifierValidite(List <IAction> BOCree)
	// IAction calculBO(List <Integer> uniteObjectif, List <Integer> ressourceObjectif)
	// IAction calculBO(List <IAction> etatInit, List <Integer> uniteObjectif, List <Integer> ressourceObjectif)
	// Boolean changerVersion(String version)
	
	public CalculatorTest() throws Exception{
		calculator = new Calculator();
		version = (IVersion) new VersionBouchon();
	}
	
	@Test
	public void testChangerVersion() {
		boolean resultatAttendu = true;
		boolean test1 = calculator.ChangerVersion("v1");
		assertEquals(resultatAttendu, test1);
	}
	
	@Test
	public void testCalculBOUser() {
		List <IAction> listAttendu = new ArrayList<IAction>();
		listAttendu.add();
		listAttendu.add();
	}
	
	@Test
	public void testCalculBOIA() {
		
	}
	
	@Test
	public void testVerfifierBO() {
		
	}

}
