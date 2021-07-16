package com.yoonho.piclog.reply.domain;

import com.yoonho.piclog.common.domain.CommonDate;
import com.yoonho.piclog.like.domain.Like;
import com.yoonho.piclog.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Reply extends CommonDate {
    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "reply")
    private Set<Like> like = new HashSet<>();

}
