package bouchons;

import java.util.List;
import java.util.Set;

import itf.IRessource;
import itf.IUnite;
import version.Type;

public class UniteBouchon implements IUnite {

	String name;
	int type;
	
	public UniteBouchon(String name, int type)
	{
		this.name=name;
		this.type=type;
	}
	
	@Override
	public String getNom() {
		return name;
	}

	@Override
	public List<IUnite> getConstructorsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IRessource> getCout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IUnite> getPrerequis() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IRessource> getRessourceProd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int typeToInt() {
		return type;
	}

}
