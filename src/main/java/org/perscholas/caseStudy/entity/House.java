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

    String pictureUrl;

    // TODO look into using https://github.com/FasterXML/jackson-datatype-hibernate
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    // TODO Constrain to unique users
    @ToString.Exclude
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_house", joinColumns=@JoinColumn(name="house_id"), inverseJoinColumns=@JoinColumn(name="user_id"))
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

    public void addUser(User user) {
        users.add(user);
    }

    public void removeHouse(User user) {
        users.remove(user);
    }
}
