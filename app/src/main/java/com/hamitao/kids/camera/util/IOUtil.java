package com.hamitao.kids.camera.util;

import java.io.Closeable;
import java.io.IOException;

public class IOUtil {

    public static void closeStream(Closeable stream) {
        try {
            if (stream != null)
                stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
