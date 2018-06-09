package logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class ImageMaster {
	//取得件数
	static final int TWEET_NUM = 1000;

	//保存対象の画像拡張子
	static final String TARGET_EXTENSION = ".jpg";

	//検索クエリ
	//hogehogeの部分で、画像流してるbotのIDなどを指定
	//単なるキーワードやハッシュタグも指定可能
	static final String MY_QUERY = "雨宮天";

	public static void main(String[] args) throws TwitterException, IOException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("IVvuy8yZeqzcNm0WVcgCbasjs")
				.setOAuthConsumerSecret("ZwxUYvuvrYB3mCANtQ9h8jYGE4dVLbfSh6vGyRMa68Eb8vTufE")
				.setOAuthAccessToken("2439108144-8JWpbgdoktf8Y1QPXi1mz7iLmZuOqH5zHgcAcRt")
				.setOAuthAccessTokenSecret("yOAkLt9UOCkRF5LonxtOe8yipiN1JMzYhA2HLUWQhRCtG");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		//検索実行
		Query query = new Query(MY_QUERY);
		query.setCount(TWEET_NUM);
		QueryResult result = twitter.search(query);
		System.out.println(result.getCount());
		//検索結果からmedia entity情報をチェックして保存
		for (Status sts : result.getTweets()) {
			MediaEntity[] arrMedia = sts.getMediaEntities();
			for (MediaEntity media : arrMedia) {
				//ファボ数でフィルタとかも良さそう
				//if(sts.getFavoriteCount() > 5)

				//とりあえず拡張子だけでフィルタ
				if (media.getMediaURL().endsWith(TARGET_EXTENSION) || (media.getMediaURL().endsWith(".ping"))) {
					URL website = new URL(media.getMediaURL());
					ReadableByteChannel rbc = Channels.newChannel(website.openStream());
					//保存ファイル名にStatusが持つ作成日を付与
					int i =0;
					i++;
					DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
					FileOutputStream fos = new FileOutputStream(
							"/Users/shuhei/Desktop/test/うさみはる" + df.format(sts.getCreatedAt()) + i + TARGET_EXTENSION);
					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				}
			}
		}
	}
}
