package com.example.demo.controller;

import java.awt.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/api/image")
@RestController
public class ImageController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE, consumes = "application/json")
    public byte[] getImage(@RequestParam int id,
                           @RequestParam(value = "length", required = false, defaultValue = "200") int length,
                           @RequestParam(value = "width") int breadth){
        String url = "https://picsum.photos/id/" + id + "/" + length + "/" + breadth;
        byte[] forObject = restTemplate.getForObject(url, byte[].class);
        return forObject;

    }
}
