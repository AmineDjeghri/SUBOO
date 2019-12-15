/**
 * 
 */
package etat;

import itf.IEtat;
import static itf.IEntite.*;
import static itf.IVersion.*;
import static itf.IUnite.*;
import static itf.IRessource.*;
import static itf.IAction.*;

import java.io.IOException;
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

	public Etat(IEtat etatPrecedent, IAction action) {
		this.etatPrecedent = etatPrecedent;
		this.action = action;
	}
	
	public List<IRessource> getRessources() {
		return ressources;
	}
	public void setRessources(List<IRessource> ressources) {
		this.ressources = ressources;
	}
	@Override
	public IEtat addAction(IAction action) throws IOException {
		

		Etat next = new Etat(this, action);
		
		etatsSuivants.add(next);
		
		int time = 1;
		if(action.getConstructedUnite()==null)
			time=action.getWaitedTime();
		
		ArrayList<IRessource> lr = (ArrayList<IRessource>) VersionSingleton.getIversion().getRessources();
		List<IEntite> le = new ArrayList<IEntite>();
		IEntite constructeur=null;
		if(action.getConstructedUnite()!=null)
		{
			//On cherche qui va construire
			/*
			for(IEntite e : estComposeDe)
			{
				if(e.isDisponible())
				{
					if(action.getConstructedUnite().getConstructorsList().contains(e.getIdentite()))
					{
						constructeur = e;
						constructeur.setMobilise();
						constructeur.setTempsRestant(action.getConstructedUnite().getTempsConstruc());
					}
				}
					
			}
			*/
			
			//Ajout unite à la liste
			le.add(EntiteFactory.createEntite(action.getConstructedUnite()));
			/*
			//On modifie les ressources en fonction
			for(int i=0;i<lr.size();i++)
			{
				lr.get(i).setValeur(ressources.get(i).getValeur()-action.getConstructedUnite().getCout().get(i).getValeur());
			}
			*/
		}
		/*
		//On ajoute les ressources produite
		for(IEntite e: estComposeDe)
		{
			for(int i=0;i<e.getIdentite().getRessourceProd().size();i++)
			{
				int mult = time - (e.getTempsRestant());
				if(mult<0)
					mult=0;
				lr.get(i).setValeur(lr.get(i).getValeur()+(e.getIdentite().getRessourceProd().get(i).getValeur())
						*mult);
			}
		}
		*/
		
		for(IEntite e : estComposeDe)
		{
			le.add(e.addTime(time));
		}
		/*
		//On remet le construteur en disponible dans l'etat courant
		if(constructeur!=null)
		{
			constructeur.setDisponible();
			constructeur.setTempsRestant(0);
		}
		*/
		
		
		next.setEntite(le);
		next.setRessources(lr);
		
		return next;
	}
	
	@Override
	public List<IAction> getBuildOrder() {
		List<IAction> bo= new ArrayList<>();
		
		if(etatPrecedent!=null && etatPrecedent.getAction()!=null) {
			bo=etatPrecedent.getBuildOrder();
		}
			
		
		bo.add(action);
		
		return bo;
	}
	
	@Override
	public List<IEntite> getEntite() {
		// TODO Auto-generated method stub
		return estComposeDe;
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

	@Override
	public void setAction(IAction action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IAction getAction() {
		return action;
	}
	
	
	


	
	

}