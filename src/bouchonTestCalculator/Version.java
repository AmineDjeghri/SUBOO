package bouchonTestCalculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import bouchons.RessourceBouchon;
import bouchonTestCalculator.Unite;
import bouchonTestCalculator.Entite;
import itf.IEntite;
import itf.IEtat;
import itf.IRessource;
import itf.IUnite;
import itf.IVersion;
import version.Ressource;
import version.Type;


public class Version implements IVersion{
	
	ArrayList<IUnite> unites = new ArrayList<IUnite>();
	ArrayList<IRessource> ressources = new ArrayList<IRessource>();
	private static IVersion version = null;
	private List<IUnite> versionJeu;

	private static List<IRessource> ressource;
	
	private CharSequence numVers;
	
	private IEtat initialState;
	
	public Version()
	{
		ressource = new ArrayList <IRessource> ();
		
		
		
		unites.add(new <IUnite> Unite("Ouvrier"));
		unites.add(new <IUnite> Unite("Ferme"));
		unites.add(new <IUnite> Unite("Caserne"));
		((Unite) (unites.get(2))).setPrerequis((IUnite) unites.get(1));
		unites.add(new <IUnite> Unite("Arme en Fer"));
		unites.add(new <IUnite> Unite("Outil en Fer"));
		unites.add(new <IUnite> Unite("Soldat"));
		((Unite) (unites.get(5))).setPrerequis((IUnite) unites.get(2));
		unites.add(new <IUnite> Unite("Boss"));
		
		ressources.add(new RessourceBouchon("Nouriture",0));
		ressources.add(new RessourceBouchon("Bois",0));
		
		initialState = new <IEtat> Etat();
		for (int i = 0 ; i<5; i++) {
			initialState.addEntite(new <IEntite> Entite(unites.get(0)));
		}

	}
	
	public static IVersion getInstanceIVersion() {
		if(version == null) {
			version = new Version();
		}
		return version;
	}
	
	public List<IRessource> getRessources() {
	
		return new ArrayList<>(ressource);
		
	}

	
	public IEtat getEtatInitial() {
		
		return initialState;
		
	}

	
	public List<IUnite> getUnites() {
		
		return unites;
		
	}

	
	public Boolean chargerVersion(String version) {
		return true;
	}
	
	private void loadFile(String pathVersion) throws IOException {
		
	}
	
	private Type toType(String nom) {
		if(nom.equals("Centre") || nom.equals("Ferme") ||nom.equals("Hall") || nom.equals("Caserne")) {
			return Type.BATIMENT;
		}
		else if(nom.equals("Ouvrier") || nom.equals("Soldat") || nom.equals("Boss")){
			return Type.UNITE;
		}
		else {
			return null;	
		}
	}
}