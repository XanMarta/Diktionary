import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.okhttp.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class apiTranslator {
    private String subcriptionKey = "";

    public apiTranslator() {
        try {
            Scanner scan = new Scanner(new File("api_key.dll"));
            subcriptionKey = scan.nextLine();
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public String apiTranslate(String word) {
        // Connection
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.cognitive.microsofttranslator.com")
                .addPathSegment("/translate")
                .addQueryParameter("api-version", "3.0")
                .addQueryParameter("from", "en")
                .addQueryParameter("to", "vi")
                .build();
        // Send request
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String content = "[{\"Text\": \"" + word + "\"}]";
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", subcriptionKey)
                .addHeader("Content-type", "application/json")
                .build();
        // Get response
        try {
            Response response = client.newCall(request).execute();
            // Get result
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response.body().string());
            String result = element
                    .getAsJsonArray()
                    .get(0)
                    .getAsJsonObject()
                    .get("translations")
                    .getAsJsonArray()
                    .get(0)
                    .getAsJsonObject()
                    .get("text")
                    .getAsString();
            return result;
        } catch (IOException e) {
            System.out.println(e);
            return "";
        }
    }
}
