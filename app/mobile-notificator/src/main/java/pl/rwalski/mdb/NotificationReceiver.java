package pl.rwalski.mdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.edu.agh.dto.NotificationDTO;
import pl.edu.agh.repository.NotificationRepository;
import pl.edu.agh.repository.impl.NotificationRepositoryImpl;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;
import java.util.logging.Logger;

@MessageDriven(
        activationConfig =  {
                @ActivationConfigProperty( propertyName = "destinationType",
                        propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty( propertyName = "destination",
                        propertyValue ="jms/queue/notifications")
        }
)
public class NotificationReceiver implements MessageListener {

        @EJB(mappedName = "ejb:/ejbs/NotificationRepositoryImpl!pl.edu.agh.repository.NotificationRepository")
        private NotificationRepository notificationRepository;

        private static Logger logger = Logger.getLogger(NotificationReceiver.class.toString());

        private static ObjectMapper MAPPER;

        static {
                MAPPER = new ObjectMapper();
                MAPPER.registerModule(new JavaTimeModule());
                MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        }


        @Override
        public void onMessage(Message message) {
                logger.info("Received message");
                try {
                        String body = message.getBody(String.class);
                        NotificationDTO notificationDTO = MAPPER.readValue(body, NotificationDTO.class);
                        notificationRepository.save(notificationDTO.toEntity());
                        logger.info("Notification has been persisted");
                } catch (JMSException e) {
                        logger.severe("Couldn't extract body from message + " + e.getMessage());
                } catch (IOException e) {
                        logger.severe("Couldn't parse body from message + " + e.getMessage());
                }
        }

        private String parseToJson(NotificationDTO notificationDTO) {
                try {
                        return MAPPER.writeValueAsString(notificationDTO);
                } catch (JsonProcessingException e) {
                        logger.severe("Could not parse body");
                        throw new RuntimeException();
                }
        }
}
