/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.strategydeploy.core.entity;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.strategydeploy.core.entity.KeyworkInfo.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Thu Dec 27 11:41:31 CST 2018")
public class KeyworkInfo_ extends SliceJpaObject_  {
    public static volatile ListAttribute<KeyworkInfo,String> attachmentList;
    public static volatile SingularAttribute<KeyworkInfo,String> id;
    public static volatile SingularAttribute<KeyworkInfo,String> isstrategywork;
    public static volatile SingularAttribute<KeyworkInfo,Integer> keyworkbegindate;
    public static volatile SingularAttribute<KeyworkInfo,String> keyworkcreator;
    public static volatile SingularAttribute<KeyworkInfo,String> keyworkdescribe;
    public static volatile SingularAttribute<KeyworkInfo,Integer> keyworkenddate;
    public static volatile SingularAttribute<KeyworkInfo,String> keyworktitle;
    public static volatile SingularAttribute<KeyworkInfo,String> keyworkunit;
    public static volatile SingularAttribute<KeyworkInfo,String> keyworkuserscope;
    public static volatile SingularAttribute<KeyworkInfo,String> keyworkyear;
    public static volatile ListAttribute<KeyworkInfo,String> measureslist;
    public static volatile SingularAttribute<KeyworkInfo,Integer> sequencenumber;
    public static volatile SingularAttribute<KeyworkInfo,String> status;
}
