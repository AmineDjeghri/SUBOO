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
	
	private IEtat etatPrecedent;
	private List<IEtat> etatsSuivants;
	private List<IEntite> estComposeDe;
	
	
	
	
	
 

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
	@Override
	public IEtat addAction(IAction action) {
		

		Etat next = new Etat(this);
		
		etatsSuivants.add(next);
		
		int time = 1;
		if(action.getConstructedUnite()==null)
			time=action.getWaitedTime();
		
		ArrayList<IRessource> lr = (ArrayList<IRessource>) VersionSingleton.getIversion().getRessources();
		List<IEntite> le = new ArrayList<IEntite>();
		
		if(action.getConstructedUnite()!=null)
		{
			//On cherche qui va construire
			for(IEntite e : estComposeDe)
			{
				if(e.isDisponible())
				{
					if(action.getConstructedUnite().getConstructorsList().contains(e.getIdentite()))
					{
						
					}
				}
					
			}
			
			//Ajout unite à la liste
			le.add(EntiteFactory.createEntite(action.getConstructedUnite()));
			
			//On modifie les ressources en fonction
			for(int i=0;i<lr.size();i++)
			{
				lr.get(i).setValeur(ressources.get(i).getValeur()-action.getConstructedUnite().getCout().get(i).getValeur());
			}
		}
		
		for(IEntite e : estComposeDe)
		{
			le.add(e.addTime(time));
		}
		
		
		
		
		
		next.setEntite(le);
		
		
		
		
		//la le précedant sera le meme que this :/
		
		return null;
		
		
		
		
	}
	@Override
	public List<IAction> getBuildOrder() {
		List<IAction> bo= new ArrayList<>();
		bo.add(action);
		
		if(etatPrecedent!=null)
			bo.addAll(etatPrecedent.getBuildOrder());
		
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
	
	
	


	
	

}