import java.time.LocalDate;
import java.time.ZonedDateTime;

public enum Datas {
    
    DATA_ATUAL(ZonedDateTime.now().toLocalDate()), DATA_30ATRAS(ZonedDateTime.now().toLocalDate().minusDays(30));

    private final LocalDate date;
    
    Datas(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
