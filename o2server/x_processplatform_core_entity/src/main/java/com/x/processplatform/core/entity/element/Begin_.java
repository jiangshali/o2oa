/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.processplatform.core.entity.element;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.Boolean;
import java.lang.String;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.processplatform.core.entity.element.Begin.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Mon Dec 24 19:04:55 CST 2018")
public class Begin_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<Begin,String> afterArriveScript;
    public static volatile SingularAttribute<Begin,String> afterArriveScriptText;
    public static volatile SingularAttribute<Begin,String> afterExecuteScript;
    public static volatile SingularAttribute<Begin,String> afterExecuteScriptText;
    public static volatile SingularAttribute<Begin,String> afterInquireScript;
    public static volatile SingularAttribute<Begin,String> afterInquireScriptText;
    public static volatile SingularAttribute<Begin,String> alias;
    public static volatile SingularAttribute<Begin,Boolean> allowReroute;
    public static volatile SingularAttribute<Begin,Boolean> allowRerouteTo;
    public static volatile SingularAttribute<Begin,String> beforeArriveScript;
    public static volatile SingularAttribute<Begin,String> beforeArriveScriptText;
    public static volatile SingularAttribute<Begin,String> beforeExecuteScript;
    public static volatile SingularAttribute<Begin,String> beforeExecuteScriptText;
    public static volatile SingularAttribute<Begin,String> beforeInquireScript;
    public static volatile SingularAttribute<Begin,String> beforeInquireScriptText;
    public static volatile SingularAttribute<Begin,String> description;
    public static volatile SingularAttribute<Begin,String> extension;
    public static volatile SingularAttribute<Begin,String> form;
    public static volatile SingularAttribute<Begin,String> id;
    public static volatile SingularAttribute<Begin,String> name;
    public static volatile SingularAttribute<Begin,String> position;
    public static volatile SingularAttribute<Begin,String> process;
    public static volatile ListAttribute<Begin,String> readDataPathList;
    public static volatile SingularAttribute<Begin,String> readDuty;
    public static volatile ListAttribute<Begin,String> readGroupList;
    public static volatile ListAttribute<Begin,String> readIdentityList;
    public static volatile SingularAttribute<Begin,String> readScript;
    public static volatile SingularAttribute<Begin,String> readScriptText;
    public static volatile ListAttribute<Begin,String> readUnitList;
    public static volatile ListAttribute<Begin,String> reviewDataPathList;
    public static volatile SingularAttribute<Begin,String> reviewDuty;
    public static volatile ListAttribute<Begin,String> reviewGroupList;
    public static volatile ListAttribute<Begin,String> reviewIdentityList;
    public static volatile SingularAttribute<Begin,String> reviewScript;
    public static volatile SingularAttribute<Begin,String> reviewScriptText;
    public static volatile ListAttribute<Begin,String> reviewUnitList;
    public static volatile SingularAttribute<Begin,String> route;
}
