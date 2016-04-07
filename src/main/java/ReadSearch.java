import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadSearch{
    private String startURL = "https://twitter.com/search?f=realtime&q=from%3A";
    private String middleURL = "%20%40";
    private String endURL = "&src=typd";
    private String url = "https://twitter.com/search?q=from%3Asantander_resp%20since%3A2016-01-04%20until%3A2016-01-15&src=typd";

    public ArrayList<Tweet> getTweets(String user, String troll) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        String expr = "small class=\"time\".*?href=\"/"
                + "([^/]+)"
                + ".*?status/"
                + "([^\"]+)"
                + ".*?title=\""
                + "([^\"]+)";
        Pattern patt = Pattern.compile(expr, Pattern.DOTALL | Pattern.UNIX_LINES);
        try {
//            Matcher m = patt.matcher(getData(startURL+user+middleURL+troll+endURL));
            Matcher m = patt.matcher(getData(url));
            while (m.find()) {
                if(user.equals(m.group(1).trim())){
                    Tweet tw = new Tweet();
                    tw.setUser(m.group(1).trim());
                    tw.setTweetid(Long.parseLong(m.group(2).trim()));
                    tw.setDate(m.group(3).trim());
                    tweets.add(tw);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception " + e);
        }
        return tweets;
    }

    private StringBuilder getData(String dataurl) throws MalformedURLException, IOException{
        URL url = new URL(dataurl);
//        https://twitter.com/search?f=realtime&q=from%3Arusansor%20%40santander_resp&src=typd
//        https://twitter.com/search?q=from%3Asantander_resp%20since%3A2016-01-04%20until%3A2016-02-01&src=typd
        HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
        httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
        StringBuilder sb = new StringBuilder(16384);
        BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), "ISO-8859-1"));
        String line;
        while ((line = br.readLine()) != null){
            sb.append(line);
            sb.append('\n');
        }
        httpcon.disconnect();
        br.close();
        System.out.println(sb.toString());
        return sb;
    }

    public static void main(String [] args){
        //testing
        ReadSearch rs = new ReadSearch();
        ArrayList<Tweet> tweets = rs.getTweets("Tony_Kennah", "PickLuckier");
        for(Tweet t : tweets){
            System.out.println("TWEET: " + t.toString());
        }
    }
}
