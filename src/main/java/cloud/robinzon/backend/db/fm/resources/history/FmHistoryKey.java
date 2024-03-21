package cloud.robinzon.backend.db.fm.resources.history;

import cloud.robinzon.backend.db.fm.resources.FmEntity;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("unused")
@NoArgsConstructor
public class FmHistoryKey
        implements Serializable {

    private FmEntity fmEntity;
    private Timestamp timestamp;

}