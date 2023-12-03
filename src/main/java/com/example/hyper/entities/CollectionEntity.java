package com.example.hyper.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

public class CollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COLLECTION_SEQ")
    @SequenceGenerator(name = "ARTIST_SEQ", sequenceName = "COLLECTION_SEQ", allocationSize = 1)
    private Long id;

    //private List<playlist> playlists
}