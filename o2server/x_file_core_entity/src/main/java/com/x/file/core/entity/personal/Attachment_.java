/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.file.core.entity.personal;

import com.x.base.core.entity.StorageObject_;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.file.core.entity.personal.Attachment.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Mon Dec 24 19:04:20 CST 2018")
public class Attachment_ extends StorageObject_  {
    public static volatile ListAttribute<Attachment,String> editorList;
    public static volatile SingularAttribute<Attachment,String> extension;
    public static volatile SingularAttribute<Attachment,String> folder;
    public static volatile SingularAttribute<Attachment,String> id;
    public static volatile SingularAttribute<Attachment,String> lastUpdatePerson;
    public static volatile SingularAttribute<Attachment,Date> lastUpdateTime;
    public static volatile SingularAttribute<Attachment,Long> length;
    public static volatile SingularAttribute<Attachment,String> name;
    public static volatile SingularAttribute<Attachment,String> person;
    public static volatile ListAttribute<Attachment,String> shareList;
    public static volatile SingularAttribute<Attachment,String> storage;
}
