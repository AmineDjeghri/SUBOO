package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import calculator.CalculatorSingleton;
import itf.ICalculator;
import itf.IUnite;
import itf.IVersion;
import version.VersionSingleton;

public class IntegrationTest {
	
	IVersion version;
	ICalculator calculator;
	
	public IntegrationTest() throws IOException {
		version = VersionSingleton.getIversion();
		calculator = CalculatorSingleton.getInstance();
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
		System.out.println(ListCharger1);
		assertEquals(listAttendu1,ListCharger1);
		
	}

}
