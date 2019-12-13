package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import itf.IEtat;
import itf.IRessource;
import itf.IUnite;
import version.Ressource;
import version.Type;
import version.Unite;

public class TestVersion {
	
	@Test
	public final void testFileLoad() {
		List<Unite> 	 liu = new ArrayList<>();
		List<IRessource> lir = new ArrayList<>();
		List<IUnite>	tmpU  = new ArrayList<>();
		IEtat 		     sta = factory.EtatFactory.createEtat();
		List<IRessource> tmpR = new ArrayList<>();
		Unite 		ferme	= new Unite("Ferme", 60, Type.BATIMENT);
		Unite		caserne = new Unite("Caserne", 100, Type.BATIMENT);
		Unite		centre	= new Unite("Centre", 200, Type.BATIMENT);
		Unite		hall	= new Unite("Hall", 120, Type.BATIMENT);
		Unite		ouvrier = new Unite("Ouvrier", 30, Type.UNITE);
		Unite		soldat  = new Unite("Soldat", 50, Type.UNITE);
		Unite		boss	= new Unite("Boss", 100, Type.UNITE);
		
		
		
		lir.add(new Ressource("Or", 50));
		lir.add(new Ressource("Nourriture", 0));
		liu.add(centre);
		liu.add(ferme);
		liu.add(caserne);
		liu.add(hall);
		liu.add(ouvrier);
		liu.add(soldat);
		liu.add(boss);
		// ON met les bonnes productions de ressources
		tmpR.add(new Ressource("Nourriture",6));
		centre.setRessourceProd(new ArrayList<>());
		ferme.setRessourceProd(new ArrayList<>());
		tmpR.add(new Ressource("Or",1));
		ouvrier.setRessourceProd(new ArrayList<>(tmpR));
		
		// On met les couts de constructions à jour pour chaque unite
		tmpR = new ArrayList<>();
		tmpR.add(new Ressource("Or", 300));
		centre.setCout(new ArrayList<>(tmpR));
		
		tmpR.get(0).setValeur(100);
		
		

		
		
	}
}
