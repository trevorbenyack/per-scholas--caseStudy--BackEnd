package org.perscholas.caseStudy.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Item {

    @Id
    @GeneratedValue
    Long itemId;

    @NonNull
    String alias;

    String manufacturer;

    String purchaseLocation;

    String dateOfPurchase;

    String purchasePrice;

    String itemImageUrl;

    String receiptImageUrl;

    String serialNumber;

    String modelYear;

    String color;

    String notes;

    @ManyToOne
    Area area;




}
