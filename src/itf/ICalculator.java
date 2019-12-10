/**
 * 
 */
package itf;

import java.util.Set;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sebma
* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface ICalculator {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param etatInitial
	* @param unite
	* @param ressource
	* @return
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<IAction> calculBO(IEtat etatInitial, Integer unite, Integer... ressource);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param unites
	* @param ressources
	* @return
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<IAction> calculBO(Set<Integer> unites, Integer... ressources);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param version
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void ChangerVersion(String version);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param BOObjectif
	* @return
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<IAction> VerifierValidite(IAction... BOObjectif);
}