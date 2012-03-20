/*
* generated by Xtext
*/
package org.osate.xtext.aadl2.propertyset.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.osate.xtext.aadl2.propertyset.services.PropertysetGrammarAccess;

public class PropertysetParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private PropertysetGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.osate.xtext.aadl2.propertyset.parser.antlr.internal.InternalPropertysetParser createParser(XtextTokenStream stream) {
		return new org.osate.xtext.aadl2.propertyset.parser.antlr.internal.InternalPropertysetParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "PropertySet";
	}
	
	public PropertysetGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(PropertysetGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
