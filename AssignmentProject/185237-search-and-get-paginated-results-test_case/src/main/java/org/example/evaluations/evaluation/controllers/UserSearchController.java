package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.UserSearchRequestDto;
import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.services.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserSearchController {

    @Autowired
    private UserSearchService userSearchService;

    @PostMapping("/search")
    public List<User> searchUsersByAddress(@RequestBody UserSearchRequestDto userSearchRequestDto) {
          return userSearchService.getUsersHavingAddress(userSearchRequestDto.getQuery(), userSearchRequestDto.getPageNumber());
    }

    @GetMapping("search/ladies")
    public List<User> searchLadies(@RequestParam(required = true) Integer pageNumber) {
        return userSearchService.getDetailsOfAllLadies(pageNumber);
    }

    @GetMapping("search/adultMales")
    public List<User> searchAdultMales(@RequestParam(required = true) Integer pageNumber) {
        return userSearchService.getDetailsOfAllAdultMales(pageNumber);
    }
}
