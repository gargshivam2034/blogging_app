package com.BlogApp.entites;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.BlogApp.Payloads.CommentDto;

import jakarta.persistence.*;


@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy="post",cascade = CascadeType.ALL)
    private Set<Comment>setComments=new HashSet<Comment>();
    
    
    public Set<Comment> getSetComments() {
		return setComments;
	}

	public void setSetComments(Set<Comment> setComments) {
		this.setComments = setComments;
	}

	public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
