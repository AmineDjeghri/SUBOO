package bouchons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import factory.ActionFactory;
import itf.IAction;
import itf.ICalculator;
import itf.IEtat;
import itf.IUnite;
import version.VersionSingleton;

public class CalculatorBouchon implements ICalculator{

	@Override
	public List<IAction> calculBO(List <IAction> etatInitial, List<Integer> unite, List<Integer> ressource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IAction> calculBO(List<Integer> unites, List<Integer> ressources) throws IOException {
		ArrayList<IAction> la = new ArrayList<IAction>();
		ArrayList<IUnite> lu = (ArrayList<IUnite>) VersionSingleton.getIversion().getUnites();
		la.add(ActionFactory.createAction(lu.get(0)));
		la.add(ActionFactory.createAction(50));
		la.add(ActionFactory.createAction(lu.get(3)));
		la.add(ActionFactory.createAction(20));
		la.add(ActionFactory.createAction(lu.get(2)));
		la.add(ActionFactory.createAction(30));
		
		return la;
		
	}

	@Override
	public Boolean ChangerVersion(String version) {
		return true;
		
	}

	@Override
	public List<IAction> verifierValidite(List <IAction> BOCree) {
		// TODO Auto-generated method stub
		return null;
	}

}
