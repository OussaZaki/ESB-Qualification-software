/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import view.MessageHandler;

/**
 *
 * @author samsung
 */
public class Luncher extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<Provider> providers = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();
        Scenario scenario;
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("scName");
            int number = Integer.parseInt(request.getParameter("number"));
            for (int i = 1; i <= number; i++) {
                int processTime = Integer.parseInt(request.getParameter("process" + i));
                int dataX = Integer.parseInt(request.getParameter("data" + i));
                int req = Integer.parseInt(request.getParameter("req" + i));
                ProcessingTime processingTime = new ProcessingTime(processTime, "ms");
                DataExchangeSize data = new DataExchangeSize(dataX, "byte");

                consumers.add(new Consumer(i, req));
                providers.add(new Provider(i, processingTime, data));
            }
            scenario = new Scenario(1, providers, number, name, consumers);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Luncher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Scenario lunched</h1>");
            out.println(scenario.toString().replaceAll("\n", "<br>"));
            System.out.println(scenario.toString());
            new Thread("567") {
                @Override
                public void run() {
                    try {
                        LogGetter.go();
                    } catch (Exception ex) {
                    }
                }
            }.start();
            MessageHandler.providerHandler(scenario);
            MessageHandler.consumerHandler(scenario);
            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
            Iterator<Thread> i = threadSet.iterator();
            //out.println("trying to show results");
            while (i.hasNext()) {
                //out.println("a thread");
                Thread t = i.next();
                if (t.getName().equals("567")) {
                    //out.println("our thread detected.. looping");
                    while (t.isAlive()) {
                        
                    }
                    // TO DO integrate results and graphs
                    out.println(LogGetter.getLog().replaceAll("\n", "<br>"));
                    //out.println("all good");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Luncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Luncher.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
