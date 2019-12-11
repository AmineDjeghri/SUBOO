package bouchons;

import itf.IRessource;

public class RessourceBouchon implements IRessource{

	int valeur;
	String name;
	
	public RessourceBouchon(String name, int valeur)
	{
		this.name=name;
		this.valeur=valeur;
	}
	
	@Override
	public void setValeur(Integer valeur) {
		this.valeur=valeur;
		
	}

	@Override
	public String getRessourceName() {
		return name;
	}

	@Override
	public Integer getValeur() {
		return valeur;
	}

}
