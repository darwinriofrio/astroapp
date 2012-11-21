/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tfg.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author darwin
 */
@ManagedBean
@RequestScoped
public class respuestaBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/www.webservicex.net/CurrencyConvertor.asmx.wsdl")
    private CurrencyConvertor service;

    /**
     * Creates a new instance of respuestaBean
     */
    public respuestaBean() {
    }
    
    private String saludo;
    private String nombre;
    private String apellidos;
    private Integer edad;
    
    private String monedaA;
    private String monedaB;
    private double valor;
    private double resultado;
    
    
    
    
    public Integer getEdad() {
        return edad;
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getSaludo() {
        return saludo;
    }
    
    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }
    
    public String getMonedaA() {
        return monedaA;
    }
    
    public void setMonedaA(String monedaA) {
        this.monedaA = monedaA;
    }
    
    public String getMonedaB() {
        return monedaB;
    }
    
    public void setMonedaB(String monedaB) {
        this.monedaB = monedaB;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public double getResultado() {
        return resultado;
    }
    
    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
    
    public void convertir(){
        //setResultado(6.9);
        Currency parm1 = Currency.fromValue(getMonedaA());
        Currency parm2 = Currency.fromValue(getMonedaB());
        setResultado((getValor()) * (conversionRate(parm1, parm2)));
    }

    /*private double conversionRate(guestbook.Currency fromCurrency, guestbook.Currency toCurrency) {
        guestbook.CurrencyConvertorSoap port = service.getCurrencyConvertorSoap12();
        return port.conversionRate(fromCurrency, toCurrency);
    }*/

    private static double conversionRate(com.tfg.jsf.Currency fromCurrency, com.tfg.jsf.Currency toCurrency) {
        com.tfg.jsf.CurrencyConvertor service = new com.tfg.jsf.CurrencyConvertor();
        com.tfg.jsf.CurrencyConvertorSoap port = service.getCurrencyConvertorSoap12();
        return port.conversionRate(fromCurrency, toCurrency);
    }
}
