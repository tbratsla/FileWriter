package com.seeton.filewriter.entity;



import lombok.Data;
import org.hibernate.annotations.CacheModeType;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

import static com.seeton.filewriter.entity.PersonnelDataEntity.PERSONNEL_REF_QUERY_NAME;

@Data
@Entity
@NamedNativeQuery(
        name = PERSONNEL_REF_QUERY_NAME,
        query = "{ ? = call personnel_ref(?, ?) }",
        callable = true,
        cacheMode = CacheModeType.IGNORE,
        resultClass = PersonnelDataEntity.class
)
public class PersonnelDataEntity  implements Serializable {
    public static final String PERSONNEL_REF_QUERY_NAME = "PersonnelDataEntity.callPersonnelRef";

//    private static final long serialVersionUID = -6780765638993961105L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "reg_date", nullable = false)
    private Instant regDate;

}
