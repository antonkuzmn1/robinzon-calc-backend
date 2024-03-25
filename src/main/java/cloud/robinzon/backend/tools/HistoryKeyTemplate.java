package cloud.robinzon.backend.tools;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@EqualsAndHashCode
@NoArgsConstructor
public abstract class HistoryKeyTemplate<T>
        implements Serializable {

    private T entity;
    private Timestamp timestamp;

}
