package test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import itf.IRessource;
import itf.IUnite;
import itf.IVersion;
import version.Ressource;
import version.Unite;

public class TestVersion {
	
	
	@Test
	public final void testFile() {
		IUnite 		ferme	= new Unite("Ferme");
		IUnite		caserne = new Unite("Caserne");
		IUnite		centre	= new Unite("Centre");
		IUnite		hall	= new Unite("Hall");
		IUnite		ouvrier = new Unite("Ouvrier");
		IUnite		soldat  = new Unite("Soldat");
		IUnite		boss	= new Unite("Boss");
		IRessource ir = new Ressource("Nourriture", 0);
		IUnite	u = new Unite("Soldat");
		List<IUnite> 	 liu = new ArrayList<>();
		List<IRessource> lir = new ArrayList<>();
		lir.add(new Ressource("Or", 0));
		lir.add(new Ressource("Nourriture", 0));
		liu.add(centre);
		liu.add(ferme);
		liu.add(caserne);
		liu.add(hall);
		liu.add(ouvrier);
		liu.add(soldat);
		liu.add(boss);
		System.out.println(lir.get(1) + " " + ir);
		
		
		//On test les méthodes equals
		assertEquals(true, ir.equals(lir.get(1)));
		assertEquals(true, u.equals(soldat));
		
		IVersion v;
		
		try {
			v = version.Version.getInstanceIVersion();
			
			// ON vérifie que la liste des unités et des ressources de base soient correctes
			assertEquals(true, equalList(lir, v.getRessources()));
			assertEquals(true, equalList(liu, v.getUnites()));
			lir.get(0).setValeur(50);
			// on vérifie que l'état initial est correcte
			assertEquals(lir, v.getEtatInitial().getRessources());
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	private <V> boolean equalList ( List<V> l, List<V> k) {
		int len = l.size();
		if(l.size() != k.size())
			return false;
		System.out.println("Même taille");
		for(int i = 0; i < len ; i++) {
			if(!l.get(i).equals(k.get(i)))
				return false;
		}
		return true;
	}
}
