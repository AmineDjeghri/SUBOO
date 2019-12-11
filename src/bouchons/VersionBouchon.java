package bouchons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import itf.IEtat;
import itf.IRessource;
import itf.IUnite;
import itf.IVersion;

public class VersionBouchon implements IVersion{
	
	ArrayList<IUnite> unites = new ArrayList<IUnite>();
	ArrayList<IRessource> ressources = new ArrayList<IRessource>();
	
	public VersionBouchon()
	{
		unites.add(new UniteBouchon("Soldat", 0));
		unites.add(new UniteBouchon("Boss", 0));
		unites.add(new UniteBouchon("Ouvrier", 0));
		unites.add(new UniteBouchon("Ferme", 1));
		unites.add(new UniteBouchon("Caserne", 1));
		unites.add(new UniteBouchon("Arme en Fer", 3));
		unites.add(new UniteBouchon("Outil en Fer", 3));
		
		ressources.add(new RessourceBouchon("Nouriture",0));
		ressources.add(new RessourceBouchon("Bois",0));

	}

	@Override
	public List<IRessource> getRessources() {
		return ressources;
	}

	@Override
	public IEtat getEtatInitial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IUnite> getUnites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean chargerVersion(String version) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
