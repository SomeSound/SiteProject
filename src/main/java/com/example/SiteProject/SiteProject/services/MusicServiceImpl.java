package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.repositories.MusicRepository;

public class MusicServiceImpl {

    private static MusicRepository musicRepository;

    public MusicDTO save(MusicDTO music) {

        MusicDTO response = new MusicDTO();
        try{
            response = new MusicDTO(music.getName(), music.getDuration());//musicRepository.save(music);
        }catch (Exception e){
            System.out.println(e);
        }
        return response;
    }

}
