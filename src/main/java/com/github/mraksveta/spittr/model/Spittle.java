package com.github.mraksveta.spittr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: mraksveta
 * Date: 03.11.21
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Spittle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spittle_id")
    private Long id;

    @Size(min = 1, max = 512)
    @NotNull
    private String message;

    @NotNull
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "spitter_id")
    @NotNull
    private Spitter spitter;

    @OneToMany(mappedBy = "spittle")
    private List<SpittleImage> images;

    public Spittle() {
        this.images = new ArrayList<>();
    }

    public Spittle(Long id, String message, LocalDateTime timestamp, Spitter spitter) {
        this(id, message, timestamp, spitter, new ArrayList<>());
    }

    public Spittle(Long id, String message, LocalDateTime timestamp, Spitter spitter, List<SpittleImage> images) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Spitter getSpitter() {
        return spitter;
    }

    public void setSpitter(Spitter spitter) {
        this.spitter = spitter;
    }

    public List<SpittleImage> getImages() {
        return images;
    }

    public void setImages(List<SpittleImage> images) {
        this.images = images;
    }

    public void addImage(SpittleImage image) {
        this.images.add(image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spittle)) return false;

        Spittle spittle = (Spittle) o;

        return Objects.equals(this.id, spittle.id)
                && Objects.equals(this.message, spittle.message)
                && Objects.equals(this.timestamp, spittle.timestamp)
                && Objects.equals(this.images, spittle.images);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        return result;
    }
}
