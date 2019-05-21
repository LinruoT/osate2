/**
 * generated by Xtext 2.17.0
 */
package org.osate.expr.expr;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.expr.expr.Category#getCategory <em>Category</em>}</li>
 * </ul>
 *
 * @see org.osate.expr.expr.ExprPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends Type
{
  /**
   * Returns the value of the '<em><b>Category</b></em>' attribute.
   * The literals are from the enumeration {@link org.osate.expr.expr.CategoryEnum}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category</em>' attribute.
   * @see org.osate.expr.expr.CategoryEnum
   * @see #setCategory(CategoryEnum)
   * @see org.osate.expr.expr.ExprPackage#getCategory_Category()
   * @model
   * @generated
   */
  CategoryEnum getCategory();

  /**
   * Sets the value of the '{@link org.osate.expr.expr.Category#getCategory <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category</em>' attribute.
   * @see org.osate.expr.expr.CategoryEnum
   * @see #getCategory()
   * @generated
   */
  void setCategory(CategoryEnum value);

} // Category
