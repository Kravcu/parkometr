package pl.rwalski.managed.beans.rest;

import pl.rwalski.dto.TicketCreationDTO;
import pl.rwalski.dto.TicketDetailsDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@SessionScoped
@ManagedBean
public class RestClient {
    public TicketDetailsDTO buyTicketAndReturnDetails(TicketCreationDTO dto) {
        Client client = ClientBuilder.newClient();
        return client.target("http://localhost:8080/main-system/rest/tickets")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(dto), TicketDetailsDTO.class);
    }
}
