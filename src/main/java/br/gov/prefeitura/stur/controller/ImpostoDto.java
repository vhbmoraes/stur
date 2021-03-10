package br.gov.prefeitura.stur.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.gov.prefeitura.stur.service.ResultadoAgregadoDto;

public class ImpostoDto {
	
	private String mensagem;
	private Integer inscricaoImovel;
	private BigDecimal valorMetro;
	private BigDecimal quantidadeMetroQuadrado;
	private BigDecimal valorVenal;
	
	private BigDecimal aliquota;
	private BigDecimal valorIptu;
	private BigDecimal valorAVista;
	private BigDecimal valorParcela1;
	private BigDecimal valorParcela2;
	private BigDecimal valorParcela3;
	private BigDecimal valorParcela4;
	private BigDecimal valorParcela5;
	private BigDecimal valorParcela6;
	
	public ImpostoDto() {}
	
	public ImpostoDto(ResultadoAgregadoDto dto)
	{
		inscricaoImovel = 132456;
		valorMetro = dto.getValorSerie();
		quantidadeMetroQuadrado = new BigDecimal("180");
		valorVenal = valorMetro.multiply(quantidadeMetroQuadrado);
		aliquota = new BigDecimal("0.70");
		valorIptu = valorVenal.multiply(aliquota.divide(new BigDecimal("100")));
		BigDecimal desconto = valorIptu.multiply(new BigDecimal("0.1"));
		valorAVista = valorIptu.subtract(desconto);
		valorParcela1 = valorIptu.divide(new BigDecimal("6")).setScale(2, BigDecimal.ROUND_DOWN);
		valorParcela2 = valorParcela1;
		valorParcela3 = valorParcela1;
		valorParcela4 = valorParcela1;
		valorParcela5 = valorParcela1;
		BigDecimal valor = valorParcela1.multiply(new BigDecimal("6"));
		BigDecimal residuo = valorIptu.subtract(valor);
		if (residuo.compareTo(BigDecimal.ZERO) == 1)
			valorParcela6 = valorParcela1.add(residuo);
		else
			valorParcela6 = valorParcela1.subtract(residuo);
		mensagem = "Sucesso";
	}
	
	public static List<ImpostoDto> converter(List<ResultadoAgregadoDto> agregados)
	{
		return agregados.stream().map(ImpostoDto::new).collect(Collectors.toList());
	}

	public static ImpostoDto converter(ResultadoAgregadoDto agregado)
	{
		return new ImpostoDto(agregado);
	}
	
	
	public Integer getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(Integer inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public BigDecimal getValorMetro() {
		return valorMetro;
	}
	public void setValorMetro(BigDecimal valorMetro) {
		this.valorMetro = valorMetro;
	}
	public BigDecimal getValorVenal() {
		return valorVenal;
	}
	public void setValorVenal(BigDecimal valorVenal) {
		this.valorVenal = valorVenal;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public BigDecimal getQuantidadeMetroQuadrado() {
		return quantidadeMetroQuadrado;
	}
	public void setQuantidadeMetroQuadrado(BigDecimal quantidadeMetroQuadrado) {
		this.quantidadeMetroQuadrado = quantidadeMetroQuadrado;
	}
	public BigDecimal getAliquota() {
		return aliquota;
	}
	public void setAliquota(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}
	public BigDecimal getValorIptu() {
		return valorIptu;
	}
	public void setValorIptu(BigDecimal valorIptu) {
		this.valorIptu = valorIptu;
	}
	public BigDecimal getValorAVista() {
		return valorAVista;
	}
	public void setValorAVista(BigDecimal valorAVista) {
		this.valorAVista = valorAVista;
	}
	
	public BigDecimal getValorParcela1() {
		return valorParcela1;
	}

	public void setValorParcela1(BigDecimal valorParcela1) {
		this.valorParcela1 = valorParcela1;
	}

	public BigDecimal getValorParcela2() {
		return valorParcela2;
	}

	public void setValorParcela2(BigDecimal valorParcela2) {
		this.valorParcela2 = valorParcela2;
	}

	public BigDecimal getValorParcela3() {
		return valorParcela3;
	}

	public void setValorParcela3(BigDecimal valorParcela3) {
		this.valorParcela3 = valorParcela3;
	}

	public BigDecimal getValorParcela4() {
		return valorParcela4;
	}

	public void setValorParcela4(BigDecimal valorParcela4) {
		this.valorParcela4 = valorParcela4;
	}

	public BigDecimal getValorParcela5() {
		return valorParcela5;
	}

	public void setValorParcela5(BigDecimal valorParcela5) {
		this.valorParcela5 = valorParcela5;
	}

	public BigDecimal getValorParcela6() {
		return valorParcela6;
	}
	public void setValorParcela6(BigDecimal valorParcela6) {
		this.valorParcela6 = valorParcela6;
	}
}
