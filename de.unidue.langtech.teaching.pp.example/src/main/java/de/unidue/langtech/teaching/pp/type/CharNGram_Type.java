
/* First created by JCasGen Thu Jan 14 16:31:10 CET 2016 */
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
 * Updated by JCasGen Thu Jan 14 16:31:10 CET 2016
 * @generated */
public class CharNGram_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CharNGram_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CharNGram_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CharNGram(addr, CharNGram_Type.this);
  			   CharNGram_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CharNGram(addr, CharNGram_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CharNGram.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.langtech.teaching.pp.type.CharNGram");
 
  /** @generated */
  final Feature casFeat_charNGrams;
  /** @generated */
  final int     casFeatCode_charNGrams;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCharNGrams(int addr) {
        if (featOkTst && casFeat_charNGrams == null)
      jcas.throwFeatMissing("charNGrams", "de.unidue.langtech.teaching.pp.type.CharNGram");
    return ll_cas.ll_getRefValue(addr, casFeatCode_charNGrams);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCharNGrams(int addr, int v) {
        if (featOkTst && casFeat_charNGrams == null)
      jcas.throwFeatMissing("charNGrams", "de.unidue.langtech.teaching.pp.type.CharNGram");
    ll_cas.ll_setRefValue(addr, casFeatCode_charNGrams, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public String getCharNGrams(int addr, int i) {
        if (featOkTst && casFeat_charNGrams == null)
      jcas.throwFeatMissing("charNGrams", "de.unidue.langtech.teaching.pp.type.CharNGram");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_charNGrams), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_charNGrams), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_charNGrams), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setCharNGrams(int addr, int i, String v) {
        if (featOkTst && casFeat_charNGrams == null)
      jcas.throwFeatMissing("charNGrams", "de.unidue.langtech.teaching.pp.type.CharNGram");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_charNGrams), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_charNGrams), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_charNGrams), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public CharNGram_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_charNGrams = jcas.getRequiredFeatureDE(casType, "charNGrams", "uima.cas.StringArray", featOkTst);
    casFeatCode_charNGrams  = (null == casFeat_charNGrams) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_charNGrams).getCode();

  }
}



    