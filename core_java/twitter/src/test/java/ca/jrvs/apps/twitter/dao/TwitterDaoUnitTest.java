package ca.jrvs.apps.twitter.dao;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterUtils;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

    @Mock
    HttpHelper mockHelper;

    @InjectMocks
    TwitterDao dao;

    @Test
    public void showTweet() throws Exception {
        //test failed request
        String text = "This is a mock test for showTweet";
        double lat = 30;
        double lon = 30;
        //exception expected here
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(TwitterUtils.buildTweet(text, lat, lon));
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        //Test good path
        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "   \"id\":12345678901234567890,\n"
                + "   \"id_str\":\"12345678901234567890\",\n"
                + "   \"text\":\"test with mocks\",\n"
                + "   \"entities\":{\n"
                + "       \"hashtags\":[],"
                + "       \"user_mentions\":[]"
                + "   },\n"
                + "   \"coordinates\":null,\n"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = TwitterUtils.toObjectFromJson(tweetJsonStr, Tweet.class);
        //mock parseResponseBody
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.create(TwitterUtils.buildTweet(text, lat, lon));
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void create() throws Exception {
        String hashtag = "#abc";
        String text = "some text " + hashtag + " " + System.currentTimeMillis();
        Double lat = 1d;
        Double lon = -1d;

        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("Mock"));
        try {
            dao.create(TwitterUtils.buildTweet(text, lat, lon));
            fail();
        } catch (RuntimeException ex) {
            assertTrue(true);
        }

        String tweetJsonStr = "{\n"
                + "  \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "  \"id\":1097607853932564480,\n"
                + "  \"id_str\":\"1097607853932564480\",\n"
                + "  \"text\":\"test with location\",\n"
                + "  \"entities\":{\n"
                + "    \"hashtags\":[],\n"
                + "    \"user_mentions\":[]\n"
                + "  },\n"
                + "  \"coordinates\":null,\n"
                + "  \"retweet_count\":0,\n"
                + "  \"favorite_count\":0,\n"
                + "  \"favorited\":false,\n"
                + "  \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = TwitterUtils.toObjectFromJson(tweetJsonStr, Tweet.class);

        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.create(TwitterUtils.buildTweet(text, lat, lon));
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void deleteById() throws Exception {
        String tweetJsonStr = "{\n"
                + "  \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "  \"id\":1097607853932564480,\n"
                + "  \"id_str\":\"1097607853932564480\",\n"
                + "  \"text\":\"test with location\",\n"
                + "  \"entities\":{\n"
                + "    \"hashtags\":[],\n"
                + "    \"user_mentions\":[]\n"
                + "  },\n"
                + "  \"coordinates\":null,\n"
                + "  \"retweet_count\":0,\n"
                + "  \"favorite_count\":0,\n"
                + "  \"favorited\":false,\n"
                + "  \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = TwitterUtils.toObjectFromJson(tweetJsonStr, Tweet.class);

        doReturn(expectedTweet).doReturn(null).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.deleteById("1097607853932564480");
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
        tweet = spyDao.deleteById("1097607853932564480");
        assertNull(tweet);
    }
}