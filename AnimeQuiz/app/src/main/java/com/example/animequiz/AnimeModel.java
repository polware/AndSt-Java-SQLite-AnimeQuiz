package com.example.animequiz;

public class AnimeModel {
    private int anime_ID;
    private String anime_name, anime_image;

    public AnimeModel() {
    }

    public AnimeModel(int anime_ID, String anime_name, String anime_image) {
        this.anime_ID = anime_ID;
        this.anime_name = anime_name;
        this.anime_image = anime_image;
    }

    public int getAnime_ID() {
        return anime_ID;
    }

    public void setAnime_ID(int anime_ID) {
        this.anime_ID = anime_ID;
    }

    public String getAnime_name() {
        return anime_name;
    }

    public void setAnime_name(String anime_name) {
        this.anime_name = anime_name;
    }

    public String getAnime_image() {
        return anime_image;
    }

    public void setAnime_image(String anime_image) {
        this.anime_image = anime_image;
    }
}
