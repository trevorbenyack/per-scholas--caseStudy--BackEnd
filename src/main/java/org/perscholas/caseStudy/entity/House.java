package org.perscholas.caseStudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    String streetAddress02;

    @NonNull
    String city;

    @NonNull
    String state;

    @NonNull
    String zipCode;

    String notes;

    String pictureUrl = "http://localhost:4200/assets/img/house-placeholder-image.png";

    // TODO look into using https://github.com/FasterXML/jackson-datatype-hibernate
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    @OneToMany()
    List<Area> areas = new ArrayList<>();

    public void addArea(Area area) {
        areas.add(area);
    }

    public void removeArea(Area area) {
        areas.remove(area);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        return houseId.equals(house.houseId);
    }
}
