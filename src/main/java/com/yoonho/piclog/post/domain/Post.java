package com.yoonho.piclog.post.domain;

import com.yoonho.piclog.common.domain.CommonDate;
import com.yoonho.piclog.user.domain.Account;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

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

    @Formula("(select count(1) from reply r where r.post_id = id)")
    private int numberOfReply;

    @Formula("(select count(1) from like l where l.post_id = id)")
    private int numberOfLike;
}
