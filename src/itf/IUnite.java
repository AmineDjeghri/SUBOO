/**
 * 
 */
package itf;

import java.util.List;
import java.util.Map;


public interface IUnite {
	
	public String getNom();

	
	public List<IUnite>  getConstructorsList();

	
	public List<IRessource> getCout();

	
	public List<IUnite> getPrerequis();

	
	public version.Type getType();
	
	public int typeToInt();

	
	public List<IRessource> getRessourceProd();
	
	public int getTempsConstruc();
}