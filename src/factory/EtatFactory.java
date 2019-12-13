package factory;

import etat.Etat;
import itf.IEtat;

public class EtatFactory {

	public static IEtat createEtat()
	{
		return new Etat();
	}
}
