

/* First created by JCasGen Tue Jan 19 02:45:04 CET 2016 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.cas.StringList;
import org.apache.uima.jcas.cas.IntegerList;
import org.apache.uima.jcas.tcas.Annotation;


import org.apache.uima.jcas.cas.IntegerArray;


/** 
 * Updated by JCasGen Tue Feb 02 22:07:16 CET 2016
 * XML source: C:/Users/Nev/git/TextAnalyticsHamacher/de.unidue.langtech.teaching.pp.example/src/main/resources/desc/type/GoldComplexity.xml
 * @generated */
public class GoldComplexity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GoldComplexity.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected GoldComplexity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public GoldComplexity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public GoldComplexity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public GoldComplexity(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: word

  /** getter for word - gets Target word.
   * @generated
   * @return value of the feature 
   */
  public String getWord() {
    if (GoldComplexity_Type.featOkTst && ((GoldComplexity_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GoldComplexity_Type)jcasType).casFeatCode_word);}
    
  /** setter for word - sets Target word. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setWord(String v) {
    if (GoldComplexity_Type.featOkTst && ((GoldComplexity_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    jcasType.ll_cas.ll_setStringValue(addr, ((GoldComplexity_Type)jcasType).casFeatCode_word, v);}    
   
    
  //*--------------*
  //* Feature: position

  /** getter for position - gets The word's position in its sentence.
   * @generated
   * @return value of the feature 
   */
  public int getPosition() {
    if (GoldComplexity_Type.featOkTst && ((GoldComplexity_Type)jcasType).casFeat_position == null)
      jcasType.jcas.throwFeatMissing("position", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((GoldComplexity_Type)jcasType).casFeatCode_position);}
    
  /** setter for position - sets The word's position in its sentence. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPosition(int v) {
    if (GoldComplexity_Type.featOkTst && ((GoldComplexity_Type)jcasType).casFeat_position == null)
      jcasType.jcas.throwFeatMissing("position", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    jcasType.ll_cas.ll_setIntValue(addr, ((GoldComplexity_Type)jcasType).casFeatCode_position, v);}    
   
    
  //*--------------*
  //* Feature: complexity

  /** getter for complexity - gets Complexity rating for given word.
1 -> complex
0 -> not complex

NOTE: Complexity is set to 1 if at least 1 person (of 20) deemed a word complex.
   * @generated
   * @return value of the feature 
   */
  public int getComplexity() {
    if (GoldComplexity_Type.featOkTst && ((GoldComplexity_Type)jcasType).casFeat_complexity == null)
      jcasType.jcas.throwFeatMissing("complexity", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((GoldComplexity_Type)jcasType).casFeatCode_complexity);}
    
  /** setter for complexity - sets Complexity rating for given word.
1 -> complex
0 -> not complex

NOTE: Complexity is set to 1 if at least 1 person (of 20) deemed a word complex. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setComplexity(int v) {
    if (GoldComplexity_Type.featOkTst && ((GoldComplexity_Type)jcasType).casFeat_complexity == null)
      jcasType.jcas.throwFeatMissing("complexity", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    jcasType.ll_cas.ll_setIntValue(addr, ((GoldComplexity_Type)jcasType).casFeatCode_complexity, v);}    
   
    
  //*--------------*
  //* Feature: complexitySum

  /** getter for complexitySum - gets The sum of all complexity ratings for given word.
Min: 0
Max: 20
   * @generated
   * @return value of the feature 
   */
  public int getComplexitySum() {
    if (GoldComplexity_Type.featOkTst && ((GoldComplexity_Type)jcasType).casFeat_complexitySum == null)
      jcasType.jcas.throwFeatMissing("complexitySum", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return jcasType.ll_cas.ll_getIntValue(addr, ((GoldComplexity_Type)jcasType).casFeatCode_complexitySum);}
    
  /** setter for complexitySum - sets The sum of all complexity ratings for given word.
Min: 0
Max: 20 
   * @generated
   * @param v value to set into the feature 
   */
  public void setComplexitySum(int v) {
    if (GoldComplexity_Type.featOkTst && ((GoldComplexity_Type)jcasType).casFeat_complexitySum == null)
      jcasType.jcas.throwFeatMissing("complexitySum", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    jcasType.ll_cas.ll_setIntValue(addr, ((GoldComplexity_Type)jcasType).casFeatCode_complexitySum, v);}    
  }

    