package by.home.edt.services.impl;

import by.home.edt.services.interfaces.ITranslator;
import by.home.edt.utils.ParameterStringBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileTranslator implements ITranslator {
    private final String URL_WITH_KEY = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20200121T201816Z.d4ce0c5511a9cd0e.8a2d6acb4e3ceb7b9a7af39b372d875693f43118";
    private URL urlObj;

    public List<String> translate(List<String> stringList) {

        final List<String> wordsList = stringList.stream()
                .filter(word -> !word.isEmpty())
                .map(string -> {
                    try {
                        return string.substring(string.indexOf('.') + 1, string.contains(" -") ? string.indexOf(" -") : string.indexOf(" \u2013"));
                    } catch (StringIndexOutOfBoundsException exc) {
                        System.out.println("Wrong syntax in the string - \" " + string + " \"");
                    }
                    return "";
                })
                .map(String::trim)
                .collect(Collectors.toList());

        try {
            this.urlObj = new URL(URL_WITH_KEY);
            for (int i = 0; i < wordsList.size(); i++) {
                HttpsURLConnection connection = getHttpsConnection(this.urlObj);

                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                final String word = wordsList.get(i);
                final Map<String, String> parameters = new HashMap<>();
                parameters.put("text", word);
                parameters.put("lang", "en-ru");
                dataOutputStream.writeBytes(ParameterStringBuilder.getParamsString(parameters));
                System.out.println("Sent request... Get response with status - " + connection.getResponseCode());
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    int start = inputLine.indexOf("[");
                    int end = inputLine.indexOf("]");
                    String translatedWord = new String(inputLine.substring(start + 2, end - 1).getBytes(), StandardCharsets.UTF_8);
                    stringList.set(i, stringList.get(i).concat(" " + translatedWord));
                }
                reader.close();
                dataOutputStream.close();
                connection.disconnect();
            }
            return stringList;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    private HttpsURLConnection getHttpsConnection(URL urlObj) throws IOException {
        final HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);
        return connection;
    }
}
