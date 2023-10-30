package com.manahotel.be.service;

import com.manahotel.be.model.entity.OrderDetail;
import com.manahotel.be.repository.OrderDetailRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoicePrinterService implements Printable {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    PDDocument invc;
    int n;
    Integer total = 0;
    Integer price;
    String CustName;
    String CustPh;
    List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
    String InvoiceTitle = new String("Hóa đơn đặt hàng");
    String SubTitle = new String("Invoice");

    public InvoicePrinterService() {
        //Create Document
        invc = new PDDocument();
        //Create Blank Page
        PDPage newpage = new PDPage();
        //Add the blank page
        invc.addPage(newpage);
    }

    public void WriteInvoice(String orderId) {

        orderDetailList = orderDetailRepository.findByOrder_OrderId(orderId);

        PDPage mypage = invc.getPage(0);
        try {
            PDPageContentStream cs = new PDPageContentStream(invc, mypage);

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 20);
            cs.newLineAtOffset(140, 750);
            cs.showText(InvoiceTitle);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 18);
            cs.newLineAtOffset(270, 690);
            cs.showText(SubTitle);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(60, 610);
            cs.showText("Ngày xuất hóa đơn: ");
            cs.newLine();
            cs.showText("Tên khách hàng: ");
            cs.newLine();
            cs.showText("Số điện thoại: ");
            cs.newLine();
            cs.showText("Mã đặt phòng: ");
            cs.newLine();
            cs.showText("----------------------------------------------------------------");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(170, 610);
//            cs.showText(CustName);
            cs.newLine();
//            cs.showText(CustPh);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(80, 540);
            cs.showText("Product Name");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(200, 540);
            cs.showText("Unit Price");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(310, 540);
            cs.showText("Quantity");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(410, 540);
            cs.showText("Price");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(80, 520);
            for (int i = 0; i < n; i++) {
//                cs.showText(ProductName.get(i));
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(200, 520);
            for (int i = 0; i < n; i++) {
//                cs.showText(ProductPrice.get(i).toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(310, 520);
            for (int i = 0; i < n; i++) {
//                cs.showText(ProductQty.get(i).toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(410, 520);
            for (int i = 0; i < n; i++) {
//                price = ProductPrice.get(i)*ProductQty.get(i);
                cs.showText(price.toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(310, (500 - (20 * n)));
            cs.showText("Total: ");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            //Calculating where total is to be written using number of products
            cs.newLineAtOffset(410, (500 - (20 * n)));
            cs.showText(total.toString());
            cs.endText();

            //Close the content stream
            String FilePath = "D:\\Java program\\SEP\\hóa đơn.pdf";
            cs.close();
            //Save the PDF
            invc.save(FilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }
        PDDocument MyPDF = new PDDocument();
        PDPage newpage = new PDPage();
        PDPage mypage = MyPDF.getPage(0);

        MyPDF.addPage(newpage);

//        Graphics2D g2d = (Graphics2D) graphics;
//        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//
//        // Vẽ nội dung hóa đơn
//        g2d.drawString("Hóa đơn", 100, 100);
//        g2d.drawString("Sản phẩm: Máy tính", 100, 120);
//        g2d.drawString("Số lượng: 2", 100, 140);
//        g2d.drawString("Tổng tiền: $200.00", 100, 160);

        return Printable.PAGE_EXISTS;
    }

    public void printInvoice() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }


}
