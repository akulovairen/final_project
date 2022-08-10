package com.example.servletstest.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/images")
public class ImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filenameParam = req.getParameter("filename");
        if (filenameParam == null || !filenameParam.contains(".")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String extension = filenameParam.substring(filenameParam.lastIndexOf(".") + 1);

        ServletContext cntx= req.getServletContext();
        // Get the absolute path of the image
        String filename = cntx.getRealPath("img/" + filenameParam);

        File f = new File(filename);
        BufferedImage bi = ImageIO.read(f);
        OutputStream out = resp.getOutputStream();
        ImageIO.write(bi, extension, out);
        out.close();
    }
}