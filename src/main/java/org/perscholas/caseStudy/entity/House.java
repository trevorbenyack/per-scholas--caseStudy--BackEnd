package org.perscholas.caseStudy.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class House {

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

    String pictureUrl;

    @ToString.Exclude
    @OneToMany(mappedBy = "house")
    List<Area> areas;

    @ToString.Exclude
    @ManyToMany(mappedBy = "houses")
    List<User> users = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        return houseId.equals(house.houseId);
    }

    @Override
    public int hashCode() {
        return houseId.hashCode();
    }
}
