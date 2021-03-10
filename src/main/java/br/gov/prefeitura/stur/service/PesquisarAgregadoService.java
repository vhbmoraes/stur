package br.gov.prefeitura.stur.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class PesquisarAgregadoService {
	
	@Value("${app.url.mimg}")
	private String urlMimg;

	public List<ResultadoAgregadoDto> pesquisar() {
		try {
			System.out.println(urlMimg);

			HttpURLConnection conn = (HttpURLConnection) new URL(urlMimg).openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

			if (conn.getResponseCode() != 200) {
				System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + urlMimg);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));

			String output = "";
			String line;
			while ((line = br.readLine()) != null) {
				output += line;
			}

			conn.disconnect();

			Gson gson = new Gson();

			ResultadoAgregadoDto[] dto = gson.fromJson(new String(output.getBytes()), ResultadoAgregadoDto[].class);
			List<ResultadoAgregadoDto> resultados = new ArrayList<ResultadoAgregadoDto>();
			for (ResultadoAgregadoDto a : dto) {
				resultados.add(a);
			}
			return resultados;

		} catch (IOException ex) {
			ex.getMessage();
		}
		return null;
	}
}
