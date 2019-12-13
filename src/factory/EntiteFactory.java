package factory;

import entite.Entite;
import itf.IEntite;
import itf.IUnite;

public class EntiteFactory {

	public static IEntite createEntite(IUnite unite)
	{
		return new Entite(unite);
	}

}
