package gov.iti.jets.persistence.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.io.File;

public class ImageConversion {

    public static byte[] convertImageToBytes(File file) throws IOException {
        byte[] data = Files.readAllBytes(file.toPath());
        return data;

    }

    public static byte[] convertBlobToBytes(Blob aBlob) throws SQLException {

        byte[] imageByte = aBlob.getBytes(1, (int) aBlob.length());
        return imageByte;
    }
}
