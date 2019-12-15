package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bouchonTestCalculator.Action;
import bouchonTestCalculator.Unite;
import bouchonTestCalculator.Version;
import calculator.Calculator;
import factory.ActionFactory;
import itf.IAction;
import itf.ICalculator;
import itf.IEtat;
import itf.IVersion;
import version.VersionSingleton;
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
	public void testChangerVersion() throws IOException {
		boolean resultatAttendu = true;
		boolean test1 = calculator.ChangerVersion("v1");
		assertEquals(resultatAttendu, test1);
	}
	

	@Test
	public void testCalculBOUser() throws IOException {
		List <String> listAttendu = new ArrayList<String>();
		listAttendu.add("Ferme");
		listAttendu.add("Caserne");
		List <Integer> listUnite = new ArrayList <Integer> ();//liste pour les unites demander en objectif (ici un soldat)
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(1);
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(0);
		List <Integer> listRessource = new ArrayList <Integer> ();//liste pour les ressources demander en objectif (ici rien)
		listUnite.add(0);
		listUnite.add(0);
		
		List <IAction> listeRecue = calculator.calculBO(listUnite, listRessource );
		List <String> test2 = new ArrayList <String> ();
		for (int i=0; i<listeRecue.size();i++) {
			test2.add(listeRecue.get(i).getConstructedUnite().getNom());
		}
		assertEquals(listAttendu, test2);
	}

	
	
	@Test
	public void testCalculBOIA() throws IOException {
		List <String> listAttendu = new ArrayList<String>();
		listAttendu.add("Caserne");
		listAttendu.add("Soldat");
		List <Integer> listUnite = new ArrayList <Integer> ();//liste pour les unites demander en objectif (ici un soldat)
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(0);
		listUnite.add(1);
		listUnite.add(0);
		List <Integer> listRessource = new ArrayList <Integer> ();//liste pour les ressources demander en objectif (ici rien)
		listUnite.add(0);
		listUnite.add(0);
		
		IEtat etatinit = VersionSingleton.getIversion().getEtatInitial();
		etatinit=etatinit.addAction(ActionFactory.createAction(new Unite("Ferme")));//ajout ferme
		
		//envoie liste
		List <IAction> listeRecue = calculator.calculBO(etatinit.getBuildOrder() ,listUnite, listRessource);
		List <String> test3 = new ArrayList <String> ();
		for (int i=0; i<listeRecue.size();i++) {
			test3.add(listeRecue.get(i).getConstructedUnite().getNom());
		}
		assertEquals(listAttendu, test3);
	}
	

	@Test
	public void testVerfifierBO() throws IOException {
		List <String> listAttendu = new ArrayList<String>();
		List <IUnite> listUnite = VersionSingleton.getIversion().getUnites();
		listAttendu.add(listUnite.get(1).getNom());
		listAttendu.add(listUnite.get(2).getNom());
		listAttendu.add(listUnite.get(5).getNom());
		List <IAction> listDataTest = new ArrayList<IAction>();
		listDataTest.add(new <IAction> Action(listUnite.get(1)));
		listDataTest.add(new <IAction> Action(listUnite.get(5)));
		
		List <IAction> listeRecue = calculator.verifierValidite(listDataTest);
		List <String> test4 = new ArrayList <String> ();
		for (int i=0; i<listeRecue.size();i++) {
			test4.add(listeRecue.get(i).getConstructedUnite().getNom());
		}
		assertEquals(listAttendu, test4);
	}


}
