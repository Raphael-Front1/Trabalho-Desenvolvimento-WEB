package com.faculdade.biblioteca.controller;

import com.faculdade.biblioteca.model.Livro;
import com.faculdade.biblioteca.repository.LivroRepository;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RelatorioController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/relatorio/livros.pdf")
    public void gerarRelatorioLivros(HttpServletResponse response) throws Exception {
        List<Livro> livros = livroRepository.findAll();

        ClassPathResource jrxml = new ClassPathResource("relatorios/livros.jrxml");
        JasperReport jasperReport;
        try (InputStream is = jrxml.getInputStream()) {
            jasperReport = JasperCompileManager.compileReport(is);
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("DATA_GERACAO",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        parametros.put("TOTAL_LIVROS", livros.size());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(livros);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=relatorio-livros.pdf");

        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
