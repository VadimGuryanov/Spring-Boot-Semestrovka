package ru.itis.springboothomework.conventers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.util.StringUtils;
import ru.itis.springboothomework.models.Summary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.DataInput;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonConverter implements GenericConverter {

    private final Class<?> clazz;
//    private final Class<?> list;

    @PersistenceContext
    private EntityManager em;

    private ObjectMapper objectMapper;

    public JsonConverter(
//            Class<Object> list,
            Class<?> clazz
    ) {
        super();
        this.clazz = clazz;
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> types = new HashSet<>();
        types.add(new ConvertiblePair(String.class, this.clazz));
        types.add(new ConvertiblePair(this.clazz, String.class));
        return types;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (String.class.equals(sourceType.getType())) {
            try {
                return objectMapper.readValue((String) source, Summary.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Cannot convert " + source + "into" + targetType.getType());
            }
        } else if (this.clazz.equals(sourceType.getType())) {
            try {
                return objectMapper.writeValueAsString((Summary) source);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Cannot convert " + source + "into" + targetType.getType());
            }
        }
        throw new IllegalArgumentException("Cannot convert " + source);
    }
}
