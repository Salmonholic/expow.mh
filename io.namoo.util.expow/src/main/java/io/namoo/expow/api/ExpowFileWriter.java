/*******************************************************************************
 * Copyright(c) 2015-2020 Incheon International Airport Corporation. 
 * All rights reserved. This software is the proprietary information of 
 * Incheon International Airport Corporation.
 *******************************************************************************/
package io.namoo.expow.api;

import java.util.List;

/**
 *
 * @author <a href="mailto:mhjang@nextree.co.kr">Jang, Mihyeon</a>
 * @since 2017. 11. 21.
 */
public interface ExpowFileWriter {
    //
    void writeAsArray(ArraySheet arraySheet);
    
    void write(String fileName, ExpowFile file);
    
}
