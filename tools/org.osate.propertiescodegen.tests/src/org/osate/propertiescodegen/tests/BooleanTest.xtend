package org.osate.propertiescodegen.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.osate.aadl2.PropertySet
import org.osate.propertiescodegen.PropertiesCodeGen
import org.osate.testsupport.Aadl2InjectorProvider
import org.osate.testsupport.TestHelper

import static org.junit.Assert.assertEquals

@RunWith(XtextRunner)
@InjectWith(Aadl2InjectorProvider)
class BooleanTest {
	@Inject
	TestHelper<PropertySet> testHelper
	
	@Test
	def void testBoolean() {
		val otherPs = '''
			property set other_ps is
				other_boolean_type: type aadlboolean;
			end other_ps;
		'''
		val booleanTest = '''
			property set boolean_test is
				with other_ps;
				
				local_boolean_type: type aadlboolean;
				
				owned_boolean: aadlboolean applies to (all);
				referenced_boolean_local: boolean_test::local_boolean_type applies to (all);
				referenced_boolean_other: other_ps::other_boolean_type applies to (all);
				
				list_1_boolean: list of other_ps::other_boolean_type applies to (all);
				list_5_boolean: list of list of list of list of list of other_ps::other_boolean_type applies to (all);
			end boolean_test;
		'''
		val booleanTestClass = '''
			package boolean_test;
			
			import java.util.List;
			import java.util.Optional;
			import java.util.stream.Collectors;
			
			import org.osate.aadl2.Aadl2Package;
			import org.osate.aadl2.BooleanLiteral;
			import org.osate.aadl2.ListValue;
			import org.osate.aadl2.NamedElement;
			import org.osate.aadl2.Property;
			import org.osate.aadl2.PropertyExpression;
			import org.osate.aadl2.modelsupport.scoping.Aadl2GlobalScopeUtil;
			import org.osate.aadl2.properties.PropertyNotPresentException;
			import org.osate.pluginsupport.properties.CodeGenUtil;
			
			public class BooleanTest {
				public static Optional<Boolean> getOwnedBoolean(NamedElement lookupContext) {
					String name = "boolean_test::owned_boolean";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					try {
						PropertyExpression propertyExpression = lookupContext.getNonModalPropertyValue(property);
						PropertyExpression resolved = CodeGenUtil.resolveNamedValue(propertyExpression, lookupContext);
						return Optional.of(((BooleanLiteral) resolved).getValue());
					} catch (PropertyNotPresentException e) {
						return Optional.empty();
					}
				}
				
				public static PropertyExpression getOwnedBoolean_EObject(NamedElement lookupContext) {
					String name = "boolean_test::owned_boolean";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					return lookupContext.getNonModalPropertyValue(property);
				}
				
				public static Optional<Boolean> getReferencedBooleanLocal(NamedElement lookupContext) {
					String name = "boolean_test::referenced_boolean_local";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					try {
						PropertyExpression propertyExpression = lookupContext.getNonModalPropertyValue(property);
						PropertyExpression resolved = CodeGenUtil.resolveNamedValue(propertyExpression, lookupContext);
						return Optional.of(((BooleanLiteral) resolved).getValue());
					} catch (PropertyNotPresentException e) {
						return Optional.empty();
					}
				}
				
				public static PropertyExpression getReferencedBooleanLocal_EObject(NamedElement lookupContext) {
					String name = "boolean_test::referenced_boolean_local";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					return lookupContext.getNonModalPropertyValue(property);
				}
				
				public static Optional<Boolean> getReferencedBooleanOther(NamedElement lookupContext) {
					String name = "boolean_test::referenced_boolean_other";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					try {
						PropertyExpression propertyExpression = lookupContext.getNonModalPropertyValue(property);
						PropertyExpression resolved = CodeGenUtil.resolveNamedValue(propertyExpression, lookupContext);
						return Optional.of(((BooleanLiteral) resolved).getValue());
					} catch (PropertyNotPresentException e) {
						return Optional.empty();
					}
				}
				
				public static PropertyExpression getReferencedBooleanOther_EObject(NamedElement lookupContext) {
					String name = "boolean_test::referenced_boolean_other";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					return lookupContext.getNonModalPropertyValue(property);
				}
				
				public static Optional<List<Boolean>> getList1Boolean(NamedElement lookupContext) {
					String name = "boolean_test::list_1_boolean";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					try {
						PropertyExpression propertyExpression = lookupContext.getNonModalPropertyValue(property);
						PropertyExpression resolved = CodeGenUtil.resolveNamedValue(propertyExpression, lookupContext);
						return Optional.of(((ListValue) resolved).getOwnedListElements().stream().map(element1 -> {
							PropertyExpression resolved1 = CodeGenUtil.resolveNamedValue(element1, lookupContext);
							return ((BooleanLiteral) resolved1).getValue();
						}).collect(Collectors.toList()));
					} catch (PropertyNotPresentException e) {
						return Optional.empty();
					}
				}
				
				public static PropertyExpression getList1Boolean_EObject(NamedElement lookupContext) {
					String name = "boolean_test::list_1_boolean";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					return lookupContext.getNonModalPropertyValue(property);
				}
				
				public static Optional<List<List<List<List<List<Boolean>>>>>> getList5Boolean(NamedElement lookupContext) {
					String name = "boolean_test::list_5_boolean";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					try {
						PropertyExpression propertyExpression = lookupContext.getNonModalPropertyValue(property);
						PropertyExpression resolved = CodeGenUtil.resolveNamedValue(propertyExpression, lookupContext);
						return Optional.of(((ListValue) resolved).getOwnedListElements().stream().map(element1 -> {
							PropertyExpression resolved1 = CodeGenUtil.resolveNamedValue(element1, lookupContext);
							return ((ListValue) resolved1).getOwnedListElements().stream().map(element2 -> {
								PropertyExpression resolved2 = CodeGenUtil.resolveNamedValue(element2, lookupContext);
								return ((ListValue) resolved2).getOwnedListElements().stream().map(element3 -> {
									PropertyExpression resolved3 = CodeGenUtil.resolveNamedValue(element3, lookupContext);
									return ((ListValue) resolved3).getOwnedListElements().stream().map(element4 -> {
										PropertyExpression resolved4 = CodeGenUtil.resolveNamedValue(element4, lookupContext);
										return ((ListValue) resolved4).getOwnedListElements().stream().map(element5 -> {
											PropertyExpression resolved5 = CodeGenUtil.resolveNamedValue(element5, lookupContext);
											return ((BooleanLiteral) resolved5).getValue();
										}).collect(Collectors.toList());
									}).collect(Collectors.toList());
								}).collect(Collectors.toList());
							}).collect(Collectors.toList());
						}).collect(Collectors.toList()));
					} catch (PropertyNotPresentException e) {
						return Optional.empty();
					}
				}
				
				public static PropertyExpression getList5Boolean_EObject(NamedElement lookupContext) {
					String name = "boolean_test::list_5_boolean";
					Property property = Aadl2GlobalScopeUtil.get(lookupContext, Aadl2Package.eINSTANCE.getProperty(), name);
					return lookupContext.getNonModalPropertyValue(property);
				}
			}
		'''
		val results = PropertiesCodeGen.generateJava(testHelper.parseString(booleanTest, otherPs))
		assertEquals(1, results.size)
		
		assertEquals("BooleanTest.java", results.head.fileName)
		assertEquals(booleanTestClass.toString, results.head.contents)
	}
}