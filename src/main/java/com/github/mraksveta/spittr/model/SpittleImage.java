package com.github.mraksveta.spittr.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class SpittleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spittle_image_id")
    private Long id;

    private String fileName;

    @ManyToOne
    @JoinColumn(name="spittle_id")
    private Spittle spittle;

    @ManyToOne
    @JoinColumn(name = "content_type")
    private SpittleImageContentType contentType;

    public SpittleImage() {
    }

    public SpittleImage(String fileName, SpittleImageContentType contentType) {
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public SpittleImageContentType getContentType() {
        return contentType;
    }

    public void setContentType(SpittleImageContentType contentType) {
        this.contentType = contentType;
    }

    public Spittle getSpittle() {
        return spittle;
    }

    public void setSpittle(Spittle spittle) {
        this.spittle = spittle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpittleImage that = (SpittleImage) o;
        return Objects.equals(id, that.id) && Objects.equals(fileName, that.fileName) && Objects.equals(spittle, that.spittle) && Objects.equals(contentType, that.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, spittle, contentType);
    }
}
