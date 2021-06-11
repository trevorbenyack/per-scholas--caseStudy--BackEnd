package org.perscholas.caseStudy.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Area {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long areaId;

    @NonNull
    String areaName;

    @ToString.Exclude
    @ManyToOne()
    House house;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        return areaId.equals(area.areaId);
    }

    @Override
    public int hashCode() {
        return areaId.hashCode();
    }
}
