package io.namoo.expow.api;

import io.namoo.expow.lib.ExpowFileWriterLib;

/**
 *
 * @author <a href="mailto:mhjang@nextree.co.kr">Jang, Mihyeon</a>
 * @since 2017. 12. 8.
 */
public class ExpowFileWriterFactory {
    //
    public static ExpowFileWriter newInstance() {
        //
        return new ExpowFileWriterLib();
    }
}
