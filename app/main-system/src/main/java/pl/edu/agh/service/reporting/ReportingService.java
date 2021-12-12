package pl.edu.agh.service.reporting;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.edu.agh.model.Notification;
import pl.edu.agh.repository.NotificationRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class ReportingService implements Serializable {

    private static final Logger logger = Logger.getLogger(ReportingService.class.getName());

    @EJB(mappedName = "ejb:/main-system/NotificationRepositoryImpl!pl.edu.agh.repository.NotificationRepository")
    private NotificationRepository notificationRepository;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public byte[] getPdf(ReportingRequestDTO reportingRequestDTO) {


        Map<Long, List<Notification>> notificationsMultimap = notificationRepository.get(
                reportingRequestDTO.getFrom(),
                reportingRequestDTO.getTo(),
                reportingRequestDTO.getSectionNumber()
        )
                .stream().collect(
                        Collectors.groupingBy(Notification::getPlaceNumber, Collectors.toList()));

        logger.info("" + notificationsMultimap.size());
        logger.info(reportingRequestDTO.toString());

        notificationsMultimap.forEach((k, v) -> {
            logger.info("----");
            logger.info(k.toString());
            v.forEach(n -> {
                logger.info("" + n.getPlaceNumber());
            });
        });

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
        Font placeFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
        Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font tableFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);


        try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();

            document.add(new Paragraph(
                    String.format("Report for: section %s",
                            reportingRequestDTO.getSectionNumber()
                    ), titleFont
            ));
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph(
                    String.format("Date range: %s - %s",
                            reportingRequestDTO.getFrom().format(formatter),
                            reportingRequestDTO.getTo().format(formatter)
                    ), placeFont
            ));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);


            notificationsMultimap.forEach((k, v) -> {
                try {
                    document.add(new Paragraph(
                            String.format("Place number: %s", k), placeFont
                    ));


                    document.add(Chunk.NEWLINE);


                    PdfPTable table = new PdfPTable(2);

                    PdfPCell dateHeader = new PdfPCell(new Paragraph("Date", tableHeaderFont));
                    PdfPCell contentHeader = new PdfPCell(new Paragraph("Event", tableHeaderFont));

                    table.addCell(dateHeader);
                    table.addCell(contentHeader);
                    v.forEach(n -> {
                        logger.info(n.getEvent().getMessage());
                        PdfPCell dateCell = new PdfPCell(new Paragraph(n.getGenerated().format(formatter), tableFont));
                        PdfPCell contentCell = new PdfPCell(new Paragraph(n.getEvent().getMessage(), tableFont));

                        table.addCell(dateCell);
                        table.addCell(contentCell);
                    });

                    document.add(table);

                    document.add(Chunk.NEWLINE);
                    document.add(Chunk.NEWLINE);

                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            });
            document.close();
            return baos.toByteArray();
        } catch (DocumentException e) {
            throw new RuntimeException("something is no yes with pdf");
        }
    }
}
