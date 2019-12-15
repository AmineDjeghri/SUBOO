/**
 * 
 */
package version;

import itf.IVersion;


public class VersionSingleton {
	

	
	public static IVersion getIversion() {
		
		return Version.getInstanceIVersion();
	}
}