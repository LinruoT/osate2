/*
* generated by Xtext
*/
package org.osate.assure;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class AssureStandaloneSetup extends AssureStandaloneSetupGenerated{

	public static void doSetup() {
		new AssureStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
