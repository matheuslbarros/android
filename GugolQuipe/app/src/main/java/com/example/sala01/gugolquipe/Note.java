package com.example.sala01.gugolquipe;

import java.util.HashMap;
import java.util.Map;

public class Note {

    public String id;
    public String user;
    public String title;
    public String description;

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("title", title);
        result.put("description", description);
        return result;
    }

}
