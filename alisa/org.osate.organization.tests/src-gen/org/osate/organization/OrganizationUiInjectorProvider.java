/*
 * generated by Xtext
 */
package org.osate.organization;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class OrganizationUiInjectorProvider implements IInjectorProvider {
	
	@Override
	public Injector getInjector() {
		return org.osate.organization.ui.internal.OrganizationActivator.getInstance().getInjector("org.osate.organization.Organization");
	}
	
}