/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.mind.entity;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.mind.entity.MindBaseInfo.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Mon Dec 24 19:04:37 CST 2018")
public class MindBaseInfo_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<MindBaseInfo,Boolean> cooperative;
    public static volatile SingularAttribute<MindBaseInfo,String> cooperative_sequence;
    public static volatile SingularAttribute<MindBaseInfo,String> creator;
    public static volatile SingularAttribute<MindBaseInfo,String> creatorUnit;
    public static volatile SingularAttribute<MindBaseInfo,String> creatorUnit_sequence;
    public static volatile SingularAttribute<MindBaseInfo,String> creator_sequence;
    public static volatile SingularAttribute<MindBaseInfo,String> description;
    public static volatile ListAttribute<MindBaseInfo,String> editorList;
    public static volatile SingularAttribute<MindBaseInfo,Integer> fileVersion;
    public static volatile SingularAttribute<MindBaseInfo,String> folderId;
    public static volatile SingularAttribute<MindBaseInfo,String> folder_sequence;
    public static volatile SingularAttribute<MindBaseInfo,String> id;
    public static volatile SingularAttribute<MindBaseInfo,String> name;
    public static volatile ListAttribute<MindBaseInfo,String> shareGroupList;
    public static volatile ListAttribute<MindBaseInfo,String> sharePersonList;
    public static volatile ListAttribute<MindBaseInfo,String> shareUnitList;
    public static volatile SingularAttribute<MindBaseInfo,Boolean> shared;
    public static volatile SingularAttribute<MindBaseInfo,String> shared_sequence;
}
