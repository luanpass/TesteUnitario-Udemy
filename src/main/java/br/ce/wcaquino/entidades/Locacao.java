package br.ce.wcaquino.entidades;

import java.util.Date;
import java.util.List;

public class Locacao {

	private Usuario usuario;
	private List<Filme> filme;
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;
	public Locacao(){
		valor = new Double(0.0);
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor){
		this.valor = valor;
	}
	public List<Filme> getFilme() {
		return filme;
	}
	public void setFilme(List<Filme> filme) {
		this.filme = filme;
	}

}