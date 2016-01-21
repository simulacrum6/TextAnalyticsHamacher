
/* First created by JCasGen Tue Jan 19 02:45:04 CET 2016 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Jan 21 23:05:57 CET 2016
 * @generated */
public class GoldComplexity_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (GoldComplexity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = GoldComplexity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new GoldComplexity(addr, GoldComplexity_Type.this);
  			   GoldComplexity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new GoldComplexity(addr, GoldComplexity_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = GoldComplexity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.langtech.teaching.pp.type.GoldComplexity");
 
  /** @generated */
  final Feature casFeat_word;
  /** @generated */
  final int     casFeatCode_word;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getWord(int addr) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_word);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setWord(int addr, int v) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    ll_cas.ll_setRefValue(addr, casFeatCode_word, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public String getWord(int addr, int i) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_word), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_word), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_word), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setWord(int addr, int i, String v) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_word), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_word), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_word), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_position;
  /** @generated */
  final int     casFeatCode_position;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPosition(int addr) {
        if (featOkTst && casFeat_position == null)
      jcas.throwFeatMissing("position", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_position);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPosition(int addr, int v) {
        if (featOkTst && casFeat_position == null)
      jcas.throwFeatMissing("position", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    ll_cas.ll_setRefValue(addr, casFeatCode_position, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getPosition(int addr, int i) {
        if (featOkTst && casFeat_position == null)
      jcas.throwFeatMissing("position", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_position), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_position), i);
  return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_position), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setPosition(int addr, int i, int v) {
        if (featOkTst && casFeat_position == null)
      jcas.throwFeatMissing("position", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    if (lowLevelTypeChecks)
      ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_position), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_position), i);
    ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_position), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_complexity;
  /** @generated */
  final int     casFeatCode_complexity;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getComplexity(int addr) {
        if (featOkTst && casFeat_complexity == null)
      jcas.throwFeatMissing("complexity", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_complexity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setComplexity(int addr, int v) {
        if (featOkTst && casFeat_complexity == null)
      jcas.throwFeatMissing("complexity", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    ll_cas.ll_setRefValue(addr, casFeatCode_complexity, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getComplexity(int addr, int i) {
        if (featOkTst && casFeat_complexity == null)
      jcas.throwFeatMissing("complexity", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_complexity), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_complexity), i);
  return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_complexity), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setComplexity(int addr, int i, int v) {
        if (featOkTst && casFeat_complexity == null)
      jcas.throwFeatMissing("complexity", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    if (lowLevelTypeChecks)
      ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_complexity), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_complexity), i);
    ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_complexity), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_complexitySum;
  /** @generated */
  final int     casFeatCode_complexitySum;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getComplexitySum(int addr) {
        if (featOkTst && casFeat_complexitySum == null)
      jcas.throwFeatMissing("complexitySum", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_complexitySum);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setComplexitySum(int addr, int v) {
        if (featOkTst && casFeat_complexitySum == null)
      jcas.throwFeatMissing("complexitySum", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    ll_cas.ll_setRefValue(addr, casFeatCode_complexitySum, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getComplexitySum(int addr, int i) {
        if (featOkTst && casFeat_complexitySum == null)
      jcas.throwFeatMissing("complexitySum", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_complexitySum), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_complexitySum), i);
  return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_complexitySum), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setComplexitySum(int addr, int i, int v) {
        if (featOkTst && casFeat_complexitySum == null)
      jcas.throwFeatMissing("complexitySum", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    if (lowLevelTypeChecks)
      ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_complexitySum), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_complexitySum), i);
    ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_complexitySum), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public GoldComplexity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_word = jcas.getRequiredFeatureDE(casType, "word", "uima.cas.StringArray", featOkTst);
    casFeatCode_word  = (null == casFeat_word) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_word).getCode();

 
    casFeat_position = jcas.getRequiredFeatureDE(casType, "position", "uima.cas.IntegerArray", featOkTst);
    casFeatCode_position  = (null == casFeat_position) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_position).getCode();

 
    casFeat_complexity = jcas.getRequiredFeatureDE(casType, "complexity", "uima.cas.IntegerArray", featOkTst);
    casFeatCode_complexity  = (null == casFeat_complexity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_complexity).getCode();

 
    casFeat_complexitySum = jcas.getRequiredFeatureDE(casType, "complexitySum", "uima.cas.IntegerArray", featOkTst);
    casFeatCode_complexitySum  = (null == casFeat_complexitySum) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_complexitySum).getCode();

  }
}



    