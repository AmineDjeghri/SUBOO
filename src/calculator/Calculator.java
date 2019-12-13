/**
 * 
 */
package calculator;

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
	 */
	public List<IAction> verifierValidite(List <IAction> BOCree) {
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
						etat=modifierBo(action,etat,prerequis.get(a));
					}
				}
				etat=etat.addAction(action);
			}
		}
		
		List<IAction> BO = etat.getBuildOrder();//demande a etat le BO final
		return (List<IAction>) BO;
	}
	
	private IEtat modifierBo(IAction action, IEtat etat, IUnite uniteManquante) {
		//On recupere la liste et quantité des unites presentes dans l'etat
		List<IEntite> listEntiteEtat = etat.getEntite();
		IUnite unite = uniteManquante;
		List<IUnite> prerequis = unite.getPrerequis();
		for (int a = 0; a<prerequis.size(); a++) {//pour chaque prerequis je vais regarder si il est dans l'etat
			boolean presente = false;//variablee qui passe a true si le prerequis est present
			for (IEntite ent : listEntiteEtat) {
				if (ent.getIdentite().getNom()==prerequis.get(a).getNom()) {
					presente=true;
				}
			}
			if (presente == false) {
				etat=modifierBo(action,etat,prerequis.get(a));
			}
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
	 */
	private IEtat calculBO(List <Integer> uniteObjectif, List <Integer> ressourceObjectif, IEtat etat) {
		//On recupere la liste des unites du jeu. On se base a son indice dans le tableau pour regarder la lists  envoye par l'utilisateur 
		List<IUnite> listUnite = VersionSingleton.getIversion().getUnites();
		//On recupere la liste et quantité des unites presentes dans l'etat
		List<IEntite> listEntiteEtat = etat.getEntite();
		
		boolean listeVide = false;
		//tant que tous les elements de la liste ne seront pas a 0
		while (!listeVide) {
			listeVide=true;
			int i=0;
			//on parcours uniteObjectif
			while (i<uniteObjectif.size()) {
				if (uniteObjectif.get(i)!=0) {
					listeVide=false; //la liste n'est donc pas encore vide
					IUnite unite = listUnite.get(i);//on regarde de quel unite il s'agit
					List<IUnite> prerequis = unite.getPrerequis();//on regarde de quel prerequis elle a besoin
					
					//phase de test pour les prerequis
					boolean tousPresente = true;//variable qui vas passer a false si un prerequi n'est pas present dans l'etat
					List <Integer> indiceUnitePrerequisBesoin = new ArrayList<Integer>();
					for (int a = 0; a<prerequis.size(); a++) {//pour chaque prerequis je vais regarder si il est dans l'etat
						boolean presente = false;//variablee qui passe a true si le prerequis est present
						int b=0;
						for (IEntite ent : listEntiteEtat) {
							if (ent.getIdentite().getNom()==prerequis.get(a).getNom()) {
								presente=true;
							}
							if (presente == false) {
								tousPresente=false;//il manque un prerequi dans l'etat
								indiceUnitePrerequisBesoin.add(b);//ajout l'indice de position de l'unite dans la liste des prerequis dont on a besoin
							}
							b++;
						}
					}
					//fin de la phase de test des prerequis
					
					//si prerequis present dans l'etat, alors ajouter les actions pour crer les unités dans l'etat et mettre a jour le BO
					if (tousPresente) {
						//boucle autemps de fois que l'on doit construire l'unite
						for (int b=0;b<uniteObjectif.get(i);b++) {
							IAction action= ActionFactory.createAction(listUnite.get(i));
							etat=etat.addAction(action);//ajoute l'action dans l'etat ainsi que l'unite concerne
						}
					}
					//Sinon rajoute les unités de prérequis dont on a besoin dans uniteObjectif si celle ci n'a pas été prévue dans le bo
					else {
						for(int a=0; a<indiceUnitePrerequisBesoin.size();a++) {
							if (uniteObjectif.get(indiceUnitePrerequisBesoin.get(a))==0){
								uniteObjectif.set(indiceUnitePrerequisBesoin.get(a),1);
							}
						}
					}
					listEntiteEtat = etat.getEntite();//MAJ de la liste des entites presentes dans l'etat
					
				}
				i++;
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
	 */
	public List<IAction> calculBO(List <Integer> uniteObjectif, List <Integer> ressourceObjectif) {
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
	 */
	public List<IAction> calculBO(List <IAction> etatInit, List <Integer> uniteObjectif, List <Integer> ressourceObjectif) {
		//On recupere l'etat initial du jeu
		IEtat etat = VersionSingleton.getIversion().getEtatInitial();
		
		//on ajoute toute les action de la liste d'action pour creer l'etat dans le quel commence le BO
		for (IAction action : etatInit) {
			etat=etat.addAction(action);
		}
		
		//on appel la fonction pour calculer le BO avec l'etat de depart que l'on viens de creer
		etat=calculBO(uniteObjectif, ressourceObjectif, etat);
		
		List<IAction> BO = etat.getBuildOrder();//demande a etat le BO final
		return (List<IAction>) BO; 
	}
	
	
	/**
	 * Fonction qui va envoyer une demande de chargement pour une nouvelle version au composant Version
	 * @param version : nom de la version a changer
	 * @return Un boolean signalant si la version a ete charger ou non
	 */
	public Boolean ChangerVersion(String version) {
		return VersionSingleton.getIversion().chargerVersion(version);
	}
	
}