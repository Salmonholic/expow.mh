package io.namoo.expow.lib;

import io.namoo.expow.api.ExpowFile;
import io.namoo.expow.api.ExpowFileReaderFactory;
import io.namoo.expow.api.ExpowFileWriterFactory;

/**
 *
 * @author <a href="mailto:mhjang@nextree.co.kr">Jang, Mihyeon</a>
 * @since 2017. 12. 8.
 */
public class ExpowFileTest {
    //
    public static void main(String[] args) {
        //
        String fileName = "./src/test/resources/SkillRoleMap.xlsx";
        ExpowFile file = ExpowFileReaderFactory.newInstance().read(fileName); 
        fileName = "./src/test/resources/SkillRoleMapCopy.xlsx";
        ExpowFileWriterFactory.newInstance().write(fileName, file); 
        
    }
    
}
