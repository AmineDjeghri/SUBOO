/**
 * 
 */
package calculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import factory.ActionFactory;
import itf.IAction;
import itf.ICalculator;
import itf.IEntite;
import itf.IEtat;
import itf.IUnite;
import itf.IVersion;
import version.VersionSingleton;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author sebma
* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class Calculator implements ICalculator{
	
	public Calculator() {
		
	}
	
	/**
	 * Fonction qui va verifier un BO fournit par l'utilisateur afin de detreminer si ce BO est valide.
	 * Si celui ci ne l'ai pas, le corrige et lui renvoie un BO valide.
	 * @param BOCree: list d'action creer par l'utilisateur que le programme va verifier
	 * @return BO: liste d'action verifier et corriger, ci besoin, a l'utilisateur pour un BO valide
	 * @throws IOException 
	 */
	public List<IAction> verifierValidite(List <IAction> BOCree) throws IOException {
		//On recupere l'etat initial du jeu
		IEtat etat = VersionSingleton.getIversion().getEtatInitial(); 
		//On recupere la liste des unites du jeu. On se base a son indice dans le tableau pour regarder la list envoye par l'utilisateur 
		List<IUnite> listUnite = VersionSingleton.getIversion().getUnites();
		//On recupere la liste et quantité des unites presentes dans l'etat
		List<IEntite> listEntiteEtat = etat.getEntite();
		
		for (IAction action : BOCree) {
			if (action.getConstructedUnite()!=null) {
				IUnite unite = action.getConstructedUnite();
				List<IUnite> prerequis = unite.getPrerequis();
				for (int a = 0; a<prerequis.size(); a++) {//pour chaque prerequis je vais regarder si il est dans l'etat
					boolean presente = false;//variable qui passe a true si le prerequis est present
					for (IEntite ent : listEntiteEtat) {
						if (ent.getIdentite().getNom()==prerequis.get(a).getNom()) {
							presente=true;
						}
					}
					if (presente == false) {
						etat=modifierBo(etat,prerequis.get(a));
					}
				}
			}
			etat=etat.addAction(action);
		}
		
		List<IAction> BO = etat.getBuildOrder();//demande a etat le BO final
		return (List<IAction>) BO;
	}
	
	private IEtat modifierBo(IEtat etat, IUnite uniteManquante) throws IOException {
		//On recupere la liste et quantité des unites presentes dans l'etat
		List<IEntite> listEntiteEtat = etat.getEntite();
		IUnite unite = uniteManquante;
		List<IUnite> prerequis = unite.getPrerequis();
		if (prerequis!=null) {
			for (int a = 0; a<prerequis.size(); a++) {//pour chaque prerequis je vais regarder si il est dans l'etat
				boolean presente = false;//variable qui passe a true si le prerequis est present
				for (IEntite ent : listEntiteEtat) {
					if (ent.getIdentite().getNom()==prerequis.get(a).getNom()) {
						presente=true;
					}
				}
				if (presente == false) {
					etat=modifierBo(etat,prerequis.get(a));
				}
				etat=etat.addAction(ActionFactory.createAction(uniteManquante));
				listEntiteEtat = etat.getEntite();
			}
		}
		else {
			etat=etat.addAction(ActionFactory.createAction(uniteManquante));
			listEntiteEtat = etat.getEntite();
		}
		
		return etat;
	}

	/**
	 * Fonction qui prend un objectif a atteindre et un etat afin de calculer un BO valide.
	 * Return un BO valide selon les parametre fournis.
	 * @param UniteObjectif : List determinant les unite que l'on veux obtenir dans l'etat Objectif
	 * @param ressourceObjectif: List determinant les ressource que l'on souhaite obtenir dans l'etat Objectif
	 * @param etat: etat dans lequel se trouve le jeu au depart
	 * @return BO: Une liste d'IAction (un BO valide)
	 * @throws IOException 
	 */
	private IEtat calculBO(List <Integer> uniteObjectif, List <Integer> ressourceObjectif, IEtat etat) throws IOException {
		//On recupere la liste des unites du jeu. On se base a son indice dans le tableau pour regarder la lists  envoye par l'utilisateur 
		List<IUnite> listUnite = VersionSingleton.getIversion().getUnites();
		//On recupere la liste et quantité des unites presentes dans l'etat
		List<IEntite> listEntiteEtat = etat.getEntite();
		for (int j=0; j<listEntiteEtat.size();j++) {
		}
		
		//ajout de chaque unité
		for (int i=0; i<uniteObjectif.size(); i++) {
			//si une unité est demandé
			if (uniteObjectif.get(i)!=0){
				IUnite unite=listUnite.get(i);
				//je demande ses prerequis
				List<IUnite> prerequis = unite.getPrerequis();
				if (prerequis.size()>0) {
					for (int a = 0; a<prerequis.size(); a++) {//pour chaque prerequis je vais regarder si il est dans l'etat
						boolean presente = false;//variable qui passe a true si le prerequis est present
						for (IEntite ent : listEntiteEtat) {
							if (ent.getIdentite().getNom()==prerequis.get(a).getNom()) {
								presente=true;
							}
						}
						if (presente == false) {
							etat=modifierBo(etat,prerequis.get(a));
						}
						//nombre de fois ou l'unite a déjà été construite plus tot (par exemple en prerequis manquant)
						int nbDejaLa=0;
						//on parcoure la liste des entité présente dans l'eetat pour voir le nombr ede fois quellee se trouve
						for (IEntite ent : listEntiteEtat){
							//si l'entité et l'unité demander se correspondant
							if (ent.getIdentite().getNom()==listUnite.get(i).getNom()) {
								nbDejaLa++;
							}
						}
						for (int j=0; j<uniteObjectif.get(i)-nbDejaLa;j++) {
							etat=etat.addAction(ActionFactory.createAction(unite));
						}
						listEntiteEtat = etat.getEntite();
					}
				}
				else {
					etat=etat.addAction(ActionFactory.createAction(unite));
					listEntiteEtat = etat.getEntite();
				}
			}
		}
		
		return etat;
	}
	
	
	
	/**
	 * Fonction qui prend un objectif a atteindre afin de calculer un BO valide.
	 * Return un BO valide selon les parametre fournis.
	 * @param UniteObjectif : List determinant les unite que l'on veux obtenir dans l'etat Objectif
	 * @param ressourceObjectif: List determinant les ressource que l'on souhaite obtenir dans l'etat Objectif
	 * @return BO: Une liste d'IAction (un BO valide)
	 * @throws IOException 
	 */
	public List<IAction> calculBO(List <Integer> uniteObjectif, List <Integer> ressourceObjectif) throws IOException {
		//On recupere l'etat initial du jeu
		IEtat etat = VersionSingleton.getIversion().getEtatInitial();
		//on appel la fonction pour calculer le BO avec l'etat initial en etat de depart
		etat=calculBO(uniteObjectif, ressourceObjectif, etat);
		//initialisation de la list d'action a retourner
		List<IAction> BO = etat.getBuildOrder();//demande a etat le BO final
		return (List<IAction>) BO; 
	}
	
	
	/**
	 * Fonction qui prend un etat initial ainsi qu'un objectif a atteindre afin de calculer un BO valide.
	 * Return un BO valide selon les parametre fournis.
	 * @param etatInit : List determinant un etat initial
	 * @param UniteObjectif : List determinant les unite que l'on veux obtenir dans l'etat Objectif
	 * @param ressourceObjectif: List determinant les ressource que l'on souhaite obtenir dans l'etat Objectif
	 * @return BO: Une liste d'IAction (un BO valide)
	 * @throws IOException 
	 */
	public List<IAction> calculBO(List <IAction> etatInit, List <Integer> uniteObjectif, List <Integer> ressourceObjectif) throws IOException {
		//On recupere l'etat initial du jeu
		IEtat etat = VersionSingleton.getIversion().getEtatInitial();
		List<IAction> BO = new ArrayList <IAction> (); 
		
		//on ajoute toute les action de la liste d'action pour creer l'etat dans le quel commence le BO
		for (IAction action : etatInit) {
			etat=etat.addAction(action);
		}
		
		//BO initial qui faudra enlever du BO retourne
		List<IAction> BOInit = etat.getBuildOrder();
		
		//on appel la fonction pour calculer le BO avec l'etat de depart que l'on viens de creer
		etat=calculBO(uniteObjectif, ressourceObjectif, etat);
		
		List<IAction> BOTotal = etat.getBuildOrder();//demande a etat le BO final
		
		for (int i = BOInit.size(); i<BOTotal.size(); i++) {
			BO.add(BOTotal.get(i));
		}
		
		return (List<IAction>) BO; 
	}
	
	
	/**
	 * Fonction qui va envoyer une demande de chargement pour une nouvelle version au composant Version
	 * @param version : nom de la version a changer
	 * @return Un boolean signalant si la version a ete charger ou non
	 * @throws IOException 
	 */
	public Boolean ChangerVersion(String version) throws IOException {
		return VersionSingleton.getIversion().chargerVersion(version);
	}
	
}