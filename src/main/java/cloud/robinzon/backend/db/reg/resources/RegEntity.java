package cloud.robinzon.backend.db.reg.resources;

import cloud.robinzon.backend.tools.EntityTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class RegEntity
        extends EntityTemplate {

    @Column(length = 100)
    private String brand;

    @NonNull
    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String part;

    @Column(length = 50)
    private String serial;

    @Column
    private Date buyDate;

    @Column
    private int warrantyMonths;

    @Column(length = 50)
    private String provider;

    public RegEntity update(String brand,
                            String name,
                            String part,
                            String serial,
                            Date buyDate,
                            int warrantyMonths,
                            String title,
                            String description,
                            String provider) {

        if (Objects.equals(this.brand, brand)
                && Objects.equals(this.name, name)
                && Objects.equals(this.part, part)
                && Objects.equals(this.serial, serial)
                && Objects.equals(this.buyDate, buyDate)
                && Objects.equals(this.provider, provider)
                && this.warrantyMonths == warrantyMonths
                && Objects.equals(this.title, title)
                && Objects.equals(this.description, description))
            return null;

        this.brand = brand;
        this.name = name;
        this.part = part;
        this.serial = serial;
        this.buyDate = buyDate;
        this.warrantyMonths = warrantyMonths;
        this.title = title;
        this.description = description;
        this.provider = provider;
        return this;
    }

    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder();
        sb.append("[id=").append(id);
        sb.append("][brand=").append(brand);
        sb.append("][name=").append(name);
        sb.append("][part=").append(part);
        sb.append("][serial=").append(serial);
        sb.append("][buyDate=").append(buyDate);
        sb.append("][warrantyMonths=").append(warrantyMonths);
        sb.append("][provider=").append(provider);
        sb.append("][title=").append(title);
        sb.append("][description=").append(description);
        sb.append("][deleted=").append(deleted).append("]");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("brand", brand);
        map.put("name", name);
        map.put("part", part);
        map.put("serial", serial);
        map.put("buyDate", buyDate);
        map.put("warrantyMonths", warrantyMonths);
        map.put("provider", provider);
        map.put("title", title);
        map.put("description", description);
        map.put("deleted", deleted);
        return map;
    }

}