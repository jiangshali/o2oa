/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.cms.core.entity;

import com.x.base.core.entity.StorageObject_;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.cms.core.entity.FileInfo.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Mon Dec 24 19:04:08 CST 2018")
public class FileInfo_ extends StorageObject_  {
    public static volatile SingularAttribute<FileInfo,String> appId;
    public static volatile SingularAttribute<FileInfo,String> categoryId;
    public static volatile SingularAttribute<FileInfo,String> cloudId;
    public static volatile SingularAttribute<FileInfo,String> creatorUid;
    public static volatile SingularAttribute<FileInfo,String> description;
    public static volatile SingularAttribute<FileInfo,String> documentId;
    public static volatile SingularAttribute<FileInfo,String> extension;
    public static volatile SingularAttribute<FileInfo,String> fileExtType;
    public static volatile SingularAttribute<FileInfo,String> fileHost;
    public static volatile SingularAttribute<FileInfo,String> fileName;
    public static volatile SingularAttribute<FileInfo,String> filePath;
    public static volatile SingularAttribute<FileInfo,String> fileType;
    public static volatile SingularAttribute<FileInfo,String> id;
    public static volatile SingularAttribute<FileInfo,Date> lastUpdateTime;
    public static volatile SingularAttribute<FileInfo,Long> length;
    public static volatile SingularAttribute<FileInfo,String> name;
    public static volatile SingularAttribute<FileInfo,Integer> seqNumber;
    public static volatile SingularAttribute<FileInfo,String> site;
    public static volatile SingularAttribute<FileInfo,String> storage;
}
