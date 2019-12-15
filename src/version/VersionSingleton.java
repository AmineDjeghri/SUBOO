/**
 * 
 */
package version;

import version.Version;

import java.io.IOException;

import itf.IVersion;


public class VersionSingleton {
	
	private static Version version = null;

	
	public static IVersion getIversion() throws IOException {
		
		return Version.getInstanceIVersion();
	}
}