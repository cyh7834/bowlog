package com.yoonho.bowlog.reply.domain;

import com.yoonho.bowlog.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Reply {
    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
