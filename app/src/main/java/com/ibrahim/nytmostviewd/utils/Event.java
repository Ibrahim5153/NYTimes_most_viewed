package com.ibrahim.nytmostviewd.utils;

public class Event<T> {

    private boolean hasBeenHandeled = false;
    private T content;

    public Event(T content) {

        this.content = content;
    }

    public boolean hasBeenHandeled() {
        return hasBeenHandeled;
    }

    public T getContent(){
        if(hasBeenHandeled){
            return null;
        }else {
            hasBeenHandeled = true;
            return content;
        }

    }

    public void resetHandling(){
        hasBeenHandeled = false;
    }

}