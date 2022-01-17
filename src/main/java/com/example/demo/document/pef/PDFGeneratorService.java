package com.example.demo.document.pef;

import com.example.demo.transfers.Transfer;

import javax.servlet.http.HttpServletResponse;

public interface PDFGeneratorService {

    void export(HttpServletResponse response, Transfer transfer);

}
