package logic;

import java.io.IOException;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetCharactaristic {
	public static void main(String[] args) throws TwitterException, IOException {

		//TODO: 本当はプロパティファイルにもたせたい
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("XXX")
				.setOAuthConsumerSecret("XXXX")
				.setOAuthAccessToken("XXX")
				.setOAuthAccessTokenSecret("XXXXX");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		//検索実行
		Query query = new Query("ひんやりマスク");
		query.setCount(100);
		QueryResult result = twitter.search(query);
		System.out.println(result.getCount());
		//検索結果からmedia entity情報をチェックして保存
		for (Status sts : result.getTweets()) {
			if (sts.getRetweetCount() > 5) {
			System.out.println(sts.getText());

			System.out.println("*****************************");
			System.out.println(sts.getFavoriteCount());
			System.out.println("*****************************");
			}
		}
	}

}
