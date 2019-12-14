/**
 * 
 */
package bouchonTestCalculator;

import itf.IEtat;
import static itf.IEntite.*;
import static itf.IVersion.*;
import static itf.IUnite.*;
import static itf.IRessource.*;
import static itf.IAction.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import action.Action;
import entite.EtatEntite;
import factory.EntiteFactory;
import itf.IEntite;
import itf.IAction;
import itf.IRessource;
import itf.IUnite;
import itf.IVersion;
import version.VersionSingleton;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sebma
* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class Etat implements IEtat {
	
	private IAction action = null;
	private List<IRessource> ressources;
	
	private IEtat etatPrecedent = null;
	private List<IEtat> etatsSuivants = new ArrayList<IEtat>();
	private List<IEntite> estComposeDe = new ArrayList<IEntite>();
	
	
	public Etat() {
		
	}

	public Etat(IEtat etatPrecedent) {
		this.etatPrecedent = etatPrecedent;
	}
	
	public List<IRessource> getRessources() {
		return ressources;
	}
	public void setRessources(List<IRessource> ressources) {
		this.ressources = ressources;
	}
	public void setAction(IAction action) {
		this.action=action;
	}
	@Override
	public IEtat addAction(IAction action) {
		
		Etat next = new Etat(this);
		next.addEntite(new Entite(action.getConstructedUnite()));
		
		etatsSuivants.add(next);
		etatsSuivants.get(etatsSuivants.size()-1).setAction(action);
		
		return next;
	}
	public IAction getAction() {
		return action;
	}
	@Override
	public List<IAction> getBuildOrder() {
		List<IAction> bo= new ArrayList<>();
		
		if(etatPrecedent!=null && etatPrecedent.getAction()!=null)
			bo=etatPrecedent.getBuildOrder();
			
		
		bo.add(action);
		
		return bo;
	}
	
	@Override
	public List<IEntite> getEntite() {
		List<IEntite> listEntite= new ArrayList<>();
		
		if(etatPrecedent!=null)
			listEntite.addAll(etatPrecedent.getEntite());
			
		listEntite.addAll(estComposeDe);
		return listEntite;
	}
	@Override
	public List<IEtat> getNextsEtats() {
		return etatsSuivants;
	}
	@Override
	public void addEntite(IEntite entite) {
		estComposeDe.add(entite);
		
	}
	@Override
	public IEtat getBackEtat() {
		return etatPrecedent;
	}
	
	public void setEntite(List<IEntite> entites)
	{
		estComposeDe = entites;
	}

	
	
	


	
	

}