package ru.eltech.elerning.geotracker.util;

import java.io.*;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 29.12.11
 */
public class IOUtils {
    public static String readFile(final String filename) throws IOException {
        return ConnectionUtils.read(new FileInputStream(filename));
    }
}
