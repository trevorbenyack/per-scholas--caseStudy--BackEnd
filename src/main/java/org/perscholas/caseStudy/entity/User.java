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
public class User implements Serializable {

    static final long serialVersionUID = 6182462249344345097L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @NonNull
    String email;

    @NonNull
    String firstName;

    @NonNull
    String lastName;

    String phoneNumber;

    // TODO look into using https://github.com/FasterXML/jackson-datatype-hibernate
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    // TODO Constrain to unique users
    @ToString.Exclude
    @OneToMany(cascade=CascadeType.ALL)
    List<House> houses = new ArrayList<>();

    public void addHouse(House house) {
        houses.add(house);
    }

    public void removeHouse(House house) {
        houses.remove(house);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
