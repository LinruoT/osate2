/*
 * generated by Xtext
 */
package org.osate.verify;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class VerifyUiInjectorProvider implements IInjectorProvider {
	
	@Override
	public Injector getInjector() {
		return org.osate.verify.ui.internal.VerifyActivator.getInstance().getInjector("org.osate.verify.Verify");
	}
	
}