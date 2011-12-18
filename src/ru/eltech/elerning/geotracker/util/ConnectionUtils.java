package ru.eltech.elerning.geotracker.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/18/11
 */
public class ConnectionUtils {

    public static String sendPostRequest(final String url, final String query) throws IOException {
        final URLConnection urlConnection = createPostConnection(url);
        write(urlConnection, query);
        return read(urlConnection);
    }

    public static URLConnection createPostConnection(final String url) throws IOException {
        final URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.setDoOutput(true);
        return urlConnection;
    }

    public static void write(final URLConnection urlConnection, final String data) throws IOException {
        write(urlConnection.getOutputStream(), data);
    }

    public static void write(final OutputStream outputStream, final String data) throws IOException {
        write(new BufferedWriter(new OutputStreamWriter(outputStream)), data, true);
    }

    public static void write(final Writer writer, final String data, final boolean closeAfterWrite) throws IOException {
        writer.write(data);
        if (closeAfterWrite) {
            writer.close();
        }
    }

    public static String read(final URLConnection urlConnection) throws IOException {
        return read(urlConnection.getInputStream());
    }

    public static String read(final InputStream inputStream) throws IOException {
        return read(new BufferedReader(new InputStreamReader(inputStream)), true);
    }

    public static String read(final BufferedReader bufferedReader, final boolean closeAfterRead) throws IOException {
        final StringBuilder result = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        if (closeAfterRead) {
            bufferedReader.close();
        }
        return result.toString();
    }
}
