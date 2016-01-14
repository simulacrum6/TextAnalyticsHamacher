
/* First created by JCasGen Wed Jan 13 12:44:04 CET 2016 */
package de.unidue.langtech.teaching.pp;

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
 * Updated by JCasGen Thu Jan 14 09:17:32 CET 2016
 * @generated */
public class TokenSyllableCount_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TokenSyllableCount_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TokenSyllableCount_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TokenSyllableCount(addr, TokenSyllableCount_Type.this);
  			   TokenSyllableCount_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TokenSyllableCount(addr, TokenSyllableCount_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TokenSyllableCount.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.langtech.teaching.pp.TokenSyllableCount");
 
  /** @generated */
  final Feature casFeat_countSyllables;
  /** @generated */
  final int     casFeatCode_countSyllables;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCountSyllables(int addr) {
        if (featOkTst && casFeat_countSyllables == null)
      jcas.throwFeatMissing("countSyllables", "de.unidue.langtech.teaching.pp.TokenSyllableCount");
    return ll_cas.ll_getRefValue(addr, casFeatCode_countSyllables);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCountSyllables(int addr, int v) {
        if (featOkTst && casFeat_countSyllables == null)
      jcas.throwFeatMissing("countSyllables", "de.unidue.langtech.teaching.pp.TokenSyllableCount");
    ll_cas.ll_setRefValue(addr, casFeatCode_countSyllables, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getCountSyllables(int addr, int i) {
        if (featOkTst && casFeat_countSyllables == null)
      jcas.throwFeatMissing("countSyllables", "de.unidue.langtech.teaching.pp.TokenSyllableCount");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_countSyllables), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_countSyllables), i);
  return ll_cas.ll_getIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_countSyllables), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setCountSyllables(int addr, int i, int v) {
        if (featOkTst && casFeat_countSyllables == null)
      jcas.throwFeatMissing("countSyllables", "de.unidue.langtech.teaching.pp.TokenSyllableCount");
    if (lowLevelTypeChecks)
      ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_countSyllables), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_countSyllables), i);
    ll_cas.ll_setIntArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_countSyllables), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public TokenSyllableCount_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_countSyllables = jcas.getRequiredFeatureDE(casType, "countSyllables", "uima.cas.IntegerArray", featOkTst);
    casFeatCode_countSyllables  = (null == casFeat_countSyllables) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_countSyllables).getCode();

  }
}



    