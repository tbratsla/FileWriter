package com.seeton.filewriter.service;



import com.seeton.filewriter.entity.PersonnelDataEntity;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;

public class ReportExcelStreamWriter {

    private final SXSSFWorkbook wb;
    private final SXSSFSheet sheet;

    public ReportExcelStreamWriter() {
        this.wb = new SXSSFWorkbook();
        this.sheet = wb.createSheet();
        createTitle();
    }

    public void createRow(int index, PersonnelDataEntity personnelDataEntity) {
        SXSSFRow row = sheet.createRow(index);
        setCellValue(row.createCell(0), personnelDataEntity.getName());
        setCellValue(row.createCell(1), personnelDataEntity.getAge());
        setCellValue(row.createCell(2), personnelDataEntity.getEmail());
        setCellValue(row.createCell(3), personnelDataEntity.getPhone());
        setCellValue(row.createCell(4), personnelDataEntity.getRegDate());
    }

    public void writeWorkbook() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(Instant.now().getEpochSecond() + ".xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    private void createTitle() {
        SXSSFRow rowTitle = sheet.createRow(0);
        setCellValue(rowTitle.createCell(0), "Name");
        setCellValue(rowTitle.createCell(1), "Age");
        setCellValue(rowTitle.createCell(2), "Email");
        setCellValue(rowTitle.createCell(3), "Phone");
        setCellValue(rowTitle.createCell(4), "Register date");
    }

    private void setCellValue(SXSSFCell cell, long value) {
        cell.setCellValue(value);
    }

    private void setCellValue(SXSSFCell cell, String value) {
        cell.setCellValue(value);
    }
    private void setCellValue(SXSSFCell cell, Instant value) {
        cell.setCellValue(value.toString());
    }
}