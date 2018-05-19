package com.tedu.druid.pojo;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name="student_book")
public class StudentBook {
	
	@Transient  //忽略该字段
	private List<StudentSection>  sections;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer bookId;

    private Integer id;

    private Integer time;

    private String teacher;

    private Integer price;

    private String url;

    private Integer studentNum;

    private String bookImg;

    private String direction;

    private Integer knowledgePointNum;

    private String name;

    private String level;

    private Integer status;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getKnowledgePointNum() {
        return knowledgePointNum;
    }

    public void setKnowledgePointNum(Integer knowledgePointNum) {
        this.knowledgePointNum = knowledgePointNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public List<StudentSection> getSections() {
		return sections;
	}

	public void setSections(List<StudentSection> sections) {
		this.sections = sections;
	}
    
    
}