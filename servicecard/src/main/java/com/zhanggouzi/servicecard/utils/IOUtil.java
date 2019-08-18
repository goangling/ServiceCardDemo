package com.zhanggouzi.servicecard.utils;

import java.io.Closeable;
import java.io.IOException;

public abstract  class IOUtil {
public static void close(Closeable closeable){
    if(closeable!=null){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
