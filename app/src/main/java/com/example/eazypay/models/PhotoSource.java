package com.example.eazypay.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PhotoSource implements Serializable {
    private String original;

    @SerializedName("large2x")
    private String large2X;

    private String large;
    private String medium;
    private String small;
    private String portrait;
    private String landscape;
    private String tiny;

    // Getters & Setters
    public String getOriginal() { return original; }
    public void setOriginal(String original) { this.original = original; }

    public String getLarge2X() { return large2X; }
    public void setLarge2X(String large2X) { this.large2X = large2X; }

    public String getLarge() { return large; }
    public void setLarge(String large) { this.large = large; }

    public String getMedium() { return medium; }
    public void setMedium(String medium) { this.medium = medium; }

    public String getSmall() { return small; }
    public void setSmall(String small) { this.small = small; }

    public String getPortrait() { return portrait; }
    public void setPortrait(String portrait) { this.portrait = portrait; }

    public String getLandscape() { return landscape; }
    public void setLandscape(String landscape) { this.landscape = landscape; }

    public String getTiny() { return tiny; }
    public void setTiny(String tiny) { this.tiny = tiny; }
}
