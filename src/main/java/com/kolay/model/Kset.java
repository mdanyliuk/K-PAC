package com.kolay.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Kset {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 250)
    @NotEmpty
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pac_set",
            joinColumns = @JoinColumn(name = "kset_id"),
            inverseJoinColumns = @JoinColumn(name = "kpac_id")
    )
    private Set<Kpac> kpacs;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Set<Kpac> getKpacs() {
        return kpacs;
    }

    public void setKpacs(Set<Kpac> kpacs) {
        this.kpacs = kpacs;
    }
}
