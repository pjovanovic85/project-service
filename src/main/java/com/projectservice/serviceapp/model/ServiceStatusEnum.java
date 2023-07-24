package com.projectservice.serviceapp.model;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceStatusEnum {

    ON_FRONT(1, "na prijemu"),
    IN_SERVICE(2, "u servisu"),
    ON_WORK(3, "u radu"),
    RELEASED(4, "izdat");

    private int statusCode;
    private String description;
}

