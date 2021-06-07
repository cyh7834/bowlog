package com.yoonho.bowlog.like.domain;

import com.yoonho.bowlog.common.domain.CommonDate;
import com.yoonho.bowlog.post.domain.Post;
import com.yoonho.bowlog.reply.domain.Reply;
import com.yoonho.bowlog.user.domain.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "POST_LIKE")
public class Like extends CommonDate {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private Reply reply;
}
