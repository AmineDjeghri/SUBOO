package factory;

import action.Action;
import itf.IAction;
import itf.IUnite;

public class ActionFactory {
	
	public static IAction createAction(int time)
	{
		return new Action(time);
	}
	
	public static IAction createAction(IUnite unite)
	{
		return new Action(unite);
	}
}
