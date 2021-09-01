import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;


public class CompressDecompress {

	public static String compressEventPayload(String eventPayload) {
		if (eventPayload == null || eventPayload.length() == 0) {
			return eventPayload;
		}
		ByteArrayOutputStream obj = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(obj);
			gzip.write(eventPayload.getBytes("UTF-8"));
			gzip.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Failed to zip content", e);
		}

		byte[] compressedPayloadBytes = obj.toByteArray();
		String compressedPayload = Base64.encodeBase64String(compressedPayloadBytes);
		return compressedPayload;
	}
	
	public static String decompressEventPayload(String str) {
    	byte[] compressed = Base64.decodeBase64(str);
    	
        if ((compressed.length == 0)) {
            throw new IllegalArgumentException("Cannot unzip null or empty bytes");
        }
 
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed)) {
            try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
                try (InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8)) {
                    try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                        StringBuilder output = new StringBuilder();
                        String line;
                        while((line = bufferedReader.readLine()) != null){
                            output.append(line);
                        }
                        return output.toString();
                    }
                }
            }
        } catch(IOException e) {
            throw new RuntimeException("Failed to unzip content", e);
        }
    }
}
