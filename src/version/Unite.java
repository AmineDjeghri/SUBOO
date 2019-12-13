/**
 * 
 */
package version;

import itf.IUnite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import itf.IRessource;

public class Unite implements IUnite {
	
	private String nom;
	
	private int tempsConstruc;
	
	private List<IUnite> construitPar;
	
	private Type type;
	
	private List<IRessource> ressourceProd;
	
	private List<IUnite> prerequis;
	
	private List<IRessource> cout;

	
	public Unite(String nom, int tempsConstruc, List<IUnite> construitPar, Type type, List<IRessource> ressourceProd,
			List<IUnite> prerequis, List<IRessource> cout) {
		this.nom = nom;
		this.tempsConstruc = tempsConstruc;
		this.construitPar = construitPar;
		this.type = type;
		this.ressourceProd = ressourceProd;
		this.prerequis = prerequis;
		this.cout = cout;
	}
	
	public Unite(String nom) {
		this.nom = nom;
		this.tempsConstruc = 0;
		this.construitPar = null;
		this.type = null;
		this.ressourceProd = null;
		this.prerequis = null;
		this.cout = null;
	}
	public Unite(String nom, int tmpConst,  Type t) {
		this.nom = nom;
		this.tempsConstruc = tmpConst;
		this.construitPar = null;
		this.type = t;
		this.ressourceProd = null;
		this.prerequis = null;
		this.cout = null;
	}

	
	public String getNom() {
		return nom;
	}

	
	public List<IUnite> getConstructorsList() {
		return construitPar;
	}
	void setConstructorsList(List<IUnite> construitPar) {
		this.construitPar = construitPar;
	}

	
	public List<IRessource> getCout() {
		return cout;
	}
	
	
	void setCout(List<IRessource> r) {
		cout = r;
	}

	
	public List<IUnite> getPrerequis() {
		return prerequis;
	}
	
	
	void setPrerequis(List<IUnite> p) {
		prerequis = p;
	}

	
	public Type getType() {
		return type;
	}

	void setType(Type t) {
		type = t;
	}
	
	
	public int getTempsConstruc() {
		return tempsConstruc;
	}

	void setTempsConstruc(int tempsConstruc) {
		this.tempsConstruc = tempsConstruc;
	}	

	public List<IRessource> getRessourceProd() {
		return ressourceProd;
	}

	void setRessourceProd(List<IRessource> ressourceProd) {
		this.ressourceProd = ressourceProd;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unite other = (Unite) obj;
		if (! nom.equals(other.nom))
				return false;
		return true;
	}

	@Override
	public int typeToInt() {
		switch(type) {
		case BATIMENT:
			return 1;
		case UNITE:
			return 0;
		case TECHNOLOGIE:
			return 2;
		}
		return 0;
	}


	
}