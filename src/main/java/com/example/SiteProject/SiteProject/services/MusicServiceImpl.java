package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.repositories.MusicRepository;

public class MusicServiceImpl implements MusicService {

    private static MusicRepository musicRepository;

    public MusicDTO save(MusicDTO music) {

        MusicDTO response = new MusicDTO();
        try{
            response = new MusicDTO();//musicRepository.save(music);
        }catch (Exception e){
            System.out.println(e);
        }
        return response;
    }

    @Override
    public MusicDTO delete(Long id) {
        return null;
    }

}
