package br.gov.prefeitura.stur.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.stur.service.ResultadoAgregadoDto;
import br.gov.prefeitura.stur.service.PesquisarAgregadoService;

@RestController
public class CalculoImpostoController {
	
	@Autowired
	private PesquisarAgregadoService servico;
	
	@RequestMapping("/resultadoIPTU")
	public List<ImpostoDto> calcular(Integer inscricaoImovel)
	{
		if (inscricaoImovel == null || inscricaoImovel != 123456)
		{
			List<ImpostoDto> dto = new ArrayList<>();
			ImpostoDto i = new ImpostoDto();
			i.setMensagem("Nenhum registro encontrato");
			dto.add(i);
			return dto;
		}
		
		List<ResultadoAgregadoDto> agregados = servico.pesquisar();
		List<ImpostoDto> dto = ImpostoDto.converter(agregados);
		return dto;
	}
}