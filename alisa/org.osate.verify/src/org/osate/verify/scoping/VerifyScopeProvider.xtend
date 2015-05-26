/*
 * generated by Xtext
 */
package org.osate.verify.scoping

import org.osate.alisa.common.scoping.AlisaAbstractDeclarativeScopeProvider
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.util.SimpleAttributeResolver
import org.eclipse.xtext.scoping.impl.SimpleScope
import org.osate.verify.verify.VerificationActivity
import org.osate.reqspec.reqSpec.ReqSpecs
import static org.osate.reqspec.util.ReqSpecUtilExtension.*

/**
 * This class contains custom scoping description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation.html#scoping
 * on how and when to use it 
 *
 */
class VerifyScopeProvider extends AlisaAbstractDeclarativeScopeProvider {

//	def scope_ComputeDeclaration(VerificationActivity context, EReference reference) {
//		var result = IScope.NULLSCOPE
//		val req = context.requirement
//		val ReqSpecs reqspecs = containingReqSpecs(req)
//		if (!reqspecs.computes.isEmpty) {
//			result = new SimpleScope(result,
//				Scopes::scopedElementsFor(reqspecs.computes,
//					QualifiedName::wrapper(SimpleAttributeResolver::NAME_RESOLVER)), true)
//		}
//		if (!req.computes.empty) {
//			result = new SimpleScope(result,
//				Scopes::scopedElementsFor(req.computes,
//					QualifiedName::wrapper(SimpleAttributeResolver::NAME_RESOLVER)), true)
//		}
//		result
//	}

}