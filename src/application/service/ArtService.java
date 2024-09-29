package application.service;

import java.io.FileOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class ArtService {

	private static final String API_URL = "https://api.stability.ai/v2beta/stable-image/generate/ultra";
	private static final String API_KEY = "YOUR API TOKEN HERE";

	public String generateImage(String description) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		String boundary = createBoundary();

		String body = "--" + boundary + "\r\n" +
				"Content-Disposition: form-data; name=\"prompt\"\r\n\r\n" +
				description + "\r\n" +
				"--" + boundary + "\r\n" +
				"Content-Disposition: form-data; name=\"output_format\"\r\n\r\n" +
				"png\r\n" +
				"--" + boundary + "--\r\n";

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(API_URL))
				.header("Authorization", "Bearer " + API_KEY)
				.header("Content-Type", "multipart/form-data; boundary=" + boundary)
				.header("Accept", "image/*")
				.POST(HttpRequest.BodyPublishers.ofString(body))
				.build();

		HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

		if (response.statusCode() == 200) {
			try (FileOutputStream fos = new FileOutputStream("generated_image.png")) {
				fos.write(response.body());
			}
			return "Imagem gerada com sucesso!";
		} else {
			String errorMessage = new String(response.body());
			throw new RuntimeException("Falha: " + response.statusCode() + " - " + errorMessage);
		}

	}


	private String createBoundary() {
		Random random = new Random();
		return Long.toHexString(random.nextLong());
	}

}