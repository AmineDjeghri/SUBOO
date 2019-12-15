/**
 * 
 */
package itf;

import java.io.IOException;
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
	 * @throws IOException 
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<IAction> calculBO(List <IAction> etatInit, List <Integer> uniteObjectif, List <Integer> ressourceObjectif) throws IOException;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param unites
	* @param ressources
	* @return
	 * @throws IOException 
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<IAction> calculBO(List <Integer> uniteObjectif, List <Integer> ressourceObjectif) throws IOException;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param version
	 * @return 
	 * @throws IOException 
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Boolean ChangerVersion(String version) throws IOException;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param BOObjectif
	* @return
	 * @throws IOException 
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<IAction> verifierValidite(List <IAction> BOCree) throws IOException;
}