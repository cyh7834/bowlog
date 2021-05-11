package com.yoonho.bowlog.user.domain;

import com.yoonho.bowlog.zone.domain.Zone;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private String bio;

    private String gender;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    @ManyToMany
    Set<Zone> zone = new HashSet<>();

    private LocalDateTime joinedAt;
}
