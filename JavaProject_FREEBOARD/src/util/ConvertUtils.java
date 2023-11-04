package util;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
 
public class ConvertUtils<T> {
    private ConvertUtils() {}
    
    
    // Map -> VO
    public static <T> T convertToVo(Map<String, Object> map, Class<T> type) {
        try {
            if (Objects.isNull(type)) {
                throw new NullPointerException("Class cannot be null");
            }
            if (Objects.isNull(map) || map.isEmpty()) {
                throw new IllegalArgumentException("map is null or empty");
            }
            T instance = type.getConstructor().newInstance();
            Field[] fields = type.getDeclaredFields();
 
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                for (Field field : fields) {
                	String key = entry.getKey();
                	String field_name = field.getName().toUpperCase();
                    if (entry.getKey().equals(field.getName())) {
                        field.setAccessible(true);
 
                        Object value = Objects.isNull(entry.getValue()) && field.getType().isPrimitive()
                                ? getDefaultValue(field.getType())
                                : map.get(field.getName());
						if (field.getType().getName().contains("int")) {
							BigDecimal bd = (BigDecimal) map.get(field_name);
							value = bd.intValue();
						}
                        field.set(instance, value);
                        break;
                    }
                    else if (key.equals(field_name)) {
                        field.setAccessible(true);
 
                        Object value = Objects.isNull(entry.getValue()) && field.getType().isPrimitive()
                                ? getDefaultValue(field.getType())
                                : map.get(field_name);
                        if(field.getType().getName().contains("int")) {
                        	BigDecimal bd = (BigDecimal)map.get(field_name);
                        	value = bd.intValue();
                        }
                        field.set(instance, value);
                        break;
                    }
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> List<T> convertToList(List<Map<String, Object>> list, Class<T> type) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> convertList = new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            convertList.add(ConvertUtils.convertToVo(map, type));
        }
        return convertList;
    }
 
    private static Object getDefaultValue(Class<?> type) {
        switch (type.getName()) {
            case "byte": case "short": case "int": return 0;
            case "long"    : return 0L;
            case "float"   : return 0.0f;
            case "double"  : return 0.0d;
            case "char"    : return '\u0000';
            case "boolean" : return false;
        }
        return null;
    }
}