package com.eeminder.skrambler.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="media")
public class Media {

    private Integer id;
    private String file;
    private String type;
    private String uuid;
    private int width;
    private int height;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "file", nullable = true, length = 255)
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 128)
    public String getType() {
        return type;
    }

    @Basic
    @Column(name="width")
    public int getWidth() {
        return width;
    }

    @Basic
    @Column(name="height")
    public int getHeight() {
        return height;
    }

    @Basic
    @Column(name="uuid",nullable=false,length=128)
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Media media = (Media) o;

        if (id != media.id) return false;
        if (file != null ? !file.equals(media.file) : media.file != null) return false;
        if (type != null ? !type.equals(media.type) : media.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (file != null ? file.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
    public Media() {
        this.uuid = UUID.randomUUID().toString();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Transient
    public String getThumbnail() {
        return String.format("/%s/thumb.jpg",this.getUuid());
    }
    @Transient
    public String getUrl() {
        return String.format("/%s/%s",this.getUuid(),this.getFile());
    }
}
