package logic;

import java.io.IOException;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
public class TweetMaster {
	public static void main( String[] args ) throws TwitterException, IOException
    {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("IVvuy8yZeqzcNm0WVcgCbasjs")
		  .setOAuthConsumerSecret("ZwxUYvuvrYB3mCANtQ9h8jYGE4dVLbfSh6vGyRMa68Eb8vTufE")
		  .setOAuthAccessToken("2439108144-8JWpbgdoktf8Y1QPXi1mz7iLmZuOqH5zHgcAcRt")
		  .setOAuthAccessTokenSecret("yOAkLt9UOCkRF5LonxtOe8yipiN1JMzYhA2HLUWQhRCtG");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
        User user = twitter.verifyCredentials();
        System.out.println(user.getName());
        System.out.println(user.getScreenName());
        System.out.println(user.getFriendsCount());
        System.out.println(user.getFollowersCount());
        System.out.println(user.getFavouritesCount());

        Query query = new Query();
        query.setQuery("お茶請け");
        QueryResult qr = twitter.search(query);
        User us = twitter.showUser("@konekodaisuki58");
        long id = us.getId();
        String st = "";
        List tweetList = twitter.getUserTimeline(id);
        for (int i=0; i < tweetList.size(); i++) {
			Status tweet = (Status) tweetList.get(i);

			if (st.length() < 50) {
			st += tweet.getText();
			}
		}
        twitter.updateStatus(us.getName() + "-san　のいいねカウントは"+ Integer.toString(us.getFavouritesCount()));
        System.out.println(st);
    }
}