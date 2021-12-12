package pl.edu.agh.communication.rest;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.edu.agh.service.reporting.ReportingRequestDTO;
import pl.edu.agh.service.reporting.ReportingService;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;

@Singleton
@Path("/reports")
public class ReportingController {

    private static final Logger logger = Logger.getLogger(ReportingController.class.toString());

    @Inject
    private ReportingService reportingService;


    @GET
    @Path("/pdf")
    @Produces("application/pdf")
    public Response getFile(
            @QueryParam("sectionNumber") Long sectionNumber,
            @QueryParam("from") String from,
            @QueryParam("to") String to) {

        ReportingRequestDTO reportingRequestDTO = new ReportingRequestDTO(from, to, sectionNumber);
        byte [] pdf = reportingService.getPdf(reportingRequestDTO);

        return Response.ok(pdf).header("Content-Disposition",
                "attachment; filename=report.pdf").build();



    }
}
