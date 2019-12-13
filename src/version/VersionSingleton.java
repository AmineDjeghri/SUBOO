/**
 * 
 */
package version;

import bouchons.VersionBouchon;
import itf.IVersion;


public class VersionSingleton {
	
	private static Version version = null;

	
	public static IVersion getIversion() {
		
		return Version.getInstanceIVersion();
	}
}