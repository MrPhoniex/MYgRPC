package org.example.IDE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class HelloResponse implements Serializable {
    private String msg;

}
