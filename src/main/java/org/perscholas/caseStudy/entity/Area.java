package org.perscholas.caseStudy.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @Slf4j
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Area implements Serializable {

    static final long serialVersionUID = 6382462292344345007L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long areaId;

    @NonNull
    String areaName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        return areaId.equals(area.areaId);
    }

    @Override
    public int hashCode() {
        return areaName.hashCode();
    }

}
