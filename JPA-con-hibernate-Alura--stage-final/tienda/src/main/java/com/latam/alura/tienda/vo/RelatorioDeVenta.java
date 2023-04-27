package com.latam.alura.tienda.vo;

import java.time.LocalDate;

public class RelatorioDeVenta {
	
	private String nombreDelProducto;
	private long cantidadDeProducto;
	private LocalDate FechadeUltimaVenta;
	
	public RelatorioDeVenta(String nombreDelProducto, long cantidadDeProducto, LocalDate fechadeUltimaVenta) {
		this.nombreDelProducto = nombreDelProducto;
		this.cantidadDeProducto = cantidadDeProducto;
		FechadeUltimaVenta = fechadeUltimaVenta;
	}

	public String getNombreDelProducto() {
		return nombreDelProducto;
	}

	public void setNombreDelProducto(String nombreDelProducto) {
		this.nombreDelProducto = nombreDelProducto;
	}

	public long getCantidadDeProducto() {
		return cantidadDeProducto;
	}

	public void setCantidadDeProducto(long cantidadDeProducto) {
		this.cantidadDeProducto = cantidadDeProducto;
	}

	public LocalDate getFechadeUltimaVenta() {
		return FechadeUltimaVenta;
	}

	public void setFechadeUltimaVenta(LocalDate fechadeUltimaVenta) {
		FechadeUltimaVenta = fechadeUltimaVenta;
	}

	@Override
	public String toString() {
		return "RelatorioDeVenta [nombreDelProducto=" + nombreDelProducto + ", cantidadDeProducto=" + cantidadDeProducto
				+ ", FechadeUltimaVenta=" + FechadeUltimaVenta + "]";
	}
	
	
	
}
