

/* First created by JCasGen Wed Jan 13 12:44:04 CET 2016 */
package de.unidue.langtech.teaching.pp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.IntegerList;
import org.apache.uima.jcas.cas.EmptyIntegerList;
import org.apache.uima.jcas.tcas.Annotation;


import org.apache.uima.jcas.cas.IntegerArray;


/** 
 * Updated by JCasGen Thu Jan 14 12:27:32 CET 2016
 * XML source: C:/Users/Nev/git/TextAnalyticsHamacher/de.unidue.langtech.teaching.pp.example/src/main/resources/desc/type/TokenSyllableCount.xml
 * @generated */
public class TokenSyllableCount extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TokenSyllableCount.class);
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
  protected TokenSyllableCount() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public TokenSyllableCount(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public TokenSyllableCount(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public TokenSyllableCount(JCas jcas, int begin, int end) {
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
  //* Feature: countSyllables

  /** getter for countSyllables - gets 
   * @generated
   * @return value of the feature 
   */
  public int getCountSyllables() {
    if (TokenSyllableCount_Type.featOkTst && ((TokenSyllableCount_Type)jcasType).casFeat_countSyllables == null)
      jcasType.jcas.throwFeatMissing("countSyllables", "de.unidue.langtech.teaching.pp.TokenSyllableCount");
    return jcasType.ll_cas.ll_getIntValue(addr, ((TokenSyllableCount_Type)jcasType).casFeatCode_countSyllables);}
    
  /** setter for countSyllables - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCountSyllables(int v) {
    if (TokenSyllableCount_Type.featOkTst && ((TokenSyllableCount_Type)jcasType).casFeat_countSyllables == null)
      jcasType.jcas.throwFeatMissing("countSyllables", "de.unidue.langtech.teaching.pp.TokenSyllableCount");
    jcasType.ll_cas.ll_setIntValue(addr, ((TokenSyllableCount_Type)jcasType).casFeatCode_countSyllables, v);}    
  }

    