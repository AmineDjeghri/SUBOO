/**
 * 
 */
package itf;

import java.util.List;
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
	public List<IAction> calculBO(List <IAction> etatInit, List <Integer> uniteObjectif, List <Integer> ressourceObjectif);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param unites
	* @param ressources
	* @return
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<IAction> calculBO(List <Integer> uniteObjectif, List <Integer> ressourceObjectif);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param version
	 * @return 
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Boolean ChangerVersion(String version);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param BOObjectif
	* @return
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<IAction> verifierValidite(List <IAction> BOCree);
}