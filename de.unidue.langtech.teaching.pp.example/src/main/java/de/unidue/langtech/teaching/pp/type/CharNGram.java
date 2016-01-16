

/* First created by JCasGen Thu Jan 14 16:31:10 CET 2016 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Jan 14 16:31:10 CET 2016
 * XML source: C:/Users/Marius/git/TextAnalyticsHamacher/de.unidue.langtech.teaching.pp.example/src/main/resources/desc/type/TokenInfo.xml
 * @generated */
public class CharNGram extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CharNGram.class);
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
  protected CharNGram() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public CharNGram(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public CharNGram(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public CharNGram(JCas jcas, int begin, int end) {
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
  //* Feature: charNGrams

  /** getter for charNGrams - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getCharNGrams() {
    if (CharNGram_Type.featOkTst && ((CharNGram_Type)jcasType).casFeat_charNGrams == null)
      jcasType.jcas.throwFeatMissing("charNGrams", "de.unidue.langtech.teaching.pp.type.CharNGram");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CharNGram_Type)jcasType).casFeatCode_charNGrams)));}
    
  /** setter for charNGrams - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCharNGrams(StringArray v) {
    if (CharNGram_Type.featOkTst && ((CharNGram_Type)jcasType).casFeat_charNGrams == null)
      jcasType.jcas.throwFeatMissing("charNGrams", "de.unidue.langtech.teaching.pp.type.CharNGram");
    jcasType.ll_cas.ll_setRefValue(addr, ((CharNGram_Type)jcasType).casFeatCode_charNGrams, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for charNGrams - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getCharNGrams(int i) {
    if (CharNGram_Type.featOkTst && ((CharNGram_Type)jcasType).casFeat_charNGrams == null)
      jcasType.jcas.throwFeatMissing("charNGrams", "de.unidue.langtech.teaching.pp.type.CharNGram");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CharNGram_Type)jcasType).casFeatCode_charNGrams), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CharNGram_Type)jcasType).casFeatCode_charNGrams), i);}

  /** indexed setter for charNGrams - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setCharNGrams(int i, String v) { 
    if (CharNGram_Type.featOkTst && ((CharNGram_Type)jcasType).casFeat_charNGrams == null)
      jcasType.jcas.throwFeatMissing("charNGrams", "de.unidue.langtech.teaching.pp.type.CharNGram");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CharNGram_Type)jcasType).casFeatCode_charNGrams), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CharNGram_Type)jcasType).casFeatCode_charNGrams), i, v);}
  }

    