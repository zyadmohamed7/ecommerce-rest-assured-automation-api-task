package org.example.testscases.files;

import org.example.framework.apis.FilesApi;
import org.example.framework.client.ApiClient;
import org.example.framework.config.Routes;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("File Management")
@Story("Tests for uploading files")
public class UploadFile {

    private File dummyImage;

    @BeforeClass
    public void setup() throws IOException {
        dummyImage = new File("dummy.jpg");
        try (FileWriter writer = new FileWriter(dummyImage)) {
            writer.write("fake image content");
        }
    }

    @AfterClass
    public void teardown() {
        if (dummyImage.exists()) {
            dummyImage.delete();
        }
    }

    @Test
    public void userCanUploadAFile() {
        FilesApi.uploadFile(dummyImage)
                .then()
                .statusCode(201)
                .body("location", notNullValue())
                .body("filename", notNullValue())
                .log().ifValidationFails();
    }

    @Test
    public void uploadingWithoutFileDataReturnsError() {
        ApiClient.send("POST", Routes.FILES_UPLOAD.getPath(), null)
                .then()
                .statusCode(anyOf(is(400), is(500), is(422)))
                .log().ifValidationFails();
    }
}
