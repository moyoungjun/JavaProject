package com.javaproject.dto.converter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class GrantedAuthoritySetAttributeConverter implements AttributeConverter<Set<GrantedAuthority>, String> {
    private String comma = ",";
    //엔티티를 DB에 집어넣을때
    @Override
    public String convertToDatabaseColumn(Set<GrantedAuthority> attribute) {
        return String.join(comma,attribute.toString());
    }
    //DB에서 엔티티 필드로 변환할 때

    @Override
    public Set<GrantedAuthority> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(comma)).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
