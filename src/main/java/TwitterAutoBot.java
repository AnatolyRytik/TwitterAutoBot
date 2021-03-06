import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.*;
import java.nio.charset.Charset;

public class TwitterAutoBot {

    public static void main(String[] args) {
        tweetLines();
    }

    private static void tweetLines() {
        String line;
        try {
            try (
                    InputStream fileInputStream = new FileInputStream("C:\\IdeaProjects\\TwitterAutoBot\\src\\main\\resources\\tweets.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("Cp1252"));
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ) {
                while ((line = bufferedReader.readLine()) != null) {
                    sendTweet(line);
                    System.out.println("Tweeting: " + line + "...");

                    try {
                        System.out.println("Sleeping for 10 minutes...");
                        Thread.sleep(360000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void sendTweet(String line) {
        Twitter twitter = TwitterFactory.getSingleton();
        Status status;
        try {
            status = twitter.updateStatus(line);
            System.out.println(status);
        } catch (TwitterException e) {;
            e.printStackTrace();
        }
    }

}