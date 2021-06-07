package com.yoonho.bowlog.post.domain;

import com.yoonho.bowlog.common.domain.CommonDate;
import com.yoonho.bowlog.like.domain.Like;
import com.yoonho.bowlog.reply.domain.Reply;
import com.yoonho.bowlog.user.domain.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Post extends CommonDate {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    @OneToMany(mappedBy = "post")
    @OrderBy("registeredAt asc")
    private List<Reply> reply = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private Set<Like> like = new HashSet<>();
}
