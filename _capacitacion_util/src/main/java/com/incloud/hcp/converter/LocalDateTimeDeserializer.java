package com.incloud.hcp.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {


    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static DateTimeFormatter formatter02 =
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm:ss");
    private static DateTimeFormatter formatter03 =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
    private static DateTimeFormatter formatter04 =
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm:ss.SSS");

    @Override
    public LocalDateTime deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException {
        try {
            if (!Optional.ofNullable(arg0).isPresent()) {
                return null;
            }
            if (!Optional.ofNullable(arg0.getText()).isPresent()) {
                return null;
            }
            if (!Optional.ofNullable(arg0.getCurrentValue()).isPresent()) {
                return null;
            }

            String valor = arg0.getValueAsString("_currentValue");
            if (StringUtils.isBlank(valor)) {
                return null;
            }
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(valor, formatter);
                return localDateTime;
            }
            catch (Exception e) {
                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(valor, formatter02);
                    return localDateTime;
                }
                catch (Exception ex02) {
                    try {
                        LocalDateTime localDateTime = LocalDateTime.parse(valor, formatter03);
                        return localDateTime;
                    }
                    catch (Exception ex03) {
                        LocalDateTime localDateTime = LocalDateTime.parse(valor, formatter04);
                        return localDateTime;
                    }
                }
            }

        }
        catch(Exception e) {
            return null;
        }

    }
}