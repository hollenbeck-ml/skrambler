package com.eeminder.skrambler.model;

import javax.persistence.*;

@Entity
@Table(name="project")
public class Project {
    private int id;
    private String name;
    private Integer mediaId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "media_id", nullable = true)
    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != project.id) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (mediaId != null ? !mediaId.equals(project.mediaId) : project.mediaId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mediaId != null ? mediaId.hashCode() : 0);
        return result;
    }
}
