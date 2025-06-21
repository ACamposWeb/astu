package com.g9.astu.service;

import com.g9.astu.model.Sesion;
import com.g9.astu.repository.SesionRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SesionExportService {

    private final SesionRepository repo;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SesionExportService(SesionRepository repo) {
        this.repo = repo;
    }

    public ByteArrayInputStream generarExcel(Long estudianteId) throws IOException {

        List<Sesion> sesiones = (estudianteId == null)
                ? repo.findAll()
                : repo.findByEstudianteId(estudianteId);

        try (Workbook wb   = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = wb.createSheet("Sesiones");

            String[] cols = {"ID", "Estudiante", "Tutor", "Fecha", "Hora","Aistencia","Observaciones"};
            Row header = sheet.createRow(0);
            CellStyle style = wb.createCellStyle();
            Font bold = wb.createFont(); bold.setBold(true); style.setFont(bold);

            for (int i = 0; i < cols.length; i++) {
                Cell c = header.createCell(i);
                c.setCellValue(cols[i]);
                c.setCellStyle(style);
            }

            int fila = 1;
            for (Sesion s : sesiones) {
                Row row = sheet.createRow(fila++);
                row.createCell(0).setCellValue(s.getId());
                row.createCell(1).setCellValue(s.getEstudiante().getName());
                row.createCell(2).setCellValue(s.getTutor().getName());
                row.createCell(3).setCellValue(s.getFecha().format(FMT));
                row.createCell(4).setCellValue(s.getHora().toString());
                row.createCell(5).setCellValue(s.getAsistencia()== null ? "": s.getAsistencia()); // si tienes ese campo
                row.createCell(6).setCellValue(s.getObservaciones()== null ? "" : s.getObservaciones());

            }

            wb.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
