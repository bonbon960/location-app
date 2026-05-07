package com.example.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.Location;

@Service
public class LocationService {

    private final Map<String, Location> locations = new HashMap<>();
    private final Map<String, Location> destinations = new HashMap<>();

    // ⭐ 位置保存（userIdごと上書き）
    public void save(Location loc) {
        loc.setTimestamp(System.currentTimeMillis());
        locations.put(loc.getUserId(), loc);
    }

    // ⭐ 全取得
    public Collection<Location> getAll() {
        return locations.values();
    }

    // ⭐ 目的地保存
    public void setDestination(Location loc) {
        destinations.put(loc.getGroupId(), loc);
    }

    // ⭐ 目的地取得
    public Location getDestination(String groupId) {
        return destinations.get(groupId);
    }

    // ⭐ 古いデータ削除（5分）
    public void cleanup() {
        long now = System.currentTimeMillis();
        locations.values().removeIf(loc ->
            now - loc.getTimestamp() > 300000
        );
    }
}