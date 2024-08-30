package com.example.demo;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AzuriteEmptyBlobTest {

    private static final String CONTAINER_NAME = "test-42b8bbde-5e85-4359-9130-64822da82035";

    private BlobContainerClient blobContainerClient;
    private BlobClient blobClient;

    @BeforeEach
    void setUp() {
        String connectionString = Objects.requireNonNull(
                System.getenv("AZURE_STORAGE_CONNECTION_STRING"));
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        blobContainerClient = blobServiceClient.getBlobContainerClient(CONTAINER_NAME);
        blobContainerClient.createIfNotExists();

        blobClient = blobContainerClient.getBlobClient("empty-file.txt");
        blobClient.upload(new ByteArrayInputStream(new byte[0]));
    }

    @AfterEach
    void tearDown() {
        blobClient.delete();
        blobContainerClient.deleteIfExists();
    }

    @Test
    void testEmptyBlob() throws IOException {
        byte[] bytes;
        try (InputStream is = blobClient.openInputStream()) {
            bytes = is.readAllBytes();
        }

        assertArrayEquals(new byte[0], bytes);
    }

}
