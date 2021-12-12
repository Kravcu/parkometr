package pl.rwalski.configuration;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String dateInput) throws Exception {
        return LocalDateTime.parse(dateInput, DateTimeFormatter.ISO_DATE_TIME);
    }
    @Override
    public String marshal(LocalDateTime localDate) throws Exception {
        return DateTimeFormatter.ISO_DATE_TIME.format(localDate);
    }
}
