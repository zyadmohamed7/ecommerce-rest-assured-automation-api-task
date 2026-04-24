package org.example.framework.apis;

import io.restassured.response.Response;
import org.example.framework.config.Routes;

import org.example.framework.client.ApiClient;

import java.io.File;

public class FilesApi {

    public static Response uploadFile(File file) {
        return ApiClient.sendMultipart("POST", Routes.FILES_UPLOAD.getPath(), file);
    }
}
