
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
 * Updated by JCasGen Tue Jan 19 02:45:04 CET 2016
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
  final Feature casFeat_token;
  /** @generated */
  final int     casFeatCode_token;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getToken(int addr) {
        if (featOkTst && casFeat_token == null)
      jcas.throwFeatMissing("token", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_token);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setToken(int addr, String v) {
        if (featOkTst && casFeat_token == null)
      jcas.throwFeatMissing("token", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    ll_cas.ll_setStringValue(addr, casFeatCode_token, v);}
    
  
 
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
    return ll_cas.ll_getIntValue(addr, casFeatCode_position);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPosition(int addr, int v) {
        if (featOkTst && casFeat_position == null)
      jcas.throwFeatMissing("position", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    ll_cas.ll_setIntValue(addr, casFeatCode_position, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentence;
  /** @generated */
  final int     casFeatCode_sentence;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSentence(int addr) {
        if (featOkTst && casFeat_sentence == null)
      jcas.throwFeatMissing("sentence", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sentence);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentence(int addr, String v) {
        if (featOkTst && casFeat_sentence == null)
      jcas.throwFeatMissing("sentence", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    ll_cas.ll_setStringValue(addr, casFeatCode_sentence, v);}
    
  
 
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
    return ll_cas.ll_getIntValue(addr, casFeatCode_complexity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setComplexity(int addr, int v) {
        if (featOkTst && casFeat_complexity == null)
      jcas.throwFeatMissing("complexity", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    ll_cas.ll_setIntValue(addr, casFeatCode_complexity, v);}
    
  
 
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
    return ll_cas.ll_getIntValue(addr, casFeatCode_complexitySum);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setComplexitySum(int addr, int v) {
        if (featOkTst && casFeat_complexitySum == null)
      jcas.throwFeatMissing("complexitySum", "de.unidue.langtech.teaching.pp.type.GoldComplexity");
    ll_cas.ll_setIntValue(addr, casFeatCode_complexitySum, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public GoldComplexity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_token = jcas.getRequiredFeatureDE(casType, "token", "uima.cas.String", featOkTst);
    casFeatCode_token  = (null == casFeat_token) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_token).getCode();

 
    casFeat_position = jcas.getRequiredFeatureDE(casType, "position", "uima.cas.Integer", featOkTst);
    casFeatCode_position  = (null == casFeat_position) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_position).getCode();

 
    casFeat_sentence = jcas.getRequiredFeatureDE(casType, "sentence", "uima.cas.String", featOkTst);
    casFeatCode_sentence  = (null == casFeat_sentence) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentence).getCode();

 
    casFeat_complexity = jcas.getRequiredFeatureDE(casType, "complexity", "uima.cas.Integer", featOkTst);
    casFeatCode_complexity  = (null == casFeat_complexity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_complexity).getCode();

 
    casFeat_complexitySum = jcas.getRequiredFeatureDE(casType, "complexitySum", "uima.cas.Integer", featOkTst);
    casFeatCode_complexitySum  = (null == casFeat_complexitySum) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_complexitySum).getCode();

  }
}



    