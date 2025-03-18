package pl.edu.pja.dziabor.tpo_07;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Code implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private String text;
    private int duration = 0;
    private String unit;
    private LocalDateTime expirationDate;
    public Code() {
    }

    public Code(String id, String text, int duration, String unit) {
        this.id = id;
        this.text = text;
        this.duration = duration;
        this.unit = unit;
        setExpirationDate(duration, unit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @Override
    public String toString() {
        return "Code{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", duration=" + duration +
                ", unit='" + unit + '\'' +
                '}' + expirationDate;
    }

    public void setExpirationDate(int duration, String unit) {
        switch (unit){
            case "s": this.expirationDate = LocalDateTime.now().plusSeconds(duration); break;
            case "m": this.expirationDate = LocalDateTime.now().plusMinutes(duration); break;
            case "h": this.expirationDate = LocalDateTime.now().plusHours(duration); break;
            case "d": this.expirationDate = LocalDateTime.now().plusDays(duration); break;
        }
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

    public boolean isValid() {
        return switch (unit) {
            case "s" -> {
                if (duration < 10) yield false;
                yield duration <= 7776000;
            }
            case "m" -> {
                if (duration <= 0) yield false;
                yield duration <= 129600;
            }
            case "h" -> {
                if (duration <= 0) yield false;
                yield duration <= 2160;
            }
            case "d" -> {
                if (duration <= 0) yield false;
                yield duration <= 90;
            }
            default -> false;
        };
    }
}
