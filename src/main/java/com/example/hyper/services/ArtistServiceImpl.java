package com.example.hyper.services;

import com.example.hyper.entities.ArtistEntity;

public class ArtistServiceImpl {

    public String save(String name){
        ArtistEntity artist = new ArtistEntity();

        return "OK";
    }
}
