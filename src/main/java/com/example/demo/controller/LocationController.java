package com.example.demo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Location;
import com.example.demo.service.LocationService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // 開発用
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    // ⭐ 位置登録
    @PostMapping("/location")
    public void save(@RequestBody Location loc) {
        service.save(loc);
    }

    // ⭐ グループごと取得
    @GetMapping("/locations")
    public Collection<Location> getAll(@RequestParam String groupId) {

        if (groupId == null || groupId.length() > 20) {
            return List.of();
        }

        service.cleanup();

        return service.getAll().stream()
                .filter(loc -> groupId.equals(loc.getGroupId()))
                .toList();
    }

    // ⭐ 目的地保存
    @PostMapping("/destination")
    public void setDestination(@RequestBody Location loc) {
        service.setDestination(loc);
    }

    // ⭐ 目的地取得
    @GetMapping("/destination")
    public Location getDestination(@RequestParam String groupId) {
        return service.getDestination(groupId);
    }
}