package com.moviewiki.api.news.domain;

        import java.sql.Timestamp;
        import java.time.LocalDateTime;
        import java.util.Date;

        import javax.persistence.*;

        import lombok.Data;
        import org.hibernate.annotations.CreationTimestamp;
        import org.springframework.data.annotation.CreatedDate;

@Entity
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_SEQUENCE")
    @SequenceGenerator(name = "NEWS_SEQUENCE", sequenceName = "NEWS_SEQUENCE", allocationSize = 1)
    @Column(name = "news_id")
    private Long newsId;

    @Column(name = "news_title", length=200)
    private String newsTitle;

    @CreationTimestamp
    @Column(name = "news_date", updatable = false)
    private LocalDateTime newsDate;

    @Column(name = "news_content", columnDefinition = "CLOB")
    private String newsContent;

    @Column(name = "news_file", length=1000)
    private String newsFile;
}
