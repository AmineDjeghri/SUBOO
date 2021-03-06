
package version;

import itf.IRessource;


public class Ressource implements IRessource {

	private String name;

	private Integer quantite;



	public Ressource (IRessource iRessource) {
		this.name = iRessource.getRessourceName();
		this.quantite = iRessource.getValeur();
	}
	public Ressource(String name){
		this.name = name;
		quantite = 0;
	}
	
	public Ressource(String name, Integer quantite) {
		this.name = name;
		this.quantite = quantite;
	}
	

	public void setValeur(Integer valeur) {
		quantite = valeur;
	}

	
	public String getRessourceName() {
		return name;
	}

	public Integer getValeur() {
		return quantite;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ressource other = (Ressource) obj;
		if (!name.equals(other.name))
			return false;
		if (!quantite.equals(other.quantite))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(name);
		sb.append(" : ");
		sb.append(quantite);
		return sb.toString();
	}
}