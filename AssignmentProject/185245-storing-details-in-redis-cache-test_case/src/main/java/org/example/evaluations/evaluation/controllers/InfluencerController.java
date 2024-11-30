package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.AddInfluencerDto;
import org.example.evaluations.evaluation.models.Influencer;
import org.example.evaluations.evaluation.services.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/influencer")
public class InfluencerController {

    @Autowired
    private InfluencerService influencerService;


    @PostMapping
    public Boolean addInfluencer(@RequestBody AddInfluencerDto addInfluencerDto) {
        Influencer influencer = new Influencer();
        influencer.setFollowers(addInfluencerDto.getFollowers());
        influencer.setName(addInfluencerDto.getName());
        influencer.setBio(addInfluencerDto.getBio());
        influencer.setTotalPosts(addInfluencerDto.getTotalPosts());
        influencer.setDisplayPictureUrl(addInfluencerDto.getDisplayPictureUrl());
        influencer.setTotalPostReach(addInfluencerDto.getTotalPostReach());
        return influencerService.addInfluencerToCache(influencer);
    }


    @GetMapping
    public List<Influencer> getAllViralInfluencers() {
        return influencerService.getAllViralInfluencers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Influencer> getInfluencer(@PathVariable Long id) {
        Influencer influencer = influencerService.getInfluencerDetails(id);
        if(influencer == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(influencer,HttpStatus.OK);
    }
}
