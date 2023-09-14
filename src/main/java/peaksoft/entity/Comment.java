package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.entity.IdGen.IdGenerator;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment extends IdGenerator {

    private String comment;
    private LocalDateTime dateOfComment;
@ManyToOne
    private User user;

    @Transient
    public String getTimeAgo() {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(dateOfComment, currentTime);
        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            return minutes + " minutes ago";
        } else if (seconds < 86400) {
            long hours = seconds / 3600;
            return hours + " hours ago";
        } else {
            Period period = Period.between(dateOfComment.toLocalDate(), currentTime.toLocalDate());

            if (period.getYears() > 0) {
                return period.getYears() + " " + getYearText(period.getYears()) + " ago";
            } else if (period.getMonths() > 0) {
                return period.getMonths() + " " + getMonthText(period.getMonths()) + " ago";
            } else if (period.getDays() > 0) {
                return period.getDays() + " " + getDayText(period.getDays()) + " ago";
            }
        }

        return "Just now"; // Если разница менее одного дня
    }

    private String getYearText(long years) {
        return years == 1 ? "year" : "years";
    }

    private String getMonthText(long months) {
        return months == 1 ? "month" : "months";
    }

    private String getDayText(long days) {
        return days == 1 ? "day" : "days";
    }
}
