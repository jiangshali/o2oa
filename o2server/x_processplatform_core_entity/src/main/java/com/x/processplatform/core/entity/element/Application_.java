/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.processplatform.core.entity.element;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.processplatform.core.entity.element.Application.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Mon Dec 24 19:04:55 CST 2018")
public class Application_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<Application,String> alias;
    public static volatile SingularAttribute<Application,String> applicationCategory;
    public static volatile ListAttribute<Application,String> availableIdentityList;
    public static volatile ListAttribute<Application,String> availableUnitList;
    public static volatile ListAttribute<Application,String> controllerList;
    public static volatile SingularAttribute<Application,String> creatorPerson;
    public static volatile SingularAttribute<Application,String> description;
    public static volatile SingularAttribute<Application,String> icon;
    public static volatile SingularAttribute<Application,String> iconHue;
    public static volatile SingularAttribute<Application,String> id;
    public static volatile SingularAttribute<Application,String> lastUpdatePerson;
    public static volatile SingularAttribute<Application,Date> lastUpdateTime;
    public static volatile SingularAttribute<Application,String> name;
}
