package com.application.blog.services.impl;

import org.springframework.stereotype.Service;

@Service
public class PathUtils {
    public String normalizedPath(String path){
        if (path == null) {
            return null;
        }
        return path.endsWith("/") ? path : path + "/";
    }
}
