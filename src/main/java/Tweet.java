public class Tweet{ 
    private String user;
    private long tweetid;
    private String date;

    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }
    public long getTweetid(){
        return tweetid;
    }
    public void setTweetid(long tweetid){
        this.tweetid = tweetid;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String toString(){
        return this.tweetid + " " + this.user + " " + this.date;
    }
}