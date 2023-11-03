package com.manahotel.be.service;

import com.manahotel.be.model.dto.BillDTO;
import com.manahotel.be.model.entity.Order;
import com.manahotel.be.repository.OrderRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoicePrinterService  {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BillService billService;
    PDDocument invc;
    int n;
    Integer price;

    List<BillDTO> billList = new ArrayList<BillDTO>();
    Order order = new Order();
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

    public ResponseEntity<ByteArrayResource>  WriteInvoice(String orderId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy ")
                .withZone(ZoneId.systemDefault());

        billList = billService.getBillList(orderId);
        order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Order not found"));
        n = billList.size();
        PDPage mypage = invc.getPage(0);
        try {
            PDPageContentStream cs = new PDPageContentStream(invc, mypage);
            PDType0Font font = PDType0Font.load(invc, new File("arial-unicode-ms.ttf"));

            cs.beginText();
            cs.setFont(font, 20);
            cs.newLineAtOffset(240, 750);
            cs.showText(InvoiceTitle);
            cs.endText();

            cs.beginText();
            cs.setFont(font, 18);
            cs.newLineAtOffset(270, 690);
//            cs.showText(SubTitle);
            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(60, 610);
            cs.showText("Ngày xuất hóa đơn: ");
            cs.newLine();
//            cs.showText("Tên khách hàng: ");
//            cs.newLine();
//            cs.showText("Số điện thoại: ");
//            cs.newLine();
            cs.showText("Mã đặt phòng: ");
            cs.newLine();
            cs.showText("--------------------------------------------------------------------------------------------------------------");
            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(210, 610);
            cs.showText(formatter.format(order.getCreatedDate()));
            cs.newLine();
            cs.showText(order.getReservationDetail().getReservationDetailId().toString());
            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            cs.newLineAtOffset(80, 540);
            cs.showText("Tên sản phẩm");
            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            cs.newLineAtOffset(200, 540);
            cs.showText("Giá");
            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            cs.newLineAtOffset(310, 540);
            cs.showText("Số lượng");
            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            cs.newLineAtOffset(410, 540);
            cs.showText("Thành tiền");
            cs.endText();

            cs.beginText();
            cs.setFont(font, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(80, 520);

            for (BillDTO billDTO : billList) {
                cs.showText(billDTO.getGoodsName());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(font, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(200, 520);
            for (BillDTO billDTO : billList) {
                cs.showText(billDTO.getUnitPrice().toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(font, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(310, 520);
            for (BillDTO billDTO : billList) {
                cs.showText(billDTO.getQuantity().toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(font, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(410, 520);
            for (BillDTO billDTO : billList) {
                cs.showText(billDTO.getPrice().toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            cs.newLineAtOffset(310, (500 - (20 * n)));
            cs.showText("Tổng: ");
            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            //Calculating where total is to be written using number of products
            cs.newLineAtOffset(410, (500 - (20 * n)));
            cs.showText(order.getTotalPay().toString());
            cs.endText();

            //Close the content stream
            String FilePath = "D:\\Java program\\SEP\\hóa đơn.pdf";
            cs.close();
            //Save the PDF
            invc.save(FilePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            invc.save(byteArrayOutputStream);
            invc.close();

            // Create a ByteArrayResource from the byte array
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=generated.pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfBytes.length)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);

//            printInvoice();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


//    @Override
//    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
//        if (pageIndex > 0) {
//            return Printable.NO_SUCH_PAGE;
//        }
////        PDDocument MyPDF = new PDDocument();
////        PDPage newpage = new PDPage();
////        PDPage mypage = MyPDF.getPage(0);
////
////        MyPDF.addPage(newpage);
//
////        Graphics2D g2d = (Graphics2D) graphics;
////        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
////
////        // Vẽ nội dung hóa đơn
////        g2d.drawString("Hóa đơn", 100, 100);
////        g2d.drawString("Sản phẩm: Máy tính", 100, 120);
////        g2d.drawString("Số lượng: 2", 100, 140);
////        g2d.drawString("Tổng tiền: $200.00", 100, 160);
//
//        return Printable.PAGE_EXISTS;
//    }

    public void printInvoice() throws IOException {
        PDDocument document = PDDocument.load(new File("D:\\Java program\\SEP\\hóa đơn.pdf"));

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));

//        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
//        }
    }


}
