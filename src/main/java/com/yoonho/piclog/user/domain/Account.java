package com.yoonho.piclog.user.domain;

import com.yoonho.piclog.common.domain.CommonDate;
import com.yoonho.piclog.post.domain.Post;
import com.yoonho.piclog.zone.domain.Zone;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends CommonDate {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
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
}
