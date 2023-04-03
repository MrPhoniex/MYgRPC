package org.example.IDE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

//实现Serializable以序列化
@Data
@Builder
public class HelloRequest implements Serializable {
    private String msg;

}
