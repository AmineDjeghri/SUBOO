package factory;

import etat.Etat;
import itf.IEtat;

public class EtatFactory {

	public static IEtat createEntite()
	{
		return new Etat();
	}
}
