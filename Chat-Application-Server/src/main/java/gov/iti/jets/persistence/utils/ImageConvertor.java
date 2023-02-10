package gov.iti.jets.persistence.utils;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

public class ImageConvertor {

    public static byte[] BlobToBytes(Blob blob) {

        if(blob == null){
            return null;
        }

        byte[] blobAsBytes = null;

        try {
            int blobLength = (int) blob.length();
            blobAsBytes = blob.getBytes(1, blobLength);
            blob.free();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return blobAsBytes;
    }

    public static Blob bytesToBlob(byte[] bytes) {
        Blob blob = null;
        try {
            blob = new SerialBlob(bytes);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blob;
    }
}
