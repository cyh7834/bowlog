package com.yoonho.piclog.like.domain;

import com.yoonho.piclog.common.domain.CommonDate;
import com.yoonho.piclog.post.domain.Post;
import com.yoonho.piclog.reply.domain.Reply;
import com.yoonho.piclog.user.domain.Account;
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
