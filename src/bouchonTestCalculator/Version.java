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
		unites.get(2).setPrerequis((IUnite) unites.get(1));
		unites.add(new <IUnite> Unite("Arme en Fer"));
		unites.add(new <IUnite> Unite("Outil en Fer"));
		unites.add(new <IUnite> Unite("Soldat"));
		unites.get(5).setPrerequis((IUnite) unites.get(2));
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
		Path p = Paths.get("v1.0.txt");
		BufferedReader br = new BufferedReader(new FileReader(p.toAbsolutePath().toFile()));
		String line = br.readLine();
		Scanner stream = new Scanner(line);
		stream.useDelimiter(",");
		List<Unite> uniteList = new ArrayList<>();
		List<IUnite> unite;
		List<IRessource> ress;
		List<IUnite> constList = new ArrayList<>();
		try {
			
		// On met toutes les ressources dans la liste attribut
		while(stream.hasNext()){
			ressource.add(new Ressource(stream.next()));
		}
		
		line = br.readLine();
		stream = new Scanner(line);
		stream.useDelimiter(",");
		
		// On met toutes les unités dans la liste 
		while(stream.hasNext()) {
			uniteList.add(new Unite(stream.next()));
		}
		
		for(Unite u : uniteList) {
			line = br.readLine();
			stream = new Scanner(line);
			stream.useDelimiter(";");
			u.setTempsConstruc(stream.nextInt());
			u.setType(toType(stream.next()));
			
			String prerq = stream.next();			
			String constructorlist = stream.next();
			String ressourcesProd = stream.next();
			String cout = stream.next();
			
			// Pour chaque champ composé, on le découpe en parties puis on remplit les champs de Unite
			stream = new Scanner(prerq);
			unite = new ArrayList<>();
			
			while(stream.hasNext()) {				// Prérequis	
				line = stream.next();
				for(Unite un : uniteList) { 		// On recherche l'unite qui correspond au prérequis
					if(un.getNom().equals(line)) {
						unite.add(un);				// on l'ajoute dans la liste des prérequis
						break;
					}
				}
			}
			if(unite.isEmpty())
				u.setPrerequis(null);
			else
				u.setPrerequis(new ArrayList<IUnite>(unite));
			
													// On passe à la liste des constructeurs 
			stream = new Scanner(constructorlist);
			stream.useDelimiter(",");
			while(stream.hasNext()) {
				line = stream.next();
				for(Unite un : uniteList) { 		// On recherche l'unite qui correspond au constructeur
					if(un.getNom().equals(line)) {
						constList.add(un);				// on l'ajoute dans la liste des constructeurs
						break;
					}
				}
			}
			if(unite.isEmpty())
				u.setConstructorsList(null);
			else
				u.setConstructorsList(new ArrayList<IUnite>(unite));
			
														// La liste des ressources produites
			stream = new Scanner(ressourcesProd);
			stream.useDelimiter(",");
			ress = new ArrayList<>();
			while(stream.hasNext()) {
				line = stream.next();
				Scanner sc = new Scanner(line);
				Ressource r = new Ressource(sc.next());
				r.setValeur(Integer.parseInt(sc.next()));
				ress.add(r);
				sc.close();
			}
			
			if(ress.isEmpty())
				u.setRessourceProd(null);
			else
				u.setRessourceProd(new ArrayList<>(ress));
						
													// Cout 
			stream = new Scanner(cout);
			stream.useDelimiter(",");
			ress = new ArrayList<>();
			while(stream.hasNext()) {
				line = stream.next();
				Scanner sc = new Scanner(line);
				Ressource r = new Ressource(sc.next());
				r.setValeur(sc.nextInt());
				ress.add(r);
				sc.close();
			}
			
			if(ress.isEmpty())
				u.setCout(null);
			else
				u.setCout(new ArrayList<>(ress));
		}
		versionJeu = new ArrayList<IUnite>(uniteList);
		
		
		// Reste à lire l'état initial : taille du tableau  + nbRessources \n
		//puis tableau avec chiffres pour chaque unite
		line = br.readLine();
		stream = new Scanner(line);
		int length = Integer.parseInt(stream.next());
		int nbRess = Integer.parseInt(stream.next());
		line = br.readLine();
		stream = new Scanner(line);
		stream.useDelimiter(",");
		ress = new ArrayList<>();
		unite = new ArrayList<>();
		for(int i = 0; i < length; i++) {
			if(i < nbRess) {
				ress.add(new Ressource(ressource.get(i)));
				ress.get(i).setValeur(Integer.parseInt(stream.next()));
			}
			else {
				int nbEntite = Integer.parseInt(stream.next());
				for(int j = 0; j < nbEntite ; j++) {
					initialState.addEntite(factory.EntiteFactory.createEntite(versionJeu.get(i-nbRess)));
				}
			}	
		}
	}finally {
		stream.close();
		br.close();
		}
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