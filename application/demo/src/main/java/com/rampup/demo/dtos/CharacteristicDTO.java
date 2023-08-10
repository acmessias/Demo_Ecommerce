package com.rampup.demo.dtos;

import java.io.Serializable;
import java.util.stream.Collector.Characteristics;

import com.rampup.demo.entities.Characteristic;
import com.rampup.demo.entities.TimePeriod;
import com.rampup.demo.entities.enums.CharacteristicType;

public class CharacteristicDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private TimePeriod validFor;
    private CharacteristicType type;
    private String valueType;

    public CharacteristicDTO() {
    }

    public CharacteristicDTO(Characteristic obj) {
        super();
        this.id = obj.getId();
        this.name = obj.getName();
        this.validFor = obj.getValidFor();
        this.type = obj.getType();
        this.valueType = obj.getValueType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public CharacteristicType getType() {
        return type;
    }

    public void setType(CharacteristicType type) {
        this.type = type;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

}
