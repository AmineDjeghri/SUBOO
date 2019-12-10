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
import java.util.Set;
import itf.IEntite;
import itf.IAction;
import itf.IRessource;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sebma
* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class Etat implements IEtat {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private Etat etatPrecedent;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private Set<IEntite> listeEntites;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private IAction action;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private Set<Etat> etatSuivant;

	/** 
	* (non-Javadoc)
	* @see IEtat#setRessources(IRessource... ressources)
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void setRessources(IRessource... ressources) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré

		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see IEtat#getRessources()
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<IRessource> getRessources() {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see IEtat#addAction(IAction action)
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void addAction(IAction action) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré

		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see IEtat#getBuildOrder()
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<IAction> getBuildOrder() {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see IEtat#getEntite()
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<IEntite> getEntite() {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see IEtat#getNextsEtats()
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<IEtat> getNextsEtats() {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see IEtat#addEntite(IEntite entite)
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void addEntite(IEntite entite) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré

		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see IEtat#getBackEtat()
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public IEtat getBackEtat() {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré
		return null;
		// end-user-code
	}
}