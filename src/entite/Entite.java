/**
 * 
 */
package entite;

import itf.IEntite;
import static itf.IUnite.*;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Set;
import itf.IUnite;

public class Entite implements IEntite {
	private IEntite construit;
	private IUnite nomUnite;
	private Integer tpsRestant;
	private EtatEntite etatEntite;

	
	public Entite(IUnite unite) {
		construit=null;
		nomUnite=unite;
		tpsRestant=null;
		etatEntite=null;
	}
	
	public void setDisponible() {
		etatEntite=EtatEntite.DISPONIBLE;
	}

	
	public void setEnConstruction() {
		etatEntite=EtatEntite.ENCONSTRUCTION;
	}

	public void setMobilise() {
		etatEntite=EtatEntite.MOBILISE;
	}

	
	public void setIdentite(IUnite unite) {
		nomUnite = unite;
	}

	public Boolean isDisponible() {
		return (etatEntite == EtatEntite.DISPONIBLE);
	}

	
	public Boolean isEnConstruction() {
		return (etatEntite == EtatEntite.ENCONSTRUCTION);
	}

	public Boolean isMobilise() {
		return (etatEntite == EtatEntite.MOBILISE);
	}

	public void setTempsRestant(Integer temps) {
		tpsRestant = temps;
	}

	
	public IUnite getIdentite() {
		return nomUnite;
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
}