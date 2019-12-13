package bouchonTestCalculator;

import java.util.ArrayList;
import java.util.List;

import itf.IRessource;
import itf.IUnite;
import version.Type;

public class Unite implements IUnite{

	private String nom;
	private List<IUnite> prerequis;
	
	public Unite(String nom) {
		this.nom = nom;
		this.prerequis = new ArrayList <IUnite> ();
	}
	
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public List<IUnite> getConstructorsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IUnite> getPrerequis() {
		// TODO Auto-generated method stub
		return prerequis;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int typeToInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<IRessource> getCout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IRessource> getRessourceProd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTempsConstruc() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setTempsConstruc(int nextInt) {
		// TODO Auto-generated method stub
		
	}

	public void setType(Type type) {
		// TODO Auto-generated method stub
		
	}

	public void setPrerequis(IUnite unite) {
		prerequis.add(unite);
		
	}

	public void setConstructorsList(Object object) {
		// TODO Auto-generated method stub
		
	}

	public void setRessourceProd(Object object) {
		// TODO Auto-generated method stub
		
	}

	public void setCout(Object object) {
		// TODO Auto-generated method stub
		
	}


}
