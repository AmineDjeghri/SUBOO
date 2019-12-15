package version;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import itf.IEtat;
import itf.IRessource;
import itf.IUnite;
import itf.IVersion;


public class Version implements IVersion {
	private static IVersion version = null;
	private List<IUnite> versionJeu;

	private static List<IRessource> ressource;
	
	private CharSequence numVers;
	
	private IEtat initialState;

	private Version () throws IOException {
		clear();
		loadFile("v1.0.txt");
	}
	
	public static IVersion getInstanceIVersion() throws IOException {
		if(version == null) {
			version = new Version();
		}
		return version;
	}
	
	public List<IRessource> getRessources() {
		List<IRessource> l = new ArrayList<>();
		for(IRessource r : ressource) {
			l.add(new Ressource(r));
		}
		return l;
		
	}

	
	public IEtat getEtatInitial() {
		
		return initialState;
		
	}

	
	public List<IUnite> getUnites() {
		
		return versionJeu;
		
	}

	
	public Boolean chargerVersion(String version) {
		Boolean b = true;
		try {
		if(!(version.equals(numVers))) {
			loadFile(version);
			numVers = version;
		}
		}catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
			b = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	private void clear()
	{
		ressource = new ArrayList<>();
		versionJeu = new ArrayList<>();
		initialState = factory.EtatFactory.createEtat();
	}
	
	private void loadFile(String pathVersion) throws IOException {
		clear();
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
			String s =stream.next();
			ressource.add(new Ressource(s));
			
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
		initialState.setRessources(ress);
	}finally {
		stream.close();
		br.close();
		}
	}
	
	private Type toType(String nom) {
		if(nom.equals("Batiment")) {
			return Type.BATIMENT;
		}
		else if(nom.equals("Unite")){
			return Type.UNITE;
		}
		else {
			return Type.TECHNOLOGIE;	
		}
	}
	
}