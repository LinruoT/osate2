package org.osate.ge.errormodel.pictogramHandlers;

import javax.inject.Named;
import org.osate.ge.ext.Names;
import org.osate.ge.ext.annotations.IsApplicable;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelSubclause;

public class ErrorModelSubclausePictogramHandler {	
	@IsApplicable
	public boolean isApplicable(final @Named(Names.BUSINESS_OBJECT) ErrorModelSubclause subclause) {
		return true;
	}
}
