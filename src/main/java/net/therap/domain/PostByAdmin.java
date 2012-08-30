package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/19/12
 * Time: 4:51 PM
 */
@Entity
@Table(name = "POST_BY_ADMIN")
public class PostByAdmin implements Serializable {

    private long postId;
    private Admin admin;
    private Date postTime;
    private String postTitle;
    private String postContent;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @ManyToOne
    @JoinColumn(name = "ADMIN_ID")
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Column(name = "POST_TIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    @Column(name = "POST_TITLE")
    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    @Column(name = "POST_CONTENT")
    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
