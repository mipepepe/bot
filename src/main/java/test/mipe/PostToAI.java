package test.mipe;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostToAI {
    HttpClient http;

    public PostToAI() {
        this.http = HttpClient.newHttpClient();
    }

    // systemPromptとはAIが守るべき指示を書く場所
    // queryとはAIに投げる内容
    public String getContent(String systemPrompt, String query) {
        String str = "[{\"role\":\"system\",\"content\":\"" + systemPrompt + "\"},{\"role\":\"user\",\"content\":\""
                + query + "\"}]";
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create("http://googlechrome:8888/stream"))
                .POST(HttpRequest.BodyPublishers.ofString(str))
                .header("Content-Type", "application/json");
        try {
            HttpResponse<String> res = http.send(builder.build(), HttpResponse.BodyHandlers.ofString());
            return res.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "エラー出た";
        }
    }
    public void usage() {
        System.out.println(new PostToAI().getContent("この発言内容から発言者が酔っているか酔っていないかを「酔っています」か「酔っていません」で判定してください", "わん！"));
    }
}
