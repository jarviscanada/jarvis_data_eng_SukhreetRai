package ca.jrvs.apps.twitter.model;

public class UserMention {

    private int id;
    private String idString;
    private int[] indices = new int[2];
    private String name;
    private String screenName;

    public UserMention() {
        super();
    }

    public UserMention(int id, String idString, int[] indices, String name, String screenName) {

        this.id = id;
        this.idString = idString;
        this.indices = indices;
        this.name = name;
        this.screenName = screenName;

    }

    public int getId() {
        return id;
    }

    public String getIdString() {
        return idString;
    }

    public int[] getIndices() {
        return indices;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}