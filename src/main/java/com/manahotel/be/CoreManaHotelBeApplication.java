package com.manahotel.be;


import com.manahotel.be.service.InvoicePrinterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class CoreManaHotelBeApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(CoreManaHotelBeApplication.class, args);

//        InvoicePrinterService invoicePrinterService = new InvoicePrinterService();
//
//        invoicePrinterService.WriteInvoice("7");
//        System.out.println("Invoice Generated!");
//        invoicePrinter.printInvoice();

    }

}
