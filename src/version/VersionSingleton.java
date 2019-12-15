/**
 * 
 */
package version;

import java.io.IOException;

import itf.IVersion;


public class VersionSingleton {
	

	
	public static IVersion getIversion() throws IOException {
		
		return Version.getInstanceIVersion();
	}
}