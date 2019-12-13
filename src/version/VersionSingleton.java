/**
 * 
 */
package version;

import bouchonTestCalculator.Version;
import itf.IVersion;


public class VersionSingleton {
	
	private static Version version = null;

	
	public static IVersion getIversion() {
		
		return Version.getInstanceIVersion();
	}
}