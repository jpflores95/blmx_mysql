package net.tutorial.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

public class TextToSpeechService {

	TextToSpeech service;

	public TextToSpeechService() {
		EnvVariables envVar = new EnvVariables();
		Map<String, String> creds = envVar.getCredentials("text_to_speech");

		service = new TextToSpeech(creds.get("username"), creds.get("password"));
		service.setUsernameAndPassword(creds.get("username"), creds.get("password"));
	}

	public void getAudio(String text, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition","attachment;filename=output.wav");
		
		InputStream stream = service.synthesize(text, Voice.EN_ALLISON, AudioFormat.WAV).execute();
		InputStream in = WaveUtils.reWriteWaveHeader(stream);
		// OutputStream out = new FileOutputStream("output.wav");
		ServletOutputStream out = resp.getOutputStream();
		
		byte[] buffer = new byte[1024];

		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		out.close();
		in.close();
		stream.close();

	}

}
