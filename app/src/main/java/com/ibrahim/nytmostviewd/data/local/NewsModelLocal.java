package com.ibrahim.nytmostviewd.data.local;

import androidx.annotation.NonNull;

public class NewsModelLocal implements Comparable, Cloneable {

    private long id;
    private String thumbUrlMedium;
    private String thumbUrlLarge;
    private String imageCaption;
    private String title;
    private String desc;
    private String copyrights;

    //empty constructor for initializations
    public NewsModelLocal() {

    }

    public NewsModelLocal(long id, String thumbUrlMedium, String thumbUrlLarge, String imageCaption, String title, String desc, String copyrights) {
        this.id = id;
        this.thumbUrlMedium = thumbUrlMedium;
        this.thumbUrlLarge = thumbUrlLarge;
        this.imageCaption = imageCaption;
        this.title = title;
        this.desc = desc;
        this.copyrights = copyrights;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThumbUrlMedium() {
        return thumbUrlMedium;
    }

    public void setThumbUrlMedium(String thumbUrlMedium) {
        this.thumbUrlMedium = thumbUrlMedium;
    }

    public String getThumbUrlLarge() {
        return thumbUrlLarge;
    }

    public void setThumbUrlLarge(String thumbUrlLarge) {
        this.thumbUrlLarge = thumbUrlLarge;
    }

    public String getImageCaption() {
        return imageCaption;
    }

    public void setImageCaption(String imageCaption) {
        this.imageCaption = imageCaption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    @Override
    public int compareTo(Object o) {

        NewsModelLocal compare = (NewsModelLocal) o;

        if(thumbUrlLarge.equals(((NewsModelLocal) o).thumbUrlLarge) && thumbUrlMedium.equals(((NewsModelLocal) o).thumbUrlMedium)
        && title.equals(((NewsModelLocal) o).title) && desc.equals(((NewsModelLocal) o).desc)){
            return 0;
        }

        return 1;
    }

    @NonNull
    @Override
    protected NewsModelLocal clone() {

        NewsModelLocal clone = null;
        try{

            clone = (NewsModelLocal) super.clone();

        }catch (CloneNotSupportedException ex){

        }

        return clone;
    }


}
