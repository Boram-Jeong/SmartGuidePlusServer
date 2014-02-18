/*
 * @(#)multipartResolver.java $version 2013. 8. 13.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.secsm;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class multipartResolver {
	@Bean
    public CommonsMultipartResolver multipartResolver()
    {
         CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
         multipartResolver.setMaxUploadSize(100000000);
         return multipartResolver;
    }
   
    @Bean
    public FileSystemResource fileSystemResource()
    {
         String path = "C:/fileupload/";
         FileSystemResource resource = new FileSystemResource(path);
         return resource;
    }


}
