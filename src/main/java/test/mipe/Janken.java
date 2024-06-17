package test.mipe;

import java.util.Random;

public class Janken {

    public static int getRandomNumber(int min, int max) {
        // Randomオブジェクトの生成
        Random rand = new Random();

        // 指定した範囲内でランダムな整数を生成
        int randomNumber = rand.nextInt((max - min) + 1) + min;

        return randomNumber;
    }

    private static String getHand(int num) {
        String hand = "";
        switch (num) {
            case 1:
                hand = "ぐー";
                break;
            case 2:
                hand = "ちょき";
                break;
            case 3:
                hand = "ぱー";
                break;
        }
        return hand;
    }

    public static String determineWinner(String playerHand, String computerHand) {
        if (playerHand == computerHand) {
            return "引き分け";
        } else if ((playerHand.equals("ぐー") && computerHand.equals("ちょき")) ||
                (playerHand.equals("ちょき") && computerHand.equals("ぱー")) ||
                (playerHand.equals("ぱー") && computerHand.equals("ぐー"))) {
            return "プレイヤーの勝ち";
        } else {
            return "コンピュータの勝ち";
        }
    }

    public static String janken(String playerHand) {
        // アイスタントの手を決める
        int computerHandNumber = getRandomNumber(1, 3);
        String computerHand = getHand(computerHandNumber);
        // 勝敗を決める
        String result = determineWinner(playerHand, computerHand);
        // 結果を出力する
        return "プレイヤーの手: " + playerHand + "\n" + "コンピュータの手: " + computerHand + "\n" + "結果: " + result;
    }
}
