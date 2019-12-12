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

import java.util.List;
import java.util.Set;

import entite.EtatEntite;
import itf.IEntite;
import itf.IAction;
import itf.IRessource;
import itf.IUnite;
import itf.IVersion;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sebma
* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class Etat implements IEtat {

	


	private IAction actionPrecedante;
	private IUnite nomUnite;
	private List<IRessource> ressources;
	private IVersion version;
	
	private Etat etatPrecedent;
	private List<IEtat> etatsSuivants;
	private List<IEntite> estComposeDe;
	
	
	
	
	
 

	public List<IRessource> getRessources() {
		return ressources;
	}
	public void setRessources(List<IRessource> ressources) {
		this.ressources = ressources;
	}
	@Override
	public void addAction(IAction action) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<IAction> getBuildOrder() {
		// TODO Auto-generated method stub
		return null;
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
	
	
	


	
	

}