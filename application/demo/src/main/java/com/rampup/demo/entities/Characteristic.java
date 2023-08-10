package com.rampup.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rampup.demo.entities.enums.CharacteristicType;

@Entity
@Table(name = "tb_characteristic")
public class Characteristic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "valid_for_id")
    private TimePeriod validFor;

    private Integer type;
    private String valueType;

    @ManyToMany(mappedBy = "characteristics")
    // Se der erro colocar JsonIgnore
    private Set<ProductOffering> productOfferings = new HashSet<>();

    private Boolean isActive = true;

    public Characteristic() {

    }

    public Characteristic(Long id, String name, TimePeriod validFor, CharacteristicType type, String valueType,
            Boolean isActive) {
        super();
        this.id = id;
        this.name = name;
        this.validFor = validFor;
        validFor.addCharacteristic(this);
        setType(type);
        this.valueType = valueType;
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
        return CharacteristicType.valueOf(type);
    }

    public void setType(CharacteristicType type) {
        if (type != null) {
            this.type = type.getCode();
        }
    }

    public Set<ProductOffering> getProductOfferings() {
        return productOfferings;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, validFor, valueType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Characteristic other = (Characteristic) obj;
        return Objects.equals(name, other.name) && type == other.type && Objects.equals(validFor, other.validFor)
                && Objects.equals(valueType, other.valueType);
    }

}
