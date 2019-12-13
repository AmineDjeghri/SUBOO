/**
 * 
 */
package bouchonTestCalculator;

import itf.IEntite;
import static itf.IUnite.*;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Set;

import entite.EtatEntite;
import itf.IUnite;

public class Entite implements IEntite {
	private IEntite construit;
	private IUnite identite;
	private Integer tpsRestant;

	
	public Entite(IUnite unite) {
		construit=null;
		identite=unite;
		tpsRestant=0;
		setDisponible();
	}
	

	
	public void setIdentite(IUnite unite) {
		identite = unite;
	}

	public Boolean isDisponible() {
		return true;
	}

	
	public Boolean isEnConstruction() {
		return true;
	}

	public Boolean isMobilise() {
		return true;
	}

	public void setTempsRestant(Integer temps) {
		tpsRestant = temps;
	}

	
	public IUnite getIdentite() {
		return identite;
	}

	
	public Integer getTempsRestant() {
		return tpsRestant;
	}


	@Override
	public void setConstruit(IEntite construit) {
		this.construit=construit;
		
	}


	@Override
	public IEntite getConstruction() {
		return construit;
	}
	
	public void setEtat(EtatEntite e)
	{

	}

	@Override
	public IEntite addTime(int time) {
		Entite newEntite = new Entite(identite);
		
		int nTime = tpsRestant - time;
		
		if(nTime <= 0)
		{
			newEntite.setDisponible();
			newEntite.setTempsRestant(0);
		}
		else
		{
			newEntite.setTempsRestant(nTime);
			newEntite.setConstruit(this.getConstruction());

		}
		
		return newEntite;
	}



	@Override
	public void setDisponible() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setEnConstruction() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setMobilise() {
		// TODO Auto-generated method stub
		
	}
}