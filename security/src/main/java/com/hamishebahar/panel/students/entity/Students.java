package com.hamishebahar.panel.students.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.panel.media.entity.Medias;
import com.hamishebahar.panel.periods.entity.Periods;
import com.hamishebahar.security.users.entity.Users;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentCode; //کد تحصیلی
    private String first_name;
    private String last_name;
    private String nationalCode;
    private String phoneNumber;
    private String emergencyPhoneNumber;

    private String studentAge;
    //Parents
    @ManyToMany
    private List<Users> studentParents;

    //period
    // دوره تحصیلی - دبستان - پیش دبستان
    @ManyToMany
    private List<Periods> studentPeriods;

    //Media
    //عکس دانش آموز
    //کارت ملی
    //شناسنامه
    @ManyToMany
    private List<Medias> documentFiles;

    //todo توانمندی و مهارت ها
    //todo هزینه ها و واریزی ها به ازای هر فرزند باید توی والدین هم اضافه بشه البته باید توی فاکتور کیف پول گزاشته بشه
    //Todo امتیاز و جوایز

    private Boolean is_active;
    private Boolean is_deleted;

    @Column(
            name = "created_time",
            updatable = false
    )
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(
            name = "updated_time",
            updatable = true
    )
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
