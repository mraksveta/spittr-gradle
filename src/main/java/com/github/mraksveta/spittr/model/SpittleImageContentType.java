package com.github.mraksveta.spittr.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class SpittleImageContentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spittle_image_content_type_id")
    private Integer id;

    private String contentType;

    public SpittleImageContentType() {
    }

    public SpittleImageContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpittleImageContentType that = (SpittleImageContentType) o;
        return Objects.equals(id, that.id) && Objects.equals(contentType, that.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType);
    }
}
