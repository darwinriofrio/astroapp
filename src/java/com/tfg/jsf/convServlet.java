/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tfg.jsf;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author darwin
 */
public class convServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            double valor = Integer.parseInt(request.getParameter("valor"));
            double inicial = valor;
            String tipox = request.getParameter("tipoA");
            String tipoy = request.getParameter("tipoB");
            com.tfg.jsf.Currency tipoA;
            com.tfg.jsf.Currency tipoB;
            tipoA = com.tfg.jsf.Currency.fromValue(tipox);
            tipoB = com.tfg.jsf.Currency.fromValue(tipoy);
            
            double cambio = conversionRate(tipoA, tipoB);
            valor = valor * cambio;
            
            request.setAttribute("final", valor);
            request.setAttribute("inicial", inicial);
            request.setAttribute("tipoA", tipoA);
            request.setAttribute("tipoB", tipoB);
            RequestDispatcher rd = request.getRequestDispatcher("respuesta.jsp");
            rd.forward(request, response);
        } finally {            
            out.close();
        }
    }
    

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static double conversionRate(com.tfg.jsf.Currency fromCurrency, com.tfg.jsf.Currency toCurrency) {
        com.tfg.jsf.CurrencyConvertor service = new com.tfg.jsf.CurrencyConvertor();
        com.tfg.jsf.CurrencyConvertorSoap port = service.getCurrencyConvertorSoap12();
        return port.conversionRate(fromCurrency, toCurrency);
    }

    
}
