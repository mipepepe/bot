package test.mipe;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Mihomo {
    private HttpClient client = HttpClient.newHttpClient();
    private Gson gson = new Gson();

    public Mihomo() {
    }

    public HashSet<String> getHelpers(int uid) {
        HttpRequest.Builder builder = HttpRequest
                .newBuilder(URI.create("https://api.mihomo.me/sr_info_parsed/" + uid + "?lang=jp"));
        try {
            HttpResponse<String> res = client.send(builder.GET().build(), HttpResponse.BodyHandlers.ofString());
            String body = res.body();
            System.out.println(body);
            JsonObject b = gson.fromJson(body, JsonObject.class);
            HashSet<String> helpers = new HashSet<String>();
            // ここから
            JsonArray characters = b.get("characters").getAsJsonArray();
            for (JsonElement character : characters) {
                String name = character.getAsJsonObject().get("name").getAsString();
                helpers.add(name);
            }
            // ここまで
            return helpers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void event(MessageReceivedEvent event) {
        // もしeventのメッセージ内容が!helpers で始まらないならreturn;
        if (!event.getMessage().getContentRaw().startsWith("!helpers ")) {
            return;
        }
        // !helpers 以降の文字列を取得して "String -> int"に変換しましょう
        String arg = event.getMessage().getContentRaw().substring(9);
        try {
            int uid = Integer.parseInt(arg);
            if (uid == 0) {
                event.getChannel().sendMessage("ないです").queue();
                return;
            }
            HashSet<String> helpers = getHelpers(800000000);
            // helpersがnullなら失敗したとみなしエラー出たよって言ってreturn
            if (helpers == null) {
                event.getChannel().sendMessage("エラーです").queue();
                return;
            }
            // helpersが存在するなら名前を改行で組み合わせて送信
            String result = "";
            for (String helper : helpers) {
                result = result + helper + "\n";
            }
            event.getChannel().sendMessage(result).queue();
            return;
        } catch (Exception e) {
            event.getChannel().sendMessage("エラー").queue();
            return;
        }
    }
}
