package ca.jrvs.apps.twitter.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterUtils;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

    private TwitterService service;

    @Before
    public void setupService() {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
                tokenSecret);

        TwitterDao dao = new TwitterDao(httpHelper);
        this.service = new TwitterService(dao);
    }

    @Test
    public void postTweet() {
        String text = "Test postTweet method in twitter service " + System.currentTimeMillis();
        double lat = 30;
        double lon = 30;
        Tweet postTweet = service.postTweet(TwitterUtils.buildTweet(text, lon, lat));

        assertNotNull(postTweet);
        assertEquals(text, postTweet.getText());

    }

    @Test
    public void showTweet() {
        String text = "Test showTweet method in twitter service " + System.currentTimeMillis();
        double lat = 30;
        double lon = 30;
        Tweet testTweet = service.postTweet(TwitterUtils.buildTweet(text, lon, lat));
        String id = testTweet.getIdString();
        Tweet showTweet = service.showTweet(id, null);

        assertNotNull(showTweet);
        assertEquals(testTweet.getId(), showTweet.getId());

    }

    @Test
    public void deleteTweets() {
        String text = "Test deleteTweets method in twitter service " + System.currentTimeMillis();
        double lat = 30;
        double lon = 30;
        Tweet testTweet = service.postTweet(TwitterUtils.buildTweet(text, lon, lat));
        String id = testTweet.getIdString();
        String[] ids = {id};
        List<Tweet> deleteTweets = service.deleteTweets(ids);

        Tweet deleteTweet = deleteTweets.get(0);

        assertNotNull(deleteTweet);
        assertEquals(testTweet.getId(), deleteTweet.getId());

    }
}