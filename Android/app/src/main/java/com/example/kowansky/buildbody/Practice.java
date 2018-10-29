package com.example.kowansky.buildbody;

public class Practice {
    //private String url;
    private String description;
    private int imageId;

    public Practice(String description, int imageId) {
        //this.url = url;
        this.description = description;
        this.imageId = imageId;
    }

    /*public String getUrl() {
        return url;
    }
*/
    public String getDescription() {
        return description;
    }

  /*  public void setUrl(String url) {
        this.url = url;
    }
*/
    public void setDescription(String description) {
        this.description = description;
    }


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
