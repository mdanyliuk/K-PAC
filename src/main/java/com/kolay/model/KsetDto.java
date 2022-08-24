package com.kolay.model;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class KsetDto {

    @NotEmpty
    private String title;
    private List<KpacDto> kpacs;

    public KsetDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<KpacDto> getKpacs() {
        return kpacs;
    }

    public void setKpacs(List<KpacDto> kpacs) {
        this.kpacs = kpacs;
    }
}
