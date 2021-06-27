package org.perscholas.caseStudy.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class House implements Serializable {

    static final long serialVersionUID = 6382462214934430007L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long houseId;

    @NonNull
    String houseName;

    @NonNull
    String streetAddress01;

    String streetAddress02 = "";

    @NonNull
    String city;

    @NonNull
    String state;

    @NonNull
    String zipCode;

    String notes = "";

    String pictureUrl = "http://localhost:4200/assets/img/house-placeholder-image.png";

    // "Changing this to a set solved the Multiple representations of the
    // same entity <entity> are being merged"
    // This happens when you overwrite the object with same value but
    // with different hashcodes
    @JsonManagedReference
    // TODO: look into using https://github.com/FasterXML/jackson-datatype-hibernate
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "house")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    Set<Area> areas = new HashSet<>();

    public void addArea(Area area) {
        this.areas.add(area);
        area.setHouse(this);
    }

    public void removeArea(Area area) {
        this.areas.remove(area);
        area.setHouse(null);
    }

    public void persistAreas(List<Area> areas) {
        areas.forEach(this::addArea);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        return houseId != null ? houseId.equals(house.houseId) : house.houseId == null;
    }

    @Override
    public int hashCode() {
        return houseId != null ? houseId.hashCode() : 0;
    }
}
