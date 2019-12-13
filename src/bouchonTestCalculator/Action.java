package bouchonTestCalculator;

import java.util.ArrayList;
import java.util.List;

import itf.IAction;
import itf.IUnite;

public class Action implements IAction{
	/** 
	* (non-Javadoc)
	* @see IAction#getConstructedUnite()
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	
	private int tempsAttente=-1;
	
	private IUnite unite = null;
	
	
	public Action(int time)
	{
		tempsAttente = time;
	}
	
	public Action(IUnite unite)
	{
		this.unite=unite;
	}
	
	public IUnite getConstructedUnite() {
		return unite;
	}

	/** 
	* (non-Javadoc)
	* @see IAction#getWaitedTime()
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Integer getWaitedTime() {
		return tempsAttente;
	}
}
