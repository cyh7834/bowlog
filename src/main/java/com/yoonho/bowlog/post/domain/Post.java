package com.yoonho.bowlog.post.domain;

import com.yoonho.bowlog.like.domain.Like;
import com.yoonho.bowlog.reply.domain.Reply;
import com.yoonho.bowlog.user.domain.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Post {
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
    private Set<Reply> reply = new HashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<Like> like = new HashSet<>();
}
