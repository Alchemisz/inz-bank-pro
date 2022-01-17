package com.example.demo.document.pef;

import com.example.demo.transfers.Transfer;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFGeneratorServiceImpl implements PDFGeneratorService{

    @Override
    public void export(HttpServletResponse response, Transfer transfer) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(22);
        Font fontContent = FontFactory.getFont(FontFactory.HELVETICA);
        fontContent.setSize(16);

        Paragraph paragraph = new Paragraph("Confirmation for transfer: " + transfer.getId(), fontTitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(new LineSeparator());

        Paragraph sender = new Paragraph("Sender: " + transfer.getSenderId(), fontContent);
        Paragraph receiver = new Paragraph("Receiver: " + transfer.getReceiverId(), fontContent);

        document.add(sender);
        document.add(receiver);
        document.add(Chunk.NEWLINE);
        document.add(new LineSeparator());

        Paragraph amount = new Paragraph("Amount: " + transfer.getAmount(), fontContent);
        Paragraph date = new Paragraph("Transfer date: " + transfer.getTransferDate(), fontContent);

        document.add(amount);
        document.add(date);

        document.add(Chunk.NEWLINE);
        fontContent.setSize(12);
        Paragraph thanks = new Paragraph("Thank you for using our services", fontContent);
        thanks.setAlignment(Element.ALIGN_RIGHT);

        document.add(new LineSeparator());
        document.add(thanks);
        document.close();
    }
}
