package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bouchonTestCalculator.Unite;
import calculator.CalculatorSingleton;
import factory.ActionFactory;
import itf.IAction;
import itf.ICalculator;
import itf.IEntite;
import itf.IEtat;
import itf.IUnite;
import itf.IVersion;
import version.VersionSingleton;

public class IntegrationTest {
	
	IVersion version;
	ICalculator calculator;
	List <IUnite> listUnite;
	
	public IntegrationTest() throws IOException {
		version = VersionSingleton.getIversion();
		calculator = CalculatorSingleton.getInstance();
		listUnite = version.getUnites();
	}
	
	@Test
	public void versionChargeListeUnite() {
		List <String> listAttendu1 = new ArrayList <String> ();
		listAttendu1.add("Centre");
		listAttendu1.add("Ferme");
		listAttendu1.add("Caserne");
		listAttendu1.add("Hall");
		listAttendu1.add("Ouvrier");
		listAttendu1.add("Soldat");
		listAttendu1.add("Boss");
		
		List <IUnite> listUniteVersion = version.getUnites();
		List <String> ListCharger1 = new ArrayList <String> ();
		
		for (int i=0; i<listUniteVersion.size(); i++) {
			ListCharger1.add(listUniteVersion.get(i).getNom());
		}

		assertEquals(listAttendu1,ListCharger1);
		
	}
	
	@Test
	public void versionUnitePrerequis() {
		List <String> listAttendu2 = new ArrayList <String> ();
		listAttendu2.add("Ouvrier");
		listAttendu2.add("Ferme");
		
		List <IUnite> listUniteVersion = version.getUnites();
		List <String> ListCharger2 = new ArrayList <String> ();
		ListCharger2.add(listUniteVersion.get(2).getPrerequis().get(0).getNom());//je teste le prerequis d'une caserne
		ListCharger2.add(listUniteVersion.get(2).getPrerequis().get(1).getNom());//je teste le prerequis d'une caserne
		
		assertEquals(listAttendu2, ListCharger2);
	}
	
	@Test
	public void creerEtatInit() {
		IEtat etat = version.getEtatInitial();
		List <IEntite> listEntiteEtat = etat.getEntite();
		List <String> listAttendu3 = new ArrayList <String> ();
		listAttendu3.add("Centre");
		listAttendu3.add("Ouvrier");
		listAttendu3.add("Ouvrier");
		listAttendu3.add("Ouvrier");
		listAttendu3.add("Ouvrier");
		listAttendu3.add("Ouvrier");
		List <String> listcharger3 = new ArrayList <String> ();
		for (int i=0; i<listEntiteEtat.size(); i++){
			listcharger3.add(listEntiteEtat.get(i).getIdentite().getNom());
		}
		
		assertEquals(listAttendu3,listcharger3);
	}
	
	@Test
	public void listEntiteCompletEtatFilles() throws IOException {
		IEtat etat = version.getEtatInitial();
		etat= etat.addAction(ActionFactory.createAction(listUnite.get(1)));
		List <IEntite> listEntiteEtat = etat.getEntite();
		List <String> listAttendu4 = new ArrayList <String> ();
		listAttendu4.add("Ferme");
		listAttendu4.add("Centre");
		listAttendu4.add("Ouvrier");
		listAttendu4.add("Ouvrier");
		listAttendu4.add("Ouvrier");
		listAttendu4.add("Ouvrier");
		listAttendu4.add("Ouvrier");
		List <String> listcharger4 = new ArrayList <String> ();
		for (int i=0; i<listEntiteEtat.size(); i++){
			listcharger4.add(listEntiteEtat.get(i).getIdentite().getNom());
		}
		
		assertEquals(listAttendu4,listcharger4);
	}
	
	@Test
	public void retourBO4Etats() throws IOException {
		IEtat etat = version.getEtatInitial();
		etat= etat.addAction(ActionFactory.createAction(listUnite.get(1)));
		etat= etat.addAction(ActionFactory.createAction(listUnite.get(2)));
		etat= etat.addAction(ActionFactory.createAction(listUnite.get(1)));
		List <String> listAttendu5 = new ArrayList <String> ();
		listAttendu5.add("Ferme");
		listAttendu5.add("Caserne");
		listAttendu5.add("Ferme");

		
		List <IAction> BOtest1 = etat.getBuildOrder();
		List <String> listCharger5 = new ArrayList <String> ();
		for (int i=0; i<BOtest1.size(); i++) {
			listCharger5.add(BOtest1.get(i).getConstructedUnite().getNom());
		}
		
		assertEquals(listCharger5, listAttendu5);
		
	}
	
	@Test
	public void chercheBOSimple() throws IOException {
		List <String> listAttendu6 = new ArrayList <String> ();
		listAttendu6.add("Caserne");
		listAttendu6.add("Ferme");
		
		List <Integer> listUnite = new ArrayList <Integer> ();//liste pour les unites demander en objectif (ici une caserne)
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
		
		List <IAction> BOtest2 = calculator.calculBO(listUnite, listRessource);
		List <String> listCharger6 = new ArrayList <String> ();
		for (int i=0; i<BOtest2.size(); i++) {
			listCharger6.add(BOtest2.get(i).getConstructedUnite().getNom());
		}
		
		assertEquals(listCharger6, listAttendu6);
	}

}
