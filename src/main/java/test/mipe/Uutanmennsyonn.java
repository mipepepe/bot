package test.mipe;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Uutanmennsyonn {

    public static void start(JDA jda) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        // 実行したいタスクを定義
        Runnable task = new Runnable() {
            @Override
            public void run() {
                // ここに一日おきに実行したい処理を記述
                TextChannel text=jda.getChannelById(TextChannel.class,"1094215078947070002");
                text.sendMessage("<@830734808455184414> 勉強しろ").queue();
            }
        };
        
        // 初回の実行を設定（今日の0時に実行されるように設定）
        scheduler.scheduleAtFixedRate(task, getDelay(), 1, TimeUnit.DAYS);
    }

    // 今日の午前0時の遅延時間を取得するユーティリティメソッド
    private static long getDelay() {
        java.util.Calendar now = java.util.Calendar.getInstance();
        java.util.Calendar tomorrow = java.util.Calendar.getInstance();
        tomorrow.add(java.util.Calendar.DATE, 1);
        tomorrow.set(java.util.Calendar.HOUR_OF_DAY, 22);
        tomorrow.set(java.util.Calendar.MINUTE, 0);
        tomorrow.set(java.util.Calendar.SECOND, 0);
        return tomorrow.getTimeInMillis() - now.getTimeInMillis();
    }
}
