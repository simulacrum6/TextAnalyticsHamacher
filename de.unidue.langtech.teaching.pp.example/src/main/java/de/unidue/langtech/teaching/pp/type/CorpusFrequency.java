

/* First created by JCasGen Sat Jan 23 15:02:07 CET 2016 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Jan 23 15:08:01 CET 2016
 * XML source: C:/Users/Nev/git/TextAnalyticsHamacher/de.unidue.langtech.teaching.pp.example/src/main/resources/desc/type/CorpusFrequency.xml
 * @generated */
public class CorpusFrequency extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CorpusFrequency.class);
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
  protected CorpusFrequency() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public CorpusFrequency(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public CorpusFrequency(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public CorpusFrequency(JCas jcas, int begin, int end) {
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
  //* Feature: count

  /** getter for count - gets 
   * @generated
   * @return value of the feature 
   */
  public int getCount() {
    if (CorpusFrequency_Type.featOkTst && ((CorpusFrequency_Type)jcasType).casFeat_count == null)
      jcasType.jcas.throwFeatMissing("count", "de.unidue.langtech.teaching.pp.type.CorpusFrequency");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CorpusFrequency_Type)jcasType).casFeatCode_count);}
    
  /** setter for count - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCount(int v) {
    if (CorpusFrequency_Type.featOkTst && ((CorpusFrequency_Type)jcasType).casFeat_count == null)
      jcasType.jcas.throwFeatMissing("count", "de.unidue.langtech.teaching.pp.type.CorpusFrequency");
    jcasType.ll_cas.ll_setIntValue(addr, ((CorpusFrequency_Type)jcasType).casFeatCode_count, v);}    
   
    
  //*--------------*
  //* Feature: rank

  /** getter for rank - gets 
   * @generated
   * @return value of the feature 
   */
  public int getRank() {
    if (CorpusFrequency_Type.featOkTst && ((CorpusFrequency_Type)jcasType).casFeat_rank == null)
      jcasType.jcas.throwFeatMissing("rank", "de.unidue.langtech.teaching.pp.type.CorpusFrequency");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CorpusFrequency_Type)jcasType).casFeatCode_rank);}
    
  /** setter for rank - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRank(int v) {
    if (CorpusFrequency_Type.featOkTst && ((CorpusFrequency_Type)jcasType).casFeat_rank == null)
      jcasType.jcas.throwFeatMissing("rank", "de.unidue.langtech.teaching.pp.type.CorpusFrequency");
    jcasType.ll_cas.ll_setIntValue(addr, ((CorpusFrequency_Type)jcasType).casFeatCode_rank, v);}    
  }

    