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
public class User {

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

    @ToString.Exclude
    @ManyToMany
    @JoinTable (
        name="user_house",
        inverseJoinColumns=@JoinColumn(name = "house_id"),
        joinColumns=@JoinColumn(name = "user_id")
    )
    List<House> houses = new ArrayList<>();

    public void addHouse(House house) {
        if (houses.contains(house)) {
            log.info("House " + house.getHouseId() + " is already associated with user " + userId + ".");
        } else {
            this.houses.add(house);
            house.getUsers().add(this);
            log.info("House " + house.getHouseId() + " added to user " + userId + ".");
        }
    }

    public void removeHouse(House house) {
        if (!houses.contains(house)) {
            log.info("House " + house.getHouseId() + " is not associated with user " + userId + ".");
        } else {
            this.houses.remove(house);
            house.getUsers().remove(this);
            log.info("House " + house.getHouseId() + " removed from user " + userId + ".");
        }
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
