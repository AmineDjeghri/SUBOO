/**
 * 
 */
package itf;

import java.util.List;
import java.util.Set;


public interface IVersion {
	
	public List<IRessource> getRessources();

	
	public IEtat getEtatInitial();

	
	public List<IUnite> getUnites();

	
	public Boolean chargerVersion(String version);	
}