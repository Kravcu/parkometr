package pl.edu.agh.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.edu.agh.dto.NotificationDTO;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public class NotificationPublisher {


    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "java:jboss/exported/jms/queue/notifications")
    private Queue queue;

    private JMSContext context;

    private static final Logger logger = Logger.getLogger(NotificationPublisher.class.toString());

    private static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @PostConstruct
    private void init() {
        context = cf.createContext("guest", "guest");

    }



    public void publish(NotificationDTO notificationDTO) {
        context.createProducer().send(queue, parseToJson(notificationDTO));
        logger.info("notification sent");
    }

    public void publish(List<NotificationDTO> notifications) {
        JMSProducer producer = context.createProducer();
        notifications.forEach(n -> context.createProducer().send(queue, parseToJson(n)));
        logger.info("notification series with size " + notifications.size() + " sent");
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
