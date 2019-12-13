package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bouchonTestCalculator.Action;
import bouchonTestCalculator.Unite;
import bouchonTestCalculator.Version;
import calculator.Calculator;
import itf.IAction;
import itf.ICalculator;
import itf.IVersion;
import itf.IUnite;



public class CalculatorTest {

	ICalculator calculator;
	IVersion version;
	IAction action;
	// verifierValidite(List <IAction> BOCree)
	// IAction calculBO(List <Integer> uniteObjectif, List <Integer> ressourceObjectif)
	// IAction calculBO(List <IAction> etatInit, List <Integer> uniteObjectif, List <Integer> ressourceObjectif)
	// Boolean changerVersion(String version)
	
	public CalculatorTest() throws Exception{
		calculator = new Calculator();
		version = (IVersion) new Version();
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
		listAttendu.add(new <IAction> Action(new <IUnite> Unite("Caserne")));
		listAttendu.add(new <IAction> Action(new <IUnite> Unite("Soldat")));
		List <Integer> listUnite = new ArrayList <Integer> ();//liste pour les unites demander en objectif (ici un soldat)
		listUnite.add(1);
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(0);
		List <Integer> listRessource = new ArrayList <Integer> ();//liste pour les ressources demander en objectif (ici rien)
		listUnite.add(0);
		listUnite.add(0);
		
		List <IAction> test2 = calculator.calculBO(listUnite, listRessource );
		
		assertEquals(listAttendu, test2);
	}
	
	@Test
	public void testCalculBOIA() {
		
	}
	
	@Test
	public void testVerfifierBO() {
		
	}

}
